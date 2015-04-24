package prog.interfaces;

import prog.exception.FundsExceededException;
import prog.exception.NotEnoughSharesException;
import prog.core.Asset;

public interface AccountManager {
	
	public void createPlayer(String name);
	
	public void buyShares(String playerName, String shareName, int quantity) throws FundsExceededException;
	
	public void sellShare(String playerName, String shareName, int quantity) throws NotEnoughSharesException;
	
	public long assetValue(Asset asset);
	
	public long playerValue(String playerName);
	
	public long sharePrice(String shareName);
	
	public String availableShares();

}
