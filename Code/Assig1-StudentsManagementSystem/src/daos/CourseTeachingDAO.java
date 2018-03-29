package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.CourseInformation;
import entities.CourseTeaching;
import entities.Teacher;

public class CourseTeachingDAO extends AbstractDAO<CourseTeaching> implements CourseTeachingDAO_I {

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
			
			insertStatement.setLong(1, this.nextID);
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
		
		List<CourseTeaching> courseTeaching = new ArrayList<CourseTeaching>();
		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				Teacher t = teacherDAO.find(resultSet.getLong(2));
				CourseInformation c = courseDAO.find(resultSet.getLong(3));
				courseTeaching.add(new CourseTeaching(id, t, c));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseTeaching;
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
	
	public List<CourseTeaching> findByTeacherId(Long teacherId) {
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
		
		List<CourseInformation> courses =  this.courseDAO.createObjects(resultSet);
		
		Teacher teacher = teacherDAO.find(teacherId);
		
		List<CourseTeaching> courseTeaching = new ArrayList<CourseTeaching>();
		for (CourseInformation course : courses) {
			courseTeaching.add(new CourseTeaching(teacher, course));
		}
		return courseTeaching;
	}
	
	public List<CourseTeaching> findByCourseId(Long courseId) {
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
		
		List<Teacher> teachers = this.teacherDAO.createObjects(resultSet);
		
		CourseInformation course = courseDAO.find(courseId);
		
		List<CourseTeaching> courseTeaching = new ArrayList<CourseTeaching>();
		for (Teacher teacher : teachers) {
			courseTeaching.add(new CourseTeaching(teacher, course));
		}
		
		return courseTeaching;
	}
}
