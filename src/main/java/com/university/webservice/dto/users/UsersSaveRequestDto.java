package com.university.webservice.dto.users;

import com.university.webservice.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveRequestDto {

    private String userId;
    private String userPw;
    private String name;
    private String phone;

    @Builder
    public UsersSaveRequestDto(String userId, String userPw, String name,String phone) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.phone = phone;
    }

    public Users toEntity(){
        return Users.builder()
                .userId(userId)
                .userPw(userPw)
                .name(name)
                .phone(phone)
                .build();
    }
}