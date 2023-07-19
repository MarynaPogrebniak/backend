package de.aittr.products.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "de.aittr.products")
public class ApplicationConfig {

}
