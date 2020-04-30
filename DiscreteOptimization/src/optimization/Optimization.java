package optimization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Optimization {
    private static final ArrayList<Permutation> permutari=new ArrayList<>();
    private static int len;
    public static int calc_cost(ArrayList<Integer> solutie){
        int cost=0;
        for(int i=0;i<len;i++){
            Permutation perm_temp=permutari.get(i);
            for(int j=0;j<solutie.size()-1;j++){
                Permutation temp=new Permutation(solutie.get(j),solutie.get(j+1));
                if(perm_temp.equals(temp)){
                    cost+=perm_temp.getCost();
                }
            }
        }
        return cost;
    }
    public static void main(String[] args){
        int i=0;
        int nr=0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("bavaria_map.txt"));
            while(true){
                String line=bf.readLine();
                if(line==null){
                    break;
                }
                String[] temp_v=line.split(" ");
                int j=i+1;
                while(j<temp_v.length) {
                        Permutation temp_p = new Permutation(i+1, j+1);
                        Permutation sim = new Permutation(j+1, i+1);
                        sim.setCost(Integer.parseInt(temp_v[j]));
                        temp_p.setCost(Integer.parseInt(temp_v[j]));
                        permutari.add(temp_p);
                        permutari.add(sim);
                        j++;
                }
                i++;
            }
            nr=i;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(0);
        }
        len=permutari.size();
        if(len==0){
            System.exit(0);
        }
        ArrayList<Integer> start_solution=randomStartSolution(nr);
        System.out.print("Random start solution :");
        for(i=0;i<start_solution.size();i++){
            System.out.print(start_solution.get(i)+" ");
        }
        System.out.println("Value: "+calc_cost(start_solution));

        HillClimbing hc=new HillClimbing(nr,randomStartSolution(nr));
        hc.run();

        SimulatedAnnealing sa=new SimulatedAnnealing(nr,randomStartSolution(nr));
        sa.run();

        //Mai jos am comentat partea unde am calculat statisticile
        /*
        int n=30;
        ArrayList<Integer> statistics=new ArrayList<>();
        while(n!=0){
            HillClimbing hc=new HillClimbing(nr,randomStartSolution(nr));
            int rez=hc.run();
            statistics.add(rez);
            n--;
        }
        int sum=0;
        int min=statistics.get(0);
        int max=statistics.get(0);
        for(Integer data:statistics){
            if(data<min){
                min=data;
            }
            if(data>max){
                max=data;
            }
            sum+=data;
        }
        int mean=sum/statistics.size();
        int dev=0;
        for(Integer data:statistics){
            dev+=Math.pow(data-mean,2);
        }

        float stDev= (float) Math.sqrt(1.0/(statistics.size()*dev));
        System.out.println("Mean: "+mean);
        System.out.println("Min: "+min);
        System.out.println("Max: "+max);
        System.out.println("Standard Deviation: "+stDev);
        int x=30;
        ArrayList<Integer> statisticsSA=new ArrayList<>();
        while(x!=0){
            SimulatedAnnealing sa=new SimulatedAnnealing(nr,randomStartSolution(nr));
            int rez=sa.run();
            statisticsSA.add(rez);
            x--;
        }
        int sum=0;
        int min=statisticsSA.get(0);
        int max=statisticsSA.get(0);
        for(Integer data:statisticsSA){
            if(data<min){
                min=data;
            }
            if(data>max){
                max=data;
            }
            sum+=data;
        }
        int mean=sum/statisticsSA.size();
        int dev=0;
        for(Integer data:statisticsSA){
            dev+=Math.pow(data-mean,2);
        }

        float stDev= (float) Math.sqrt(1.0/(statisticsSA.size()*dev));
        System.out.println("Mean: "+mean);
        System.out.println("Min: "+min);
        System.out.println("Max: "+max);
        System.out.println("Standard Deviation: "+stDev);
        */
    }
    public static ArrayList<Integer> randomStartSolution(int nr){
        ArrayList<Integer> sol=new ArrayList<>();
        ArrayList<Integer> used =new ArrayList<>();
        sol.add(1);
        int i=1;
        while(i<nr) {
            int n;
            do {
                n = (int) (Math.random() * ((nr - 2) + 1)) + 2;
            } while (used.contains(n));
            sol.add(n);
            used.add(n);
            i++;
        }
        return sol;
    }
}
