package alt.loomis.teachingwithimpact.com;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.text.NumberFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;



public class StartPanel extends JPanel{
	private static final long serialVersionUID = -7703329180555522456L;
	
	/*DATA*/
	private String characterName = "";
	private long experience = 0;
	
	private String skill1Name = "";
	private float fullLevel_1 = 0;
	private float swpl_1 = 0;
	
	private String skill2Name = "";
	private float fullLevel_2 = 0;
	private float swpl_2 = 0;
	
	
	/*interface elements*/
	private static ButtonGroup[] rbGroups = new ButtonGroup[13];
	private JRadioButton[][] buttons = new JRadioButton[13][3];
	public static int[] skillsTracked = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	private JLabel Sw_per_pct1;
	private JLabel Sw_per_pct2;
	private JCheckBox trained1;
	private JCheckBox trained2;
	private JLabel Sw_per_lvl1;
	private JLabel Sw_per_lvl2;
	private String[] skillNames = {"", "1-Awkward", "2-Mediocre", "3-Capable", "4-Familiar", "5-Practiced", "6-Competent", "7-Experienced", "8-Skillful", "9-Proficient",  "10-Exceptional", "11-Brilliant",
									"12-Expert", "13-Astonishing", "14-Amazing", "15-Incredible", "16-Master", "17-Genius", "18-Unearthly", "19-Immortal"};
	private String[] skillNames_hand = {"", "White", "Yellow", "Green", "Blue", "Red", "Black", "1st Dan", "2nd Dan", "3rd Dan",  "4th Dan", "5th Dan",
										"6th Dan", "7th Dan", "8th Dan", "9th Dan", "White Sash", "Red Sash", "Gold Sash", "Master"};
	private JComboBox level1_CB;
	private JComboBox level2_CB;
	private JComboBox skill1_CB;
	private JComboBox skill2_CB;
	private JTextField experience_TF;
	private JButton btnSave;
	private JComboBox charName;
	private JTextField pctGained1;
	private JTextField pctGained2;
	private JLabel lblSaveWarning;
	private TrackingPanel tpanel;
	private String[] skillsList = {"Bow", "Dagger", "Flail", "Halberd", "Mace", "Rapier", "Shuriken", "Staff", "Sword", "Twohanded", "Threestaff", "Hand"};
	
	StartPanel(){
		loadInterface();
		
		this.setBackground(Color.white);
		this.setLayout(null);		
		this.setVisible(true);
	}
	
	/*
	 * CREATE INTERFACE
	 */
	private void loadInterface() {
		UIManager.put("ComboBox.disabledForeground", Color.BLACK);
		
		loadCharacterInterface();
		loadExperienceInterface();
		loadSkillSelectionInterface();
		loadSkillLevelInterface();
		loadSkillPercentagesInterface();
		loadTrainingToggleInterface();
		loadSkillPerPctInterface();
		loadSkillPerLevelInterface();
		loadSaveInterface();
		createRadios();
	}
	
