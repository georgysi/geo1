package SpringLearning.demo.rest;
import com.google.gson.Gson;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import SpringLearning.demo.response.stockDataResponse;
import SpringLearning.demo.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "stc/quot")
public class APIController {

    @Autowired
    private StockService stockService;
    @GetMapping(path = "/{stockId}")
    public ResponseEntity<String> getStockPrice(@PathVariable("stockId") String stockId) throws Exception {

           // return ResponseEntity.status(HttpStatus.OK)
             //       .body(new Gson().toJson(this.stockService.getPrice(stockId)));
           return ResponseEntity.status(HttpStatus.OK)
                .body(this.stockService.getPrice(stockId));
        }



}
