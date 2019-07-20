package com.university.webservice.domain.userMoneyItem;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Data
@Entity
@SequenceGenerator(name = "USERMONEYITEM_SEQ", sequenceName = "SOONGSIL_TEAM_SEQ", initialValue = 2, allocationSize = 1)
// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
@IdClass(UserMoneyItemPK.class)
public class UserMoneyItem {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERMONEYITEM_SEQ")

    @Id
    private String userId;

    @Column(length = 10,columnDefinition = "TEXT", nullable = false) //컬럼 형식이 TEXT 널 못넣음
    private String typeCode;

    @Column(length = 12,columnDefinition = "TEXT", nullable = false) //컬럼 형식이 TEXT 널 못넣음
    private String year;

    private String month;

    private String price;

    private String ratio;

    private String regdate;

    private String moddate;


    @Builder
    public UserMoneyItem(String userId, String year, String month, String price, String ratio) {
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String formatTime = dataFormat.format(time.getTime());
        this.userId = userId;
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
}
