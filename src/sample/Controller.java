//this is the Controller class, which obviously is the main class running the game.
package sample;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

//Source used for drag event handler: https://www.youtube.com/watch?v=ME6WfnR6zys (only used basic mouse event handler format, had to figure out stuff for coords and matching which column and row to go to, etc.)

public class Controller {

    //various array lists for all the cards (unchanged), cards in the stock pile, and cards in the waste pile
    ArrayList<Card> stockPileCards = new ArrayList<>();
    ArrayList<Card> wastePileCards = new ArrayList<>();

    //2D array of Card objects, to store what is in what position
    Card[][] cardGrid = new Card[13][7];

    //2D array of ImageViews objects, which stores the imageviews on the main board
    ImageViews[][] imgViewGrid = new ImageViews[13][7];

    //arraylist used to store all the image views being dragged
    ArrayList<ImageViews> allImgViewsInDrag = new ArrayList<>();

    //doubles used for drag function
    double clickX;
    double clickY;

    //imageviews declaration for the top waste pile
    ImageViews imgViewsWastePileTop;

    //imageviews array of the foundation pile
    ImageViews[] imgViewsFoundationPiles = new ImageViews[8];

    //Card array of arraylists, storing the cards in each foundation pile in each arraylist corresponding to the row of the array (this isn't a 2D array since I need to constantly be removing and adding cards to the array list, seeing the size, etc. which is why it is an array of arraylists)
    ArrayList<Card>[] foundationPileCards = new ArrayList[4];

    //score integers
    int score = 0;
    int scoreChange = 0;

    //mode selected integer (easy(1), hard(2))
    int mode = 0;

    //reveal click boolean
    boolean revealClick = false;

    //fxml buttons
    @FXML
    Button btnRestart, btnStart, btnEasy, btnHard, btnReveal, btnWildCard;

    //fxml labels
    @FXML
    Label lblScore, lblScoreChange, lblDifficulty, lblDiffSelected;

    //fxml text areas
    @FXML
    TextArea txtInstructions, txtWin;

    //fxml imageviews of start screen pictures
    @FXML
    ImageView imgViewStartCards1, imgViewStartCards2, imgViewStartCards3, imgViewStartCards4;

    //fxml imageviews of different piles
    @FXML
    ImageView imgViewStockPile, imgViewWastePileTop, imgViewWastePileBottom, imgViewFoundationHeartsTop, imgViewFoundationDiamondsTop, imgViewFoundationClubsTop, imgViewFoundationSpadesTop, imgViewFoundationHeartsBottom, imgViewFoundationDiamondsBottom, imgViewFoundationClubsBottom, imgViewFoundationSpadesBottom;

    //fxml imageviews of board spots in various rows and columns
    @FXML
    ImageView imgViewR0C0, imgViewR0C1, imgViewR0C2, imgViewR0C3, imgViewR0C4, imgViewR0C5, imgViewR0C6;

    @FXML
    ImageView imgViewR1C0, imgViewR1C1, imgViewR1C2, imgViewR1C3, imgViewR1C4, imgViewR1C5, imgViewR1C6;

    @FXML
    ImageView imgViewR2C0, imgViewR2C1, imgViewR2C2, imgViewR2C3, imgViewR2C4, imgViewR2C5, imgViewR2C6;

    @FXML
    ImageView imgViewR3C0, imgViewR3C1, imgViewR3C2, imgViewR3C3, imgViewR3C4, imgViewR3C5, imgViewR3C6;

    @FXML
    ImageView imgViewR4C0, imgViewR4C1, imgViewR4C2, imgViewR4C3, imgViewR4C4, imgViewR4C5, imgViewR4C6;

    @FXML
    ImageView imgViewR5C0, imgViewR5C1, imgViewR5C2, imgViewR5C3, imgViewR5C4, imgViewR5C5, imgViewR5C6;

    @FXML
    ImageView imgViewR6C0, imgViewR6C1, imgViewR6C2, imgViewR6C3, imgViewR6C4, imgViewR6C5, imgViewR6C6;

    @FXML
    ImageView imgViewR7C0, imgViewR7C1, imgViewR7C2, imgViewR7C3, imgViewR7C4, imgViewR7C5, imgViewR7C6;

    @FXML
    ImageView imgViewR8C0, imgViewR8C1, imgViewR8C2, imgViewR8C3, imgViewR8C4, imgViewR8C5, imgViewR8C6;

    @FXML
    ImageView imgViewR9C0, imgViewR9C1, imgViewR9C2, imgViewR9C3, imgViewR9C4, imgViewR9C5, imgViewR9C6;

    @FXML
    ImageView imgViewR10C0, imgViewR10C1, imgViewR10C2, imgViewR10C3, imgViewR10C4, imgViewR10C5, imgViewR10C6;

    @FXML
    ImageView imgViewR11C0, imgViewR11C1, imgViewR11C2, imgViewR11C3, imgViewR11C4, imgViewR11C5, imgViewR11C6;

    @FXML
    ImageView imgViewR12C0, imgViewR12C1, imgViewR12C2, imgViewR12C3, imgViewR12C4, imgViewR12C5, imgViewR12C6;

    //all images for all the cards of the clubs suit
    Image CA = new Image("resources/clubs/CA.png");
    Image C2 = new Image("resources/clubs/C2.png");
    Image C3 = new Image("resources/clubs/C3.png");
    Image C4 = new Image("resources/clubs/C4.png");
    Image C5 = new Image("resources/clubs/C5.png");
    Image C6 = new Image("resources/clubs/C6.png");
    Image C7 = new Image("resources/clubs/C7.png");
    Image C8 = new Image("resources/clubs/C8.png");
    Image C9 = new Image("resources/clubs/C9.png");
    Image C10 = new Image("resources/clubs/C10.png");
    Image CJ = new Image("resources/clubs/CJ.png");
    Image CQ = new Image("resources/clubs/CQ.png");
    Image CK = new Image("resources/clubs/CK.png");

    //all images for all the cards of the diamonds suit
    Image DA = new Image("resources/diamonds/DA.png");
    Image D2 = new Image("resources/diamonds/D2.png");
    Image D3 = new Image("resources/diamonds/D3.png");
    Image D4 = new Image("resources/diamonds/D4.png");
    Image D5 = new Image("resources/diamonds/D5.png");
    Image D6 = new Image("resources/diamonds/D6.png");
    Image D7 = new Image("resources/diamonds/D7.png");
    Image D8 = new Image("resources/diamonds/D8.png");
    Image D9 = new Image("resources/diamonds/D9.png");
    Image D10 = new Image("resources/diamonds/D10.png");
    Image DJ = new Image("resources/diamonds/DJ.png");
    Image DQ = new Image("resources/diamonds/DQ.png");
    Image DK = new Image("resources/diamonds/DK.png");

    //all images for all the cards of the hearts suit
    Image HA = new Image("resources/hearts/HA.png");
    Image H2 = new Image("resources/hearts/H2.png");
    Image H3 = new Image("resources/hearts/H3.png");
    Image H4 = new Image("resources/hearts/H4.png");
    Image H5 = new Image("resources/hearts/H5.png");
    Image H6 = new Image("resources/hearts/H6.png");
    Image H7 = new Image("resources/hearts/H7.png");
    Image H8 = new Image("resources/hearts/H8.png");
    Image H9 = new Image("resources/hearts/H9.png");
    Image H10 = new Image("resources/hearts/H10.png");
    Image HJ = new Image("resources/hearts/HJ.png");
    Image HQ = new Image("resources/hearts/HQ.png");
    Image HK = new Image("resources/hearts/HK.png");

    //all images for all the cards of the spades suit
    Image SA = new Image("resources/spades/SA.png");
    Image S2 = new Image("resources/spades/S2.png");
    Image S3 = new Image("resources/spades/S3.png");
    Image S4 = new Image("resources/spades/S4.png");
    Image S5 = new Image("resources/spades/S5.png");
    Image S6 = new Image("resources/spades/S6.png");
    Image S7 = new Image("resources/spades/S7.png");
    Image S8 = new Image("resources/spades/S8.png");
    Image S9 = new Image("resources/spades/S9.png");
    Image S10 = new Image("resources/spades/S10.png");
    Image SJ = new Image("resources/spades/SJ.png");
    Image SQ = new Image("resources/spades/SQ.png");
    Image SK = new Image("resources/spades/SK.png");

    //all images for all the cards of the foundation piles
    Image foundationHearts = new Image("resources/extras/foundation-hearts.png");
    Image foundationDiamonds = new Image("resources/extras/foundation-diamonds.png");
    Image foundationClubs = new Image("resources/extras/foundation-clubs.png");
    Image foundationSpades = new Image("resources/extras/foundation-spades.png");

