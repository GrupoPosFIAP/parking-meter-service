package br.com.fiap.parking.meter.core.aspect;

import java.time.LocalDateTime;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LogAspect {

    Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private ObjectMapper mapper;

    @Around("execution(* br.com.fiap.parking.meter.*.controller.*.*(..) )")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String requestId = UUID.randomUUID().toString();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getName();
        String classname = pjp.getTarget().getClass().toString();
        Object[] arguments = pjp.getArgs();
        log.info("Timestamp: " + LocalDateTime.now() + " | RequestId: " + requestId + " | " + classname + " : " + methodName + "() | argumentos: " + mapper.writeValueAsString(arguments));
        Object response = pjp.proceed();
        log.info("Timestamp: " + LocalDateTime.now() + " | RequestId: " + requestId + " | " + classname + " : " + methodName + "() | Response: " + mapper.writeValueAsString(response));
        return response;
    }

}
