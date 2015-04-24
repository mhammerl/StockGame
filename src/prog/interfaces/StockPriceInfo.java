package prog.interfaces;

import prog.core.Share;

public interface StockPriceInfo{

	public boolean isShareListed(String shareName);

	public long getCurrentShareRate(String shareName);
	
	public Share[] getAllSharesAsSnapshot();
}
