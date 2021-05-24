package com.example.android.Service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NaverApiService {

    @Autowired
    private Parse API;

    public String getData(String uri) {
        String result = "";

        try {
            result = API.naverParse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getNaverNews() {
        final String uri = "https://openapi.naver.com/v1/search/news.json" +
                "?query=전기차&display=10";

        String data = getData(uri);
        JSONObject jobj = null;

        try{
            JSONParser parse = new JSONParser();
            jobj = (JSONObject) parse.parse(data);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return jobj.get("items").toString();
    }
}
