package com.eth.dir.util;

import java.util.HashMap;
import java.util.Map;

public class ServletUtil {

    public static Map<String, String> getInitialParameters(){
        Map<String, String> initParamMap = new HashMap<>();
        initParamMap.put("jersey.config.server.provider.packages", "com.eth.dir.service");
        return initParamMap;
    }
}
