package br.com.marcioss.cards.repository;

import br.com.marcioss.cards.model.Cards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardsRepository extends CrudRepository<Cards,Integer> {
    List<Cards> findByCustomerId(int customerId);
}
