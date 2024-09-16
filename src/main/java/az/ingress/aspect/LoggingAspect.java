package az.ingress.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("within(@az.ingress.annotation.Log *)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        var params = Arrays.toString(joinPoint.getArgs());

        try {
            log.info("ActionLog.{}.start {}", methodName, params);

            var response = joinPoint.proceed();

            logEndAction(response, methodName);

            return response;
        } catch (Throwable throwable) {
            log.error("ActionLog.{}.error ", methodName, throwable);
            throw throwable;
        }
    }

    private void logEndAction(Object response, String methodName) {
        if (response != null) log.info("ActionLog.{}.end response {}", methodName, response);
        else log.info("ActionLog.{}.end", methodName);
    }
}