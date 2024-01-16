package com.fed.profile.api;

import com.fed.profile.api.model.Profile;
import com.fed.profile.api.service.ProfileService;
import com.fed.profile.api.web.ProfileApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileApiControllerTest {

	@Mock
	private ProfileService profileService;

	@InjectMocks
	private ProfileApiController profileApiController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllProfiles() {
		when(profileService.get()).thenReturn(Collections.emptyList());

		Iterable<Profile> result = profileApiController.get();

		assertNotNull(result);
		assertEquals(0, ((Collection<?>) result).size());
	}

	@Test
	void testGetProfileById() {
		int profileId = 1;
		Profile mockProfile = new Profile();
		mockProfile.setId(profileId);
		when(profileService.get(profileId)).thenReturn(mockProfile);

		Profile result = profileApiController.get(profileId);

		assertNotNull(result);
		assertEquals(profileId, result.getId());
	}

	@Test
	void testGetProfileByIdNotFound() {
		int profileId = 1;
		when(profileService.get(profileId)).thenReturn(null);

		assertThrows(ResponseStatusException.class, () -> profileApiController.get(profileId));
	}

	@Test
	void testDeleteProfile() {
		int profileId = 1;

		assertDoesNotThrow(() -> profileApiController.delete(profileId));

		verify(profileService, times(1)).remove(profileId);
	}

	@Test
	void testCreateProfileWithFile() throws IOException {
		String name = "John Doe";
		byte[] fileContent = "file content".getBytes();
		MultipartFile mockFile = mock(MultipartFile.class);
		when(mockFile.getContentType()).thenReturn("image/jpeg");
		when(mockFile.getBytes()).thenReturn(fileContent);

		Profile mockProfile = new Profile();
		when(profileService.save(name, "image/jpeg", fileContent)).thenReturn(mockProfile);

		Profile result = profileApiController.create(name, mockFile);

		assertNotNull(result);
		assertEquals(mockProfile, result);
	}

	@Test
	void testCreateProfileWithoutFile() throws IOException {
		String name = "John Doe";

		Profile mockProfile = new Profile();
		when(profileService.save(name, null, null)).thenReturn(mockProfile);

		Profile result = profileApiController.create(name, null);

		assertNotNull(result);
		assertEquals(mockProfile, result);
	}
}
