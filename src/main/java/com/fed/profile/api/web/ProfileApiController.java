package com.fed.profile.api.web;

import com.fed.profile.api.model.Profile;
import com.fed.profile.api.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class ProfileApiController {

    private final ProfileService profileService;

    public ProfileApiController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles")
    public Iterable<Profile> get() {
        return profileService.get();
    }

    @GetMapping("/profiles/{id}")
    public Profile get(@PathVariable Integer id) {
        Profile profile = profileService.get(id);

        if(profile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return profile;
    }

    @DeleteMapping("/profiles/{id}")
    public void delete(@PathVariable Integer id) {
        profileService.remove(id);
    }

    // Updated create method to accept the name parameter
    @PostMapping("/profiles")
    public Profile create(@RequestParam String name,
                          @RequestPart("data") MultipartFile file) throws IOException {
        Profile profile = profileService.save(name, file.getContentType(), file.getBytes());
        return profile;
    }
}
