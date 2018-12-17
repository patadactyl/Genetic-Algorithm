/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import static ga.Evaluable.L;
/**
 *
 * @author patrickcharlton
 */
public class Chromosome {

    protected byte[] dna;

    public Chromosome(){
        dna = new byte[L];
        for (int i = 0; i < L; i++) {  // random bits
            dna[i] = (byte) Util.rand(2);      // 0 or 1
        }
    }   // default constructor


    public byte[] getDna() {return dna;}

    public void setDna(byte[] dna) { this.dna = dna;}

    public String toString() {
        String returnMe = "";
        returnMe += bytesToString();
        return returnMe;
    } // toString()
    
    /**
     * Make the byte[] a String
     * @return the bytes as a String!
     */
    private String bytesToString() {
        String returnMe = "";

        for (byte nextByte : dna) {
            returnMe += "" + nextByte;
        }

        return returnMe;
    }
    
    public static void main(String[] args) {
        Chromosome chromo = new Chromosome();
        System.out.println("chromo = " + chromo);
    }
}