/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Bill {

    private int oID;
    private String dateCreate;
    private String cname;
    private String cphone;
    private String cAddress;
    private double total;
    private double totalPrize;
    private int status;
    private int cid;
    private String ceckbill;
    
    public Bill() {
    }

    public Bill(int oID, String dateCreate, String cname, String cphone, String cAddress, double total, double totalPrize, int status, int cid, String ceckbill) {
        this.oID = oID;
        this.dateCreate = dateCreate;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.total = total;
        this.totalPrize = totalPrize;
        this.status = status;
        this.cid = cid;
        this.ceckbill = ceckbill;
    }

    public double getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(double totalPrize) {
        this.totalPrize = totalPrize;
    }

    public String getCeckbill() {
        return ceckbill;
    }

    public void setCeckbill(String ceckbill) {
        this.ceckbill = ceckbill;
    }
    

//    public Bill(int oID, String dateCreate, String cname, String cphone, String cAddress, double total, double totalPrize, int status, int cid) {
//        this.oID = oID;
//        this.dateCreate = dateCreate;
//        this.cname = cname;
//        this.cphone = cphone;
//        this.cAddress = cAddress;
//        this.total = total;
//        this.totalPrize = totalPrize;
//        this.status = status;
//        this.cid = cid;
//    }
    

    public Bill(int oID, String dateCreate, String cname, String cphone, String cAddress, double total, int status, int cid,String ceckbill) {
        this.oID = oID;
        this.dateCreate = dateCreate;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.total = total;
        this.status = status;
        this.cid = cid;
        this.ceckbill = ceckbill;
    }

    public Bill(String dateCreate, String cname, String cphone, String cAddress, int total, int status, int cid) {
        this.dateCreate = dateCreate;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.total = total;
        this.status = status;
        this.cid = cid;
    }

   

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

}
