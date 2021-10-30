/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class BillDetail {
    private int pid;
    private String oID;
    private String dateCreate;
    private int quantity;
    private double money;
    private double total;

    public BillDetail() {
    }

    public BillDetail(int pid, String oID, String dateCreate, int quantity, double money, double total) {
        this.pid = pid;
        this.oID = oID;
        this.dateCreate = dateCreate;
        this.quantity = quantity;
        this.money = money;
        this.total = total;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    

    
    
    
    
}
