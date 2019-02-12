package util;

import java.util.TimerTask;

import javafx.scene.text.Text;

public class Countdown extends TimerTask {
	private WrapInt seconds;
	private Text text, resultText;
	public Countdown(WrapInt seconds, Text text, Text resultText) {
		this.seconds = seconds;
		this.text = text;
		this.resultText = resultText;
	}

	@Override
	public void run() {
		seconds.i--;
		
		if (seconds.i == 0) {
			resultText.setText(Constant.TIMEOUT);
			this.cancel();
		}
		
		int mins = seconds.i / 60;
		int secs = seconds.i % 60;
		
		String time = mins + " : " + secs; 
		text.setText(time);
	}
	
}
