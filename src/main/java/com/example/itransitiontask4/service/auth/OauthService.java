package com.example.itransitiontask4.service.auth;

import com.example.itransitiontask4.dto.request.auth.LoginRequestDto;
import com.example.itransitiontask4.dto.request.auth.RegistrationRequestDto;
import com.example.itransitiontask4.dto.response.jwt.JWTokenResponse;
import com.example.itransitiontask4.model.entity.UserEntity;
import com.example.itransitiontask4.repository.UserRepository;
import com.example.itransitiontask4.service.jwt.JWTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.itransitiontask4.model.enums.RoleEnum.USER;
import static com.example.itransitiontask4.model.enums.UserStateEnum.ACTIVE;
import static org.springframework.http.HttpStatus.OK;


@Service
@RequiredArgsConstructor
public class OauthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JWTokenProvider jwTokenProvider;


    public JWTokenResponse register(RegistrationRequestDto registrationDto){

        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()){
            throw new IllegalStateException("user already registered with: " + registrationDto.getEmail());
        }

        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        UserEntity user = modelMapper.map(registrationDto, UserEntity.class);
        user.setEnable(true);
        user.setStates(ACTIVE);
        user.setRole(USER);
        user.setRegistrationTime(LocalDateTime.now());
        String tokens[] = jwTokenProvider.generateJwtTokens(user);
        userRepository.save(user);
        return new JWTokenResponse(OK.value(), OK.name(), tokens[0], tokens[1]);
    }


    public JWTokenResponse login(LoginRequestDto loginDto){
        Optional<UserEntity> optionalUser = userRepository.findByEmail(loginDto.getEmail());

        if (!optionalUser.isPresent()){
            throw new UsernameNotFoundException("Email not found");
        }
        UserEntity user = optionalUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        String tokens[] = jwTokenProvider.generateJwtTokens(user);

        return new JWTokenResponse(OK.value(), OK.name(), tokens[0], tokens[1]);
    }


}