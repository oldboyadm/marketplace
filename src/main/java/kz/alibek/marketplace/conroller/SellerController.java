package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.SellerAuthResponseDto;
import kz.alibek.marketplace.dto.SellerRequestDto;
import kz.alibek.marketplace.model.Shop;
import kz.alibek.marketplace.model.User;
import kz.alibek.marketplace.repository.ShopRepository;
import kz.alibek.marketplace.repository.UserRepository;
import kz.alibek.marketplace.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @PostMapping("/auth")
    public SellerAuthResponseDto create (@RequestBody SellerRequestDto dto){
        User user = new User();
        Shop shop = new Shop();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(PasswordEncoder.hash(dto.password()));
        User user1 = userRepository.save(user);
        shop.setTitle(dto.shopTitle());
        shop.setAddress(dto.address());
        shop.setPhoneNumber(dto.phoneNumber());
        shop.setOwner(user1);
        shopRepository.save(shop);

        return new SellerAuthResponseDto(
                user1.getId(),
                user1.getName(),
                user1.getEmail(),
                "Pending",

        );
    }
}
