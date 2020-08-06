package alt.loomis.teachingwithimpact.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqLite {
	private static Connection con;
	private static boolean hasData = false;
	
	public ResultSet getCharacters() throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT * FROM characters");
		return res;
	}
	public ResultSet getCharacter(String cname) throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
		
		String query = "SELECT * FROM characters WHERE character = ? LIMIT 1";
		PreparedStatement prep = con.prepareStatement(query);
		prep.setString(1, cname);
		ResultSet res = prep.executeQuery();
		
		return res;
	}
	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SwingCounterData.db");
		initialize();
	}

	private void initialize() throws SQLException {
		if(!hasData) {
			hasData = true;
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='characters'");
			if(!res.next()) {
				System.out.println("Building the Characters table");
				//need to build the table since it doesn't exist yet
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE characters(character varchar(60)," + "experience varchar(60),"
						 + "skill1 varchar(10)," + "level1 varchar(40)," + "trained1 varchar(2),"
						 + "skill2 varchar(10)," + "level2 varchar(40)," + "trained2 varchar(2),"
						 + "settings varchar(50),"
						+ "primary key(character));");
				
				
			}
			
			
			ResultSet res2 = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='sessions'");
			if(!res2.next()) {
				System.out.println("Building the Sessions table");
				//need to build the table since it doesn't exist yet
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE sessions(id integer," 
						+ "date text DEFAULT CURRENT_TIMESTAMP," 
						+ "character varchar(60)," 
						+ "experience_gained varchar(60)," 
						+ "length_of_session varchar(30),"
						+ "skill1 varchar(10),"+ "skill1_swings varchar(10)," + "pct1_gained varchar(10)," + "sph1 varchar(10),"
						+ "skill2 varchar(10),"+ "skill2_swings varchar(10)," + "pct2_gained varchar(10)," + "sph2 varchar(10),"
						+ "primary key(id));");
				
				
			}
		}
	}
	
	public void saveCharacter(String cname) throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
				
		PreparedStatement prep = con.prepareStatement("INSERT OR IGNORE INTO characters(character, experience, skill1, level1, trained1, skill2, level2, trained2, settings) values(?,?,?,?,?,?,?,?,?)");
		prep.setString(1, cname);
		prep.setString(2, "0");
		prep.setString(3, "");
		prep.setString(4, "");
		prep.setString(5, "false");
		prep.setString(6, "");
		prep.setString(7, "");
		prep.setString(8, "false");
		prep.setString(9, "0,0,0,0,0,0,0,0,0,0,0,0,0");
		prep.execute();
	}
	
	public void saveCharacter(String cname, String experience, String skill1, String level1, String trained1, String skill2, String level2, String trained2, String settings) throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
		
		
		PreparedStatement prep = con.prepareStatement("INSERT OR IGNORE INTO characters(character, experience, skill1, level1, trained1, skill2, level2, trained2, settings) values(?,?,?,?,?,?,?,?,?)");
		prep.setString(1, cname);
		prep.setString(2, experience);
		prep.setString(3, skill1);
		prep.setString(4, level1);
		prep.setString(5, trained1);
		prep.setString(6, skill2);
		prep.setString(7, level2);
		prep.setString(8, trained2);
		prep.setString(9, settings);
		prep.execute();
		
		String query = "UPDATE characters SET experience=?, skill1=?, level1=?, trained1=?, skill2=?, level2=?, trained2=?, settings=? WHERE character = ?;";
				
		PreparedStatement prep2 = con.prepareStatement(query);
		
		prep2.setString(1, experience);
		prep2.setString(2, skill1);
		prep2.setString(3, level1);
		prep2.setString(4, trained1);
		prep2.setString(5, skill2);
		prep2.setString(6, level2);
		prep2.setString(7, trained2);
		prep2.setString(8, settings);
		prep2.setString(9,  cname);
		prep2.execute();
	}
	
	public void deleteCharacter(String cname) throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
				
		PreparedStatement prep = con.prepareStatement("DELETE FROM characters WHERE character = ?");
		prep.setString(1, cname);		
		prep.execute();
	}
	
	public void saveSession(String cname, String gained, String sessionLength, String skill1, String s1Swings, String pct1, String sph1, String skill2, String s2Swings, String pct2, String sph2) throws SQLException, ClassNotFoundException {
		if(con==null) {
			getConnection();
		}
			
		PreparedStatement prep = con.prepareStatement("INSERT INTO sessions(character, experience_gained, length_of_session, skill1, skill1_swings, pct1_gained, sph1, skill2, skill2_swings, pct2_gained, sph2) values(?,?,?,?,?,?,?,?,?,?,?)");
		
		prep.setString(1, cname);
		prep.setString(2, gained);
		prep.setString(3, sessionLength);
		prep.setString(4, skill1);
		prep.setString(5, s1Swings);
		prep.setString(6, pct1);
		prep.setString(7, sph1);
		prep.setString(8, skill2);
		prep.setString(9, s2Swings);
		prep.setString(10, pct2);
		prep.setString(11, sph2);
		prep.execute();
		
	}
	
	public ResultSet getSessionData() throws ClassNotFoundException, SQLException {
		if(con==null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT * FROM sessions ORDER BY date DESC");
		return res;
	}
}
