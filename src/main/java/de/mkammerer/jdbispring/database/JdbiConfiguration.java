package de.mkammerer.jdbispring.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration(proxyBeanMethods = false)
class JdbiConfiguration {
    /**
     * Makes {@link Jdbi} available as a bean and registers all {@link JdbiPlugin}s and {@link RowMapper}s on the
     * returned object.
     */
    @Bean
    Jdbi jdbi(DataSource dataSource, List<JdbiPlugin> plugins, List<RowMapper<?>> rowMappers) {
        // Using a TransactionAwareDataSourceProxy makes JDBI automatically aware of Spring managed transactions
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        for (JdbiPlugin plugin : plugins) {
            jdbi.installPlugin(plugin);
        }
        for (RowMapper<?> rowMapper : rowMappers) {
            jdbi.registerRowMapper(rowMapper);
        }
        return jdbi;
    }

    /**
     * Enables JDBI SqlObjects.
     */
    @Bean
    SqlObjectPlugin sqlObjectPlugin() {
        return new SqlObjectPlugin();
    }

    /**
     * Makes {@link TaskDao} available as a bean.
     */
    @Bean
    TaskDao taskDao(Jdbi jdbi) {
        return jdbi.onDemand(TaskDao.class);
    }
}
