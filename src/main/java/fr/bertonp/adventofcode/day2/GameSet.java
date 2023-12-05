package fr.bertonp.adventofcode.day2;

public class GameSet {
    private int blue;

    private int green;

    private int red;

    public GameSet() {
    }

    public GameSet(int blue, int green, int red) {
        this.blue = blue;
        this.green = green;
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    @Override
    public String toString() {
        return "GameSet{" +
                "blue=" + blue +
                ", green=" + green +
                ", red=" + red +
                '}';
    }
}
