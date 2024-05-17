package com.cool.service;

import org.json.JSONException;
import org.json.JSONObject;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson(String fcm_token, String title, String content) throws JSONException {
        JSONObject body = new JSONObject();

        // 다중
        // String sampleData[] =
        // {"eTqH1v0HSe-UqbWtqvjFHH:APA91bHh3RbWQEUac7mgcrEdJ6P4-fnuIUCmTEz8RtS4QI1WftfQ_zHVKgwF3fMl6GXKNvINYOTkm1e5DBVu7FWjtH0slaq6YWIaT7SxSWnG6WEBY73047ZUzZ3RLU4C0yWv_NheMUNy"};

        // List<String> tokenlist = new ArrayList<String>();

        // for(int i=0; i<sampleData.length; i++){
        // tokenlist.add(sampleData[i]);
        // }

        // JSONArray array = new JSONArray();

        // for(int i=0; i<tokenlist.size(); i++) {
        // array.put(tokenlist.get(i));
        // }
        // body.put("registration_ids", array); 다중

        body.put("to", fcm_token);

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", content);
        // notification.put("sound", "default");

        body.put("notification", notification);

        return body.toString();
    }
}