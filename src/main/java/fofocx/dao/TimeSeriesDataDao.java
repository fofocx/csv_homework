/**
 * Project Name: csv_homework
 * File Name: TimeSeriesDataDao.java
 * Package Name: fofocx.dao
 * Date: Aug 1, 20183:59:16 PM
 *
 */
package fofocx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fofocx.entity.TimeSeriesData;

/**
 *
 * @author ChenXiang
 * @ClassName: TimeSeriesDataDao
 * @version: V1.0
 * @Description: TODO
 * @since 2018-08-01 15:59:16 
 *
 */
@Mapper
@Transactional(
        propagation = Propagation.REQUIRED)
public interface TimeSeriesDataDao {
    int insert(TimeSeriesData timeSerialData);
    int batchInsert(List<TimeSeriesData> list);

}
