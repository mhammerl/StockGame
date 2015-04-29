package prog.ui;

import prog.interfaces.CommandTypeInfo;

public class CommandDescriptor {
	CommandTypeInfo cmdType;
	Object[] params;
	
	public void setCommandType(CommandTypeInfo commandType){
		cmdType = commandType;
	}
	
	public void setParams(Object[] params){
		this.params = params;
	}
	
	public CommandTypeInfo getCommandType(){
		return cmdType;
	}
	
	public Object[] getParams(){
		return params;
	}
}
