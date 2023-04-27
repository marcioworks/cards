package br.com.marcioss.cards.controller;

import br.com.marcioss.cards.model.Cards;
import br.com.marcioss.cards.model.Customer;
import br.com.marcioss.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {
    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer){
        return cardsRepository.findByCustomerId(customer.getCustomerId());
    }
}
