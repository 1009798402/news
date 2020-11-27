package org.jianchunchen.model.response;

import java.lang.annotation.*;

/**
 * @author jianchun.chen
 * @date 2020/8/25 11:25
 * <p>
 * -----返回结果不需要格式化的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreResultFormat {

}
