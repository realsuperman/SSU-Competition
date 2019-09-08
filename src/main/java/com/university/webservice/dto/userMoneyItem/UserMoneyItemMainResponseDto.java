package com.university.webservice.dto.userMoneyItem;

import com.university.webservice.domain.userMoneyItem.UserMoneyItem;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class UserMoneyItemMainResponseDto {
    private String userId;
    private Long typeCode;
    private String year;
    private String month;
    private Long price;
    private Long ratio;
    private String regdate;
    private String moddate;
    private String typeName;

    public UserMoneyItemMainResponseDto(UserMoneyItem entity) {
        userId = entity.getUserId();
        typeCode = entity.getTypeCode();
        year = entity.getYear();
        month = entity.getMonth();
        price = entity.getPrice();
        typeName = entity.getTypeName();
        regdate = entity.getRegdate();
        moddate = entity.getModdate();
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