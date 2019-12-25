package personal.wt.ufo.card.ui;

import org.springframework.beans.BeanUtils;
import personal.wt.ufo.card.entity.Card;
import personal.wt.ufo.card.enums.PictureType;
import personal.wt.ufo.card.util.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class GamePanel extends JPanel {
    /**
     * 游戏画面宽度
     */
    private int width;

    /**
     * 游戏画面高度
     */
    private int height;

    /**
     * 游戏画面背景图
     */
    private Image bg;

    /**
     * 牌的宽度
     */
    private int cardWidth;

    /**
     * 牌的高度
     */
    private int cardHeight;

    /**
     * 已发的牌开始显示位置X坐标
     */
    private int cardStartPosX;

    /**
     * 已发的牌开始显示位置Y坐标
     */
    private int cardStartPosY;

    /**
     * 容纳所有54张扑克牌的集合
     */
    private List<Card> allCardList = new ArrayList<>();

    /**
     * 存放玩家当前有的牌
     */
    private List<Card> cardList = new ArrayList<>();

    /**
     * 玩家当前出的牌开始显示位置X坐标
     */
    private int currentPlayedCardStartX;

    /**
     * 玩家当前出的牌开始显示位置Y坐标
     */
    private int currentPlayedCardStartY;

    /**
     * 存放玩家当前打出的牌
     */
    private List<Card> currentPlayedCardList = new ArrayList<>();

    private Map<String, Image> imageMap = new HashMap<>();

    private Map<String, Integer> charValueMap = new HashMap<>();

    private JButton redealCardBtn = new JButton("重新发牌");

    {
        charValueMap.put("A", 14);charValueMap.put("K", 13);charValueMap.put("Q", 12);charValueMap.put("J", 11);
        charValueMap.put("10", 10);charValueMap.put("9", 9);charValueMap.put("8", 8);charValueMap.put("7", 7);
        charValueMap.put("6", 6);charValueMap.put("5", 5);charValueMap.put("4", 4);charValueMap.put("3", 3);
        charValueMap.put("2", 15);
    }

    public GamePanel(){
        initImageMap();
        initCards();
        dealCard(17);
        initSize();
        this.bg = new ImageIcon(Util.getProjectDir() + "/images/card/background/bg2.jpg").getImage();
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int button = e.getButton();//1: 鼠标左键  3：鼠标右键
                if(button == 1){ //选派（反选牌）
                    int index = getCard(e.getPoint());
                    if(index > -1){
                        //调整计算误差
                        if(index == GamePanel.this.cardList.size()){
                            index = GamePanel.this.cardList.size() - 1;
                        }
                        boolean selected = GamePanel.this.cardList.get(index).isSelected();
                        GamePanel.this.cardList.get(index).setSelected(!selected);
                    }
                }else if(button == 3){ //出牌
                    List<Card> selectedCards = GamePanel.this.cardList.stream().filter(card -> card.isSelected()).collect(Collectors.toList());
                    GamePanel.this.currentPlayedCardList.clear();
                    GamePanel.this.currentPlayedCardList.addAll(selectedCards);
                    GamePanel.this.cardList.removeAll(selectedCards);
                    calCurrentPlayedCardStartPosXY();
                    calCardStartPosXY();
                }
                GamePanel.this.repaint();
            }
        });

        this.add(redealCardBtn);
        redealCardBtn.setFocusPainted(false);
        redealCardBtn.addActionListener(e -> {
            GamePanel.this.cardList.clear();
            dealCard(17);
            GamePanel.this.repaint();
        });
    }

    /**
     * 初始化界面大小
     */
    private void initSize(){
        this.width = (int) (Util.getScreenSize().width * 0.8);
        this.height = (int) (this.width * 0.55);

        this.cardWidth = this.width / 25;
        this.cardHeight = (int) (this.cardWidth * 1.5);
        calCardStartPosXY();
        calCurrentPlayedCardStartPosXY();
    }

    /**
     * 计算发给用户的牌的显示位置
     */
    private void calCardStartPosXY(){
        this.cardStartPosX = (this.width - ((this.cardList.size()-1) * (this.cardWidth / 2) + this.cardWidth)) / 2;
        this.cardStartPosY = this.height - (this.cardHeight + 30);
    }

    /**
     * 计算发给用户的牌的显示位置
     */
    private void calCurrentPlayedCardStartPosXY(){
        this.currentPlayedCardStartX = (this.width - ((this.currentPlayedCardList.size()-1) * (this.cardWidth / 2) + this.cardWidth)) / 2;
        this.currentPlayedCardStartY = this.cardStartPosY - (this.cardHeight + 30);
    }

    private void initImageMap(){
        PictureType[] pictureTypes = PictureType.values();
        String[] values = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        for(PictureType pictureType : pictureTypes){
            if("JOKER".equals(pictureType.getCode())){
                continue;
            }
            for(String v : values){
                String imageName = pictureType.getCode() + v;
                this.imageMap.put(imageName, new ImageIcon(Util.getProjectDir() + "/images/card/card/" + imageName + ".png").getImage());
            }
        }
        this.imageMap.put("JOKER0", new ImageIcon(Util.getProjectDir() + "/images/card/card/JOKER0.png").getImage());
        this.imageMap.put("JOKER1", new ImageIcon(Util.getProjectDir() + "/images/card/card/JOKER1.png").getImage());
    }

    /**
     * 初始化一副牌
     */
    private void initCards(){
        PictureType[] pictureTypes = PictureType.values();
        String[] values = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        for(PictureType pictureType : pictureTypes){
            if("JOKER".equals(pictureType.getCode())){
                continue;
            }
            for(String v : values){
                Card card = new Card(pictureType, v);
                card.setImage(imageMap.get(pictureType.getCode() + v));
                card.setSortValue(charValueMap.get(v));
                allCardList.add(card);
            }
        }
        Card joker0 = new Card(null, "0");
        joker0.setImage(imageMap.get("JOKER0"));
        joker0.setSortValue(9000001);
        Card joker1 = new Card(null, "1");
        joker1.setImage(imageMap.get("JOKER1"));
        joker1.setSortValue(9000002);
        allCardList.add(joker0);
        allCardList.add(joker1);
    }

    /**
     * 发牌
     */
    private void dealCard(int count){
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(set.size()<count){
            int i = random.nextInt(allCardList.size());
            set.add(i);
        }
        set.forEach(i -> {
            Card card = this.allCardList.get(i);
            Card c = new Card();
            BeanUtils.copyProperties(card, c);
            this.cardList.add(c);
        });
        this.cardList.sort((card1,card2) -> card2.getSortValue() - card1.getSortValue());
    }

    /**
     * 根据鼠标点击位置，返回指定的牌的index
     */
    private int getCard(Point point){
        if(this.cardList.isEmpty()){
            return -1;
        }
        int clickedX = point.x;
        int clickedY = point.y;
        if(clickedX>this.cardStartPosX &&
            clickedX<(this.cardStartPosX + (this.cardList.size()-1)*(this.cardWidth/2)+this.cardWidth)){
            if(clickedY>this.cardStartPosY && clickedY<this.cardStartPosY+this.cardHeight){
                int index = (clickedX - this.cardStartPosX) / (this.cardWidth / 2);
                System.out.println("index: " + index);
                return index;
            }
        }
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintCards(g);
        paintCurrentPlayedCards(g);
    }

    private void paintBackground(Graphics g){
        g.drawImage(bg, 0, 0, this.width, this.height, 0, 0, bg.getWidth(null), bg.getHeight(null), null);
    }

    /**
     * 绘制玩家手中的牌
     */
    private void paintCards(Graphics g){
        for(int i=0; i<cardList.size(); i++){
            Card card = cardList.get(i);
            boolean selected = card.isSelected();
            Image cardImage = card.getImage();
            if(selected){
                g.drawImage(cardImage, this.cardStartPosX + (i * this.cardWidth / 2), this.cardStartPosY - 30, this.cardStartPosX + (i * this.cardWidth / 2) + this.cardWidth, this.cardStartPosY - 30 + this.cardHeight, 0, 0, cardImage.getWidth(null), cardImage.getHeight(null), null);
            }else{
                g.drawImage(cardImage, this.cardStartPosX + (i * this.cardWidth / 2), this.cardStartPosY, this.cardStartPosX + (i * this.cardWidth / 2) + this.cardWidth, this.cardStartPosY + this.cardHeight, 0, 0, cardImage.getWidth(null), cardImage.getHeight(null), null);
            }
        }
    }

    /**
     * 绘制玩家当前已打出的牌
     */
    private void paintCurrentPlayedCards(Graphics g){
        for(int i=0; i<currentPlayedCardList.size(); i++){
            Card card = currentPlayedCardList.get(i);
            Image cardImage = card.getImage();
            g.drawImage(cardImage, this.currentPlayedCardStartX + (i * this.cardWidth / 2), this.currentPlayedCardStartY, this.currentPlayedCardStartX + (i * this.cardWidth / 2) + this.cardWidth, this.currentPlayedCardStartY + this.cardHeight, 0, 0, cardImage.getWidth(null), cardImage.getHeight(null), null);
        }
    }

    /**
     * 判断牌型:
     * 判断玩家打出的牌是单张，对子，三带一，三带二，顺子等等情况
     */
    private void judgeCardType(List<Card> cards){
        int count = cards.size();
        if(count == 1){ //单张

        }else if(count == 2){ //对子或王炸qazxcswAZWa

        }else if(count == 3){ //三不带

        }else if(count == 4){ //炸弹 或 三带一

        }else if(count == 5){ //顺子 或者三带二

        }else if(count == 6){ //顺子 或者 连队 或者 四带二 或者 飞机

        }
    }
}
