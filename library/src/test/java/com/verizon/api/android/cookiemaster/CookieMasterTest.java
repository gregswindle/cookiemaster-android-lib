package com.verizon.api.android.cookiemaster;

import android.webkit.CookieManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpCookie;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.SuppressCode.suppressConstructor;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CookieManager.class)
public class CookieMasterTest {

    private final String url = "https://vzweb2.verizon.com";
    private final String sessionTokenName = "SMSESSION";
    private final String sessionTokenValue = "AlB42sJq5JY73L6LVA3t";
    private final String smsessionCookieHeader = "SMSESSION=AlB42sJq5JY73L6LVA3t; path=/; domain=.verizon.com; Secure; HTTPOnly";
    private final String thirdPartyCookie = "user_session=PUsECFYS; path=/; expires=Thu, 09 Mar 2017 23:39:40 -0000; secure; HttpOnly";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private HttpCookie httpCookie;
    private CookieManager cookieManagerMock;

    @Before
    public void setUp() throws Exception {
        suppressConstructor(CookieManager.class);
        mockStatic(CookieManager.class);
        cookieManagerMock = mock(CookieManager.class);
        when(CookieManager.getInstance()).thenReturn(cookieManagerMock);
    }

    @After
    public void tearDown() throws Exception {
        httpCookie = null;
    }

    @Test
    public void getCookieValue() throws Exception {
        when(cookieManagerMock.getCookie(anyString())).thenReturn(smsessionCookieHeader);
        String url = "https://vzweb2.verizon.com";
        String cookieName = "SMSESSION";
        String jsonHttpCookieString = CookieMaster.getCookieValue(url, cookieName);
        assertThat(jsonHttpCookieString, containsString(sessionTokenName));
    }

    @Test
    public void getCookieValueWithoutMatch() throws Exception {
        when(cookieManagerMock.getCookie(anyString())).thenReturn(thirdPartyCookie);
        String url = "https://vzweb2.verizon.com";
        String cookieName = "SMSESSION";
        String jsonHttpCookieString = CookieMaster.getCookieValue(url, cookieName);
        assertTrue(jsonHttpCookieString.isEmpty());
    }

    @Test
    public void setCookieValueWithHeaderString() throws Exception {
        CookieMaster.setCookieValue(url, smsessionCookieHeader);
        verify(cookieManagerMock, times(1)).setAcceptCookie(anyBoolean());
        verify(cookieManagerMock, times(1)).setCookie(anyString(), anyString());
    }

    @Test
    public void setCookieValueWithNameAndValue() throws Exception {
        CookieMaster.setCookieValue(url, sessionTokenName, sessionTokenValue);
        verify(cookieManagerMock, times(1)).setAcceptCookie(anyBoolean());
        verify(cookieManagerMock, times(1)).setCookie(anyString(), anyString());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void constructorThrowsIllegalAccessError() throws Exception {
        final Constructor<CookieMaster> constructor = CookieMaster.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            throw (UnsupportedOperationException) e.getTargetException();
        }
    }

}
