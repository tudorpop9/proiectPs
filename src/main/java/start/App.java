package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
}
