# blackjack
This is an exercise to further understand OOP.

To run the game, use BlackjackClient.java. In this Blackjack game, the computer randomly deals two cards to the player and two cards to the "Dealer".
If the player gets Blackjack, the game immediately rewards the player 1.5 points, and they are prompted to select whether or not to play another round.
The game keeps track of the points accumulated in each playing session.

Otherwise, after being dealt the first two cards, the player is prompted to "Hit" or "Stand" by typing the 'h' or 's' keys on their keyboard.
Once the player is chooses to "Stand" or goes "Bust", the "Dealer" cards are revealed.

For the "Dealer", the embedded rule is that the computer will keep selecting "Hit" as long as their hand's total is less than 17.
Whoever wins (closest to 21 without going over) gets an additional 1 point. If they tie (same value or both bust), neither get additional points. 
If the "Dealer" gets Blackjack, they get 1 additional point (+1.5 points for Blackjack is just for the player!)
