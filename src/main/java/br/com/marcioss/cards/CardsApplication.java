package br.com.marcioss.cards;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScans({@ComponentScan("br.com.marcioss.cards.controller")})
@EnableJpaRepositories("br.com.marcioss.cards.repository")
@EntityScan("br.com.marcioss.cards.model")
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry){
		return new TimedAspect(registry);
	}
}
