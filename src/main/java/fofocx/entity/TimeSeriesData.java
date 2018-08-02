/**
 * 表对象
 */
package fofocx.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import fofocx.utils.UUIDGenerator;

/**
 *
 * @author ChenXiang
 * @ClassName: TimeSeriesData
 * @version: V1.0
 * @Description: 实体代表对应的表time_series_data
 * @since 2018-07-29 16:17:56
 *
 */
public class TimeSeriesData implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5699801207141827922L;
    private String itemId;
    private LocalDate tradingDate;
    @Size(
            max = 6,
            min = 6,
            message = "股票代码是6位")
    private String stockCode;
    @Digits(
            fraction = 2,
            integer = 2000)
    private float itemValue;

    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     *            the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the tradingDate
     */
    public LocalDate getTradingDate() {
        return tradingDate;
    }

    /**
     * @param tradingDate
     *            the tradingDate to set
     */
    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    /**
     * @return the stockCode
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * @param stockCode
     *            the stockCode to set
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    /**
     * @return the itemValue
     */
    public float getItemValue() {
        return itemValue;
    }

    /**
     * @param itemValue
     *            the itemValue to set
     */
    public void setItemValue(float itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * 实例化
     * 
     * @param tradingDate
     * @param stockCode
     * @param itemValue
     */
    public TimeSeriesData(LocalDate tradingDate, @Size(
            max = 6,
            min = 6,
            message = "股票代码是6位") String stockCode,
            @Digits(
                    fraction = 2,
                    integer = 2000) float itemValue) {
        super();
        this.itemId = UUIDGenerator.generateId();
        this.tradingDate = tradingDate;
        this.stockCode = stockCode;
        this.itemValue = itemValue;
    }

    /**
     * Constructor
     */
    public TimeSeriesData() {
        super();
    }

}
