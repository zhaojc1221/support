package top.support.aop;

import java.lang.annotation.*;

/**
 * <p>lockKey</p>
 * 暂未使用
 * <p>prefix</p>
 * 前缀，方便定位日志；
 * @author zhaojc
 * @create 2022-11-25
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogLinker {
    String lockKey() default "";

    String prefix() default "";
}
