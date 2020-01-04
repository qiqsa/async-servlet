package com.example.asyncservlet.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Qi.qingshan on 2020/1/4
 * 监听异步任务生命周期中的一些动作。
 */
public class AppAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        String message = "Request timeout !";
        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write(message);
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}
