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
}
