package com.javatechie.lambda.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.javatechie.lambda.example.domain.Order;
import com.javatechie.lambda.example.repository.OrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class SpringbootAwsLambdaDemoApplication implements RequestHandler<String, List<Order>> {


    @Bean
    public List<Order> handleRequest(String input, Context context) {

        log.info("input is {} ", input);
        List<Order> orders = OrderDao.buildOrders().stream()
                .filter(order -> order.getName().equals(input))
                .collect(Collectors.toList());

        log.info("orders List {} ", orders);
        return orders;
    }


//    @Bean
//    public Supplier<List<Order>> orders() {
//        return () -> orderDao.buildOrders();
//    }

//    @Bean
//    public Function<APIGatewayProxyRequestEvent, List<Order>> findOrderByName() {
//        return (proxyRequestEvent) -> OrderDao.buildOrders().stream()
//                .filter(order -> order.getName().equals(proxyRequestEvent.getQueryStringParameters().get("orderName")))
//                .collect(Collectors.toList());
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsLambdaDemoApplication.class, args);
    }
}
