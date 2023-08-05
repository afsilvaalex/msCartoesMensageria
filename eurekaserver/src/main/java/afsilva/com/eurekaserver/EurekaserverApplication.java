package afsilva.com.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {


	// para mensageria foi usado o rabbitMQ no docker / usuario e senha = guest
	//docker run -it --name rabbitmq3.9 -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
	// para segurança keycloak
	// docker run --name keycloak18.0.0 -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin  quay.io/keycloak/keycloak:18.0.0 start-dev
	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
