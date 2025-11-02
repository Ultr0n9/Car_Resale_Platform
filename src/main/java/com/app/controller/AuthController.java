package com.app.controller;

import com.app.entity.User;
import com.app.payload.JWTTokenDto;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;

    private UserService userService;

    private OTPService otpService;

    private JWTService jwtService;

    public AuthController(UserRepository userRepository, UserService userService, OTPService otpService, JWTService jwtService) {
        this.userRepository = userRepository;

        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
    ){

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("EmailId already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
//
//        It's the 1st approach

//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return new ResponseEntity<>("createdUser ",HttpStatus.CREATED);
    }

    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManagerUser(
            @RequestBody User user
    ){

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("EmailId already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }


        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");

        userRepository.save(user);
        return new ResponseEntity<>("createdUser ",HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManagerUser(
            @RequestBody User user
    ){

        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("EmailId already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE-BLOGMANAGER");

        userRepository.save(user);
        return new ResponseEntity<>("createdUser ",HttpStatus.CREATED);
    }

   @PostMapping("/userlogin")
    public ResponseEntity<?> userSignIn(

          @RequestBody LoginDto dto
   ){
       String jwtToken  = userService.verifyLogin(dto);
       if(jwtToken!=null) {
           JWTTokenDto tokenDto = new JWTTokenDto();
           tokenDto.setToken(jwtToken);
           tokenDto.setTokenType("JWT");
           return new ResponseEntity<>(tokenDto,HttpStatus.CREATED);
       }
       return new ResponseEntity<>("Wrong credentials" +
               "Habibi Tum kuch galat type karti Ache se type karke andar jati",HttpStatus.INTERNAL_SERVER_ERROR);
   }

    @PostMapping("/login-otp")
   public String generateOTP(@RequestParam String mobile){
        Optional<User> opUser = userRepository.findByMobile(mobile);
        if(opUser.isPresent()){
            String otp = otpService.generateOTP(mobile);
            return "For this Number" + " "+ mobile + " " + "The OTP Is"+" "+ otp;
        }
        return "User not found";
   }

   @PostMapping("/validate-otp")
   public String validateOtp(
          @RequestParam String mobile,
          @RequestParam String otp
   ){
       boolean status = otpService.validateOTP(mobile, otp);
       if (status){
           //genetate JWT token
           Optional<User> opUser = userRepository.findByMobile(mobile);
           if (opUser.isPresent()){
               String jwtToken = jwtService.generateToken(opUser.get().getUsername());
               return jwtToken;
           }
       }
       return status? "OTP Validated Successfully" : "Invalid Otp";
   }
}
