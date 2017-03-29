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
public class Danhgia {
    private String username;
    private double hamso, loga, tichphan, sophuc, hhkg, oxyz;

    public Danhgia() {
    }

    public Danhgia(String username, double hamso, double loga, double tichphan, double sophuc, double hhkg, double oxyz) {
        this.username = username;
        this.hamso = hamso;
        this.loga = loga;
        this.tichphan = tichphan;
        this.sophuc = sophuc;
        this.hhkg = hhkg;
        this.oxyz = oxyz;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getHamso() {
        return hamso;
    }

    public void setHamso(double hamso) {
        this.hamso = hamso;
    }

    public double getLoga() {
        return loga;
    }

    public void setLoga(double loga) {
        this.loga = loga;
    }

    public double getTichphan() {
        return tichphan;
    }

    public void setTichphan(double tichphan) {
        this.tichphan = tichphan;
    }

    public double getSophuc() {
        return sophuc;
    }

    public void setSophuc(double sophuc) {
        this.sophuc = sophuc;
    }

    public double getHhkg() {
        return hhkg;
    }

    public void setHhkg(double hhkg) {
        this.hhkg = hhkg;
    }

    public double getOxyz() {
        return oxyz;
    }

    public void setOxyz(double oxyz) {
        this.oxyz = oxyz;
    }
}
