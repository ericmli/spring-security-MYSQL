package io.isiflix.authdemo.controller;

import io.isiflix.authdemo.dto.LoginDTO;
import io.isiflix.authdemo.model.User;
import io.isiflix.authdemo.security.IsiToken;
import io.isiflix.authdemo.security.TokenUtil;
import io.isiflix.authdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IUserService service;

    @PostMapping("/login")
    public ResponseEntity<IsiToken> realizarLogin(@RequestBody LoginDTO dadosLogin){
            IsiToken tk = service.login(dadosLogin);
            if (tk != null) {
                return ResponseEntity.ok(tk);
            }

        return ResponseEntity.status(403).build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        return ResponseEntity.status(201).body(service.create(user));
    }
}
