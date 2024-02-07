package hr.mbehin.springbootjwtauthenticationandemailverification.model.verificationToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationToken saveVerificationToken(VerificationToken verificationToken){
        return verificationTokenRepository.save(verificationToken);
    }
    public Optional<VerificationToken> getVerificationToken(String token){
        return verificationTokenRepository.findByToken(token);
    }
}
