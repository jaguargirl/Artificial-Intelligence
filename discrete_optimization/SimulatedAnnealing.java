package optimization;

import java.util.ArrayList;

import static optimization.Optimization.calc_cost;

public class SimulatedAnnealing {
    private int n;
    private ArrayList<Integer> start_solution;
    public SimulatedAnnealing(int n, ArrayList<Integer> start_solution){
        this.n=n;
        this.start_solution=start_solution;
    }
    public int run(){
        System.out.println("Simulated Annealing");
        int t=0;
        float T=2;
        ArrayList<Integer> solutie_potentiala= (ArrayList<Integer>) start_solution.clone();
        while(T>0){
            int z=0;
            System.out.print("Step "+(t+1)+": Old solution: ");
            for (Integer integer : solutie_potentiala) {
                System.out.print(integer + " ");
            }
            System.out.print(" Value: "+calc_cost(solutie_potentiala));
            System.out.println("");
            while(z<10){
                ArrayList<Integer> neighbour=chooseNeighboor(solutie_potentiala);
                if(calc_cost(neighbour)<calc_cost(solutie_potentiala)){
                    solutie_potentiala= (ArrayList<Integer>) neighbour.clone();
                }else if(Math.random()<calcP(neighbour,solutie_potentiala,T)){
                    solutie_potentiala= (ArrayList<Integer>) neighbour.clone();
                }
                z++;
            }
            System.out.print("Step "+(t+1)+".1: After changing neighbour:  ");
            for(Integer integer : solutie_potentiala){
                System.out.print(integer+" ");
            }
            System.out.print(" Value: "+calc_cost(solutie_potentiala));
            System.out.println("");
            T-=0.1;
            t++;
        }
        System.out.print("Final solution: ");
        for(Integer integer : solutie_potentiala){
            System.out.print(integer+" ");
        }
        System.out.println(" Value: "+calc_cost(solutie_potentiala));
        return calc_cost(solutie_potentiala);
    }
    private ArrayList<Integer> chooseNeighboor(ArrayList<Integer> solution){
        ArrayList<Integer> temp= (ArrayList<Integer>) solution.clone();
        int poz1 = (int) (Math.random() * ((n - 3) + 1)) + 2;
        int poz2;
        do {
            poz2 = (int) (Math.random() * ((n - 3) + 1)) + 2;
        } while (poz1 == poz2);
        int value1=temp.get(poz1);
        int value2=temp.get(poz2);
        temp.set(poz1,value2);
        temp.set(poz2,value1);
        return temp;
    }
    private double calcP(ArrayList<Integer> neighbour, ArrayList<Integer> solution, float T){
        int val1=calc_cost(neighbour);
        int val2=calc_cost(solution);
        float rsp=-(float)Math.abs(val2-val1)/T;
        return Math.exp(rsp);
    }
}
