package com.verizon.api.android.cookiemaster;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Http cookie json.
 */
final class HttpCookieJson {

    private static final String LOG_TAG = "[cookiemaster-android]";

    /**
     * To json string.
     *
     * @param httpCookie the http cookie
     * @return the string
     */
    static String toJson(HttpCookie httpCookie) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(httpCookie);
    }

    /**
     * Parse json node.
     *
     * @param httpCookie the http cookie
     * @return the json node
     */
    static JsonNode parse(HttpCookie httpCookie) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonHttpCookieString = toJson(httpCookie);
        JsonNode httpCookieJson = mapper.readTree(jsonHttpCookieString);
        Log.d(LOG_TAG, jsonHttpCookieString);

        return httpCookieJson;
    }

    /**
     * From header list.
     *
     * @param cookieHeaderValue the cookie header value
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    static List<String> fromHeader(String cookieHeaderValue) throws JsonProcessingException {
        List<HttpCookie> httpCookies = HttpCookie.parse(cookieHeaderValue);
        List<String> jsonHttpCookies = new ArrayList<>();
        for (HttpCookie httpCookie : httpCookies) {
            String jsonCookie = toJson(httpCookie);
            jsonHttpCookies.add(jsonCookie);
            Log.d(LOG_TAG, jsonCookie);
        }

        return jsonHttpCookies;
    }

}