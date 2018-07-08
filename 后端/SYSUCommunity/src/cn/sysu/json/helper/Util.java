package cn.sysu.json.helper;

import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/*
author: kingchiu
 */

public class Util {

    public static Object jsonToBean(String json,Class cls) {
        String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
        JSONObject jsonObject = JSONObject.fromObject(json);
        return JSONObject.toBean(jsonObject, cls);
    }

    public static String beanToJson(Object obj,String dateFormat) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor(dateFormat));
        JSONObject json = JSONObject.fromObject(obj, config);
        return json.toString();
    }

    public static String arrayToJson(Object obj) {
    	 JsonConfig config = new JsonConfig();
         config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
         JSONArray json = JSONArray.fromObject(obj, config);
         return json.toString();
    }
    
}