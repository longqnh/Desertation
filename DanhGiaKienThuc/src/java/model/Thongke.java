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
    private String madangtoan, dangtoan, mucdo;
    private int socau, socaudung;
    private int dopc;

    public Thongke() {
    }

    public Thongke(String madangtoan, String dangtoan, String mucdo, int socau, int socaudung, int dopc) {
        this.madangtoan = madangtoan;
        this.dangtoan = dangtoan;
        this.mucdo = mucdo;
        this.socau = socau;
        this.socaudung = socaudung;
        this.dopc = dopc;
    }

    public Thongke(String dangtoan, int socau) {
        this.dangtoan = dangtoan;
        this.socau = socau;
    }

    public Thongke(String dangtoan, int socau, int socaudung) {
        this.dangtoan = dangtoan;
        this.socau = socau;
        this.socaudung = socaudung;
    }

    public Thongke(String madangtoan, String dangtoan, int socau) {
        this.madangtoan = madangtoan;
        this.dangtoan = dangtoan;
        this.socau = socau;
    }

    public Thongke(String madangtoan, String dangtoan, int socau, int socaudung) {
        this.madangtoan = madangtoan;
        this.dangtoan = dangtoan;
        this.socau = socau;
        this.socaudung = socaudung;
    }

    public Thongke(String madangtoan, String dangtoan, String mucdo, int socau, int socaudung) {
        this.madangtoan = madangtoan;
        this.dangtoan = dangtoan;
        this.mucdo = mucdo;
        this.socau = socau;
        this.socaudung = socaudung;
    }

    public String getMadangtoan() {
        return madangtoan;
    }

    public void setMadangtoan(String madangtoan) {
        this.madangtoan = madangtoan;
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

    public void setDopc(int dopc) {
        this.dopc = dopc;
    }
}
