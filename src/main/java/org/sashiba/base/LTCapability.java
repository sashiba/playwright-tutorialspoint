package org.sashiba.base;

import com.google.gson.JsonObject;

public class LTCapability {
    public static JsonObject getDefaultTestCapability() {
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();

        String user = "test";
        String accessKey = "123";

        capabilities.addProperty("browsername", "Chrome");
        ltOptions.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Playwright Text");
        ltOptions.addProperty("build", "Playwright Java Build 2");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        capabilities.add("LT:Options", ltOptions);

        return capabilities;
    }
}
