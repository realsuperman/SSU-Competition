package com.university.webservice.dto.userMoney;

import com.university.webservice.domain.userMoney.UserMoney;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMoneySaveRequestDto {

    private String userId;
    private String year;
    private String month;
    private Long money;
    private String regdate;
    private String moddate;

    @Builder
    public UserMoneySaveRequestDto(String userId, String year, String month,Long money) {
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.money = money;
    }

    public UserMoney toEntity(){
        return UserMoney.builder()
                .userId(userId)
                .year(year)
                .month(month)
                .money(money)
                .build();
    }
}