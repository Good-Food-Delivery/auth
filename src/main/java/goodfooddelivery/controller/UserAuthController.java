package goodfooddelivery.controller;

import goodfooddelivery.config.MQConfig;
import goodfooddelivery.dto.request.UserAuthCreateRequestDTO;
import goodfooddelivery.dto.request.UserAuthLogInRequestDTO;
import goodfooddelivery.dto.request.UserMessage;
import goodfooddelivery.interfaces.IUserAuthService;
import goodfooddelivery.mapper.UserAuthMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private IUserAuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/register")
    public ResponseEntity<String>  createUser(@RequestBody UserAuthCreateRequestDTO user)
    {
        try {
            Long userAuthId = service.saveUserAuth(UserAuthMapper.INSTANCE.toUser(user));
            UserMessage userMessage = new UserMessage(userAuthId, "CREATE");

            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.ROUTING_KEY, userMessage);

            return new ResponseEntity<>("User registered with ID: " + userAuthId, HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserAuthLogInRequestDTO user)
    {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authenticate.isAuthenticated()) {
            return new ResponseEntity<>(service.generateToken(user.getEmail()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token)
    {
        // Validate the token
        try {
            service.validateToken(token);
            return new ResponseEntity<>("Token is valid", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Token is invalid or expired", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id) {
        try {
            UserMessage userMessage = new UserMessage(id, "DELETE");

            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.ROUTING_KEY, userMessage);

            service.deleteUserAuth(id);

            return new ResponseEntity<>("User delete with ID: " + id, HttpStatus.OK);
        }
        catch(IllegalArgumentException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
