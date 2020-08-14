package alt.loomis.teachingwithimpact.com;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class SwingCounter {
	private static SwingKeyCapture kc;
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Swing Counter");
		
		StartPanel startpanel = new StartPanel();
		startpanel.setBounds(5,5,440,750);	
		
		TrackingPanel panel = new TrackingPanel(startpanel);
		panel.setBounds(450,5,180,750);
		
		
		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		
		int height = (int)(screenSize.height * 0.9);
		if(height>820) height = 820;
		int width = 670; //screenSize.width * 1/ 3;
		
		// set the jframe height and width
		frame.setPreferredSize(new Dimension(width, height));
		
		frame.setSize(width, height);
		//frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//frame.getContentPane().setBackground(Color.black);	    
		
		frame.addWindowFocusListener(new WindowAdapter() {
		    public void windowGainedFocus(WindowEvent e) {
		        startpanel.getCharName().requestFocusInWindow();
		    }
		});
		JPanel container = new JPanel();
		JScrollPane pane = new JScrollPane(container);

        frame.setContentPane(pane);
        
        container.setPreferredSize(new Dimension(630, 750));
        container.setLayout(null);
        container.add(startpanel);
        container.add(panel);
        
        new Thread(new Runnable() {
            public void run() {
              

                // Runs inside of the Swing UI thread
                SwingUtilities.invokeLater(new Runnable() {
                  public void run() {
                	  JMenuBar mb=new JMenuBar();  
                      JMenu fileMenu=new JMenu("File");  
                      JMenuItem newChar=new JMenuItem("New Character"); 
                      newChar.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              				String name=JOptionPane.showInputDialog(frame,"Enter Name"); 
              				if(name!=null) {
              					startpanel.getCharName().addItem(name);
              					CharacterManager cm = new CharacterManager();
              					cm.saveNew(name);
              					startpanel.getCharName().setSelectedItem(name);
              				}
              	        }  
              	    }); 
                      
                      JMenuItem dltChar=new JMenuItem("Delete Character"); 
                      dltChar.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              				if(startpanel.getCharName().getSelectedIndex()!=0) {
              					int a=JOptionPane.showConfirmDialog(frame,"Are you sure you want to permanently delete this character?"); 
              					if(a==JOptionPane.YES_OPTION){ 
              						String name = (String)startpanel.getCharName().getSelectedItem();
              						CharacterManager cm = new CharacterManager();
              						cm.delete(name);
              						startpanel.getCharName().removeItem(name);
              						startpanel.getCharName().setSelectedIndex(0);
              					}
              				}
              	        }  
              	    }); 
                      JMenuItem pause=new JMenuItem("Pause"); 
                      pause.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              	            panel.pauseTracker();
              	        }  
              	    }); 
                      JMenuItem save=new JMenuItem("Save");  
                      save.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              	            startpanel.saveSettings();
              	        }  
              	    });
                      JMenuItem quit=new JMenuItem("Quit");  
                      quit.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              				if(startpanel.getCharacterIndex()!=0) {
              			    	String experience = JOptionPane.showInputDialog(frame, "Enter you characters experience");
              			    	
              			    	if(experience!=null && !experience.isEmpty()) {
              			    		panel.setExperience();
              			    	}
              		    	}
              		    	kc.setQuit(true);
              	        }  
              	    });
                      
                      JMenu historyMenu=new JMenu("History");    
                      JMenuItem history=new JMenuItem("View History"); 
                      history.addActionListener(new ActionListener(){  
              			public void actionPerformed(ActionEvent e){  	            
              				@SuppressWarnings("unused")
              				SessionData sd = new SessionData();
              	        }  
              	    });
                      
                      fileMenu.add(newChar); fileMenu.add(pause); fileMenu.add(save);   fileMenu.add(quit); 
                      mb.add(fileMenu); 
                      
                      historyMenu.add(history);
                      mb.add(historyMenu);
                      
                      frame.setJMenuBar(mb);
                      frame.validate();
                      frame.repaint();
                  }
                });

                try {
                  java.lang.Thread.sleep(100);
                }
                catch(Exception e) { }
              
            }
          }).start();
        
        
        
		kc = new SwingKeyCapture(frame, panel.getLabels(), startpanel, panel);		
		kc.paused = false;
		
		
	}

	
	
}
