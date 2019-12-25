package personal.wt.ufo.teak;

public enum Direction {
    UP("UP", "上"), DOWN("DOWN", "下"), LEFT("LEFT","左"), RIGHT("RIGHT", "右");
    private final String code;
    private final String desc;
    Direction(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
