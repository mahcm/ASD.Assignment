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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PRODUCT extends JFrame {

    JLabel header = new JLabel("Product Management Menu");

    JButton btnAdd = new JButton("Add New Product");
    JButton btnCheck = new JButton("Check Availability Of Product");
    JButton btnBack = new JButton("Back To Menu");

    public PRODUCT() {

        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\ACER\\Desktop\\medical.png"));
        add(background);
        background.setLayout(new FlowLayout(1, 50, 20));

        background.add(header);
        header.setForeground(Color.green);
        header.setFont(new Font("Cambria", Font.PLAIN, 30));
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runAdd();
            }

        });

        
        background.add(btnAdd);
        btnAdd.setFont(new Font("Cambria", Font.ITALIC, 20));
        Image img = new ImageIcon(this.getClass().getResource("/images/Add.png")).getImage();
        btnAdd.setIcon(new ImageIcon(img));
        
        
        background.add(btnCheck);
        btnCheck.setFont(new Font("Cambria", Font.ITALIC, 20));
        Image img2 = new ImageIcon(this.getClass().getResource("/images/Search.png")).getImage();
        btnCheck.setIcon(new ImageIcon(img2));

        background.add(btnBack);
        btnBack.setFont(new Font("Cambria", Font.ITALIC, 20));
        Image img4 = new ImageIcon(this.getClass().getResource("/images/back.png")).getImage();
        btnBack.setIcon(new ImageIcon(img4));
        
    }

 

    private void runAdd() {
        dispose();
         AddProduct objFrame = new AddProduct();

        objFrame.setTitle("Add New Blood Bag");
        objFrame.setSize(800, 500);
        objFrame.setLocationRelativeTo(null);
        objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objFrame.setVisible(true);

    }


    public static void main(String[] args) {
        PRODUCT objFrame = new PRODUCT();

        objFrame.setTitle("Product Manegement Menu");
        objFrame.setSize(400, 460);
        objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objFrame.setLocationRelativeTo(null);
        objFrame.setVisible(true);
    }

}
