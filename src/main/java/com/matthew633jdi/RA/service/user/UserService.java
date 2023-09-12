package com.matthew633jdi.RA.service.user;

import com.matthew633jdi.RA.domain.user.User;
import com.matthew633jdi.RA.domain.user.UserRepository;
import com.matthew633jdi.RA.web.dto.user.UserRequestDto;
import com.matthew633jdi.RA.web.dto.user.UserResponseDto;
import com.matthew633jdi.RA.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public Long register(UserSaveRequestDto userSaveRequestDto) {
        // 검증
        validateDuplicateUser(userSaveRequestDto);

        return userRepository.save(userSaveRequestDto.toEntity()).getId();
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 회원입니다."));
        return new UserResponseDto(user);
    }

    private void validateDuplicateUser(UserSaveRequestDto userSaveRequestDto) {
        userRepository.findByEmail(userSaveRequestDto.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    public UserResponseDto findUser(UserRequestDto userRequestDto) {
        User user = userRepository.findByEmailAndAndPassword(userRequestDto.getEmail(), userRequestDto.getPwd()).orElseThrow(() -> new IllegalArgumentException("아이디와 비밀번호를 확인하세요,"));
        return new UserResponseDto(user);

    }
}
