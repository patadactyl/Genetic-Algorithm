/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.Random;
import static ga.Evaluable.L;

/**
 *
 * @author patrickcharlton
 */
class GA {

    Population thePopulation;
    IndividualList matingPool;
    byte[] dna = new byte[L];

    public GA() {
        thePopulation = new Population();
    }

    void doAGeneration() {
        evaluateFitness();
        selectMatingPool();
        applyGeneticOperators();
        replace();
    }

    @Override
    public String toString() {
        return "GA\n" + thePopulation.toString();
    }

    public int evaluateFitness() {
        Individual current;
        for (int i = 0; i < thePopulation.getList().size(); i++) {
            current = thePopulation.getList().get(i);
            current.fitness = Environment.eval(current);
        }
        thePopulation.getMostFit();
        return thePopulation.getAvgFitness();
    }

    private void selectMatingPool() {
        matingPool = new IndividualList();
        int sum = 0;
        for (Individual current : thePopulation.getList()) {
            sum += current.fitness;
            current.runningSum = sum;
        }
        Random rn = new Random();

        for (int i = 0; i < Environment.getPoolSize(); i++) {
            int r = rn.nextInt(sum);
            int index = 0;
            Individual current = thePopulation.getList().get(0);
            while (current.runningSum < r) {
                index++;
                current = thePopulation.getList().get(index);
            }
            matingPool.add(current.myClone());
        }

    }

    private void applyGeneticOperators() {
        mutate();
        crossOver();
    }

    private void replace() {
        int popSize = thePopulation.getList().size() - 1;
        System.out.println("Mating pool size: " + matingPool.size());
        for (int i = 0; i < matingPool.size(); i++) {
            thePopulation.getList().set(popSize--, matingPool.get(i));
        }
    }

    public void mutate() {
        for (int i = 0; i < matingPool.size(); i++) {
            byte[] currentDNA = matingPool.get(i).getDNA();
            for (int j = 0; j < currentDNA.length; j++) {
                byte currGene = currentDNA[j];
                if (Math.random() < Environment.getMuRate()) { //random chance
                    currGene = flip(currGene);
                }
            }
        }
    }

    public static byte flip(byte b) {
        if (b == 1) {
            return (byte) 0;
        } else {
            return (byte) 1;
        }
    }

    public void crossOver() {
        Random rn = new Random();
        switch (Environment.getCrossType()) {
            case "1":
                onePoint(matingPool, rn.nextInt(L));
                System.out.println("Ran 1 point");
                break;
            case "2":
                int start = rn.nextInt(L);
                int end;
                while (true) {
                    end = rn.nextInt(L);
                    if (end > start) {
                        break;
                    }
                }
                onePoint(matingPool, start);
                onePoint(matingPool, end);
                break;

            case "n":
                int numRuns = rn.nextInt(L);
                int dnaStart = rn.nextInt(L);
                for (int i = 0; i < numRuns; i++) {
                    onePoint(matingPool, dnaStart);
                    if (dnaStart >= 99) {
                        break;
                    }
                    int temp;
                    while (true) {
                        temp = rn.nextInt(L);
                        if (temp > dnaStart) {
                            break;
                        }
                    }
                    dnaStart = temp;
                }
        }
    }

    public void onePoint(IndividualList matingPool, int startPoint) {
        int index = 0;
        while (index + 1 < matingPool.size()) {
            Individual ind1 = matingPool.get(index);
            Individual ind2 = matingPool.get(index + 1);
            for (int i = startPoint; i < ind1.getDNA().length; i++) {
                byte temp = ind1.getDNA()[i];
                ind1.getDNA()[i] = ind2.getDNA()[i];
                ind2.getDNA()[i] = temp;
            }
            index++;
        }
    }
    
    
    public void test() {
        while (thePopulation.getMostFit().fitness < 123456) {
            doAGeneration();
        }
    }
    

}

