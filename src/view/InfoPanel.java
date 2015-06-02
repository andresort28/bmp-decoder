package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel
{
	private JLabel name;
	private JLabel width;
	private JLabel height;
	private JLabel format;
	private JLabel depth;
	private JLabel bytes;
	private JLabel size;
	
	private JLabel iName;
	private JLabel iWidth;
	private JLabel iHeight;
	private JLabel iFormat;
	private JLabel iDepth;
	private JLabel iBytes;
	private JLabel iSize;
	
	public InfoPanel ()
	{
		 TitledBorder border = BorderFactory.createTitledBorder("Information");
		 setBorder(border);
		 setLayout(new GridLayout(7, 2, 30, 0));
		
		this.name = new JLabel("   Name:");
		this.width = new JLabel("   Width:");
		this.height = new JLabel("   Height:");
		this.format = new JLabel("   Format:");
		this.bytes = new JLabel("   Bytes:");
		this.depth = new JLabel("   Depth:");
		this.size = new JLabel("   Size:");
		
		this.iName = new JLabel("                       ");
		this.iWidth = new JLabel("                       ");
		this.iHeight = new JLabel("                       ");
		this.iFormat = new JLabel("                       ");
		this.iBytes = new JLabel("                       ");
		this.iDepth = new JLabel("                       ");
		this.iSize = new JLabel("                       ");
		
		add(name);
		add(iName);
		add(width);
		add(iWidth);
		add(height);
		add(iHeight);
		add(format);
		add(iFormat);
		add(bytes);
		add(iBytes);
		add(depth);
		add(iDepth);
		add(size);
		add(iSize);
	}
	
	public void refresh (String name, String width, String height, String format, String bytes, String depth, String size)
	{
		this.iName.setText(name + "   ");
		this.iWidth.setText(width + " px   ");
		this.iHeight.setText(height + " px   ");
		this.iFormat.setText(format + "   ");
		this.iBytes.setText(bytes + "   ");
		this.iDepth.setText(depth +"  bits   ");
		this.iSize.setText(size + " KB   ");
	}
	

	
}
