package br.com.fiap.parking.meter.core.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {

    Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* br.com.fiap.parking.meter.*.controller.*.*(..) )")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String requestId = UUID.randomUUID().toString();
        ObjectMapper mapper = new ObjectMapper();

        LogProperties logProperties = new LogProperties(pjp);
        logProperties.setRequestId(requestId);

        logRequest(logProperties, mapper);
        return logResponse(pjp, logProperties, mapper);
    }

    private Object logResponse(ProceedingJoinPoint pjp, LogProperties logProperties, ObjectMapper mapper) throws Throwable {
        StringBuilder baseMessage = prepareResponseBaseMessage(logProperties);
        try {
            Object response = pjp.proceed();
            log.info(baseMessage.append(" | Response: ").append(mapper.writeValueAsString(response)).toString());
            return response;
        } catch (Exception e) {
            log.info(baseMessage.append(" | Throwed Exception: ").append(e.getClass().getName())
                    .append(" | Exception Message: ").append(e.getMessage()).toString());
            throw e;

        }
    }

        private void logRequest (LogProperties logProperties, ObjectMapper mapper) throws JsonProcessingException {
            String logReqMessage = "Timestamp: " + LocalDateTime.now() +
                    " | RequestId: " + logProperties.getRequestId() +
                    " | " + logProperties.getClassname() + " : " + logProperties.getMethodName() + "()" +
                    " | argumentos: " + mapper.writeValueAsString(logProperties.getArguments());
            log.info(logReqMessage);
        }


    private StringBuilder prepareResponseBaseMessage(LogProperties logProperties) {
        StringBuilder baseMessage = new StringBuilder();
        baseMessage.append("Timestamp: ").append(LocalDateTime.now());
        baseMessage.append(" | RequestId: ").append(logProperties.getRequestId());
        baseMessage.append(" | ").append(logProperties.getClassname()).append(" : ")
                .append(logProperties.getMethodName()).append("()");
        return baseMessage;
    }
}
