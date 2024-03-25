package org.example.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int id; // 번호
    private String title; // 제목
    private String body; // 내용
    private int hit; // 조회수
    private String regDate; // 등록날짜



    public void increaseHit() {
        this.hit++;
    }
}
