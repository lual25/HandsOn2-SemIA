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
    boolean elitism;
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
    void crossOver(Individual parent1, Individual parent2, int mutationRate)
    {
        String[] aBinary = {Integer.toBinaryString(parent1.getA()), Integer.toBinaryString(parent2.getA())};
        String[] bBinary = {Integer.toBinaryString(parent1.getB()), Integer.toBinaryString(parent2.getB())};
        aBinary=fillUpString(aBinary);
        bBinary=fillUpString(bBinary);
        StringBuilder aChildren= new StringBuilder(aBinary[0]);
        StringBuilder bChildren= new StringBuilder(bBinary[0]);
        for(int i=0; i<aBinary[0].length(); i++)
            if(mutationRate<ThreadLocalRandom.current().nextInt(0, 100 + 1))
            {
                aChildren.setCharAt(i, aBinary[1].charAt(i));
            }
        for(int i=0; i<bBinary[0].length(); i++)
            if(mutationRate<ThreadLocalRandom.current().nextInt(0, 100 + 1))
            {
                bChildren.setCharAt(i, bBinary[1].charAt(i));
            }
        a=Integer.parseInt(aChildren.toString(), 2);
        b=Integer.parseInt(bChildren.toString(), 2);
        selectFitness();
    }
    void setElitism(boolean el)
    {
        elitism = el;
    }
    String[] fillUpString(String [] strings)
    {
        if(strings[0].length()==strings[1].length())
            return strings;
        if(strings[0].length()>strings[1].length())
        {
            String s="";
            for(int i= 0; i<strings[0].length()-strings[1].length(); i++)
            {
                s+="0";
            }
            s+=strings[1];
            strings[1]=s;
            return strings;
        }
        else{
            String s="";
            for(int i= 0; i<strings[1].length()-strings[0].length(); i++)
            {
                s+="0";
            }
            s+=strings[0];
            strings[0]=s;
            return strings;
        }
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
