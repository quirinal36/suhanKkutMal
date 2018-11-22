package file.to.json;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Makelogin implements ActionListener {

	JPanel jp = new JPanel();
	JLabel jl = new JLabel("끝말잇기");

	JTextField id = new JTextField("아이디");
	JTextField pw = new JTextField("비밀번호");


	JLabel result;
	JButton btnlogin = new JButton("로그인");
	JButton btnjoin = new JButton("회원가입");
	JButton btnvoca = new JButton("단어장");

	public Makelogin(){
		JFrame frame = new JFrame("MAIN");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500, 400);
		frame.setVisible(true); 
		frame.getContentPane().setLayout(new GridLayout(4,1));
		frame.getContentPane().add(jl);
		//frame.getContentPane().add(id);
		//frame.getContentPane().add(pw);
		
		
		jl.setFont(new Font(null, Font.BOLD, 25));
		jl.setHorizontalAlignment(JLabel.CENTER);
		
		btnlogin.setBounds(140, 270, 100, 40);
		btnjoin.setBounds(250, 270, 100, 40);
		btnvoca.setBounds(380, 320, 100, 20);

		//id.setBounds(200, 140, 100, 50);
		//pw.setBounds(200, 210, 100, 50);
		
		//jp.add(btnlogin);
		//jp.add(id); // jp라는 패널에 tf라는 텍스트필드 추가
		//jp.add(btnjoin);
		//jp.add(pw); 
		//jp.add(btnvoca);
		//jp.add(jl); 
		
		//frame.add(jp);
		
		frame.validate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
