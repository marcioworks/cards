package br.com.marcioss.cards.controller;

import br.com.marcioss.cards.config.CardsServiceConfig;
import br.com.marcioss.cards.model.Cards;
import br.com.marcioss.cards.model.Customer;
import br.com.marcioss.cards.model.Properties;
import br.com.marcioss.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer){
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/cards/properties")
    public String getCardsProperties() throws JsonProcessingException {
        ObjectWriter objectWriter =  new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(),cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),cardsServiceConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);
    }
}
