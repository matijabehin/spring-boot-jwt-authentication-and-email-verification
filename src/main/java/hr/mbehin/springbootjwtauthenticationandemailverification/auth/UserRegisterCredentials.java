package hr.mbehin.springbootjwtauthenticationandemailverification.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterCredentials {
    private String email;
    private String username;
    private String password;
}
