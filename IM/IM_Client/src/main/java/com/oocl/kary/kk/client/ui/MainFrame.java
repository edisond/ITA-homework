package com.oocl.kary.kk.client.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.oocl.kary.kk.client.model.KPacket;
import com.oocl.kary.kk.client.model.User;
import com.oocl.kary.kk.client.service.Response;
import com.oocl.kary.kk.client.service.Request;

/**
 * 主界面
 * 
 * @author edisond@qq.com & Jason
 * 
 */
public class MainFrame extends JFrame implements Shakable {

	/**
	 * 窗口配置信息
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension MAIN_FRAME_SIZE = new Dimension(920, 620);
	private static final Rectangle CONTACT_CONTAINER_RECTANGLE = new Rectangle(
			0, 0, 300, 600);
	private static final Rectangle CONTACT_SCROLLPANE_RECTANGLE = new Rectangle(
			0, 0, 300, 600);
	private static final Rectangle CHAT_CONTAINER_RECTANGLE = new Rectangle(
			300, 0, 600, 600);
	private static final Rectangle CHAT_RECORD_CONTAINER_RECTANGLE = new Rectangle(
			300, 0, 600, 400);
	private static final Rectangle CHAT_RECORD_SCROLLPANE_RECTANGLE = new Rectangle(
			0, 0, 600, 400);
	private static final Rectangle INPUT_CONTAINER_RECTANGLE = new Rectangle(
			300, 400, 600, 200);
	private static final Rectangle INPUT_TEXTAREA_RECTANGLE = new Rectangle(0,
			0, 600, 150);
	private static final Rectangle SEND_BUTTON_RECTANGLE = new Rectangle(500,
			150, 100, 30);
	private static final Rectangle SHAKE_BUTTON_RECTANGLE = new Rectangle(400,
			150, 100, 30);
	private static final String SEND = "Send";
	private static final String SHAKE = "Shake";
	private static final String FRAME_TITLE = "KK";
	private static final String CONTACT_TITLE = "Contact List";
	private static final String INPUT_TITLE = "Input Area";

	/**
	 * 主要控件
	 */
	private Container contactContainer, chatContainer, chatRecordContainer,
			inputContainer;
	private JScrollPane contactScrollPane, chatRecordScrollPane;
	private JTextArea chatRecordTextArea, inputTextArea;
	private JButton sendButton, shakeButton;
	private JList<String> contactList;

	/**
	 * 当前用户id，token以及Socket
	 */
	private String id;
	private String token;
	private Socket socket;

	/**
	 * 所有user信息
	 */
	private List<User> users;

	/**
	 * 已发送或接受到的聊天消息包
	 */
	private List<KPacket> msgPackets;

	/**
	 * 协议包时间戳格式
	 */
	private static final SimpleDateFormat packetDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd-hh-mm-ss");

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

	public List<KPacket> getMsgPackets() {
		return msgPackets;
	}

	public void setMsgPackets(List<KPacket> msgPackets) {
		this.msgPackets = msgPackets;
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
		msgPackets = new LinkedList<KPacket>();

		/**
		 * 启动永动监听器
		 */
		new Thread(new Response(this)).start();

		/**
		 * 发送getuser包，请求刷新用户列表
		 */
		KPacket packet = new KPacket("getuser");
		new Thread(new Request(socket, packet)).start();

		/**
		 * 初始化GUI
		 */
		initContactContainer();
		initChatContainer();
		initMainFrame();
	}

