package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CourseEnrollment;
import models.CourseInformation;
import models.Student;

public class CourseEnrollmentDAO extends AbstractDAO<CourseEnrollment> {

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
			
			insertStatement.setLong(1, AbstractDAO.nextID);
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
		return null;
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
	
	public List<CourseEnrollment> findStudentCourses(Long studentId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findCoursesStatement = null;
		PreparedStatement findGradesStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		List<CourseEnrollment> enrollments = new ArrayList<CourseEnrollment>();
		List<CourseInformation> courses = null;;
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
		
		String query = sb.toString();
		String query2 = sb2.toString();
		
		try {
			findCoursesStatement = connection.prepareStatement(query);
			findCoursesStatement.setLong(1, studentId);
			findGradesStatement = connection.prepareStatement(query2);
			findGradesStatement.setLong(1, studentId);
			resultSet = findCoursesStatement.executeQuery();
			resultSet2 = findGradesStatement.executeQuery();
			courses = this.courseDAO.createObjects(resultSet);
			while (resultSet2.next()) {
				grades.add(resultSet2.getFloat(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findCoursesStatement);
			ConnectionFactory.close(findGradesStatement);
			ConnectionFactory.close(connection);
		}
				
		int i = 0;
		for (CourseInformation courseInfo : courses) {
			enrollments.add(new CourseEnrollment(null, courseInfo, grades.get(i++)));
		}
		return enrollments;
	}
	
	public List<Student> findCourseStudents(Long courseId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " + Constants.STUDENTS_TABLE_NAME + ".* FROM ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + " INNER JOIN ");
		sb.append(Constants.STUDENTS_TABLE_NAME);
		sb.append(" ON " + Constants.ENROLLMENTS_TABLE_NAME + ".student_id = ");
		sb.append(Constants.STUDENTS_TABLE_NAME + ".id WHERE ");
		sb.append(Constants.ENROLLMENTS_TABLE_NAME + ".course_id = ?;");
		
		String query = sb.toString();
		
		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, courseId);
			resultSet = findStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return this.studentDAO.createObjects(resultSet);
	}
}
