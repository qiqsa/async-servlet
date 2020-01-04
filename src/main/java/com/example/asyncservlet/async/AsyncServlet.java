package com.example.asyncservlet.async;

import com.example.asyncservlet.listener.AppAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Qi.qingshan on 2020/1/4
 */
@WebServlet(urlPatterns = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        String time = request.getParameter("time");
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AppAsyncListener());
        asyncContext.setTimeout(1000);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("executor");

        executor.execute(new AsyncRequestProcessor(asyncContext,Long.parseLong(time)));

    }
}
