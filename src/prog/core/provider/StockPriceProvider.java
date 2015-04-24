package prog.core.provider;

import prog.core.GlobalTimer;
import prog.core.Share;
import prog.interfaces.StockPriceInfo;
import prog.exception.ObjectNotFoundException;
import prog.exception.ObjectAlreadyExistsException;
import java.util.TimerTask;

public abstract class StockPriceProvider implements StockPriceInfo {
	//Create a few shares to play around with
	protected Share[] shares;
	//Set a global timer
	protected GlobalTimer timer = GlobalTimer.getInstance();
	
	public StockPriceProvider(){
		shares = new Share[0];
		startUpdate();
	}
	
	public void createShare(String shareName, long price){
		if(isShareListed(shareName)){
			throw new ObjectAlreadyExistsException();
		}
		
		Share[] buffer = new Share[shares.length+1];
		
		for(int i=0; i<shares.length; i++){
			buffer[i] = shares[i];
		}
		
		buffer[buffer.length-1] = new Share(shareName, price);
		shares = buffer;
	}
	
	//Check if a share is listed or not
	public boolean isShareListed(String shareName){
		for(int i=0; i<shares.length; i++){
			if(shares[i].getName().equals(shareName)){
				return true;
			}
		}
		
		return false;
	}
	
	//Get the current share rate of a share
	public long getCurrentShareRate(String shareName){
		for(int i=0; i<shares.length; i++){
			if(shares[i].getName().equals(shareName)){
				return shares[i].getPrice();
			}
		}
		
		//Share doesn't exist
		throw new ObjectNotFoundException("Share " + shareName + " doesn't exist");
	}
	
	//Get a copy of all shares
	public Share[] getAllSharesAsSnapshot(){
		//Create buffer
		Share[] copy = new Share[shares.length];
		
		//Create deep copy
		for(int i=0; i<copy.length; i++){
			copy[i] = new Share(shares[i]);
		}
		
		return copy;
	}
	
	//Get a copy of a specific share
	public Share getShare(String shareName){
		for(int i=0; i<shares.length; i++){
			if(shares[i].getName().equals(shareName)){
				Share copy = shares[i];
				return copy;
			}
		}
		
		//Share doesn't exist
		throw new ObjectNotFoundException("Share " + shareName + " doesn't exist");
	}
	
	//Start auto-updating share rates
	public void startUpdate(){
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				updateShareRates();
			}
		}, 3000, 1000);
	}
	
	//Get info
	public String shareInfo(){
		String out = "";
		
		for(int i=0; i<shares.length; i++){
			out += shares[i].getName() + "\n" + shares[i].getPrice() + "\n\n";
		}
		
		return out;
		
	}
	
	public String shareList(){
		String out = "";
		
		for(int i=0; i<shares.length; i++){
			out += shares[i].getName() + "\n";
		}
		
		return out;
	}
	
	//Update all share rates
	protected void updateShareRates(){
		for(int i=0; i<shares.length; i++){
			updateShareRate(shares[i]);
		}
	}
	
	//Update a single shares share rate
	protected abstract void updateShareRate(Share share);
}
