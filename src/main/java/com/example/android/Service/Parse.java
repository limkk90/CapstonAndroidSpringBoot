package com.example.android.Service;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;


@Component
@Transactional
public class Parse {
    protected String naverParse(String url) {
        final String CLIENT_ID = "q6Eo2DmxuhgNFGBacL5i";
        final String CLIENT_SECRET = "iKdLFSl0BF";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.addHeader("X-Naver-Client-Id", CLIENT_ID);
        request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);

        return ParseJson(client, request);
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

    private String ParseXML(String url) { // 수정 필요
        String xml = "";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // request.addHeader("X-Naver-Client-Id", CLIENT_ID);
        // request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);

        try {
            HttpResponse response = client.execute(request);
            xml = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xml2json(xml);
    }

    public String xml2json(String xml) {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        String json = "";
        JSONObject xmlJSONObj = null;

        try {
            xmlJSONObj = XML.toJSONObject(xml);
            json = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