    //image for the back of the card
    Image back = new Image("resources/extras/red_back.png");

    //image for the reset card
    Image resetCard = new Image("resources/extras/reset.png");

    //image for the wild card
    Image wildCardImg = new Image("resources/extras/wild-card.png");

    //card initialization for the wild card
    Card wildCard = new Card(wildCardImg);

    //method handling start button click
    @FXML
    private void start(){
        //makes all the imageviews on the board visible
        for (ImageViews[] a: imgViewGrid){
            for (ImageViews b: a){
                b.getImageView().setVisible(true);
            }
        }

        //makes all foundation pile image views visible
        for (ImageViews a: imgViewsFoundationPiles){
            a.getImageView().setVisible(true);
        }

        //changes visibility and aspects of various fxml components
        btnRestart.setVisible(true);
        imgViewStockPile.setVisible(true);
        imgViewWastePileTop.setVisible(true);
        imgViewWastePileBottom.setVisible(true);
        btnStart.setVisible(false);
        txtInstructions.setVisible(false);
        lblScore.setVisible(true);
        lblScoreChange.setVisible(true);
        btnEasy.setLayoutX(50);
        btnEasy.setLayoutY(113);
        btnEasy.setDisable(true);
        btnHard.setDisable(true);
        btnHard.setLayoutX(115);
        btnHard.setLayoutY(113);
        lblDiffSelected.setLayoutX(50);
        lblDiffSelected.setLayoutY(150);
        lblDiffSelected.setVisible(false);
        lblDifficulty.setVisible(false);
        imgViewStartCards1.setVisible(false);
        imgViewStartCards2.setVisible(false);
        imgViewStartCards3.setVisible(false);
        imgViewStartCards4.setVisible(false);
        btnReveal.setVisible(true);
        btnWildCard.setVisible(true);

        //displays score
        displayScore();
    }

    //method handling restart button click
    @FXML
    private void restart(){
        //changes visibility and aspects of various fxml components
        btnEasy.setDisable(false);
        btnHard.setDisable(false);
        lblDiffSelected.setVisible(false);

        //clears all images of board image views, and sets all spots in card grid and image view grid 2d arrays to null
        for (int i=0;i< imgViewGrid.length;i++){
            for (int j=0;j< imgViewGrid[0].length;j++){
                imgViewGrid[i][j].getImageView().setImage(null);
                imgViewGrid[i][j] = null;
                cardGrid[i][j] = null;
            }
        }

        //changes visibility and aspects of various fxml components
        imgViewStockPile.setImage(back);
        imgViewWastePileTop.setImage(null);
        imgViewWastePileBottom.setImage(null);
        imgViewStockPile.setDisable(false);
        imgViewWastePileTop.setDisable(false);
        txtWin.setVisible(false);

        //sets foundation pile images to original images
        for (ImageViews imgViewsFoundationPile : imgViewsFoundationPiles) {
            imgViewsFoundationPile.getImageView().setImage(imgViewsFoundationPile.getFoundationImage());
        }

        //clears all cards in foundation card piles
        for (ArrayList<Card> foundationPileCard : foundationPileCards) {
            foundationPileCard.clear();
        }

        //resets score variables
        score = 0;
        scoreChange = 0;

        //displays score
        displayScore();

        //initializes program again
        initialize();
    }

    //initialize method
    public void initialize() {
        //clears card array lists
        allImgViewsInDrag.clear();
        stockPileCards.clear();
        wastePileCards.clear();

        //sets instructions view property
        txtInstructions.setStyle("-fx-background-color: #fff");

        //runs various methods which start the game up
        setImageViewGrid();
        addInitCards();
        setImgViews();
        setCardMouseEvents();
        disableUnnecessaryImgViews();
        setImgViewProperties();
    }

