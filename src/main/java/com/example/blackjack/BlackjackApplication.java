package com.example.blackjack;

import com.example.blackjack.exception.DeckParseException;
import com.example.blackjack.model.Deck;
import com.example.blackjack.model.Player;
import com.example.blackjack.util.DeckUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BlackjackApplication implements CommandLineRunner {

	private static final int BLACK_JACK = 21;
	private static final int PLAYER_DRAW_LIMIT = 17;
	private static final int MINIMUM_BUST_VALUE = 22;
	Player sam;
	Player dealer;
	Deck deck;

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String deckFile = deckFileExists(args)? args[0] : null;
		initializeGame(deckFile);
		evaluateWinner();
	}

	public boolean deckFileExists(String[] deckFile){
		return deckFile != null && deckFile.length > 0;
	}

	public void initializeGame(String deckFile) throws IOException, DeckParseException {
		this.dealer = new Player("Dealer");
		this.sam = new Player("Sam");
		this.deck = createDeck(deckFile);
		this.deck.shuffle();
		this.sam.drawCard(deck.drawCard());
		this.sam.drawCard(deck.drawCard());
		this.dealer.drawCard(deck.drawCard());
		this.dealer.drawCard(deck.drawCard());
	}

	public Deck createDeck(String deckFile) throws IOException, DeckParseException {
		if (deckFile == null || deckFile.isEmpty()){
			return DeckUtil.generateDeck();
		}
		return DeckUtil.readDeckFromFile(deckFile);
	}

	public void evaluateWinner(){
		if (bothHaveBlackJack()){
			printWinner(sam, dealer);
			return;
		}
		if (bothBust()){
			printWinner(dealer, sam);
			return;
		}
		if (hasBlackJack(sam)) {
			printWinner(sam, dealer);
			return;
		}
		if (hasBlackJack(dealer)){
			printWinner(dealer, sam);
			return;
		}
		startDrawingCards(sam, PLAYER_DRAW_LIMIT);
		if (playerLost(sam)){
			printWinner(dealer, sam);
			return;
		}
		startDrawingCards(dealer, calcDealerDrawLimit());
		if (playerLost(dealer)){
			printWinner(sam, dealer);
			return;
		}
		if (isWinnerByMajorityPoints(sam, dealer)){
			printWinner(sam, dealer);
			return;
		}
		if (isWinnerByMajorityPoints(dealer, sam)) {
			printWinner(dealer, sam);
			return;
		}
		if (tieLessThanBlackJack()){
			printWinner(sam, dealer);
		}
	}

	public boolean bothHaveBlackJack(){
		return hasBlackJack(sam) && hasBlackJack(dealer);
	}

	public boolean bothBust(){
		return sam.getScore() == MINIMUM_BUST_VALUE && dealer.getScore() == MINIMUM_BUST_VALUE;
	}

	public boolean hasBlackJack(Player player){
		return player.getScore() == BLACK_JACK;
	}

	public void startDrawingCards(Player player, int drawLimit){
		while (player.getScore() < drawLimit){
			player.drawCard(this.deck.drawCard());
		}
	}

	public int calcDealerDrawLimit(){
		return sam.getScore() + 1;
	}

	public boolean playerLost(Player player){
		return player.getScore() > BLACK_JACK;
	}

	public boolean isWinnerByMajorityPoints(Player winner, Player loser){
		return winner.getScore() > loser.getScore();
	}

	public void printWinner(Player winner, Player loser){
		System.out.println("The winner is " + winner.toString());
		System.out.println("The loser is " + loser.toString());
	}

	public boolean tieLessThanBlackJack(){
		return sam.getScore() == dealer.getScore() && sam.getScore() < BLACK_JACK;
	}
}