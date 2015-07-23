package com.oocl.kary.kk.ui.prototypes;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oocl.kary.kk.client.ClientIn;
import com.oocl.kary.kk.client.ClientOut;
import com.oocl.kary.kk.model.KPacket;
import com.oocl.kary.kk.model.Message;
import com.oocl.kary.kk.model.User;

/**
 * 主界面
 * 
 * @author edisond@qq.com & Jason
 * 
 */
public class MainFrame extends JFrame {

	private Container contactContainer, chatContainer, chatRecordContainer,
			inputContainer;
	private JScrollPane contactScrollPane, chatRecordScrollPane;
	private JTextArea chatRecordTextArea, inputTextArea;
	private JButton sendButton, shakeButton;
	private JList<String> contactList;

	private String id;
	private String token;
	private Socket socket;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	private List<User> users;
	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void getUsersFromServer() throws IOException {
		KPacket packet = new KPacket("getuser");
		new Thread(new ClientOut(socket, packet)).start();
	}

	/**
	 * 构建方法
	 * 
	 * @param id
	 *            用户id
	 * @param token
	 *            用户验证token
	 * @throws IOException
	 */
	public MainFrame(String id, String token, Socket socket) throws IOException {
		this.id = id;
		this.token = token;
		this.socket = socket;
		messages = new LinkedList<Message>();

		new Thread(new ClientIn(this)).start();

		getUsersFromServer();

		initContactContainer();
		initChatContainer();
		initMainFrame();
	}

