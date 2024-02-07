package hr.mbehin.springbootjwtauthenticationandemailverification.model.verificationToken;

import hr.mbehin.springbootjwtauthenticationandemailverification.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private LocalDateTime expirationDate;
    private LocalDateTime confirmationDate;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken(String token, LocalDateTime creationDate, LocalDateTime expirationDate, User user) {
        this.token = token;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.user = user;
    }
}
