/**
 * 常量
 */
package fofocx.constant;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author ChenXiang
 * @ClassName: Constant
 * @version: V1.0
 * @Description: 常量
 * @since 2018-07-30 14:41:37
 *
 */
public class Constant {
    public static final int LF = 10; // 换行
    public static final int CR = 13; // 回车
    public static final String SEPERATOR = " ";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    /**
     * REGEX yyyy-MM-dd.csv过滤
     */
    public static final String REGEX = "^((((19|20)\\d{2})-(0?[13578]|1[02])"
            + "-(0?[1-9]|[12]\\d|3[01]))"
            + "|(((19|20)\\d{2})-(0?[469]|11)-(0?[1-9]|[12]\\d|30))"
            + "|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))"
            + "|((((19|20)([13579][26]|[2468][048]|0[48]))"
            + "|(2000))-0?2-(0?[1-9]|[12]\\d)))\\.csv$";
}
