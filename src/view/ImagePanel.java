package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private byte[] bytes;
	private int offset;
	private int width;
	private int height;
	private int pixel;
	private boolean bw;
	private int umbral;
	
	
	public ImagePanel ()
	{
		bytes = null;
		offset = -1;
		width = 0;
		height = 0;
		pixel = 1;
		umbral = 127;
		bw = false;
	}
	
	public void drawImage (byte[] bytes, int offset, int width, int height)
	{
		this.bytes = bytes;
		this.offset = offset;
		this.width = width;
		this.height = height;
		repaint();
	}
	
	@Override
	public void paint (Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(bw)
			paintBW(g2);
		else
			paintNormal(g2);		
	}
	
	
	public void paintNormal (Graphics2D g2)
	{
		int rest = width%4;
		int x = (int)(this.getSize().getWidth() - width)/2;
		int y = (int)(this.getSize().getHeight() - height)/2;
		
		int k = offset;
		for (int i = height-1; i > 0; i--) 
		{
			for (int j = 0; j < width; j++) 
			{
				g2.setColor(new Color((int)bytes[k+2] & 0xFF, (int)bytes[k+1] & 0xFF, (int)bytes[k] & 0xFF));
				//System.out.println(gd2.getColor().getRed() + "\t" + gd2.getColor().getGreen() + "\t" + gd2.getColor().getBlue() );
				g2.fillRect(x+j, y+i, pixel, pixel);
				k += 3;
			}
			//System.out.println(Integer.toHexString((int)bytes[k] & 0xFF));
			k+= rest;
		}
	}
	
	public void paintBW (Graphics2D g2)
	{
		//String n = JOptionPane.showInputDialog(this, "Enter the umbral to Black & White: ");
		int rest = width%4;
		int x = (int)(this.getSize().getWidth() - width)/2;
		int y = (int)(this.getSize().getHeight() - height)/2;
		
		int k = offset;
		for (int i = height-1; i > 0; i--) 
		{
			for (int j = 0; j < width; j++) 
			{
				int r = ((int)bytes[k+2] & 0xFF)  > umbral ? 255 : 0;
				int g = ((int)bytes[k+1] & 0xFF)  > umbral ? 255 : 0;
				int b = ((int)bytes[k] & 0xFF)  > umbral ? 255 : 0;
				int sum = r + g + b;
				if(sum >= 510)				
					g2.setColor(new Color(255, 255, 255));
				else
					g2.setColor(new Color(0, 0, 0));
				//System.out.println(gd2.getColor().getRed() + "\t" + gd2.getColor().getGreen() + "\t" + gd2.getColor().getBlue() );
				g2.fillRect(x+j, y+i, pixel, pixel);
				k += 3;
			}
			//System.out.println(Integer.toHexString((int)bytes[k] & 0xFF));
			k+= rest;
		}
	}
	
//	public void zoom ()
//	{
//		pixel++;
//		repaint();
//	}
	
	public void setBW (boolean available) {
		bw = available;
	}
	
	
}
