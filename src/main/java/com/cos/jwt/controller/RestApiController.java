package com.cos.jwt.controller;

import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("home")
    public String goHome(){
        return"<h1>home</h1>";
    }

    @PostMapping("home2")
    public String goHome2(){
        return"<h1>home</h1>";
    }

    @PostMapping("token")
    public String token(){
        return"<h1>token</h1>";
    }

    @PostMapping("join")
    public String join (@RequestBody User user){
        user.setRoles("ROLE_USER");
        user.setUsername(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        User user1 = userRepository.findByusername(user.getUsername());
        return "<p>"+ user1.getUsername() +"</p>";
    }

}
