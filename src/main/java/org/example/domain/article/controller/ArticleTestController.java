package org.example.domain.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;
import org.example.domain.article.model.Article;
import org.example.domain.article.model.ArticleFileRepository;
import org.example.domain.article.model.ArticleRepository;
import org.example.domain.article.view.ArticleView;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleTestController {
    CommonUtil commonUtil = new CommonUtil();
    ArticleView articleView = new ArticleView();
    ArticleFileRepository articleRepository = new ArticleFileRepository();

    Scanner scan = commonUtil.getScanner();
    int WRONG_VALUE = -1;
    ObjectMapper mapper = new ObjectMapper();
    public void add() {

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        articleRepository.saveArticle(title, body);
        System.out.println("게시물이 등록되었습니다.");

    }
    public void list() {
        ArrayList<Article> articleList = articleRepository.findAll();
        articleView.printArticleList(articleList); // 전체 출력 -> 전체 저장소 넘기기
    }

    public void delete() {
        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if(inputId == WRONG_VALUE) {
            return;
        }
        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        articleRepository.deleteArticle(article);
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }
    public Article findArticleById(int id){
        return articleRepository.findById(id);
    }
    private int getParamAsInt(String param, int defaultValue) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
}
