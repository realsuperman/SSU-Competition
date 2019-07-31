package com.university.webservice.domain.common;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Data
@Entity
@IdClass(CommonPK.class)
public class Common {

    @Id
    private String userId;

    @Id
    private Long typeCode;

    @Column(length = 100, columnDefinition = "TEXT",nullable = false) //컬럼 사이즈 100 이며 널 못넣음
    private String typeName;

    private String regdate;

    private String moddate;


    @Builder
    public Common(String userId, Long typeCode, String typeName) {
        SimpleDateFormat dataFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        String formatTime = dataFormat.format(time.getTime());
        this.userId = userId;
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.regdate = formatTime;
        this.moddate = formatTime;
    }

}

@Data
class CommonPK implements Serializable {
    private String userId;
    private Long typeCode;
}
