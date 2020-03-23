package guru.springframework.blog;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class BlogPostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPostsApplication.class, args);
        
    }
}
