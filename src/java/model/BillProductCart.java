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
public class BillProductCart {
    private int id;
    private Product pro;
    private int quan;

    public BillProductCart() {
    }

    public BillProductCart(int id, Product pro, int quan) {
        this.id = id;
        this.pro = pro;
        this.quan = quan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public BillProductCart(Product pro, int quan) {
        this.pro = pro;
        this.quan = quan;
    }

    public Product getPro() {
        return pro;
    }

    public void setPro(Product pro) {
        this.pro = pro;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }
    
    
}
