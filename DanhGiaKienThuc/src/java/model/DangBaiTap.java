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
public class DangBaiTap {
    private String dangbt, dangtoan, dangbtTV;

    public DangBaiTap() {
    }

    public DangBaiTap(String dangbt, String dangtoan, String dangbtTV) {
        this.dangbt = dangbt;
        this.dangtoan = dangtoan;
        this.dangbtTV = dangbtTV;
    }

    public DangBaiTap(String dangbt, String dangbtTV) {
        this.dangbt = dangbt;
        this.dangbtTV = dangbtTV;
    }

    public DangBaiTap(String dangbt) {
        this.dangbt = dangbt;
    }

    public String getDangbt() {
        return dangbt;
    }

    public void setDangbt(String dangbt) {
        this.dangbt = dangbt;
    }

    public String getDangtoan() {
        return dangtoan;
    }

    public void setDangtoan(String dangtoan) {
        this.dangtoan = dangtoan;
    }

    public String getDangbtTV() {
        return dangbtTV;
    }

    public void setDangbtTV(String dangbtTV) {
        this.dangbtTV = dangbtTV;
    }
}
