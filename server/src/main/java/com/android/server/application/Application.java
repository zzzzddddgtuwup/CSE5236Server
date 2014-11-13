package com.android.server.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.android.server.answerrepository.*;
import com.android.server.forumrepository.*;
import com.android.server.questionrepository.*;
import com.android.server.userrepository.*;


//Tell Spring to automatically inject any dependencies that are marked in
//our classes with @Autowired
@EnableAutoConfiguration
// Tell Spring to automatically create a JPA implementation of our
// VideoRepository
@EntityScan(basePackageClasses={Answer.class,Forum.class,Question.class,User.class})
@EnableJpaRepositories(basePackageClasses={AnswerRepository.class,UserRepository.class,
		ForumRepository.class,QuestionRepository.class})
// Tell Spring that this object represents a Configuration for the
// application
@Configuration
// Tell Spring to turn on WebMVC (e.g., it should enable the DispatcherServlet
// so that requests can be routed to our Controllers)
@EnableWebMvc
// Tell Spring to go and scan our controller package (and all sub packages) to
// find any Controllers or other components that are part of our applciation.
// Any class in this package that is annotated with @Controller is going to be
// automatically discovered and connected to the DispatcherServlet.
@ComponentScan(basePackages={"com.android.server.controller"})
public class Application {
	
	// Tell Spring to launch our app!
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Value("${AWS_ACCESS_KEY_ID}")
//    private String amazonAWSAccessKey;
//
//    @Value("${AWS_SECRET_KEY}")
//    private String amazonAWSSecretKey;
//    
//    @Bean
//    public AmazonRDS rdsClient() {
//        return new AmazonRDSClient(amazonAWSCredentials());
//    }
//    
//    @Bean
//    public AWSCredentials amazonAWSCredentials() {
//        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
//    }
}
