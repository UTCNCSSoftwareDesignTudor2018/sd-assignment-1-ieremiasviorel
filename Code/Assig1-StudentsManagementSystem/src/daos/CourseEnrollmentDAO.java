package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.Student;

public class CourseEnrollmentDAO extends AbstractDAO<CourseEnrollment> implements CourseEnrollmentDAO_I {

	private StudentDAO studentDAO;
	private CourseInformationDAO courseDAO;
	
	public CourseEnrollmentDAO() {
		this.studentDAO = new StudentDAO();
		this.courseDAO = new CourseInformationDAO();
	}
	
	@Override
	public String getTableName() {
		return Constants.ENROLLMENTS_TABLE_NAME;
	}

	@Override
	protected PreparedStatement createInsertQuery(CourseEnrollment courseEnroll, Connection connection) {
		PreparedStatement insertStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + this.getTableName());
		sb.append(" (id, student_id, course_id, grade) ");
		sb.append("VALUES (?, ?, ?, ?);");
		
		String insertQuery = sb.toString();
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setLong(1, courseEnroll.getId());
			insertStatement.setLong(2, courseEnroll.getStudent().getId());
			insertStatement.setLong(3, courseEnroll.getCourse().getId());
			insertStatement.setFloat(4, courseEnroll.getGrade());
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
		
		return insertStatement;
	}
	
	@Override
	protected List<CourseEnrollment> createObjects(ResultSet resultSet) {
		List<CourseEnrollment> enrollments = new ArrayList<CourseEnrollment>();
		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				Student s = studentDAO.find(resultSet.getLong(2));
				CourseInformation c = courseDAO.find(resultSet.getLong(3));
				Float grade = resultSet.getFloat(4);
				enrollments.add(new CourseEnrollment(id, s, c, grade));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enrollments;
	}
	
	@Override
	protected PreparedStatement createUpdateQuery(CourseEnrollment courseEnroll, Connection connection) {
		PreparedStatement updateStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " + this.getTableName() + " SET ");
		sb.append("student_id = ?, ");
		sb.append("course_id = ?, ");
		sb.append("grade = ?, ");
		
		String updateQuery = sb.toString();
		
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setLong(1, courseEnroll.getStudent().getId());
			updateStatement.setLong(2, courseEnroll.getCourse().getId());
			updateStatement.setFloat(3, courseEnroll.getGrade());
			updateStatement.setLong(4, courseEnroll.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}
		
		return updateStatement;
	}
	
	public List<CourseEnrollment> findByStudentId(Long studentId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findCoursesStatement = null;
		PreparedStatement findGradesStatement = null;
		ResultSet resultSetCourses = null;
		ResultSet resultSetGrades = null;
		
		List<CourseEnrollment> enrollments = new ArrayList<CourseEnrollment>();
		List<CourseInformation> courses = null;
		List<Float> grades = new ArrayList<Float>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " + Constants.COURSES_TABLE_NAME + ".* FROM ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + " INNER JOIN ");
		sb.append(Constants.COURSES_TABLE_NAME);
		sb.append(" ON " + Constants.ENROLLMENTS_TABLE_NAME + ".course_id = ");
		sb.append(Constants.COURSES_TABLE_NAME + ".id WHERE ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + ".student_id = ?;");
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append("SELECT " + Constants.ENROLLMENTS_TABLE_NAME + ".grade FROM ");
		sb2.append(Constants.ENROLLMENTS_TABLE_NAME + " WHERE ");
		sb2.append(Constants.ENROLLMENTS_TABLE_NAME + ".student_id = ?;");
		
		String queryCourses = sb.toString();
		String queryGrades = sb2.toString();
		
		try {
			findCoursesStatement = connection.prepareStatement(queryCourses);
			findCoursesStatement.setLong(1, studentId);
			findGradesStatement = connection.prepareStatement(queryGrades);
			findGradesStatement.setLong(1, studentId);
			resultSetCourses = findCoursesStatement.executeQuery();
			resultSetGrades = findGradesStatement.executeQuery();
			courses = this.courseDAO.createObjects(resultSetCourses);
			while (resultSetGrades.next()) {
				grades.add(resultSetGrades.getFloat(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(resultSetCourses);
			ConnectionFactory.close(resultSetGrades);
			ConnectionFactory.close(findCoursesStatement);
			ConnectionFactory.close(findGradesStatement);
			ConnectionFactory.close(connection);
		}
		
		Student student = studentDAO.find(studentId);
				
		int i = 0;
		for (CourseInformation courseInfo : courses) {
			enrollments.add(new CourseEnrollment(student, courseInfo, grades.get(i++)));
		}
		return enrollments;
	}
	
	public List<CourseEnrollment> findByCourseId(Long courseId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStudentsStatement = null;
		PreparedStatement findGradesStatement = null;
		ResultSet resultSetStudents = null;
		ResultSet resultSetGrades = null;
		
		List<CourseEnrollment> enrollments = new ArrayList<CourseEnrollment>();
		List<Student> students = null;
		List<Float> grades = new ArrayList<Float>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT " + Constants.STUDENTS_TABLE_NAME + ".* FROM ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + " INNER JOIN ");
		sb.append(Constants.STUDENTS_TABLE_NAME);
		sb.append(" ON " + Constants.ENROLLMENTS_TABLE_NAME + ".student_id = ");
		sb.append(Constants.STUDENTS_TABLE_NAME + ".id WHERE ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + ".course_id = ?;");
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append("SELECT " + Constants.ENROLLMENTS_TABLE_NAME + ".grade FROM ");
		sb2.append(Constants.ENROLLMENTS_TABLE_NAME + " WHERE ");
		sb2.append(Constants.ENROLLMENTS_TABLE_NAME + ".course_id = ?;");
		
		String queryStudents = sb.toString();
		String queryGrades = sb2.toString();
		
		try {
			findStudentsStatement = connection.prepareStatement(queryStudents);
			findStudentsStatement.setLong(1, courseId);
			findGradesStatement = connection.prepareStatement(queryGrades);
			findGradesStatement.setLong(1, courseId);
			resultSetStudents = findStudentsStatement.executeQuery();
			resultSetGrades = findGradesStatement.executeQuery();
			students = this.studentDAO.createObjects(resultSetStudents);
			while (resultSetGrades.next()) {
				grades.add(resultSetGrades.getFloat(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(resultSetStudents);
			ConnectionFactory.close(resultSetGrades);
			ConnectionFactory.close(findStudentsStatement);
			ConnectionFactory.close(findGradesStatement);
			ConnectionFactory.close(connection);
		}
		
		CourseInformation course = courseDAO.find(courseId);
				
		int i = 0;
		for (Student student : students) {
			enrollments.add(new CourseEnrollment(student, course, grades.get(i++)));
		}
		return enrollments;
	}
}
