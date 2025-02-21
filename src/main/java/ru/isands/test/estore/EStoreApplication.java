package ru.isands.test.estore;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
@OpenAPIDefinition(servers = { @Server(url = "/", description = "EStore Server") })
@EnableJpaRepositories(entityManagerFactoryRef = "dataSourceEntityManagerFactory", transactionManagerRef = "dataSourceTransactionManager")
public class EStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStoreApplication.class, args);
	}

    @Bean
    public LocalContainerEntityManagerFactoryBean dataSourceEntityManagerFactory(
            @Qualifier("dataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("ru.isands.test.estore")
                .persistenceUnit("Default")
                .build();
    }

    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(
            @Qualifier("dataSourceEntityManagerFactory") LocalContainerEntityManagerFactoryBean dataSourceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(dataSourceEntityManagerFactory.getObject()));
    }
}