	private void loadSkillPercentagesInterface() {
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(245, 79, 35, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(245, 128, 35, 14);
		this.add(lblNewLabel_1);
		
		pctGained1 = new JTextField();
		pctGained1.setDisabledTextColor(Color.BLACK);
		pctGained1.setText("0");
		pctGained1.setHorizontalAlignment(SwingConstants.CENTER);
		pctGained1.setBounds(244, 95, 38, 20);
		this.add(pctGained1);
		pctGained1.setColumns(10);
		
		
		
		pctGained2 = new JTextField();
		pctGained2.setDisabledTextColor(Color.BLACK);
		pctGained2.setText("0");
		pctGained2.setHorizontalAlignment(SwingConstants.CENTER);
		pctGained2.setBounds(244, 145, 38, 20);
		this.add(pctGained2);
		pctGained2.setColumns(10);
	}

	private void loadCharacterInterface() {
		JLabel lbl1= new JLabel("Character Name: ");		
		lbl1.setBounds(10, 8, 150, 30);
		CharacterManager cm = new CharacterManager();
		String[] cnames = cm.getCharacters();
		charName = new JComboBox();
		if(cnames!=null) {
			charName = new JComboBox(cnames);
		}
		charName.setBounds(10,38, 150,30); 
		charName.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		
		charName.addItemListener(new NameSelected());
		this.add(lbl1);
		this.add(charName);
	}
	
	private void loadExperienceInterface() {
		JLabel lbl2= new JLabel("Starting Experience: ");		
		lbl2.setBounds(168, 8, 150, 30);
		experience_TF = new JTextField();
		experience_TF.setDisabledTextColor(Color.BLACK);
		
		Border spacer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border linedBorder = BorderFactory.createLineBorder(Color.black);
		getExperience().setBounds(168,38, 150,30);
		getExperience().setHorizontalAlignment(SwingConstants.RIGHT);
		getExperience().setBorder(BorderFactory.createCompoundBorder(linedBorder, spacer));
		this.add(lbl2);
		this.add(experience_TF);
	}
	
	private void loadSkillSelectionInterface() {
		JLabel lbl6= new JLabel("Skill 1: ");		
		lbl6.setBounds(10, 70, 40, 30);		
		skill1_CB = new JComboBox(skillsList);
		skill1_CB.setBounds(10, 95, 120, 20);
		
		
		JLabel lbl7= new JLabel("Skill 2: ");		
		lbl7.setBounds(10, 120, 40, 30);
		skill2_CB = new JComboBox(skillsList);
		skill2_CB.setBounds(10, 145, 120, 20);
				
		
		skill1_CB.addItemListener(new Skill_1_Selected());
		
		
		skill2_CB.addItemListener(new Skill_2_Selected());
		
		this.add(lbl6);this.add(lbl7);
		this.add(skill1_CB);this.add(skill2_CB);
	}
		
	private void loadSkillLevelInterface() {
		JLabel lbl8= new JLabel("Level: ");		
		lbl8.setBounds(140, 70, 40, 30);
		level1_CB = new JComboBox(skillNames);		
		level1_CB.setBounds(140, 95, 100, 20);
		
		JLabel lbl9= new JLabel("Level: ");		
		lbl9.setBounds(140, 120, 40, 30);
		level2_CB = new JComboBox(skillNames);
		level2_CB.setBounds(140, 145, 100, 20);
		
		
		level1_CB.addItemListener(new Level_1_Selected());
		level2_CB.addItemListener(new Level_2_Selected());
		this.add(lbl8);this.add(lbl9);
		this.add(level1_CB);this.add(level2_CB);
	}
		
	private void loadTrainingToggleInterface() {
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
		
		JLabel lbl10= new JLabel("Trained?");		
		lbl10.setToolTipText("Is this skill trained?");
		lbl10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl10.setBounds(280, 70, 30, 30);
		trained1 = new JCheckBox();
		trained1.setToolTipText("Is this skill trained?");
		trained1.setBounds(285, 90, 20, 30);
		
		trained1.addItemListener(new Training_1_Selected());
		
		
		JLabel lbl11= new JLabel("Trained?");		
		lbl11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl11.setToolTipText("Is this skill trained?");
		lbl11.setHorizontalAlignment(SwingConstants.CENTER);
		lbl11.setBounds(280, 120, 30, 30);
		trained2 = new JCheckBox();
		trained2.setToolTipText("Is this skill trained?");
		trained2.setBounds(285, 140, 20, 30);
		
		trained2.addItemListener(new Training_2_Selected());
		
		this.add(lbl10);this.add(lbl11);
		this.add(trained1); this.add(trained2);
	}
	
	private void loadSkillPerPctInterface() {
		JLabel lbl12= new JLabel("Sw per %");
		lbl12.setHorizontalAlignment(SwingConstants.CENTER);
		lbl12.setBounds(310, 70, 60, 30);
		
		Sw_per_pct1= new JLabel("0");		
		Sw_per_pct1.setBounds(310, 90, 60, 30);
		Sw_per_pct1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl13= new JLabel("Sw per %");		
		lbl13.setBounds(310, 120, 60, 30);
		lbl13.setHorizontalAlignment(SwingConstants.CENTER);
		Sw_per_pct2= new JLabel("0");		
		Sw_per_pct2.setBounds(310, 140, 60, 30);
		Sw_per_pct2.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(Sw_per_pct1); this.add(Sw_per_pct2);
		this.add(lbl12);this.add(lbl13);
	}
	
	private void loadSkillPerLevelInterface() {
		JLabel lbl14= new JLabel("Sw per lvl:");
		lbl14.setHorizontalAlignment(SwingConstants.CENTER);
		lbl14.setBounds(370, 70, 60, 30);
		
		Sw_per_lvl1= new JLabel("0");		
		Sw_per_lvl1.setBounds(370, 90, 60, 30);
		Sw_per_lvl1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl15= new JLabel("Sw per lvl:");
		lbl15.setHorizontalAlignment(SwingConstants.CENTER);
		lbl15.setBounds(370, 120, 60, 30);
		
		Sw_per_lvl2= new JLabel("0");		
		Sw_per_lvl2.setBounds(370, 140, 60, 30);
		Sw_per_lvl2.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(Sw_per_lvl1);this.add(Sw_per_lvl2);
		this.add(lbl14);this.add(lbl15);
	}
	
	private void loadSaveInterface() {
		btnSave = new JButton("Start");
		btnSave.setBounds(354,38,76,30);
		btnSave.addActionListener(new SaveCharacter()); 
		btnSave.setVisible(false);
		this.add(btnSave);
		
		lblSaveWarning = new JLabel("Changes can only be made before the session begins.  Click 'End Session' to reset.");
		lblSaveWarning.setForeground(Color.WHITE);
		lblSaveWarning.setBackground(Color.RED);
		lblSaveWarning.setOpaque(true);
		lblSaveWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveWarning.setBounds(10, 176, 420, 14);
		lblSaveWarning.setVisible(false);
		this.add(lblSaveWarning);
	}
	
	private void createRadios() {
		JLabel lbl3= new JLabel("Unassigned: ");		
		lbl3.setBounds(10, 190, 120, 30);
		
		JLabel lbl4= new JLabel("Skill 1: ");		
		lbl4.setBounds(110, 190, 120, 30);
		
		JLabel lbl5= new JLabel("Skill 2: ");		
		lbl5.setBounds(210, 190, 120, 30);
		
		
		
		this.add(lbl3);this.add(lbl4);this.add(lbl5);
		
		SkillListener slistener = new SkillListener();
		
		for(int g=0; g<10; g++) {
			ButtonGroup bg = new ButtonGroup();
			JRadioButton f_u = new JRadioButton("F"+(g+1), true);
			f_u.setBounds(10, 220+g*40, 100, 30);
			f_u.setActionCommand(0+"");
			buttons[g][0] = f_u;
			
			JRadioButton f_1 = new JRadioButton("F"+(g+1));
			f_1.setBounds(110, 220+g*40, 100, 30);
			f_1.setActionCommand(1+"");
			buttons[g][1] = f_1;
			
			JRadioButton f_2 = new JRadioButton("F"+(g+1));
			f_2.setBounds(210, 220+g*40, 100, 30);
			f_2.setActionCommand(2+"");
			bg.add(f_u);bg.add(f_1);bg.add(f_2);
			buttons[g][2] = f_2;
			
			f_u.addActionListener(slistener);
			f_1.addActionListener(slistener);
			f_2.addActionListener(slistener);
			rbGroups[g]=bg;
			this.add(f_u);this.add(f_1);this.add(f_2);
		}
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton f_u = new JRadioButton("Fight(f)", true);
		f_u.setBounds(10, 220+10*40, 100, 30);
		f_u.setActionCommand(0+"");
		JRadioButton f_1 = new JRadioButton("Fight(f)");
		f_1.setBounds(110, 220+10*40, 100, 30);
		f_1.setActionCommand(1+"");
		JRadioButton f_2 = new JRadioButton("Fight(f)");
		f_2.setBounds(210, 220+10*40, 100, 30);
		f_2.setActionCommand(2+"");
		bg.add(f_u);bg.add(f_1);bg.add(f_2);
		f_u.addActionListener(slistener);
		f_1.addActionListener(slistener);
		f_2.addActionListener(slistener);
		rbGroups[10]=bg;
		buttons[10][0] = f_u;
		buttons[10][1] = f_1;
		buttons[10][2] = f_2;
		this.add(f_u);this.add(f_1);this.add(f_2);
		
		bg = new ButtonGroup();
		f_u = new JRadioButton("Jumpkick(j)", true);
		f_u.setBounds(10, 220+11*40, 100, 30);
		f_u.setActionCommand(0+"");
		f_1 = new JRadioButton("Jumpkick(j)");
		f_1.setBounds(110, 220+11*40, 100, 30);
		f_1.setActionCommand(1+"");
		f_2 = new JRadioButton("Jumpkick(j)");
		f_2.setBounds(210, 220+11*40, 100, 30);
		f_2.setActionCommand(2+"");
		bg.add(f_u);bg.add(f_1);bg.add(f_2);
		f_u.addActionListener(slistener);
		f_1.addActionListener(slistener);
		f_2.addActionListener(slistener);
		rbGroups[11]=bg;
		buttons[11][0] = f_u;
		buttons[11][1] = f_1;
		buttons[11][2] = f_2;
		this.add(f_u);this.add(f_1);this.add(f_2);
		
		bg = new ButtonGroup();
		f_u = new JRadioButton("Pole kick(p)", true);
		f_u.setBounds(10, 220+12*40, 100, 30);
		f_u.setActionCommand(0+"");
		f_1 = new JRadioButton("Pole kick(p)");
		f_1.setBounds(110, 220+12*40, 100, 30);
		f_1.setActionCommand(1+"");
		f_2 = new JRadioButton("Pole kick(p)");
		f_2.setBounds(210, 220+12*40, 100, 30);
		f_2.setActionCommand(2+"");
		bg.add(f_u);bg.add(f_1);bg.add(f_2);
		f_u.addActionListener(slistener);
		f_1.addActionListener(slistener);
		f_2.addActionListener(slistener);
		rbGroups[12]=bg;
		buttons[12][0] = f_u;
		buttons[12][1] = f_1;
		buttons[12][2] = f_2;
		this.add(f_u);this.add(f_1);this.add(f_2);	
		
		
	}
	
	/*LOAD A CHARACTER*/
	public void loadCharacter(String name) {
		CharacterManager cm = new CharacterManager();
		String[] data = cm.getCharacterData(name);
		if(data!=null) {
			String[] settings = cm.convertStringToArray(data[8]);
			experience_TF.setText(data[1]);
			//skill1_CB.setSelectedItem(data[2]);
			//level1_CB.setSelectedItem(data[3]);
			//trained1.setSelected(Boolean.parseBoolean(data[4]));
			//pctGained1.setText(data[9]);
			
			/*skill2_CB.setSelectedItem(data[5]);
			level2_CB.setSelectedItem(data[6]);
			trained2.setSelected(Boolean.parseBoolean(data[7]));
			pctGained2.setText(data[10]);*/
			
			for(int i=0; i<settings.length; i++) {
				int b = Integer.parseInt(settings[i]);
				buttons[i][b].setSelected(true);
				skillsTracked[i] = b;
			}
			
			loadData(data);
			
			trained1.setSelected(Boolean.parseBoolean(data[4]));
			trained2.setSelected(Boolean.parseBoolean(data[7]));
		}
		
	}

	private void loadData(String[] data) {
		characterName = data[0];
		experience = Long.parseLong(data[1]);
		skill1Name = data[2];
		int lvl1 = Arrays.asList(skillNames).indexOf(data[3]);
		if(lvl1==-1) lvl1 = Arrays.asList(skillNames_hand).indexOf(data[3]);
		float pct1 = Float.parseFloat(data[9])/100.0f;
		fullLevel_1 = lvl1+pct1;
		swpl_1 = calculateSwingsPerLvl(lvl1, Boolean.parseBoolean(data[4]));
		
		skill2Name = data[5];
		int lvl2 = Arrays.asList(skillNames).indexOf(data[6]);
		if(lvl2==-1) lvl2 = Arrays.asList(skillNames_hand).indexOf(data[6]);
		float pct2 = Float.parseFloat(data[10])/100.0f;
		fullLevel_2 = lvl2+pct2;
		swpl_2 = calculateSwingsPerLvl(lvl2, Boolean.parseBoolean(data[7]));
		/*System.out.println(Boolean.parseBoolean(data[4]));
		System.out.println(Boolean.parseBoolean(data[7]));
		
		
		System.out.println("Startpanel main variables: char="+characterName+
				", exp="+experience+
				", skill1="+skill1Name+
				", lvl1="+fullLevel_1+
				", swpll1="+swpl_1+
				", skill2="+skill2Name+
				", lvl2="+fullLevel_2+
				", swpll2="+swpl_2);*/
	}
	
	
	
	
	public void saveSettings() {
		btnSave.doClick();
	}
	public int getSkillTracked(int index) {
		return skillsTracked[index];
	}
	
	
	
	private int getLevel(int skillNum) {	
 	   if(skillNum==1) return (int)fullLevel_1;
 	   return (int)fullLevel_2;
 	   
	}
	
	private int calculateSwingsPerLvl(int level, boolean trained) {
		int swings = (int) ((100*Math.pow(2, level))/(level+2));
		//System.out.println(Math.pow(2, level));
		//int swings = (int) ((50*(Math.pow(2, level)))/(level+2));
		if(trained) swings = swings/2;
		return (int)swings;
	}
	
	
	
	
	
	public ButtonGroup[] getButtonGroups() {
		return rbGroups;
	}
	
	public float getSwingsPerPct(int skillNum) {		
		String spp = Sw_per_pct2.getText();
		if(skillNum==1) spp = Sw_per_pct1.getText();
		spp = spp.replaceAll(",", "");
		if(skillNum==1) return Float.parseFloat(spp);
		return Float.parseFloat(spp);
	}
	
	public String getSwingsNeeded(int skillNum) {
		if(skillNum==1) return Sw_per_pct1.getText();
		return Sw_per_pct2.getText();
	}
	
	public int getCharacterIndex() {
		return charName.getSelectedIndex();
	}
	
	

	public JComboBox getCharName() {
		return charName;
	}


	public JTextField getExperience() {
		return experience_TF;
	}
	
	public void setExperience(String endExp) {
		experience_TF.setText(endExp);		
	}
	
	public String getSkill(int skillNum) {
		if(skillNum==1) return (String)skill1_CB.getSelectedItem();
		return (String)skill2_CB.getSelectedItem();
	}
	
	public float getLevel1() {
		return fullLevel_1;
	}
	
	public float getLevel2() {
		return fullLevel_2;
	}

	public void setLevel1(float gainedAmt) {
		fullLevel_1+=gainedAmt;
		updateInterface();
	}
	
	
	public void setLevel2(float gainedAmt) {
		fullLevel_2+=gainedAmt;
		updateInterface();
	}
	
	private void updateInterface() {
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
			
		float pct = (fullLevel_1%1)*10000.0f;
		int wholeLevel = (int)fullLevel_1;
		pct = ((int)pct)/100.0f;
		
		skill1_CB.setSelectedItem(skill1Name);
		level1_CB.setSelectedIndex(wholeLevel);
		pctGained1.setText(pct+"");
		Sw_per_pct1.setText(fmt.format(swpl_1/100.0f));	
    	Sw_per_lvl1.setText(fmt.format(swpl_1));
		
		pct = (fullLevel_2%1)*10000.0f;
		wholeLevel = (int)fullLevel_2;
		pct = ((int)pct)/100.0f;
		skill2_CB.setSelectedItem(skill2Name);
		level2_CB.setSelectedIndex(wholeLevel);
		pctGained2.setText(pct+"");
		Sw_per_pct2.setText(fmt.format(swpl_2/100.0f));	
    	Sw_per_lvl2.setText(fmt.format(swpl_2));
	}
	
	
	
	public void pressSaveButton() {
		btnSave.doClick();
	}

	public void toggleTracking(boolean startTracking) {
		if(startTracking) {
			lblSaveWarning.setVisible(true);
			
			trained1.setEnabled(false);
			trained2.setEnabled(false);
			
			level1_CB.setEnabled(false);
			level2_CB.setEnabled(false);

			skill1_CB.setEnabled(false);
			skill2_CB.setEnabled(false);
			experience_TF.setEnabled(false);
			//btnSave;
			charName.setEnabled(false);
			pctGained1.setEnabled(false);
			pctGained2.setEnabled(false);
			btnSave.setVisible(false);
			
		} else {
			lblSaveWarning.setVisible(false);
			trained1.setEnabled(true);
			trained2.setEnabled(true);
			
			level1_CB.setEnabled(true);
			level2_CB.setEnabled(true);

			skill1_CB.setEnabled(true);
			skill2_CB.setEnabled(true);
			experience_TF.setEnabled(true);
			//btnSave;
			charName.setEnabled(true);
			pctGained1.setEnabled(true);
			pctGained2.setEnabled(true);
			btnSave.setVisible(true);
		}
		
	}
	
	public void setTPanel(TrackingPanel tp) {
		tpanel = tp;
	}
	
	
	
	/*ACTION LISTENERS*/
	class NameSelected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   String name = (String)event.getItem();
	    	   if(!name.equals(" ")) {
	    		   loadCharacter(name);
	    		   btnSave.setVisible(true);
	    	   }
	       }
	    }       
	}
	
	class Skill_1_Selected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   String skill = (String)event.getItem();
	    	   if(skill.equals("Hand")){
	    		   DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>( skillNames_hand );
	    		   level1_CB.setModel( model );
	    	   }else{
	    		   DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>( skillNames );
	    		   level1_CB.setModel( model );
	    	   }
	       }
	    }       
	}	
	
	class Skill_2_Selected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   String skill = (String)event.getItem();
	    	   if(skill.equals("Hand")){
	    		   DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>( skillNames_hand );
	    		   level2_CB.setModel( model );
	    	   }else{
	    		   DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>( skillNames );
	    		   level2_CB.setModel( model );
	    	   }
	       }
	    }       
	}
	
	class Level_1_Selected implements ItemListener{
		
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   int level = getLevel(1);		    	   
	    	   swpl_1 = calculateSwingsPerLvl(level, trained1.isSelected());
	    	   updateInterface();
	       }
	    }       
	}
	
	class Level_2_Selected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   int level = getLevel(2);
	    	   swpl_2 = calculateSwingsPerLvl(level, trained2.isSelected());
	    	   updateInterface();
	       }
	    }       
	}
	
	class Training_1_Selected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
		    int level = getLevel(1);
		    swpl_1 = calculateSwingsPerLvl(level, trained1.isSelected());
	    	updateInterface();	    	
	    }       
	}
	
	class Training_2_Selected implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       int level = getLevel(2);
	       swpl_2 = calculateSwingsPerLvl(level, trained2.isSelected());
	       updateInterface();
	    }       
	}
	
	class SkillListener implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			for(int g=0; g<rbGroups.length; g++) {
				skillsTracked[g] = Integer.parseInt(rbGroups[g].getSelection().getActionCommand());	
			}
		}
	}
	
	class SaveCharacter implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			String character = (String)charName.getSelectedItem();
            String exp = experience_TF.getText();
            String skill1 = (String)skill1_CB.getSelectedItem();
            String level1 = (String)level1_CB.getSelectedItem();
            String pct1 = ((fullLevel_1%1)*100)+"";
            
            String t1 = Boolean.toString(trained1.isSelected());
            String skill2 = (String)skill2_CB.getSelectedItem();
            String level2 = (String)level2_CB.getSelectedItem();
            String pct2 = ((fullLevel_2%1)*100)+"";
            String t2 = Boolean.toString(trained2.isSelected());
            String settings = "";
            
            
            for(int i=0; i<skillsTracked.length; i++) {
            	if(i<skillsTracked.length-1) {
            		settings+=skillsTracked[i]+",";
            	}else {
            		settings+=skillsTracked[i];
            	}
            }
            CharacterManager cm = new CharacterManager();
            cm.save(character, exp, skill1, level1, t1, skill2, level2, t2, settings, pct1, pct2);
            float nPct1 =Float.parseFloat(pct1)%1;
            float nPct2 =Float.parseFloat(pct2)%1;
            /*if(nPct1==0) nPct1 = 1;
            if(nPct2==0) nPct2 = 1;
            */
            float swNeeded1 = Float.parseFloat(Sw_per_pct1.getText().replaceAll(",", ""));
            float swNeeded2 = Float.parseFloat(Sw_per_pct2.getText().replaceAll(",", ""));
            
            System.out.println("s1: "+swNeeded1+", "+nPct1+", s2: "+swNeeded2+", "+nPct2);
            tpanel.setSwingsNeeded(1, swNeeded1-nPct1*swNeeded1);
            tpanel.setSwingsNeeded(2, swNeeded2-nPct2*swNeeded2);
            tpanel.startPlayTimer();
            btnSave.setVisible(false);
		}
	}
}
