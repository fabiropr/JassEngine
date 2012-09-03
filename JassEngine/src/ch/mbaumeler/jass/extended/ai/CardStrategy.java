package ch.mbaumeler.jass.extended.ai;

import java.util.List;

import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.PlayedCard;

public interface CardStrategy {

	Card getPlayableCard(List<Card> cardsInHand, Match match);
	
	boolean isResponsible(List<PlayedCard> cardsOnTable); 
}
