package com.example.coindesk.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class Tools {
    @SuppressWarnings("unchecked")
    public void getcurrentprice() throws IOException, ParseException {
        URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            //Close the scanner
            scanner.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(inline);

            JSONObject jsonTime = (JSONObject) parser.parse(jsonObject.get("time").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String datetime = jsonTime.get("updatedISO").toString();

            String format_date = null;
            try {
                Date dateISO = sdf.parse(datetime);
                format_date = sdf2.format(dateISO);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            JSONObject jsonbpi = (JSONObject) parser.parse(jsonObject.get("bpi").toString());

            JSONObject jsonUSD = (JSONObject) parser.parse(jsonbpi.get("USD").toString());
            JSONObject jsonUSD_New = new JSONObject();
            jsonUSD_New.put("匯率", jsonUSD.get("rate").toString());
            jsonUSD_New.put("幣別", jsonUSD.get("symbol").toString());
            jsonUSD_New.put("幣別名稱", jsonUSD.get("code").toString());
            jsonUSD_New.put("更新時間", format_date);
            jsonUSD_New.put("浮動利率", jsonUSD.get("rate").toString());
            jsonUSD_New.put("說明", jsonUSD.get("description").toString());

            this.savejson("http://127.0.0.1:8080/api/v1/save",jsonUSD_New.toJSONString());

            JSONObject jsonGBP = (JSONObject) parser.parse(jsonbpi.get("GBP").toString());
            JSONObject jsonGBP_New = new JSONObject();
            jsonGBP_New.put("匯率", jsonGBP.get("rate").toString());
            jsonGBP_New.put("幣別", jsonGBP.get("symbol").toString());
            jsonGBP_New.put("幣別名稱", jsonGBP.get("code").toString());
            jsonGBP_New.put("更新時間", format_date);
            jsonGBP_New.put("浮動利率", jsonGBP.get("rate").toString());
            jsonGBP_New.put("說明", jsonGBP.get("description").toString());
            this.savejson("http://127.0.0.1:8080/api/v1/save",jsonGBP_New.toJSONString());

            JSONObject jsonEur = (JSONObject) parser.parse(jsonbpi.get("EUR").toString());
            JSONObject jsonEur_New = new JSONObject();
            jsonEur_New.put("匯率", jsonEur.get("rate").toString());
            jsonEur_New.put("幣別", jsonEur.get("symbol").toString());
            jsonEur_New.put("幣別名稱", jsonEur.get("code").toString());
            jsonEur_New.put("更新時間", format_date);
            jsonEur_New.put("浮動利率", jsonEur.get("rate").toString());
            jsonEur_New.put("說明", jsonEur.get("description").toString());
            this.savejson("http://127.0.0.1:8080/api/v1/save",jsonEur_New.toJSONString());
        }
    }


    public void savejson(String url,String json) throws IOException {
        URL urlsave = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlsave.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }
    }

}


