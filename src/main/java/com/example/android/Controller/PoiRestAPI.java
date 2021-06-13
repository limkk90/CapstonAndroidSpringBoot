package com.example.android.Controller;

import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class PoiRestAPI {

    @GetMapping("/poi")
    public Object getPoi(int lng, int lat){
        String url = "https://apis.openapi.sk.com/tmap/pois/search/around" +
                "?version=1&categories=편의점;노래방;PC방;카페"
                + "&centerLon=" + lng + "&centerLat=" + lat
                + "&radius=1";

        PoiRestAPI parse = null;
        Object result = parse.tMapAPI(url);
        JSONObject json = null;

        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(result.toString());
            result = json.get("searchPoiInfo");
            json = (JSONObject) parser.parse(result.toString());
            result = json.get("pois");
            json = (JSONObject) parser.parse(result.toString());
            result = json.get("poi");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    String tMapAPI(String url) {
        final String APP_KEY = "l7xx9a66c72f17c54b6997ea1e5081927786";

        url += "&appKey=" + APP_KEY;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        return ParseJson(client, request);
    }

    private String ParseJson(HttpClient client, HttpGet request) {
        String json = "";

        try {
            HttpResponse response = client.execute(request);
            json = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
