package io.taskmanager.controler;


import io.taskmanager.client.request.AuthRequest;
import io.taskmanager.exception.ExceptionHandling;
import io.taskmanager.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationRestController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            LOGGER.info(String.format("%s successfully log in", authRequest.getUsername()));
            return tokenResponse(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new BadCredentialsException("INCORRECT_USER_CREDENTIALS");
        }
    }

    private Map<String, String> tokenResponse(String token){
        Map<String, String> returnObject = new HashMap<>();
        returnObject.put("token", token);

        return returnObject;
    }
}
