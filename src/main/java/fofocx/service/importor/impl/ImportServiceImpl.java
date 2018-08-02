/**
 * Project Name: csv_homework File Name: ImportServiceImpl.java Package Name:
 * fofocx.service.importor.impl Date: Aug 1, 20184:14:09 PM
 *
 */
package fofocx.service.importor.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fofocx.constant.Constant;
import fofocx.dao.TimeSeriesDataDao;
import fofocx.entity.TimeSeriesData;
import fofocx.file.ReadFileChannel;
import fofocx.service.importor.ImportService;

/**
 *
 * @author ChenXiang
 * @ClassName: ImportServiceImpl
 * @version: V1.0
 * @Description: TODO
 * @since 2018-08-01 16:14:09
 *
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Resource
    private TimeSeriesDataDao timeSeriesDataDao;

    @Resource
    private ReadFileChannel ReadFileChannel;

    /*
     * (non-Javadoc)
     * 
     * @see fofocx.service.importor.ImportService#importCsv(java.lang.String)
     */
    @Override
    public void importCsv(String path) throws IOException, InterruptedException {
        File file = new File(path);
        String filename = file.getName();
        if (!filename.matches(Constant.REGEX)) {
            throw new IOException(path + " not found.");
        }
        LocalDate tradingDate = LocalDate.parse(filename.replaceAll(".csv", ""),
                Constant.DATE_FORMAT);
        ReadFileChannel.readFromFile(path, content -> {
            String[] values = content.split(Constant.SEPERATOR);
            String stockCode = values[0];
            values = Arrays.copyOfRange(values, 1, values.length);
            List<TimeSeriesData> entityList = Arrays.stream(values)
                    .map(v -> new TimeSeriesData(tradingDate, stockCode,
                            Float.valueOf(v)))
                    .collect(Collectors.toList());
            timeSeriesDataDao.batchInsert(entityList);
        });
    }

}
