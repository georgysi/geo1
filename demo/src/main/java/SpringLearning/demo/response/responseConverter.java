package SpringLearning.demo.response;
import SpringLearning.demo.response.stockDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class responseConverter {

    public  stockDataResponse convertToResponse(String ahigh, String alow, String aavg, String asell){

         return stockDataResponse.builder().high(ahigh).low(alow).avg(aavg).sell(asell).build();

    }
}
