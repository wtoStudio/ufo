package personal.wt.ufo.snake;

public enum Direction {
    UP("UP", "上"), DOWN("DOWN", "下"), LEFT("LEFT","左"), RIGHT("RIGHT", "右");
    String code;
    String desc;
    Direction(String code, String desc){
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
}
