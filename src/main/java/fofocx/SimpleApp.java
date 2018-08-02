package fofocx;
/**
 * 
 */


import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author ChenXiang
 * @ClassName: SimpleApp
 * @version: V1.0
 * @Description: TODO
 * @since 2018-07-31 10:01:12
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("fofocx.dao")
@EnableAsync
public class SimpleApp {
    private static final Log LOGGER = LogFactory.getLog(SimpleApp.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return arg -> {
            LOGGER.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LOGGER.info(beanName);
            }
        };
    }

}
