package ua.com.marketplace.armordrive.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("ua.com.marketplace.armordrive.domain")
@EnableJpaRepositories("ua.com.marketplace.armordrive.repos")
@EnableTransactionManagement
public class DomainConfig {
}
