package org.my.booter;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Felix on 2017/7/7.
 */
@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Runner.class);
        application.setBannerMode(Banner.Mode.valueOf("OFF"));

        application.run(args);
    }
}
