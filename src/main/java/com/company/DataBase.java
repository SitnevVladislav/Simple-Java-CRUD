package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static Connection dbConnection;
    private static Boolean status = false;

    private static Connection getDbConnection() {
        System.out.println("getDbConnection");
        if (!status) {
            String connectionString = "jdbc:mysql://localhost:3306/room";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                dbConnection = DriverManager.getConnection(connectionString, "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            status = true;
        }
        System.out.println(dbConnection);
        System.out.println("getDbConnection       ok");
        return dbConnection;
    }

    public static void add(Teacher teacher) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        statement.execute("INSERT INTO room.teachers (id, name, last_name, faculty, subject)" +
                " VALUES(" + countOfTeachers() + ",'" + teacher.getName() + "','" + teacher.getLast_name() + "','"
                + teacher.getFaculty() + "','" + teacher.getSubject() + "')");
    }

    public static List<Teacher> getTeacher() throws SQLException {
        List<Teacher> list = new ArrayList<>();
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers");
        while (resultSet.next()) {
            list.add(new Teacher(resultSet.getInt("id"), resultSet.getNString("name"),
                    resultSet.getNString("last_name"), resultSet.getNString("faculty"),
                    resultSet.getNString("subject")));
        }
        return list;
    }

    public static void deleteTeacher(int id) throws SQLException {

        Statement statement = getDbConnection().createStatement();
        System.out.println(statement.execute("DELETE FROM teachers WHERE id = " + id));
    }

    private static int countOfTeachers() {
        int count = 1;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet set = statement.executeQuery("SELECT id from teachers");
            while (set.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void updateTeacher(Teacher teacher) throws SQLException {
        Statement statement = getDbConnection().createStatement();
        System.out.println(statement.execute("UPDATE teachers SET name='" + teacher.getName() + "'," +
                " last_name='" + teacher.getLast_name() +
                "', faculty='" + teacher.getFaculty() + "'," +
                " subject='" + teacher.getSubject() +
                "' WHERE id='" + teacher.getId() + "'"));
    }
}
