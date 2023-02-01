package coms309.RoundTrip.demo;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@SpringBootApplication
@EnableJpaRepositories
@EnableWebSocket
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Bean(name = "this")
//	public LocalSessionFactoryBean sessionFactory() {
//	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//	return sessionFactory;
//	}
	
}