	/**
	 * 初始化联系人面板
	 */
	private void initContactContainer() {
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

			list[i] = "<html>";

			list[i] += u.getState().equals("online") ? "<img src='file:online.png'>"
					: "<img src='file:offline.png'>";

			list[i] += u.getUsername();

			list[i] += "</html>";

			// for (KPacket p : msgPackets) {
			// if (p.read == false && p.from.getId().equals(u.getId())) {
			// list[i] += "    [new]";
			// break;
			// }
			// }

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
	 * 更新聊天面板
	 */
	public void updateChatContainer() {
		int selectedContactIndex = contactList.getSelectedIndex();

		if (selectedContactIndex == -1) {
			return;
		} else if (selectedContactIndex == users.size()) {
			String str = "";
			for (KPacket msg : msgPackets) {
				if (msg.from.getId().equals(id) && msg.type.equals("groupchat")) {
					str += "me ";
					str += msg.stamp;
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				} else if (msg.type.equals("groupchat")) {
					String fromUserName = "";
					for (User u : users) {
						if (u.getId().equals(msg.from.getId())) {
							fromUserName = u.getUsername();
						}
					}
					str += fromUserName + " ";
					str += msg.stamp;
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				}
			}
			chatRecordTextArea.setText(str);
		} else {
			User targetUser = users.get(selectedContactIndex);
			String str = "";
			for (KPacket msg : msgPackets) {
				if (msg.type.equals("chat")
						&& msg.to[0].getId().equals(targetUser.getId())
						&& msg.from.getId().equals(id)) {
					str += "me ";
					str += msg.stamp;
					str += "\r\n";
					str += msg.body;
					str += "\r\n\r\n";
				} else if (msg.type.equals("chat")
						&& msg.from.getId().equals(targetUser.getId())
						&& msg.to[0].getId().equals(id)) {
					str += targetUser.getUsername() + " ";
					str += msg.stamp;
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
			new Thread(new Request(socket, packet)).start();
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
			return;
		} else if (contactList.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Please select a contact.");
			return;
		} else if (contactList.getSelectedIndex() == users.size()) {
			/**
			 * 群聊信息包
			 */
			KPacket packet = new KPacket("groupchat");
			packet.from = new User(id);
			packet.to = users.toArray(new User[0]);
			packet.body = msg;
			packet.stamp = packetDateFormat.format(Calendar.getInstance()
					.getTime());

			msgPackets.add(packet);

			/**
			 * 更新聊天框
			 */
			updateChatContainer();
			inputTextArea.setText("");

			/**
			 * 发送消息包
			 */
			new Thread(new Request(socket, packet)).start();

		} else {
			/**
			 * 单聊信息包
			 */
			KPacket packet = new KPacket("chat");
			packet.from = new User(id);
			packet.to = new User[] { users.get(selectedContactIndex) };
			packet.body = msg;
			packet.stamp = packetDateFormat.format(Calendar.getInstance()
					.getTime());

			msgPackets.add(packet);

			/**
			 * 更新聊天框
			 */
			updateChatContainer();
			inputTextArea.setText("");

			/**
			 * 发送消息包
			 */
			new Thread(new Request(socket, packet)).start();
		}
	}

	/**
	 * 震动窗口功能相关变量
	 */
	// 窗口距离中心左右晃动的最大距离
	public static final int SHAKE_DISTANCE = 10;
	// 窗口晃动一个循环（中间，右，中间，左， 中间）所需要的时间 (ms),
	// 这个值越小， 晃动的就越快。
	public static final double SHAKE_CYCLE = 80;
	// 整个晃动所需要的时间。
	public static final int SHAKE_DURATION = 300;
	// 这个是设定 Swing 多长时间（ ms ）更新窗口的位置。
	public static final int SHAKE_UPDATE = 5;

	private Point naturalLocation;
	private long startTime;
	private Timer shakeTimer;
	@SuppressWarnings("unused")
	private final double HALF_PI = Math.PI / 2.0;
	private final double TWO_PI = Math.PI * 2.0;

	@Override
	public void startShake() {
		if (shakeTimer == null) {
			naturalLocation = getLocation();
			startTime = System.currentTimeMillis();
			shakeTimer = new Timer(SHAKE_UPDATE, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					long elapsed = System.currentTimeMillis() - startTime;
					double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
					double angle = waveOffset * TWO_PI;
					double angley = waveOffset * TWO_PI;
					int shakenX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + naturalLocation.x);
					int shakenY = (int) ((Math.sin(angley) * SHAKE_DISTANCE) + naturalLocation.y);
					setLocation(shakenX, shakenY);
					repaint();
					if (elapsed >= SHAKE_DURATION)
						stopShake();
				}
			});
			shakeTimer.start();
		}
	}

	@Override
	public void stopShake() {
		shakeTimer.stop();
		shakeTimer = null;
		setLocation(naturalLocation);
		repaint();
	}
}