    //sets all spots in the image view grid with the corresponding values in the parameters
    public void setImageViewGrid() {
        imgViewGrid[0][0] = new ImageViews(207.0, 30.0, 0, 0, imgViewR0C0);
        imgViewGrid[0][1] = new ImageViews(333.0, 30.0, 0, 1, imgViewR0C1);
        imgViewGrid[0][2] = new ImageViews(459.0, 30.0, 0, 2, imgViewR0C2);
        imgViewGrid[0][3] = new ImageViews(585.0, 30.0, 0, 3, imgViewR0C3);
        imgViewGrid[0][4] = new ImageViews(711.0, 30.0, 0, 4, imgViewR0C4);
        imgViewGrid[0][5] = new ImageViews(837.0, 30.0, 0, 5, imgViewR0C5);
        imgViewGrid[0][6] = new ImageViews(963.0, 30.0, 0, 6, imgViewR0C6);

        imgViewGrid[1][0] = new ImageViews(207.0, 71.0, 1, 0, imgViewR1C0);
        imgViewGrid[1][1] = new ImageViews(333.0, 71.0, 1, 1, imgViewR1C1);
        imgViewGrid[1][2] = new ImageViews(459.0, 71.0, 1, 2, imgViewR1C2);
        imgViewGrid[1][3] = new ImageViews(585.0, 71.0, 1, 3, imgViewR1C3);
        imgViewGrid[1][4] = new ImageViews(711.0, 71.0, 1, 4, imgViewR1C4);
        imgViewGrid[1][5] = new ImageViews(837.0, 71.0, 1, 5, imgViewR1C5);
        imgViewGrid[1][6] = new ImageViews(963.0, 71.0, 1, 6, imgViewR1C6);

        imgViewGrid[2][0] = new ImageViews(207.0, 112.0, 2, 0, imgViewR2C0);
        imgViewGrid[2][1] = new ImageViews(333.0, 112.0, 2, 1, imgViewR2C1);
        imgViewGrid[2][2] = new ImageViews(459.0, 112.0, 2, 2, imgViewR2C2);
        imgViewGrid[2][3] = new ImageViews(585.0, 112.0, 2, 3, imgViewR2C3);
        imgViewGrid[2][4] = new ImageViews(711.0, 112.0, 2, 4, imgViewR2C4);
        imgViewGrid[2][5] = new ImageViews(837.0, 112.0, 2, 5, imgViewR2C5);
        imgViewGrid[2][6] = new ImageViews(963.0, 112.0, 2, 6, imgViewR2C6);

        imgViewGrid[3][0] = new ImageViews(207.0, 153.0, 3, 0, imgViewR3C0);
        imgViewGrid[3][1] = new ImageViews(333.0, 153.0, 3, 1, imgViewR3C1);
        imgViewGrid[3][2] = new ImageViews(459.0, 153.0, 3, 2, imgViewR3C2);
        imgViewGrid[3][3] = new ImageViews(585.0, 153.0, 3, 3, imgViewR3C3);
        imgViewGrid[3][4] = new ImageViews(711.0, 153.0, 3, 4, imgViewR3C4);
        imgViewGrid[3][5] = new ImageViews(837.0, 153.0, 3, 5, imgViewR3C5);
        imgViewGrid[3][6] = new ImageViews(963.0, 153.0, 3, 6, imgViewR3C6);

        imgViewGrid[4][0] = new ImageViews(207.0, 194.0, 4, 0, imgViewR4C0);
        imgViewGrid[4][1] = new ImageViews(333.0, 194.0, 4, 1, imgViewR4C1);
        imgViewGrid[4][2] = new ImageViews(459.0, 194.0, 4, 2, imgViewR4C2);
        imgViewGrid[4][3] = new ImageViews(585.0, 194.0, 4, 3, imgViewR4C3);
        imgViewGrid[4][4] = new ImageViews(711.0, 194.0, 4, 4, imgViewR4C4);
        imgViewGrid[4][5] = new ImageViews(837.0, 194.0, 4, 5, imgViewR4C5);
        imgViewGrid[4][6] = new ImageViews(963.0, 194.0, 4, 6, imgViewR4C6);

        imgViewGrid[5][0] = new ImageViews(207.0, 235.0, 5, 0, imgViewR5C0);
        imgViewGrid[5][1] = new ImageViews(333.0, 235.0, 5, 1, imgViewR5C1);
        imgViewGrid[5][2] = new ImageViews(459.0, 235.0, 5, 2, imgViewR5C2);
        imgViewGrid[5][3] = new ImageViews(585.0, 235.0, 5, 3, imgViewR5C3);
        imgViewGrid[5][4] = new ImageViews(711.0, 235.0, 5, 4, imgViewR5C4);
        imgViewGrid[5][5] = new ImageViews(837.0, 235.0, 5, 5, imgViewR5C5);
        imgViewGrid[5][6] = new ImageViews(963.0, 235.0, 5, 6, imgViewR5C6);

        imgViewGrid[6][0] = new ImageViews(207.0, 276.0, 6, 0, imgViewR6C0);
        imgViewGrid[6][1] = new ImageViews(333.0, 276.0, 6, 1, imgViewR6C1);
        imgViewGrid[6][2] = new ImageViews(459.0, 276.0, 6, 2, imgViewR6C2);
        imgViewGrid[6][3] = new ImageViews(585.0, 276.0, 6, 3, imgViewR6C3);
        imgViewGrid[6][4] = new ImageViews(711.0, 276.0, 6, 4, imgViewR6C4);
        imgViewGrid[6][5] = new ImageViews(837.0, 276.0, 6, 5, imgViewR6C5);
        imgViewGrid[6][6] = new ImageViews(963.0, 276.0, 6, 6, imgViewR6C6);

        imgViewGrid[7][0] = new ImageViews(207.0, 317.0, 7, 0, imgViewR7C0);
        imgViewGrid[7][1] = new ImageViews(333.0, 317.0, 7, 1, imgViewR7C1);
        imgViewGrid[7][2] = new ImageViews(459.0, 317.0, 7, 2, imgViewR7C2);
        imgViewGrid[7][3] = new ImageViews(585.0, 317.0, 7, 3, imgViewR7C3);
        imgViewGrid[7][4] = new ImageViews(711.0, 317.0, 7, 4, imgViewR7C4);
        imgViewGrid[7][5] = new ImageViews(837.0, 317.0, 7, 5, imgViewR7C5);
        imgViewGrid[7][6] = new ImageViews(963.0, 317.0, 7, 6, imgViewR7C6);

        imgViewGrid[8][0] = new ImageViews(207.0, 358.0, 8, 0, imgViewR8C0);
        imgViewGrid[8][1] = new ImageViews(333.0, 358.0, 8, 1, imgViewR8C1);
        imgViewGrid[8][2] = new ImageViews(459.0, 358.0, 8, 2, imgViewR8C2);
        imgViewGrid[8][3] = new ImageViews(585.0, 358.0, 8, 3, imgViewR8C3);
        imgViewGrid[8][4] = new ImageViews(711.0, 358.0, 8, 4, imgViewR8C4);
        imgViewGrid[8][5] = new ImageViews(837.0, 358.0, 8, 5, imgViewR8C5);
        imgViewGrid[8][6] = new ImageViews(963.0, 358.0, 8, 6, imgViewR8C6);

        imgViewGrid[9][0] = new ImageViews(207.0, 399.0, 9, 0, imgViewR9C0);
        imgViewGrid[9][1] = new ImageViews(333.0, 399.0, 9, 1, imgViewR9C1);
        imgViewGrid[9][2] = new ImageViews(459.0, 399.0, 9, 2, imgViewR9C2);
        imgViewGrid[9][3] = new ImageViews(585.0, 399.0, 9, 3, imgViewR9C3);
        imgViewGrid[9][4] = new ImageViews(711.0, 399.0, 9, 4, imgViewR9C4);
        imgViewGrid[9][5] = new ImageViews(837.0, 399.0, 9, 5, imgViewR9C5);
        imgViewGrid[9][6] = new ImageViews(963.0, 399.0, 9, 6, imgViewR9C6);

        imgViewGrid[10][0] = new ImageViews(207.0, 440.0, 10, 0, imgViewR10C0);
        imgViewGrid[10][1] = new ImageViews(333.0, 440.0, 10, 1, imgViewR10C1);
        imgViewGrid[10][2] = new ImageViews(459.0, 440.0, 10, 2, imgViewR10C2);
        imgViewGrid[10][3] = new ImageViews(585.0, 440.0, 10, 3, imgViewR10C3);
        imgViewGrid[10][4] = new ImageViews(711.0, 440.0, 10, 4, imgViewR10C4);
        imgViewGrid[10][5] = new ImageViews(837.0, 440.0, 10, 5, imgViewR10C5);
        imgViewGrid[10][6] = new ImageViews(963.0, 440.0, 10, 6, imgViewR10C6);

        imgViewGrid[11][0] = new ImageViews(207.0, 481.0, 11, 0, imgViewR11C0);
        imgViewGrid[11][1] = new ImageViews(333.0, 481.0, 11, 1, imgViewR11C1);
        imgViewGrid[11][2] = new ImageViews(459.0, 481.0, 11, 2, imgViewR11C2);
        imgViewGrid[11][3] = new ImageViews(585.0, 481.0, 11, 3, imgViewR11C3);
        imgViewGrid[11][4] = new ImageViews(711.0, 481.0, 11, 4, imgViewR11C4);
        imgViewGrid[11][5] = new ImageViews(837.0, 481.0, 11, 5, imgViewR11C5);
        imgViewGrid[11][6] = new ImageViews(963.0, 481.0, 11, 6, imgViewR11C6);

        imgViewGrid[12][0] = new ImageViews(207.0, 522.0, 12, 0, imgViewR12C0);
        imgViewGrid[12][1] = new ImageViews(333.0, 522.0, 12, 1, imgViewR12C1);
        imgViewGrid[12][2] = new ImageViews(459.0, 522.0, 12, 2, imgViewR12C2);
        imgViewGrid[12][3] = new ImageViews(585.0, 522.0, 12, 3, imgViewR12C3);
        imgViewGrid[12][4] = new ImageViews(711.0, 522.0, 12, 4, imgViewR12C4);
        imgViewGrid[12][5] = new ImageViews(837.0, 522.0, 12, 5, imgViewR12C5);
        imgViewGrid[12][6] = new ImageViews(963.0, 522.0, 12, 6, imgViewR12C6);
    }

    //initializes new Card objects and adds them to the stock pile, with corresponding values in the parameters
    public void addInitCards() {
        //clears stock pile
        stockPileCards.clear();

        stockPileCards.add(new Card(CA, Color.BLACK, 'c', 1));
        stockPileCards.add(new Card(C2, Color.BLACK, 'c', 2));
        stockPileCards.add(new Card(C3, Color.BLACK, 'c', 3));
        stockPileCards.add(new Card(C4, Color.BLACK, 'c', 4));
        stockPileCards.add(new Card(C5, Color.BLACK, 'c', 5));
        stockPileCards.add(new Card(C6, Color.BLACK, 'c', 6));
        stockPileCards.add(new Card(C7, Color.BLACK, 'c', 7));
        stockPileCards.add(new Card(C8, Color.BLACK, 'c', 8));
        stockPileCards.add(new Card(C9, Color.BLACK, 'c', 9));
        stockPileCards.add(new Card(C10, Color.BLACK, 'c', 10));
        stockPileCards.add(new Card(CJ, Color.BLACK, 'c', 11));
        stockPileCards.add(new Card(CQ, Color.BLACK, 'c', 12));
        stockPileCards.add(new Card(CK, Color.BLACK, 'c', 13));

        stockPileCards.add(new Card(DA, Color.RED, 'd', 1));
        stockPileCards.add(new Card(D2, Color.RED, 'd', 2));
        stockPileCards.add(new Card(D3, Color.RED, 'd', 3));
        stockPileCards.add(new Card(D4, Color.RED, 'd', 4));
        stockPileCards.add(new Card(D5, Color.RED, 'd', 5));
        stockPileCards.add(new Card(D6, Color.RED, 'd', 6));
        stockPileCards.add(new Card(D7, Color.RED, 'd', 7));
        stockPileCards.add(new Card(D8, Color.RED, 'd', 8));
        stockPileCards.add(new Card(D9, Color.RED, 'd', 9));
        stockPileCards.add(new Card(D10, Color.RED, 'd', 10));
        stockPileCards.add(new Card(DJ, Color.RED, 'd', 11));
        stockPileCards.add(new Card(DQ, Color.RED, 'd', 12));
        stockPileCards.add(new Card(DK, Color.RED, 'd', 13));

        stockPileCards.add(new Card(HA, Color.RED, 'h', 1));
        stockPileCards.add(new Card(H2, Color.RED, 'h', 2));
        stockPileCards.add(new Card(H3, Color.RED, 'h', 3));
        stockPileCards.add(new Card(H4, Color.RED, 'h', 4));
        stockPileCards.add(new Card(H5, Color.RED, 'h', 5));
        stockPileCards.add(new Card(H6, Color.RED, 'h', 6));
        stockPileCards.add(new Card(H7, Color.RED, 'h', 7));
        stockPileCards.add(new Card(H8, Color.RED, 'h', 8));
        stockPileCards.add(new Card(H9, Color.RED, 'h', 9));
        stockPileCards.add(new Card(H10, Color.RED, 'h', 10));
        stockPileCards.add(new Card(HJ, Color.RED, 'h', 11));
        stockPileCards.add(new Card(HQ, Color.RED, 'h', 12));
        stockPileCards.add(new Card(HK, Color.RED, 'h', 13));

        stockPileCards.add(new Card(SA, Color.BLACK, 's', 1));
        stockPileCards.add(new Card(S2, Color.BLACK, 's', 2));
        stockPileCards.add(new Card(S3, Color.BLACK, 's', 3));
        stockPileCards.add(new Card(S4, Color.BLACK, 's', 4));
        stockPileCards.add(new Card(S5, Color.BLACK, 's', 5));
        stockPileCards.add(new Card(S6, Color.BLACK, 's', 6));
        stockPileCards.add(new Card(S7, Color.BLACK, 's', 7));
        stockPileCards.add(new Card(S8, Color.BLACK, 's', 8));
        stockPileCards.add(new Card(S9, Color.BLACK, 's', 9));
        stockPileCards.add(new Card(S10, Color.BLACK, 's', 10));
        stockPileCards.add(new Card(SJ, Color.BLACK, 's', 11));
        stockPileCards.add(new Card(SQ, Color.BLACK, 's', 12));
        stockPileCards.add(new Card(SK, Color.BLACK, 's', 13));

        //shuffles stock pile
        Collections.shuffle(stockPileCards);
    }

