package com.cleox.quickcart.order_service_api;

import com.cleox.quickcart.order_service_api.service.OderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RequiredArgsConstructor
public class OrderServiceApiApplication implements CommandLineRunner {

private final OderStatusService orderStatusService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Conected....");
		initializeStatus();

	}
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApiApplication.class, args);
	}



	private void initializeStatus(){
		orderStatusService.initializeStatusList();
	}
}
