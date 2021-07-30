package org.my.booter;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Felix on 2017/7/7.
 */
@SpringBootApplication
public class Runner extends SpringBootServletInitializer {


    /**
     * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Runner.class);
        application.setBannerMode(Banner.Mode.valueOf("OFF"));

        application.run(args);
    }
}
