package com.wxsm.kk.ums.service;

/**
 * 读取与分割指令，判断合法性 指令形式： -c param1, param2, param3
 * 
 * @author GUOKA2
 * 
 */
public class CommandReader {

	private String rawCommand;
	private String command;
	private String[] params;

	/**
	 * 读取原始指令并得到指令与参数集
	 * 
	 * @param command
	 *            传入的指令
	 * @return 指令合法则返回true，否则返回false
	 */
	public boolean setCommand(String command) {
		if (command.matches("^\\s*-[a-zA-Z]+.*\\s*$")) {
			/**
			 * 去除头尾空格，设置原始指令
			 */
			rawCommand = command.trim();

			/**
			 * 获取指令
			 */
			this.command = rawCommand.substring(rawCommand.indexOf("-") + 1,
					rawCommand.indexOf("-") + 2);

			/**
			 * 获取参数集，并对每个参数去除头尾空格
			 */
			params = rawCommand.substring(rawCommand.indexOf("-") + 2).split(
					",");

			if (params.length == 1 && params[0].equals("")) {
				params = new String[0];
			}

			for (int i = 0; i < params.length; i++) {
				params[i] = params[i].trim();
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取带指令与参数的原始命令
	 * @return
	 */
	public String getRawCommand() {
		return rawCommand;
	}

	/**
	 * 获取指令
	 * @return
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 获取参数
	 * @return
	 */
	public String[] getParams() {
		return params;
	}

}
