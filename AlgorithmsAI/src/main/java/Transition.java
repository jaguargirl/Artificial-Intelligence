public class Transition {
    private int nrMisionars;
    private int nrCanibals;

    public Transition(int nrMisionars, int nrCanibals) {
        this.nrMisionars = nrMisionars;
        this.nrCanibals = nrCanibals;
    }

    public int getNrMisionars() {
        return nrMisionars;
    }

    public void setNrMisionars(int nrMisionars) {
        this.nrMisionars = nrMisionars;
    }

    public int getNrCanibals() {
        return nrCanibals;
    }

    public void setNrCanibals(int nrCanibals) {
        this.nrCanibals = nrCanibals;
    }
}
