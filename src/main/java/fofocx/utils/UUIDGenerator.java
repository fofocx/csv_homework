/**
 * 
 */
package fofocx.utils;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author ChenXiang
 * @ClassName: UUIDGenerator
 * @version: V1.0
 * @Description: TODO
 * @since 2018-07-29 18:14:30
 *
 */
public class UUIDGenerator {

    /**
     * 用type version1的uuid生成timebased uuid, 并去掉"-", 结果为32位字符串
     * @return uuid
     */
    public static String generateId() {
        return new UUID(System.currentTimeMillis(), new Random().nextLong())
                .toString().replaceAll("-", "");
    }

}
