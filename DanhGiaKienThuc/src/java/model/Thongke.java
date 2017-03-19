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
public class Thongke {
    private String dangtoan, mucdo;
    private int socau, socaudung;
    private float dopc;

    public Thongke() {
    }

    public Thongke(String dangtoan, String mucdo, int socau, int socaudung, float dopc) {
        this.dangtoan = dangtoan;
        this.mucdo = mucdo;
        this.socau = socau;
        this.socaudung = socaudung;
        this.dopc = dopc;
    }

    public String getDangtoan() {
        return dangtoan;
    }

    public void setDangtoan(String dangtoan) {
        this.dangtoan = dangtoan;
    }

    public String getMucdo() {
        return mucdo;
    }

    public void setMucdo(String mucdo) {
        this.mucdo = mucdo;
    }

    public int getSocau() {
        return socau;
    }

    public void setSocau(int socau) {
        this.socau = socau;
    }

    public int getSocaudung() {
        return socaudung;
    }

    public void setSocaudung(int socaudung) {
        this.socaudung = socaudung;
    }

    public float getDopc() {
        return dopc;
    }

    public void setDopc(float dopc) {
        this.dopc = dopc;
    }
}
