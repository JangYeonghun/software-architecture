/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movie;

import java.io.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 회원가입
 */
public class SignUp extends JFrame {

    public SignUp() {
        setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new SignUpPanel());
        setSize(700, 500);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); // 첫 시작 프레임

        // 아이디
        JTextField id = new JTextField(15);
        id.setBounds(300, 115, 240, 35); // 가로 위치, 세로 위치, 가로 길이, 세로 길이
        contentPane.add(id);

        // 비밀번호
        JPasswordField pw = new JPasswordField(15);
        pw.setBounds(300, 165, 240, 35);
        contentPane.add(pw);

        // 이름
        JTextField name = new JTextField(15);
        name.setBounds(300, 215, 240, 35); // 가로 위치, 세로 위치, 가로 길이, 세로 길이
        contentPane.add(name);

        // 생년월일
        JTextField birth = new JTextField(15);
        birth.setBounds(300, 265, 240, 35); // 가로 위치, 세로 위치, 가로 길이, 세로 길이
        contentPane.add(birth);

        // 전화번호
        JTextField phoneNumber = new JTextField(15);
        phoneNumber.setBounds(300, 315, 240, 35); // 가로 위치, 세로 위치, 가로 길이, 세로 길이
        contentPane.add(phoneNumber);

        // < 회원가입 버튼 >
        JButton signup = new JButton("회원가입");
        Font font = signup.getFont();
        signup.setFont(new Font(font.getName(), font.getStyle(), 15));
        signup.setLocation(280, 380);
        signup.setSize(100, 40);
        contentPane.add(signup);
        signup.setBackground(Color.WHITE);
        contentPane.setLayout(null);

        setVisible(true);

        signup.addActionListener((var e) -> {
            try {
                if (id.getText().isEmpty() || String.valueOf(pw.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호는 필수 정보입니다. 입력해주세요.");
                    return;

                } else if (isIdDuplicate(id.getText())) {
                    JOptionPane.showMessageDialog(null, "이미 사용 중인 ID입니다. 다른 ID를 입력해주세요.");
                    return;
                }

                Member.MemberBuilder builder = new Member.MemberBuilder()
                        .setId(id.getText(), String.valueOf(pw.getPassword()))
                        .setName(name.getText())
                        .setBirth(birth.getText())
                        .setPhoneNumber(phoneNumber.getText());
                Member newMember = builder.build();

                if (!name.getText().isEmpty()) { // 이름 - 한글과 영어
                    String nameText = name.getText();
                    if (!nameText.matches("[가-힣a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(null, "이름은 한글과 영어만 입력 가능합니다.");
                        return;
                    }
                    builder.setName(nameText);
                }

                if (!birth.getText().isEmpty()) { // 생년월일 - 숫자
                    String birthText = birth.getText();
                    if (!birthText.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "생년월일은 숫자만 입력 가능합니다.");
                        return;
                    }
                    builder.setBirth(birthText);
                }

                if (!phoneNumber.getText().isEmpty()) { // 전화번호 - 숫자
                    String phoneNumberText = phoneNumber.getText();
                    if (!phoneNumberText.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력 가능합니다.");
                        return;
                    }
                    builder.setPhoneNumber(phoneNumberText);
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("member.txt", true))) {
                    writer.write(newMember.toString() + "\n");
                    writer.close();
                }
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                dispose(); // 현재 창 닫기
                Main main = new Main();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "회원가입에 실패하셨습니다.");
            }
        }
        );
    }

    boolean isIdDuplicate(String id) {
        File file = new File("member.txt");
        if (!file.exists()) {
            return false; // 파일이 존재하지 않으면 중복 ID도 없음
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(id)) {
                    return true; // 중복 ID가 존재함
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // 중복 ID가 존재하지 않음

    }

    class SignUpPanel extends JPanel {

        @Override
        public void paintComponent(Graphics p) {
            super.paintComponent(p);
            p.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 30));
            p.drawString("회원가입 페이지입니다", 210, 45); // 회원가입

            p.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
            p.drawString("ID", 230, 140); // id
            p.setFont(new Font("맑은 고딕", Font.PLAIN, 19));
            p.drawString("PW", 225, 190); // pw
            p.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
            p.drawString("NAME", 215, 240); // name
            p.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
            p.drawString("BIRTH", 215, 290); // birth
            p.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
            p.drawString("PHONE NUMBER", 150, 340); // phonenumber
        }
    }
}
