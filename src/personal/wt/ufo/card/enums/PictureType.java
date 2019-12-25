package personal.wt.ufo.card.enums;

import lombok.Getter;
/**
 * @author ttb
 */
@Getter
public enum PictureType {
    /**
     * 表示几种花色
     */
    HX(1000000, "HX", "红心"),
    FP(10000, "FP", "方片"),
    HT(100, "HT", "黑桃"),
    MH(1, "MH", "梅花"),
    JOKER0(null, "JOKER0", "小王"),
    JOKER1(null, "JOKER1", "大王");

    String code;

    String desc;

    Integer sortValue;

    PictureType(Integer sortValue, String code, String desc){
        this.sortValue = sortValue;
        this.code = code;
        this.desc = desc;
    }
}
