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
    
    
	SwingKeyCapture(JFrame frame, StartPanel spanel, TrackingPanel tpanel) {		 
		//tpanel.loadKeyCapture(this); 
		final User32 lib = User32.INSTANCE;
	     HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
	     keyboardHook = new LowLevelKeyboardProc() {
	    
	    
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
                        	tpanel.keyPressed(info.vkCode);
                            
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
		        
		    	if(spanel.getCharacterIndex()!=0) {
		    		tpanel.saveSession();			    	
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
