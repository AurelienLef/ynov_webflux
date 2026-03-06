package com.example.exo11.service;

import com.example.exo11.models.User;
import com.example.exo11.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(User user, Long id) {
        return userRepository.findById(id)
                .flatMap(elem -> {
                    elem.setName(user.getName());
                    elem.setEmail(user.getEmail());
                    elem.setActive(user.isActive());
                    return userRepository.save(elem);
                });
    }

    public Mono<Void> delete(Long id) {
        return userRepository.deleteById(id);
    }
}
