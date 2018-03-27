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

public class aaa extends JFrame {

	private static final long serialVersionUID = -2284850020956825159L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblStartDate;
	private JTable coursesTable;

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
		
		JPanel PersonalInfo = new JPanel();
		StudentMenu.addTab("Personal Info", null, PersonalInfo, null);
		PersonalInfo.setLayout(null);
		
		JTextPane fullName1 = new JTextPane();
		fullName1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName1.setBackground(SystemColor.menu);
		fullName1.setEditable(false);
		fullName1.setBounds(10, 16, 599, 45);
		PersonalInfo.add(fullName1);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 79, 599, 2);
		PersonalInfo.add(separator1);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(49, 140, 103, 31);
		PersonalInfo.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(49, 215, 103, 31);
		PersonalInfo.add(lblLastName);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(49, 286, 103, 31);
		PersonalInfo.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(334, 286, 103, 31);
		PersonalInfo.add(lblPassword);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumber.setBounds(334, 140, 103, 31);
		PersonalInfo.add(lblIdNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(334, 215, 103, 31);
		PersonalInfo.add(lblEmail);
		
		JLabel lblFirstNameVal = new JLabel("");
		lblFirstNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstNameVal.setBounds(162, 140, 127, 31);
		PersonalInfo.add(lblFirstNameVal);
		
		JLabel lblLastNameVal = new JLabel("");
		lblLastNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastNameVal.setBounds(162, 215, 127, 31);
		PersonalInfo.add(lblLastNameVal);
		
		JLabel lblIdNumberVal = new JLabel("");
		lblIdNumberVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumberVal.setBounds(447, 140, 127, 31);
		PersonalInfo.add(lblIdNumberVal);
		
		JLabel lblEmailVal = new JLabel("");
		lblEmailVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailVal.setBounds(447, 215, 127, 31);
		PersonalInfo.add(lblEmailVal);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(162, 284, 127, 31);
		PersonalInfo.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(447, 284, 127, 31);
		PersonalInfo.add(txtPassword);
		
		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.setBounds(490, 379, 119, 23);
		PersonalInfo.add(btnSaveChanges);
		
		JPanel Enrollments = new JPanel();
		StudentMenu.addTab("Enrollments", null, Enrollments, null);
		StudentMenu.setEnabledAt(1, true);
		Enrollments.setLayout(null);
		
		JTextPane fullName2 = new JTextPane();
		fullName2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName2.setEditable(false);
		fullName2.setBackground(SystemColor.menu);
		fullName2.setBounds(10, 16, 599, 45);
		Enrollments.add(fullName2);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 79, 599, 2);
		Enrollments.add(separator2);
		
		JList<String> enrollmentList = new JList<String>();
		enrollmentList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enrollmentList.setValueIsAdjusting(true);
		enrollmentList.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		enrollmentList.setBackground(SystemColor.activeCaption);
		enrollmentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		enrollmentList.setBounds(30, 106, 263, 185);
		Enrollments.add(enrollmentList);
		
		lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDate.setBounds(323, 150, 105, 25);
		Enrollments.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDate.setBounds(323, 190, 105, 25);
		Enrollments.add(lblEndDate);
		
		JLabel lblStartDateVal = new JLabel("");
		lblStartDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDateVal.setBounds(457, 150, 129, 25);
		Enrollments.add(lblStartDateVal);
		
		JLabel lblEndDateVal = new JLabel("");
		lblEndDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDateVal.setBounds(457, 190, 129, 25);
		Enrollments.add(lblEndDateVal);
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCode.setBounds(323, 110, 105, 25);
		Enrollments.add(lblCode);
		
		JLabel lblCodeVal = new JLabel("");
		lblCodeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodeVal.setBounds(457, 110, 129, 25);
		Enrollments.add(lblCodeVal);
		
		JLabel lblTeacher = new JLabel("Teacher:");
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacher.setBounds(323, 230, 105, 25);
		Enrollments.add(lblTeacher);
		
		JLabel lblTeacherVal = new JLabel("");
		lblTeacherVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherVal.setBounds(457, 230, 129, 25);
		Enrollments.add(lblTeacherVal);
		
		JLabel lblExamDate = new JLabel("Exam Date:");
		lblExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDate.setBounds(323, 270, 105, 25);
		Enrollments.add(lblExamDate);
		
		JLabel lblExamDateVal = new JLabel("");
		lblExamDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDateVal.setBounds(457, 270, 129, 25);
		Enrollments.add(lblExamDateVal);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrade.setBounds(323, 340, 105, 25);
		Enrollments.add(lblGrade);
		
		JLabel lblGradeVal = new JLabel("");
		lblGradeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGradeVal.setBounds(457, 340, 129, 25);
		Enrollments.add(lblGradeVal);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(110, 302, 105, 25);
		Enrollments.add(lblDescription);
		
		JTextPane txtDescription = new JTextPane();
		txtDescription.setBackground(SystemColor.control);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescription.setBounds(30, 330, 263, 72);
		Enrollments.add(txtDescription);
		
		JPanel Courses = new JPanel();
		StudentMenu.addTab("Courses", null, Courses, null);
		Courses.setLayout(null);
		
		JTextPane fullName3 = new JTextPane();
		fullName3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName3.setEditable(false);
		fullName3.setBackground(SystemColor.menu);
		fullName3.setBounds(10, 16, 599, 45);
		Courses.add(fullName3);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(10, 79, 599, 2);
		Courses.add(separator3);
		
		JScrollPane coursesScrollPanel = new JScrollPane();
		coursesScrollPanel.setBounds(10, 113, 599, 229);
		Courses.add(coursesScrollPanel);
		
		coursesTable = new JTable();
		coursesTable.setSurrendersFocusOnKeystroke(true);
		coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coursesTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Teacher", "Start Date", "Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		coursesTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coursesTable.setFillsViewportHeight(true);
		coursesTable.setColumnSelectionAllowed(true);
		coursesTable.setCellSelectionEnabled(true);
		coursesScrollPanel.setViewportView(coursesTable);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.setBounds(500, 366, 109, 36);
		Courses.add(btnEnroll);
	}
}
