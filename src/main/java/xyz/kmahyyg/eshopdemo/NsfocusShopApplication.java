package xyz.kmahyyg.eshopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;


@SpringBootApplication
public class NsfocusShopApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(NsfocusShopApplication.class, args);
    }

}
