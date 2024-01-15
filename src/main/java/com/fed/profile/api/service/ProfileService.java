package com.fed.profile.api.service;

// DI in action. To talk db correctly. Controllers now only know about service not db itself.

import com.fed.profile.api.model.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Service
public class ProfileService {
    
    private Map<String, Profile> db = new HashMap<>() {{
        put("1", new Profile());

    }};

    public Collection<Profile> get() {
        return db.values();
    }

    public Profile get(String id) {
        return db.get(id);
    }

    public Profile remove(String id) {
        return db.remove(id);
    }

    public Profile save(String name, String contentType, byte[] data) {
        Profile profile = new Profile();
        profile.setContentType(contentType);
        profile.setId(UUID.randomUUID().toString());
        profile.setName(name);
        profile.setData(data);
        db.put(profile.getId(), profile);
        return profile;
    }
}
