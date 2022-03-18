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
public final class Population {
    Individual[] population = new Individual[10];
    int[] probabilities = new int[10];
    int populationSize;
    int totalFitness;
    int generation=0;
    Population(int populationSize)
    {
        this.populationSize = populationSize;
        for(int i=0; i<populationSize; i++)
        {
            population[i] = new Individual();
        }
        getProbabilities();
    }
    void printPopulaton()
    {
        System.out.println("Gen: "+generation+" Global Fitness: "+totalFitness);
        //int i=0;
        for (Individual population1 : population) {
            population1.printIndividual();
            //System.out.println(probabilities[i]);
            //i++;
        }
        selectParent().printIndividual();
    }
    int selectGlobalFitness()
    {
        totalFitness=0;
        for (Individual population1 : population) {
            totalFitness += population1.getFitness();
        }
        
        return totalFitness;
    }
    Individual selectParent()
    {
        Individual parent = new Individual();
            int random = ThreadLocalRandom.current().nextInt(0, probabilities[probabilities.length-1] + 1);
            for(int i=0; i<probabilities.length; i++)
            {
                if(random<=probabilities[i])
                {
                    parent=population[i];
                    break;
                }
            }
        return parent;
    }
    int[] getProbabilities()
    {
        int[] prob = new int[population.length];
        int q=0;
        int p;
        for(int i= 0; i<population.length; i++)
        {
            p = (population[i].getFitness()*100)/selectGlobalFitness();
            q+=p;
            prob[i]=q;
        }
        probabilities= prob;
        return prob;
    }
    Individual[] produceNewPopulation(int crossoverRate)
    {
        Individual[] newPopulation = population;
        //int[] p = getProbabilities(fitness(population));
        for(int i=0; i<population.length; i++)
        {
            if(crossoverRate>ThreadLocalRandom.current().nextInt(0, 100 + 1))
            {
                Individual secondParent = selectParent();
                newPopulation[i].crossOver(newPopulation[i], secondParent);
            }
        }
        population=newPopulation;
        getProbabilities();
        generation++;
        return newPopulation;
        
    }
}
