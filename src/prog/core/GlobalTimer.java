package prog.core;

import java.util.Timer;
import java.util.TimerTask;

public class GlobalTimer {
	private static GlobalTimer globalTimer;
	private Timer timer;
	
	private GlobalTimer() {};
	
	public static GlobalTimer getInstance(){
		if(globalTimer == null){
			globalTimer = new GlobalTimer();
		}
		
		return globalTimer;
	}
	
	public void startTiming(Runnable runnable, long delay, long period){
		if(timer == null){
			timer = new Timer();
		}
		
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				runnable.run();
			}
		}, delay, period);
	}
	
	public void stopTiming(){
		timer.cancel();
		timer = null;
	}
}
