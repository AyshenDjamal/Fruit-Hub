package com.example.fruithub.service;

import com.example.fruithub.dto.LoginDto;
import com.example.fruithub.dto.ResponseDto;
import com.example.fruithub.dto.SignUpDto;
import com.example.fruithub.entity.Status;
import com.example.fruithub.entity.User;
import com.example.fruithub.repository.StatusRepository;
import com.example.fruithub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public Object register(SignUpDto signUpDto){
        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent()){
            return "Email already exists";
        }

        if(!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())){
            return "Passwords do not match";
        }

        Status activeStatus = statusRepository.findByName("Active");
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .status(activeStatus)
                .build();

        userRepository.save(user);
       // return "Successfully registered";
         var token = jwtService.generateToken(user);

         return ResponseDto.builder().token(token).build();
    }

    public Object loginWithToken(LoginDto loginDto){
        Optional<User> checkUser = userRepository.findByEmail(loginDto.getEmail());
        if(!checkUser.isPresent()){
            return "Write your email correctly";
        }

        if(!passwordEncoder.matches(loginDto.getPassword(), checkUser.get().getPassword())){
            return "Write your password correctly";
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return ResponseDto.builder().token(token).build();

    }
}
