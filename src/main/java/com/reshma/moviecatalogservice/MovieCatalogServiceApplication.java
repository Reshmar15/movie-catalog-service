package com.reshma.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import resources.MovieCatalogResource;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackageClasses = MovieCatalogResource.class)
public class MovieCatalogServiceApplication {

	@Bean
	public WebClient.Builder getWebClient(){
		return    WebClient.builder();
		
	}
	
	@Bean("restTemplate")
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
