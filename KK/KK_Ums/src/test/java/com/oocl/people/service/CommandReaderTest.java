package com.oocl.people.service;

import junit.framework.Assert;

import org.junit.Test;

import com.oocl.kk.ums.service.CommandReader;

@SuppressWarnings("deprecation")
public class CommandReaderTest {

	public static CommandReader cr= new CommandReader();
	
	@Test
	public void trimTest(){
		cr.setCommand("\t -l 1    ");
		Assert.assertTrue(cr.getRawCommand().matches("^\\S.*\\S$"));
		cr.setCommand("-l 1");
		Assert.assertTrue(cr.getRawCommand().matches("^\\S.*\\S$"));
	}
	
	@Test
	public void getCommandTest(){
		cr.setCommand("-a 1");
		Assert.assertTrue(cr.getCommand().equals("a"));
		cr.setCommand("-b 1,2,3-5");
		Assert.assertTrue(cr.getCommand().equals("b"));
		cr.setCommand("  -abc 1,2,3-5 ");
		Assert.assertTrue(cr.getCommand().equals("abc"));
	}
	
	@Test
	public void getParamsTest(){
		cr.setCommand("-b 1,2,3-5");
		String[] params=cr.getParams();
		Assert.assertTrue(params[0].equals("1"));
		Assert.assertTrue(params[1].equals("2"));
		Assert.assertTrue(params[2].equals("3-5"));
		
		cr.setCommand("-f  name:john  , sex:male ,   ");
		params=cr.getParams();
		Assert.assertTrue(params[0].equals("name:john"));
		Assert.assertTrue(params[1].equals("sex:male"));
		
	}
}
