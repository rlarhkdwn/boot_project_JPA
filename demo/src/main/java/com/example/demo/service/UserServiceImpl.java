package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.AuthRole;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public String register(UserDTO userDTO) {
        userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
        User user = convertDTOToEntity(userDTO);
        user.addAuth(AuthRole.USER);
        return userRepository.save(user).getEmail();
    }

    @Transactional
    @Override
    public void lastLoginUpdate(String name) {
        User user = userRepository.findById(name).orElseThrow(() -> new EntityNotFoundException("해당 사용자가 없습니다."));
        // 상태 변경(dirty checking 발생) => transactional
        user.setLastLogin(LocalDateTime.now());
    }

    @Override
    public List<UserDTO> getList() {
        List<User> userList = userRepository.findAllWithAuthList();
        return userList.stream().map(this::convertEntityToDTO).toList();
    }

    @Transactional
    @Override
    public void delete(String email) {
        User user = userRepository.findById(email).orElseThrow(() -> new EntityNotFoundException());
        userRepository.delete(user);
    }

    @Override
    public void modify(UserDTO userDTO) {
        userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
        User user = convertDTOToEntity(userDTO);
        user.addAuth(AuthRole.USER);
        userRepository.save(user);
    }
}
