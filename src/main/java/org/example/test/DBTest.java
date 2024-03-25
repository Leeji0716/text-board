package org.example.test;

import org.example.base.CommonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBTest {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/t2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static void main(String[] args) {
        CommonUtil commonUtil = new CommonUtil();
//        String sql = "INSERT INTO article (title, body, hit, regDate) VALUES (?, ?, ?, ?)";
        try {
            // JDBC 드라이버 로드
            Class.forName(DRIVER_CLASS);

            // 데이터베이스 연결
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                // SQL 쿼리 작성
                String sql = "INSERT INTO article (title, body, hit, regDate) VALUES (?, ?, ?, ?)";

                // PreparedStatement 준비
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // 쿼리에 파라미터 설정
                    statement.setString(1, "제목1");
                    statement.setString(2, "내용1");
                    statement.setInt(3,0);
                    statement.setString(4,commonUtil.getCurrentDateTime());

                    // 쿼리 실행
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows == 1) {
                        // 게시물이 성공적으로 저장된 경우
                        System.out.println("게시물이 성공적으로 저장되었습니다.");
                        // 저장된 게시물 반환
//                        return new Article(title, body); // 적절한 Article 객체를 반환하도록 수정해야 합니다.
                    } else {
                        // 게시물 저장 실패
                        System.err.println("게시물 저장에 실패했습니다.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }
//        return null;
    }
}
