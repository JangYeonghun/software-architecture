/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieinfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author gnsdu
 */
public class Info extends JFrame {
    
    Info() {        
        setTitle("MOVIEINFO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel());
        setSize(700, 500);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); // 첫 시작 프레임
        
        // 파일 내용 출력을 위한 JTextArea 추가
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // 편집 불가능하도록 설정
        textArea.setLineWrap(true); // 텍스트 영역에 라인 랩 적용
        textArea.setWrapStyleWord(true); // 단어 단위로 라인 랩 적용
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 세로 스크롤바 비활성화
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 버튼 추가
        JButton bookBtn1 = new JButton("예매1");
        JButton rvBtn1 = new JButton("리뷰1");
        JButton bookBtn2 = new JButton("예매2");
        JButton rvBtn2 = new JButton("리뷰2");
        JButton bookBtn3 = new JButton("예매3");
        JButton rvBtn3 = new JButton("리뷰3");

        // JPanel에 버튼 추가
        JPanel buttonPanel = new JPanel(new GridLayout(9, 1));
        buttonPanel.add(bookBtn1);  // GridLayout은 컴포넌트의 위치를 모두 사용하기 때문에 빈 JLabel 추가
        buttonPanel.add(rvBtn1);
        buttonPanel.add(new JLabel());  
        buttonPanel.add(bookBtn2);
        buttonPanel.add(rvBtn2);
        buttonPanel.add(new JLabel());  
        buttonPanel.add(bookBtn3);
        buttonPanel.add(rvBtn3);
        buttonPanel.add(new JLabel()); 

        contentPane.add(buttonPanel, BorderLayout.EAST);
        
        // 버튼 연결
        bookBtn1.addActionListener((ActionEvent e) -> {
            // 예매 버튼 1 클릭 시 좌석 선택 코드와 연결
        });
          rvBtn1.addActionListener((ActionEvent e) -> {
              ComicReviewPage reviewPage = new ComicReviewPage();
              reviewPage.moveToPage();
        });
        bookBtn2.addActionListener((ActionEvent e) -> {
            // 예매 버튼 2 클릭 시 좌석 선택 코드와 연결
        });
          rvBtn2.addActionListener((ActionEvent e) -> {
              ActionReviewPage reviewPage = new ActionReviewPage();
              reviewPage.moveToPage();
        });
        bookBtn3.addActionListener((ActionEvent e) -> {
            // 예매 버튼 3 클릭 시 좌석 선택 코드와 연결
        });
          rvBtn3.addActionListener((ActionEvent e) -> {
              AnimeReviewPage reviewPage = new AnimeReviewPage();
              reviewPage.moveToPage();
        });


        // 각각의 Genre 객체 생성
        Genre comicGenre = new Comic("C:\\Users\\gnsdu\\OneDrive\\문서\\NetBeansProjects\\softwareproject\\src\\main\\java\\movieinfo\\movie_comic.txt");
        Genre actionGenre = new Action("C:\\Users\\gnsdu\\OneDrive\\문서\\NetBeansProjects\\softwareproject\\src\\main\\java\\movieinfo\\movie_action.txt");
        Genre animeGenre = new Anime("C:\\Users\\gnsdu\\OneDrive\\문서\\NetBeansProjects\\softwareproject\\src\\main\\java\\movieinfo\\movie_anime.txt");

        // GenreComposite 객체 생성 후 각각의 Genre 객체를 추가
        GenreComposite genreComposite = new GenreComposite();
        genreComposite.addGenre(comicGenre);
        genreComposite.addGenre(actionGenre);
        genreComposite.addGenre(animeGenre);

        // Composite 객체의 getContent() 메소드를 호출하여 내용을 출력
        textArea.setText(genreComposite.getContent());
        
        setVisible(true);
    }
    
    class MainPanel extends JPanel {
    
        public MainPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);

            JLabel label = new JLabel("오늘의 영화 입니다.");
            label.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 35));
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label, BorderLayout.NORTH);
        }

        @Override
        public void paintComponent(Graphics p) {
            super.paintComponent(p);
        }
    }
    
    public static void main(String[] args) {
        new Info();
    }
}