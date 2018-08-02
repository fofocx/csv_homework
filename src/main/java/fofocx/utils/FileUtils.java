/**
 * Project Name: csv_homework File Name: FileUtils.java Package Name:
 * fofocx.utils Date: Aug 1, 20188:26:08 PM
 *
 */
package fofocx.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author ChenXiang
 * @ClassName: FileUtils
 * @version: V1.0
 * @Description: TODO
 * @since 2018-08-01 20:26:08
 *
 */
public class FileUtils {

    // 文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName)
            throws Exception {

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static int getLineNum(File file) throws IOException {
        if (file.exists()) {
            long fileLength = file.length();
            try (LineNumberReader reader = new LineNumberReader(
                    new FileReader(file))) {
                reader.skip(fileLength);
                int lines = reader.getLineNumber();
                return lines;
            } catch (IOException e) {
                throw e;
            }
        } else {
            throw new FileNotFoundException("File does not exists!");
        }
    }
}
