package co.com.viveres.susy.microserviceproduct.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
@EntityScan(basePackages = {
		"co.com.viveres.susy.microserviceproduct.entity", 
		"co.com.viveres.susy.microservicecommons.entity"})
@EnableJpaRepositories(basePackages = {
    "co.com.viveres.susy.microserviceproduct.repository", 
	"co.com.viveres.susy.microservicecommons.repository"})
public class ConfigutationApp {
	
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("co.com.viveres.susy.microserviceproduct.controller"))
				.paths(PathSelectors.ant("/v1/**"))
				.build();
	}

}
