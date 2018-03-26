package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.CourseTeaching;

public class CourseTeachingDAO extends AbstractDAO<CourseTeaching> {

	@Override
	public String getTableName() {
		return "teaching";
	}

	@Override
	protected PreparedStatement createInsertQuery(CourseTeaching courseTeaching, Connection connection) {
		PreparedStatement insertStatement = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + AbstractDAO.DB_NAME + "." + this.tableName);
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
		sb.append("UPDATE " + AbstractDAO.DB_NAME + "." + this.tableName + " SET ");
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
}
