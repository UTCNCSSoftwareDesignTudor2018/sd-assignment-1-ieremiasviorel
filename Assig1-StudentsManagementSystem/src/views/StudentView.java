package views;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.CourseEnrollment;
import models.CourseInformation;
import models.Student;
import services.CourseService;
import services.StudentService;

public class StudentView extends JFrame {

	private static final long serialVersionUID = 1L;

	private StudentService studentService;
	private CourseService courseService;

	private JPanel contentPane;
	private JTabbedPane studentMenu;

	private JTextPane fullName1, fullName2, fullName3;
	private JSeparator separator1, separator2, separator3;

	private JPanel personalInfo;

	private JLabel lblFirstName, lblFirstNameVal, lblLastName, lblLastNameVal, lblUserName, lblPassword, lblIdNumber,
			lblIdNumberVal, lblEmail, lblEmailVal;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnSaveChanges;

	private JPanel enrollments;

	private JList<String> enrollmentList;
	private JLabel lblStartDate, lblStartDateVal, lblEndDate, lblEndDateVal, lblExamDate, lblExamDateVal, lblCode,
			lblCodeVal, lblTeacher, lblTeacherVal, lblGrade, lblGradeVal, lblDescription;
	private JTextPane txtDescription;

	private JPanel courses;

	private JScrollPane coursesScrollPanel;
	private JTable coursesTable;
	private JButton btnEnroll;

	public StudentView(StudentService studentService, CourseService courseService) {

		this.studentService = studentService;
		this.courseService = courseService;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TUC-N Portal");
		setBounds(100, 100, 640, 480);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		studentMenu = new JTabbedPane(JTabbedPane.TOP);
		studentMenu.setBounds(0, 0, 624, 441);
		contentPane.add(studentMenu);

		personalInfo = new JPanel();
		studentMenu.addTab("Personal Info", null, personalInfo, null);
		personalInfo.setLayout(null);

		fullName1 = new JTextPane();
		fullName1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName1.setBackground(SystemColor.menu);
		fullName1.setEditable(false);
		fullName1.setBounds(10, 16, 599, 45);
		personalInfo.add(fullName1);

		separator1 = new JSeparator();
		separator1.setBounds(10, 79, 599, 2);
		personalInfo.add(separator1);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName.setBounds(49, 140, 103, 31);
		personalInfo.add(lblFirstName);

		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastName.setBounds(49, 215, 103, 31);
		personalInfo.add(lblLastName);

		lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(49, 286, 103, 31);
		personalInfo.add(lblUserName);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(334, 286, 103, 31);
		personalInfo.add(lblPassword);

		lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumber.setBounds(334, 140, 103, 31);
		personalInfo.add(lblIdNumber);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(334, 215, 103, 31);
		personalInfo.add(lblEmail);

		lblFirstNameVal = new JLabel("");
		lblFirstNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstNameVal.setBounds(162, 140, 127, 31);
		personalInfo.add(lblFirstNameVal);

		lblLastNameVal = new JLabel("");
		lblLastNameVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLastNameVal.setBounds(162, 215, 127, 31);
		personalInfo.add(lblLastNameVal);

		lblIdNumberVal = new JLabel("");
		lblIdNumberVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdNumberVal.setBounds(447, 140, 127, 31);
		personalInfo.add(lblIdNumberVal);

		lblEmailVal = new JLabel("");
		lblEmailVal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailVal.setBounds(447, 215, 127, 31);
		personalInfo.add(lblEmailVal);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUsername.setBounds(162, 284, 127, 31);
		personalInfo.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(447, 284, 127, 31);
		personalInfo.add(txtPassword);

		btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.setBounds(490, 379, 119, 23);
		personalInfo.add(btnSaveChanges);

		enrollments = new JPanel();
		studentMenu.addTab("Enrollments", null, enrollments, null);
		studentMenu.setEnabledAt(1, true);
		enrollments.setLayout(null);

		fullName2 = new JTextPane();
		fullName2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName2.setEditable(false);
		fullName2.setBackground(SystemColor.menu);
		fullName2.setBounds(10, 16, 599, 45);
		enrollments.add(fullName2);

		separator2 = new JSeparator();
		separator2.setBounds(10, 79, 599, 2);
		enrollments.add(separator2);

		enrollmentList = new JList<String>();
		enrollmentList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enrollmentList.setValueIsAdjusting(true);
		// enrollmentList.setModel(new AbstractListModel() {
		// String[] values = new String[] {};
		//
		// public int getSize() {
		// return values.length;
		// }
		//
		// public Object getElementAt(int index) {
		// return values[index];
		// }
		// });
		enrollmentList.setBackground(SystemColor.activeCaption);
		enrollmentList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		enrollmentList.setBounds(30, 106, 263, 185);
		enrollments.add(enrollmentList);

		lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDate.setBounds(323, 150, 105, 25);
		enrollments.add(lblStartDate);

		lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDate.setBounds(323, 190, 105, 25);
		enrollments.add(lblEndDate);

		lblStartDateVal = new JLabel("");
		lblStartDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDateVal.setBounds(457, 150, 129, 25);
		enrollments.add(lblStartDateVal);

		lblEndDateVal = new JLabel("");
		lblEndDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDateVal.setBounds(457, 190, 129, 25);
		enrollments.add(lblEndDateVal);

		lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCode.setBounds(323, 110, 105, 25);
		enrollments.add(lblCode);

		lblCodeVal = new JLabel("");
		lblCodeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodeVal.setBounds(457, 110, 129, 25);
		enrollments.add(lblCodeVal);

		lblTeacher = new JLabel("Teacher:");
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacher.setBounds(323, 230, 105, 25);
		enrollments.add(lblTeacher);

		lblTeacherVal = new JLabel("");
		lblTeacherVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherVal.setBounds(457, 230, 129, 25);
		enrollments.add(lblTeacherVal);

		lblExamDate = new JLabel("Exam Date:");
		lblExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDate.setBounds(323, 270, 105, 25);
		enrollments.add(lblExamDate);

		lblExamDateVal = new JLabel("");
		lblExamDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDateVal.setBounds(457, 270, 129, 25);
		enrollments.add(lblExamDateVal);

		lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGrade.setBounds(323, 340, 105, 25);
		enrollments.add(lblGrade);

		lblGradeVal = new JLabel("");
		lblGradeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGradeVal.setBounds(457, 340, 129, 25);
		enrollments.add(lblGradeVal);

		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(110, 302, 105, 25);
		enrollments.add(lblDescription);

		txtDescription = new JTextPane();
		txtDescription.setBackground(SystemColor.control);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescription.setBounds(30, 330, 263, 72);
		enrollments.add(txtDescription);

		courses = new JPanel();
		studentMenu.addTab("Courses", null, courses, null);
		courses.setLayout(null);

		fullName3 = new JTextPane();
		fullName3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName3.setEditable(false);
		fullName3.setBackground(SystemColor.menu);
		fullName3.setBounds(10, 16, 599, 45);
		courses.add(fullName3);

		separator3 = new JSeparator();
		separator3.setBounds(10, 79, 599, 2);
		courses.add(separator3);

		coursesScrollPanel = new JScrollPane();
		coursesScrollPanel.setBounds(10, 113, 599, 229);
		courses.add(coursesScrollPanel);

		coursesTable = new JTable();
		coursesTable.setSurrendersFocusOnKeystroke(true);
		coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coursesTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Code", "Name", "Teacher", "Start Date", "Description" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			Class<String>[] columnTypes = new Class[] { String.class, String.class, Object.class, String.class,
					String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		coursesTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		coursesTable.setFillsViewportHeight(true);
		coursesTable.setColumnSelectionAllowed(true);
		coursesTable.setCellSelectionEnabled(true);
		coursesScrollPanel.setViewportView(coursesTable);

		btnEnroll = new JButton("Enroll");
		btnEnroll.setBounds(500, 366, 109, 36);
		courses.add(btnEnroll);
	}

