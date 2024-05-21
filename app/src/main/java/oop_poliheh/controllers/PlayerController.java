package oop_poliheh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @GetMapping(path = {"", "/"})
    public ResponseEntity<String> getMethod() {
        return ResponseEntity.ok("Hello world");
    }

}
