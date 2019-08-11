package com.university.webservice.domain.userMoney;

import com.university.webservice.domain.userMoneyItem.UserMoneyItem;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Data
@Entity
// n=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
@IdClass(UserMoneyPK.class)
public class UserMoney {

    @Id
    private String userId;

    @Id
    private String year;

    @Id
    private String month;

    @Id
    private Long money;

    private String regdate;

    private String moddate;

    @OneToMany(mappedBy = "userMoney",cascade=CascadeType.ALL)
    private Collection<UserMoneyItem> userMoneyItem;

    @Builder
    public UserMoney(String userId, String year, String month, Long money) {
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String formatTime = dataFormat.format(time.getTime());
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.money = money;
        this.regdate = formatTime;
        this.moddate = formatTime;
    }

}

@Data
class UserMoneyPK implements Serializable {
    private String userId;
    private String year;
    private String month;
    private Long money;

}

