package core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {
    private static Logger LOG = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* *.logEvent(Event))")
    private void allLogEventMethods() {
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {

        LOG.info("BEFORE:" +
                joinPoint.getTarget().getClass().getSimpleName() +
                " " + joinPoint.getSignature().getName()
        );
    }

    @AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
    }

}
