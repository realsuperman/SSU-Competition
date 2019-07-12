package com.university.webservice.domain.users;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Data
@Entity
@SequenceGenerator(name = "USERS_SEQ", sequenceName = "SOONGSIL_TEAM_SEQ", initialValue = 2, allocationSize = 1)
// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
@IdClass(UsersPK.class)
public class Users {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    private Long id;

    @Id
    private String userId;

    @Column(length = 100, columnDefinition = "TEXT",nullable = false) //컬럼 사이즈 100 이며 널 못넣음
    private String userPw;

    @Column(length = 10,columnDefinition = "TEXT", nullable = false) //컬럼 형식이 TEXT 널 못넣음
    private String name;

    @Column(length = 12,columnDefinition = "TEXT", nullable = false) //컬럼 형식이 TEXT 널 못넣음
    private String phone;

    private String regdate;

    private String moddate;


    @Builder
    public Users(String userId, String userPw, String name, String phone) {
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String formatTime = dataFormat.format(time.getTime());
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.phone = phone;
        this.regdate = formatTime;
        this.moddate = formatTime;
    }

}

@Data
class UsersPK implements Serializable {
    private Long id;
    private String userId;
}
