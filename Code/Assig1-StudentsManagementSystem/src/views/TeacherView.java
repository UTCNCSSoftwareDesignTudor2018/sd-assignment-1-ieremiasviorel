package views;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import entities.CourseInformation;
import entities.CourseTeaching;
import entities.Teacher;
import services.TeacherSessionData;

public class TeacherView extends JFrame {

	private static final long serialVersionUID = 1L;

	public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private JPanel contentPane;
	private JTabbedPane teacherMenu;

	private JTextPane fullName1, fullName2, fullName3;
	private JSeparator separator1, separator2, separator3;

	private JPanel personalInfo;

	private JLabel lblFirstName, lblFirstNameVal, lblLastName, lblLastNameVal, lblUserName, lblPassword, lblIdNumber,
			lblIdNumberVal, lblEmail, lblEmailVal;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnSaveChanges;

	private JPanel teaching;

	private JList<String> courseList;
	private JLabel lblStartDate, lblStartDateVal, lblEndDate, lblEndDateVal, lblExamDate, lblCode, lblCodeVal,
			lblDescription;
	private JTextPane txtDescription;
	private JTextField tfExamDate;
	private JButton btnSaveCourseChanges;

	private JPanel course;

	private JLabel lblCourseName, lblCourseCode, lblCourseDescription, lblCourseStartDate, lblCourseEndDate,
			lblCourseExamDate;
	private JTextField tfCourseName, tfCourseCode, tfCourseStartDate, tfCourseEndDate, tfCourseExamDate;
	private JTextPane txtCourseDescription;
	private JButton btnAddCourse;

	public TeacherView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TUC-N Portal");
		setBounds(100, 100, 640, 480);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		teacherMenu = new JTabbedPane(JTabbedPane.TOP);
		teacherMenu.setBounds(0, 0, 624, 441);
		contentPane.add(teacherMenu);

		personalInfo = new JPanel();
		teacherMenu.addTab("Personal Info", null, personalInfo, null);
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

		teaching = new JPanel();
		teacherMenu.addTab("Teaching", null, teaching, null);
		teacherMenu.setEnabledAt(1, true);
		teaching.setLayout(null);

		fullName2 = new JTextPane();
		fullName2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName2.setEditable(false);
		fullName2.setBackground(SystemColor.menu);
		fullName2.setBounds(10, 16, 599, 45);
		teaching.add(fullName2);

		separator2 = new JSeparator();
		separator2.setBounds(10, 79, 599, 2);
		teaching.add(separator2);

		courseList = new JList<String>();
		courseList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		courseList.setValueIsAdjusting(true);
		courseList.setBackground(SystemColor.activeCaption);
		courseList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		courseList.setBounds(30, 106, 263, 185);
		teaching.add(courseList);

		lblStartDate = new JLabel("Start Date: ");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDate.setBounds(323, 150, 105, 25);
		teaching.add(lblStartDate);

		lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDate.setBounds(323, 190, 105, 25);
		teaching.add(lblEndDate);

		lblExamDate = new JLabel("Exam Date:");
		lblExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExamDate.setBounds(323, 270, 105, 25);
		teaching.add(lblExamDate);

		lblStartDateVal = new JLabel("");
		lblStartDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStartDateVal.setBounds(457, 150, 129, 25);
		teaching.add(lblStartDateVal);

		lblEndDateVal = new JLabel("");
		lblEndDateVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndDateVal.setBounds(457, 190, 129, 25);
		teaching.add(lblEndDateVal);

		tfExamDate = new JTextField();
		tfExamDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfExamDate.setBounds(457, 270, 129, 25);
		teaching.add(tfExamDate);

		lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCode.setBounds(323, 110, 105, 25);
		teaching.add(lblCode);

		lblCodeVal = new JLabel("");
		lblCodeVal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCodeVal.setBounds(457, 110, 129, 25);
		teaching.add(lblCodeVal);

		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(110, 302, 105, 25);
		teaching.add(lblDescription);

		txtDescription = new JTextPane();
		txtDescription.setBackground(SystemColor.control);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescription.setBounds(30, 330, 263, 72);
		teaching.add(txtDescription);

		btnSaveCourseChanges = new JButton("Save changes");
		btnSaveCourseChanges.setBounds(490, 379, 119, 23);
		teaching.add(btnSaveCourseChanges);

		course = new JPanel();
		teacherMenu.addTab("Add Course", null, course, null);
		course.setLayout(null);

		fullName3 = new JTextPane();
		fullName3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		fullName3.setEditable(false);
		fullName3.setBackground(SystemColor.menu);
		fullName3.setBounds(10, 16, 599, 45);
		course.add(fullName3);

		separator3 = new JSeparator();
		separator3.setBounds(10, 79, 599, 2);
		course.add(separator3);

		btnAddCourse = new JButton("Create");
		btnAddCourse.setBounds(500, 366, 109, 36);
		course.add(btnAddCourse);

