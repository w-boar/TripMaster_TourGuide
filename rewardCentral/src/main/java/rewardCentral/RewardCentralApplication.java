package rewardCentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RewardCentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardCentralApplication.class, args);
	}

}
