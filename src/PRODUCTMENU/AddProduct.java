/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRODUCTMENU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProduct extends JFrame {


    private final String SQL_INSERT = "INSERT INTO PRODUCT VALUES (?,?,?,?,?,?)";
    private final String SQL = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = (SELECT MAX(PRODUCT_ID) FROM PRODUCT)";


    private JLabel Title = new JLabel("Please fill up the all the details:");
    private JLabel Product_ID = new JLabel("Product ID");
    private JLabel Product_Name = new JLabel("Product Name");
    private JLabel Product_Desc = new JLabel("Product Description");
    private JLabel Product_Price = new JLabel("Product Price");
    private JLabel Product_Quantity = new JLabel("Product Qunatity");
    private JLabel Product_Type = new JLabel("Product Type");
    
    private ButtonGroup BgProductType = new ButtonGroup();
    
    private final String[] ProductType = {"fresh flowers", "bouquets"};
    private JRadioButton[] cbProductType = new JRadioButton[ProductType.length];
    
    private JTextField txtProduct_ID = new JTextField();
    private JTextField txtProduct_Name = new JTextField();
    private JTextArea txtProduct_Desc = new JTextArea();
    private JTextField txtProduct_Price = new JTextField();
    private JTextField txtProduct_Quantity = new JTextField();
    private JTextField txtProduct_Type = new JTextField();

    private Connection con;
    private PreparedStatement pStmt_Insert,pStmt;
    
    String root = "P0000";
    int root2 = 1;
    String digit = Integer.toString(root2);
    String fullid = root + digit;
    ResultSet rs;
    
    

    public AddProduct() {

        setLayout(new BorderLayout());
	try {
            initDbConnection();
            initPrepareStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        add(getHeaderPanel(), BorderLayout.NORTH);
        add(getInputPanel(), BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);

}
    private JPanel getHeaderPanel() {

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(Title);
        Title.setFont(new Font("Cambria", Font.BOLD, 25));
        Title.setForeground(Color.red);
        
        

        return panel;
    }
    
    private String selectRecord() {
            ResultSet rs = null;
            String fullid = null;
            
            try {
                rs = pStmt.executeQuery();
                
                if(rs.next()){
                    fullid = rs.getString("PRODUCT_ID");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            return fullid;
        }

    public JPanel getInputPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2));
        
        
        if(selectRecord() != null){
            char[] ch = selectRecord().toCharArray();
            
            String s = Character.toString(ch[5]);
            String s2 = Character.toString(ch[4]);
            String s3 = Character.toString(ch[3]);
            String s4 = Character.toString(ch[2]);
            String s5 = Character.toString(ch[1]);
            String st = s5 + s4 + s3 + s2 + s;
            int count = Integer.parseInt(st);
            
            root = "";
            
            count++;
            
            if(count >= 10){
                root = "P000";
            }else if(count >= 100){
                root = "P00";
            }else if(count >= 1000){
                root = "P0";
            }else if(count >= 10000){
                root = "P";
            }else{
                root = "P0000";
            }
            
            String digit = Integer.toString(count);
            fullid = root + digit;
            
        }
        
        panel.add(Product_ID);
        Product_ID.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProduct_ID);
        txtProduct_ID.setFont(new Font("Cambria", Font.PLAIN, 25));
        txtProduct_ID.setText(fullid);
        txtProduct_ID.setEditable(false);

        panel.add(Product_Name);
        Product_Name.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProduct_Name);
        txtProduct_Name.setFont(new Font("Cambria", Font.PLAIN, 25));


        panel.add(Product_Desc);
        Product_Desc.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProduct_Desc);
        txtProduct_Desc.setFont(new Font("Cambria", Font.PLAIN, 15));


        panel.add(Product_Price);
        Product_Price.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProduct_Price);
        txtProduct_Price.setFont(new Font("Cambria", Font.PLAIN, 25));

        
        panel.add(Product_Quantity);
        Product_Quantity.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProduct_Quantity);
        txtProduct_Quantity.setFont(new Font("Cambria", Font.PLAIN, 25));
        
        panel.add(Product_Type);
        Product_Type.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(getProductTypePanel());



        return panel;
    }

    private JPanel getButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnConfirm = new JButton("Confirm");
        JButton btnReset = new JButton("Reset");
        JButton btnBack = new JButton("Back");

        Image img = new ImageIcon(this.getClass().getResource("/images/accept-icon.png")).getImage();
        btnConfirm.setIcon(new ImageIcon(img));

        Image img1 = new ImageIcon(this.getClass().getResource("/images/back.png")).getImage();
        btnBack.setIcon(new ImageIcon(img1));

        Image img2 = new ImageIcon(this.getClass().getResource("/images/Refresh.png")).getImage();
        btnReset.setIcon(new ImageIcon(img2));
        
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                String productType = null;

                if (!txtProduct_ID.getText().isEmpty() && !txtProduct_Name.getText().isEmpty() && !txtProduct_Desc.getText().isEmpty() && !txtProduct_Price.getText().isEmpty()&& !txtProduct_Quantity.getText().isEmpty()) {

                    sb.append("Product ID       : " + txtProduct_ID.getText() + "\n");
                    sb.append("Product Name     : " + txtProduct_Name.getText() + "\n");
                    sb.append("Product Desc     : " + txtProduct_Desc.getText() + "\n");
                    sb.append("Product Price    : RM " + txtProduct_Price.getText() + "\n");
                    sb.append("Product Quantity : " + txtProduct_Quantity.getText() + "\n");
                    sb.append("Product Type     : ");

                    for (int i = 0; i < cbProductType.length; i++) {
                        if (cbProductType[i].isSelected()) {
                            sb.append(cbProductType[i].getText());
                            productType = cbProductType[i].getText();
                            sb.append("\n");
                        }
                    }

                    sb.append("\nIs the product information correct?");

                    int isConfirm = JOptionPane.showConfirmDialog(
                            null,
                            sb.toString(),
                            "Check Information",
                            JOptionPane.YES_NO_OPTION);

                    if (isConfirm == JOptionPane.YES_OPTION) {

                        JOptionPane.showMessageDialog(null, "New product has been added.");

//                        String Date = (String)txtDate.getText();
//                        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                        


                        try {
                            //Setup parameterized SQL in PreparedStatement

                            pStmt_Insert.setString(1, fullid);
                            pStmt_Insert.setString(2, txtProduct_Name.getText());
                            pStmt_Insert.setString(3, txtProduct_Desc.getText());
                            pStmt_Insert.setString(4, txtProduct_Price.getText());
                            pStmt_Insert.setString(5, txtProduct_Quantity.getText());
                            pStmt_Insert.setString(6, productType);


                            //Execuse SQL
                            pStmt_Insert.executeUpdate();

                            dispose();
                            PRODUCT objFrame = new PRODUCT();

                            objFrame.setTitle("Product Manegement Menu");
                            objFrame.setSize(400, 460);
                            objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            objFrame.setLocationRelativeTo(null);
                            objFrame.setVisible(true);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }


                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the empty field", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PRODUCT product = new PRODUCT();

                product.setTitle("Product Management Menu");
                product.setSize(400, 460);
                product.setLocationRelativeTo(null);
                product.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                product.setVisible(true);
            }

        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtProduct_ID.setText(fullid);
                txtProduct_Name.setText("");
                txtProduct_Desc.setText("");
                txtProduct_Price.setText("");
                txtProduct_Quantity.setText("");
                txtProduct_Type.setText("");

            }
        });

        panel.add(btnConfirm);
        panel.add(btnBack);
        panel.add(btnReset);
        return panel;
    }
    
    

    private void initDbConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/FioreFlowerShopSystem", "nbuser", "nbuser");
    }

    private void initPrepareStatement() throws SQLException {

        pStmt_Insert = con.prepareStatement(SQL_INSERT);
        pStmt = con.prepareStatement(SQL);

    }

    public static void main(String[] args) {
        AddProduct objFrame = new AddProduct();

        objFrame.setTitle("Add New Product");
        objFrame.setSize(800, 500);
        objFrame.setLocationRelativeTo(null);
        objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objFrame.setVisible(true);
    }

    private JPanel getProductTypePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 0));

        for (int i = 0; i < ProductType.length; i++) {
            cbProductType[i] = new JRadioButton(ProductType[i], true);
            BgProductType.add(cbProductType[i]);

            panel.add(cbProductType[i]);

            cbProductType[i].setFont(new Font("Cambria", Font.PLAIN, 20));
        }
        return panel;
    }

    

}
