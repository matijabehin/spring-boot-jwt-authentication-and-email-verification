package hr.mbehin.springbootjwtauthenticationandemailverification.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticationCredentials {
    private String username;
    private String password;
}
