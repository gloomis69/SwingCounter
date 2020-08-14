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
	private int count = 0;
	private SwingKeyCapture kc;
	private JLabel lblPctCalc1;
	private JLabel lblPctCalc2;
	private JLabel lblpph1;
	private JLabel lblpph2;
	private JLabel skill1Estimate;
	private JLabel lblNewLabel_1;
	private JLabel skill2Estimate;
	private JLabel lblNewLabel_2;
	private JLabel hundredths1;
	
	TrackingPanel(StartPanel startpanel){
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setGroupingUsed(true);
		fmt.setMaximumFractionDigits(3);

		spanel = startpanel;
		
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
		btnPause.setBounds(100,670,70,30);
		btnPause.setVisible(false);
		btnPause.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	            
				if(paused) {
					paused = false;
					btnPause.setText("Pause");
					kc.paused = false;
					timer = new Timer();
					timer.schedule(new PlayTime(), 1000, 1*1000);
				}else {
					paused = true;
					kc.paused = true;
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
		
		lblNewLabel_1 = new JLabel("Estimated Level:");
		lblNewLabel_1.setBounds(20, 430, 84, 14);
		add(lblNewLabel_1);
		
		skill2Estimate = new JLabel("0");
		skill2Estimate.setBounds(114, 430, 46, 14);
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
	}

	public void pauseTracker() {
		btnPause.doClick();
	}
	public String getSessionLength() {
		return lblTime.getText();
	}
	
	public void loadKeyCapture(SwingKeyCapture kc) {
		this.kc = kc;
	}
	
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

	public void startPlayTimer() {	
		paused = false;
		btnPause.setVisible(true);
		swingsNeeded = spanel.getSwingsPerPct(1);
		lblSwingsNeeded.setText(swingsNeeded+"");
		swingsNeeded2 = spanel.getSwingsPerPct(2);	
		lblSwingsNeeded2.setText(swingsNeeded2+"");
		timer = new Timer();
		timer.schedule(new PlayTime(), 1000, 1*1000);
	}

	class PlayTime extends TimerTask{		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			count++;
			if(count%5==0) {
				int sCount = Integer.parseInt(lblSwingCount.getText());
				float sph = (float) sCount/count*3600;
				lblSph1.setText((int)sph+"");
				String pctgainedString = lblPctCalc1.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				float pctGained = Float.parseFloat(pctgainedString);
				float pctph = (float)pctGained/count*3600;
				lblpph1.setText(String.format("%.3f", pctph)+"%");
				float getLvl = spanel.getLevel1()+pctGained/100.0f;
				System.out.println(getLvl);
				int wholeLvl = Math.round(getLvl);
				int pct = Math.round(getLvl%1*100);
				int partialPct = (int)(((float) ((Math.round(getLvl*10000)/100.0))%1)*100); 
				
								
				skill1Estimate.setText("Lvl "+wholeLvl+"    "+pct+"% and ");
				hundredths1.setText(partialPct+"");
				sCount = Integer.parseInt(lblSwingCount2.getText());
				sph = (float) sCount/count*3600;
				
				
				lblSph2.setText((int)sph+"");
				pctgainedString = lblPctCalc2.getText();
				pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
				pctGained = Float.parseFloat(pctgainedString);
				pctph = (float)pctGained/count*3600;
				lblpph2.setText(String.format("%.3f", pctph)+"%");
				
			}
			
			int hours = count/3600;
			String hh = hours+"";
			if(hours<10) hh = "0"+hours;
			int minutes = (count/60)-(hours*60);
			String mm = minutes+"";
			if(minutes<10) mm = "0"+minutes;
			int seconds = count%60;
			String ss = seconds+"";
			if(seconds<10) ss = "0"+seconds;
			lblTime.setText(hh+":"+mm+":"+ss);
		}

	}

	public JLabel[][] getLabels() {
		JLabel[] labels1 = {lblSwingCount, lblSwingsNeeded};
		JLabel[] labels2 = {lblSwingCount2, lblSwingsNeeded2};
		JLabel[][] labels = {labels1, labels2};
		return labels;
	}
	
	public void setExperience(String endExp) {
		if(spanel.getCharName().getSelectedIndex()!=0) {
			Long exp = Long.parseLong(spanel.getExperience().getText());
			Long endexp = Long.parseLong(endExp);
			Long gained = endexp-exp;
			spanel.setExperience(endExp+"");
			String pctgainedString = lblPctCalc1.getText();
			pctgainedString = pctgainedString.substring(0, pctgainedString.length()-1);
			float pctGained = Float.parseFloat(pctgainedString)/100.0f;
			System.out.println("pctGained: "+pctGained);
			float current = spanel.getLevel1();
			System.out.println("current: "+current);
			float getLvl = current+pctGained;
			
			getLvl = ((int)(getLvl*10000))/10000.0f;
			System.out.println("total: "+getLvl);
			
			spanel.setLevel(getLvl);
			spanel.save();
			
			CharacterManager cm = new CharacterManager();
			cm.save_session_data((String)spanel.getCharName().getSelectedItem(), gained+"", getSessionLength(), spanel.getSkill(1), lblSwingCount.getText(), lblPctCalc1.getText(), lblSph1.getText(), spanel.getSkill(2), lblSwingCount2.getText(), lblPctCalc2.getText(), lblSph2.getText());
			JOptionPane.showMessageDialog(this.getParent(),"Experience gained this session: "+gained);
		}
	}
}
