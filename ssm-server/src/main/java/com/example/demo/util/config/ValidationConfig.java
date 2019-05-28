package com.example.demo.util.config;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {
	
	@Bean
    public Validator getValidator() {
        Validator validator = Validation.byDefaultProvider().
        configure().
        messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("i18n/validation/cutomerValidationMessage"))).
        buildValidatorFactory().getValidator();
        return validator;
    }

}
