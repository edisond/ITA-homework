package com.oocl.kk.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;
import com.oocl.kk.dao.impl.UserDaoImpl;
import com.oocl.kk.jms.action.Action;
import com.oocl.kk.jms.action.GetUserAction;
import com.oocl.kk.jms.action.LoginAction;

/**
 * Hello world!
 * 
 */
public class Jms {
	public static final UserDaoImpl DAO = new UserDaoImpl();
	public static final Gson GSON = new Gson();
	@SuppressWarnings("rawtypes")
	private Map<String, Class> actionMap;

	@SuppressWarnings("rawtypes")
	public Jms() {
		actionMap = new HashMap<String, Class>();
		actionMap.put(Action.LOGIN, LoginAction.class);
		actionMap.put(Action.GET_USER, GetUserAction.class);
	}

	public void start() throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				"failover://tcp://localhost:61616");
		Queue queue = new ActiveMQQueue("kk_c2s");
		Connection con = factory.createConnection();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(queue);
		con.start();

		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message msg) {
				TextMessage textMessage = (TextMessage) msg;
				try {
					String json = textMessage.getText();
					Packet packet = GSON.fromJson(json, Packet.class);
					try {
						Action action=(Action) actionMap.get(packet.getType()).newInstance();
						action.execute(packet);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws JMSException {
		new Jms().start();
	}
}
