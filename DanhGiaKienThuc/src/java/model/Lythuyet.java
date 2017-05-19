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

    public Lythuyet() {
    }

    public Lythuyet(String kienthuc, String noidung) {
        this.kienthuc = kienthuc;
        this.noidung = noidung;
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
}
