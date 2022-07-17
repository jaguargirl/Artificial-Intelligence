package ContinuousOptimization;

import static ContinuousOptimization.ContinuousOptimization.sumOfDifferentPower;
import static ContinuousOptimization.ContinuousOptimization.toDecimal;

public class SimulatedAnnealing {
    public int b;

    public SimulatedAnnealing(int b){
        this.b=b;
    }
    public double run(String randomStartSolution){
        System.out.println("Simulated Annealing");
        int t=0;
        float T=50;
        String potentialSolution= randomStartSolution;
        System.out.println("Global start solution : "+randomStartSolution+" Value : "+sumOfDifferentPower(randomStartSolution));
        while(T>0){
            int z=0;
            System.out.println("Step "+(t+1)+": Current solution: "+potentialSolution+" Value: "+sumOfDifferentPower(potentialSolution));
            while(z<10){
                String neighbour=chooseNeighboor(potentialSolution);
                if(sumOfDifferentPower(neighbour)>sumOfDifferentPower(potentialSolution)){
                    potentialSolution=neighbour;
                }else if(Math.random()<calcP(neighbour,potentialSolution,T)){
                    potentialSolution=neighbour;
                }
                z++;
            }
            System.out.println("Step "+(t+1)+".1: After choosing neighbour:  "+potentialSolution+" Value: "+sumOfDifferentPower(potentialSolution));
            T-=0.1;
            t++;
        }
        System.out.println("Final binary solution: "+potentialSolution+"\n Final decimal solution: "+toDecimal(potentialSolution)+"\n Value: "+sumOfDifferentPower(potentialSolution));
        return sumOfDifferentPower(potentialSolution);
    }
    private String chooseNeighboor(String sol){
        StringBuilder temp= new StringBuilder(sol);
        int poz = (int) (Math.random() * b) ;
        int to=Integer.parseInt(String.valueOf(temp.charAt(poz)));
        if(to==0){
            to=1;
        }else{
            to=0;
        }
        temp.setCharAt(poz,String.valueOf(to).charAt(0));
        return temp.toString();
    }
    private double calcP(String neighbour, String solution, float T){
        double val1=sumOfDifferentPower(neighbour);
        double val2=sumOfDifferentPower(solution);
        double rsp=-Math.abs(val2-val1) /T;
        return Math.exp(rsp);
    }
}
