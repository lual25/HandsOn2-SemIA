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
    int a;
    int b;
    int[][] info = {{23, 661}, {26, 661}, {30, 661},{34, 661},{43, 661},{48, 661},{52, 661},{57, 661},{58, 661}};
    int fitness;
    Individual()
    {
        a=randomGen();
        b=randomGen();
        selectFitness();
    }
    String getIndividual()
    {
        return "dsa";
    }
    int getA()
    {
        return a;
    }
    int getB()
    {
        return a;
    }
    int randomGen()
    {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1); 

    }
    void printIndividual()
    {
        System.out.println("a= "+a+" b= "+b+" Fitness: "+fitness);
    }
    int selectFitness()
    {
        int sum = 0;
        for(int i=0; i<info.length; i++)
        {
                int x=info[i][0];
                int y=info[i][1];
                int y1=a+(b*x);
                int dif = y1-y;
                if(dif<0)
                    dif=dif*(-1);
                sum+=dif;
        }
        fitness=sum;
        return sum;
    }
    int getFitness()
    {
        return fitness;
    }
    void crossOver(Individual parent1, Individual parent2)
    {
        String aBinaryp1 = Integer.toBinaryString(parent1.getA());
        String aBinaryp2 = Integer.toBinaryString(parent2.getA());
        String bBinaryp1 = Integer.toBinaryString(parent1.getB());
        String bBinaryp2 = Integer.toBinaryString(parent2.getB());
        String aChildren;
        String bChildren;
        int crossoverpoint = ThreadLocalRandom.current().nextInt(0, aBinaryp1.length() + 1);
        aChildren = aBinaryp1.substring(0, crossoverpoint) + aBinaryp2.substring(crossoverpoint);
        crossoverpoint = ThreadLocalRandom.current().nextInt(0, bBinaryp1.length() + 1);
        bChildren = bBinaryp1.substring(0, crossoverpoint) + bBinaryp2.substring(crossoverpoint);
        a=Integer.parseInt(aChildren, 2);
        b=Integer.parseInt(bChildren, 2);
        selectFitness();
    }
    /*String mutation(int mutationRate)
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
    }*/
}
