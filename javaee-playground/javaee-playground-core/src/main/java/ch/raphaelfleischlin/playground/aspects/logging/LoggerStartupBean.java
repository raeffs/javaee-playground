package ch.raphaelfleischlin.playground.aspects.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@Startup
@DependsOn("MigrationStartupBean")
@Singleton
public class LoggerStartupBean {
    
    private static final String logbackConfigurationFile = "/META-INF/logback.xml";

    @PostConstruct
    public void initializeLogger() {
        
        String configurationFile = getClass().getResource(logbackConfigurationFile).getFile();
        
        LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        
        try {
            configurator.doConfigure(configurationFile);
        } catch (JoranException ex) {
            
        } finally {
            StatusPrinter.print(context);
        }
        
        Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerStartupBean.class);

        logger.error("An error msg...");
        logger.warn("A warn msg...");
        logger.info("A info msg...");
        logger.debug("A debug msg...");
        logger.trace("A trace msg...");

    }
    
}
