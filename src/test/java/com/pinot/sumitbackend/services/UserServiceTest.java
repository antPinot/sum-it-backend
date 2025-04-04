package com.pinot.sumitbackend.services;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.pinot.sumitbackend.document.User;
import com.pinot.sumitbackend.dto.UserFavoritesDto;
import com.pinot.sumitbackend.repositories.PeakRepository;
import com.pinot.sumitbackend.repositories.UserRepository;
import com.pinot.sumitbackend.services.UserService;

@SpringBootTest(
	    properties = {
	        "spring.data.mongodb.repositories.enabled=false",
	        "jwt.secret=someSecretValue"  // Valeur de test pour JWT_SECRET
	    }
	)
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
class UserServiceTest {

	@MockitoBean
	private UserRepository mockUserRepository;
	
	@MockitoBean
	private PeakRepository mockPeakRepository;
	
	@InjectMocks
	private UserService mockUserService;
	
	@MockitoBean
	private PasswordEncoder passwordEncoder;
	
	@Captor
	private ArgumentCaptor<User> userCaptor;

	private UserFavoritesDto mockUserFavoriteDto;
	
	private User mockUser;
	
	private User mockUser2;

	@BeforeEach
	final void setUp() {

		MockitoAnnotations.openMocks(this);
		
		mockUser = new User("mock", "mock.mock@mock.fr", "mock", LocalDate.now());
		ObjectId userId = new ObjectId("65fdf5d1543b7f25e0e3c1a2");
		mockUser.setId(userId);
		
		
		mockUser2 = new User("mock2", "mock2.mock@mock.fr", "mock2", LocalDate.now());
		ObjectId userId2 = new ObjectId("65f4b02f522b7a05b0b3f4d3");
		mockUser2.setId(userId2);
		
		
		List<String> mockFavoritesId = new ArrayList<>(Arrays.asList("mockFavorite", "mockFavorite2", "mockFavorite3"));
		mockUser.setFavorites(mockFavoritesId);

		mockUserFavoriteDto = UserFavoritesDto.builder()
				.username("mock")
				.favorite("mockFavorite")
				.build();
		
		when(mockUserRepository.findByUsername("mock")).thenReturn(Optional.of(mockUser));
		when(mockUserRepository.findById(userId)).thenReturn(Optional.of(mockUser));
		
		
	}
	
	@Test
	final void should_get_user_with_corresponding_id() {
		User result = mockUserService.findById("65fdf5d1543b7f25e0e3c1a2");
		
		assertEquals(mockUser, result);
		assertNotEquals(mockUser2, result);	
	}
	
	@Test
	final void should_throw_illegal_argument_exception_if_id_is_not_hexString(){
		assertThatIllegalArgumentException().isThrownBy(() -> mockUserService.findById("Some false id"));
	}
	
	@Test
	final void should_throw_not_such_an_element_exception_if_no_id_is_corresponding_to_user(){
		assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> mockUserService.findById("660d5a72841a7c3cbe12f8a4"));
	}
	
	@Test
	final void should_get_the_created_user() {
		
		when(mockUserRepository.findByUsername(mockUser2.getUsername())).thenReturn(Optional.empty());
		when(mockUserRepository.findByMail(mockUser2.getMail())).thenReturn(Optional.empty());
		when(passwordEncoder.encode(mockUser2.getPassword())).thenReturn("encodedMock2");
		
		mockUserService.createUser(mockUser2.getUsername(), mockUser2.getMail(), mockUser2.getPassword());
		verify(mockUserRepository).save(userCaptor.capture());
		User savedUser = userCaptor.getValue();
		
		assertEquals(mockUser2.getUsername(), savedUser.getUsername());
		assertEquals(mockUser2.getMail(), savedUser.getMail());
		assertEquals("encodedMock2", savedUser.getPassword());
		
		
	}
	
	@Test
	final void should_have_added_favorite_to_user_favorites() {
		mockUserService.updateFavorites(mockUserFavoriteDto.getUsername(), mockUserFavoriteDto.getFavorite());
		assertTrue(mockUser.getFavorites().contains(mockUserFavoriteDto.getFavorite()));
	}

	@Test
	final void should_not_get_deleted_Favorite() {
		mockUserService.deleteFavorites(mockUserFavoriteDto.getUsername(), mockUserFavoriteDto.getFavorite());
		assertFalse(mockUser.getFavorites().contains(mockUserFavoriteDto.getFavorite()));
	}

}
