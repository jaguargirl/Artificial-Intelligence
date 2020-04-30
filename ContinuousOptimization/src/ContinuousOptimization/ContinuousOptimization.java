package ContinuousOptimization;

import java.util.ArrayList;

public class ContinuousOptimization {
    public static int s=-1;
    public static int d=1;
    public static int b=0;
    public static int n=10;
    public static int precision=6;
    public static void main(String[] args){
        int nr= (int) ((d-s)*Math.pow(10,precision));
        while(nr>0){
            b+=1;
            nr=nr/2;
        }
        //Generate random start solution/*
        String randomStartSolution=randomBinaryStartSolution();
        //Codul pentru statistici
        /*int z=30;
        ArrayList<Double> list=new ArrayList<>();
        while(z!=0){
            HillClimbing hc=new HillClimbing();
            String sol=randomBinaryStartSolution();
            double rez=hc.run(sol);
            list.add(rez);
            z--;
        }
        double min=list.get(0);
        double max=list.get(0);
        double sum=0.0;
        for(Double re:list){
            if(re<min){
                min=re;
            }
            if(re>max){
                max=re;
            }
            sum+=re;
        }
        double mean=sum/list.size();
        double dev=0;
        for(Double re:list){
            dev+=Math.pow(re-mean,2);
        }
        double devSt=Math.sqrt(1.0/list.size()*dev);
        System.out.println("Mean: "+mean);
        System.out.println("Min: "+min);
        System.out.println("Max: "+max);
        System.out.println("Standard Deviation: "+devSt);*/
        /*int z=30;
        ArrayList<Double> list=new ArrayList<>();
        while(z!=0){
            SimulatedAnnealing sa=new SimulatedAnnealing(b);
            String sol=randomBinaryStartSolution();
            double rez=sa.run(randomStartSolution);
            list.add(rez);
            z--;
        }
        double min=list.get(0);
        double max=list.get(0);
        double sum=0.0;
        for(Double re:list){
            if(re<min){
                min=re;
            }
            if(re>max){
                max=re;
            }
            sum+=re;
        }
        double mean=sum/list.size();
        double dev=0;
        for(Double re:list){
            dev+=Math.pow(re-mean,2);
        }
        double devSt=Math.sqrt(1.0/list.size()*dev);
        System.out.println("Mean: "+mean);
        System.out.println("Min: "+min);
        System.out.println("Max: "+max);
        System.out.println("Standard Deviation: "+devSt);*/
        /*SimulatedAnnealing sa=new SimulatedAnnealing(b);
        sa.run(randomStartSolution);*/
        /*int z=30;
        ArrayList<Double> list=new ArrayList<>();
        while(z!=0){
            GeneticAlgorithm ga=new GeneticAlgorithm(b,precision);
            double rez=ga.run();
            list.add(rez);
            z--;
        }
        double min=list.get(0);
        double max=list.get(0);
        double sum=0.0;
        for(Double re:list){
            if(re<min){
                min=re;
            }
            if(re>max){
                max=re;
            }
            sum+=re;
        }
        double mean=sum/list.size();
        double dev=0;
        for(Double re:list){
            dev+=Math.pow(re-mean,2);
        }
        double devSt=Math.sqrt(1.0/list.size()*dev);
        System.out.println("Mean: "+mean);
        System.out.println("Min: "+min);
        System.out.println("Max: "+max);
        System.out.println("Standard Deviation: "+devSt);*/
        
        HillClimbing hc=new HillClimbing();
        hc.run(randomStartSolution);

        SimulatedAnnealing sa=new SimulatedAnnealing(b);
        sa.run(randomStartSolution);

        GeneticAlgorithm ga=new GeneticAlgorithm(b,precision);
        ga.run();
    }
    //function to optimize
    public static double sumOfDifferentPower(String potentialBinarySolution){
        double x=toDecimal(potentialBinarySolution);
        double sum=0;
        for(int i=1;i<n+1;i++){
            sum+=Math.pow(Math.abs(x),i+1);
        }
        return Math.round(sum*Math.pow(10,precision))/Math.pow(10,precision);
    }
    public static double toDecimal(String binaryNr){
        double xp=0;
        int len=b-1;
        int i=0;
        while(len!=0){
            xp+=Integer.parseInt(String.valueOf(binaryNr.charAt(i)))*Math.pow(2,len);
            len--;
            i++;
        }
        xp+=Integer.parseInt(String.valueOf(binaryNr.charAt(i)));
        double rsp=s+xp*(d-s)/(Math.pow(2,b)-1);
        return Math.round(rsp*Math.pow(10,precision))/Math.pow(10,precision);
    }
    public static String randomBinaryStartSolution(){
        StringBuilder solution= new StringBuilder();
        int i=0;
        while(i<b){
            int a=(int)(Math.random() * 2) ;
            solution.append(a);
            i++;
        }
        return solution.toString();
    }
}
