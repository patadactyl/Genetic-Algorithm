/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrickcharlton
 */
public class Individual implements Comparable, Evaluable {

    int fitness;
    byte[] dna;
    int runningSum;

    public Individual() {
        dna = new byte[L];
        init();
    }

    public Individual(Individual parent) {
        dna = new byte[L];

        for (int i = 0; i < L; i++) {
            dna[i] = parent.dna[i];
        }
        fitness = Environment.eval(this);
    }
    
    void init() {
        for (int i = 0; i < L; i++) {
            dna[i] = Util.rand(2);
        }
        fitness = Environment.eval(this);       // so it has a fitness to start off
    }

    @Override
    public String toString() {
        return "Ind: " + Environment.printFitnessFunctionName()
                + " fitness=" + fitness + ", dna=" + format(dna);
    }

    public static void mainEnumerate(String[] args) {    // trying to get a handle on how well enumberation would do...
        Environment.setState(Environment.FitnessFunction.rr);        // set the fitness function

        Individual anInd = null;
        Individual best = new Individual();
        for (long i = 0; i < 1000000000; i++) {
            if (i % 1000000 == 0) {                 // every million tries, print a .
                System.out.print(".");
                System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory());
                System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory());

            }
            if (best.getFitness() == 100) {         // see how long it takes to find all ones; this is simply true for the others
                System.out.println("Victory!!");
                break;
            }
            anInd = new Individual();               // create a random ind
            if (anInd.getFitness() > best.getFitness()) {   // if it's better, remember it and report
                best = anInd;
                System.out.println("i=" + i + " best.getFitness() = " + best.toString());
            }
        }
        System.out.println("done...");
    }

    @Override
    public byte[] getDNA() {
        return dna;
    }

    @Override
    public int getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public Individual myClone() {
        return new Individual(this);
//        Individual myClone = null;
//        
//        try {
//            myClone = (Individual) super.clone();  // do the shallow copy
//        } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(Individual.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        myClone.dna = new byte[L];
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < L; j++) {
//                myClone.dna[L] = dna[L];                
//            }
//        }
//        return myClone;
    }

    public static void main(String[] args) {
        Individual ind = new Individual();
        ind.dna[0] = 1;  // so we know it's a 1
        Individual clone = (Individual) ind.myClone();
        System.out.println("ind = " + ind);
        System.out.println("clone = " + clone);
        clone.getDNA()[0] = 0;  // change the clone dna, does the original change too?
        System.out.println("clone = " + clone);
        System.out.println("ind = " + ind);
    }

    private String format(byte[] dna) { // make the byte[] into a String
        String returnMe = "";

        for (byte nextByte : dna) {
            returnMe += nextByte;
        }

        return returnMe;
    }

    public static void mainAgain(String[] args) {
        Environment.setState(Environment.FitnessFunction.mystery);
        for (int i = 0; i < 10; i++) {
            Individual ind = new Individual();
            System.out.println("ind = " + ind);
        }

    }

    @Override
    public int compareTo(Object o) {
        Individual that = (Individual) o;
        if (this.fitness < that.fitness) {
            return 1;
        } else if (this.fitness == that.fitness) {
            return 0;
        } else {
            return -1;
        }
        
    }
}  // Individual
