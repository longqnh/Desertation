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

    public Dethi(String made, String id, String noidung, String dapanA, String dapanB, String dapanC, String dapanD, String dapan, String dangtoan, String dangbt, int dokho, int dophancach, int malop, int hinh) {
        super(id, noidung, dapanA, dapanB, dapanC, dapanD, dapan, dangtoan, dangbt, dokho, dophancach, malop, hinh);
        this.made = made;
    }
    
    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }
}
