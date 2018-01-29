package matera.systems.cursoferias2018.api.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@ComponentScan("matera.systems.cursoferias2018.api")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserDetailsService userDetailService() {
    	InMemoryUserDetailsManager manager = 
    			new InMemoryUserDetailsManager();

    	UserDetails user = 
    			User.withUsername("usuario")
    			.password("password")
    			.roles("USER")
    			.build();
    	
    	manager.createUser(user);
    	return manager;
    }
    
}
