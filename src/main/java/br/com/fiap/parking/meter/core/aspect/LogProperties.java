package br.com.fiap.parking.meter.core.aspect;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@Getter
@Setter
public class LogProperties {

    private String requestId;
    private MethodSignature signature;
    private String methodName;
    private String classname;
    private Object[] arguments;

    public LogProperties(ProceedingJoinPoint pjp) {
        this.signature = (MethodSignature) pjp.getSignature();
        this.methodName = signature.getName();
        this.classname = pjp.getTarget().getClass().toString();
        this.arguments = pjp.getArgs();
    }

    public LogProperties() {
    }

}
