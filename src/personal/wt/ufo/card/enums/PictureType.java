package personal.wt.ufo.card.enums;

public enum PictureType {

    HX(1000000, "HX", "红心"), FP(10000, "FP", "方片"), HT(100, "HT", "黑桃"), MH(1, "MH", "梅花"), JOKER(null, "JOKER", "大小王");

    String code;
    String desc;
    Integer sortValue;

    PictureType(Integer sortValue, String code, String desc){
        this.sortValue = sortValue;
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }
}
