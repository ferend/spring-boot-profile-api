package com.fed.profile.api.web;

import com.fed.profile.api.model.Profile;
import com.fed.profile.api.service.ProfileService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    private final ProfileService profileService;

    
    public DownloadController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping("/download/{name}")
    public ResponseEntity<byte[]> download(@PathVariable String name) {
        Profile profile = profileService.getProfileByName(name);
        
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }

        byte[] data = profile.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(profile.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment").filename(profile.getName()).build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
