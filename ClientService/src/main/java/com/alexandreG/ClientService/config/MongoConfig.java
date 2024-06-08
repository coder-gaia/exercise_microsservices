/**
 * @author alexandre.gaia
 */

package com.alexandreG.ClientService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alexandreG.ClientService.repository")
public class MongoConfig {
}
