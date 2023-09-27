package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	private static final String serviceKey = "Lpcy%2FgHhHaEFJ5C92XQlkk20T57uozr5vw%2Fcg5%2B9W6U51qN9yr63cSAu9W51xynxIvbd0iwDrtzb1Z4zU5i7Gg%3D%3D";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		System.out.println("[APIController] home method has been called");
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="/air.do", produces="application/json; charset=utf-8")
	public String airPollution(@RequestParam String location) throws IOException {
		System.out.println("[APIController] query method has been called");
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + serviceKey;
		
		url += "&returnType=" + URLEncoder.encode("json", "UTF-8");
	    if ("서울".equals(location)) {
	        url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
	    } else if ("부산".equals(location)) {
	        url += "&sidoName=" + URLEncoder.encode("부산", "UTF-8");
	    } else if ("대전".equals(location)) {
	        url += "&sidoName=" + URLEncoder.encode("대전", "UTF-8");
	    } else {
	        return "Invalid location";
	    }
		
		URL requestURL = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestURL.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		System.out.println(responseText);
		br.close();
		urlConnection.disconnect();
		return responseText;
	}
}
