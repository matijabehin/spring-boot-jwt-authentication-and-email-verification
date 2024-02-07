package hr.mbehin.springbootjwtauthenticationandemailverification.auth;

import hr.mbehin.springbootjwtauthenticationandemailverification.email.EmailService;
import hr.mbehin.springbootjwtauthenticationandemailverification.email.MailContentBuilder;
import hr.mbehin.springbootjwtauthenticationandemailverification.model.user.User;
import hr.mbehin.springbootjwtauthenticationandemailverification.model.user.UserRepository;
import hr.mbehin.springbootjwtauthenticationandemailverification.model.verificationToken.VerificationToken;
import hr.mbehin.springbootjwtauthenticationandemailverification.model.verificationToken.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final VerificationTokenService verificationTokenService;

    private final EmailService emailService;

    private final MailContentBuilder mailContentBuilder;

    public JwtToken login(UserAuthenticationCredentials request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return JwtToken.builder()
                .access_token(token)
                .creation_date(jwtService.extractCreation(token))
                .expiration_date(jwtService.extractExpiration(token))
                .user_id(user.getId())
                .user_username(user.getUsername())
                .build();
    }

    public void register(UserRegisterCredentials request){
        boolean userExists = userRepository.findByUsername(request.getUsername()).isPresent()
                || userRepository.findByEmail(request.getEmail()).isPresent();

        if(userExists)  throw new IllegalStateException("User with that username or email already exists");

        User user = new User(
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken =
                new VerificationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), user);

        verificationTokenService.saveVerificationToken(verificationToken);

        String email = mailContentBuilder.build(request.getUsername(), "http://localhost:8080/api/auth/email-verification?token=" + token);
        emailService.send(request.getEmail(),email);
    }

    // TODO: izbriši ovu metodu ako neće nigdje biti korištena
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public String verifyEmail(String token) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token)
                .orElseThrow(() -> new IllegalStateException("Verification token doesn't exist."));

        if(verificationToken.getConfirmationDate() != null) throw new IllegalStateException("Email is already verified.");

        if(verificationToken.getExpirationDate().isBefore(LocalDateTime.now())) throw new IllegalStateException("Verification token has expired.");

        verificationToken.setConfirmationDate(LocalDateTime.now());

        User user = userRepository.findById(verificationToken.getUser().getId())
                .orElseThrow(() -> new IllegalStateException("User has not been found"));

        user.setEnabled(true);
        user.setUnlocked(true);
        userRepository.save(user);

        return "User's email has been verified successfully";
    }
}
