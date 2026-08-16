//package com.flipkart.orderservice;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//
//@SpringBootApplication
//@EnableFeignClients
//public class OrderServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(OrderServiceApplication.class, args);
//	}
//
//}



package com.flipkart.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);

		try {
			Class.forName("io.micrometer.tracing.Tracer");
			System.out.println(">>> TRACER CLASS FOUND ON CLASSPATH <<<");
		} catch (ClassNotFoundException e) {
			System.out.println(">>> TRACER CLASS NOT FOUND — DEPENDENCY MISSING FROM RUNTIME CLASSPATH <<<");
		}
	}

}