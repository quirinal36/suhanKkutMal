package file.to.json;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.control.Button;

public class TimerTest extends JFrame{
	JButton button;
	JLabel labelTimer;
	Timer timerSec = new Timer();
	int sec;


	public TimerTest() {
		super("TimerTest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 1));

		labelTimer = new JLabel();
		labelTimer.setFont(new Font("GOTHIC",Font.BOLD,20));
		labelTimer.setHorizontalAlignment(NORMAL);

		button = new JButton("new game");
		button.addActionListener(new MyActionListener());
		add(button);
		add(labelTimer);
		setSize(100,100);
		setVisible(true);

	}
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

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

				new TimerTest();
			}

		}

