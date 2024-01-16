package com.fed.profile.api.repository;

import com.fed.profile.api.model.Profile;
import org.springframework.data.repository.CrudRepository;

// Spring data wants you to create so-called repositories. Basic sql statements updated.
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    Profile findByName(String name);
}
