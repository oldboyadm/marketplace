package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.auth.AuthResponseDto;
import kz.alibek.marketplace.dto.auth.UserRequestDto;
import kz.alibek.marketplace.model.User;
import kz.alibek.marketplace.model.UserRole;
import kz.alibek.marketplace.repository.UserRepository;
import kz.alibek.marketplace.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthConroller {
    private final UserRepository userRepository;


    @PostMapping("/register")
    public AuthResponseDto create (@RequestBody UserRequestDto dto){
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(PasswordEncoder.hash(dto.password()));
        user.setApiKey(UUID.randomUUID().toString());
        user.setRole(UserRole.USER);

        userRepository.save(user);

        return AuthResponseDto.of(user);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody UserRequestDto dto){
        User user = userRepository.findByEmail(dto.email()).orElseThrow();
        if(!BCrypt.checkpw(dto.password(),user.getPassword())){
            throw new RuntimeException();
        }

        return AuthResponseDto.of(user);
    }


}
