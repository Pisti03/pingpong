package szakdolgozat.istvan.pingpong;

/**
 * Created by Pisti on 2017. 03. 24..
 */

public class Player {
    private int point;
    private double x,y;
    private double height, width;
    private int color;
    private double maxSpeed;

    public Player(int point, double x, double y, double width, double height, int color) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public Player(int point, double x, double y, double width, double height, double maxSpeed, int color) {
        this.point = point;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.maxSpeed = maxSpeed;
        this.color = color;
    }

    public Player(double x, double y, double width, double height, int color) {
        this.point = 0;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public int getPoint() {
        return point;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public int getColor() {
        return color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
