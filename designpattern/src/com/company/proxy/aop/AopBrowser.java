package com.company.proxy.aop;

import com.company.proxy.cache.Html;
import com.company.proxy.cache.IBrowser;

public class AopBrowser implements IBrowser {

    private String url;
    private Html html;
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after) {
        this.url = url;
        this.before = before;
        this.after = after;
    }

    @Override
    public Html show() {

        before.run();

        if (html == null) {
            System.out.println("browser loading html from " + url);
            html = new Html(url);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        after.run();

        System.out.println("browser loading cached from " + url);
        return html;
    }

}