	public void initializeView() {

		Student s = this.studentService.getLoggedInStudent();

		this.fullName1.setText(s.getFirstName() + " " + s.getLastName());
		this.fullName2.setText(s.getFirstName() + " " + s.getLastName());
		this.fullName3.setText(s.getFirstName() + " " + s.getLastName());

		this.lblFirstNameVal.setText(s.getFirstName());
		this.lblLastNameVal.setText(s.getLastName());
		this.txtUsername.setText(s.getUserName());
		this.txtPassword.setText(s.getPassword());
		this.lblIdNumberVal.setText(s.getIdNumber());
		this.lblEmailVal.setText(s.getEmail());

		List<CourseEnrollment> courseEnrollments = s.getCourses();
		DefaultListModel<String> courseNames = new DefaultListModel<String>();
		for (CourseEnrollment c : courseEnrollments) {
			courseNames.addElement(c.getCourse().getCode());
		}
		this.enrollmentList.setModel(courseNames);

		DefaultTableModel tableModel = (DefaultTableModel) this.coursesTable.getModel();
		List<CourseInformation> courses = this.courseService.getAllCourses();
		for (CourseInformation c : courses) {
			tableModel.addRow(
					new Object[] { c.getCode(), c.getName(), c.getEndDate(), c.getStartDate(), c.getDescription() });
			System.out.println(c.getCode());
		}
		this.coursesTable.setModel(tableModel);
	}

	public void updateEnrollmentDetails() {

		String courseCode = this.enrollmentList.getSelectedValue();

		List<CourseEnrollment> enrollments = this.studentService.getLoggedInStudent().getCourses();
		for (CourseEnrollment e : enrollments) {
			if (e.getCourse().getCode().equals(courseCode)) {
				CourseInformation c = e.getCourse();
				this.lblCodeVal.setText(c.getName());
				this.lblStartDateVal.setText(c.getStartDate().toString());
				this.lblEndDateVal.setText(c.getEndDate().toString());
				// this.lblTeacherVal
				this.lblGradeVal.setText(e.getGrade().toString());
				this.txtDescription.setText(c.getDescription());
				return;
			}
		}

	}

	public List<String> getUserNameAndPassword() {
		List<String> modifiedData = new ArrayList<String>();
		modifiedData.add(this.txtUsername.getText());
		modifiedData.add(new String(this.txtPassword.getPassword()));
		return modifiedData;
	}

	public void addEnrollmentDetailsActionListener(ListSelectionListener e) {
		this.enrollmentList.addListSelectionListener(e);
	}

	public void addModifyStudentDataActionListener(ActionListener e) {
		this.btnSaveChanges.addActionListener(e);
	}
}
