package top.support.aop.logLinker;

import org.apache.log4j.NDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import top.support.aop.LogLinker;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 日志链路器
 *  拼接参数、时间戳和随机数生成关键字；加在方法可定位一整个请求链路；
 *
 * <b>log4j</b>
 * 关键字在配置中为 |%x| 注入
 *
 * <b>slf4j</b>
 * 关键字在配置中为 |%X{LINKER}| 注入
 * @author zhaojc
 * create 2022-08-19
 * update 2022-11-29
 */
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("@annotation(top.support.aop.LogLinker)")
    public void annotationPointCut(){

    }

    @Before("annotationPointCut()")
    public void insertIntoLog(JoinPoint joinPoint){
        String threadLock = "";
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogLinker annotation = method.getAnnotation(LogLinker.class);
        String tamp = String.valueOf(System.currentTimeMillis());

        String s = annotation.lockKey();
        String prefix = annotation.prefix();

        if ("".equals(s)){
            int s2 = new Random().nextInt(1000000);
            threadLock = tamp+s2;
        }else{
            threadLock = s;
        }

        if (!"".equals(prefix)){
            threadLock = prefix+threadLock;
        }
        NDC.push(threadLock);
        MDC.put("LINKER",threadLock);
    }

    @After("annotationPointCut()")
    public void deleteIntoLog(JoinPoint joinPoint){
        MDC.remove("LINKER");
        NDC.clear();
    }
}
