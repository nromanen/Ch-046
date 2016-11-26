package FirstProject;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FirstProject.Teacher;


public class TeachersToDB {
	 public List<Teacher> readTeachers(String fileName){
	        
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return  (List) mapper.readValue(new File(fileName), new TypeReference<List<Teacher>>(){});

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
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
		
		public static void createTable()  {
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

}
