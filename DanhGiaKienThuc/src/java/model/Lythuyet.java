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
public class Lythuyet {
    private String kienthuc;
    private String noidung;
    private int lop;

    public Lythuyet() {
    }

    public Lythuyet(String kienthuc, String noidung, int lop) {
        this.kienthuc = kienthuc;
        this.noidung = noidung;
        this.lop = lop;
    }
    
    public String getKienthuc() {
        return kienthuc;
    }

    public void setKienthuc(String kienthuc) {
        this.kienthuc = kienthuc;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getLop() {
        return lop;
    }

    public void setLop(int lop) {
        this.lop = lop;
    }
}
