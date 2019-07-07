package com.university.webservice.dto.users;

import com.university.webservice.domain.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class UsersMainResponseDto {
    private Long id;
    private String userId;
    private String uId;
    private String uPw;
    private String userPw;
    private String name;
    private String phone;
    private String regdate;
    private String moddate;

    public UsersMainResponseDto(Users entity) {
        id = entity.getId();
        //modifiedDate = toStringDateTime(entity.getModdate());
    }

    /**
     * Java 8 버전
     */
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    /**
     * Java 7 버전
     */
    private String toStringDateTimeByJava7(LocalDateTime localDateTime){
        if(localDateTime == null){
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }
}