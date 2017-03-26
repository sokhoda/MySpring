package core;

import core.loggers.CacheFileEventLogger;
import core.loggers.ConsoleEventLogger;
import core.loggers.EventLogger;
import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Data
public class StatisticsAspect {
    private static final Integer MAX_CONSOLELOGGER_COUNT = 1;

    private static Logger LOG = LogManager.getLogger(LoggingAspect.class);
    private Map<Class<?>, Integer> pointCutMethExecCount = new HashMap<>();
    @Autowired
    @Qualifier("fileEventLogger")
    private EventLogger otherLogger;

    @Pointcut("execution(* *.logEvent(Event))")
    private void allLogEventMethods() {
    }

    @Pointcut("execution(* *.ConsoleEventLogger.logEvent(Event))")
    private void consoleLogEventMethods() {
    }

//    @AfterReturning(
//            pointcut = "allLogEventMethods()",
//            returning = "result")
//    public void logAfter(JoinPoint joinPoint, Object result) {
//        Class<?> clazz = joinPoint.getTarget().getClass();
//        if (!pointCutMethExecCount.containsKey(clazz)) {
//            pointCutMethExecCount.put(clazz, 0);
//        }
//        pointCutMethExecCount.put(clazz, pointCutMethExecCount.get(clazz) + 1);
//    }
    @Before("allLogEventMethods()")
    public void logAfter(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!pointCutMethExecCount.containsKey(clazz)) {
            pointCutMethExecCount.put(clazz, 0);
        }
        pointCutMethExecCount.put(clazz, pointCutMethExecCount.get(clazz) + 1);
    }

    @Around("consoleLogEventMethods() && args(evt)")
    public void doMaxCount(ProceedingJoinPoint jp, Object evt) throws Throwable {
        Integer currentCount = 0;
        if (pointCutMethExecCount.containsKey(ConsoleEventLogger.class)) {
            currentCount = pointCutMethExecCount.get(ConsoleEventLogger.class);
        }

        if (currentCount < MAX_CONSOLELOGGER_COUNT) {
            jp.proceed(new Object[]{evt});
        }
        else {
            otherLogger.logEvent((Event) evt);
        }
    }
}
