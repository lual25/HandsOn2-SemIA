/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handson2;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author Luis
 */
public class HandsOn2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*String[] arreglo = produceInitialRandomPopulation(10,0,0,0);
        int[] fitness = fitness(arreglo);
        int[] p= getProbabilities(fitness);
        for(int i=0; i<10; i++)
        {
        String[] parents = selectParents(arreglo, p, 2);
         System.out.println(arreglo[i]+" F="+fitness[i]+" p1="+parents[0]+" p2="+parents[1]);*/
        /*String[] population = produceInitialRandomPopulation(10, 0, 0, 0);
        for(int i=0; i<50; i++)
        {
            System.out.println("-----Generation: "+i+"-----");
            for(String individual:population)
            {
                System.out.println(individual);
            }
            System.out.println("Fitness total: "+ globalFitness(population));
            population = produceNewPopulation(population, 60);
        }*/
        Population p = new Population(10);
        p.printPopulaton();
        /*for(int i=0; i<50; i++)
        {
            p.produceNewPopulation(50);
            p.printPopulaton();
        }*/
    }
    static String[]  produceInitialRandomPopulation(int populationSize, int elitismValue, float crossoverRate, float mutationRate)
    {
        String objects[];
        objects = new String[populationSize];
        for( int i=0; i<populationSize; i++)
        {
            objects[i]= randomObject();
        }
        return objects;
    }
    static String randomObject()
    {
        String object = "";
        for(int i=0; i<10; i++)
        {
            object+= String.valueOf(ThreadLocalRandom.current().nextInt(0, 1 + 1)); 
        }
        return object;
    }
    static int[] fitness(String[] array)
    {
        int[] fitnessArray = new int[array.length];
        int indice = 0;
        for (String Individual : array) {
            int fitness = 0;
            for(int j=0; j<Individual.length(); j++)
                if(Individual.charAt(j) == '1')
                {
                    fitness++;
                }
            fitnessArray[indice] = fitness;
            indice++;
        }
        return fitnessArray;
    }
    static int[] getProbabilities(int[] fitness)
    {
        int[] probabilities = new int[fitness.length];
        int sum=0;
        int q=0;
        int p;
        for(int i = 0; i<fitness.length; i++)
            sum+= fitness[i];
        for(int i= 0; i<fitness.length; i++)
        {
            p = (fitness[i]*100)/sum;
            q+=p;
            probabilities[i]=q;
        }
        return probabilities;
    }
    static String[] selectParents(String[] population, int[] probabilities, int num)
    {
        String[] parents = new String[num];
        for(int n=0; n<num; n++)
        {
            int random = ThreadLocalRandom.current().nextInt(0, probabilities[probabilities.length-1] + 1);
            for(int i=0; i<probabilities.length; i++)
            {
                if(random<=probabilities[i])
                {
                    parents[n]=population[i];
                    break;
                }
            }
        }
        return parents;
    }
    static String[] produceNewPopulation(String[] currentPopulation, int crossoverRate)
    {
        String[] newPopulation = new String[currentPopulation.length];
        int[] p = getProbabilities(fitness(currentPopulation));
        for(int i=0; i<currentPopulation.length; i++)
        {
            if(crossoverRate>ThreadLocalRandom.current().nextInt(0, 100 + 1))
            {
                String secondParent = selectParents(currentPopulation, p, 1)[0];
                String offspring = crossover(currentPopulation[i], secondParent);
                newPopulation[i] = offspring;
            }
            else
                newPopulation[i] = currentPopulation[i];
        }
        return newPopulation;
        
    }

    
    static String crossover(String parent1, String parent2)
    {
        String children;
        int crossoverpoint = ThreadLocalRandom.current().nextInt(0, parent1.length() + 1);
        children = parent1.substring(0, crossoverpoint) + parent2.substring(crossoverpoint);
        //children[1] = parent2.substring(0, crossoverpoint) + parent1.substring(crossoverpoint);
        return children;
    }
    static int globalFitness(String[] array)
    {
        int[] fitness = fitness(array);
        int total=0;
        for(int i=0; i<fitness.length; i++)
            total+=fitness[i];
        return total;
    }
}


