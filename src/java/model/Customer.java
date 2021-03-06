/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HP
 */
public class Customer {
    private int cid;//int primary key identity(1,1)
    private String  cname,cphone,cAddress,username,password;
    private int status;
    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Customer(int cid, String cname, String cphone, String cAddress, String username, String password, int status, String isAdmin) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
        this.isAdmin = isAdmin;
    }
    public Customer() {
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
    }
    
    public Customer(int cid, String cname, String cphone, String cAddress, String username, 
            String password, int status) {
        this.cid = cid;
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Customer(String cname, String cphone, String cAddress, String username, String password, int status , String isAdmin) {
        this.cname = cname;
        this.cphone = cphone;
        this.cAddress = cAddress;
        this.username = username;
        this.password = password;
        this.status = status;
        this.isAdmin = isAdmin;
    }
    

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
