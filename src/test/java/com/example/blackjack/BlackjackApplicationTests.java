package com.example.blackjack;

import com.example.blackjack.enums.CardValue;
import com.example.blackjack.enums.Suit;
import com.example.blackjack.exception.DeckParseException;
import com.example.blackjack.model.Card;
import com.example.blackjack.model.Deck;
import com.example.blackjack.model.Player;
import com.example.blackjack.util.DeckUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackApplicationTests {

	private BlackjackApplication game;
	private Deck deck;
	private Player sam;
	private Player dealer;
	private static final String VALID_DECK_FILE_PATH = "src/main/resources/cards.csv";
	private static final String DUMMY_DECK_FILE = "file.csv";
	private static final int DRAW_LIMIT = 15;
	private static final Card ACE = new Card(Suit.HEART, CardValue.ACE);
	private static final Card KING = new Card(Suit.HEART, CardValue.KING);


	@BeforeEach
	void setUp() {
		sam = new Player("Sam");
		dealer = new Player("Dealer");
		deck = DeckUtil.generateDeck();
		game = new BlackjackApplication(sam, dealer, deck);
	}

	@Test
	void givenDeckFileName_whenInvalid_thenReturnFalse() {
		assertFalse(game.deckFileExists(null));
		assertFalse(game.deckFileExists(new String[]{}));
	}

	@Test
	void givenDeckFileName_whenValid_thenReturnTrue() {
		assertTrue(game.deckFileExists(new String[]{DUMMY_DECK_FILE}));
	}

	@Test
	void givenValidDeckFile_whenCreateDeck_thenReturnValidDeck() throws IOException, DeckParseException {
		Deck deck = game.createDeck(VALID_DECK_FILE_PATH);
		int actual = deck.getSize();

		assertNotNull(deck);
		assertEquals(52, actual);
	}

	@Test
	void givenInvalidDeckFile_whenCreateDeck_thenReturnValidDeck() throws IOException, DeckParseException {
		Deck deck = game.createDeck(null);

		assertNotNull(deck);
		assertEquals(52, deck.getSize());
	}


	@Test
	void givenPlayerAndDealer_whenBothHaveBlackjack_thenReturnTrue() {
		sam.drawCard(ACE);
		sam.drawCard(KING);
		dealer.drawCard(ACE);
		dealer.drawCard(KING);

		assertTrue(game.bothHaveBlackJack(sam, dealer));
	}

	@Test
	void givenPlayerAndDealer_whenBothScore22_thenBust() {
		sam.drawCard(ACE);
		sam.drawCard(ACE);
		dealer.drawCard(ACE);
		dealer.drawCard(ACE);

		assertTrue(game.bothBust(sam, dealer));
	}

	@Test
	void givenPlayer_whenBlackjack_thenReturnTrue() {
		sam.drawCard(ACE);
		sam.drawCard(KING);

		assertTrue(game.hasBlackJack(sam));
	}

	@Test
	void givenPlayerAndLimit_whenTurnEnds_thenScoreGreaterOrEqualLimit() {
		game.startDrawingCards(sam, DRAW_LIMIT);

		assertTrue(sam.getScore() >= DRAW_LIMIT);
	}

	@Test
	void givenPlayer_whenCardDrawn_thenDealerLimitEqualPlayerScorePlus1() {
		int expectedLimit = ACE.value().value + 1;

		sam.drawCard(ACE);
		int actualLimit = game.calcDealerDrawLimit();

		assertEquals(expectedLimit, actualLimit);
	}

	@Test
	void givenPlayer_whenScoreMoreThan21_thenPlayerLost() {
		sam.drawCard(ACE);
		sam.drawCard(ACE);

		assertTrue(game.playerLost(sam));
	}

	@Test
	void givenPlayerAndDealer_whenPlayerScoreGreaterThanDealerScoreAndLessThan21_thenPlayerWins() {
		sam.drawCard(KING);
		sam.drawCard(KING);
		dealer.drawCard(ACE);

		assertTrue(game.isWinnerByMajorityPoints(sam, dealer));
	}

	@Test
	void givenPlayerAndDealer_whenScoresEqualAndLessThan21_thenTie() {
		sam.drawCard(KING);
		dealer.drawCard(KING);

		assertTrue(game.tieLessThanBlackJack(sam, dealer));
	}

	@Test
	void givenGame_whenInitialized_then2PlayersCreated() throws IOException, DeckParseException {
		game = new BlackjackApplication();

		game.initializeGame(null);

		assertNotNull(game.getSam());
		assertNotNull(game.getDealer());
	}

	@Test
	void givenGame_whenInitialized_thenDraw4Cards() throws IOException, DeckParseException {
		game = new BlackjackApplication();
		int expectedSize = deck.getSize() - 4;

		game.initializeGame(null);
		int actualSize = game.getDeck().getSize();

		assertEquals(expectedSize, actualSize);
	}
}