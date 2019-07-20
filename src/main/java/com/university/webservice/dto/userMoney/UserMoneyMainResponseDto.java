package com.university.webservice.dto.userMoney;

import com.university.webservice.domain.userMoney.UserMoney;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class UserMoneyMainResponseDto {
    private String userId;
    private String year;
    private String month;
    private Long money;
    private String regdate;
    private String moddate;

    public UserMoneyMainResponseDto(UserMoney entity) {
        userId = entity.getUserId();
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