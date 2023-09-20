package br.com.fiap.parking.meter.core.interceptor;

import br.com.fiap.parking.meter.core.annotations.PaymentPath;
import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.exception.EntityNotFoundException;
import br.com.fiap.parking.meter.exception.PaymentRequiredException;
import br.com.fiap.parking.meter.condutor.repository.CondutorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PaymentInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(PaymentInterceptor.class);

    @Autowired
    private CondutorRepository condutorRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        PaymentPath paymentPath = null;
        try {
            paymentPath = ((HandlerMethod) handler).getMethod().getAnnotation((PaymentPath.class));
        } catch (Exception e) {
            logger.info("Endpoint inválido.");
        }

        if (paymentPath != null) {
            String id = request.getHeader("condutorId");
            if (id != null) {

                // Busque um condutor pelo ID desse condutor e se não achar retorne um erro chamado EntityNotFound com a mensagem "Usuário inválido"
                Condutor entidade = this.condutorRepository.findById(Long.parseLong(id)).orElseThrow(() -> new EntityNotFoundException("Usuário inválido"));
                logger.info("Para acessar este recurso é necessário cadastrar um método de pagamento. E-mail: " + entidade.getEmail());
            }
            throw new PaymentRequiredException("Para realizar esta ação, é necessário cadastrar um método de pagamento");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Path: ").append(request.getRequestURI())
                .append(" Método: ").append(request.getMethod());
        logger.info("Requisição interceptada: " + sb);
        return true;
    }

}
