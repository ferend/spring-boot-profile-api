package com.fed.profile.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

//Spring Boot works with annotations, so you have to declare what that class does.
@RestController
public class ProfileApiController {
    
    private Map <String, Profile> db = new HashMap<> () {{
       put("1", new Profile("1", "profile1"));
        put("2", new Profile("2", "profile2"));

    }};
    
    //You need to decide user/browser going to send HTTP Request you map it.
    @GetMapping("/")
    public String hello() {
        return "Hello word";
    }
    
    @GetMapping("/profiles")
    public Collection<Profile> get() {
        return db.values();
    }

    // This annotation says if you have mapping some param. with {id} just put whatever is in the "id" 
    @GetMapping("/profiles/{id}")
    public Profile get(@PathVariable String id) {
        Profile profile = db.get(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return profile;
    }

    @DeleteMapping("/profiles/{id}")
    public void delete(@PathVariable String id) {
        Profile profile = db.remove(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/profiles")
    public Profile create (@RequestBody @Valid Profile profile) {
        profile.setId(UUID.randomUUID().toString());
        db.put(profile.getId(), profile);
        return profile;
    }
}
