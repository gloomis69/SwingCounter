package alt.loomis.teachingwithimpact.com;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class TrackingPanel extends JPanel{
	private static final long serialVersionUID = 5840930458430349729L;
	public static JLabel lblSwingCount;
	public static JLabel lblSwingCount2;
	private JLabel lblSph1;
	private float swingsNeeded;
	private JLabel lblSwingsNeeded;
	private float swingsNeeded2;
	private JLabel lblSwingsNeeded2;
	private StartPanel spanel;
	private JLabel lblSph2;
	private JLabel skill1_bg;
	private JLabel skill2_bg;
	private Timer timer;
	private boolean paused = true;
	private JButton btnPause;
	private JLabel lblTime;
	private int secondsElapsed = 0;
	//private SwingKeyCapture kc;
	private JLabel lblPctCalc1;
	private JLabel lblPctCalc2;
	private JLabel lblpph1;
	private JLabel lblpph2;
	private JLabel skill1Estimate;
	private JLabel skill2Estimate;
	private JLabel lblNewLabel_2;
	private JLabel hundredths1;
	private JLabel lblNewLabel_3;
	private JLabel hundredths2;
	private static int skillNumBeingTracked = 0; //0=no skill tracked, 1=skill 1, 2=skill 2: remember -1 for index of swingCount
    private static int[] swingCount = {0, 0};
    private boolean playStarted = false;
	private JButton btnSaveSession;
    private long startTime;
	private int secElapsedBeforePause;
    
	TrackingPanel(StartPanel startpanel){
		
		
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setGroupingUsed(true);
		fmt.setMaximumFractionDigits(3);

		spanel = startpanel;
		spanel.setTPanel(this);
		
		JLabel lblTimeTitle = new JLabel("Time Elapsed:");
		lblTimeTitle.setBounds(10, 5, 160, 30);
		this.add(lblTimeTitle);
		
		lblTime = new JLabel("0:00:00");
		lblTime.setBounds(10, 25, 160, 30);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTime);
		
		JLabel lblSwings = new JLabel("Skill 1 Swing Count: ");		
		lblSwings.setBounds(10, 60, 160, 30);
		lblSwings.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		lblSwings.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSwings);



		lblSwingCount = new JLabel(0+"");
		lblSwingCount.setBounds(10, 95, 160, 30);
		lblSwingCount.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		lblSwingCount.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSwingCount);


		JLabel sneed = new JLabel("Swings until next %:");
		sneed.setBounds(20, 155, 140, 30);
		sneed.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(sneed);
		swingsNeeded = startpanel.getSwingsPerPct(1);		
		lblSwingsNeeded = new JLabel(swingsNeeded+"");		
		lblSwingsNeeded.setBounds(20, 175, 140, 30);
		lblSwingsNeeded.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblSwingsNeeded);

		JLabel lblPctGain1 = new JLabel("% gained:");		
		lblPctGain1.setBounds(20, 200, 140, 30);
		lblPctGain1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblPctGain1);
		lblPctCalc1 = new JLabel("0%");		
		lblPctCalc1.setBounds(20, 220, 140, 30);
		lblPctCalc1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblPctCalc1);

		JLabel lbl1 = new JLabel("Swings per hour:");		
		lbl1.setBounds(20, 250, 140, 30);
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lbl1);

		lblSph1 = new JLabel("0");		
		lblSph1.setBounds(20, 270, 140, 30);
		lblSph1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblSph1);

		JLabel lbl1a = new JLabel("% per hour:");		
		lbl1a.setBounds(20, 290, 140, 30);
		lbl1a.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lbl1a);
		
		lblpph1 = new JLabel("0%");		
		lblpph1.setBounds(20, 310, 140, 30);
		lblpph1.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblpph1);
		
		PropertyChangeListener l = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				int swings = Integer.parseInt(lblSwingCount.getText());

				float spp = startpanel.getSwingsPerPct(1);
				float pctGained = (float) swings/spp;
				lblPctCalc1.setText(String.format("%.3f", pctGained)+"%");
				/*float pctph = (float)pctGained/count*3600;
				lblpph1.setText(String.format("%.3f", pctph)+"%");*/
			}
		};
		lblSwingCount.addPropertyChangeListener("text", l);



		JLabel lblSwings2 = new JLabel("Skill 2 Swing Count: ");		
		lblSwings2.setBounds(10, 360, 160, 30);
		lblSwings2.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		lblSwings2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSwings2);

		lblSwingCount2 = new JLabel(0+"");
		lblSwingCount2.setBounds(10, 395, 160, 30);
		lblSwingCount2.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		lblSwingCount2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSwingCount2);


		JLabel sneed2 = new JLabel("Swings until next %:");
		sneed2.setBounds(20, 455, 140, 30);
		sneed2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(sneed2);
		swingsNeeded2 = startpanel.getSwingsPerPct(2);		
		lblSwingsNeeded2 = new JLabel(swingsNeeded2+"");		
		lblSwingsNeeded2.setBounds(20, 475, 140, 30);
		lblSwingsNeeded2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblSwingsNeeded2);

		JLabel lblPctGain2 = new JLabel("% gained:");		
		lblPctGain2.setBounds(20, 500, 140, 30);
		lblPctGain2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblPctGain2);
		lblPctCalc2 = new JLabel("0%");		
		lblPctCalc2.setBounds(20, 520, 140, 30);
		lblPctCalc2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblPctCalc2);

		JLabel lbl2 = new JLabel("Swings per hour:");		
		lbl2.setBounds(20, 550, 140, 30);
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lbl2);

		lblSph2 = new JLabel("0");		
		lblSph2.setBounds(20, 570, 140, 30);
		lblSph2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblSph2);

		JLabel lbl2a = new JLabel("% per hour:");		
		lbl2a.setBounds(20, 590, 140, 30);
		lbl2a.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lbl2a);
		
		lblpph2 = new JLabel("0%");		
		lblpph2.setBounds(20, 610, 140, 30);
		lblpph2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lblpph2);
		
		PropertyChangeListener l2 = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				int swings = Integer.parseInt(lblSwingCount2.getText());

				float spp = startpanel.getSwingsPerPct(2);
				float pctGained = (float) swings/spp;
				lblPctCalc2.setText(String.format("%.3f", pctGained)+"%");
			}
		};
		lblSwingCount2.addPropertyChangeListener("text", l2);

		skill1_bg = new JLabel();
		skill1_bg.setBounds(8,58,164,284);
		skill1_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		this.add(skill1_bg);

		skill2_bg = new JLabel();
		skill2_bg.setBounds(8,360,164,300);
		skill2_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		this.add(skill2_bg);

		btnPause = new JButton("Pause");
		btnPause.setBounds(20,671,140,30);
		btnPause.setVisible(false);
		btnPause.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				CharacterManager cm = new CharacterManager();
				String charName = (String)spanel.getCharName().getSelectedItem();
				float pctGained = (float)spanel.getSwingsPerPct(1)/(float)swingCount[0];
				float current = cm.getSavedPct(charName, 1);
				System.out.println("pct saved: "+pctGained+", current: "+current);
				
				
				if(paused) {
					paused = false;
					playStarted = true;
					btnPause.setText("Pause");
					/*timer = new Timer();
					timer.schedule(new PlayTime(), 1000, 1*1000);*/
					startPlayTimer();
				}else {
					paused = true;
					playStarted = false;
					btnPause.setText("Resume");
					timer.cancel();
				}
			}  
		}); 
		this.add(btnPause);		

		
		
		
		this.setBackground(Color.white);
		this.setSize(200, 800);
		this.setLayout(null);
		
		skill1Estimate = new JLabel("");
		skill1Estimate.setHorizontalAlignment(SwingConstants.RIGHT);
		skill1Estimate.setBounds(20, 130, 104, 14);
		add(skill1Estimate);
		
		skill2Estimate = new JLabel("");
		skill2Estimate.setBounds(20, 430, 104, 14);
		add(skill2Estimate);
		
		lblNewLabel_2 = new JLabel("100ths");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(130, 137, 30, 14);
		lblNewLabel_2.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		add(lblNewLabel_2);
		
		hundredths1 = new JLabel("0");
		hundredths1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		hundredths1.setHorizontalAlignment(SwingConstants.CENTER);
		hundredths1.setBounds(128, 125, 35, 14);
		add(hundredths1);
		this.setVisible(true);
		
		
		
		lblNewLabel_3 = new JLabel("100ths");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(130, 437, 30, 14);
		lblNewLabel_3.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		add(lblNewLabel_3);
		
		hundredths2 = new JLabel("0");
		hundredths2.setFont(new Font("Tahoma", Font.PLAIN, 8));
		hundredths2.setHorizontalAlignment(SwingConstants.CENTER);
		hundredths2.setBounds(128, 425, 35, 14);
		add(hundredths2);
		
		btnSaveSession = new JButton("End Session");
		btnSaveSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int optionToSave = JOptionPane.showConfirmDialog(null, "Save session data?", "Reset Session", JOptionPane.YES_NO_CANCEL_OPTION);
				if(optionToSave == JOptionPane.YES_OPTION) {
					saveSession();
				}else if(optionToSave == JOptionPane.NO_OPTION) {
					resetTrackingSession();
				}
			}
		});
		btnSaveSession.setBounds(20, 705, 140, 30);
		btnSaveSession.setVisible(false);
		add(btnSaveSession);
		this.setVisible(true);
	}

	public void keyPressed(int key) {
		if(!paused && playStarted) {
        	if(key == 192 || key == 160) {
        		skillNumBeingTracked=0;
        	}
        	
        	if(key == 13) {
        		NumberFormat fmt = NumberFormat.getInstance();
        		fmt.setGroupingUsed(true);
            	if(skillNumBeingTracked==1) {
            		swingCount[0]++; 
                	lblSwingCount.setText(swingCount[0]+"");
                	float sremaining = Float.parseFloat(lblSwingsNeeded.getText().replaceAll(",", ""))-1;
                	if(sremaining<=0) sremaining = Float.parseFloat(spanel.getSwingsNeeded(1).replaceAll(",", ""));
                	lblSwingsNeeded.setText(fmt.format(sremaining)+"");
                	
            	}else if(skillNumBeingTracked==2) {            		
            		swingCount[1]++;
            		//System.out.println("Swing Total: "+swingCount[1]);
                	lblSwingCount2.setText(swingCount[1]+"");
                	float sremaining = Float.parseFloat(lblSwingsNeeded2.getText().replaceAll(",", ""))-1;
                	if(sremaining<=0) sremaining = Float.parseFloat(spanel.getSwingsNeeded(2).replaceAll(",", ""));
                	lblSwingsNeeded2.setText(fmt.format(sremaining)+"");
            	}                            	
            }
            
            //F1 to F10 pressed determines which skill number to track
            for(int i=0; i<10; i++) {
            	if(key==(112+i)) {
            		skillNumBeingTracked = spanel.getSkillTracked(i);            		
            	}
            }
            
            if(key == 70) { //"f" - track fight
            	skillNumBeingTracked = spanel.getSkillTracked(10);
            }
            if(key == 74) { //"j" - track jumpkick
            	skillNumBeingTracked = spanel.getSkillTracked(11);
            }
            if(key == 80) { //"p" - track polekick
            	skillNumBeingTracked = spanel.getSkillTracked(12);
            }
            
           toggleSkillAlert(skillNumBeingTracked);
        }
	}
	
	public void pauseTracker() {
		btnPause.doClick();
	}
	public String getSessionLength() {
		return lblTime.getText();
	}
	
	/*public void loadKeyCapture(SwingKeyCapture kc) {
		this.kc = kc;
	}*/
	
	public void toggleSkillAlert(int skillNum) {
		switch (skillNum) {
		case 0:
			lblSwingCount.setForeground(Color.black);
			lblSwingCount2.setForeground(Color.black);
			skill1_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
			skill2_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
			break;
		case 1:
			lblSwingCount.setForeground(new Color(0,150,0));
			lblSwingCount2.setForeground(Color.black);
			skill1_bg.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
			skill2_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
			break;
		case 2:
			lblSwingCount.setForeground(Color.black);
			lblSwingCount2.setForeground(new Color(0,150,0));
			skill1_bg.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
			skill2_bg.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
		}


	}
	
	private float calculateSwingsNeeded(int skillnum) {
		float swpp = spanel.getSwingsPerPct(skillnum);
		String pctgainedString = lblPctCalc1.getText();
		if(skillnum==2) pctgainedString = lblPctCalc2.getText();
		pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
		float pctGained = Float.parseFloat(pctgainedString);
		float getLvl = spanel.getLevel1()+pctGained/100.0f;
		if(skillnum==2) getLvl = spanel.getLevel2()+pctGained/100.0f;
		float pct = getLvl%1;
		float swRemaining = swpp - swpp*pct;		
		return swRemaining;
	}
	
	public void startPlayTimer() {	
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
		
		spanel.toggleTracking(true);
		playStarted = true;
		startTime = System.currentTimeMillis();
		paused = false;
		btnPause.setVisible(true);
		btnSaveSession.setVisible(true);
		//swingsNeeded = spanel.getSwingsPerPct(1);
		swingsNeeded = calculateSwingsNeeded(1);		
		lblSwingsNeeded.setText(swingsNeeded+"");
		if(swingsNeeded>20) lblSwingsNeeded.setText(fmt.format((int)swingsNeeded));
		//swingsNeeded2 = spanel.getSwingsPerPct(2);
		swingsNeeded2 = calculateSwingsNeeded(2);
		lblSwingsNeeded2.setText(swingsNeeded2+"");
		if(swingsNeeded2>20) lblSwingsNeeded2.setText(fmt.format((int)swingsNeeded2));
		secElapsedBeforePause = secondsElapsed;
		
		timer = new Timer();
		timer.schedule(new PlayTime(), 1000, 1*1000);
		
	}

	

	class PlayTime extends TimerTask{		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			secondsElapsed = Math.round((System.currentTimeMillis()-startTime)/1000)+secElapsedBeforePause;
			if(secondsElapsed==1 || secondsElapsed%3==0) {
				int sCount = Integer.parseInt(lblSwingCount.getText());
				float sph = (float) sCount/secondsElapsed*3600;
				lblSph1.setText((int)sph+"");
				String pctgainedString = lblPctCalc1.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				float pctGained = Float.parseFloat(pctgainedString);
				float pctph = (float)pctGained/secondsElapsed*3600;
				lblpph1.setText(String.format("%.3f", pctph)+"%");
				float getLvl = spanel.getLevel1()+pctGained/100.0f;
				int wholeLvl = Math.round(getLvl);
				int pct = Math.round(getLvl%1*100);
				int partialPct = (int)(((float) ((Math.round(getLvl*10000)/100.0))%1)*100); 
				skill1Estimate.setText("Lvl "+wholeLvl+"    "+pct+"% and ");
				hundredths1.setText(partialPct+"");
				
				
				sCount = Integer.parseInt(lblSwingCount2.getText());
				sph = (float) sCount/secondsElapsed*3600;	
				lblSph2.setText((int)sph+"");
				pctgainedString = lblPctCalc2.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				pctGained = Float.parseFloat(pctgainedString);
				pctph = (float)pctGained/secondsElapsed*3600;
				lblpph2.setText(String.format("%.3f", pctph)+"%");
				getLvl = spanel.getLevel2()+pctGained/100.0f;
				wholeLvl = Math.round(getLvl);
				pct = Math.round(getLvl%1*100);
				partialPct = (int)(((float) ((Math.round(getLvl*10000)/100.0))%1)*100); 
				skill2Estimate.setText("Lvl "+wholeLvl+"    "+pct+"% and ");
				hundredths2.setText(partialPct+"");
			}
			
			int hours = secondsElapsed/3600;
			String hh = hours+"";
			if(hours<10) hh = "0"+hours;
			int minutes = (secondsElapsed/60)-(hours*60);
			String mm = minutes+"";
			if(minutes<10) mm = "0"+minutes;
			int seconds = secondsElapsed%60;
			String ss = seconds+"";
			if(seconds<10) ss = "0"+seconds;
			lblTime.setText(hh+":"+mm+":"+ss);
		}

	}

	/*public JLabel[][] getLabels() {
		JLabel[] labels1 = {lblSwingCount, lblSwingsNeeded};
		JLabel[] labels2 = {lblSwingCount2, lblSwingsNeeded2};
		JLabel[][] labels = {labels1, labels2};
		return labels;
	}*/
	
	public void saveSession() {
		
		if(spanel.getCharName().getSelectedIndex()!=0) {
			try {
				if(timer!=null) timer.cancel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Long exp = Long.parseLong(spanel.getExperience().getText());
			String endExp = JOptionPane.showInputDialog(this.getParent(), "Enter you characters experience", exp);
	    	
	    	if(endExp!=null && !endExp.isEmpty()) {
	    		CharacterManager cm = new CharacterManager();
	    		String charName = (String)spanel.getCharName().getSelectedItem();
				Long endexp = Long.parseLong(endExp);
				Long gained = endexp-exp;
				spanel.setExperience(endExp+"");
				String pctgainedString = lblPctCalc1.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				//float pctGained = Float.parseFloat(pctgainedString)/100.0f;
				float pctGained = spanel.getSwingsPerPct(1)/(float)swingCount[0];
				
				//float current = spanel.getLevel1();
				float current = cm.getSavedPct(charName, 1);
				
				float getLvl = current+pctGained;
				getLvl = ((int)(getLvl*10000))/10000.0f;
				spanel.setLevel1(getLvl);
				
				pctgainedString = lblPctCalc2.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				//pctGained = Float.parseFloat(pctgainedString)/100.0f;
				//current = spanel.getLevel2();
				pctGained = spanel.getSwingsPerPct(2)/(float)swingCount[1];
				current = cm.getSavedPct(charName, 2);
				getLvl = current+pctGained;
				getLvl = ((int)(getLvl*10000))/10000.0f;
				spanel.setLevel2(getLvl);
				
				spanel.save();
				
				
				cm.save_session_data(charName, gained+"", getSessionLength(), spanel.getSkill(1), lblSwingCount.getText(), lblPctCalc1.getText(), lblSph1.getText(), spanel.getSkill(2), lblSwingCount2.getText(), lblPctCalc2.getText(), lblSph2.getText());
				JOptionPane.showMessageDialog(this.getParent(),"Experience gained this session: "+gained);
				
				resetTrackingSession();
	    	}
		}
	}

	private void resetTrackingSession() {
		swingCount[0]=0;
		swingCount[1]=0;
		lblSwingCount.setText(0+"");
		lblSwingCount2.setText(0+"");
		lblSph1.setText(0+"");
		//swingsNeeded=0;
		lblSwingsNeeded.setText(0+"");
		//swingsNeeded2=0;
		lblSwingsNeeded2.setText(0+"");
		lblSph2.setText(0+"");
		try {
			timer.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		secondsElapsed = 0;
		lblPctCalc1.setText(0+"%");
		lblPctCalc2.setText(0+"%");
		lblpph1.setText(0+"");
		lblpph2.setText(0+"");
		skill1Estimate.setText("");
		skill2Estimate.setText("");
		hundredths1.setText(0+"");
		hundredths2.setText(0+"");	
		skillNumBeingTracked=0;
		playStarted=false;
		paused = true;
		lblTime.setText("00:00:00");
		toggleSkillAlert(0);
		spanel.toggleTracking(false);
		btnSaveSession.setVisible(false);
		btnPause.setVisible(false);
	}
}
