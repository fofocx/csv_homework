/**
 * Project Name: csv_homework File Name: ImportController.java Package Name:
 * fofocx.controller Date: Aug 1, 20184:36:47 PM
 *
 */
package fofocx.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fofocx.service.importor.ImportService;

/**
 *
 * @author ChenXiang
 * @ClassName: ImportController
 * @version: V1.0
 * @Description: TODO
 * @since 2018-08-01 16:36:47
 *
 */
@RestController
@RequestMapping("/time-series-data")
public class ImportController {
    private static final Log LOGGER = LogFactory.getLog(ImportController.class);
    @Resource
    private ImportService importService;

    private static final File UPLOAD_PATH = new File(
            ImportService.class.getResource("/upload").getFile());

    @PostMapping("/csv")
    @ResponseBody
    public String importFile(Model model,
            @RequestParam("file") MultipartFile file) {
        String result;
        try {
            String path = UPLOAD_PATH.getPath() + File.separator
                    + System.currentTimeMillis();
            File upload = new File(path);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            path += File.separator + file.getOriginalFilename();
            upload = new File(path);
            if (!upload.exists()) {
                upload.createNewFile();
            }
            file.transferTo(upload);
            importService.importCsv(path);
            result = "上传成功";
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            result = "上传失败";
        }
        return result;

    }

    @GetMapping("/info")
    @ResponseBody
    public String getInfo() {
        return "nothing";
    }

}
