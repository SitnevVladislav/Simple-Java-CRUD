package com.company;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("get");
        resp.getWriter().write(DataBaseService.getTeacher());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String json = req.getReader().readLine();
        if(json.contains("add = ")){
            json = json.replaceAll("add = ","");
            DataBaseService.writeTeacher(json);
        }
        if(json.contains("delete = ")){
            System.out.println("deleteYarn  MAINCONTROLLEER");
            json = json.replaceAll("delete = ","");
            DataBaseService.deleteTeacher(json);
        }
        if(json.contains("update = ")){
            System.out.println("update  MAINCONTROLLEER");
            json = json.replaceAll("update = ","");
            DataBaseService.updateTeacher(json);
        }

    }

}
