package hr.mbehin.springbootjwtauthenticationandemailverification.auth;

import hr.mbehin.springbootjwtauthenticationandemailverification.model.verificationToken.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterCredentials request){
        authService.register(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserAuthenticationCredentials request){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));
    }

    @GetMapping("/email-verification")
    public ResponseEntity<String> verifyEmail(@RequestParam String token){
        return ResponseEntity.status(HttpStatus.OK).body(authService.verifyEmail(token));
    }
}
