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
    private String dangtoan, dangbt;
    private int dokho;
    private int dophancach;
    private int malop;
    private int hinh;

    public Question() {
    }

    public Question(String id, String noidung, String dapanA, String dapanB, String dapanC, String dapanD, String dapan, String dangtoan, String dangbt, int dokho, int dophancach, int malop, int hinh) {
        this.id = id;
        this.noidung = noidung;
        this.dapanA = dapanA;
        this.dapanB = dapanB;
        this.dapanC = dapanC;
        this.dapanD = dapanD;
        this.dapan = dapan;
        this.dangtoan = dangtoan;
        this.dangbt = dangbt;
        this.dokho = dokho;
        this.dophancach = dophancach;
        this.malop = malop;
        this.hinh = hinh;
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

    public int getDophancach() {
        return dophancach;
    }

    public void setDophancach(int dophancach) {
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
}
