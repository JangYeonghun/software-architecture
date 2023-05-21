/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author 회원정보변경
 */
public class MemberInfoChanger extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField idField;
    private JTextField pwField;
    private JTextField changeField;
    private JTextField nameField;
    private JTextField birthField;
    private JTextField phoneField;
    private JButton confirmButton;

    public MemberInfoChanger() {
        super("회원정보변경");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("회원정보변경");
        titleLabel.setBounds(250, 10, 400, 50);
        titleLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35));
        panel.add(titleLabel);

        JLabel idLabel = new JLabel("아이디");
        idLabel.setBounds(200, 100, 80, 20);
        Font font = idLabel.getFont();
        idLabel.setFont(new Font(font.getName(), font.getStyle(), 15));
        panel.add(idLabel);
        idField = new JTextField(20);
        idField.setBounds(270, 100, 200, 20);
        panel.add(idField);

        JLabel pwLabel = new JLabel("현재 비밀번호");
        pwLabel.setBounds(180, 140, 80, 20);
        pwLabel.setFont(new Font(font.getName(), font.getStyle(), 12));
        panel.add(pwLabel);
        pwField = new JPasswordField(20);
        pwField.setBounds(270, 140, 200, 20);
        panel.add(pwField);

        JLabel changeLabel = new JLabel("변경 비밀번호");
        changeLabel.setBounds(180, 180, 80, 20);
        changeLabel.setFont(new Font(font.getName(), font.getStyle(), 12));
        panel.add(changeLabel);
        changeField = new JPasswordField(20);
        changeField.setBounds(270, 180, 200, 20);
        panel.add(changeField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(205, 220, 80, 20);
        nameLabel.setFont(new Font(font.getName(), font.getStyle(), 15));
        panel.add(nameLabel);
        nameField = new JTextField(20);
        nameField.setBounds(270, 220, 200, 20);
        panel.add(nameField);

        JLabel birthLabel = new JLabel("생년월일");
        birthLabel.setBounds(190, 260, 80, 20);
        birthLabel.setFont(new Font(font.getName(), font.getStyle(), 15));
        panel.add(birthLabel);
        birthField = new JTextField(20);
        birthField.setBounds(270, 260, 200, 20);
        panel.add(birthField);

        JLabel phoneLabel = new JLabel("전화번호");
        phoneLabel.setBounds(190, 300, 80, 20);
        phoneLabel.setFont(new Font(font.getName(), font.getStyle(), 15));
        panel.add(phoneLabel);
        phoneField = new JTextField(20);
        phoneField.setBounds(270, 300, 200, 20);
        panel.add(phoneField);

        confirmButton = new JButton("변경");
        confirmButton.setBounds(290, 350, 100, 40);
        confirmButton.setFont(new Font(font.getName(), font.getStyle(), 15));
        confirmButton.setBackground(Color.WHITE);
        confirmButton.addActionListener(this);
        panel.add(confirmButton);

        add(panel);
        setVisible(true);

        confirmButton.addActionListener((ActionEvent e) -> {
            String id = idField.getText();
            String pw = pwField.getText();
            String pw2 = changeField.getText();
            String name = nameField.getText();
            String birth = birthField.getText();
            String phone = phoneField.getText();

            if (!isAlphabetic(name)) {
                JOptionPane.showMessageDialog(null, "이름은 영어와 한글만 입력할 수 있습니다.");
                return;
            }

            if (!isNumeric(birth)) {
                JOptionPane.showMessageDialog(null, "생년월일은 숫자만 입력할 수 있습니다.");
                return;
            }

            if (!isNumeric(phone)) {
                JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력할 수 있습니다.");
                return;
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader("member.txt"));
                String line = "";
                StringBuilder sb = new StringBuilder();
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens[0].equals(id)) {
                        if (tokens[1].equals(pw)) {
                            line = id + "," + pw2 + "," + name + "," + birth + "," + phone;
                            found = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "현재 비밀번호가 일치하지 않습니다.");
                            return;
                        }
                    }
                    sb.append(line).append("\n");
                }
                reader.close();

                if (!found) {
                    JOptionPane.showMessageDialog(null, "해당 아이디를 찾을 수 없습니다.");
                    return;
                }

                BufferedWriter writer = new BufferedWriter(new FileWriter("member.txt"));
                writer.write(sb.toString());
                writer.close();

                JOptionPane.showMessageDialog(null, "회원 정보가 변경되었습니다.");
                dispose(); // 현재 창 닫기
                Main main = new Main();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "파일 처리 중 오류가 발생하였습니다.");
            }
        });
    }

    private boolean isAlphabetic(String input) {
        String regex = "^[a-zA-Z가-힣]+$";
        return input.matches(regex);
    }

    private boolean isNumeric(String input) {
        String regex = "^[0-9]+$";
        return input.matches(regex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
