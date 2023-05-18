package com.jaehyun.store;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForeignKeyChecker {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/store";
        String username = "root";
        String password = "0000";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            String tableName = "store"; // 테이블 이름

            // 외래 키 제약 조건 조회
            ResultSet resultSet = metaData.getImportedKeys(null, null, tableName);
            while (resultSet.next()) {
                String fkName = resultSet.getString("FK_NAME");
                System.out.println("Foreign Key Name: " + fkName);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
