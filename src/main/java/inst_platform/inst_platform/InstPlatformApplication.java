package inst_platform.inst_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class InstPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstPlatformApplication.class, args);
	}

	@Service
	public class NaverPlaceService {

		private final String CLIENT_ID = "YOUR_CLIENT_ID";
		private final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
		private final String NAVER_API_URL = "https://openapi.naver.com/v1/search/local.json";

		public String searchPlaces(String query) {
			RestTemplate restTemplate = new RestTemplate();

			// API 호출 시 필요한 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Naver-Client-Id", CLIENT_ID);
			headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

			String apiUrl = NAVER_API_URL + "?query=" + query + "&display=5";

			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

			return response.getBody(); // 검색 결과를 JSON 형식으로 반환
		}
	}
}
