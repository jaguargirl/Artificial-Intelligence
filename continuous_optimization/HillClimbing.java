package ContinuousOptimization;

import java.util.ArrayList;

import static ContinuousOptimization.ContinuousOptimization.sumOfDifferentPower;
import static ContinuousOptimization.ContinuousOptimization.toDecimal;

public class HillClimbing {
    public HillClimbing(){
    }
    public double run(String randomStartSolution){
        //System.out.println("Hill Climbing");
        String potentialSolution=randomStartSolution;
        //System.out.println("Global start solution : "+randomStartSolution+" Value : "+sumOfDifferentPower(randomStartSolution));
        String test;
        int z=0;
        while(z<10) {
            //System.out.println("Step " + (z + 1) + ": Old solution: " + potentialSolution + " Value: " + sumOfDifferentPower(potentialSolution));
            test = this.check_best(potentialSolution);
            //System.out.println("Step " + (z + 1) + ".1: After checking best neighbour:  " + test + " Value: " + sumOfDifferentPower(potentialSolution));
            if (potentialSolution.equals(test)) {
                break;
            } else {
                potentialSolution = test;
            }
            z++;
        }
        //System.out.print("Final binary solution: "+potentialSolution+"\n Final decimal solution: "+toDecimal(potentialSolution)+"\nFinal solution value: "+sumOfDifferentPower(potentialSolution));
        return sumOfDifferentPower(potentialSolution);
    }

    public String check_best(String sol){
        String final_solution= sol;
        int m=final_solution.length();
        int i=0;
        while(i<m){
            StringBuilder temp=new StringBuilder(sol);
            int to=Integer.parseInt(String.valueOf(temp.charAt(i)));
            if(to==0){
                to=1;
            }
            else{
                to=0;
            }
            temp.setCharAt(i, String.valueOf(to).charAt(0));
            if(sumOfDifferentPower(temp.toString())>sumOfDifferentPower(final_solution)){
                final_solution=temp.toString();
            }
            i++;
        }
        return final_solution;
    }
}
