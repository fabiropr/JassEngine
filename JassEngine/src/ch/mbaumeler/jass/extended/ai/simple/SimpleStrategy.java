package ch.mbaumeler.jass.extended.ai.simple;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.card.Card;
import ch.mbaumeler.jass.core.game.Ansage;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ai.AnsageStrategy;
import ch.mbaumeler.jass.extended.ai.PlayStrategy;

public class SimpleStrategy implements PlayStrategy, AnsageStrategy {

	@Inject
	private SelectTrumpfStrategy selectTrumpfStrategy;

	@Inject
	@Named(value = "strategies")
	private List<SimpleCardStrategy> strategies;

	@Override
	public Card getCardToPlay(Match match) {
		PlayerToken activePlayer = match.getActivePlayer();
		List<Card> cardsInHand = match.getCards(activePlayer);

		for (SimpleCardStrategy strategy : strategies) {
			if (strategy.isResponsible(match.getCardsOnTable())) {
				return strategy.getPlayableCard(
						new ArrayList<Card>(cardsInHand), match);
			}
		}
		throw new IllegalStateException("No strategy to play");
	}

	@Override
	public Ansage getAnsage(Match match) {
		return selectTrumpfStrategy.getAnsage(match);
	}
}
