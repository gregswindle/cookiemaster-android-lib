package com.verizon.api.android.cookiemaster;

import android.webkit.CookieManager;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.HttpCookie;
import java.util.List;


/**
 * The type Cookie master.
 */
public final class CookieMaster {

    private CookieMaster() {
        throw new UnsupportedOperationException("CookieMaster is a static utility class, and cannot be instantiated.");
    }

    /**
     * Gets cookie value.
     *
     * @param url        the url
     * @param cookieName the cookie name
     * @return the cookie value
     * @throws JsonProcessingException the json processing exception
     */
    public static String getCookieValue(String url, String cookieName) throws JsonProcessingException {
        CookieManager cookieManager = CookieManager.getInstance();
        String cookieManagerCookie = cookieManager.getCookie(url);
        List<HttpCookie> httpCookies = HttpCookie.parse(cookieManagerCookie);
        String jsonHttpCookie = "";
        for (HttpCookie cookie : httpCookies) {
            if (cookie.getName().equals(cookieName)) {
                jsonHttpCookie = HttpCookieJson.toJson(cookie);
                break;
            }
        }
        return jsonHttpCookie;
    }

    /**
     * Create or update an `HttpCookie` with a `Set-Cookie` directive's value by URL.
     *
     * @param url               the url
     * @param cookieHeaderValue the cookie header value
     */
    public static void setCookieValue(String url, String cookieHeaderValue) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookieHeaderValue);
    }

    /**
     * Sets cookie value.
     *
     * @param url         the url
     * @param cookieName  the cookie name
     * @param cookieValue the cookie value
     */
    public static void setCookieValue(String url, String cookieName, String cookieValue) {
        HttpCookie httpCookie = new HttpCookie(cookieName, cookieValue);
        String cookieHeaderValue = httpCookie.toString().replace("\"", "");
        setCookieValue(url, cookieHeaderValue);
    }
}
