package prog.ui.console;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

import prog.core.AccountManagerImpl;
import prog.ui.CommandScanner;
import prog.core.enumerator.StockGameCommandType;
import prog.ui.CommandDescriptor;
import prog.exception.GameException;
import prog.exception.GameRuntimeException;

public class StockGameCommandProcessor {
	private AccountManagerImpl manager;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public StockGameCommandProcessor(AccountManagerImpl accountManager){
		manager = accountManager;
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(System.out);
	}
	
	private void handleGameException(GameException e){
		writer.println(e.getMessage());
		writer.flush();
		process();
	}
	
	private void handleGameRuntimeException(GameRuntimeException e){
		writer.println(e.getMessage());
		writer.flush();
		process();
	}
	
	public void process(){
		CommandScanner scanner = new CommandScanner(StockGameCommandType.values(), reader);
		
		//Main loop
		while(true){
			CommandDescriptor commandDescriptor = new CommandDescriptor();
			
			commandDescriptor = scanner.commandLine2CommandDescriptor(commandDescriptor);
			
			Object[] params = commandDescriptor.getParams();
			
			StockGameCommandType commandType = (StockGameCommandType)commandDescriptor.getCommandType();
			
			try{
				switch(commandType){
				case EXIT:
					writer.println("Bye");
					writer.flush();
					System.exit(0);
					break;
				case HELP:
					writer.println(commandType.getHelpText());
					break;
				case CREATEPLAYER:
					writer.println("Attempting to create player...");
					manager.createPlayer((String)params[0]);
					writer.println("Done");
					break;
				case BUYSHARE:
					writer.println("Attempting to buy share...");
					manager.buyShares((String)params[0], (String)params[1], (int)params[2]);
					writer.println("Done");
					break;
				case LISTPLAYERS:
					writer.println(manager.playerList());
					break;
				default:
					writer.println("Command not found");
					break;
				}
			}catch(GameException e){
				handleGameException(e);
			}catch(GameRuntimeException e){
				handleGameRuntimeException(e);
			};
			
		writer.flush();
		}
	}
}
