package com.github.pavelkv96.vk_coffee.mocks;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class Mocks {
    public static InputStream stream(final String pName) throws IOException {
        final InputStream resourceAsStream = Mocks.class.getClassLoader().getResourceAsStream(pName);
        Assert.assertNotNull("resource it's null", resourceAsStream);
        return resourceAsStream;//convertStreamToString(resourceAsStream);
    }
}
