/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfunction;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class FlowerDesign extends javax.swing.JFrame {

    private javax.swing.JComboBox<String> Access1;
    private javax.swing.JComboBox<String> Access2;
    private javax.swing.JComboBox<String> Access3;
    private javax.swing.JLabel DeliFees;
    private javax.swing.JComboBox<String> FloArrange;
    private javax.swing.JLabel FloPrice;
    private javax.swing.JComboBox<String> Size;
    private javax.swing.JLabel TotalPrice;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> floType1;
    private javax.swing.JComboBox<String> floType2;
    private javax.swing.JComboBox<String> floType3;
    private java.util.List<customizeflower.Flower> flowerList;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;

    Connection con;
    PreparedStatement pst, pst1, pst2, pst3;
    ResultSet rs;
    String root = "A000";
    int root2 = 1;
    String digit = Integer.toString(root2);
    String fullID = root + digit;
    double delivFee = 0;
    double sizeFee = 0;
    double arrFee = 0;
    double flowerFee = 0;
    double access1Fee = 0;
    double access2Fee = 0;
    double access3Fee = 0;
    double flower1Fee = 0;
    double flower2Fee = 0;
    double flower3Fee = 0;
    double fees = 0;
    String flowertxt;
    String[] flowers = new String[13];
    String[] acces = new String[8];
    private final String[] size = {"small", "medium", "large", "extra large"};
    private final String[] arrangement = {"Horizontal Flower Arrangement", "Veritical Flower Arrangement", "Oval Flower Arrangement", "Minimal Flower Arrangement", "Cresent Flower Arrangement", "Triangular Flower Arrangement"};
    private final String[] delivery = {"express(highest priority)", "normal", "flexi(lowest priority)"};
//    private final String[] acces = {"Acrylic Diamonds", "Decorative Gems", "Sea Shells", "Decorative Butterflies", "Decorative Birds", "Rhinestone Quintet", "Curly Tings"};

    /**
     * Creates new form FlowerDesign
     */
    public FlowerDesign() {
        database();
        initComponents();
        getData();

        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (jComboBox1.getSelectedItem() == "express(highest priority)") {
                    delivFee = 20.0;
                    DeliFees.setText("RM " + delivFee);

                } else if (jComboBox1.getSelectedItem() == "normal") {
                    delivFee = 10.0;
                    DeliFees.setText("RM " + delivFee);

                } else if (jComboBox1.getSelectedItem() == "flexi(lowest priority)") {
                    delivFee = 5.0;
                    DeliFees.setText("RM " + delivFee);
                }
                calculate();
            }
        });

        Size.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (Size.getSelectedItem() == "small") {
                    sizeFee = 10.0;
                } else if (Size.getSelectedItem() == "medium") {
                    sizeFee = 15.0;
                } else if (Size.getSelectedItem() == "large") {
                    sizeFee = 20.0;
                } else if (Size.getSelectedItem() == "extra large") {
                    sizeFee = 25.0;
                }
                calculate();
            }
        });

        FloArrange.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (FloArrange.getSelectedItem() == "Horizontal Flower Arrangement") {
                    arrFee = 10.0;
                } else if (FloArrange.getSelectedItem() == "Veritical Flower Arrangement") {
                    arrFee = 10.0;
                } else if (FloArrange.getSelectedItem() == "Oval Flower Arrangement") {
                    arrFee = 15.0;
                } else if (FloArrange.getSelectedItem() == "Minimal Flower Arrangement") {
                    arrFee = 10.0;
                } else if (FloArrange.getSelectedItem() == "Cresent Flower Arrangement") {
                    arrFee = 25.0;
                } else if (FloArrange.getSelectedItem() == "Triangular Flower Arrangement") {
                    arrFee = 25.0;
                }
                calculate();
            }
        });

        Access1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (Access1.getSelectedIndex() == 0) {
                    access1Fee = 10.0;
                } else if (Access1.getSelectedIndex() == 1) {
                    access1Fee = 15.0;
                } else if (Access1.getSelectedIndex() == 2) {
                    access1Fee = 8.0;
                } else if (Access1.getSelectedIndex() == 3) {
                    access1Fee = 10.0;
                } else if (Access1.getSelectedIndex() == 4) {
                    access1Fee = 20.0;
                }
                calculate();
            }
        });

        Access2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (Access2.getSelectedIndex() == 0) {
                    access2Fee = 10.0;
                } else if (Access2.getSelectedIndex() == 1) {
                    access2Fee = 15.0;
                } else if (Access2.getSelectedIndex() == 2) {
                    access2Fee = 8.0;
                } else if (Access2.getSelectedIndex() == 3) {
                    access2Fee = 10.0;
                } else if (Access2.getSelectedIndex() == 4) {
                    access2Fee = 20.0;
                }
                calculate();
            }
        });

        Access3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (Access3.getSelectedIndex() == 0) {
                    access3Fee = 10.0;
                } else if (Access3.getSelectedIndex() == 1) {
                    access3Fee = 15.0;
                } else if (Access3.getSelectedIndex() == 2) {
                    access3Fee = 8.0;
                } else if (Access3.getSelectedIndex() == 3) {
                    access3Fee = 10.0;
                } else if (Access3.getSelectedIndex() == 4) {
                    access3Fee = 20.0;
                }
                calculate();
            }
        });

        floType1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (floType1.getSelectedIndex() == 0) {
                    flower1Fee = 10.0;
                } else if (floType1.getSelectedIndex() == 1) {
                    flower1Fee = 15.0;
                } else if (floType1.getSelectedIndex() == 2) {
                    flower1Fee = 8.0;
                } else if (floType1.getSelectedIndex() == 3) {
                    flower1Fee = 10.0;
                } else if (floType1.getSelectedIndex() == 4) {
                    flower1Fee = 20.0;
                }
                calculate();
            }
        });

        floType2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (floType2.getSelectedIndex() == 0) {
                    flower2Fee = 10.0;
                } else if (floType2.getSelectedIndex() == 1) {
                    flower2Fee = 15.0;
                } else if (floType2.getSelectedIndex() == 2) {
                    flower2Fee = 8.0;
                } else if (floType2.getSelectedIndex() == 3) {
                    flower2Fee = 10.0;
                } else if (floType2.getSelectedIndex() == 4) {
                    flower2Fee = 20.0;
                }
                calculate();
            }
        });

        floType3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (floType3.getSelectedIndex() == 0) {
                    flower3Fee = 10.0;
                } else if (floType3.getSelectedIndex() == 1) {
                    flower3Fee = 15.0;
                } else if (floType3.getSelectedIndex() == 2) {
                    flower3Fee = 8.0;
                } else if (floType3.getSelectedIndex() == 3) {
                    flower3Fee = 10.0;
                } else if (floType3.getSelectedIndex() == 4) {
                    flower3Fee = 20.0;
                }
                calculate();
            }
        });
    }

    private void calculate() {
        flowerFee = flower1Fee + flower2Fee + flower3Fee;
        fees = arrFee + sizeFee + delivFee + access1Fee + access2Fee + access3Fee + flowerFee;
        FloPrice.setText("RM " + flowerFee);
        TotalPrice.setText("RM " + fees);
    }

    private void database() {
        try {

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/FlowerShop", "nbuser", "nbuser");

            pst = con.prepareStatement("INSERT INTO ARRANGEMENT VALUES(?,?,?,?,?,?,CAST(? as DOUBLE),?)");
            pst1 = con.prepareStatement("SELECT * FROM ITEM WHERE ITEM_TYPE = 'FLOWER' ");
            pst2 = con.prepareStatement("SELECT * FROM ITEM WHERE ITEM_TYPE = 'ACCESSORIES' ");
            pst3 = con.prepareStatement("SELECT * FROM ARRANGEMENT WHERE ARR_ID=(SELECT max(ARR_ID) FROM ARRANGEMENT)");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getData() {
        rs = null;
        int i = 0;
        try {
            rs = pst1.executeQuery();
            if (rs.next()) {
                do {
                    String flower = rs.getString("ITEM_NAME");
                    if (flower != rs.getString("ITEM_NAME")) {
                        flowers[i] = flower;
                        i++;
                    }
                } while (rs.next());
                floType1.setModel(new DefaultComboBoxModel(flowers));
                floType2.setModel(new DefaultComboBoxModel(flowers));
                floType3.setModel(new DefaultComboBoxModel(flowers));
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found", "Failed!!",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            i = 0;
            rs = pst2.executeQuery();
            if (rs.next()) {
                do {
                    String accessory = rs.getString("ITEM_NAME");
                    if (accessory != rs.getString("ITEM_NAME")) {
                        acces[i] = accessory;
                        i++;
                    }
                } while (rs.next());
                Access1.setModel(new DefaultComboBoxModel(acces));
                Access2.setModel(new DefaultComboBoxModel(acces));
                Access3.setModel(new DefaultComboBoxModel(acces));
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found", "Failed!!",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getID() {
        if (selectRecord() != null) {
            char[] ch = selectRecord().toCharArray();

            String s = Character.toString(ch[5]);
            String s1 = Character.toString(ch[4]);
            String s2 = Character.toString(ch[3]);
            String s3 = Character.toString(ch[2]);
            String s4 = Character.toString(ch[1]);
            String ss = s4 + s3 + s2 + s1 + s;
            int count = Integer.parseInt(ss);

            count++;

            if (count >= 10) {
                root = "A000";
            } else if (count >= 100) {
                root = "A00";
            } else if (count >= 1000) {
                root = "A0";
            } else if (count >= 10000) {
                root = "AU";
            } else {
                root = "A000";
            }
            String digit = Integer.toString(count);
            fullID = root + digit;
        }
    }

    private String selectRecord() {
        ResultSet rs = null;
        String fullID = null;
        try {
            rs = pst3.executeQuery();

            if (rs.next()) {
                fullID = rs.getString("ARR_ID");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return fullID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        floType1 = new javax.swing.JComboBox<>();
        floType2 = new javax.swing.JComboBox<>();
        floType3 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Access1 = new javax.swing.JComboBox<>();
        Access2 = new javax.swing.JComboBox<>();
        Access3 = new javax.swing.JComboBox<>();
        FloArrange = new javax.swing.JComboBox<>();
        Size = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        FloPrice = new javax.swing.JLabel();
        DeliFees = new javax.swing.JLabel();
        TotalPrice = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Fiore Flower Shop");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("FLOWER ARRANGEMENT");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("FLOWER ARRANGEMENT SIZE");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("FLOWER TYPE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ACCESSORIES");

        jLabel2.setText("Option 1:");

        jLabel7.setText("Option 2:");

        jLabel8.setText("Option 3:");

        floType1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floType1ActionPerformed(evt);
            }
        });

        floType2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        floType3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jLabel9.setText("Option 1:");

        jLabel10.setText("Option 2:");

        jLabel11.setText("Option 3:");

        Access1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        Access2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        Access3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        FloArrange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Horizontal Flower Arrangement", "Veritical Flower Arrangement", "Oval Flower Arrangement", "Minimal Flower Arrangement", "Cresent Flower Arrangement", "Triangular Flower Arrangement"}));
        FloArrange.setSelectedItem("Horizontal Flower Arrangement");

        Size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"small", "medium", "large", "extra large"}));
        Size.setSelectedItem("small");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Delivery Priority");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"express(highest priority)", "normal", "flexi(lowest priority)"}));
        jComboBox1.setSelectedItem("express(highest priority)");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setText("Total Flower Price  :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setText("Delivery Fees         :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setText("Total Price             :");

        FloPrice.setText("RM  0.00");

        DeliFees.setText("RM  0.00");

        TotalPrice.setText("RM  0.00");

        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(272, 272, 272)
                                .addComponent(jLabel1)
                                .addGap(41, 41, 41))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(FloArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(floType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(floType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(floType3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(Access1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(Access2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(Access3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnConfirm)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnCancel))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel13)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel15)
                                                                        .addComponent(jLabel14)))
                                                        .addGap(47, 47, 47)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(FloPrice)
                                                                .addComponent(DeliFees)
                                                                .addComponent(TotalPrice)))))
                                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel1)
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(FloArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(floType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(floType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(13, 13, 13)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(floType3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(Access1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(Access2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(Access3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)
                                        .addComponent(FloPrice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(DeliFees))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(TotalPrice))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfirm)
                                        .addComponent(btnCancel))
                                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>                        

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        rs = null;
        String accessories = "" + Access1.getSelectedItem() + ", " + Access2.getSelectedItem() + ", " + Access3.getSelectedItem();
        String flowers = "" + floType1.getSelectedItem() + ", " + floType2.getSelectedItem() + ", " + floType3.getSelectedItem();
        String cust_id = "C0001";
        String totalFees=""+fees;
        try {
            pst.setString(1, fullID);
            pst.setString(2, (String) FloArrange.getSelectedItem());
            pst.setString(3, (String) Size.getSelectedItem());
            pst.setString(4, accessories);
            pst.setString(5, flowers);
            pst.setString(6, (String) jComboBox1.getSelectedItem());
            pst.setString(7, totalFees);
            pst.setString(8, cust_id);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Record Had Been Added");
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    private void Access1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void floType1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FlowerDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FlowerDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FlowerDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FlowerDesign.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlowerDesign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
