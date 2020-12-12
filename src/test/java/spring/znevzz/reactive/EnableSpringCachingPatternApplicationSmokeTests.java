package spring.znevzz.reactive;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EnableSpringCachingPatternApplicationSmokeTests {

	private String host;
	private int port;
	private RestTemplate restTemplate;
	private List<SmokeTestRequest> requests = new LinkedList<>();

	@Before
	public void init(){
		this.host = "localhost";
		this.port = 10001;
		this.restTemplate = new RestTemplate();
		this.requests.add(
				new SmokeTestRequest(
						"", Collections.emptyMap(), ""
				)
		);
	}

	@Test
	public void checkStartUp(){
		this.requests.forEach(smokeTestRequest -> {

			String url = buildCompleteUrl(smokeTestRequest);
			if(StringUtils.isBlank(smokeTestRequest.postObjectJson)) {
				// GET REQUEST

				String response = this.restTemplate.getForObject(
						url, String.class
				);

				assertTrue(StringUtils.isNotBlank(response));

			} else {
				// POST REQUEST
				// TODO
			}


		});
	}

	private String buildCompleteUrl(SmokeTestRequest request) {
		String baseUrl = String.format("http://%s:%d/%s?",
				this.host, this.port, request.apiUrl
		);

		StringJoiner queryParameters = new StringJoiner("&");

		request.parameters.forEach( (key, value) -> queryParameters.add(String.format("%s=%s", key, value)));

		return baseUrl + queryParameters.toString();
	}


	class SmokeTestRequest {
		String apiUrl;
		Map<String, String> parameters;
		String postObjectJson;

		public SmokeTestRequest(String apiUrl, Map<String, String> parameters, String postObjectJson) {
			this.apiUrl = apiUrl;
			this.parameters = parameters;
			this.postObjectJson = postObjectJson;
		}
	}


}
