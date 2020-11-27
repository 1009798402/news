package org.jianchunchen.utils.common;

/**
 * 分片桶字段算法
 * @author jianchun.chen
 */
public class BurstUtils {

    public final static String SPLIT_CHAR = "-";

    /**
     * 用-符号链接
     * @param filed 字段
     * @return 结果
     */
    public static String encrypt(Object... filed){
        StringBuilder sb  = new StringBuilder();
        if(filed!=null&&filed.length>0) {
            sb.append(filed[0]);
            for (int i = 1; i < filed.length; i++) {
                sb.append(SPLIT_CHAR).append(filed[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 默认第一组
     * @param filed 字段
     * @return 结果
     */
    public static String groupOne(Object... filed){
        StringBuilder sb  = new StringBuilder();
        if(filed!=null&&filed.length>0) {
            sb.append("0");
            for (Object o : filed) {
                sb.append(SPLIT_CHAR).append(o);
            }
        }
        return sb.toString();
    }
}
