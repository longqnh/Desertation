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
    private String noidung, dapanA, dapanB, dapanC, dapanD, answer;
    private String dangtoan, dangbt;
    private int level;
    private int HasImage;
    
    public Question() {
    }

    public Question(String id, String noidung, String dapanA, String dapanB, String dapanC, String dapanD, String answer, String dangtoan, String dangbt, int level, int HasImage) {
        this.id = id;
        this.noidung = noidung;
        this.dapanA = dapanA;
        this.dapanB = dapanB;
        this.dapanC = dapanC;
        this.dapanD = dapanD;
        this.answer = answer;
        this.dangtoan = dangtoan;
        this.dangbt = dangbt;
        this.level = level;
        this.HasImage = HasImage;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getHasImage() {
        return HasImage;
    }

    public void setHasImage(int HasImage) {
        this.HasImage = HasImage;
    }
}
