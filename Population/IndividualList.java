/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;

/**
 *
 * @author patrickcharlton
 */
class IndividualList extends ArrayList<Individual> {
    
//    public int getAvgFitness() {
//        int total = 0;
//        for (Individual mp : this) {
//            total += mp.getFitness();
//        }
//        return (total / this.size());
//    }

    @Override
    public String toString() {
        String returnMe = "IndividualList";
        
        for (Individual nextInd : this) {
            returnMe += "\n\t" + nextInd.toString();
        }
        
        return returnMe;
    }

}
