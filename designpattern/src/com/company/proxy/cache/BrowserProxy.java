package com.company.proxy.cache;

public class BrowserProxy implements IBrowser {

    private String url;
    private Html html;

    public BrowserProxy(String url) {
        this.url = url;
    }

    @Override
    public Html show() {

        if (html == null) {
            System.out.println("browser loading html from " + url);
            html = new Html(url);
        }

        System.out.println("browser loading cached from " + url);
        return html;
    }
}
