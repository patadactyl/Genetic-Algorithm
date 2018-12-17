/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.Collections;

/**
 *
 * @author patrickcharlton
 */
public class Population extends Individual {

    protected IndividualList list;

    public Population() {
        list = new IndividualList();
        for (int i = 0; i < Environment.getPopSize(); i++) {
            list.add(new Individual());
        }
    }   //default constructor

    public Individual getMostFit() {
        Collections.sort(list);
        System.out.println(list.get(0));
        return list.get(0);
    }

    public int getAvgFitness() {
        int total = 0;
        for (Individual mp : list) {
            total += mp.getFitness();
        }
        return (total / list.size());
    }

    public IndividualList getList() {
        return list;
    }

    public void setList(IndividualList list) {
        this.list = list;
    }

    public String toString() {
        String returnMe = "I am a Population: ";
        returnMe += "\tlist=" + getList().toString();
        return returnMe;
    } // toString()

    public static void main(String[] args) {
        Population thePop = new Population();
        System.out.println("thePop = " + thePop);
    }
}  // Population
