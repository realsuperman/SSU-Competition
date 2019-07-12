package com.university.webservice.dto.common;

import com.university.webservice.domain.common.Common;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class CommonMainResponseDto {
    private String userId;
    private String typeCode;
    private String typeName;
    private String regdate;
    private String moddate;

    public CommonMainResponseDto(Common entity) {
        userId = entity.getUserId();
        typeCode = entity.getTypeCode();
        typeName = entity.getTypeName();
        regdate = entity.getRegdate();
        moddate = entity.getModdate();
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