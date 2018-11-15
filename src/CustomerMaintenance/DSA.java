/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa;

import java.util.Scanner;
/**
 *
 * @author LIM_PC
 */
public class DSA {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int selection;
        String name;
        String ic ;
        String phoneNo;
        String address;
        // TODO code application logic here
        System.out.println("Hello. Welcome for using our system.");
        System.out.println("===== Main Menu =====");
        System.out.println("1. Registration ");
        System.out.println("0. Exit ");
        System.out.print("Please enter your selection: ");
        selection = sc.nextInt();
        
        if(selection == 1){
            System.out.println("==== Registration ====");
            System.out.println("1. Staff");
            System.out.println("2. Customer");
            System.out.println("0. Back to Main Menu");
            System.out.print("Please enter your selection: ");
            int selection2 = sc.nextInt();
            
            if(selection2 == 1){
                System.out.println("To be continue");
                System.exit(0);
            }
            else if(selection2 == 2){
                System.out.println("==== Registration ====");
                System.out.println("Please fill in all the information.");
                System.out.print("Name: ");
                name = sc.nextLine();
                while (name.length()==0){
                    System.out.print("*****Name is required.\nName: ");
                    name = sc.nextLine();
                }
                System.out.print("IC (xxxxxx-xx-xxxx): ");
                ic = sc.nextLine();
                while(ic.length()==0 || !ic.matches("\\d{6}\\-\\d{2}\\-\\d{4}")){
                    if(ic.length()==0){
                        System.out.print("*****IC is required.\nIC (xxxxxx-xx-xxxx): ");
                        ic = sc.nextLine();
                    }
                    else if (!ic.matches("\\d{6}\\-\\d{2}\\-\\d{4}")) {
                        System.out.print("*****IC format wrong.\nIC (xxxxxx-xx-xxxx): ");
                        ic = sc.nextLine();
                    }
                }
                System.out.print("Phone No (xxx-xxxxxxx): ");
                phoneNo = sc.nextLine();
                while(phoneNo.length()==0 || !phoneNo.matches("\\d{3}\\-\\d{7}")){
                    if(phoneNo.length()==0){
                        System.out.print("*****Phone No is required.\nPhone No (xxx-xxxxxxx): ");
                        phoneNo = sc.nextLine();
                    }
                    else if(!phoneNo.matches("\\d{3}\\-\\d{7}")){
                        System.out.print("*****Phone format wrong.\nPhone No (xxx-xxxxxxx): ");
                        phoneNo = sc.nextLine();
                    }
                }
                System.out.print("Address: ");
                address = sc.nextLine();
                while(address.length()==0){
                    System.out.print("*****Address is required.\nAddress: ");
                    address = sc.nextLine();
                }
                Customer customer = new Customer(name,ic,phoneNo,address);
                System.out.println("Register Successfully");
                
            }
        }
        else if (selection ==0){
            System.out.println("THX FOR USING....");
            System.exit(0);
        }
        
        
    }
    
}
