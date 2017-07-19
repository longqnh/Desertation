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
public class Dethi extends Question{

    private String made;
    
    public Dethi() {
        super();
    }

    public Dethi(String made, String id, String noidung, String dapanA, String dapanB, String dapanC, String dapanD, String dapan, String monhoc, String dangtoan, String dangbt, int dokho, double dophancach, int malop, int hinh, int dao) {
        super(id, noidung, dapanA, dapanB, dapanC, dapanD, dapan, monhoc, dangtoan, dangbt, dokho, dophancach, malop, hinh, dao);
        this.made = made;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }
}
