package personal.wt.ufo.card.entity;

import lombok.Getter;
import lombok.Setter;
import personal.wt.ufo.card.enums.PictureType;
import java.awt.*;

/**
 * @author ttb
 */
@Setter
@Getter
public class Card{
    private PictureType pictureType;

    private String v;

    private boolean isSelected;

    private Image image;

    private int sortValue;

    public Card(){}

    public Card(PictureType pictureType, String v){
        this.pictureType = pictureType;
        this.v = v;
    }
}
