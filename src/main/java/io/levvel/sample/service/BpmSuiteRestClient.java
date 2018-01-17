package io.levvel.sample.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

@Service
public class BpmSuiteRestClient {
	
	private static final Logger log = LoggerFactory.getLogger(BpmSuiteRestClient.class);
	private static final String BASE_URL = "http://localhost:8080/business-central/rest/";
	private static final String DEPLOYMENT_ID = "org.jbpm:human-resources:1.0";
	private static final String PROCESS_DEF_ID = "hiring";
	private final RestTemplate restTemplate;
	
	public BpmSuiteRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private HttpHeaders buildAuthHeader() {
		String plainCreds = "bpmsAdmin:bpmsuite1!";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64Utils.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		return headers;
	}
	
	public String startWorkFlow() {
		String url = BASE_URL + "runtime/" + DEPLOYMENT_ID + "/process/" + PROCESS_DEF_ID + "/start?map_name={name}";
		HttpEntity<String> request = new HttpEntity<String>(buildAuthHeader());
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class, "mark");
		
		log.debug("result of POST: {}", response.getBody());
		return response.getBody();
	}
	
	public String signalWorkFlow(String id) {
		String url = BASE_URL + "runtime/" + DEPLOYMENT_ID + "/process/instance/" + id + "/signal?signal={signal}&name={name}";
	
		HttpEntity<String> request = new HttpEntity<String>(buildAuthHeader());
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class, "tweetIt","mark");
		
		log.debug("result of POST: {}", response.getBody());
		return response.getBody();
		
	}

}
