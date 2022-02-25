package com.example.coindesk;

import com.example.coindesk.controller.BitcoinEntityController;
import com.example.coindesk.model.BitcoinEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoindeskApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("測試1")
    public void showBYid() {
        String port ="8080";
        assertThat(this.restTemplate.getForObject("http://127.0.0.1:" + port + "/api/v1/bitcoin/47",
                String.class)).contains("British Pound Sterling");
    }

    @Test
    @DisplayName("測試2")
    public void showAll() {
        String port ="8080";
        assertThat(this.restTemplate.getForObject("http://127.0.0.1:" + port + "/api/v1/bitcoin",
                String.class)).contains("United");

        assertThat(this.restTemplate.getForObject("http://127.0.0.1:" + port + "/api/v1/bitcoin",
                String.class)).contains("British");

        assertThat(this.restTemplate.getForObject("http://127.0.0.1:" + port + "/api/v1/bitcoin",
                String.class)).contains("Euro");
    }

    @Test
    @DisplayName("測試3")
    public void delete() {
        String url = "http://127.0.0.1:8080/api/v1/delete/67";
        restTemplate.delete(url);
    }

    @Test
    @DisplayName("測試4")
    public void modify() {
        String url = "http://127.0.0.1:8080/api/v1/modify/67";
        BitcoinEntity postDTO = new BitcoinEntity();
        postDTO.set浮動利率("122");
        postDTO.set幣別名稱("EFB");
        postDTO.set幣別("234");
        postDTO.set更新時間("ssss");
        postDTO.set匯率("2344");
        restTemplate.put(url, postDTO);
    }

}
