package hr.mbehin.springbootjwtauthenticationandemailverification.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class JwtToken {
    private String access_token;
    private Date creation_date;
    private Date expiration_date;
    private Long user_id;
    private String user_username;
}
