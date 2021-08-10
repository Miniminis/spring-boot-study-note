package com.company.proxy.cache;

import com.company.proxy.cache.BrowserProxy;

public class MainCacheProxy {

    public static void run() {
//        Browser browser = new Browser("www.naver.com");
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();

        BrowserProxy browserProxy = new BrowserProxy("www.naver.com");
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
        browserProxy.show();
    }
}
