package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.function.DoubleToIntFunction;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
@ComponentScan(basePackages = "service")
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = "repository")

public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
        System.out.println("Success!");
    }

    @Component
    public class OriginFilter implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                    .allowCredentials(true);
        }
    }
}
