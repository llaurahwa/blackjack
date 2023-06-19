import static java.lang.System.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {
    /* instance variables here */

    // deck of cards used for this game
    private Deck deck;

    // arrays to hold the dealer's and player's hands
    private Card[] dealerHand;
    private Card[] playerHand;

    // keep track of the current total value for player and dealer
    private int dealerVal;
    private int playerVal;

    // keep track of win points for player and dealer
    private double dealerPts;
    private double playerPts;

    // number of rounds that have been played
    private int rounds;

    // scanner used for keyboard input
    Scanner kb = new Scanner(System.in);

    // constructor
    public Blackjack() {
    }

    //accessor method for deck needed in runner
    public Deck getDeck() {
        return deck;
    }

    // "main" method in the class, used to play one complete round of Blackjack
    public void playRound() {
        //create a new deck to play with
        Deck a = new Deck();
        //make deck and a refer to the same Deck object
        deck = a;
        //size the dealerHand array to 8
        dealerHand = new Card[8];
        int dealerPos;
        //deal first card for dealer
        dealerHand[0] = a.dealCard();
        dealerPos = 0;
        //update value of dealer's hand
        dealerVal += dealerHand[0].getValue();
        //append new card to array and print dealer's hand
        String dCurrHand = "";
        dCurrHand += dealerHand[0].toString();
        System.out.println("\nDealer: " + dCurrHand);

        //size the playerHand array to 8
        playerHand = new Card[8];
        int playerPos;
        //deal two cards to the player
        playerHand[0] = a.dealCard();
        playerPos = 0;
        playerHand[1] = a.dealCard();
        playerPos = 1;
        //update value of player's hand
        playerVal += playerHand[0].getValue();
        playerVal += playerHand[1].getValue();
        //print player's hand
        String pCurrHand = "";
        pCurrHand = playerHand[0] + ", " + playerHand[1];
        System.out.println("Player: [" + pCurrHand + "] : " + playerVal);

        //check if player has blackjack
        if (playerVal == 21) {
            //print player gets blackjack message
            System.out.println("!!! Player gets a blackjack and 1.5 points, nice !!!!");
            //add player points
            playerPts += 1.5;
            System.out.println("Score is: P = " + playerPts + ", D = " + dealerPts);
        }

        //all scenarios where player does not have blackjack
        else {
            String answer = "";
            pCurrHand = playerHand[0] + ", " + playerHand[1];
            do {
                //ask player to hit or stand
                System.out.print("\nPlayer! What would you like to do? (H)it or (S)tand? ");
                answer = kb.next();

                //if player hits, deal new card and add to hand
                if (answer.toLowerCase().equals("h")) {
                    int addPos = 1;
                    playerHand[playerPos + addPos] = a.dealCard();
                    System.out.print("\n");
                    //print new card dealt
                    System.out.println("*** " + playerHand[playerPos + addPos] + " ***\n");
                    //get player hand value
                    playerVal = getHandValue(playerHand);
                    //update hand and print player's array
                    pCurrHand += ", " + playerHand[playerPos + addPos];
                    System.out.println("Player: [" + pCurrHand + "] : " + playerVal);
                    //update position for new calculations
                    playerPos++;
                    //if new hit led to player bust, print player busted message
                    if (playerVal > 21) {
                        System.out.println("### Player busted! Dealer wins! ###\n");
                        dealerPts += 1.0;
                        System.out.println("Score is: P = " + playerPts + ", D = " + dealerPts);
                        break;
                    }

                }
                //ensuring h or s has been inputted
                else if (answer.toLowerCase().equals("s") == false) {
                    System.out.println("Sorry, I didn't recognize that, try typing (H) or (S)");
                }

            }

            //check if player has stayed yet, if player hasn't stayed, continue loop
            while (answer.toLowerCase().equals("s") == false);
            //conditions if player hasn't busted yet
            if (playerVal <= 21) {
                int addPos = 1;
                //dealer continues to hit if dealerVal is less than 17
                while (dealerVal < 17) {
                    dealerHand[dealerPos + addPos] = a.dealCard();
                    dealerVal = getHandValue(dealerHand);
                    dCurrHand += ", " + dealerHand[dealerPos + addPos].toString();
                    addPos++;
                }
                //print dealer hand array
                System.out.println("\nDealer: [" + dCurrHand + "] : (" + dealerVal + ")");

                //check if dealer got a blackjack
                if (addPos == 2 && dealerVal == 21) {
                    System.out.println(":( Dealer gets a blackjack, tough break! :(");
                    dealerPts += 1.0;
                    System.out.println("Score is: P = " + playerPts + ", D = " + dealerPts);
                }
                //check if dealer busted
                else if (dealerVal > 21) {
                    System.out.println("### Dealer busted! Player wins! ###");
                    playerPts += 1.0;
                    System.out.println("Score is: P = " + playerPts + ", D = " + dealerPts);
                }
                //check helper method for when neither player or dealer busted or got blackjack
                else {
                    checkWinner();
                    System.out.println("Score is: P = " + playerPts + ", D = " + dealerPts);
                }
            }
        }
        reset();
    }

    //reset player and dealer values after each round
    public void reset() {
        dealerVal = 0;
        playerVal = 0;
    }

    // look at the current hands and determine a winner when neither player got blackjack or busted
    public void checkWinner() {
        if (playerVal > dealerVal) {
            System.out.println("$$$ Player wins! $$$");
            playerPts += 1.0;
        } else if (dealerVal > playerVal) {
            System.out.println("### Dealer wins! ###\n");
            dealerPts += 1.0;
        } else {
            System.out.println("^^^ Draw! ^^^");
        }

    }

    // calculate the value of a hand (handle aces correctly!)
    public int getHandValue(Card[] cards) {
        int val = 0;
        //loop through the size of dealer and player's hand array
        for (int i = 0; i < 8; i++) {
            //handle null case
            if (cards[i] == null) {
                val += 0;
            } else {
                //handle aces
                if (val > 10 && cards[i].getValue() == 11) {
                    val += 1;
                }
                //all other cases
                else {
                    val += cards[i].getValue();
                }
            }
        }
        return val;
    }

}
