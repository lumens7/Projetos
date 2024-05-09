package br.com.luis.Cliente.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author br.com.luis
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.luis.Cliente.repository")
public class MongoConfig {

}
