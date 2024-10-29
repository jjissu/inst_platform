package inst_platform.controller;

import inst_platform.inst_platform.InstPlatformApplication;
import inst_platform.model.Store;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class StroreController {
    @Value("${naver.map.api.key}")
    private String naverMapApiKey;

    @GetMapping("/stores")
    public String getStores(Model model) {
        // 서울 내 악기점과 공방 리스트 (예시 데이터)
        List<Store> stores = Arrays.asList(
//                new Store(1L, "서울악기점", "서울시 강남구 ...", 37.497942, 127.027621),
//                new Store(2L, "홍대공방", "서울시 마포구 ...", 37.557545, 126.924542)
//                // 추가적인 데이터 작성 필요
        );

        model.addAttribute("stores", stores);
        model.addAttribute("naverMapApiKey", naverMapApiKey);
        return "stores";
    }

    @RestController
    public class NaverPlaceController {

        @Autowired
        private InstPlatformApplication.NaverPlaceService naverPlaceService;

        @GetMapping("/api/search")
        public String search(@RequestParam String query) {
            return naverPlaceService.searchPlaces(query);
        }
    }
}
