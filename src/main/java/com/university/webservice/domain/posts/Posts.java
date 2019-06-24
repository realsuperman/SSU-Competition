package com.university.webservice.domain.posts;

import javafx.stage.DirectoryChooserBuilder;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 추가 및 기본생성자의 접근 권한을 protected로 제한
@Getter
@Entity
public class Posts {

    @Id // 기본키 설정
    @GeneratedValue //mysql의 Auto_increment 설정임
    private Long id;

    @Column(length = 500, nullable = false) //컬럼 사이즈 500 이며 널 못넣음
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //컬럼 형식이 TEXT 널 못넣음
    private String content;

    private String author;


    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}