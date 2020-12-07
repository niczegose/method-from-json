package pl.kurs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.kurs.java.service.MethodService;

@SpringBootApplication
public class MvcJsonMethodApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcJsonMethodApplication.class, args);
    }

    @Bean()
    public MethodService getMethodService(){
        return new MethodService();
    }
}
