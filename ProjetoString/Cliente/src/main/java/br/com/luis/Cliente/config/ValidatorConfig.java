package br.com.luis.Cliente.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author br.com.luis
 *
 */
@Configuration
public class ValidatorConfig {

	@Bean
    public LocalValidatorFactoryBean validatorFactory () {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource);
	    return  bean;
    }
}
