package org.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;
import org.example.domain.article.model.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileTest {
    public static void main(String[] args) {

//        CommonUtil commonUtil = new CommonUtil();
//        Article a1 = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            // 객체를 JSON 형태로 변환하여 파일에 저장
//            mapper.writeValue(new File("article.json"), a1);
//            System.out.println("객체가 JSON 형태로 파일에 저장되었습니다.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void jsonRead() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // JSON 파일을 읽어와 MyObject 객체로 변환
            Article a1 = mapper.readValue(new File("article.json"), Article.class);

            // 객체 정보 출력 (toString 메소드를 이용)
            System.out.println(a1.getTitle());
            System.out.println(a1.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            File file = new File("example.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
    }
    public static void jsonWrite() {

        CommonUtil commonUtil = new CommonUtil();
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, commonUtil.getCurrentDateTime());
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 객체를 JSON 형태로 변환하여 파일에 저장
            mapper.writeValue(new File("article.json"), a1);
            System.out.println("객체가 JSON 형태로 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try {
            PrintWriter writer = new PrintWriter("example.txt");
            writer.println("Hello, Java 파일 저장 예제입니다.");
            writer.println("이것은 두 번째 줄입니다.");
            writer.close(); // 파일을 닫아서 변경사항을 저장합니다.
            System.out.println("파일이 성공적으로 저장되었습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 생성할 수 없습니다.");
            e.printStackTrace();
        }
    }
}
