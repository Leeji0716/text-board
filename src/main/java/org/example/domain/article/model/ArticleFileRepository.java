package org.example.domain.article.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ArticleFileRepository implements Repository{
    private int latestId = 1;
    private ArrayList<Article> articleList = new ArrayList<>(); //저장소
    private final String jsonFilePath = "article.json";

    public void makeTestData(){
        System.out.println("테스트 데이터를 생성하지 않습니다.");
    }

    public Article findArticleById(int id) {
        //id에 해당하는 게시물 (article) 반환
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null; // null -> 없다. 객체 타입에서만 사용 가능

    }
    public void deleteArticle(Article article) {
        articleList.remove(article);
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(jsonFilePath);

        try {
            mapper.writeValue(file, articleList);
            System.out.println("JSON 파일에서 요소가 성공적으로 삭제되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArticleFileRepository(){
        this.articleList = loadPostsFromFile("article.json");
        if(articleList.size() == 0){
            latestId = 0;
            return;
        }
        int index = (this.articleList.size() - 1); //개수 -1. 마지막 인덱스
        Article article = articleList.get(index);
        latestId = article.getId();
    }
    private ArrayList<Article> loadPostsFromFile(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Article> data = new ArrayList<>();
        try {
            // JSON 파일을 읽어와 ArrayList<Article>으로 변환
            data = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Article saveArticle(String title, String body){
        // 번호는 latestID, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간
        //json 파일로 저장
        latestId++; //번호 증가

        CommonUtil commonUtil = new CommonUtil();
        Article a1 = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
        articleList.add(a1); //Article 먼저 리스트에 담기

        ObjectMapper mapper = new ObjectMapper();
        try {
            // 객체를 JSON 형태로 변환하여 파일에 저장
            mapper.writeValue(new File("article.json"), articleList);
            System.out.println("객체가 JSON 형태로 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a1;
    }
    public ArrayList<Article> findAll() {
        //json 파일을 읽어와서 ArrayList로 반환
        ArrayList<Article> data = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON 파일을 읽어와 ArrayList<Article>으로 변환
            data = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void updateArticle(Article article, String newTitle, String newBody) {
        Article target = findArticleById(article.getId());

        if(target != null) {
            target.setTitle(newTitle);
            target.setBody(newBody);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON 파일을 읽어와서 ArrayList<Article> 객체로 변환
            ArrayList<Article> data = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>() {});
            // article 객체를 업데이트
            article.setTitle(newTitle);
            article.setBody(newBody);

            // data 리스트에서 해당 article을 찾아 업데이트
            for (Article a : data) {
                if (a.getId() == article.getId()) {
                    a.setTitle(newTitle);
                    a.setBody(newBody);
                    break;
                }
            }
            // 업데이트된 data를 다시 JSON 파일로 쓰기
            mapper.writeValue(new File("article.json"), data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Article> findArticleByKeyword(String keyword) {
        ArrayList<Article> searchedList = new ArrayList<>();
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }
        return searchedList;
    }
}
