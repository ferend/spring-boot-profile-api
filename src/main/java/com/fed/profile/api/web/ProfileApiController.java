package com.fed.profile.api.web;

import com.fed.profile.api.model.Profile;
import com.fed.profile.api.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

//Spring Boot works with annotations, so you have to declare what that class does.
@RestController
public class ProfileApiController {
    
    private final ProfileService profileService;

    // Constructor injection. Spring scans classes and finds that service. Creates instance and injects.
    // Can also use @AutoWired instead of constructing.
    public ProfileApiController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //You need to decide user/browser going to send HTTP Request you map it.
    @GetMapping("/")
    public String hello() {
        return "Hello word";
    }
    
    @GetMapping("/profiles")
    public Collection<Profile> get() {
        return profileService.get();
    }

    // This annotation says if you have mapping some param. with {id} just put whatever is in the "id" 
    @GetMapping("/profiles/{id}")
    public Profile get(@PathVariable String id) {
        Profile profile = profileService.get(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return profile;
    }

    @DeleteMapping("/profiles/{id}")
    public void delete(@PathVariable String id) {
        Profile profile = profileService.remove(id);
        
        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
    //Wrapper method inside SpringBoot. Converts the file.
    @PostMapping("/profiles")
    public Profile create (@RequestPart("data") MultipartFile file) throws IOException {

       Profile profile = profileService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return profile;
    }
}
