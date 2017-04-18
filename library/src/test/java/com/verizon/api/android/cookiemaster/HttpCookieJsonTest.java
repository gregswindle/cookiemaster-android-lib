package com.verizon.api.android.cookiemaster;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpCookie;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class HttpCookieJsonTest {

    private final String sessionTokenName = "SMSESSION";
    private final String sessionTokenValue = "AlB42sJq5JY73L6LVA3t";
    private final String cookieHeader = "SMSESSION=AlB42sJq5JY73L6LVA3t; path=/; domain=.verizon.com; Secure; HTTPOnly";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private HttpCookie httpCookie;

    @Before
    public void setUp() throws Exception {
        httpCookie = new HttpCookie(sessionTokenName, sessionTokenValue);
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(Log.class);
    }

    @After
    public void tearDown() throws Exception {
        httpCookie = null;
    }

    @Test
    public void toJson() throws Exception {
        String expectedJson = "{\"name\":\"SMSESSION\",\"value\":\"AlB42sJq5JY73L6LVA3t\",\"comment\":null,\"commentURL\":null,\"domain\":null,\"maxAge\":-1,\"path\":null,\"portlist\":null,\"secure\":false,\"httpOnly\":false,\"version\":1,\"discard\":false}";
        String actualJson = HttpCookieJson.toJson(httpCookie);
        assertEquals(actualJson, expectedJson, actualJson);
    }

    @Test
    public void parse() throws Exception {
        JsonNode jsonNode = HttpCookieJson.parse(httpCookie);
        assertNotNull(jsonNode);
        assertEquals(jsonNode.get("name").asText(), sessionTokenName);
        assertEquals(jsonNode.get("value").asText(), sessionTokenValue);
    }

    @Test
    public void fromHeader() throws Exception {
        List<String> httpCookieList = HttpCookieJson.fromHeader(cookieHeader);
        assertEquals(httpCookieList.size(), 1);
        System.out.println(httpCookieList.get(0));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void constructorThrowsIllegalAccessError() throws Exception {
        final Constructor<HttpCookieJson> constructor = HttpCookieJson.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }
}