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
public class MonHoc {
    private String monhocID;
    private String tenmonhoc;

    public MonHoc() {
    }

    public MonHoc(String monhocID, String tenmonhoc) {
        this.monhocID = monhocID;
        this.tenmonhoc = tenmonhoc;
    }   
    
    public String getMonhocID() {
        return monhocID;
    }

    public void setMonhocID(String monhocID) {
        this.monhocID = monhocID;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }      
}
