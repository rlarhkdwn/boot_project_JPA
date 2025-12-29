package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.AuthRole;
import com.example.demo.entity.AuthUser;
import com.example.demo.entity.User;

public interface UserService {
    default User convertDTOToEntity(UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .pwd(userDTO.getPwd())
                .nickName(userDTO.getNickName())
                .lastLogin(userDTO.getLastLogin())
                .build();
    }

    default UserDTO convertEntityToDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .nickName(user.getNickName())
                .lastLogin(user.getLastLogin())
                .regDate(user.getRegDate())
                .modDate(user.getModDate())
                .authList(user.getAuthList() == null ? null : user.getAuthList().stream().map(this::convertEntityToDTO).toList())
                .build();
    }

    // auth변환
    default AuthUserDTO convertEntityToDTO(AuthUser authUser){
        return AuthUserDTO.builder()
                .id(authUser.getId())
                .role(authUser.getAuth().getRoleName())
                .email(authUser.getUser().getEmail())
                .build();
    }

    default AuthUser convertDTOToEntity(AuthUserDTO authUserDTO){
        return AuthUser.builder()
                .id(authUserDTO.getId())
                .auth(AuthRole.USER)
                .build();
    }

    String register(UserDTO userDTO);
}
