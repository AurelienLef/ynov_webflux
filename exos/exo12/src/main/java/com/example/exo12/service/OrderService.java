package com.example.exo12.service;

import com.example.exo12.models.Order;
import com.example.exo12.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Flux<Order> findAllByStatus(String etat) {
        return orderRepository.findAllByStatus(etat);
    }

    public Flux<Order> findAllPaged(int page, int size) {
        return orderRepository.findAll()
                .skip((long) page * size)
                .take(size);
    }

    public Mono<Order> create(Order order) {
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public Mono<Order> update(Order order, Long id) {
        return orderRepository.findById(id)
                .flatMap(elem -> {
                    elem.setCustomerName(order.getCustomerName());
                    elem.setTotalAmount(order.getTotalAmount());
                    elem.setStatus(order.getStatus());
                    return orderRepository.save(elem);
                });
    }

    public Mono<Void> delete(Long id) {
        return orderRepository.deleteById(id);
    }
}
