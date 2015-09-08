package com.wxsm.kk.ums.controller;

import com.oocl.kary.pojo.User;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.wxsm.kk.ums.action.Action;
import com.wxsm.kk.ums.action.impl.AddAction;
import com.wxsm.kk.ums.action.impl.DeleteAction;
import com.wxsm.kk.ums.action.impl.FindAction;
import com.wxsm.kk.ums.action.impl.ListAction;
import com.wxsm.kk.ums.action.impl.QuitAction;
import com.wxsm.kk.ums.action.impl.SortAction;
import com.wxsm.kk.ums.service.CommandReader;
import com.wxsm.kk.ums.service.Displayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主控制器
 * 
 * @author GUOKA2
 * 
 */
public class MainController {

	private UserDaoImpl dao = new UserDaoImpl();
	private List<User> list;
	private String[] commands;
	private boolean isExit = false;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public boolean isExit() {
		return isExit;
	}

	private Displayer displayer = new Displayer();
	private CommandReader commandReader = new CommandReader();

	@SuppressWarnings("rawtypes")
	private Map<String, Class> actionMap;

	@SuppressWarnings("rawtypes")
	public MainController() {
		commands = new String[] { Action.LIST, Action.DELETE, Action.FIND,
				Action.SORT, Action.ADD, Action.QUIT };
		actionMap = new HashMap<String, Class>();
		actionMap.put(Action.ADD, AddAction.class);
		actionMap.put(Action.LIST, ListAction.class);
		actionMap.put(Action.FIND, FindAction.class);
		actionMap.put(Action.DELETE, DeleteAction.class);
		actionMap.put(Action.SORT, SortAction.class);
		actionMap.put(Action.QUIT, QuitAction.class);
	}

	public void init() {
		list = dao.findAll();
		displayer.welcomeMessage();
		execCommand("-l");
	}

	public void execCommand(String command) {
		if (commandReader.setCommand(command)) {
			if (actionMap.containsKey(commandReader.getCommand())) {
				try {
					Action action = (Action) actionMap.get(
							commandReader.getCommand()).newInstance();
					action.execute(commandReader.getParams(), this);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				if (!isExit) {
					displayer.commands(commands);
				}
			}
		} else {
			displayer.error("指令格式不正确");
		}
	}
}
