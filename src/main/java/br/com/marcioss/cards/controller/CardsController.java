package br.com.marcioss.cards.controller;

import br.com.marcioss.cards.config.CardsServiceConfig;
import br.com.marcioss.cards.model.Cards;
import br.com.marcioss.cards.model.Customer;
import br.com.marcioss.cards.model.Properties;
import br.com.marcioss.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CardsController {

    private Logger logger = LoggerFactory.getLogger(CardsController.class);
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @PostMapping("/myCards")
    @Timed(value = "getCardDetails.time",description = "Time taken to return cards Details")
    public List<Cards> getCardDetails(@RequestHeader("marciossbank-correlation-id") String correlationId,@RequestBody Customer customer){
        logger.info("getCardDetails() method started");
         List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        logger.info("getCardDetails() method started");
        return cards;
    }

    @GetMapping("/cards/properties")
    public String getCardsProperties() throws JsonProcessingException {
        ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(),cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),cardsServiceConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}
