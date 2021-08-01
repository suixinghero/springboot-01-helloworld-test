package org.xujin.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xujin.springboot.config.BeanConfig;

/**
 * @author xujin
 * @package-name org.xujin.springboot
 * @createtime 2020-02-18 14:05
 *
 * @SpringBootApplication 来标注一个主程序类，说明这是一个springboot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        //sping应用启动起来
        SpringApplication.run(new Object[]{HelloWorldMainApplication.class,BeanConfig.class},args);
    }
}
