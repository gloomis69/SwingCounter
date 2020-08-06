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
	private JComboBox<String> level1_CB;
	private JComboBox<String> level2_CB;
	private JComboBox<String> skill1_CB;
	private JComboBox<String> skill2_CB;
	private JTextField experience;
	private JButton btnSave;
	private JComboBox<String> charName;
	
	StartPanel(){
		
		
		//JFrame frame = (JFrame) this.getParent();
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
		
		Border spacer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border linedBorder = BorderFactory.createLineBorder(Color.black); 
		JLabel lbl1= new JLabel("Character Name: ");		
		lbl1.setBounds(10, 8, 150, 30);
		CharacterManager cm = new CharacterManager();
		String[] cnames = cm.getCharacters();
		
		if(cnames!=null) {
			charName = new JComboBox<String>(cnames);
		}else {
			charName = new JComboBox<String>();
		}
		getCharName().setBounds(10,38, 150,30); 
		getCharName().setFont(new Font("Sans-Serif", Font.BOLD, 14));
		class NameSelected implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		    	   String name = (String)event.getItem();
		    	   if(!name.equals(" ")) {
		    		   loadCharacter(name);
		    	   }
		       }
		    }       
		}
		getCharName().addItemListener(new NameSelected());
		
		
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
		lbl2.setBounds(260, 8, 150, 30);
		experience = new JTextField();
		getExperience().setBounds(260,38, 150,30);
		getExperience().setHorizontalAlignment(SwingConstants.RIGHT);
		getExperience().setBorder(BorderFactory.createCompoundBorder(linedBorder, spacer));
		
		String[] skillsList = {"Bow", "Dagger", "Flail", "Halberd", "Mace", "Rapier", "Shuriken", "Staff", "Sword", "Twohanded", "Threestaff", "Hand"};
		
		JLabel lbl6= new JLabel("Skill 1: ");		
		lbl6.setBounds(10, 70, 120, 30);		
		skill1_CB = new JComboBox<String>(skillsList);
		skill1_CB.setBounds(10, 95, 120, 20);
		
		
		JLabel lbl7= new JLabel("Skill 2: ");		
		lbl7.setBounds(10, 120, 120, 30);
		skill2_CB = new JComboBox<String>(skillsList);
		skill2_CB.setBounds(10, 145, 120, 20);
				
		
		
		JLabel lbl8= new JLabel("Level: ");		
		lbl8.setBounds(140, 70, 120, 30);
		level1_CB = new JComboBox<String>(skillNames);
		level1_CB.setBounds(140, 95, 100, 20);
		
		JLabel lbl9= new JLabel("Level: ");		
		lbl9.setBounds(140, 120, 120, 30);
		level2_CB = new JComboBox<String>(skillNames);
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
		    	   int swings = calculateSwingsPerPct(level, trained1.isSelected());
		    	   Sw_per_pct1.setText(fmt.format(swings)+"");	
		    	   Sw_per_lvl1.setText(fmt.format(swings*100)+"");
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
		    	   int swings = calculateSwingsPerPct(level, trained2.isSelected());
		    	   Sw_per_pct2.setText(fmt.format(swings)+"");
		    	   Sw_per_lvl2.setText(fmt.format(swings*100)+"");
		       }
		    }       
		}
		level2_CB.addItemListener(new LevelSelected2());
		
		JLabel lbl10= new JLabel("Trained?: ");		
		lbl10.setBounds(210, 70, 120, 30);
		trained1 = new JCheckBox();
		trained1.setBounds(250, 90, 20, 30);
		class TrainingSelected1 implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		    	String skill = (String)level1_CB.getSelectedItem();
			    int level = getLevel(skill, 1);
		    	int swings = calculateSwingsPerPct(level, trained1.isSelected());
		    	Sw_per_pct1.setText(fmt.format(swings)+"");	
		    	Sw_per_lvl1.setText(fmt.format(swings*100)+"");
		    }       
		}
		trained1.addItemListener(new TrainingSelected1());
		
		
		JLabel lbl11= new JLabel("Trained?: ");		
		lbl11.setBounds(210, 120, 120, 30);
		trained2 = new JCheckBox();
		trained2.setBounds(250, 140, 20, 30);
		class TrainingSelected2 implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       String skill = (String)level2_CB.getSelectedItem();
		       int level = getLevel(skill, 2);
		       int swings = calculateSwingsPerPct(level, trained2.isSelected());
		       Sw_per_pct2.setText(fmt.format(swings)+"");	
		       Sw_per_lvl2.setText(fmt.format(swings*100)+"");
		    }       
		}
		trained2.addItemListener(new TrainingSelected2());
		
		JLabel lbl12= new JLabel("Sw per %");
		lbl12.setHorizontalAlignment(SwingConstants.CENTER);
		lbl12.setBounds(280, 70, 60, 30);
		
		Sw_per_pct1= new JLabel("0");		
		Sw_per_pct1.setBounds(280, 90, 50, 30);
		Sw_per_pct1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl13= new JLabel("Sw per %");		
		lbl13.setBounds(280, 120, 60, 30);
		lbl13.setHorizontalAlignment(SwingConstants.CENTER);
		Sw_per_pct2= new JLabel("0");		
		Sw_per_pct2.setBounds(280, 140, 50, 30);
		Sw_per_pct2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl14= new JLabel("Sw per lvl:");
		lbl14.setHorizontalAlignment(SwingConstants.CENTER);
		lbl14.setBounds(350, 70, 80, 30);
		
		Sw_per_lvl1= new JLabel("0");		
		Sw_per_lvl1.setBounds(350, 90, 80, 30);
		Sw_per_lvl1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl15= new JLabel("Sw per lvl:");
		lbl15.setHorizontalAlignment(SwingConstants.CENTER);
		lbl15.setBounds(350, 120, 80, 30);
		
		Sw_per_lvl2= new JLabel("0");		
		Sw_per_lvl2.setBounds(350, 140, 80, 30);
		Sw_per_lvl2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl3= new JLabel("Unassigned: ");		
		lbl3.setBounds(10, 190, 120, 30);
		
		JLabel lbl4= new JLabel("Skill 1: ");		
		lbl4.setBounds(110, 190, 120, 30);
		
		JLabel lbl5= new JLabel("Skill 2: ");		
		lbl5.setBounds(210, 190, 120, 30);
		
		this.add(getCharName());this.add(getExperience());
		
		this.add(lbl1);this.add(lbl2);this.add(lbl3);this.add(lbl4);this.add(lbl5);this.add(lbl6);this.add(lbl7);
		this.add(lbl8);this.add(lbl9);this.add(lbl10);this.add(lbl11);this.add(lbl12);this.add(lbl13);this.add(lbl14);this.add(lbl15);
		
		this.add(skill1_CB);this.add(skill2_CB);this.add(level1_CB);this.add(level2_CB);
		this.add(trained1); this.add(trained2);
		
		this.add(Sw_per_pct1); this.add(Sw_per_pct2);
		this.add(Sw_per_lvl1);this.add(Sw_per_lvl2);
		createRadios();
		
		btnSave = new JButton("Save");
		btnSave.setBounds(360,700,70,30);
		btnSave.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  	            
	            String character = (String)getCharName().getSelectedItem();
	            String exp = getExperience().getText();
	            String skill1 = (String)skill1_CB.getSelectedItem();
	            String level1 = (String)level1_CB.getSelectedItem();
	            String t1 = Boolean.toString(trained1.isSelected());
	            String skill2 = (String)skill2_CB.getSelectedItem();
	            String level2 = (String)level2_CB.getSelectedItem();
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
	            cm.save(character, exp, skill1, level1, t1, skill2, level2, t2, settings);
	        }  
	    }); 
		this.add(btnSave);
		
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setVisible(true);
		
		
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
			
			skill2_CB.setSelectedItem(data[5]);
			level2_CB.setSelectedItem(data[6]);
			trained2.setSelected(Boolean.parseBoolean(data[7]));
			
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
	
	private int calculateSwingsPerPct(int level, boolean trained) {
		int swings = (int) ((100*Math.pow(2, level))/(level+2)/100);
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
		f_2.setActionCommand(1+"");
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
	
	public int getSwingsPerPct(int skillNum) {		
		String spp = Sw_per_pct2.getText();
		if(skillNum==1) spp = Sw_per_pct1.getText();
		spp = spp.replaceAll(",", "");
		if(skillNum==1) return Integer.parseInt(spp);
		return Integer.parseInt(spp);
	}
	
	public String getSwingsNeeded(int skillNum) {
		if(skillNum==1) return Sw_per_pct1.getText();
		return Sw_per_pct2.getText();
	}
	
	public int getCharacterIndex() {
		return getCharName().getSelectedIndex();
	}
	
	

	public JComboBox<String> getCharName() {
		return charName;
	}


	public JTextField getExperience() {
		return experience;
	}
	
	public void setExperience(String endExp) {
		experience.setText(endExp);
		btnSave.doClick();
	}
	
	public String getSkill(int skillNum) {
		if(skillNum==1) return (String)skill1_CB.getSelectedItem();
		return (String)skill2_CB.getSelectedItem();
	}
}
