package ContinuousOptimization;

import static ContinuousOptimization.ContinuousOptimization.sumOfDifferentPower;

public class Individual {
    private String bitString;
    private double eval;
    private double probability;
    public Individual(String bitString){
        this.bitString=bitString;
        this.eval=sumOfDifferentPower(bitString);
    }
    public void setProbability(double p){
        this.probability=p;
    }
    public String getBitString(){
        return this.bitString;
    }
    public double getEval(){
        return this.eval;
    }
    public double getProbability(){
        return this.probability;
    }
    public void setBitString(String s){
        this.bitString=s;
        setEval(sumOfDifferentPower(bitString));
    }
    public void setEval(double eval){
        this.eval=eval;
    }

}
