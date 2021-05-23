package com.ngbilling.core.controller;

import com.ngbilling.core.common.exception.TokenRefreshException;
import com.ngbilling.core.common.response.JwtResponse;
import com.ngbilling.core.common.response.MessageResponse;
import com.ngbilling.core.common.response.TokenRefreshResponse;
import com.ngbilling.core.payload.request.security.LogOutRequest;
import com.ngbilling.core.payload.request.security.LoginRequest;
import com.ngbilling.core.payload.request.security.TokenRefreshRequest;
import com.ngbilling.core.payload.request.user.CompanyUserDetails;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.security.JwtUtils;
import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dao.user.RoleDAO;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.user.RefreshToken;
import com.ngbilling.core.server.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CompanyUserDetails userDetails = (CompanyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        RefreshToken refreshToken = userService.createRefreshToken(userDetails.getUserId());
        return ResponseEntity.ok(new JwtResponse(jwt,refreshToken.getToken(),
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getUser().getCompany().getDescription(),
                roles));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken( @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return userService.findByToken(requestRefreshToken)
                .map(userService::verifyExpiration)
                .map(RefreshToken::getBaseUser)
                .map(baseUser -> {
                    String token = jwtUtils.generateTokenFromUsername(baseUser.getUserName());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody LogOutRequest logOutRequest) {
        userService.deleteByUserName(logOutRequest.getUserName());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
