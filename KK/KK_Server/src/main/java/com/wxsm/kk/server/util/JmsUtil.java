package com.wxsm.kk.server.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import com.google.gson.Gson;
import com.oocl.kary.pojo.Packet;

public class JmsUtil {
	private static final String URL = "failover://tcp://localhost:61616";
	private static final String PRODUCE_QUEUE = "kk_c2s";
	private static final String CONSUME_TOPIC = "kk_s2c";
	private static final Gson GSON = new Gson();

	public static void produce(String msg) {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Queue queue = new ActiveMQQueue(PRODUCE_QUEUE);
		Connection con = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			con = factory.createConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(queue);
			con.start();
			producer.send(session.createTextMessage(msg));
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			free(con, session, producer, null);
		}
	}

	public static String consume(String token) {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Topic topic = new ActiveMQTopic(CONSUME_TOPIC);
		Connection con = null;
		Session session = null;
		MessageConsumer consumer = null;
		TextMessage result = null;
		try {
			con = factory.createConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			consumer = session.createConsumer(topic);
			con.start();
			while (true) {
				result = (TextMessage) consumer.receive();
				Packet p = GSON.fromJson(result.getText(), Packet.class);
				if (p.getToken().equals(token)) {
					break;
				}
			}
			return result.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			free(con, session, null, consumer);
		}
		return null;
	}

	public static void free(Connection con, Session session,
			MessageProducer producer, MessageConsumer consumer) {
		try {
			if (consumer != null)
				consumer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			if (producer != null)
				producer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			if (session != null)
				session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
