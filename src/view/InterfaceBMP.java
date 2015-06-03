package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InterfaceBMP extends JFrame
{

	private MenuBar menuBar;
	private InfoPanel infoPanel;
	private ImagePanel imagePanel;
	
	private File file;
	private int totalBytes;
	private int reserved1;
	private int reserved2;
	private int offset;
	private int header;
	private int width;
	private int height;
	private int planes;
	private int depth;
	private int compression;
	private int size;
	private int resolutionW;
	private int resolutionH;
	private int colors1;
	private int colors2;
	private byte[] bytes;
	
	
	
	public InterfaceBMP ()
	{
		setTitle("Decoder BMP - Information and Coding Theory");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(this);
		setResizable(false);
		
		infoPanel = new InfoPanel();
		imagePanel = new ImagePanel();		
		menuBar  = new MenuBar(this);
		
		setJMenuBar(menuBar);
		
		add(imagePanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.setSize(new Dimension(300, 0));
		panel.add(infoPanel);
		add(panel, BorderLayout.EAST);
	}
	
	public void loadBMP (File file)
	{
		try
		{
			long total = file.length();
			if(total > Integer.MAX_VALUE)
				throw new Exception("The BMP is so big to be loaded");
			bytes = new byte[(int)total];
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));				
			in.read(bytes);
			totalBytes = getDecimalValueLSB(bytes, 2, 5);
			if(!checkBMP(bytes) || totalBytes != (int)total)
				throw new Exception("This is not an original BMP file");
			reserved1 = getDecimalValueLSB(bytes, 6, 7);
			reserved2 = getDecimalValueLSB(bytes, 8, 9);
			offset = getDecimalValueLSB(bytes, 10, 13);
			header = getDecimalValueLSB(bytes, 14, 17);
			width = getDecimalValueLSB(bytes, 18, 21);
			height = getDecimalValueLSB(bytes, 22, 25);
			planes = getDecimalValueLSB(bytes, 26, 27);
			depth = getDecimalValueLSB(bytes, 28, 29);
			if(depth != 24)
				throw new Exception("This decoder accept 24-bits BMP image only");
			compression = getDecimalValueLSB(bytes, 30, 33);
			size = getDecimalValueLSB(bytes, 34, 37);
			resolutionW = getDecimalValueLSB(bytes, 38, 41);
			resolutionH = getDecimalValueLSB(bytes, 42, 45);
			colors1 = getDecimalValueLSB(bytes, 46, 49);
			colors2 = getDecimalValueLSB(bytes, 50, 53);
			refresh();
			printInformation();
			imagePanel.setBW(false);
			imagePanel.drawImage(bytes, offset, width, height);			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean checkBMP (byte[] bytes)
	{
		if(bytes[0] == Integer.parseInt("42", 16)  && bytes[1] == Integer.parseInt("4D", 16))
			return true;
		return false;
	}
	
	public int getDecimalValueLSB (byte[] bytes, int begin, int end)
	{
		String hx="";
		for (int i = end; i > begin-1; i--) {
			String n = Integer.toHexString((int)bytes[i] & 0xFF); 
			if(n.length() == 1)
				hx += "0";
			hx += n;
		}
		return Integer.parseInt(hx, 16);
	}
	
	
	public void refresh ()
	{
		infoPanel.refresh(file.getName(), width+"", height+"", "BMP", totalBytes+"", depth+"", (size/1024)+"");
	}
	
	
	public void openFile ()
	{
		try 
		{
			JFileChooser fc = new JFileChooser(System.getProperty(System.getProperty("user.home") + "/Desktop"));
			fc.setDialogTitle("Select an BMP Image");
			if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				file = fc.getSelectedFile();
				if (file != null)
				{
					String path = file.getPath();
					int pos = path.indexOf(".");
					String type = path.substring(pos);
					if(type.equalsIgnoreCase(".bmp"))
						loadBMP(file);
					else
						JOptionPane.showMessageDialog(this, "The image must be a BMP file, not " + type, "Error to open", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveAs ()
	{
		if(file == null)
			JOptionPane.showMessageDialog(this, "There is not any BMP image loaded", "Not Available", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			try 
			{
				JFileChooser fc = new JFileChooser(System.getProperty(System.getProperty("user.home") + "/Desktop"));
				fc.setDialogTitle("Save As");
				if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
				{
					File f = fc.getSelectedFile();
					if (f != null)
					{
						String path = f.getPath() + ".bmp";
						File file2 = new File(path);
						//Aqui llamo a un metodo que convierte todo a un bmp de nuevo
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void exit () 
	{
		this.dispose();
	}
	
	public void convertTo_BlackAndWhite ()
	{
		if(bytes == null)
			JOptionPane.showMessageDialog(this, "There are not any BMP image loaded", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			imagePanel.setBW(true);
			imagePanel.drawImage(bytes, offset, width, height);		
		}
	}
	
	public void about ()
	{
		String text= "Decodificador BMP \n\n" +
				"" + "Developed by: \n" +
				"" + "Andres Felipe Ortiz \n" +
				"" + "Estudiante de Ingenieria Telematica e Ingenieria de Sistemas \n\n" +
				"" + "Daniel Andres Palacios \n" +
				"" + "Estudiante de Ingenieria Telematica \n\n" +
				"" + "Proyecto Final - Teoria de Informacion y Codificacion \n" +
				"" + "Universidad ICESI \n" +
				"" + "11/26/2012";
		JOptionPane.showMessageDialog(this, text, "Development", JOptionPane.DEFAULT_OPTION);
	}
	
	
	public void printInformation ()
	{
		if(file != null)
		{
			System.out.println("Name: " + file.getName());
			System.out.println("Total of bytes: " + totalBytes);
			System.out.println("Reserved 1: " + reserved1);
			System.out.println("Reserved 2: " + reserved2);
			System.out.println("First Pixel of BMP: " + offset);
			System.out.println("Header: " + header);
			System.out.println("Width: " + width + " px");
			System.out.println("Heigth: " + height + " px");
			System.out.println("Number of Planes: " + planes);
			System.out.println("Bits per pixel: " + depth);
			System.out.println("Compression: " + compression);
			System.out.println("Size: " + (size > 1024 ? (size/1024) + " KB" : size + " Bytes"));
			System.out.println("Width resolution: " + resolutionW);
			System.out.println("Height resolution: " + resolutionH);
			System.out.println("Size of colors table: " + colors1);
			System.out.println("Important colors: " + colors2);
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		InterfaceBMP decorder = new InterfaceBMP();
		decorder.setVisible(true);

	}

}
