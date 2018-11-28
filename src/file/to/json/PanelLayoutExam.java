package file.to.json;

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 

public class PanelLayoutExam extends JFrame { 

    JButton btn1 = new JButton("WestButton"); 
    JButton btn2 = new JButton("CenterButton"); 

    JPanel jpn = new JPanel(); 
    JButton btn3 = new JButton("File"); 
    JButton btn4 = new JButton("Help"); 

    public PanelLayoutExam() { 
        super("PanelLayoutExam"); 
        // 레이아웃 변경 

        // JPanel에 컴포넌트 추가 
        jpn.add(btn3); 
        jpn.add(btn4); 

        // JFrame에 컴포넌트 추가 
        add(jpn, "North"); 
        add(btn1, "West"); 
        add(btn2, "Center"); 

        // 크기 
        setSize(500, 500); 
        // super.pack(); 
        // 보이기 
        setVisible(true); 
        // x : 종료 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    } 

    public static void main(String[] args) { 
        new PanelLayoutExam(); 
    } 
}
