package com.example.demo.controller;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final List<User> users = new ArrayList<>();

    //curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"Alex\",\"age\":20}" http://localhost:8080/users
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) { users.add(user); return ResponseEntity.accepted().build(); }

    //curl -X GET http://localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() { return ResponseEntity.ok(users); }

    //curl -X GET http://localhost:8080/users/0
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable int index) { return ResponseEntity.ok(users.get(index)); }

    //curl -X DELETE http://localhost:8080/users/0
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable int index) { users.remove(index); return ResponseEntity.noContent().build(); }

    //curl -X PUT -H "Content-Type: application/json" -d "30" http://localhost:8080/users/0/age
    @PutMapping("users/{index}/age")
    public ResponseEntity<Void> updateAge(@PathVariable int index, @RequestBody int age) { users.get(index).setAge(age); return ResponseEntity.accepted().build(); }
}