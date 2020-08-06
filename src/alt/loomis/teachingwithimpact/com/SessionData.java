package alt.loomis.teachingwithimpact.com;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SessionData {
	SessionData(){
		JFrame frame = new JFrame("Session Data");
		String[] columns = {"DATE", "CHARACTER", "EXP GAINED", "TIME ELAPSED", "SKILL", "SWINGS", "% GAIN", "SpH", "SKILL", "SWINGS", "% GAIN", "SpH"}; 
		
		
		SqLite sqlite = new SqLite();
		ResultSet rs;
		
		try {
			
			rs= sqlite.getSessionData();
			ArrayList<String[]> data = new ArrayList<String[]>();
			while(rs.next()) {
				//character, experience_gained, length_of_session, skill1, skill1_swings, pct1_gained, sph1, skill2, skill2_swings, pct2_gained, sph2
				String[] rowdata = {rs.getString("date"), rs.getString("character"), rs.getString("experience_gained"), rs.getString("length_of_session"), 
						rs.getString("skill1"), rs.getString("skill1_swings"), rs.getString("pct1_gained"), rs.getString("sph1"),
						rs.getString("skill2"), rs.getString("skill2_swings"), rs.getString("pct2_gained"), rs.getString("sph2")
				};
				data.add(rowdata);
			}
			
			String[][] rows = new String[data.size()][12];
			for(int r=0; r<data.size(); r++) {
				for(int c=0; c<12; c++) {
					rows[r][c] = data.get(r)[c];
				}
			}
			
			// get the screen size as a java dimension
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

			// get 2/3 of the height, and 2/3 of the width
			int height = (int)(screenSize.height * 0.9);
			if(height>820) height = 820;
			int width = (int)(screenSize.width * 0.9);
			if(width>1200) width = 1000;
			JTable jt=new JTable(rows,columns);   
			//jt.setBounds(30, 40, 1200, 1000);
			jt.setPreferredSize(new Dimension(1200, 1000));
			JScrollPane sp=new JScrollPane(jt);   
			frame.add(sp);
			frame.setSize(width, height);
			frame.setVisible(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
