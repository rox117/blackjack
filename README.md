# blackjack
## Description
a console blackjack implementation using spring and maven.
The aim of the game is for the player to achieve the highest possible hand up to 21 in order to beat the dealer of the cards.
Each numeric card values the number assigned to them.
Aces are valued 11 points and all remaining cards are valued 10 points.
The player or dealer loses when their cards add up to a value greater than 21.
When both the dealer and the player draw  Aces for the the first hand consisting of two cards each, it's a bust and the dealer wins.
After the intial draw, the player and the dealer begin to draw until they reach their draw limit.
The player's draw limit is 17 or higher.
The dealer's draw limit is any score higher than the player's.
If both the dealer and the player have received 21 points in the initial draw then the dealer loses.
If both the dealer and the player have equal scores in the initial draw and the value is less than 21 the dealer loses.
Cards can be read from a file in the format CA, D4, H7, SJ,..., S5, S9, D10
## Installation
The jdk required is java 20.
Clone the project and import it into your Preferred IDE using the pom file.
Start the application by running the main method of Blackjack application class.
Unit tests are in their corresponing packages inside the test folder.
