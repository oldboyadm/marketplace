package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.auth.UserRequestDto;
import kz.alibek.marketplace.dto.auth.UserResponseDto;
import kz.alibek.marketplace.model.User;
import kz.alibek.marketplace.model.UserRole;
import kz.alibek.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserConroller {
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow();
        return UserResponseDto.of(user);
    }

    @PostMapping
    public UserResponseDto create (@RequestBody UserRequestDto dto){
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(BCrypt.hashpw(dto.password(),BCrypt.gensalt(12)));
        user.setApiKey(UUID.randomUUID().toString());
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return UserResponseDto.of(user);
    }

    @GetMapping("/me")
    public UserResponseDto findByApiKey(@RequestHeader("Authorization") String apiKey){
        User user = userRepository.findByApiKey(apiKey).orElseThrow();
        return UserResponseDto.of(user);
    }

}
