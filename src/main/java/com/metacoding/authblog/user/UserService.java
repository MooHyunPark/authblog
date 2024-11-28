package com.metacoding.authblog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // DB에 저장할 때 비밀번호를 hash로 변경하여 넣을 수 있도록 인코더를 매개 변수로 전달.
    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity(passwordEncoder));

    }

    // POST 요청
    // /login 일때 호출 됨
    // key 값 -> username, password
    // Content-Type -> x-www-form-urlencoded
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return user;
    }
}
