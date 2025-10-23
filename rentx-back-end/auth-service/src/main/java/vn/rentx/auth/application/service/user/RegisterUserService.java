package vn.rentx.auth.application.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.dto.command.RegisterRequestDto;
import vn.rentx.auth.application.port.in.user.command.RegisterUserUseCase;
import vn.rentx.auth.application.port.out.EventPublisherPort;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.domain.user.aggregate.AppUser;
import vn.rentx.auth.domain.user.vo.Email;
import vn.rentx.auth.domain.user.vo.Password;
import vn.rentx.auth.domain.user.vo.UserName;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisherPort eventPublisher;

    @Transactional
    @Override
    public AppUser register(RegisterRequestDto registerUserDto) {

        // Build Value-Object
        UserName userNameVO = new UserName(registerUserDto.username());
        Email emailVO = new Email(registerUserDto.email());

        // Encode password
        String hashedPassword = passwordEncoder.encode(registerUserDto.password());
        Password passwordVO = new Password(hashedPassword);

        // Create Aggregate
        AppUser user = AppUser.register(
                registerUserDto.fullName(),
                userNameVO,
                emailVO,
                passwordVO,
                Instant.now()
        );

        // Save in database
        return userRepository.save(user, registerUserDto.roles());

        // Publish event
//        eventPublisher.publish(new UserRegisteredEvent(user.getId(), user.getUsername().getValue(), user.getEmail().getValue(), LocalDateTime.now()), "Create user");

    }
}

