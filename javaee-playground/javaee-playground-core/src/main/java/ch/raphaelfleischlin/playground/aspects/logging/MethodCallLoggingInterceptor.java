package ch.raphaelfleischlin.playground.aspects.logging;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@MethodCallLogged
@Interceptor
public class MethodCallLoggingInterceptor implements Serializable {
    
    @Inject
    private transient Logger logger;
    
    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception {
        logger.entering(context.getTarget().getClass().getName(), context.getMethod().getName());
        try {
            return context.proceed();
        } finally {
            logger.exiting(context.getTarget().getClass().getName(), context.getMethod().getName());
        }
    }
    
}
