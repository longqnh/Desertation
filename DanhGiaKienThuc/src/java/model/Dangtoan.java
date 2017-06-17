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
public class Dangtoan {
    private String dangtoan;
    private String madangtoan;
    private String monhoc;
    private int malop;
    private String dangtoanTV;
    private int hocky;
    private double dopc_de, dopc_tb, dopc_tbk, dopc_kho;
    
    public Dangtoan() {
    }

    public Dangtoan(String dangtoan, String madangtoan, String monhoc, int malop, String dangtoanTV, int hocky, double dopc_de, double dopc_tb, double dopc_tbk, double dopc_kho) {
        this.dangtoan = dangtoan;
        this.madangtoan = madangtoan;
        this.monhoc = monhoc;
        this.malop = malop;
        this.dangtoanTV = dangtoanTV;
        this.hocky = hocky;
        this.dopc_de = dopc_de;
        this.dopc_tb = dopc_tb;
        this.dopc_tbk = dopc_tbk;
        this.dopc_kho = dopc_kho;
    }

    public Dangtoan(String dangtoan, String dangtoanTV) {
        this.dangtoan = dangtoan;
        this.dangtoanTV = dangtoanTV;
    }

    public String getDangtoan() {
        return dangtoan;
    }

    public void setDangtoan(String dangtoan) {
        this.dangtoan = dangtoan;
    }

    public String getMadangtoan() {
        return madangtoan;
    }

    public void setMadangtoan(String madangtoan) {
        this.madangtoan = madangtoan;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public int getMalop() {
        return malop;
    }

    public void setMalop(int malop) {
        this.malop = malop;
    }

    public String getDangtoanTV() {
        return dangtoanTV;
    }

    public void setDangtoanTV(String dangtoanTV) {
        this.dangtoanTV = dangtoanTV;
    }

    public int getHocky() {
        return hocky;
    }

    public void setHocky(int hocky) {
        this.hocky = hocky;
    }

    public double getDopc_de() {
        return dopc_de;
    }

    public void setDopc_de(double dopc_de) {
        this.dopc_de = dopc_de;
    }

    public double getDopc_tb() {
        return dopc_tb;
    }

    public void setDopc_tb(double dopc_tb) {
        this.dopc_tb = dopc_tb;
    }

    public double getDopc_tbk() {
        return dopc_tbk;
    }

    public void setDopc_tbk(double dopc_tbk) {
        this.dopc_tbk = dopc_tbk;
    }

    public double getDopc_kho() {
        return dopc_kho;
    }

    public void setDopc_kho(double dopc_kho) {
        this.dopc_kho = dopc_kho;
    }
}
