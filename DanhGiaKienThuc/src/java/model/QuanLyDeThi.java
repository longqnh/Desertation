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
public class QuanLyDeThi {
    private String made;
    private int socau;
    private String monhoc;
    private String noidung;
    private int thoigian;
    private String mucdo;
    private float diem;
    private String ngaythi;
    private String username;

    public QuanLyDeThi() {
    }

    public QuanLyDeThi(String made, int socau, String monhoc, String noidung, int thoigian, String mucdo, float diem, String ngaythi, String username) {
        this.made = made;
        this.socau = socau;
        this.monhoc = monhoc;
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.mucdo = mucdo;
        this.diem = diem;
        this.ngaythi = ngaythi;
        this.username = username;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public int getSocau() {
        return socau;
    }

    public void setSocau(int socau) {
        this.socau = socau;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }
 
    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public String getMucdo() {
        return mucdo;
    }

    public void setMucdo(String mucdo) {
        this.mucdo = mucdo;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
