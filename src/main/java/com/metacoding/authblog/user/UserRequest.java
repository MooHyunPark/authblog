package com.metacoding.authblog.user;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        // User를 초기화 할 때, 비밀번호는 기존 값을 hash코드로 변경한 값으로 변경하여 초기화 한 뒤 반환한다.
        public User toEntity(PasswordEncoder encoder) {
            String encPassword = encoder.encode(password);
            System.out.println("확인");
            System.out.println(encPassword);
            User user = new User(null, username, encPassword, email, null);
            return user;
        }
    }
}
