/**
 * 
 */
package com.pinot.sumitbackend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * {@link Deprecated}
 */
@Service
public class SERPService {

	private String zenRowsAPIKey = "167787c53beeac3664882aba7eeda4a147a82bc9";

	public void ZenRowsApiRequest() {

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				"https://api.zenrows.com/v1/?apikey=" + zenRowsAPIKey + "&url=https%3A%2F%2Fhttpbin.io%2Fanything",
				String.class);
		System.out.println(response);
	}
	
	public void DuckDuckGoRequest(String query) {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				"https://serpapi.com/search.json?engine=duckduckgo&kl=fr-fr&q=" + query,
				String.class);
		System.out.println(response);
	}
}
