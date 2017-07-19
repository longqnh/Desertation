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
public class Question {
    private String id;
    private String noidung, dapanA, dapanB, dapanC, dapanD, dapan;
    private String monhoc, dangtoan, dangbt;
    private int dokho;
    private double dophancach;
    private int malop;
    private int hinh;
    private int dao;
    
    public Question() {
    }

    public Question(String id, String noidung, String dapanA, String dapanB, String dapanC, String dapanD, String dapan, String monhoc, String dangtoan, String dangbt, int dokho, double dophancach, int malop, int hinh, int dao) {
        this.id = id;
        this.noidung = noidung;
        this.dapanA = dapanA;
        this.dapanB = dapanB;
        this.dapanC = dapanC;
        this.dapanD = dapanD;
        this.dapan = dapan;
        this.monhoc = monhoc;
        this.dangtoan = dangtoan;
        this.dangbt = dangbt;
        this.dokho = dokho;
        this.dophancach = dophancach;
        this.malop = malop;
        this.hinh = hinh;
        this.dao = dao;
    }

    public Question(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getDapanA() {
        return dapanA;
    }

    public void setDapanA(String dapanA) {
        this.dapanA = dapanA;
    }

    public String getDapanB() {
        return dapanB;
    }

    public void setDapanB(String dapanB) {
        this.dapanB = dapanB;
    }

    public String getDapanC() {
        return dapanC;
    }

    public void setDapanC(String dapanC) {
        this.dapanC = dapanC;
    }

    public String getDapanD() {
        return dapanD;
    }

    public void setDapanD(String dapanD) {
        this.dapanD = dapanD;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }  
    
    public String getDangtoan() {
        return dangtoan;
    }

    public void setDangtoan(String dangtoan) {
        this.dangtoan = dangtoan;
    }

    public String getDangbt() {
        return dangbt;
    }

    public void setDangbt(String dangbt) {
        this.dangbt = dangbt;
    }

    public int getDokho() {
        return dokho;
    }

    public void setDokho(int dokho) {
        this.dokho = dokho;
    }

    public double getDophancach() {
        return dophancach;
    }

    public void setDophancach(double dophancach) {
        this.dophancach = dophancach;
    }

    public int getMalop() {
        return malop;
    }

    public void setMalop(int malop) {
        this.malop = malop;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getDao() {
        return dao;
    }

    public void setDao(int dao) {
        this.dao = dao;
    }       
}
