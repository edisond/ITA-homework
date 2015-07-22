package com.oocl.kary.kk.ui.prototypes;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.google.gson.Gson;
import com.oocl.kary.kk.model.KPacket;
import com.oocl.kary.kk.model.LoginBody;
import com.oocl.kary.kk.model.User;

import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private Properties config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public LoginFrame() throws FileNotFoundException, IOException {
		config = new Properties();
		config.load(new FileInputStream("config.ini"));

		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setTitle("KK");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserSignIn = new JLabel("User Login");
		lblUserSignIn.setFont(new Font("Arial", Font.BOLD, 23));
		lblUserSignIn.setBounds(22, 11, 141, 40);
		contentPane.add(lblUserSignIn);

		textField = new JTextField();
		textField.setToolTipText("Username");
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(22, 86, 400, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 2, 0,
				new Color(220, 220, 220)));

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordField.setBounds(22, 140, 400, 28);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, new Color(220, 220,
				220)));
		contentPane.add(passwordField);

		btnNewButton = new JButton("Login");

		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(22, 209, 400, 40);
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		getRootPane().setDefaultButton(btnNewButton);
	}

	public void doLogin() {
		String id = textField.getText();
		String password = new String(passwordField.getPassword());
		if (id.equals("")) {
			textField.grabFocus();
		} else if (password.equals("")) {
			passwordField.grabFocus();
		} else {
			Socket socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				socket = new Socket(config.getProperty("host"),
						Integer.parseInt(config.getProperty("port")));
				out = new PrintWriter(socket.getOutputStream());
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				User user = new User();
				user.setId(id);
				user.setPassword(password);
				KPacket packet = new KPacket("login", user);
				Gson gson = new Gson();
				String json = gson.toJson(packet, packet.getClass());
				out.println(json);
				out.flush();
				json = in.readLine();
				packet=gson.fromJson(json, packet.getClass());
				LoginBody loginBody=gson.fromJson(packet.body.toString(), LoginBody.class);
				if(loginBody.message.equals("success")){
					JOptionPane.showMessageDialog(contentPane, "Login success.");
					String token=in.readLine();
					System.out.println(token);
				}else{
					JOptionPane.showMessageDialog(contentPane, "Wrong login id/password.", "Login Fail", JOptionPane.ERROR_MESSAGE);
				}
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					out.close();
					in.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
