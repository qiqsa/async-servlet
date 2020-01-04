package com.example.asyncservlet.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * Created by Qi.qingshan on 2020/1/4
 */
public class AsyncRequestProcessor implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncRequestProcessor.class);

    private AsyncContext asyncContext;

    private long time;

    public AsyncRequestProcessor(AsyncContext asyncContext, long time) {
        this.asyncContext = asyncContext;
        this.time = time;
    }

    @Override
    public void run() {
        LOG.info("if supported async ? " + asyncContext.getRequest().isAsyncSupported());
        doPreocessor(time);
    }

    private void doPreocessor(long time){
        ServletRequest servletRequest = asyncContext.getRequest();
        ServletResponse servletResponse = asyncContext.getResponse();
        try {
            Thread.sleep(time);
            ServletInputStream inputStream = servletRequest.getInputStream();
            byte[]bytes =new byte[inputStream.available()];
            while (inputStream.isReady()){
                inputStream.read(bytes);
            }
            String request = new String(bytes);
            LOG.info("request : " + request);
            PrintWriter writer = servletResponse.getWriter();
            writer.write("ok!");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
