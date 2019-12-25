package personal.wt.ufo.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeNode> snakeNodeList;

    private Direction direction;

    public static Snake initSnake(){
        Snake snake = new Snake();
        SnakeNode node1 = new SnakeNode(Color.RED, new Point(9, 10), Direction.UP);
        node1.setHeader(true);
        SnakeNode node2 = new SnakeNode(Color.PINK, new Point(9, 11), Direction.UP);
        SnakeNode node3 = new SnakeNode(Color.PINK, new Point(9, 12), Direction.UP);
        SnakeNode node4 = new SnakeNode(Color.PINK, new Point(9, 13), Direction.UP);
        SnakeNode node5 = new SnakeNode(Color.PINK, new Point(9, 14), Direction.UP);
        SnakeNode node6 = new SnakeNode(Color.PINK, new Point(9, 15), Direction.UP);
        SnakeNode node7 = new SnakeNode(Color.PINK, new Point(9, 16), Direction.UP);
        List<SnakeNode> snakeNodeList = new ArrayList<>(7);
        snakeNodeList.add(node1);
        snakeNodeList.add(node2);
        snakeNodeList.add(node3);
        snakeNodeList.add(node4);
        snakeNodeList.add(node5);
        snakeNodeList.add(node6);
        snakeNodeList.add(node7);
        snake.setSnakeNodeList(snakeNodeList);
        return snake;
    }

    public Direction getDirection() {
        return this.snakeNodeList.get(0).getDirection();
    }

    public List<SnakeNode> getSnakeNodeList() {
        return snakeNodeList;
    }

    public void setSnakeNodeList(List<SnakeNode> snakeNodeList) {
        this.snakeNodeList = snakeNodeList;
    }

    public void move(){

        //前一个节点的当前坐标是后一个节点的下一个坐标
        Point[] points = new Point[snakeNodeList.size()];
        for(int i=0; i<snakeNodeList.size(); i++){
            Point p = snakeNodeList.get(i).getCurrentPoint();
            points[i] = new Point(p.x, p.y);
        }

        Direction[] directions = new Direction[snakeNodeList.size()];
        for(int i=0; i<snakeNodeList.size(); i++){
            Direction direction = snakeNodeList.get(i).getDirection();
            directions[i] = direction;
        }

        for(int i=1; i<snakeNodeList.size();i++){
            SnakeNode node = snakeNodeList.get(i);
            node.getCurrentPoint().x = points[i-1].x;
            node.getCurrentPoint().y = points[i-1].y;
            node.setDirection(directions[i-1]);
        }

        SnakeNode firstNode = this.snakeNodeList.get(0);
        Point currentPoint = firstNode.getCurrentPoint();
        switch (this.getDirection()){
            case UP:{
                currentPoint.y = currentPoint.y - 1;
                break;
            }
            case DOWN:{
                currentPoint.y = currentPoint.y + 1;
                break;
            }
            case LEFT:{
                currentPoint.x = currentPoint.x - 1;
                break;
            }
            case RIGHT:{
                currentPoint.x = currentPoint.x + 1;
                break;
            }
        }
    }

    /**
     * 判断是否到达某个点
     * @param point
     */
    public boolean isArrivedAt(Point point){
        return this.snakeNodeList.get(0).getCurrentPoint().equals(point);
    }
}
