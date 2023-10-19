package br.com.fiap.parking.meter.core.aspect;

import br.com.fiap.parking.meter.exception.ExceptionHandlerControllerAdvice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Aspect
public class LogAspect {

    Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
    public void restcontroller() {}

//    @Around("execution(* br.com.fiap.parking.meter.*.controller..*(..))")
    @Around("restcontroller() ")
    public Object logaItens(ProceedingJoinPoint joinPoint) throws Throwable {

        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ObjectMapper objectMapper = new ObjectMapper();

        // Define informações contextuais no MDC
        MDC.put("userID", joinPoint.getSignature().toShortString());
        MDC.put("host", request.getRemoteHost());
        MDC.put("req_uri", request.getRequestURI());
        MDC.put("req_query_params", request.getQueryString());
        MDC.put("sessionID", "abcdef");


        Object target = joinPoint.getTarget();

        var result = joinPoint.proceed();
        String json = objectMapper.writeValueAsString(result);
        var responseBody = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {}).get("body");
        MDC.put("response", objectMapper.writeValueAsString(responseBody));

        if(joinPoint.getTarget() instanceof ExceptionHandlerControllerAdvice) {
            LOG.error("deu ruim");
            return result;
        }

        LOG.info("Exemplo de mensagem de log com MDC");

        // Limpa as informações do MDC após o uso
        MDC.clear();
        return result;
    }

}
