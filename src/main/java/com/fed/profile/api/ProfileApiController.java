package com.fed.profile.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Spring Boot works with annotations, so you have to declare what that class does.
@RestController
public class ProfileApiController {
    
    private Map <String, Profile> db = new HashMap<> () {{
       put("1", new Profile("1", "profile1")); 
    }};
    
    //You need to decide user/browser going to send HTTP Request you map it.
    @GetMapping("/")
    public String Hello() {
        return "Hello word";
    }
    
    @GetMapping("/profiles")
    public Collection<Profile> Get() {
        return db.values();
    }

    // This annotation says if you have mapping some param. with {id} just put whatever is in the "id" 
    @GetMapping("/profiles/{id}")
    public Profile Get(@PathVariable String id) {
        Profile profile = db.get(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return profile;
    }

    @GetMapping("/profiles/{id}")
    public void Delete(@PathVariable String id) {
        Profile profile = db.remove(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
