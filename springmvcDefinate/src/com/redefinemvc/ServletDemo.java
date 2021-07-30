package com.redefinemvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/5/1 12:34
 * @Modified By：
 */
public class ServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String attribute = (String)getServletContext().getAttribute("mydata");
        System.out.println(attribute);
    }
}
