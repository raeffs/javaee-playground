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
@MethodCallLogged
@Interceptor
public class MethodCallLoggingInterceptor implements Serializable {

    @Inject
    private transient Logger logger;

    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception {
        if (!logger.isTraceEnabled()) {
            return context.proceed();
        }
        String methodIdentifier = getMethodIdentifier(context);
        logger.trace("{} entry", methodIdentifier);
        try {
            return context.proceed();
        } finally {
            logger.trace("{} exit", methodIdentifier);
        }
    }

    private String getMethodIdentifier(InvocationContext context) {
        return String.format("%s.%s", context.getTarget().getClass().getName(), context.getMethod().getName());
    }
}
