package org.skistation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for the web MVC.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    /**
     * Adds CORS mappings.
     *
     * @param registry the CORS registry.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4321")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}