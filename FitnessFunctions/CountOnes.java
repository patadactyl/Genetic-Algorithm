/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import ga.Evaluable;
/**
 *
 * @author patrickcharlton
 */
class CountOnes {
    
      
     public static int getValue(Evaluable nextInd) {
        int count = 0;

        for (byte nextByte : nextInd.getDNA()) {
            if (nextByte == 1) {
                count++;
            }
        }

        return count;
    }
}
