package com.university.webservice.domain.users;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Entity
public class Users {

    @Id // 기본키 설정
    @GeneratedValue //mysql의 Auto_increment 설정임
    private Long id;

    @Column(length = 100,columnDefinition = "TEXT", nullable = false) //컬럼 사이즈 100 이며 널 못넣음
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
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-d HH:mm:ss");
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