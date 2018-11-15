/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa;

/**
 *
 * @author LIM_PC
 */
public class Customer {
    private static int nextId = 100;
    private int id;
    private String name;
    private String ic;
    private String phoneNo;
    private String address;
    private String type;
    
    public Customer(String name, String ic, String phoneNo, String address){
        this.id = nextId++;
        this.name = name;
        this.ic = ic;
        this.phoneNo = phoneNo;
        this.address = address;
        this.type = "consumer";
    }
    
    public static int getNextId(){
        return nextId;
    }
    
    public static void setNextId(int nextId){
        Customer.nextId = nextId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
}
