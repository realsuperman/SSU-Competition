package com.university.webservice.dto.userMoneyItem;

import com.university.webservice.domain.common.Common;
import com.university.webservice.domain.userMoneyItem.UserMoneyItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMoneyItemSaveRequestDto {

    private String userId;
    private Long typeCode;
    private String year;
    private String month;
    private Long price;
    private Long ratio;
    private String regdate;
    private String moddate;

    @Builder
    public UserMoneyItemSaveRequestDto(String userId, Long typeCode, String year, String month,Long price,Long ratio) {
        this.userId = userId;
        this.typeCode = typeCode;
        this.year = year;
        this.month = month;
        this.price = price;
        this.ratio = ratio;
    }

    public UserMoneyItem toEntity(){
        return UserMoneyItem.builder()
                .userId(userId)
                .typeCode(typeCode)
                .year(year)
                .month(month)
                .price(price)
                .ratio(ratio)
                .build();
    }
}