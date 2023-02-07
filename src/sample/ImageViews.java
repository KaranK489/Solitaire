//this is the ImageViews class, which essentially makes it much, much easier to access imageview information. I can keep 2d or 1d arrays of ImageViews objects, which consists of the imageview obviously, the current row and column it's in, different x and y values, etc. rather than having to find information in a more difficult manner (ex. scouting through the image view name to try to find its row and column).
package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViews {
    //stores original x and y values of imageviews
    private final double origX;
    private final double origY;
    //stores row and column of imageviews
    private int row = 20;
    private int col = 20;
    //stores imageview
    private final ImageView imageView;
    //stores original image for the foundation image views
    private Image foundationImage;

    //constructor 1
    public ImageViews(double x, double y, ImageView a) {
        origX = x;
        origY = y;
        imageView = a;
    }

    //constructor 2
    public ImageViews(double x, double y, ImageView a, Image fondImg) {
        origX = x;
        origY = y;
        imageView = a;
        foundationImage = fondImg;
    }

    //constructor 3
    public ImageViews(double x, double y, int r, int c, ImageView a) {
        origX = x;
        origY = y;
        row = r;
        col = c;
        imageView = a;
    }

    //various accessors used to return information
    public double getLeftX() {
        return origX;
    }

    public double getTopY() {
        return origY;
    }

    public double getRightX() {
        return origX + 110;
    }

    public double getBottomY() {
        return origY + 168;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Image getFoundationImage() {
        return foundationImage;
    }
}