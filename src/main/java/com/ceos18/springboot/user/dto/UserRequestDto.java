package com.ceos18.springboot.user.dto;

import com.ceos18.springboot.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String phone;

    private String imgUrl;

    private BigDecimal temperature;

    public User toMember(PasswordEncoder passwordEncoder) {
        return User.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .imgUrl(imgUrl)
                .temperature(BigDecimal.valueOf(36.5))
                .build();
    }
}