	/**
	 * 初始化联系人面板
	 */
	public void initContactContainer() {
		contactContainer = new Container();
		contactContainer.setLayout(null);
		contactContainer.setBounds(CONTACT_CONTAINER_RECTANGLE);
		// SubComponent:contactScrollPane->contactList
		contactList = new JList<>();
		contactScrollPane = new JScrollPane(contactList);
		contactScrollPane.setBorder(new TitledBorder(CONTACT_TITLE));
		contactScrollPane.setBounds(CONTACT_SCROLLPANE_RECTANGLE);
		// Merge
		contactContainer.add(contactScrollPane);

		contactList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedContactIndex = contactList.getSelectedIndex();
				if (selectedContactIndex == -1) {
					return;
				} else if (selectedContactIndex == users.size()) {
					chatRecordTextArea.setBorder(new TitledBorder(contactList
							.getSelectedValue()));
					updateChatContainer();
					shakeButton.setVisible(false);
				} else {
					User targetUser = users.get(selectedContactIndex);
					String targetUserName = targetUser.getUsername();
					chatRecordTextArea.setBorder(new TitledBorder("chat with "
							+ targetUserName));
					updateChatContainer();
					shakeButton.setVisible(true);
				}
			}
		});

	}

	/**
	 * 更新联系人面板
	 */
	public void updateContactContainer() {

		String[] list = new String[users.size() + 1];
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(id)) {
				continue;
			}
			User u = users.get(i);
			list[i] = u.getUsername()
					+ (u.getState().equals("online") ? "    ✓" : "");
		}
		list[list.length - 1] = "Group chat";
		contactList.setListData(list);
		contactList.updateUI();
	}

	/**
	 * 初始化聊天面板
	 */
	private void initChatContainer() {
		chatContainer = new Container();
		chatContainer.setLayout(null);
		chatContainer.setBounds(CHAT_CONTAINER_RECTANGLE);

		chatRecordContainer = new Container();
		chatRecordContainer.setLayout(null);
		chatRecordContainer.setBounds(CHAT_RECORD_CONTAINER_RECTANGLE);

		chatRecordTextArea = new JTextArea();
		chatRecordTextArea.setLineWrap(true);
		chatRecordTextArea.setWrapStyleWord(true);
		chatRecordTextArea.setEditable(false);
		chatRecordTextArea.setBorder(new TitledBorder("KK"));

		chatRecordScrollPane = new JScrollPane(chatRecordTextArea);
		chatRecordScrollPane.setBounds(CHAT_RECORD_SCROLLPANE_RECTANGLE);
		chatRecordContainer.add(chatRecordScrollPane);

		inputContainer = new Container();
		inputContainer.setLayout(null);
		inputContainer.setBounds(INPUT_CONTAINER_RECTANGLE);

		inputTextArea = new JTextArea();
		inputTextArea.setBorder(new TitledBorder(INPUT_TITLE));
		inputTextArea.setBounds(INPUT_TEXTAREA_RECTANGLE);

		sendButton = new JButton(SEND);
		sendButton.setBounds(SEND_BUTTON_RECTANGLE);

		shakeButton = new JButton(SHAKE);
		shakeButton.setBounds(SHAKE_BUTTON_RECTANGLE);

		inputContainer.add(inputTextArea);
		inputContainer.add(sendButton);
		inputContainer.add(shakeButton);

		chatContainer.add(chatRecordContainer);
		chatContainer.add(inputContainer);

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doChat();
			}

		});

		shakeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doShake();
			}

		});
		getRootPane().setDefaultButton(sendButton);
	}

	/**
	 * 震动
	 */
	public void doShake() {
		if (contactList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Please select a contact.");
		} else {
			/**
			 * 封装信息包
			 */
			KPacket packet = new KPacket("shake");
			packet.from = new User(id);
			packet.to = new User[] { users.get(contactList.getSelectedIndex()) };

			/**
			 * 发送消息包
			 */
			new Thread(new ClientOut(socket, packet)).start();
		}
	}

	/**
	 * 聊天
	 */
	public void doChat() {
		String msg = inputTextArea.getText();
		int selectedContactIndex = contactList.getSelectedIndex();

		if (msg.equals("")) {
			inputTextArea.grabFocus();
		} else if (contactList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Please select a contact.");
		} else if (contactList.getSelectedIndex() == users.size()) {
			/**
			 * 群聊信息包
			 */
			KPacket packet = new KPacket("groupchat");
			packet.from = new User(id);
			packet.to = users.toArray(new User[0]);
			packet.body = msg;
			/**
			 * 此对象供客户端刷新聊天框用
			 */
			Message msgObj = new Message();
			msgObj.from = id;
			msgObj.groupChat = true;
			msgObj.body = msg;
			msgObj.date = Calendar.getInstance();
			messages.add(msgObj);

			/**
			 * 更新聊天框
			 */
			updateChatContainer();
			inputTextArea.setText("");

			/**
			 * 发送消息包
			 */
			new Thread(new ClientOut(socket, packet)).start();

		} else {
			/**
			 * 封装信息包
			 */
			KPacket packet = new KPacket("chat");
			packet.from = new User(id);
			packet.to = new User[] { users.get(selectedContactIndex) };
			packet.body = msg;

			/**
			 * 此对象供客户端刷新聊天框用
			 */
			Message msgObj = new Message();
			msgObj.from = id;
			msgObj.to = users.get(contactList.getSelectedIndex()).getId();
			msgObj.groupChat = false;
			msgObj.body = msg;
			msgObj.date = Calendar.getInstance();
			messages.add(msgObj);

			/**
			 * 更新聊天框
			 */
			updateChatContainer();
			inputTextArea.setText("");

			/**
			 * 发送消息包
			 */
			new Thread(new ClientOut(socket, packet)).start();
		}
	}

	/**
	 * 更新聊天面板
	 */
	public void updateChatContainer() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		int selectedContactIndex = contactList.getSelectedIndex();

		if (selectedContactIndex == -1) {
			return;
		} else if (selectedContactIndex == users.size()) {
			String str = "";
			for (Message msg : messages) {
				if (msg.from.equals(id) && msg.groupChat) {
					str += "me ";
					str += sdf.format(msg.date.getTime());
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				} else if (msg.groupChat) {
					str += msg.from + " ";
					str += sdf.format(msg.date.getTime());
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				}
			}
			chatRecordTextArea.setText(str);
		} else {
			User targetUser = users.get(selectedContactIndex);
			String str = "";
			for (Message msg : messages) {
				if (!msg.groupChat && msg.to.equals(targetUser.getId())
						&& msg.from.equals(id)) {
					str += "me ";
					str += sdf.format(msg.date.getTime());
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				} else if (!msg.groupChat
						&& msg.from.equals(targetUser.getId())
						&& msg.to.equals(id)) {
					str += targetUser.getUsername() + " ";
					str += sdf.format(msg.date.getTime());
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				}
			}
			chatRecordTextArea.setText(str);
		}

	}

	/**
	 * 初始化主面板
	 */
	private void initMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(contactContainer);
		add(chatContainer);
		setSize(MAIN_FRAME_SIZE);
		setLocationRelativeTo(null);
		setTitle(FRAME_TITLE + " - Logined as " + id);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 窗口配置信息
	 */
	private static final long serialVersionUID = 1L;
	public static final Dimension MAIN_FRAME_SIZE = new Dimension(900, 600);
	public static final Rectangle CONTACT_CONTAINER_RECTANGLE = new Rectangle(
			0, 0, 300, 600);
	public static final Rectangle CONTACT_SCROLLPANE_RECTANGLE = new Rectangle(
			0, 0, 300, 600);
	public static final Rectangle CHAT_CONTAINER_RECTANGLE = new Rectangle(300,
			0, 600, 600);
	public static final Rectangle CHAT_RECORD_CONTAINER_RECTANGLE = new Rectangle(
			300, 0, 600, 400);
	public static final Rectangle CHAT_RECORD_SCROLLPANE_RECTANGLE = new Rectangle(
			0, 0, 600, 400);
	public static final Rectangle INPUT_CONTAINER_RECTANGLE = new Rectangle(
			300, 400, 600, 200);
	public static final Rectangle INPUT_TEXTAREA_RECTANGLE = new Rectangle(0,
			0, 600, 150);
	public static final Rectangle SEND_BUTTON_RECTANGLE = new Rectangle(500,
			150, 100, 30);
	public static final Rectangle SHAKE_BUTTON_RECTANGLE = new Rectangle(400,
			150, 100, 30);

	public static final String SEND = "Send";
	public static final String SHAKE = "Shake";
	public static final String FRAME_TITLE = "KK";
	public static final String CONTACT_TITLE = "Contact List";
	public static final String INPUT_TITLE = "Input Area";

}
