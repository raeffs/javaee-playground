package ch.raphaelfleischlin.playground.aspects.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@Startup
@Singleton
public class LoggerStartupBean {

    @PostConstruct
    private void startup() {

        Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerStartupBean.class);

        LoggerContext lc = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        StatusManager statusManager = lc.getStatusManager();
        OnConsoleStatusListener onConsoleListener = new OnConsoleStatusListener();
        statusManager.add(onConsoleListener);


        String file = this.getClass().getResource("/META-INF/logback.xml").getFile();
        //System.setProperty("logback.configurationFile", file);

        StatusPrinter.print(lc);
        /*
         JoranConfigurator configurator = new JoranConfigurator();
         configurator.setContext(lc);
         //lc.reset();
         try {
         String file = this.getClass().getResource("/META-INF/logback.xml").getFile();
         logger.error(file);
         configurator.doConfigure(file);
         } catch (JoranException ex) {
         java.util.logging.Logger.getLogger(LoggerStartupBean.class.getName()).log(Level.SEVERE, null, ex);
         }
         configurator.setContext(lc);
         */

        logger.error("Hallo Error!");
        logger.warn("Hallo Warn!");


    }

    @PreDestroy
    private void shutdown() {
    }
}
