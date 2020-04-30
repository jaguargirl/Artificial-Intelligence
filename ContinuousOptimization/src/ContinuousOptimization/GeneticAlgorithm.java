package ContinuousOptimization;

import java.util.ArrayList;

import static ContinuousOptimization.ContinuousOptimization.*;

public class GeneticAlgorithm {
    public ArrayList<Individual> initialPopulation=new ArrayList<>();
    public int b;
    public int precision;
    public GeneticAlgorithm(int b,int precision){
        this.b=b;
        this.precision=precision;
    }
    public double run(){
        double mutationRate=0.01;
        double crossOverRate=0.25;
        int numberOfGenerations=500;
        ArrayList<Double> ll=new ArrayList<Double>();
        generateInitialPopulation();
        ArrayList<Individual> population=(ArrayList<Individual>)initialPopulation.clone();
        System.out.println("First generation");
        Individual bestInd=population.get(0);
        for(Individual individual:population){
            System.out.println(individual.getEval());
            if(individual.getEval()>bestInd.getEval()){
                bestInd=individual;
            }
        }
        System.out.println("Initial best individual\n Binary string: "+bestInd.getBitString()+"\n Decimal number: "+toDecimal(bestInd.getBitString())+"\n Value : "+bestInd.getEval());
        while(numberOfGenerations!=0){
            double totalValue=0;
            for (Individual individual : population) {
                totalValue += individual.getEval();
            }
            //setting probabilities
            for(Individual individual: population){
                double p=individual.getEval();
                if(p==0.0){
                    individual.setProbability(0.001);//to avoid NaN
                }else {
                    p=p/totalValue;
                    p=Math.round(p*Math.pow(10,precision))/Math.pow(10,precision);
                    individual.setProbability(p);
                }
            }
            //Sort population
            for(int i=0;i<population.size();i++) {
                for (int j = i; j < population.size(); j++) {
                    if (population.get(j).getProbability()>population.get(i).getProbability()) {
                        Individual temp = population.get(i);
                        population.set(i, population.get(j));
                        population.set(j, temp);
                    }
                }
            }
            ArrayList<Individual> newPopulation=new ArrayList<>();
            while(newPopulation.size()!=50) {
                double r = Math.random();
                double sum = 0;
                int i = 0;
                while (i < population.size()) {
                    sum += population.get(i).getProbability();
                    if (sum>r) {
                        newPopulation.add(population.get(i));
                        break;
                    }
                    i++;
                }
            }
            population.clear();
            population=(ArrayList<Individual>)newPopulation.clone();
            //Crossover
            for(int i=0;i<population.size()-1;i+=2){
                //int j = (int)(Math.random() * b) ;
                if(i<crossOverRate) {
                    String first = population.get(i).getBitString();
                    String second = population.get(i + 1).getBitString();
                    int rand = (int) (Math.random() * b - 1) + 1;
                    String f1 = first.substring(0,rand);
                    String s1 = second.substring(0,rand);
                    String f2 = first.substring(rand);
                    String s2 = second.substring(rand);
                    String f3 =f1+s2;
                    String s3 = s1+f2;
                    population.add(new Individual(f3));
                    population.add(new Individual(s3));
                }
            }
            //Mutation
            for(Individual individual:population){
                double r=Math.random();
                if(r<mutationRate){
                    StringBuilder temp= new StringBuilder(individual.getBitString());
                    int poz = (int) (Math.random() * b) ;
                    int to=Integer.parseInt(String.valueOf(temp.charAt(poz)));
                    if(to==0){
                        to=1;
                    }else{
                        to=0;
                    }
                    temp.setCharAt(poz,String.valueOf(to).charAt(0));
                    individual.setBitString(temp.toString());
                }
            }
            numberOfGenerations--;
        }
        Individual bestIndividual=population.get(0);
        System.out.println("Last generation");
        for(Individual individual:population){
            System.out.println(individual.getEval());
            if(individual.getEval()>bestIndividual.getEval()){
                bestIndividual=individual;
            }
        }
        System.out.println("Final best individual \n Binary string: "+bestIndividual.getBitString()+"\n Decimal number: "+toDecimal(bestIndividual.getBitString())+"\n Value : "+bestIndividual.getEval());
        return bestIndividual.getEval();
    }
    public void generateInitialPopulation(){
        while(initialPopulation.size()<50){
            Individual temp=new Individual(randomBinaryStartSolution());
            initialPopulation.add(temp);
        }
    }
}
