package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.CourseInformation;
import models.CourseTeaching;
import models.Teacher;

public class CourseTeachingDAO extends AbstractDAO<CourseTeaching> {

	private TeacherDAO teacherDAO;
	private CourseInformationDAO courseDAO;
	
	public CourseTeachingDAO() {
		this.teacherDAO = new TeacherDAO();
		this.courseDAO = new CourseInformationDAO();
	}
	
	@Override
	public String getTableName() {
		return Constants.TEACHING_TABLE_NAME;
	}

	@Override
	protected PreparedStatement createInsertQuery(CourseTeaching courseTeaching, Connection connection) {
		PreparedStatement insertStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + this.getTableName());
		sb.append(" (id, teacher_id, course_id) ");
		sb.append("VALUES (?, ?, ?);");
		
		String insertQuery = sb.toString();
		
		try {
			insertStatement = connection.prepareStatement(insertQuery);
			
			insertStatement.setLong(1, AbstractDAO.nextID);
			insertStatement.setLong(2, courseTeaching.getTeacher().getId());
			insertStatement.setLong(3, courseTeaching.getCourse().getId());
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
		}
		
		return insertStatement;
	}
	
	@Override
	protected List<CourseTeaching> createObjects(ResultSet resultSet) {
		return null;
	}
	
	@Override
	protected PreparedStatement createUpdateQuery(CourseTeaching courseTeaching, Connection connection) {
		PreparedStatement updateStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE " + this.getTableName() + " SET ");
		sb.append("teacher_id = ?, ");
		sb.append("course_id = ?, ");
		
		String updateQuery = sb.toString();
		
		try {
			updateStatement = connection.prepareStatement(updateQuery);
			
			updateStatement.setLong(1, courseTeaching.getTeacher().getId());
			updateStatement.setLong(2, courseTeaching.getCourse().getId());
			updateStatement.setLong(4, courseTeaching.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(connection);
		}
		
		return updateStatement;
	}
	
	public List<CourseInformation> findTeacherCourses(Long teacherId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " + Constants.COURSES_TABLE_NAME + ".* FROM ");
		sb.append(Constants.TEACHING_TABLE_NAME + " INNER JOIN ");
		sb.append(Constants.COURSES_TABLE_NAME);
		sb.append(" ON " + Constants.TEACHING_TABLE_NAME + ".course_id = ");
		sb.append(Constants.COURSES_TABLE_NAME + ".id WHERE ");
		sb.append(Constants.TEACHING_TABLE_NAME + ".teacher_id = ?;");
		
		String query = sb.toString();
		
		try {
			findStatement = connection.prepareStatement(query);
			findStatement.setLong(1, teacherId);
			resultSet = findStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return this.courseDAO.createObjects(resultSet);
	}
	
	public List<Teacher> findCourseTeachers(Long courseId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet resultSet = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT " + Constants.TEACHERS_TABLE_NAME + ".* FROM ");
		sb.append(Constants.TEACHING_TABLE_NAME + " INNER JOIN ");
		sb.append(Constants.TEACHERS_TABLE_NAME);
		sb.append(" ON " + Constants.TEACHING_TABLE_NAME + ".teacher_id = ");
		sb.append(Constants.TEACHERS_TABLE_NAME + ".id WHERE ");
		sb.append(Constants.TEACHING_TABLE_NAME + ".course_id = ?;");
		
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
		return this.teacherDAO.createObjects(resultSet);
	}
}
