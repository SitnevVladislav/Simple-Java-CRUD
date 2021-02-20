package com.company;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseService {

    public static String getTeacher() {
        Gson gson = new Gson();
        //readFile();
        List<Teacher> list = null;
        try {
            list = DataBase.getTeacher();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Teacher teacher : list) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(teacher.getId()));
            map.put("name", teacher.getName());
            map.put("last_name", teacher.getLast_name());
            map.put("faculty", teacher.getFaculty());
            map.put("subject", teacher.getSubject());

            mapList.add(map);
        }
        System.out.println(gson.toJson(mapList));

        return gson.toJson(mapList);
    }

    public static void writeTeacher(String msg) {

        try {
            Teacher teacher = makeTeacher(msg);
            DataBase.add(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateTeacher(String json) {
        System.out.println("update SERVICE");
        json = json.replace("$", "");
        System.out.println(json);
        try {
            DataBase.updateTeacher(makeTeacher(json));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteTeacher(String id) {
        System.out.println(id);
        System.out.println(Integer.valueOf(id));
        try {
            DataBase.deleteTeacher(Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static Teacher makeTeacher(String msg) {
        return new Gson().fromJson(msg, Teacher.class);
    }
}
