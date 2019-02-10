package util;

import java.util.TimerTask;

import javafx.scene.text.Text;

public class Countdown extends TimerTask {
	private WrapInt seconds;
	private Text text;
	public Countdown(WrapInt seconds, Text text) {
		this.seconds = seconds;
		this.text = text;
	}

	@Override
	public void run() {
		seconds.i--;
		
		int mins = seconds.i / 60;
		int secs = seconds.i % 60;
		
		String time = mins + " : " + secs; 
		text.setText(time);
	}
	
}
