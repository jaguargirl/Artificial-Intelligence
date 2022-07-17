package optimization;

public class Permutation {
    private int from;
    private int to;
    private int cost;

    public Permutation(int from, int to) {
        this.from=from;
        this.to=to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permutation permutation = (Permutation) o;
        return from == permutation.from &&
                to == permutation.to;
    }

    @Override
    public String toString() {
        return "Permutare{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}
