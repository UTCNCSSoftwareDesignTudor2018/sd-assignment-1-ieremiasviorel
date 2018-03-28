import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

public class aaa extends JFrame {

	private static final long serialVersionUID = -2284850020956825159L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblStartDate;
	private JTextField tfCourseCode;
	private JTextField tfCourseStartDate;
	private JTextField tfCourseEndDate;
	private JTextField tfCourseExamDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aaa frame = new aaa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public aaa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane StudentMenu = new JTabbedPane(JTabbedPane.TOP);
		StudentMenu.setBounds(0, 0, 624, 441);
		contentPane.add(StudentMenu);
		
		JPanel personalInfo = new JPanel();
		StudentMenu.addTab("Personal Info", null, personalInfo, null);
		personalInfo.setLayout(null);
		
		JTextPane fullName1 = new JTextPane();
		fullName1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName1.setBackground(SystemColor.menu);
		fullName1.setEditable(false);
		fullName1.setBounds(10, 16, 599, 45);
		personalInfo.add(fullName1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 79, 599, 2);
		personalInfo.add(separator1);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(49, 140, 103, 31);
		personalInfo.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(49, 215, 103, 31);
		personalInfo.add(lblLastName);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(49, 286, 103, 31);
		personalInfo.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(334, 286, 103, 31);
		personalInfo.add(lblPassword);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumber.setBounds(334, 140, 103, 31);
		personalInfo.add(lblIdNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(334, 215, 103, 31);
		personalInfo.add(lblEmail);
		
		JLabel lblFirstNameVal = new JLabel("");
		lblFirstNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstNameVal.setBounds(162, 140, 127, 31);
		personalInfo.add(lblFirstNameVal);
		
		JLabel lblLastNameVal = new JLabel("");
		lblLastNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastNameVal.setBounds(162, 215, 127, 31);
		personalInfo.add(lblLastNameVal);
		
		JLabel lblIdNumberVal = new JLabel("");
		lblIdNumberVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumberVal.setBounds(447, 140, 127, 31);
		personalInfo.add(lblIdNumberVal);
		
		JLabel lblEmailVal = new JLabel("");
		lblEmailVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailVal.setBounds(447, 215, 127, 31);
		personalInfo.add(lblEmailVal);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(162, 284, 127, 31);
		personalInfo.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(447, 284, 127, 31);
		personalInfo.add(txtPassword);
		
		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.setBounds(490, 379, 119, 23);
		personalInfo.add(btnSaveChanges);
		
		JPanel teaching = new JPanel();
		StudentMenu.addTab("Teaching", null, teaching, null);
		StudentMenu.setEnabledAt(1, true);
		teaching.setLayout(null);
		
		JTextPane fullName2 = new JTextPane();
		fullName2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName2.setEditable(false);
		fullName2.setBackground(SystemColor.menu);
		fullName2.setBounds(10, 16, 599, 45);
		teaching.add(fullName2);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 79, 599, 2);
		teaching.add(separator2);
		
		JList<String> courseList = new JList<String>();
		courseList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		courseList.setValueIsAdjusting(true);
		courseList.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		courseList.setBackground(SystemColor.activeCaption);
		courseList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		courseList.setBounds(30, 106, 263, 185);
		teaching.add(courseList);
		
		lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDate.setBounds(323, 150, 105, 25);
		teaching.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDate.setBounds(323, 190, 105, 25);
		teaching.add(lblEndDate);
		
		JLabel lblStartDateVal = new JLabel("");
		lblStartDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDateVal.setBounds(457, 150, 129, 25);
		teaching.add(lblStartDateVal);
		
		JLabel lblEndDateVal = new JLabel("");
		lblEndDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDateVal.setBounds(457, 190, 129, 25);
		teaching.add(lblEndDateVal);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCode.setBounds(323, 110, 105, 25);
		teaching.add(lblCode);
		
		JLabel lblCodeVal = new JLabel("");
		lblCodeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodeVal.setBounds(457, 110, 129, 25);
		teaching.add(lblCodeVal);
		
		JLabel lblExamDate = new JLabel("Exam Date:");
		lblExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDate.setBounds(323, 270, 105, 25);
		teaching.add(lblExamDate);
		
		JTextField tfExamDate = new JTextField("");
		tfExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfExamDate.setBounds(457, 270, 129, 25);
		teaching.add(tfExamDate);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(110, 302, 105, 25);
		teaching.add(lblDescription);
		
		JTextPane txtDescription = new JTextPane();
		txtDescription.setBackground(SystemColor.control);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescription.setBounds(30, 330, 263, 72);
		teaching.add(txtDescription);
		
		JPanel course = new JPanel();
		StudentMenu.addTab("Add Course", null, course, null);
		course.setLayout(null);
		
		JTextPane fullName3 = new JTextPane();
		fullName3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName3.setEditable(false);
		fullName3.setBackground(SystemColor.menu);
		fullName3.setBounds(10, 16, 599, 45);
		course.add(fullName3);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(10, 79, 599, 2);
		course.add(separator3);
		
		JButton btnAddCourse = new JButton("Create");
		btnAddCourse.setBounds(500, 366, 109, 36);
		course.add(btnAddCourse);
		
		JLabel lblcourseName = new JLabel("Name:");
		lblcourseName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcourseName.setBounds(49, 140, 103, 31);
		course.add(lblcourseName);
		
		JTextField tfCourseName = new JTextField("");
		tfCourseName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseName.setBounds(162, 140, 127, 31);
		course.add(tfCourseName);
		
		JLabel lblCourseCode = new JLabel("Code:");
		lblCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseCode.setBounds(49, 193, 103, 31);
		course.add(lblCourseCode);
		
		tfCourseCode = new JTextField("");
		tfCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseCode.setBounds(162, 193, 127, 31);
		course.add(tfCourseCode);
		
		JLabel lblCourseDescription = new JLabel("Description:");
		lblCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseDescription.setBounds(49, 242, 103, 31);
		course.add(lblCourseDescription);
		
		JTextPane txtCourseDescription = new JTextPane();
		txtCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCourseDescription.setBounds(49, 284, 240, 76);
		course.add(txtCourseDescription);
		
		JLabel lblCourseStartDate = new JLabel("Start Date:");
		lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseStartDate.setBounds(346, 140, 103, 31);
		course.add(lblCourseStartDate);
		
		tfCourseStartDate = new JTextField("");
		tfCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseStartDate.setBounds(459, 140, 127, 31);
		course.add(tfCourseStartDate);
		
		JLabel lblCourseEndDate = new JLabel("End Date:");
		lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseEndDate.setBounds(346, 193, 103, 31);
		course.add(lblCourseEndDate);
		
		tfCourseEndDate = new JTextField("");
		tfCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseEndDate.setBounds(459, 193, 127, 31);
		course.add(tfCourseEndDate);
		
		JLabel lblCourseExamDate = new JLabel("Exam Date:");
		lblCourseExamDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseExamDate.setBounds(346, 246, 103, 31);
		course.add(lblCourseExamDate);
		
		tfCourseExamDate = new JTextField("");
		tfCourseExamDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseExamDate.setBounds(459, 246, 127, 31);
		course.add(tfCourseExamDate);
	}
}
