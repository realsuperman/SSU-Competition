package com.university.webservice.dto.common;

import com.university.webservice.domain.common.Common;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonSaveRequestDto {

    private String userId;
    private String typeCode;
    private String typeName;

    @Builder
    public CommonSaveRequestDto(String userId, String typeCode, String typeName) {
        this.userId = userId;
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public Common toEntity(){
        return Common.builder()
                .userId(userId)
                .typeCode(typeCode)
                .typeName(typeName)
                .build();
    }
}