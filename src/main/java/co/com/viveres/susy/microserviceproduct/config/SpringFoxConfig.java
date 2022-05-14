package co.com.viveres.susy.microserviceproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)				
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo())
			.useDefaultResponseMessages(false);
	}
	
	ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("TDS Product API")
            .description("API para la gestión de productos de TDS")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("Pedro luis Chavez Castro","", "pedro.chavezcastro@outlook.com"))
            .build();
    }

}
