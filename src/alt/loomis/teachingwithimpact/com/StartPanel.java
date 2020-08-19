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
	private JTextField experience;
	private JButton btnSave;
	private JComboBox charName;
	private JTextField pctGained1;
	private JTextField pctGained2;
	private JLabel lblSaveWarning;
	private TrackingPanel tpanel;
	
	StartPanel(){
		UIManager.put("ComboBox.disabledForeground", Color.BLACK);
		
		
		//JFrame frame = (JFrame) this.getParent();
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
		
		Border spacer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border linedBorder = BorderFactory.createLineBorder(Color.black); 
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
		charName.addItemListener(new NameSelected());
		
		
		/*JButton addCharacter = new JButton("+ New");
		addCharacter.setBounds(10, 10, 70, 25);  
		this.add(addCharacter);
	
		addCharacter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				String name=JOptionPane.showInputDialog(frame,"Enter Name"); 
				if(name!=null) {
					getCharName().addItem(name);
					CharacterManager cm = new CharacterManager();
					cm.saveNew(name);
					getCharName().setSelectedItem(name);
				}
	        }  
	    });  
		
		JButton deleteCharacter = new JButton("Delete");
		deleteCharacter.setBounds(10, 40, 70, 25);  
		this.add(deleteCharacter);
	
		deleteCharacter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				if(getCharName().getSelectedIndex()!=0) {
					int a=JOptionPane.showConfirmDialog(frame,"Are you sure you want to permanently delete this character?"); 
					if(a==JOptionPane.YES_OPTION){ 
						String name = (String)getCharName().getSelectedItem();
						CharacterManager cm = new CharacterManager();
						cm.delete(name);
						getCharName().removeItem(name);
						getCharName().setSelectedIndex(0);
					}
				}
	        }  
	    }); */
		
		JLabel lbl2= new JLabel("Starting Experience: ");		
		lbl2.setBounds(168, 8, 150, 30);
		experience = new JTextField();
		experience.setDisabledTextColor(Color.BLACK);
		getExperience().setBounds(168,38, 150,30);
		getExperience().setHorizontalAlignment(SwingConstants.RIGHT);
		getExperience().setBorder(BorderFactory.createCompoundBorder(linedBorder, spacer));
		
		String[] skillsList = {"Bow", "Dagger", "Flail", "Halberd", "Mace", "Rapier", "Shuriken", "Staff", "Sword", "Twohanded", "Threestaff", "Hand"};
		
		JLabel lbl6= new JLabel("Skill 1: ");		
		lbl6.setBounds(10, 70, 40, 30);		
		skill1_CB = new JComboBox(skillsList);
		skill1_CB.setBounds(10, 95, 120, 20);
		
		
		JLabel lbl7= new JLabel("Skill 2: ");		
		lbl7.setBounds(10, 120, 40, 30);
		skill2_CB = new JComboBox(skillsList);
		skill2_CB.setBounds(10, 145, 120, 20);
				
		
		
		JLabel lbl8= new JLabel("Level: ");		
		lbl8.setBounds(140, 70, 40, 30);
		level1_CB = new JComboBox(skillNames);		
		level1_CB.setBounds(140, 95, 100, 20);
		
		JLabel lbl9= new JLabel("Level: ");		
		lbl9.setBounds(140, 120, 40, 30);
		level2_CB = new JComboBox(skillNames);
		level2_CB.setBounds(140, 145, 100, 20);
		
		class SkillSelected implements ItemListener{
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
		skill1_CB.addItemListener(new SkillSelected());
		
		class SkillSelected2 implements ItemListener{
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
		skill2_CB.addItemListener(new SkillSelected2());
		
		class LevelSelected implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   String skill = (String)event.getItem();
		    	   int level = getLevel(skill, 1);		    	   
		    	   int swings = calculateSwingsPerLvl(level, trained1.isSelected());
		    	   Sw_per_pct1.setText(fmt.format(swings/100.0f)+"");	
		    	   Sw_per_lvl1.setText(fmt.format(swings)+"");
		       }
		    }       
		}
		level1_CB.addItemListener(new LevelSelected());
		
		class LevelSelected2 implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   String skill = (String)event.getItem();
		    	   int level = getLevel(skill, 2);
		    	   int swings = calculateSwingsPerLvl(level, trained2.isSelected());
		    	   Sw_per_pct2.setText(fmt.format(swings/100.0f)+"");
		    	   Sw_per_lvl2.setText(fmt.format(swings)+"");
		       }
		    }       
		}
		level2_CB.addItemListener(new LevelSelected2());
		
		JLabel lbl10= new JLabel("Trained?");		
		lbl10.setToolTipText("Is this skill trained?");
		lbl10.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl10.setBounds(280, 70, 30, 30);
		trained1 = new JCheckBox();
		trained1.setToolTipText("Is this skill trained?");
		trained1.setBounds(285, 90, 20, 30);
		class TrainingSelected1 implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		    	String skill = (String)level1_CB.getSelectedItem();
			    int level = getLevel(skill, 1);
		    	int swings = calculateSwingsPerLvl(level, trained1.isSelected());
		    	Sw_per_pct1.setText(fmt.format(swings/100.0f)+"");	
		    	Sw_per_lvl1.setText(fmt.format(swings)+"");
		    }       
		}
		trained1.addItemListener(new TrainingSelected1());
		
		
		JLabel lbl11= new JLabel("Trained?");		
		lbl11.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl11.setToolTipText("Is this skill trained?");
		lbl11.setHorizontalAlignment(SwingConstants.CENTER);
		lbl11.setBounds(280, 120, 30, 30);
		trained2 = new JCheckBox();
		trained2.setToolTipText("Is this skill trained?");
		trained2.setBounds(285, 140, 20, 30);
		class TrainingSelected2 implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       String skill = (String)level2_CB.getSelectedItem();
		       int level = getLevel(skill, 2);
		       int swings = calculateSwingsPerLvl(level, trained2.isSelected());
		       Sw_per_pct2.setText(fmt.format(swings/100.0f)+"");	
		       Sw_per_lvl2.setText(fmt.format(swings)+"");
		    }       
		}
		trained2.addItemListener(new TrainingSelected2());
		
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
		
		JLabel lbl3= new JLabel("Unassigned: ");		
		lbl3.setBounds(10, 190, 120, 30);
		
		JLabel lbl4= new JLabel("Skill 1: ");		
		lbl4.setBounds(110, 190, 120, 30);
		
		JLabel lbl5= new JLabel("Skill 2: ");		
		lbl5.setBounds(210, 190, 120, 30);
		
		this.add(charName);this.add(getExperience());
		
		this.add(lbl1);this.add(lbl2);this.add(lbl3);this.add(lbl4);this.add(lbl5);this.add(lbl6);this.add(lbl7);
		this.add(lbl8);this.add(lbl9);this.add(lbl10);this.add(lbl11);this.add(lbl12);this.add(lbl13);this.add(lbl14);this.add(lbl15);
		
		this.add(skill1_CB);this.add(skill2_CB);this.add(level1_CB);this.add(level2_CB);
		this.add(trained1); this.add(trained2);
		
		this.add(Sw_per_pct1); this.add(Sw_per_pct2);
		this.add(Sw_per_lvl1);this.add(Sw_per_lvl2);
		createRadios();
		
		
		
		this.setBackground(Color.white);
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(245, 79, 35, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(245, 128, 35, 14);
		add(lblNewLabel_1);
		
		pctGained1 = new JTextField();
		pctGained1.setDisabledTextColor(Color.BLACK);
		pctGained1.setText("0");
		pctGained1.setHorizontalAlignment(SwingConstants.CENTER);
		pctGained1.setBounds(244, 95, 38, 20);
		add(pctGained1);
		pctGained1.setColumns(10);
		
		
		
		pctGained2 = new JTextField();
		pctGained2.setDisabledTextColor(Color.BLACK);
		pctGained2.setText("0");
		pctGained2.setHorizontalAlignment(SwingConstants.CENTER);
		pctGained2.setBounds(244, 145, 38, 20);
		add(pctGained2);
		pctGained2.setColumns(10);
		this.setVisible(true);
		
		btnSave = new JButton("Start");
		btnSave.setBounds(354,38,76,30);
		btnSave.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	            
	            String character = (String)charName.getSelectedItem();
	            String exp = getExperience().getText();
	            String skill1 = (String)skill1_CB.getSelectedItem();
	            String level1 = (String)level1_CB.getSelectedItem();
	            String pct1 = pctGained1.getText();
	            
	            String t1 = Boolean.toString(trained1.isSelected());
	            String skill2 = (String)skill2_CB.getSelectedItem();
	            String level2 = (String)level2_CB.getSelectedItem();
	            String pct2 = pctGained2.getText();
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
	            if(nPct1==0) nPct1 = 1;
	            if(nPct2==0) nPct2 = 1;
	            float swNeeded1 = Float.parseFloat(Sw_per_pct1.getText().replaceAll(",", ""));
	            float swNeeded2 = Float.parseFloat(Sw_per_pct2.getText().replaceAll(",", ""));
	            tpanel.setSwingsNeeded(1, swNeeded1-nPct1*swNeeded1);
	            tpanel.setSwingsNeeded(2, swNeeded2-nPct2*swNeeded2);
	            tpanel.startPlayTimer();
	            btnSave.setVisible(false);
	        }  
	    }); 
		btnSave.setVisible(false);
		this.add(btnSave);
		
		lblSaveWarning = new JLabel("Changes can only be made before the session begins.  Click 'End Session' to reset.");
		lblSaveWarning.setForeground(Color.WHITE);
		lblSaveWarning.setBackground(Color.RED);
		lblSaveWarning.setOpaque(true);
		lblSaveWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveWarning.setBounds(10, 176, 420, 14);
		lblSaveWarning.setVisible(false);
		add(lblSaveWarning);
	}
	
	public void saveSettings() {
		btnSave.doClick();
	}
	public int getSkillTracked(int index) {
		return skillsTracked[index];
	}
	
	public void loadCharacter(String name) {
		CharacterManager cm = new CharacterManager();
		String[] data = cm.getCharacterData(name);
		if(data!=null) {
			String[] settings = cm.convertStringToArray(data[8]);
			getExperience().setText(data[1]);
			skill1_CB.setSelectedItem(data[2]);
			level1_CB.setSelectedItem(data[3]);
			trained1.setSelected(Boolean.parseBoolean(data[4]));
			pctGained1.setText(data[9]);
			
			skill2_CB.setSelectedItem(data[5]);
			level2_CB.setSelectedItem(data[6]);
			trained2.setSelected(Boolean.parseBoolean(data[7]));
			pctGained2.setText(data[10]);
			
			for(int i=0; i<settings.length; i++) {
				int b = Integer.parseInt(settings[i]);
				buttons[i][b].setSelected(true);
				skillsTracked[i] = b;
			}
		}
		
	}

	
	
	private int getLevel(String skill, int skillNum) {	
 	   int level = 0;
 	   if(skillNum==1) {
 		  if(skill1_CB.getSelectedItem().equals("Hand")){
 	 		   level = Arrays.asList(skillNames_hand).indexOf(skill);
 	 	   }else{
 	 		   level = Arrays.asList(skillNames).indexOf(skill);
 	 	   }
 	   }else{
 		  if(skill2_CB.getSelectedItem().equals("Hand")){
 	 		   level = Arrays.asList(skillNames_hand).indexOf(skill);
 	 	   }else{
 	 		   level = Arrays.asList(skillNames).indexOf(skill);
 	 	   }
 	   }
 	   return level;
 	   
	}
	
	private int calculateSwingsPerLvl(int level, boolean trained) {
		int swings = (int) ((100*Math.pow(2, level))/(level+2));
		System.out.println(Math.pow(2, level));
		//int swings = (int) ((50*(Math.pow(2, level)))/(level+2));
		if(trained) swings = swings/2;
		return (int)swings;
	}
	
	/*private int calculateSwingsPerLevel(int level, boolean trained) {
		int swings = (int) ((100*Math.pow(2, level))/(level+2));
		if(trained) swings = swings/2;
		return (int)swings;
	}*/
	
	private void createRadios() {
		class SkillListener implements ActionListener{
			public void actionPerformed(ActionEvent ev) {
				for(int g=0; g<rbGroups.length; g++) {
					skillsTracked[g] = Integer.parseInt(rbGroups[g].getSelection().getActionCommand());	
				}
			}
		}
		
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
		return experience;
	}
	
	public void setExperience(String endExp) {
		experience.setText(endExp);		
	}
	
	public String getSkill(int skillNum) {
		if(skillNum==1) return (String)skill1_CB.getSelectedItem();
		return (String)skill2_CB.getSelectedItem();
	}
	
	public float getLevel1() {
		int level = level1_CB.getSelectedIndex();
		float pct = Float.parseFloat(pctGained1.getText())*0.01f;
		return level+pct;
	}
	
	public float getLevel2() {
		int level = level2_CB.getSelectedIndex();
		float pct = Float.parseFloat(pctGained2.getText())*0.01f;
		return level+pct;
	}

	public void setLevel1(float getLvl) {
		float pct = (getLvl%1)*10000.0f;
		int wholeLevel = (int)getLvl;
		pct = ((int)pct)/100.0f;
		level1_CB.setSelectedIndex(wholeLevel);
		pctGained1.setText(pct+"");
	}
	
	public void setLevel2(float getLvl) {
		float pct = (getLvl%1)*10000.0f;
		int wholeLevel = (int)getLvl;
		pct = ((int)pct)/100.0f;
		level2_CB.setSelectedIndex(wholeLevel);
		pctGained2.setText(pct+"");
	}
	
	public void save() {
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
			experience.setEnabled(false);
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
			experience.setEnabled(true);
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
	
}
