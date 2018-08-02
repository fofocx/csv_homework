/**
 * 控制器
 */
package fofocx.file.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Charsets;

import fofocx.constant.Constant;

/**
 *
 * @author ChenXiang
 * @ClassName: FileNioHandler
 * @version: V1.0
 * @Description: 行读取器按行读取文件内容，每次调用只执行当前行处理，异步调用后续
 * @since 2018-07-29 14:37:00
 *
 */
public class FileNioHandler implements CompletionHandler<Integer, ByteBuffer> {
    private static final Log LOGGER = LogFactory.getLog(FileNioHandler.class);
    private final AsynchronousFileChannel fileChannel;
    private int position = 0;
    private StringBuilder read = new StringBuilder();
    private Consumer<String> consumer;
    private volatile int lineNum = 0;
    private CountDownLatch cdl;

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.channels.CompletionHandler#completed(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        if (result > -1) {
            String line = readLine(attachment);
            if (StringUtils.isNotBlank(line)) {
                consumer.accept(line);
            }
        } else {
            // Close channel.
            try {
                fileChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        cdl.countDown();
    }

    private String readLine(ByteBuffer attachment) {
        attachment.flip();
        boolean hasCR = false;
        int remaining = attachment.remaining();
        for (int i = 0; i < remaining; i++) {
            // win下回车先于换行？
            if (attachment.get() == Constant.CR) {
                hasCR = true;
                if (attachment.get() == Constant.LF) {
                    i++;
                }
                break;
            }
        }
        byte[] bytes = new byte[attachment.position()];
        attachment.rewind();
        attachment.get(bytes);
        read.append(new String(bytes, Charsets.UTF_8));
        String line = null;
        if (hasCR) {
            lineNum++;
            if (lineNum == 1) {
                LOGGER.info("表头: " + read.toString());
            } else {
                line = read.toString();
            }
            read.delete(0, read.length());
        }
        attachment.clear();
        position += bytes.length;
        fileChannel.read(attachment, position, attachment, this);
        return line;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable,
     * java.lang.Object)
     */
    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        // TODO Auto-generated method stub
        LOGGER.error("error");
        LOGGER.error(exc.getMessage(), exc);;
        try {
            fileChannel.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cdl.countDown();

    }

    public FileNioHandler(@NotNull AsynchronousFileChannel fileChannel,
            @NotNull Consumer<String> consumer, CountDownLatch cdl) {
        super();
        this.fileChannel = fileChannel;
        this.consumer = consumer;
        this.cdl = cdl;
    }

}