    //sets the images and other various components
    public void setImgViews() {
        //sets the images of the 28 cards based on their position
        for (int y = 0; y <= 6; y++) {
            for (int x = 0; x <= y; x++) {
                boolean cardShow = false;
                if (x == y) {
                    cardShow = true;
                }

                //sets the images using a randomizeCards method
                imgViewGrid[x][y].getImageView().setImage(randomizeCards(x, y, cardShow));
            }
        }

        //initializes waste pile ImageViews object, and disables its imageview
        imgViewsWastePileTop = new ImageViews(48.5, 499, imgViewWastePileTop);
        imgViewWastePileTop.setDisable(true);

        //initializes ImageViews objects of the top 4 foundation piles
        imgViewsFoundationPiles[0] = new ImageViews(1121.5, 9.6, imgViewFoundationHeartsTop, foundationHearts);
        imgViewsFoundationPiles[1] = new ImageViews(1121.5, 187.2, imgViewFoundationDiamondsTop, foundationDiamonds);
        imgViewsFoundationPiles[2] = new ImageViews(1121.5, 364.8, imgViewFoundationClubsTop, foundationClubs);
        imgViewsFoundationPiles[3] = new ImageViews(1121.5, 542.4, imgViewFoundationSpadesTop, foundationSpades);

        //initializes ImageViews objects of bottom 4 the foundation piles
        imgViewsFoundationPiles[4] = new ImageViews(1121.5, 9.6, imgViewFoundationHeartsBottom, foundationHearts);
        imgViewsFoundationPiles[5] = new ImageViews(1121.5, 187.2, imgViewFoundationDiamondsBottom, foundationDiamonds);
        imgViewsFoundationPiles[6] = new ImageViews(1121.5, 364.8, imgViewFoundationClubsBottom, foundationClubs);
        imgViewsFoundationPiles[7] = new ImageViews(1121.5, 542.4, imgViewFoundationSpadesBottom, foundationSpades);

        //creates new array lists for the cards to be stored in the top 4 arrays of foundation piles
        for (int i = 0; i < 4; i++) {
            foundationPileCards[i] = new ArrayList<>();
        }
    }

    //randomizeCards method to return the image of the card
    public Image randomizeCards(int x, int y, boolean isShownCard) {
        //finds out what the image is based on the isShownCard parameter
        cardGrid[x][y] = stockPileCards.get(0);
        Image a;
        cardGrid[x][y].setBack(!isShownCard);

        if (isShownCard) {
            a = stockPileCards.get(0).getImage();
        } else {
            a = back;
        }

        //removes the card from the stock pile
        stockPileCards.remove(0);

        //returns the image
        return a;
    }

    //method to set the mouse events for all the image views
    public void setCardMouseEvents() {
        //sets mouse events for all the image views on the board
        for (ImageViews[] r : imgViewGrid) {
            for (ImageViews a : r) {
                a.getImageView().setOnMousePressed(event -> pressed(event, a));
                a.getImageView().setOnMouseDragged(event -> dragged(event, a));
                a.getImageView().setOnMouseReleased(event -> dragReleased(a));
                a.getImageView().setOnMouseEntered(event -> hoveredOver(a));
                a.getImageView().setOnMouseClicked(event -> cardClicked(a));
            }
        }

        //sets mouse events for all the foundation piles image views
        for (int i = 0; i < 4; i++) {
            int j = i;
            imgViewsFoundationPiles[i].getImageView().setOnMousePressed(event -> pressed(event, imgViewsFoundationPiles[j]));
            imgViewsFoundationPiles[i].getImageView().setOnMouseDragged(event -> draggedWasteFoundationPile(event, imgViewsFoundationPiles[j], false));
            imgViewsFoundationPiles[i].getImageView().setOnMouseReleased(event -> dragReleased(imgViewsFoundationPiles[j]));
            imgViewsFoundationPiles[i].getImageView().setOnMouseEntered(event -> hoveredOver(imgViewsFoundationPiles[j]));
        }

        //sets mouse events for the waste piles image views
        imgViewWastePileTop.setOnMousePressed(event -> pressed(event, imgViewsWastePileTop));
        imgViewWastePileTop.setOnMouseDragged(event -> draggedWasteFoundationPile(event, imgViewsWastePileTop, true));
        imgViewWastePileTop.setOnMouseReleased(event -> dragReleased(imgViewsWastePileTop));
        imgViewWastePileTop.setOnMouseEntered(event -> hoveredOver(imgViewsWastePileTop));
    }

    //handles wild card button click
    @FXML
    private void wildCard(){
        //adds a wild card to the waste pile, and displays it
        wastePileCards.add(wildCard);
        imgViewsWastePileTop.getImageView().setDisable(false);
        imgViewWastePileTop.setImage(wastePileCards.get(wastePileCards.size()-1).getImage());

        //decreases the score, since points were used to get the wild card
        score-= (50*mode);
        scoreChange -= (50*mode);

        //displays the score
        displayScore();
    }

    //handles reveal button click
    @FXML
    private void reveal(){
        //sets reveal click to true
        revealClick = true;

        //runs method chaining cursor properties of imageviews
        setImgViewProperties();
        btnReveal.setDisable(true);

        //decreases the score, since points were used to get the reveal power up
        score-= (30*mode);
        scoreChange -= (30*mode);

        //displays the score
        displayScore();
    }

    //card clicked method, which handles when cards are clicked (used for reveal power up)
    public void cardClicked(ImageViews a){
        //if the reveal click button has been clicked and the card clicked on is hidden,
        if (revealClick && a.getImageView().getImage()==back){
            int lastRowOfCard = -1;
            int numCardsEndRow = 0;

            //finds out the last row of the set of cards being clicked, and the number of cards in the row
            for (int i=0;i<12;i++){
                if (cardGrid[i][a.getCol()]!=null){
                    if (imgViewGrid[i][a.getCol()].getImageView().getImage()!=back){
                        numCardsEndRow++;
                    }
                    if (cardGrid[i+1][a.getCol()]==null){
                        lastRowOfCard = i;
                    }
                }
            }

            //adds the hidden card to the waste pile, and sets resets its old spot in the board
            cardGrid[a.getRow()][a.getCol()].setBack(false);
            wastePileCards.add(cardGrid[a.getRow()][a.getCol()]);
            cardGrid[a.getRow()][a.getCol()] = null;
            a.getImageView().setImage(null);

            //moves all of the cards that were under the revealed card, up 1 row, so that there is no gap
            for (int i=a.getRow();i<lastRowOfCard;i++){
                cardGrid[i][a.getCol()] = cardGrid[i+1][a.getCol()];
                cardGrid[i][a.getCol()].setBack(!(i>lastRowOfCard - numCardsEndRow - 1));
                if (i>lastRowOfCard - numCardsEndRow - 1){
                    imgViewGrid[i][a.getCol()].getImageView().setImage(cardGrid[i][a.getCol()].getImage());
                } else {
                    imgViewGrid[i][a.getCol()].getImageView().setImage(back);
                }
            }

            //sets the last row of the rows of cards to null, since there is no longer a card there
            cardGrid[lastRowOfCard][a.getCol()] = null;
            imgViewGrid[lastRowOfCard][a.getCol()].getImageView().setImage(null);

            //enables the waste pile image view and sets its image to the revealed card image
            imgViewsWastePileTop.getImageView().setDisable(false);
            imgViewWastePileTop.setImage(wastePileCards.get(wastePileCards.size()-1).getImage());

            //resets reveal click boolean to false
            revealClick = false;

            //runs methods to reset some of the image view properties
            setImgViewProperties();
            disableUnnecessaryImgViews();
        }
    }

