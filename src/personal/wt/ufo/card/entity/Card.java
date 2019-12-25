package personal.wt.ufo.card.entity;

import personal.wt.ufo.card.enums.PictureType;

import java.awt.*;

public class Card {
    private PictureType pictureType;

    private String v;

    private boolean isSelected;

    private Image image;

    private int sortValue;

    public Card(PictureType pictureType, String v){
        this.pictureType = pictureType;
        this.v = v;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSortValue() {
        return sortValue;
    }

    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }
}
