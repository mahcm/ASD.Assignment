/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRODUCTMENU;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class UpdateProduct extends JFrame {

    private final String SQL_SELECT = "SELECT* FROM PRODUCT WHERE PRODUCT_NAME = ? AND PRODUCT_TYPE = ? ";
    private final String SQL_UPDATE = "UPDATE PRODUCT " + "SET PRODUCT_DESC = ?," + "PRODUCT_PRICE = ?," + "PRODUCT_QUANTITY = ?";

    private JLabel Header = new JLabel("Please follow the step:");
    private JLabel Header1 = new JLabel("1.Enter your product name.");
    private JLabel Header2 = new JLabel("2.Select the product type.");
    private JLabel Header3 = new JLabel("3.Press the Retrieve Button.");

    private JLabel ProductName = new JLabel("Product Name");
    private JLabel ProductType = new JLabel("Product Type");
    private JLabel ProductDesc = new JLabel("Product Desc");
    private JLabel ProductPrice = new JLabel("Product Price");
    private JLabel ProductQuantity = new JLabel("Product Quantity");

    private ButtonGroup BgProductType = new ButtonGroup();

    private final String[] Product_Type = {"fresh flowers", "bouquets"};
    private JRadioButton[] cbProductType = new JRadioButton[Product_Type.length];

    private JTextField txtProductName = new JTextField();
    private JTextField txtProductDesc = new JTextField();
    private JTextField txtProductPrice = new JTextField();
    private JTextField txtProductQuantity = new JTextField();

    private Connection con;
    private PreparedStatement pStmt_Select, pStmt_Update;

    public UpdateProduct() {
        setLayout(new BorderLayout());

        add(getHeaderPanel(), BorderLayout.NORTH);
        add(getDisplayPanel(), BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);

        try {
            initDbConnection();
            initPrepareStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private JPanel getHeaderPanel() {

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(Header);
        panel.add(Header1);
        panel.add(Header2);
        panel.add(Header3);

        Header.setFont(new Font("Cambria", Font.BOLD, 15));
        Header1.setFont(new Font("Cambria", Font.BOLD, 15));
        Header2.setFont(new Font("Cambria", Font.BOLD, 15));
        Header3.setFont(new Font("Cambria", Font.BOLD, 15));

        return panel;
    }

    private JPanel getDisplayPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(ProductName);
        ProductName.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProductName);
        txtProductName.setFont(new Font("Cambria", Font.PLAIN, 25));

        panel.add(ProductType);
        ProductType.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(getProductTypePanel());

        panel.add(ProductDesc);
        ProductDesc.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProductDesc);
        txtProductDesc.setFont(new Font("Cambria", Font.PLAIN, 25));
        txtProductDesc.setEditable(false);

        panel.add(ProductPrice);
        ProductPrice.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProductPrice);
        txtProductPrice.setFont(new Font("Cambria", Font.PLAIN, 25));
        txtProductPrice.setEditable(false);

        panel.add(ProductQuantity);
        ProductQuantity.setFont(new Font("Cambria", Font.BOLD, 25));
        panel.add(txtProductQuantity);
        txtProductQuantity.setFont(new Font("Cambria", Font.PLAIN, 25));

        return panel;
    }

    private JPanel getButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton btnRetrieve = new JButton("Retrieve");
        JButton btnUpdate = new JButton("Update");
        JButton btnCancel = new JButton("Cancel");

        Image img = new ImageIcon(this.getClass().getResource("/images/Search.png")).getImage();
        btnRetrieve.setIcon(new ImageIcon(img));

        Image img1 = new ImageIcon(this.getClass().getResource("/images/edit-icon.png")).getImage();
        btnUpdate.setIcon(new ImageIcon(img1));

        Image img2 = new ImageIcon(this.getClass().getResource("/images/back.png")).getImage();
        btnCancel.setIcon(new ImageIcon(img2));

        btnRetrieve.addActionListener(new RetrieveListener());
        btnUpdate.addActionListener(new UpdateListener());
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PRODUCT objFrame = new PRODUCT();

                objFrame.setTitle("Product Manegement Menu");
                objFrame.setSize(400, 460);
                objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                objFrame.setLocationRelativeTo(null);
                objFrame.setVisible(true);
            }

        });

        panel.add(btnRetrieve);
        panel.add(btnUpdate);
        panel.add(btnCancel);
        return panel;

    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String productType = null;
            
            for (int i = 0; i < cbProductType.length; i++) {
                if (cbProductType[i].isSelected()) {
                    productType = cbProductType[i].getText();
                }
            }
            ResultSet rs = selectRecord(txtProductName.getText(), productType);

            try {
                if (rs.next()) {
                    txtProductDesc.setText(rs.getString("PRODUCT_DESC"));
                    txtProductPrice.setText(rs.getString("PRODUCT_PRICE"));
                    txtProductQuantity.setText(rs.getString("PRODUCT_QUANTITY"));
                } else {
                    JOptionPane.showMessageDialog(null, "No such this Product ID.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        private ResultSet selectRecord(String PRODUCT_NAME, String PRODUCT_TYPE) {
            ResultSet rs = null;

            try {
                pStmt_Select.setString(1, PRODUCT_NAME);
                pStmt_Select.setString(2, PRODUCT_TYPE);

                rs = pStmt_Select.executeQuery();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

            return rs;
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String ProductDesc = txtProductDesc.getText();
            String ProductPrice = txtProductPrice.getText();
            String ProductQuantity = txtProductQuantity.getText();
            String productType = null;
            StringBuilder sb = new StringBuilder();

            try {
                pStmt_Update.setString(1, ProductDesc);
                pStmt_Update.setString(2, ProductPrice);
                pStmt_Update.setString(3, ProductQuantity);

                pStmt_Update.executeUpdate();

                sb.append("Product Name     : " + txtProductName.getText() + "\n");
                sb.append("Product Desc     : " + txtProductDesc.getText() + "\n");
                sb.append("Product Price    : " + txtProductPrice.getText() + "\n");
                sb.append("Product Quantity : " + txtProductQuantity.getText() + "\n");
                sb.append("Product Type     : ");

                for (int i = 0; i < cbProductType.length; i++) {
                    if (cbProductType[i].isSelected()) {
                        sb.append(cbProductType[i].getText());
                        productType = cbProductType[i].getText();
                        sb.append("\n");
                    }
                }

                sb.append("\n\nIs the information correct?");

                int isConfirm = JOptionPane.showConfirmDialog(
                        null,
                        sb.toString(),
                        "Check Information",
                        JOptionPane.YES_NO_OPTION);

                if (isConfirm == JOptionPane.YES_OPTION) {

                    JOptionPane.showMessageDialog(null, "Updated Sucessfully !!!");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initDbConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/FioreFlowerShopSystems", "nbuser", "nbuser");
    }

    private void initPrepareStatement() throws SQLException {

        pStmt_Select = con.prepareStatement(SQL_SELECT);
        pStmt_Update = con.prepareStatement(SQL_UPDATE);

    }

    public static void main(String[] args) {

        UpdateProduct objFrame = new UpdateProduct();

        objFrame.setTitle("Update Product Details");
        objFrame.setSize(800, 500);
        objFrame.setLocationRelativeTo(null);
        objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objFrame.setVisible(true);

    }

    private JPanel getProductTypePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 0));

        for (int i = 0; i < Product_Type.length; i++) {
            cbProductType[i] = new JRadioButton(Product_Type[i], true);
            BgProductType.add(cbProductType[i]);

            panel.add(cbProductType[i]);

            cbProductType[i].setFont(new Font("Cambria", Font.PLAIN, 20));
        }
        return panel;
    }

}
