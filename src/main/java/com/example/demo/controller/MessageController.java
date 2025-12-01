package com.example.demo.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    // curl -X GET http://localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() { return ResponseEntity.ok(messages); }

    // curl -X POST -H "Content-Type: application/json" -d "\"Hello\"" http://localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) { messages.add(text); return ResponseEntity.accepted().build(); }

    // curl -X GET http://localhost:8080/messages/0
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable int index) { return ResponseEntity.ok(messages.get(index)); }

    // curl -X DELETE http://localhost:8080/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int index) { messages.remove(index); return ResponseEntity.noContent().build(); }

    // curl -X PUT -H "Content-Type: application/json" -d "\"New text\"" http://localhost:8080/messages/0
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(@PathVariable int index, @RequestBody String message) { messages.set(index, message); return ResponseEntity.accepted().build(); }

    // curl -X GET http://localhost:8080/messages/search/lo
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchFirstMatch(@PathVariable String text) {
        for (int i = 0; i < messages.size(); i++) if (messages.get(i).contains(text)) return ResponseEntity.ok(i);
        return ResponseEntity.ok(-1);
    }

    // curl -X GET http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> getCount() { return ResponseEntity.ok(messages.size()); }

    // curl -X POST -H "Content-Type: application/json" -d "\"Hello\"" http://localhost:8080/messages/1/create
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> createAtIndex(@PathVariable int index, @RequestBody String text) { messages.add(index, text); return ResponseEntity.accepted().build(); }

    // curl -X DELETE http://localhost:8080/messages/search/lo
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteBySubstring(@PathVariable String text) { messages.removeIf(m -> m.contains(text)); return ResponseEntity.noContent().build(); }

    // curl -X GET "http://localhost:8080/messages?start=He"
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessagesFilter(@RequestParam(required = false) String start) {
        if (start == null) return ResponseEntity.ok(messages);
        return ResponseEntity.ok(messages.stream().filter(m -> m.startsWith(start)).toList());
    }
}