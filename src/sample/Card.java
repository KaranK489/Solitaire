//this is the Card class, which stores all the information about each card on the board, like suit, color, etc. this way, we can match the cards and make sure everything is going where it is supposed to.
package sample;

import javafx.scene.image.Image;
import java.awt.*;

public class Card {
    //stores image
    private final Image image;
    //stores color (red, black)
    private Color color = null;
    //stores suit (clubs(c), diamonds (d), hearts (h), and spades (s))
    private char suit = 'a';
    //stores value (number/letter) of card (1(ace), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11(jack), 12(queen), 13(king))
    private int value = 0;
    //stores boolean of whether the card is showing its back or if its full values are visible
    private boolean back = false;

    //constructor 1
    public Card(Image a, Color b, char c, int d){
        image = a;
        color = b;
        suit = c;
        value = d;
    }

    //constructor 2
    public Card(Image a){
        image = a;
    }

    //various accessors used to return information
    public Image getImage(){
        return image;
    }

    public Color getColor(){
        return color;
    }

    public char getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    public boolean getBack(){
        return back;
    }

    public void setBack(boolean a){
        back = a;
    }
}