package com.example.asyncservlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Qi.qingshan on 2020/1/4
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        sce.getServletContext().setAttribute("executor",
                executor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //销毁线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) sce.getServletContext().getAttribute("executor");
        executor.shutdown();
    }
}
