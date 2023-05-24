/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movie;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberInfoChanger extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JTextField pwField;
    private JTextField pw2Field;
    private JTextField nameField;
    private JTextField birthField;
    private JTextField phoneField;
    private JButton confirmButton;

    public MemberInfoChanger() {
        super("회원 정보 변경");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);

        JPanel panel = new JPanel();
       // panel.setLayout(new FlowLayout(20));
       panel.setLayout(null);
       //JPanel panel = new JPanel(new GridLayout(2, 1));
        

        JLabel idLabel = new JLabel("아이디");
        idLabel.setBounds(25,20,80,20);
        panel.add(idLabel);
        idField = new JTextField(20);
        idField.setBounds(100,20,200,20);
        panel.add(idField);
        
        JLabel pwLabel = new JLabel("현재 비밀번호");
        pwLabel.setBounds(25, 70, 80, 20);
        panel.add(pwLabel);
        pwField = new JTextField(20);
        pwField.setBounds(100,70,200,20);
        
        JLabel pw2Label = new JLabel("변경될 비밀번호");
        pw2Label.setBounds(25,120,80,20);
        panel.add(pw2Label);
        pw2Field = new JTextField(20);
        pw2Field.setBounds(100,120,200,20);
        panel.add(pw2Field);
        panel.add(pwField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(25,170,80,20);
        panel.add(nameLabel); 
        nameField = new JTextField(20);
        nameField.setBounds(100,170,200,20);
        panel.add(nameField);

        JLabel birthLabel = new JLabel("생년월일");
        birthLabel.setBounds(25,220,80,20);
        panel.add(birthLabel);
        birthField = new JTextField(20);
        birthField.setBounds(100,220,200,20);
        panel.add(birthField);

        JLabel phoneLabel = new JLabel("전화번호");
        phoneLabel.setBounds(25,270,80,20);
        panel.add(phoneLabel);
        phoneField = new JTextField(20);
        phoneField.setBounds(100,270,200,20);
        panel.add(phoneField);
        
        

        confirmButton = new JButton("변경");
        confirmButton.setBounds(250,300,100,40);
        confirmButton.addActionListener(this);
        panel.add(confirmButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            String id = idField.getText();
            String pw = pwField.getText();
            String pw2 = pw2Field.getText();
            String name = nameField.getText();
            String birth = birthField.getText();
            String phone = phoneField.getText();

            try {
                BufferedReader reader = new BufferedReader(new FileReader("member.txt"));
                String line = "";
                StringBuilder sb = new StringBuilder();
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens[0].equals(id)) {
                        if (tokens[1].equals(pw)) {
                        line = id + "," + pw2 + "," + name + "," + birth + "," + phone ;
                        found = true;
                        }
                        else {
                        JOptionPane.showMessageDialog(null, "현재 비밀번호가 일치하지 않습니다.");
                        return; // exit the method
                        }
                    }
                    sb.append(line).append("\n");
                }
                reader.close();

                if (!found) {
                JOptionPane.showMessageDialog(null, "해당 아이디를 찾을 수 없습니다.");
                return; // exit the method
            }
                
                
                BufferedWriter writer = new BufferedWriter(new FileWriter("member.txt"));
                writer.write(sb.toString());
                writer.close();

                JOptionPane.showMessageDialog(null, "회원 정보가 변경되었습니다.");
                System.exit(0);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "파일 처리 중 오류가 발생하였습니다.");
            }
        }
    }

    public static void main(String[] args) {
        new MemberInfoChanger();
    }
}


