package FirstProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FirstProject.Teacher;

public class TeachersToDB {

	public static Connection getConnection() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String database = "jdbc:mysql://localhost:3306/myfirstdb";
			String userName = "root";
			String password = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(database, userName, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void createTable() {
		Connection conn = getConnection();
		try {
			PreparedStatement create = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS teachers(id int NOT NULL AUTO_INCREMENT, lastname varchar(64), firstname varchar(64), PRIMARY KEY(id))");
			create.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void TeachersToDB(List<Teacher> list) throws SQLException {
		Connection conn = getConnection();

		for (Teacher teacher : list) {
			PreparedStatement s = conn.prepareStatement("INSERT INTO teachers (lastname, firstname) VALUES(?,?)");
			s.setString(1, teacher.getLastName());
			s.setString(2, teacher.getFirstName());
			s.executeUpdate();
		}
		conn.close();
	}

	public List<Teacher> getTeachersFromDB() {
		List<Teacher> TeachersList = new ArrayList<>();
		try {
			PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM teachers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher(rs.getString("firstname"), rs.getString("lastname"));
				TeachersList.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return TeachersList;
	}

	public boolean deleteTeacher(Teacher teacher) {
		Connection conn = getConnection();

		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM teachers WHERE firstname LIKE ? AND lastname LIKE ?");
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addTeacher(Teacher teacher) {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO teachers (firstname,lastname) VALUES (?,?)");
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
