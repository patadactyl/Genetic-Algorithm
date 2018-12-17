/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author patrickcharlton
 */
class Environment {
    
    static int mPoolSize = 20;
    static String crossType = "1";   
    static double muRate = 0.1;
    static int popSize = 1000;

    public static int getPopSize() {
        return popSize;
    }

    public static void setPopSize(int popSize) {
        Environment.popSize = popSize;
    }
    
    public static int getPoolSize() {
        return mPoolSize;
    }

    public static void setPoolSize(int mPoolSize) {
        Environment.mPoolSize = mPoolSize;
    }

    public static String getCrossType() {
        return crossType;
    }

    public static void setCrossType(String crossType) {
        Environment.crossType = crossType;
    }

    public static double getMuRate() {
        return muRate;
    }

    public static void setMuRate(double rate) {
        muRate = rate;
    } 
            
    public static enum FitnessFunction { // so we can use these identifiers to select which fitness we are using
        countOnes, mystery, rr
    };

    public static FitnessFunction state = FitnessFunction.rr;
    public static FitnessFunction getState() {
        return state;
    }

    public static void setState(FitnessFunction state) {
        Environment.state = state;
    }
    

    public static int eval(Evaluable it) {
        switch (state) {
            case countOnes:
                return CountOnes.getValue(it);
            case mystery:
                return Mystery.getValue(it);
            case rr:
                return Fitness4.getValue(it);
            default: {
                System.out.println("Oops!");
                System.exit(7);
            }
        }
        return -1;  // never!
    }

    public static String printFitnessFunctionName() {
        return "Using fitness: " + whichFitness();
    }

    private static String whichFitness() {  // so we can see which fitness the Environment is using
        switch (state) {
            case countOnes:
                return "countOnes";
            case mystery:
                return "mystery";
            case rr:
                return "rr";
            default: {
                System.out.println("Oops!");
                System.exit(7);
            }
        }
        return "xxx";  // never!    
    }

    public static void main(String[] args) {    // just checking!

        Environment.setState(FitnessFunction.countOnes);
        System.out.println(Environment.printFitnessFunctionName());
        Environment.setState(FitnessFunction.mystery);
        System.out.println(Environment.printFitnessFunctionName());
        Environment.setState(FitnessFunction.rr);
        System.out.println(Environment.printFitnessFunctionName());
    }
}
