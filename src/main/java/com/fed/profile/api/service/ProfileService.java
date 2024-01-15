package com.fed.profile.api.service;

import com.fed.profile.api.model.Profile;
import com.fed.profile.api.repository.ProfileRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Iterable<Profile> get() {
        return profileRepository.findAll();
    }

    public Profile get(Integer id) {
        return profileRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        profileRepository.deleteById(id);
    }

    public Profile save(String name, String contentType, byte[] data) {
        Profile profile = new Profile();
        profile.setContentType(contentType);
        profile.setName(name);
        profile.setData(data);
        profileRepository.save(profile);
        return profile;
    }
}
