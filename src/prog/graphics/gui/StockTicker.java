package prog.graphics.gui;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

import prog.core.GlobalTimer;
import prog.core.provider.*;

public class StockTicker extends JFrame{
	private static final int TICK_PERIOD = 1000;
	private GlobalTimer timer = GlobalTimer.getInstance();
	private JLabel clockLabel;
	private StockPriceProvider provider;
	
	private class TickerTask extends TimerTask {
		public void run() {
			String output = createText();
			clockLabel.setText(output);
			clockLabel.repaint();
		}
		
		private String createText(){
			String output = "<html><body>"; 
			
			output += "Stock Prices:<br><br>";
			output += provider.shareInfo().replaceAll("\n\n", "<br>").replaceAll("\n", ":&nbsp;");
			output += "<br>";
			
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance();
            output += dateFormatter.format(date) + "</body></html>";
            return output;
		}
	}
	
	public StockTicker(StockPriceProvider provider) {
		this.provider = provider;
		
		clockLabel = new JLabel("Stock Ticker");
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(175, 300);
        setVisible(true);
	}
	
	public void start(){
		timer.scheduleAtFixedRate(new TickerTask(), 1000, TICK_PERIOD);
	}

}
