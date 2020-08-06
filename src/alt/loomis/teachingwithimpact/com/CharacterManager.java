package alt.loomis.teachingwithimpact.com;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterManager {
	
	public String[] getCharacters() {
		SqLite sqlite = new SqLite();
		ResultSet rs;
		String[] response = null;
		try {
			String names = " ,";
			rs= sqlite.getCharacters();
			while(rs.next()) {
				//character, experience, skill1, level1, trained1, skill2, level2, trained2, settings
				/*System.out.println(rs.getString("character")+" "+rs.getString("experience")+" "
									+rs.getString("skill1")+" "+rs.getString("level1")+" "+rs.getString("trained1")+" "
									+rs.getString("skill2")+" "+rs.getString("level2")+" "+rs.getString("trained2")
									+" "+rs.getString("settings"));*/
				names+=(rs.getString("character")+",");
			}
			
			
			response = names.split(",",0);
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return response;
	}	
	
	public String convertArrayToString(String[] settings) {
		String output = "";
		for(int i=0; i<settings.length; i++) {
			if(i<settings.length-1) {
				output+=settings[i]+",";
			}else {
				output+=settings[i];
			}
		}
		//System.out.println(output);
		return output;
	}
	
	public String[] convertStringToArray(String data) {
		String[] settings = data.split(",",0);
		return settings;
	}
	public void saveNew(String cname) {
		SqLite sqlite = new SqLite();
		try {
			sqlite.saveCharacter(cname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void save(String cname, String experience, String skill1, String level1, String trained1, String skill2, String level2, String trained2, String settings) {
		SqLite sqlite = new SqLite();
		try {
			sqlite.saveCharacter(cname, experience, skill1, level1, trained1, skill2, level2, trained2, settings);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] getCharacterData(String name) {
		SqLite sqlite = new SqLite();
		ResultSet rs;
		String[] response = new String[10];
		try {
			rs= sqlite.getCharacter(name);
			while(rs.next()) {
				//character, experience, skill1, level1, trained1, skill2, level2, trained2, settings
				response[0] = rs.getString("character");
				response[1] = rs.getString("experience");
				response[2] = rs.getString("skill1");
				response[3] = rs.getString("level1");
				response[4] = rs.getString("trained1");
				response[5] = rs.getString("skill2");
				response[6] = rs.getString("level2");
				response[7] = rs.getString("trained2");
				response[8] = rs.getString("settings");	
				/*System.out.println(rs.getString("character")+" "+rs.getString("experience")+" "
						+rs.getString("skill1")+" "+rs.getString("level1")+" "+rs.getString("trained1")+" "
						+rs.getString("skill2")+" "+rs.getString("level2")+" "+rs.getString("trained2")
						+" "+rs.getString("settings"));*/
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return response;
	}

	public void delete(String name) {
		SqLite sqlite = new SqLite();
		try {
			sqlite.deleteCharacter(name);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}

	public void save_session_data(String cname, String gained, String sessionLength, String skill1, String s1Swings, String pct1, String sph1, String skill2, String s2Swings, String pct2, String sph2) {
		
		SqLite sqlite = new SqLite();
		
		try {
			sqlite.saveSession(cname, gained, sessionLength, skill1, s1Swings, pct1,  sph1, skill2, s2Swings, pct2, sph2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}