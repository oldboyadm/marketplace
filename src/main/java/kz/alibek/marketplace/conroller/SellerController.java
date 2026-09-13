package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.auth.LoginRequestDto;
import kz.alibek.marketplace.dto.auth.SellerAuthResponseDto;
import kz.alibek.marketplace.dto.auth.SellerRequestDto;
import kz.alibek.marketplace.model.Shop;
import kz.alibek.marketplace.model.User;
import kz.alibek.marketplace.model.UserRole;
import kz.alibek.marketplace.repository.ShopRepository;
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
@RequestMapping("/sellers/auth")
@RequiredArgsConstructor
public class SellerController {
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @PostMapping("/register")
    public SellerAuthResponseDto create (@RequestBody SellerRequestDto dto){
        User user = new User();
        Shop shop = new Shop();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setApiKey(UUID.randomUUID().toString());
        user.setPassword(PasswordEncoder.hash(dto.password()));
        user.setRole(UserRole.SELLER);
        userRepository.save(user);
        shop.setTitle(dto.shopTitle());
        shop.setAddress(dto.address());
        shop.setPhoneNumber(dto.phoneNumber());
        shop.setOwner(user);
        shopRepository.save(shop);

        return new SellerAuthResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getApiKey()
        );
    }
    @PostMapping("/login")
    public SellerAuthResponseDto login(@RequestBody LoginRequestDto dto){
        User user = userRepository.findByEmail(dto.email()).orElseThrow();
        if(!BCrypt.checkpw(dto.password(),user.getPassword())){
            throw new RuntimeException();
        }

        return SellerAuthResponseDto.of(user);
    }
}
