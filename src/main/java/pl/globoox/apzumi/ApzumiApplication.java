package pl.globoox.apzumi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import pl.globoox.apzumi.service.PostService;


@SpringBootApplication
@EntityScan("pl.globoox.apzumi.model")
@EnableJpaRepositories("pl.globoox.apzumi.repository")
@EnableScheduling
public class ApzumiApplication {

    @Autowired
    private PostService postService;

    public static void main(String[] args) {
        SpringApplication.run(ApzumiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            postService.downloadData();
        };
    }
}
