package ch.mbaumeler.jass.extended.ui;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ch.mbaumeler.jass.core.Game;
import ch.mbaumeler.jass.core.Match;
import ch.mbaumeler.jass.core.game.PlayerToken;
import ch.mbaumeler.jass.extended.ui.ObserverableMatch.Event;

public class ObservableGameTest {

	private ObservableGame observerableGame;
	private Game gameMock = mock(Game.class);
	private JassModelObserver observerMock = mock(JassModelObserver.class);
	private Match matchMock = mock(Match.class);

	@Before
	public void setUp() throws Exception {
		observerableGame = new ObservableGame(gameMock);
		observerableGame.addObserver(observerMock);
		when(gameMock.getCurrentMatch()).thenReturn(matchMock);
	}

	@Test
	public void testGetPlayer() {
		observerableGame.getPlayerRepository();
		verify(gameMock).getPlayerRepository();
		verify(observerMock, times(0)).updated(any(Event.class), any(PlayerToken.class), anyObject());
	}

	@Test
	public void testGetTotalScore() {
		observerableGame.getTotalScore();
		verify(gameMock).getTotalScore();
		verify(observerMock, times(0)).updated(any(Event.class), any(PlayerToken.class), anyObject());
	}

	@Test
	public void testCurrentMatch() {
		Match currentMatch = observerableGame.getCurrentMatch();
		assertNotSame(currentMatch, matchMock);
		verify(gameMock).getCurrentMatch();
		verify(observerMock, times(0)).updated(any(Event.class), any(PlayerToken.class), anyObject());
	}
}
