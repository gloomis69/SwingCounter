package alt.loomis.teachingwithimpact.com;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

public class SwingKeyCapture {
	private static volatile boolean quit;
    private static HHOOK hhk;
    private static LowLevelKeyboardProc keyboardHook;
    private static int skillNumBeingTracked = 0; //0=no skill tracked, 1=skill 1, 2=skill 2: remember -1 for index of swingCount
    private static int[] swingCount = {0, 0};
    public boolean paused = false;
    
	SwingKeyCapture(JFrame frame, JLabel[][] labels, StartPanel spanel, TrackingPanel tpanel) {		 
		tpanel.loadKeyCapture(this); 
		final User32 lib = User32.INSTANCE;
	     HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
	     keyboardHook = new LowLevelKeyboardProc() {
	    boolean playStarted = false;
	    
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
                if (nCode >= 0) {
                    switch(wParam.intValue()) {
                        case WinUser.WM_KEYUP:
                        //case WinUser.WM_KEYDOWN:
                        //case WinUser.WM_SYSKEYUP:
                        //case WinUser.WM_SYSKEYDOWN:
                            //System.err.println("in callback, key=" + info.vkCode);
                            /*if (info.vkCode == 81) {
                            	quit = true;
                            }*/
                            if(!paused) {
	                        	if(info.vkCode == 192 || info.vkCode == 160) {
	                        		skillNumBeingTracked=0;
	                        	}
	                        	
	                        	if(info.vkCode == 13) {
	                        		NumberFormat fmt = NumberFormat.getInstance();
	                        		fmt.setGroupingUsed(true);
	                            	if(skillNumBeingTracked==1) {
	                            		if(!playStarted) {
	                            			playStarted = true;
	                            			tpanel.startPlayTimer(); 
	                            		}
	                            		swingCount[0]++; 
	                                	labels[0][0].setText(swingCount[0]+"");
	                                	float sremaining = Float.parseFloat(labels[0][1].getText())-1;
	                                	if(sremaining<=0) sremaining = Float.parseFloat(spanel.getSwingsNeeded(1));
	                                	labels[0][1].setText(fmt.format(sremaining)+"");
	                                	
	                            	}else if(skillNumBeingTracked==2) {
	                            		if(!playStarted) {
	                            			playStarted = true;
	                            			tpanel.startPlayTimer(); 
	                            		}
	                            		swingCount[1]++;
	                            		//System.out.println("Swing Total: "+swingCount[1]);
	                                	labels[1][0].setText(swingCount[1]+"");
	                                	float sremaining = Float.parseFloat(labels[1][1].getText())-1;
	                                	if(sremaining<=0) sremaining = Float.parseFloat(spanel.getSwingsNeeded(2));
	                                	labels[1][1].setText(fmt.format(sremaining)+"");
	                            	}                            	
	                            }
	                            
	                            //F1 to F10 pressed determines which skill number to track
	                            for(int i=0; i<10; i++) {
	                            	if(info.vkCode==(112+i)) {
	                            		skillNumBeingTracked = spanel.getSkillTracked(i);
	                            		if(!playStarted) {
	                            			playStarted = true;
	                            			tpanel.startPlayTimer(); 
	                            		}
	                            	}
	                            }
	                            
	                            if(info.vkCode == 70) { //"f" - track fight
	                            	skillNumBeingTracked = spanel.getSkillTracked(10);
	                            }
	                            if(info.vkCode == 74) { //"j" - track jumpkick
	                            	skillNumBeingTracked = spanel.getSkillTracked(11);
	                            }
	                            if(info.vkCode == 80) { //"p" - track polekick
	                            	skillNumBeingTracked = spanel.getSkillTracked(12);
	                            }
	                            
	                           tpanel.toggleSkillAlert(skillNumBeingTracked);
                            }
                    }
                }

                Pointer ptr = info.getPointer();
                long peer = Pointer.nativeValue(ptr);
                return lib.CallNextHookEx(hhk, nCode, wParam, new LPARAM(peer));
            }				
        };
        
        
        hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
        //System.out.println("Keyboard hook installed, type anywhere, 'q' to quit");
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        /*
		         * 
		         * ACTIONS TO PERFORM BEFORE CLOSING THE PROGRAM
		         * 
		    	*/
		    	if(spanel.getCharacterIndex()!=0) {
		    		tpanel.setExperience();			    	
		    	}
		    	setQuit(true);
		    }
		});
        
        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try { Thread.sleep(10); } catch(Exception e) { }
                }
                System.err.println("unhook and exit");
                lib.UnhookWindowsHookEx(hhk);
                System.exit(0);
            }
        }.start();
        
        
     // This bit never returns from GetMessage
        int result;
        MSG msg = new MSG();
        while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                System.err.println("error in get message");
                break;
            }
            else {
                System.err.println("got message");
                lib.TranslateMessage(msg);
                lib.DispatchMessage(msg);
            }
        }
        lib.UnhookWindowsHookEx(hhk);
        
        
       
	}

	public void setQuit(boolean quit) {
		SwingKeyCapture.quit = quit;
	}	
	
}
