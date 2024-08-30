package org.example.tests;

import io.restassured.response.Response;
import org.example.config.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.utils.TokenManager;

public class AuthenticationTest extends TestBase {

    @Test
    public void testAuthTokenGeneration() {
        String token = TokenManager.getToken();
        Assert.assertNotNull(token, "Token should not be null");
    }
}

