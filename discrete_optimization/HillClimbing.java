package optimization;
import java.util.ArrayList;

import static optimization.Optimization.calc_cost;

public class HillClimbing {
    private final int n;
    private final ArrayList<Integer> start_solution;

   public HillClimbing(int n,ArrayList<Integer> start_solution)
    {
        this.n=n;
        this.start_solution=start_solution;
    }

    public int run() {
        System.out.println("Hill Climbing");
        ArrayList<Integer> solutie_potentiala= (ArrayList<Integer>) start_solution.clone();
        ArrayList<Integer> test;
        int cost_potential=calc_cost(solutie_potentiala);
        int z=0;
        while(z<50){
            System.out.print("Step "+(z+1)+": Old solution: ");
            for (Integer integer : solutie_potentiala) {
                System.out.print(integer + " ");
            }
            System.out.println(" Value: "+calc_cost(solutie_potentiala));
            test=this.check_best(solutie_potentiala,cost_potential);
            System.out.print("Step "+(z+1)+".1: After checking best neighbour:  ");
            for(Integer integer : test){
                System.out.print(integer+" ");
            }
            System.out.println(" Value: "+calc_cost(test));
            boolean a=true;
            for(int i=0;i<n;i++){
                if(!solutie_potentiala.get(i).equals(test.get(i))){
                    a=false;
                    break;
                }
            }
            if(a){
                break;
            }else{
                cost_potential=calc_cost(test);
                solutie_potentiala= (ArrayList<Integer>) test.clone();
            }
            z++;
        }
        System.out.print("Final solution: ");
        for (Integer integer : solutie_potentiala) {
            System.out.print(integer + " ");
        }
        System.out.println("Final solution value: "+cost_potential);
        return cost_potential;
    }


    public ArrayList<Integer> check_best(ArrayList<Integer> solutie_st, int cost_st){
        int cost_final=cost_st;
        ArrayList<Integer> solutie_finala= (ArrayList<Integer>) solutie_st.clone();
        int m=solutie_st.size();
        int i=1;
        while(i<m){
            ArrayList<Integer> temp= (ArrayList<Integer>) solutie_st.clone();
            int value=temp.get(i);
            for(int j=i+1;j<m;j++){
                int value2=temp.get(j);
                temp.set(i,value2);
                temp.set(j,value);
                int cost=calc_cost(temp);
                if(cost<cost_final){
                    cost_final=cost;
                    solutie_finala= (ArrayList<Integer>) temp.clone();
                }
                temp= (ArrayList<Integer>) solutie_st.clone();
            }
            i++;
        }
        return solutie_finala;
    }
}