    //method handling when the mouse is hovered over an image view
    public void hoveredOver(ImageViews a) {
        //if there is a card at the spot, and the image is not a hidden card, it changes the image view cursor to an open hand
        if ((a.getImageView().getImage() != null) && (a.getImageView().getImage() != back)) {
            a.getImageView().setCursor(Cursor.OPEN_HAND);
        }
    }

    //handles when image views are pressed (not clicked on and off, but pressed)
    public void pressed(MouseEvent event, ImageViews a) {
        //changes visibility and aspects of various fxml components
        btnEasy.setDisable(true);
        btnHard.setDisable(true);
        lblDiffSelected.setVisible(false);

        //if there is a card at the spot, and the image is not a hidden card, it changes the image view cursor to an closed hand
        if ((a.getImageView().getImage() != null) && (a.getImageView().getImage() != back)) {
            a.getImageView().setCursor(Cursor.CLOSED_HAND);
        }

        //sets click x and click y variables to the event coordinate minus the image view coordinate, to precisely be able to move the cards later on
        clickX = event.getX() - a.getImageView().getX();
        clickY = event.getY() - a.getImageView().getY();
    }

    //method handling when the image views are dragged
    public void dragged(MouseEvent event, ImageViews a) {
        //clears current image views being dragged in this arraylist
        allImgViewsInDrag.clear();

        //adds new image views being dragged into the arraylist
        for (int i = a.getRow(); i < 13; i++) {
            if (cardGrid[i][a.getCol()] != null && !cardGrid[i][a.getCol()].getBack()) {
                allImgViewsInDrag.add(imgViewGrid[i][a.getCol()]);
            }
        }

        //if there is a valid card being dragged,
        if ((cardGrid[a.getRow()][a.getCol()] != null) && (!cardGrid[a.getRow()][a.getCol()].getBack())) {
            //changes coordinates for all imageviews in the drag, so that they all move along with the mouse
            for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                allImgViewsInDrag.get(j).getImageView().setX(event.getX() - clickX);
                allImgViewsInDrag.get(j).getImageView().setY(event.getY() - clickY + (j * 41));
                allImgViewsInDrag.get(j).getImageView().toFront();
            }
        }

    }

    //method handling when the waste pile or foundation pile image views are dragged
    public void draggedWasteFoundationPile(MouseEvent event, ImageViews a, boolean isWastePile){
        //clears current image views being dragged in this arraylist, and adds the waste pile image view
        allImgViewsInDrag.clear();
        allImgViewsInDrag.add(a);

        //changes coordinates for the waste pile image view, so that they all move along with the mouse
        a.getImageView().setX(event.getX() - clickX);
        a.getImageView().setY(event.getY() - clickY);
        a.getImageView().toFront();

        //if the waste pile card is being dragged
        if (isWastePile){
            //if there is more than 1 card in the waste pile,
            if (wastePileCards.size() > 1) {
                //the waste pile bottom image view show the next card
                imgViewWastePileBottom.setImage(wastePileCards.get(wastePileCards.size() - 2).getImage());
            //else, it sets the waste pile bottom image view to null
            } else {
                imgViewWastePileBottom.setImage(null);
            }
        }
    }

    //method handling when the mouse (card) is released
    public void dragReleased(ImageViews a) {
        //sets various variables
        boolean isWastePile = (a == imgViewsWastePileTop);
        double dragImgViewMidX = a.getImageView().getX() + 55;
        boolean newColIsEmptyCol = false;
        boolean oldColIsEmptyCol = false;
        int newImgViewCol = -2;

        //runs if the card released is a wild card
        if (a.getImageView().getImage()==wildCardImg){

            //determines which column the image is being placed in
            for (int i = 0; i < imgViewGrid[0].length; i++) {
                if ((dragImgViewMidX >= imgViewGrid[0][i].getLeftX() - 8) && (dragImgViewMidX <= imgViewGrid[0][i].getRightX() + 8)) {
                    newImgViewCol = i;
                }
            }

            //finds out if the old column is an empty one
            if (newImgViewCol >-1) {
                if (a.getRow() == 0 && !isWastePile) {
                    oldColIsEmptyCol = true;
                }

                //loops through all rows of the board grid
                for (int i = 0; i < imgViewGrid.length; i++) {
                    //if there is an empty spot in the new column, at the i row,
                    if (imgViewGrid[i][newImgViewCol].getImageView().getImage() == null) {
                        //runs wild card match method
                        wildCardMatch(a, i, imgViewGrid[i][newImgViewCol], oldColIsEmptyCol, isWastePile, false);
                        i = imgViewGrid.length;
                    //else, if i is 12
                    } else if (i == 12) {
                        //runs wild card match method, with different parameters
                        wildCardMatch(a, i, imgViewGrid[i][newImgViewCol], oldColIsEmptyCol, isWastePile, true);
                    }
                }
            //if the image view was not placed in a valid column (invalid play, out of bounds)
            } else {
                //loops through all images being dragged
                for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                    //if the wild card is not from a waste pile, it resets all the image views being dragged to their original spots
                    if (!isWastePile) {
                        imgViewGrid[a.getRow() + j][a.getCol()].getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                        imgViewGrid[a.getRow() + j][a.getCol()].getImageView().setY(allImgViewsInDrag.get(j).getTopY());
                    //if it is from a waste pile, it resets the card back to the waste pile image view spot
                    } else {
                        a.getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                        a.getImageView().setY(allImgViewsInDrag.get(j).getTopY());
                    }
                }
            }

            //else, if the card is not a wild card, and it is not a hidden card
            } else if (a.getImageView().getImage()!=back) {
            //sets booleans used later.
            boolean toFoundationPile = false;
            boolean fromFoundationPile = false;

            //if the image dragged is not null,
            if (a.getImageView().getImage() != null) {
                //determines which column the image is being placed in
                for (int i = 0; i < imgViewGrid[0].length; i++) {
                    if ((dragImgViewMidX >= imgViewGrid[0][i].getLeftX() - 8) && (dragImgViewMidX <= imgViewGrid[0][i].getRightX() + 8)) {
                        newImgViewCol = i;
                    }
                }
                //determines if the image is being dragged from the foundation pile
                for (int i = 0; i < 4; i++) {
                    if (a == imgViewsFoundationPiles[i]) {
                        fromFoundationPile = true;
                        break;
                    }
                }
                //if there is no new image view column detected, this determines if the image is being dragged to the foundation pile
                if (newImgViewCol == -2) {
                    if ((dragImgViewMidX >= imgViewsFoundationPiles[0].getLeftX() - 24.25) && (dragImgViewMidX <= imgViewsFoundationPiles[0].getRightX() + 48.5) && allImgViewsInDrag.size() == 1) {
                        newImgViewCol = 20;
                        toFoundationPile = true;
                    }
                }
            }

            //if there is some place for the card(s) to go to,
            if ((newImgViewCol > -1)) {
                //determines if the old column is empty
                if (a.getRow() == 0 && !isWastePile && !fromFoundationPile) {
                    oldColIsEmptyCol = true;
                }

                //if card(s) are going to a normal pile
                if (!toFoundationPile) {
                    //determines if the new row is empty
                    if (imgViewGrid[0][newImgViewCol].getImageView().getImage() == null || imgViewGrid[0][newImgViewCol] == a) {
                        newColIsEmptyCol = true;
                    }

                    //loops through all rows of the board grid, and runs a match method, with different parameters, which will determine if the cards match or not
                    for (int i = 0; i < imgViewGrid.length; i++) {
                        if (imgViewGrid[i][newImgViewCol].getImageView().getImage() == null) {
                            match(a, i - 1, imgViewGrid[i][newImgViewCol], newColIsEmptyCol, oldColIsEmptyCol, isWastePile, fromFoundationPile, false);
                            i = imgViewGrid.length;
                        } else if (i == 12) {
                            match(a, i - 1, imgViewGrid[i][newImgViewCol], newColIsEmptyCol, oldColIsEmptyCol, isWastePile, fromFoundationPile, true);
                        }
                    }
                //else, if the card is going to the foundation pile, and there is not more than 1 card being dragged,
                } else if (allImgViewsInDrag.size() == 1) {
                    //if it is from the foundation pile, it resets the coordinates, since they cannot go from one foundation pile to another
                    if (fromFoundationPile) {
                        a.getImageView().setX(a.getLeftX());
                        a.getImageView().setY(a.getTopY());
                        a.getImageView().toFront();
                    //else, it runs match foundation pile, a different match method that determines if the cards will match or not
                    } else {
                        matchFoundationPile(a, oldColIsEmptyCol, isWastePile);
                    }
                }
            //if there is no place for the cards to go
            } else {
                //loop runs through all cards being dragged, and resets their positions, since they cannot move where they have selected to  move
                for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                    if (!isWastePile && !fromFoundationPile) {
                        imgViewGrid[a.getRow() + j][a.getCol()].getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                        imgViewGrid[a.getRow() + j][a.getCol()].getImageView().setY(allImgViewsInDrag.get(j).getTopY());
                    } else {
                        a.getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                        a.getImageView().setY(allImgViewsInDrag.get(j).getTopY());
                    }
                }
            }

        //else, if the image view is hidden,
        } else {
            //sets cursor to default
            a.getImageView().setCursor(Cursor.DEFAULT);
        }

        //clears arraylist of image views being dragged, runs methods to change image view aspects
        allImgViewsInDrag.clear();
        setImgViewProperties();
        disableUnnecessaryImgViews();

        //if the waste pile and stock pile have no cards, it runs various things to prevent the user from using it
        if (wastePileCards.size() == 0 && stockPileCards.size() == 0) {
            imgViewStockPile.setImage(back);
            imgViewStockPile.setCursor(Cursor.DEFAULT);
        }

        //displays score, and checks if the player has won
        displayScore();
        checkWin();
    }

    //method to handle wild card match logic
    public void wildCardMatch(ImageViews dragImgView, int rowToBePlaceIn, ImageViews newSpotImageView, boolean oldColIsEmptyCol, boolean isWastePile, boolean rowFull){
        //sets match boolean to true.
        boolean match = true;

        //sets match to false if any of the conditions are true
        if ((rowToBePlaceIn-1 + allImgViewsInDrag.size() > 12) || (dragImgView.getCol() == newSpotImageView.getCol()) || rowFull) {
            match = false;
        }

        //if it is still a match,
        if (match) {
            //runs match change things method with these parameters, which changes the actual aspects of the board and other parts, since there is a match
            matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, isWastePile, false);
        }
        //runs matching abstract function with various purposes
        matchingAbstract(dragImgView, isWastePile);
    }

    //method to handle matching of main cards (i would say it's the big brains of the program)
    public void match(ImageViews dragImgView, int comparingImgViewRow, ImageViews newSpotImageView, boolean newColIsEmptyCol, boolean oldColIsEmptyCol, boolean isWastePile, boolean fromFoundationPile, boolean rowFull) {
        //sets foundation row integer.
        int foundationRow = -1;
        //if the card is from a foundation pile, it finds which foundation pile (used as foundationRow) it is in
        if (fromFoundationPile) {
            for (int i = 0; i < 4; i++) {
                if (dragImgView == imgViewsFoundationPiles[i]) {
                    foundationRow = i;
                }
            }
        }

        //if the new column is not empty,
        if (!newColIsEmptyCol) {

            //sets comparing image view
            ImageViews comparingImgView = imgViewGrid[comparingImgViewRow][newSpotImageView.getCol()];

            //sets match boolean to true
            boolean match = true;

            if (rowFull || (comparingImgViewRow + allImgViewsInDrag.size() > 12) || comparingImgView.getCol() == dragImgView.getCol()) {
                match = false;
            }
            //if the card being compared to is the wild card (not card being dragged) and the column of the dragged card is different from the column of the card being compared to
            if (comparingImgView.getImageView().getImage()==wildCardImg){
                //if it is a match,
                if (match) {
                    //runs match change things method, with different parameters
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, isWastePile, fromFoundationPile);
                }
            //else if the card being dragged is from the main board,
            } else if (!isWastePile && !fromFoundationPile) {

                //checks to make sure colors of card being dragged and card in the column being compared to are different
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getColor() == cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getColor()) {
                    match = false;
                }

                //checks to make sure value of card being dragged is 1 less than card in the column being compared to
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getValue() != cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getValue() - 1) {
                    match = false;
                }

                //if it is a match,
                if (match) {
                    //runs match change things method, with different parameters
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, false, false);
                }

            //else, if the card being dragged is from a waste pile
            } else if (isWastePile) {

                //checks to make sure colors of card being dragged(checking the last/latest card in the waste pile arraylist) and card in the column being compared to are different
                if (wastePileCards.get(wastePileCards.size() - 1).getColor() == cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getColor()) {
                    match = false;
                }

                //checks to make sure value of card being dragged is 1 less than card in the column being compared to
                if (wastePileCards.get(wastePileCards.size() - 1).getValue() != cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getValue() - 1) {
                    match = false;
                }


                //if it is a match,
                if (match) {
                    //runs match change things method, with different parameters
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, true, false);
                }

            //else, if the card being dragged is from a foundation pile
            } else {
                //if there are no cards in the foundation pile being dragged from, match is set to false
                if (foundationPileCards[foundationRow].size() == 0) {
                    match = false;

                //else, if there are cards in the foundation pile,
                } else {

                    //checks to make sure colors of card being dragged(checking the last/latest card in the foundation pile) and card in the column being compared to are different
                    if (foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 1).getColor() == cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getColor()) {
                        match = false;
                    }
                    //checks to make sure value of card being dragged is 1 less than card in the column being compared to
                    if (foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 1).getValue() != cardGrid[comparingImgView.getRow()][comparingImgView.getCol()].getValue() - 1) {
                        match = false;
                    }
                }

                //if it is a match,
                if (match) {
                    //runs match change things method, with different parameters
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, false, true);
                }
            }

        //else, if the new row is empty,
        } else if (newSpotImageView.getCol() != dragImgView.getCol()){

            //if the card being dragged is from the main board,
            if (!isWastePile && !fromFoundationPile) {
                //if the card is a king (only kings can be placed into an empty column), it runs the match change things method, with different parameters
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getValue() == 13) {
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, false, false);
                }
            //else, if the card is being dragged from the waste pile,
            } else if (isWastePile) {
                //if the card is a king, it runs the match change things method, with different parameters, with different parameters
                if (wastePileCards.get(wastePileCards.size() - 1).getValue() == 13) {
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, true, fromFoundationPile);
                }
            //else, if the card is being dragged from the foundation pile,
            } else {

                //if the card is a king, it runs the match change things method, with different parameters, with different parameters
                if (foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 1).getValue() == 13) {
                    matchChangeThings(dragImgView, newSpotImageView, oldColIsEmptyCol, true, true);
                }
            }
        }

        //if the card being dragged is from the main board, it resets those corresponding image views
        if (!isWastePile && !fromFoundationPile) {
            for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                imgViewGrid[dragImgView.getRow() + j][dragImgView.getCol()].getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                imgViewGrid[dragImgView.getRow() + j][dragImgView.getCol()].getImageView().setY(allImgViewsInDrag.get(j).getTopY());
            }
        //else, if the card is being dragged from the waste pile, it resets those corresponding image views
        } else if (isWastePile) {
            imgViewWastePileTop.setX(imgViewsWastePileTop.getLeftX());
            imgViewWastePileTop.setY(imgViewsWastePileTop.getTopY());
        //else, if the card is being dragged from the foundation pile, it resets those corresponding image views
        } else {
            dragImgView.getImageView().setX(dragImgView.getLeftX());
            dragImgView.getImageView().setY(dragImgView.getTopY());
        }
    }

    //method match change things, which changes various parts of the board if there is a match
    public void matchChangeThings(ImageViews dragImgView, ImageViews newSpotImageView, boolean oldColIsEmptyCol, boolean isWastePile, boolean fromFoundationPile) {
        //if the card being dragged is from the main board,
        if (!isWastePile && !fromFoundationPile) {
            //loops through all cards being dragged
            for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                //replaces new spots with card values of cards being dragged, and resets spots of cards being dragged
                cardGrid[newSpotImageView.getRow() + j][newSpotImageView.getCol()] = cardGrid[dragImgView.getRow() + j][dragImgView.getCol()];
                cardGrid[dragImgView.getRow() + j][dragImgView.getCol()] = null;
                imgViewGrid[newSpotImageView.getRow() + j][newSpotImageView.getCol()].getImageView().setImage(allImgViewsInDrag.get(j).getImageView().getImage());
                imgViewGrid[dragImgView.getRow() + j][dragImgView.getCol()].getImageView().setImage(null);

                //if the old row is not empty, runs method for certain purposes
                if (!oldColIsEmptyCol) {
                    changeThingsAbstract(dragImgView);
                }
            }

        //else, if the card is being dragged from the waste pile,
        } else if (isWastePile) {
            //increases score
            score+=5;
            scoreChange +=5;

            //replaces new spot with card value of card being dragged, and resets spot of card being dragged
            cardGrid[newSpotImageView.getRow()][newSpotImageView.getCol()] = wastePileCards.get(wastePileCards.size() - 1);
            imgViewGrid[newSpotImageView.getRow()][newSpotImageView.getCol()].getImageView().setImage(allImgViewsInDrag.get(0).getImageView().getImage());

            //removes card being dragged from the waste pile
            wastePileCards.remove(wastePileCards.size() - 1);

            //sets the image view waste pile bottom image to the appropriate image
            if (imgViewWastePileBottom.getImage() != null) {
                imgViewWastePileTop.setImage(imgViewWastePileBottom.getImage());
                imgViewWastePileBottom.setImage(null);
            } else {
                imgViewWastePileTop.setImage(null);
            }

        //else, if the card is being dragged from the foundation pile,
        } else {
            //decreases score
            score-=(15*mode);
            scoreChange -= (15*mode);

            //determines which foundation pile row the card is being dragged from
            int foundationRow = -1;
            for (int i = 0; i < 4; i++) {
                if (dragImgView == imgViewsFoundationPiles[i]) {
                    foundationRow = i;
                }
            }

            //replaces new spot with card values of cards being dragged, and resets spot of card being dragged
            cardGrid[newSpotImageView.getRow()][newSpotImageView.getCol()] = foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 1);
            imgViewGrid[newSpotImageView.getRow()][newSpotImageView.getCol()].getImageView().setImage(allImgViewsInDrag.get(0).getImageView().getImage());

            //removes card being dragged from the foundation pile
            foundationPileCards[foundationRow].remove(foundationPileCards[foundationRow].size() - 1);

            //if the foundation row has no cards after the drag, it sets its image to the default foundation pile image
            if (foundationPileCards[foundationRow].size() == 0) {
                imgViewsFoundationPiles[foundationRow].getImageView().setImage(imgViewsFoundationPiles[foundationRow].getFoundationImage());

            //else, if it has 1 card, it sets its top image to that one card's image, and sets the bottom image to the default foundation pile image
            } else if (foundationPileCards[foundationRow].size() == 1) {
                imgViewsFoundationPiles[foundationRow].getImageView().setImage(foundationPileCards[foundationRow].get(0).getImage());
                imgViewsFoundationPiles[foundationRow + 4].getImageView().setImage(imgViewsFoundationPiles[foundationRow].getFoundationImage());

            //else, if it has more than 1 card, it sets its top image to the highest card in the pile, and its bottom image to the 2nd highest card in the pile
            } else {
                imgViewsFoundationPiles[foundationRow].getImageView().setImage(foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 1).getImage());
                imgViewsFoundationPiles[foundationRow + 4].getImageView().setImage(foundationPileCards[foundationRow].get(foundationPileCards[foundationRow].size() - 2).getImage());
            }
        }
    }

    //match foundation pile method, which runs if the card is going to a foundation pile
    public void matchFoundationPile(ImageViews dragImgView, boolean oldColIsEmptyCol, boolean isWastePile) {
        //various variables used later on
        int comparingImgViewRow = 0;
        boolean match = true;
        boolean pileEmpty = false;
        double dragImgViewMidY = dragImgView.getImageView().getY() + 84;

        //determines which foundation pile row to compare to
        for (int i = 0; i < 4; i++) {
            if ((dragImgViewMidY >= imgViewsFoundationPiles[i].getTopY() - 4.8) && (dragImgViewMidY <= imgViewsFoundationPiles[i].getBottomY() + 4.8)) {
                comparingImgViewRow = i;
                i = 4;
            }
        }

        //if the foundation pile has no cards, it sets the pile empty boolean to true
        if (foundationPileCards[comparingImgViewRow].size() == 0) {
            pileEmpty = true;
        }

        //if the pile is not empty,
        if (!pileEmpty) {
            //if the card dragged is not from a waste pile,
            if (!isWastePile) {
                //checks to make sure suits of card being dragged and the card in the foundation pile being compared to are the same
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getSuit() != foundationPileCards[comparingImgViewRow].get(foundationPileCards[comparingImgViewRow].size() - 1).getSuit()) {
                    match = false;
                }

                //checks to make sure value of card being dragged is 1 more than the card in the foundation pile being compared to
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getValue() != foundationPileCards[comparingImgViewRow].get(foundationPileCards[comparingImgViewRow].size() - 1).getValue() + 1) {
                    match = false;
                }

                //if it is a match,
                if (match) {
                    //runs match change things foundation pile method
                    matchChangeThingsFoundationPile(dragImgView, comparingImgViewRow, oldColIsEmptyCol, false, false);
                }

            //else, if the card dragged is from a waste pile,
            } else {

                //checks to make sure suits of card being dragged and the card in the foundation pile being compared to are the same
                if (wastePileCards.get(wastePileCards.size() - 1).getSuit() != foundationPileCards[comparingImgViewRow].get(foundationPileCards[comparingImgViewRow].size() - 1).getSuit()) {
                    match = false;
                }

                //checks to make sure value of card being dragged is 1 more than the card in the foundation pile being compared to
                if (wastePileCards.get(wastePileCards.size() - 1).getValue() != foundationPileCards[comparingImgViewRow].get(foundationPileCards[comparingImgViewRow].size() - 1).getValue() + 1) {
                    match = false;
                }

                //if it is a match,
                if (match) {
                    //runs match change things foundation pile method, with different parameters
                    matchChangeThingsFoundationPile(dragImgView, comparingImgViewRow, oldColIsEmptyCol, false, true);
                }
            }

        //else, if the pile is full
        } else {

            //determines what suit the foundation pile is for, since there are no cards in that foundation pile to determine the suit from
            char suit;
            if (comparingImgViewRow == 0) {
                suit = 'h';
            } else if (comparingImgViewRow == 1) {
                suit = 'd';
            } else if (comparingImgViewRow == 2) {
                suit = 'c';
            } else {
                suit = 's';
            }

            //if the card dragged is not from a waste pile,
            if (!isWastePile) {

                //checks to make sure suits of card being dragged and the foundation pile being compared to are the same
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getSuit() != suit) {
                    match = false;
                }

                //checks to make sure value of card being dragged is an ace (1)
                if (cardGrid[dragImgView.getRow()][dragImgView.getCol()].getValue() != 1) {
                    match = false;
                }

                //if it is a match,
                if (match) {
                    //runs match change things foundation pile method, with different parameters
                    matchChangeThingsFoundationPile(dragImgView, comparingImgViewRow, oldColIsEmptyCol, true, false);
                }

            //else, if the card dragged is from a waste pile,
            } else {

                //checks to make sure suits of card being dragged and the foundation pile being compared to are the same
                if (wastePileCards.get(wastePileCards.size() - 1).getSuit() != suit) {
                    match = false;
                }

                //checks to make sure value of card being dragged is an ace (1)
                if (wastePileCards.get(wastePileCards.size() - 1).getValue() != 1) {
                    match = false;
                }

                //if it is a match,
                if (match) {
                    //runs match change things foundation pile method, with different parameters
                    matchChangeThingsFoundationPile(dragImgView, comparingImgViewRow, oldColIsEmptyCol, true, true);
                }
            }
        }

        //runs a matching abstract method for certain purposes
        matchingAbstract(dragImgView, isWastePile);
    }

    //match change things foundation pile, which changes various aspects of the board when cards are going to a foundation pile
    public void matchChangeThingsFoundationPile(ImageViews dragImgView, int newRow, boolean oldColIsEmptyCol, boolean pileEmpty, boolean isWastePile) {
        //increases score
        score+=10;
        scoreChange += 10;

        //if the pile is empty, it sets the bottom image to the original foundation pile image,
        if (pileEmpty) {
            imgViewsFoundationPiles[newRow + 4].getImageView().setImage(imgViewsFoundationPiles[newRow].getImageView().getImage());
        //else, if the pile is not empty, it sets the bottom image to the image of the card under top card
        } else {
            imgViewsFoundationPiles[newRow + 4].getImageView().setImage(foundationPileCards[newRow].get(foundationPileCards[newRow].size() - 1).getImage());
        }

        //if the card dragged is not from a waste pile,
        if (!isWastePile) {

            //adds the card dragged to the array list corresponding to its row in the foundation pile cards array, and changes that image in the  arraylist corresponding to its row in the image view foundation piles array to the image of the card dragged (now the top card on that pile)
            foundationPileCards[newRow].add(cardGrid[dragImgView.getRow()][dragImgView.getCol()]);
            imgViewsFoundationPiles[newRow].getImageView().setImage(allImgViewsInDrag.get(0).getImageView().getImage());

            //resets the old drag spots in the card grid and image view grid 2D arrays
            cardGrid[dragImgView.getRow()][dragImgView.getCol()] = null;
            imgViewGrid[dragImgView.getRow()][dragImgView.getCol()].getImageView().setImage(null);

            //if the old column is not empty,
            if (!oldColIsEmptyCol) {
                //runs change things abstract method for certain purposes,
                changeThingsAbstract(dragImgView);
            }

        //else, if the card dragged is from a waste pile,
        } else {

            //adds the card dragged to the array list corresponding to its row in the foundation pile cards array, and changes that image in the  arraylist corresponding to its row in the image view foundation piles array to the image of the card dragged (now the top card on that pile)
            foundationPileCards[newRow].add(wastePileCards.get(wastePileCards.size() - 1));
            imgViewsFoundationPiles[newRow].getImageView().setImage(allImgViewsInDrag.get(0).getImageView().getImage());

            //removes the card dragged from the waste pile
            wastePileCards.remove(wastePileCards.size() - 1);

            //sets the image view waste pile bottom image to the appropriate image
            if (imgViewWastePileBottom.getImage() != null) {
                imgViewWastePileTop.setImage(imgViewWastePileBottom.getImage());
                imgViewWastePileBottom.setImage(null);
            } else {
                imgViewWastePileTop.setImage(null);
            }
        }
    }

    //method abstracting code segments from other methods
    public void changeThingsAbstract(ImageViews dragImgView){
        //if the image above the one dragged in the original row was hidden,
        if (imgViewGrid[dragImgView.getRow() - 1][dragImgView.getCol()].getImageView().getImage()==back){
            //adds 5 to the score, as a card was revealed
            score+=5;
            scoreChange +=5;
        }
        //sets the image of that spot to the card's image, and sets that card's back boolean to false
        imgViewGrid[dragImgView.getRow() - 1][dragImgView.getCol()].getImageView().setImage(cardGrid[dragImgView.getRow() - 1][dragImgView.getCol()].getImage());
        cardGrid[dragImgView.getRow() - 1][dragImgView.getCol()].setBack(false);
    }

    //method abstracting code segments from other methods
    public void matchingAbstract(ImageViews dragImgView, boolean isWastePile){
        //if the card is not from the waste pile,
        if (!isWastePile) {
            for (int j = 0; j < allImgViewsInDrag.size(); j++) {
                //resets all the image views to their original spots
                imgViewGrid[dragImgView.getRow() + j][dragImgView.getCol()].getImageView().setX(allImgViewsInDrag.get(j).getLeftX());
                imgViewGrid[dragImgView.getRow() + j][dragImgView.getCol()].getImageView().setY(allImgViewsInDrag.get(j).getTopY());
            }
        //if it is from the waste pile,
        } else {
            //resets the waste pile image views to their original spots
            imgViewWastePileTop.setX(imgViewsWastePileTop.getLeftX());
            imgViewWastePileTop.setY(imgViewsWastePileTop.getTopY());
        }
    }

    //method which sets various image view properties
    public void setImgViewProperties() {
        for (int i = 0; i < imgViewGrid[0].length; i++) {
            for (ImageViews[] imageViews : imgViewGrid) {
                //moves image views to front, in order, to prevent overlapping errors
                imageViews[i].getImageView().toFront();
                //changes image view cursor properties based on different factors
                if (imageViews[i].getImageView().getImage() != null) {
                    if (imageViews[i].getImageView().getImage() == back) {
                        if (revealClick) {
                            imageViews[i].getImageView().setCursor(Cursor.HAND);
                        } else {
                            imageViews[i].getImageView().setCursor(Cursor.DEFAULT);
                        }
                    } else {
                        imageViews[i].getImageView().setCursor(Cursor.OPEN_HAND);
                    }
                }
            }
        }
    }

    //method disabling image views that are not needed
    public void disableUnnecessaryImgViews() {
        for (int i = 0; i < cardGrid.length; i++) {
            for (int j = 0; j < cardGrid[0].length; j++) {
                //disables image view if no card is present at that spot in card grid
                imgViewGrid[i][j].getImageView().setDisable(cardGrid[i][j] == null);
            }
        }
        //disables foundation pile image views that have no cards in it
        for (int i = 0; i < 4; i++) {
            imgViewsFoundationPiles[i].getImageView().setDisable(foundationPileCards[i].size() == 0);
        }
    }

    //method to check if the player has won
    public void checkWin(){
        boolean win = true;
        //if any of the foundation piles do not have 13 cards, then it sets win to false
        for (ArrayList<Card> foundationPileCard : foundationPileCards) {
            if (foundationPileCard.size() != 13) {
                win = false;
                break;
            }
        }
        //if the player has won, it disables many imageviews so that the player cannot interact when they have finished
        if (win){
            for (ImageViews a: imgViewsFoundationPiles){
                a.getImageView().setDisable(true);
            }
            for (ImageViews[] a: imgViewGrid){
                for (ImageViews b: a){
                    b.getImageView().setImage(null);
                }
            }
            //disables various image views
            imgViewStockPile.setDisable(true);
            imgViewWastePileTop.setDisable(true);
            //displays that the player has won
            txtWin.setVisible(true);
        }
    }

    //sets difficulty to easy and displays it
    @FXML
    private void diffEasy(){
        updateBtns(1);
        lblDiffSelected.setText("Easy difficulty selected");
    }

    //sets difficulty to hard and displays it using update buttons method
    @FXML
    private void diffHard(){
        updateBtns(2);
        lblDiffSelected.setText("Hard difficulty selected");
    }

    //method which updates mode and other fxml components
    public void updateBtns(int i){
        //sets mode to integer i parameter
        mode = i;
        //disables start button
        btnStart.setDisable(false);
        //sets text for power up buttons, changes cost based on mode
        btnReveal.setText("Reveal Card - " + mode*30 + " pts");
        btnWildCard.setText("Wild Card - " + mode*50 + " pts");
    }

    //method to display the score
    public void displayScore(){
        //prevents score from going negative
        if (score<0){
            score = 0;
        }
        //enables/disables power up buttons based on the player's score
        btnReveal.setDisable(score < 30*mode);
        btnWildCard.setDisable(score<50*mode);
        //displays current total score
        lblScore.setText("Score: " + score);
        //if the person has just gained points, it displays how many points they gained in green
        if (scoreChange>0){
            lblScoreChange.setText("+" + scoreChange);
            lblScoreChange.setStyle("-fx-text-fill: #12ff00");
        //if the person has just lost points, it displays how many points they lost in red
        } else if (scoreChange<0){
            lblScoreChange.setText(""+scoreChange);
            lblScoreChange.setStyle("-fx-text-fill: #ff0000");
        //if they haven't gained or lost points, it doesn't show anything in the score change label
        } else {
            lblScoreChange.setText("");
        }
        //resets score change so it can be accurately used later.
        scoreChange = 0;
    }

    //handles clicking of stock pile
    @FXML
    private void stockPileClick() {
        //disables/enables and sets various parts.
        imgViewsWastePileTop.getImageView().setDisable(false);
        btnEasy.setDisable(true);
        btnHard.setDisable(true);
        lblDiffSelected.setVisible(false);

        //this runs if the stock pile has 1 or more cards in it, to normally proceed and move the card from the stock pile to waste pile
        //this executes various code to move it from each array list, change the imageview properties, etc.
        if (stockPileCards.size() > 0) {
            if (wastePileCards.size() > 0) {
                imgViewWastePileTop.setCursor(Cursor.HAND);
            } else {
                imgViewWastePileTop.setCursor(Cursor.DEFAULT);
            }
            //adds the card to the waste pile
            wastePileCards.add(stockPileCards.get(0));
            imgViewStockPile.setCursor(Cursor.HAND);
            //sets the waste pile imageview to the card just added
            imgViewWastePileTop.setImage(wastePileCards.get(wastePileCards.size()-1).getImage());
            if (stockPileCards.size() == 1) {
                imgViewStockPile.setImage(resetCard);
            }
            //removes that card from stockpile
            stockPileCards.remove(0);
            //if the stock pile has 0 cards
        } else {
            //subtract this amount of score for resetting the stock pile
            score-= (50*mode);
            scoreChange -= (50*mode);
            //moves all cards in waste pile to stock pile
            stockPileCards.addAll(wastePileCards);
            wastePileCards.clear();
            //sets various imageview properties
            imgViewWastePileTop.setImage(null);
            imgViewWastePileTop.setDisable(true);
            imgViewWastePileBottom.setImage(null);
            //sets image and cursor properties
            imgViewWastePileTop.setCursor(Cursor.DEFAULT);
            imgViewStockPile.setImage(back);
            //if both piles have 0 cards, it disables the stock pile, since it cannot be used.
            if (wastePileCards.size()==0 && stockPileCards.size()==0){
                imgViewStockPile.setImage(back);
                imgViewStockPile.setCursor(Cursor.DEFAULT);
                imgViewStockPile.setDisable(true);
                imgViewStockPile.setImage(null);
                imgViewWastePileTop.setCursor(Cursor.DEFAULT);
            }
        }
        //displays current score
        displayScore();
    }

}