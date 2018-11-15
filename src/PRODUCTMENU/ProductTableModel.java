/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRODUCTMENU;

import java.sql.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ProductTableModel extends AbstractTableModel {
    static final String url = "jdbc:derby://localhost:1527/FioreFlowerShopSystem";
    static final String username = "nbuser";
    static final String password = "nbuser";
    static final String query = "SELECT DN.DonationID,B.BDate,B.BTime,D.DName,SC.ScheduleDate,SC.ScheduleTime,SC.RoomDonation,S.StaffName FROM Donation DN, Booking B,Donor D, Schedule SC, Staff S WHERE S.StaffID=SC.StaffID AND SC.ScheduleID=DN.ScheduleID AND D.DID=B.DID AND B.BID=DN.BID ";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows = 2;
  

    public ProductTableModel() {
        try{
        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
     
        //setQuery(query);
        retrieveAll();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        try {
            return metaData.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int column)  {
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    public int getRowCount() {
         return numberOfRows;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void setQuery()  {
       try{
        //rs = stmt.executeQuery(query);
        metaData = rs.getMetaData();

        // determine number of rows in ResultSet
        rs.last();                      // move to last row
        numberOfRows = rs.getRow();     // get row number
        
        // notify JTable that model has changed
        fireTableStructureChanged();    
       }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void retrieveAll() {
        try{
            String query = "SELECT DN.DonationID,B.BDate,B.BTime,D.DName,SC.ScheduleDate,SC.ScheduleTime,SC.RoomDonation,S.StaffName FROM Donation DN, Booking B,Donor D, Schedule SC, Staff S WHERE S.StaffID=SC.StaffID AND SC.ScheduleID=DN.ScheduleID AND D.DID=B.DID AND B.BID=DN.BID";
            rs = stmt.executeQuery(query);
            setQuery();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void retrieveCode(String DonationID){
        try{
            String query = "SELECT DN.DonationID,B.BDate,B.BTime,D.DName,SC.ScheduleDate,SC.ScheduleTime,SC.RoomDonation,S.StaffName FROM Donation DN, Booking B,Donor D, Schedule SC, Staff S WHERE S.StaffID=SC.StaffID AND SC.ScheduleID=DN.ScheduleID AND D.DID=B.DID AND B.BID=DN.BID= '"+ DonationID + "' ";
            rs = stmt.executeQuery(query);
            setQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String [] args){
        ProductTableModel tableModel =new ProductTableModel();
        JTable jtable = new JTable(tableModel);
        TableRowSorter sorter = new TableRowSorter<TableModel>(tableModel);
        jtable.setRowSorter(sorter);
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(jtable));
        frame.setSize(500, 250);
        frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}