		lblCourseName = new JLabel("Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseName.setBounds(49, 140, 103, 31);
		course.add(lblCourseName);

		tfCourseName = new JTextField("");
		tfCourseName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseName.setBounds(162, 140, 127, 31);
		course.add(tfCourseName);

		lblCourseCode = new JLabel("Code:");
		lblCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseCode.setBounds(49, 193, 103, 31);
		course.add(lblCourseCode);

		tfCourseCode = new JTextField("");
		tfCourseCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseCode.setBounds(162, 193, 127, 31);
		course.add(tfCourseCode);

		lblCourseDescription = new JLabel("Description:");
		lblCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseDescription.setBounds(49, 242, 103, 31);
		course.add(lblCourseDescription);

		txtCourseDescription = new JTextPane();
		txtCourseDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCourseDescription.setBounds(49, 284, 240, 76);
		course.add(txtCourseDescription);

		lblCourseStartDate = new JLabel("Start Date:");
		lblCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseStartDate.setBounds(346, 140, 103, 31);
		course.add(lblCourseStartDate);

		tfCourseStartDate = new JTextField("");
		tfCourseStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseStartDate.setBounds(459, 140, 127, 31);
		course.add(tfCourseStartDate);

		lblCourseEndDate = new JLabel("End Date:");
		lblCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseEndDate.setBounds(346, 193, 103, 31);
		course.add(lblCourseEndDate);

		tfCourseEndDate = new JTextField("");
		tfCourseEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseEndDate.setBounds(459, 193, 127, 31);
		course.add(tfCourseEndDate);

		lblCourseExamDate = new JLabel("Exam Date:");
		lblCourseExamDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCourseExamDate.setBounds(346, 246, 103, 31);
		course.add(lblCourseExamDate);

		tfCourseExamDate = new JTextField("");
		tfCourseExamDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCourseExamDate.setBounds(459, 246, 127, 31);
		course.add(tfCourseExamDate);
	}

	public void updatePersonalInfoTab(Teacher teacher) {
		this.fullName1.setText(teacher.getFirstName() + " " + teacher.getLastName());
		this.fullName2.setText(teacher.getFirstName() + " " + teacher.getLastName());
		this.fullName3.setText(teacher.getFirstName() + " " + teacher.getLastName());

		this.lblFirstNameVal.setText(teacher.getFirstName());
		this.lblLastNameVal.setText(teacher.getLastName());
		this.txtUsername.setText(teacher.getUserName());
		this.txtPassword.setText(teacher.getPassword());
		this.lblIdNumberVal.setText(teacher.getIdNumber());
		this.lblEmailVal.setText(teacher.getEmail());
	}

	public void updateTeachingTab(List<CourseTeaching> courses) {
		DefaultListModel<String> courseNames = new DefaultListModel<String>();
		for (CourseTeaching c : courses) {
			courseNames.addElement(c.getCourse().getName());
		}
		this.courseList.setModel(courseNames);
	}

	public void updateCourseDetails() {

		String courseCode = this.courseList.getSelectedValue();

		List<CourseTeaching> teachingList = TeacherSessionData.getCourses();
		for (CourseTeaching e : teachingList) {
			if (e.getCourse().getName().equals(courseCode)) {
				CourseInformation c = e.getCourse();
				this.lblCodeVal.setText(c.getCode());
				this.lblStartDateVal.setText(c.getStartDate().toString());
				this.lblEndDateVal.setText(c.getEndDate().toString());
				this.tfExamDate.setText(c.getExamDate().toString());
				this.txtDescription.setText(c.getDescription());
				return;
			}
		}
	}

	public CourseInformation getCourseToInsert() {
		String courseName = this.tfCourseName.getText();
		String courseCode = this.tfCourseCode.getText();
		String courseDescription = this.txtCourseDescription.getText();
		LocalDate courseStartDate = LocalDate.parse(this.tfCourseStartDate.getText(), dateFormat);
		LocalDate courseEndDate = LocalDate.parse(this.tfCourseStartDate.getText(), dateFormat);
		LocalDate courseExamDate = LocalDate.parse(this.tfCourseStartDate.getText(), dateFormat);

		return new CourseInformation(courseName, courseCode, courseDescription, courseStartDate, courseEndDate,
				courseExamDate);
	}

	public List<String> getUserNameAndPassword() {
		List<String> modifiedData = new ArrayList<String>();
		modifiedData.add(this.txtUsername.getText());
		modifiedData.add(new String(this.txtPassword.getPassword()));
		return modifiedData;
	}

	public String getNewExamDate() {
		return this.tfExamDate.getText();
	}

	public String getSelectedCourse() {
		return this.courseList.getSelectedValue();
	}

	public void addCourseDetailsActionListener(ListSelectionListener e) {
		this.courseList.addListSelectionListener(e);
	}

	public void addModifyTeacherDataActionListener(ActionListener e) {
		this.btnSaveChanges.addActionListener(e);
	}

	public void addModifyCourseActionListener(ActionListener e) {
		this.btnSaveCourseChanges.addActionListener(e);
	}

	public void addCreateCourseActionListener(ActionListener e) {
		this.btnAddCourse.addActionListener(e);
	}
}
