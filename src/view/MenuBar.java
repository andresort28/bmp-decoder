package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar implements ActionListener
{
	private JMenu menuFile;	
	private JMenuItem iOpen;
	private JMenuItem iSaveAs;
	private JMenuItem iExit;		
	
	private JMenu menuEdit;	
	private JMenuItem iChange;		
		
	private JMenu menuAbout;	
	private JMenuItem iAbout;
	
	private InterfaceBMP decoder;
	
		
	public MenuBar(InterfaceBMP decoder)
	{
		this.decoder = decoder;
		menuFile = new JMenu("File");
		
		iOpen = new JMenuItem("Open", new ImageIcon("img/open.png"));
		iOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		iOpen.addActionListener(this);		
		
		iSaveAs = new JMenuItem("Save As",  new ImageIcon("img/saveas.png"));
		iSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		iSaveAs.addActionListener(this);	
		
		iExit = new JMenuItem ("Exit", new ImageIcon("img/exit.png"));
		iExit.addActionListener(this);	
		
		menuFile.add(iOpen);
		menuFile.add(iSaveAs);
		menuFile.add(new JSeparator());
		menuFile.add(iExit);
		
		
		
		
		menuEdit = new JMenu("Edit");
		
		iChange = new JMenuItem("Convert to Black & White", new ImageIcon("img/bw.png"));
		iChange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		iChange.addActionListener(this);
		
		menuEdit.add(iChange);
		
			
		
		
		menuAbout = new JMenu("About");
		
		iAbout = new JMenuItem("About", new ImageIcon("img/about.png"));
		iAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.SHIFT_MASK));
		iAbout.addActionListener(this);
		
		menuAbout.add(iAbout);
		
		
		add(menuFile);
		add(menuEdit);
		add(menuAbout);
	}




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Open"))
			decoder.openFile();
		if(cmd.equalsIgnoreCase("Save As"))
			decoder.saveAs();
		if(cmd.equalsIgnoreCase("Exit"))
			decoder.exit();
		if(cmd.equalsIgnoreCase("Convert to Black & White"))
			decoder.convertTo_BlackAndWhite();		
		if(cmd.equalsIgnoreCase("About"))
			decoder.about();		
	}
	
}
