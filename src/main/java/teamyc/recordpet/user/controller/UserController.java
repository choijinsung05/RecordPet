package teamyc.recordpet.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamyc.recordpet.user.dto.UserSignupRequest;
import teamyc.recordpet.user.dto.UserSignupResponse;
import teamyc.recordpet.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public UserSignupResponse signup(@RequestBody UserSignupRequest req){
        return userService.signup(req);
    }
}
