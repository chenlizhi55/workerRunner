package com.chen.worker.test.servlet;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import sun.java2d.pipe.AAShapePipe;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author chenlizhi
 * @date 2018/7/19
 */
@WebServlet(name = "mark_work", urlPatterns ={"/work/*"},loadOnStartup = 1)
public class workServlet extends HttpServlet {
    ApplicationContextProvider applicationContextProvider=new ApplicationContextProvider();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        System.out.println("workServlet初始化...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getPathInfo();
        String beanName=url.split("/")[1];
        String methodName=url.split("/")[2];
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        try {
           // Object obj=wac.getBean(beanName);
            Object obj=applicationContextProvider.getBean(beanName);
            //Object obj=applicationContextProvider.getBean(Class.forName(beanName));
            Method method=obj.getClass().getMethod(methodName);
            method.invoke(obj);
        } catch (NoSuchMethodException e){
            e.printStackTrace();
            resp.getWriter().append("worker中该方法不存在");
            return;
        }
        catch (Exception e){
            e.printStackTrace();
            resp.getWriter().append("worker执行失败:"+e.getMessage());
            return;
        }
        resp.getWriter().append("worker执行成功");

    }

}
