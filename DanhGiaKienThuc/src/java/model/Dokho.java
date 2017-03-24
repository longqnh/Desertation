/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NTL
 */
public class Dokho {
    private int dokho;
    private String mucdo;

    public Dokho() {
    }

    public Dokho(int dokho, String mucdo) {
        this.dokho = dokho;
        this.mucdo = mucdo;
    }

    public int getDokho() {
        return dokho;
    }

    public void setDokho(int dokho) {
        this.dokho = dokho;
    }

    public String getMucdo() {
        return mucdo;
    }

    public void setMucdo(String mucdo) {
        this.mucdo = mucdo;
    }    
}
