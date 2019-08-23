package com.university.webservice.domain.userMoneyItem;

import com.university.webservice.domain.common.Common;
import com.university.webservice.domain.userMoney.UserMoney;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Data
@Entity
// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
@IdClass(UserMoneyItemPK.class)
public class UserMoneyItem {

    @Id
    private String userId;

    @Id
    private String typeCode;

    @Id
    private String year;

    @Id
    private String month;

    private Long price;

    private Long ratio;

    private String regdate;

    private String moddate;

    @Transient
    @OneToOne
    private Common common;

    @Transient
    @OneToOne
    private UserMoney userMoney;

    @Builder
    public UserMoneyItem(String userId, String typeCode,String year, String month, Long price, Long ratio) {
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String formatTime = dataFormat.format(time.getTime());
        this.userId = userId;
        this.typeCode = typeCode;
        this.year = year;
        this.month = month;
        this.price = price;
        this.ratio = ratio;
        this.regdate = formatTime;
        this.moddate = formatTime;
    }

}

@Data
class UserMoneyItemPK implements Serializable {
    private String userId;
    private String year;
    private String month;
    private String typeCode;

}


