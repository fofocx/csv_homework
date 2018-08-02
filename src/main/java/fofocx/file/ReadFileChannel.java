/**
 * 文件通道
 */
package fofocx.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fofocx.file.handler.FileNioHandler;
import fofocx.utils.FileUtils;

/**
 *
 * @author ChenXiang
 * @ClassName: ReadFileChannel
 * @version: V1.0
 * @Description: 设置文件异步读取通道
 * @since 2018-07-28 17:16:15
 *
 */
@Component
public class ReadFileChannel {
    @Value("${channel.capacity}")
    private int capacity;

    public void readFromFile(String path, Consumer<String> importBehavior)
            throws IOException, InterruptedException {
        Path file = Paths.get(path);
        if (!file.toFile().exists()) {
            throw new IOException(path + "不存在");
        }
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel
                .open(file, StandardOpenOption.READ);
        int fileLines = FileUtils.getLineNum(file.toFile());
        CountDownLatch cdl = new CountDownLatch(fileLines);
        CompletionHandler<Integer, ByteBuffer> handler = new FileNioHandler(
                fileChannel, importBehavior, cdl);
        final ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
        fileChannel.read(byteBuffer, 0, byteBuffer, handler);
        cdl.await(Long.valueOf(fileLines).longValue() * 20000L,
                TimeUnit.MILLISECONDS);
    }

}
