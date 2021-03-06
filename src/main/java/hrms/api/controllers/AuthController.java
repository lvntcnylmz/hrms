package hrms.api.controllers;

import hrms.business.abstracts.UserService;
import hrms.entities.dtos.request.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto user) {
        return ResponseEntity.ok(this.userService.login(user));
    }
}
