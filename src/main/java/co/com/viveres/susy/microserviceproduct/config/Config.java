package co.com.viveres.susy.microserviceproduct.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EntityScan(basePackages = {
		"co.com.viveres.susy.microserviceproduct.entity", 
		"co.com.viveres.susy.microservicecommons.entity"})
@EnableJpaRepositories(basePackages = {
    "co.com.viveres.susy.microserviceproduct.repository", 
	"co.com.viveres.susy.microservicecommons.repository"})
public class Config {}
