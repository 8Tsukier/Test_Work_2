package ua.telesens.io;

public class Route<T> {
    private T from;
    private T to;
    private double distance;
    private int time;

    public Route() {
    }

    public Route(T from, T end, double distance, int hours, int mins) {
        super();
        this.from = from;
        this.to = end;
        this.distance = distance;
        time = hours * 60 + mins;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public int getHours() {
        return time / 60;
    }

    public int getMins() {
        return time % 60;
    }

    public void setTime(int hours, int mins) {
        time = hours * 60 + mins;
    }

    public T getFrom() {
        return from;
    }

    public void setFrom(T from) {
        this.from = from;
    }

    public T getTo() {
        return to;
    }

    public void setTo(T to) {
        this.to = to;
    }

    public double speed() {
        return (distance / time) * 60;
    }

    @Override
    public String toString() {
        return from + " - " + to + ". Расстояние: " + distance +
                " км. Время: " + getHours() + " ч. " + getMins() + " м. Скорость: " + speed() + " км/ч.";
    }

}