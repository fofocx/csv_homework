/**
 * Project Name: csv_homework File Name: ImportService.java Package Name:
 * fofocx.service.importor Date: Aug 1, 20184:03:31 PM
 *
 */
package fofocx.service.importor;

import java.io.IOException;

/**
 *
 * @author ChenXiang
 * @ClassName: ImportService
 * @version: V1.0
 * @Description: TODO
 * @since 2018-08-01 16:03:31
 *
 */
public interface ImportService {
    void importCsv(String path) throws IOException, InterruptedException;

}
