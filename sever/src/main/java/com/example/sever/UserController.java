package com.example.sever;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080/")
@RequestMapping("api/v1")
public class UserController {
    String user = "piranavan";
    String pass = "admin123";
    String key = "C/DKZUBfKt5fI2/QW8gdR0KKu2ivOFsC3PL4DHe+wAY=";


    @PostMapping("/login")
    public ResponseEntity<?> UserLogin(@RequestBody Map<String,String> userDetails) {
        String username = userDetails.get("username");
        String password = userDetails.get("password");
        long date = System.currentTimeMillis();

        if(username.equals(user) && password.equals(pass)){

            String token = Jwts.builder()
                    .setClaims(Jwts.claims()
                            .setIssuer(username)
                            .setIssuedAt(new Date(date)))
                    .setExpiration(new Date(date + 24*60*60*1000))
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody );

        }
        else

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

        }


    }

