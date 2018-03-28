package views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel usernameL;
	private JTextField usernameTf;
	private JLabel passwordL;
	private JPasswordField passwordPf;
	private JLabel errorL;
	private JButton loginStudentBtn;
	private JButton loginTeacherBtn;
	
	public LoginView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TUC-N Portal");
		setBounds(100, 100, 410, 320);
		setLayout(null);
		
		
		usernameL = new JLabel("Username:");
		usernameL.setBounds(80, 40, 80, 25);
		add(usernameL);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(160, 40, 155, 25);
		add(usernameTf);
		
		passwordL = new JLabel("Password:");
		passwordL.setBounds(80, 100, 80, 25);
		add(passwordL);
		
		passwordPf = new JPasswordField();
		passwordPf.setBounds(160, 100, 155, 25);
		add(passwordPf);
		
		errorL = new JLabel("Username or password incorrect");
		errorL.setForeground(Color.RED);
		errorL.setHorizontalAlignment(0);;
		errorL.setBounds(80, 130, 235, 25);
		errorL.setVisible(false);
		add(errorL);
		
		loginStudentBtn = new JButton("Login as student");
		loginStudentBtn.setBounds(80, 160, 235, 30);
		add(loginStudentBtn);
		
		loginTeacherBtn = new JButton("Login as teacher");
		loginTeacherBtn.setBounds(80, 220, 235, 30);
		add(loginTeacherBtn);
		
		setVisible(true);
	}
	
	public String[] getLoginData() {
		return new String[] { usernameTf.getText(), new String(passwordPf.getPassword()) };
	}
	
	public void displayErrorMessage() {
		this.errorL.setVisible(true);
	}
	
	public void addStudentLoginActionListener(ActionListener e) {
		loginStudentBtn.addActionListener(e);
	}
	
	public void addTeacherLoginActionListener(ActionListener e) {
		loginTeacherBtn.addActionListener(e);
	}
}