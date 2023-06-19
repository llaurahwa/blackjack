import static java.lang.System.*;

import java.util.Scanner;

public class BlackjackClient {
    public static void main(String[] args) {
        // create a new scanner object
        Scanner kb = new Scanner(System.in);

        // create a new instance of the game class
        Blackjack game = new Blackjack();

        //welcome message
        System.out.println("Welcome to Blackjack! (this is only for fun, no betting!!)");

        // initial conditions for loop to operate
        boolean playGame = true;
        boolean response = true;
        //counting rounds before needing to shuffle
        int gameCount = 0;
        // loop to keep playing until player wants to stop
        while (playGame) {
            //start by playing one round
            game.playRound();
            //check if player wants to continue playing
            while (response) {
                System.out.print("\nWow, that was fun! Do you want to play again? (y/n) ");
                String keepPlay = kb.next();
                if (keepPlay.toLowerCase().equals("y")) {
                    playGame = true;
                    gameCount++;
                    //check if shuffling needs to happen
                    if (gameCount == 5) {
                        System.out.println("@@@@@ Wait a sec, shuffling deck @@@@@");
                        game.getDeck().shuffle();
                        gameCount = 0;
                    }
                    //stop asking whether player wants to play if player gives valid response
                    break;
                }
                //end game for player
                else if (keepPlay.toLowerCase().equals("n")) {
                    playGame = false;
                    System.out.println("That was fun, come back if you want more practice!");
                    break;
                }
                //handling invalid response
                else {
                    System.out.println("Sorry I didn't recognize that, try typing (y) or (n)");
                }
            }
        }
    }
}
