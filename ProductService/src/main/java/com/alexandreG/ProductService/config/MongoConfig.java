/**
 * @author alexandre.gaia
 */

package com.alexandreG.ProductService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alexandreG.ProductService.repository")
public class MongoConfig {

}
