package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.CourseEnrollment;

public class CourseEnrollmentDAO extends AbstractDAO<CourseEnrollment> {

	@Override
	public String getTableName() {
		return "enrollments";
	}

	@Override
	protected PreparedStatement createInsertQuery(CourseEnrollment courseEnroll, Connection connection) {
		PreparedStatement insertStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + AbstractDAO.DB_NAME + "." + this.tableName);
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
		sb.append("UPDATE " + AbstractDAO.DB_NAME + "." + this.tableName + " SET ");
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
}
