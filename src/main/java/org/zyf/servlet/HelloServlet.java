package org.zyf.servlet;

import org.zyf.service.HelloService;
import org.zyf.service.impl.HelloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZYF
 * @create 2021-4-2 11:08
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private HelloService helloService = new HelloServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(this.helloService.findAll().toString());
    }
}
