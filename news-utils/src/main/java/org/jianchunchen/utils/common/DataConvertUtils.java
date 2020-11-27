package org.jianchunchen.utils.common;

import org.apache.commons.lang3.StringUtils;
import org.jianchunchen.model.annotation.DateConvert;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据类转换
 * @author jianchun.chen
 */
public class DataConvertUtils {

    /**
     * 自定义数据类型转换
     *
     * @param value value
     * @param annotations annotations
     * @return Object
     */
    public static Object convert(Object value, Annotation[] annotations) {
        Object convertValue = value;
        DateConvert dateConvert = getDateConvert(annotations);
        if (null != dateConvert) {
            convertValue = dateConvert(value, dateConvert);
        }
        return convertValue;
    }


    public static Object unConvert(Object value, Annotation[] annotations) {
        Object convertValue = value;
        DateConvert dateConvert = getDateConvert(annotations);
        if (null != dateConvert) {
            convertValue = unDateConvert(value, dateConvert);
        }
        return convertValue;
    }

    /**
     * value
     *
     * @param value value
     * @param dateConvert dateConvert
     * @return Object
     */
    private static Object unDateConvert(Object value, DateConvert dateConvert) {
        Object unConvertValue = null;
        if (value instanceof Date && null != dateConvert) {
            String datePattern = dateConvert.value();
            if (StringUtils.isNotEmpty(datePattern)) {
                unConvertValue = DateUtils.dateToString((Date) value, datePattern);
            }
        }
        return unConvertValue;
    }

    /**
     * Date 数据类型转换
     *
     * @param value value
     * @param dateConvert dateConvert
     * @return return
     */
    private static Object dateConvert(Object value, DateConvert dateConvert) {
        Object dateValue = null;
        String strDateValue = toString(value);
        if (StringUtils.isNotEmpty(strDateValue) && null != dateConvert) {
            String datePattern = dateConvert.value();
            if (StringUtils.isNotEmpty(datePattern)) {
                dateValue = DateUtils.stringToDate(strDateValue, datePattern);
            }
        }
        return dateValue;
    }

    /**
     * 获取DateConvert 转换注解
     *
     * @param annotations annotations
     * @return return
     */
    public static DateConvert getDateConvert(Annotation[] annotations) {
        DateConvert dateConvert = null;
        if (null != annotations && annotations.length > 0) {
            List<Annotation> annotationList = Arrays.stream(annotations).filter(annotation -> annotation instanceof DateConvert).collect(Collectors.toList());
            if (!annotationList.isEmpty()) {
                dateConvert = (DateConvert) annotationList.get(0);
            }
        }
        return dateConvert;
    }


    public static String toString(Object value) {
        String serValue = "";
        if (null != value) {
            serValue = value.toString();
        }
        return serValue;
    }
}
