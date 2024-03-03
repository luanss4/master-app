package br.meu.cliente.masterapp.controller;

import br.meu.cliente.masterapp.model.User;
import br.meu.cliente.masterapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(Pageable pageable) {
        if(pageable == null) pageable = Pageable.unpaged();
        //log info com o némero da pagina
        System.out.println("Número da página: " + pageable.getPageNumber());
        return ResponseEntity.ok(userRepository.findAll(pageable).getContent());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(user == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userRepository.save(user));
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
