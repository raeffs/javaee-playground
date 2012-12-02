package ch.raphaelfleischlin.playground.aspects.logging;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

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
            logger.logp(Level.WARNING, context.getTarget().getClass().getName(), context.getMethod().getName(), exception.getMessage(), exception);
            throw exception;
        }
    }
    
}
