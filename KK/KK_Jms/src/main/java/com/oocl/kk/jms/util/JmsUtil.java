package com.oocl.kk.jms.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

public class JmsUtil {
	private static final String URL = "failover://tcp://localhost:61616";
	private static final String TOPIC = "kk_s2c";

	public static void produce(String msg) {
		ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
		Topic topic = new ActiveMQTopic(TOPIC);
		Connection con = null;
		Session session = null;
		MessageProducer producer = null;
		try {
			con = factory.createConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(topic);
			con.start();
			producer.send(session.createTextMessage(msg));
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			free(con, session, producer, null);
		}
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
