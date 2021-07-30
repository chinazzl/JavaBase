package com.redefinemvc.listener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/5/1 12:13
 * @Modified By：
 */
public class MydataListener extends ContextLoaderListener implements ServletContextListener{

    private ServletContext sc = null;

    // 该方法 在servletContext启动后被调用，并准备好处理客户端请求
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        sc = servletContextEvent.getServletContext();
        sc.setAttribute("mydata", "this is myListener");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.sc = null;
    }
}
