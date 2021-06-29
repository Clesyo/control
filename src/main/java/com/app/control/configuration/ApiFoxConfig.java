package com.app.control.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class ApiFoxConfig {

	@Bean
	public Docket api() {
		// TODO Auto-generated method stub
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, responses());
	}

	private ArrayList<Response> responses() {
		return new ArrayList<Response>() {
			{
				add(new ResponseBuilder().code("500").description("500 message").build());
				add(new ResponseBuilder().code("403").description("Forbidden!").build());
				add(new ResponseBuilder().code("201").description("Resposta ok").build());
			}
		};
	}

}
