package ch.raphaelfleischlin.playground.database;

import com.googlecode.flyway.core.Flyway;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@Startup
@Singleton
public class MigrationStartupBean {
    
    @Resource(name = "jdbc/playground")
    private DataSource dataSource;
    
    private static final String databaseMigrationScriptLocation = "database";
    
    @PostConstruct
    public void doDatabaseMigration() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(databaseMigrationScriptLocation);
        flyway.migrate();
    }
    
}
