package ch.raphaelfleischlin.playground.aspects.logging;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@ExceptionLogged
@Interceptor
public class ExceptionLoggingInterceptor implements Serializable {

    @Inject
    private transient Logger logger;

    @AroundInvoke
    public Object logException(InvocationContext context) throws Exception {
        try {
            return context.proceed();
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            throw exception;
        }
    }
}
