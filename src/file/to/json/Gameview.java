package file.to.json;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gameview extends JFrame implements ActionListener{
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	JPanel panel = new JPanel();
	JLabel me = new JLabel("나");
	JLabel you = new JLabel("상대");
	JLabel labelTimer = new JLabel("타이머");
	JButton button = new JButton("new game");
	
	JButton btnInput = new JButton("입력");
	
	JTextField input = new JTextField("입력창");
	JTextField output = new JTextField("결과창");
	Timer timerSec = new Timer();
	int sec;
	public Gameview()
	{
		JFrame frame = new JFrame("GAME");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new GridLayout(2,4));
		
		frame.getContentPane().add(me);
		frame.getContentPane().add(labelTimer);
		frame.getContentPane().add(you);
		

		final int hGap = 10;
		final int vGap = 20;

		JPanel ipPanel = new JPanel(new BorderLayout(50,20));
		ipPanel.setBorder(
				BorderFactory.createEmptyBorder(hGap, vGap, hGap,vGap));

		ipPanel.add(input,BorderLayout.CENTER);
		
		frame.getContentPane().add(ipPanel);
		
		JPanel btnPanel = new JPanel(new BorderLayout(50,30));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
		JPanel btnCenterPanel = new JPanel(new GridLayout(2,4,20,0));

		btnCenterPanel.add(btnInput);
		btnCenterPanel.add(button);
		btnPanel.add(btnCenterPanel,BorderLayout.CENTER);
		frame.getContentPane().add(btnPanel);
		

		JPanel opPanel = new JPanel(new BorderLayout(20,20));
		opPanel.setBorder(
				BorderFactory.createEmptyBorder(hGap, vGap, hGap,vGap));

		opPanel.add(output,BorderLayout.CENTER);
		
		frame.getContentPane().add(opPanel);
		
		JPanel tiPanel = new JPanel(new BorderLayout(20,20));
		tiPanel.setBorder(
				BorderFactory.createEmptyBorder(hGap, vGap, hGap,vGap));

		
		
		
		

		me.setFont(new Font(null, Font.BOLD, 30));
		me.setHorizontalAlignment(JLabel.CENTER);
		
		labelTimer.setFont(new Font(null, Font.BOLD, 30));
		labelTimer.setHorizontalAlignment(JLabel.CENTER);
		
		you.setFont(new Font(null, Font.BOLD, 30));
		you.setHorizontalAlignment(JLabel.CENTER);
		
		btnInput.addActionListener(this);

		
		button.addActionListener(this);
	
		

		frame.validate();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == button) {
		sec = 0;
		timerSec.cancel();
		timerSec = new Timer();

		timerSec.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				labelTimer.setText("  " +sec+" 초");
				sec=sec+1;
			}
		},500,1000);
	}
	}


	public static void main(String[] args) {
		
		new Gameview();

	}
}
