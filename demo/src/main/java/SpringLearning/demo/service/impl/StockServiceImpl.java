package SpringLearning.demo.service.impl;

import SpringLearning.demo.response.stockDataResponse;
import SpringLearning.demo.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import SpringLearning.demo.response.responseConverter;


@Service
@Slf4j
public class StockServiceImpl implements StockService{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private responseConverter rConverter;
   // @Autowired
    private stockDataResponse sdResponse;

    @Value("${quot.url}")
    private String apiUrl;


    @Override
    public String getPrice(String stockId) throws Exception  {
        System.out.print("GEORGY IMPL ALERT******************************" + stockId );

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.print("GEORGY U   R   L  " + apiUrl );

        try {
        String responce = restTemplate.getForObject(apiUrl,String.class);


        System.out.print("GEORGY RESPONCEEEEEEEEEEEEEEEEEE   " + responce );
        String high = objectMapper.readTree(responce).get("eth_usd").get("high").asText();
        String low = objectMapper.readTree(responce).get("eth_usd").get("low").asText();
        String avg = objectMapper.readTree(responce).get("eth_usd").get("avg").asText();
        String sell = objectMapper.readTree(responce).get("eth_usd").get("sell").asText();
            //responseConverter rConverter = new responseConverter();
            sdResponse = rConverter.convertToResponse(high,low,avg,sell);
            return sdResponse.getHigh() + sdResponse.getLow() + sdResponse.getAvg() + sdResponse.getSell();
        } catch (Exception e) {
            final String errorMessage = "Web Service Call Attempt Failed : " + e.getLocalizedMessage();
            log.error(errorMessage);
            System.out.print("E  r R  O R  " + errorMessage );

        }

        return "SUCCESS";
    }
}