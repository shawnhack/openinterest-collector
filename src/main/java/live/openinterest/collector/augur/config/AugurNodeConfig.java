package live.openinterest.collector.augur.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "augEntityManagerFactory", basePackages = {
        "live.openinterest.collector.augur.repository" })
public class AugurNodeConfig {

    @Autowired
    private Environment env;

    @Bean(name = "augDataSource")
    @ConfigurationProperties(prefix = "aug.datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("aug.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("aug.datasource.url"));

        return dataSource;
    }

    @Bean(name = "augEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("augDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("live.openinterest.collector.augur.model").persistenceUnit("aug")
                .build();
    }

    @Bean(name = "augTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("augEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
