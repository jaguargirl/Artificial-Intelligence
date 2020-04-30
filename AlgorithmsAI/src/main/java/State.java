
public class State {

    private int misionarsL;
    private int canibalsL;
    private int misionarsR;
    private int canibalsR;
    private String sheep;
    private State father = null;
    private int nr;

    public State(int misionarsL, int canibalsL, int misionarsR, int canibalsR, String sheep) {
        this.misionarsL = misionarsL;
        this.canibalsL = canibalsL;
        this.misionarsR = misionarsR;
        this.canibalsR = canibalsR;
        this.sheep = sheep;
        this.nr=misionarsL+canibalsL;
    }
    
    public int nr(){
        return nr;
    }
    
    public void setFather(State father) {
        this.father = father;
    }

    public State getFather() {
        return father;
    }

    public int getMisionarsL() {
        return misionarsL;
    }

    public void setMisionarsL(int misionarsL) {
        this.misionarsL = misionarsL;
    }

    public int getCanibalsL() {
        return canibalsL;
    }

    public void setCanibalsL(int canibalsL) {
        this.canibalsL = canibalsL;
    }

    public String getSheep() {
        return sheep;
    }

    public void setSheep(String sheep) {
        this.sheep = sheep;
    }

    public boolean isValid() {
        int nrML = this.getMisionarsL();
        int nrCL = this.getCanibalsL();
        int nrMR = this.getMisionarsR();
        int nrCR = this.getCanibalsR();
        if (nrCL > nrML && nrML != 0) {
            return false;
        }
        if (nrCR > nrMR && nrMR != 0) {
            return false;
        }
        if (nrCL > 3 || nrML > 3) {
            return false;
        }
        if (nrCR > 3 || nrMR > 3) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.misionarsL != other.misionarsL) {
            return false;
        }
        if (this.canibalsL != other.canibalsL) {
            return false;
        }
        if (this.sheep != other.sheep) {
            return false;
        }
        if (this.misionarsR != other.misionarsR) {
            return false;
        }
        if (this.canibalsR != other.canibalsR) {
            return false;
        }
        return true;
    }

    public int getMisionarsR() {
        return misionarsR;
    }

    public void setMisionarsR(int misionarsR) {
        this.misionarsR = misionarsR;
    }

    public int getCanibalsR() {
        return canibalsR;
    }

    public void setCanibalsR(int canibalsR) {
        this.canibalsR = canibalsR;
    }

    public State minus(Transition tr) {
        int nrML = this.getMisionarsL() - tr.getNrMisionars();
        int nrCL = this.getCanibalsL() - tr.getNrCanibals();
        int nrMR = this.getMisionarsR() + tr.getNrMisionars();
        int nrCR = this.getCanibalsR() + tr.getNrCanibals();
        return new State(nrML, nrCL, nrMR, nrCR, "right");
    }

    public State plus(Transition tr) {
        int nrML = this.getMisionarsL() + tr.getNrMisionars();
        int nrCL = this.getCanibalsL() + tr.getNrCanibals();
        int nrMR = this.getMisionarsR() - tr.getNrMisionars();
        int nrCR = this.getCanibalsR() - tr.getNrCanibals();
        return new State(nrML, nrCL, nrMR, nrCR, "left");
    }

    @Override
    public String toString() {
        return this.getMisionarsL() + "  " + this.getCanibalsL() + "\t" + this.getMisionarsR() + "  " + this.getCanibalsR() + "\t" + this.getSheep();
    }
}
