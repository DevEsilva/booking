package com.bideafactory.codingtest.Service;

import com.bideafactory.codingtest.Dto.DiscountDto;
import com.bideafactory.codingtest.Dto.DiscountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiDiscountService {

    private static final Logger logger = LoggerFactory.getLogger(ApiDiscountService.class);

    private final String discountApiUrl = "https://sbv2bumkomidlxwffpgbh4k6jm0ydskh.lambda-url.us-east-1.on.aws/";

    /**
     *
     *
     * @param code--> Codigo de descuento para su validación
     * @return ouseId discountCode, id, userId, status
     */
    public Boolean validateDiscount(String houseid,String id,String code) throws Exception {
        DiscountResponse discountResponse = null;

        DiscountDto restTemplate = new DiscountDto(id,houseid,code);
        int retries = 0;
        while (retries <= 3) {
            try {
                HttpClient    httpClient = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(5))
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(discountApiUrl))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(restTemplate)))
                        .build();

                ObjectMapper objectMapper = new ObjectMapper();

                HttpResponse<String>  response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                discountResponse = objectMapper.readValue(response.body(), DiscountResponse.class);
                retries=4;
            }catch (HttpTimeoutException ex) {
                retries++;
                if (retries >= 3) {
                    logger.error("Se alcanzo el limite de tiempo para una respuesta: " + ex.getMessage());
                    throw new Exception("Se alcanzo el limite de tiempo para una respuesta"+ ex.getMessage());
                }

            }  catch (Exception e) {
                logger.error("Se obtuvo un error al validar Codigo de descuento: " + e.getMessage());
                throw new Exception("Se obtuvo un error al validar Codigo de descuento"+e.getMessage() );
            }
        }
        return discountResponse.isStatus();
    }
}
