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
public final class Individual {
    String individual=randomGen();
    int fitness;
    Individual()
    {
        individual=randomGen();
        selectFitness();
    }
    String getIndividual()
    {
        return individual;
    }
    String randomGen()
    {
        String object = "";
        for(int i=0; i<10; i++)
        {
            object+= String.valueOf(ThreadLocalRandom.current().nextInt(0, 1 + 1)); 
        }
        return object;
    }
    void printIndividual()
    {
        System.out.println(individual+" Fitness: "+fitness);
    }
    int selectFitness()
    {
        fitness = 0;
            for(int j=0; j<individual.length(); j++)
                if(individual.charAt(j) == '1')
                {
                    fitness++;
                }
            return fitness;
    }
    int getFitness()
    {
        return fitness;
    }
    String crossOver(Individual parent1, Individual parent2)
    {
        String children;
        int crossoverpoint = ThreadLocalRandom.current().nextInt(0, parent1.getIndividual().length() + 1);
        children = parent1.getIndividual().substring(0, crossoverpoint) + parent2.getIndividual().substring(crossoverpoint);
        individual = children;
        selectFitness();
        return children;
    }
    String mutation(int mutationRate)
    {
        StringBuilder aux = new StringBuilder(individual);
        for(int i=0; i<aux.length(); i++)
        {
            if(mutationRate<=ThreadLocalRandom.current().nextInt(0, 100 + 1))
            {
                if(aux.charAt(i)=='0')
                {
                    aux.setCharAt(i, '1');
                }
                else
                    aux.setCharAt(i, '0');

            }
        }
        return aux.toString();
    }
}
