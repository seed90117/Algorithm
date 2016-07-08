package InterFace;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Input.loadfile;
import Program.Main;
import Value.value;

public class View extends JFrame {

	public static boolean check= false;
	String[] codebooknum = new String[]{"2","4","8","16","32","64","128","256","512"};
	
	//*****宣告界面*****//
	Container cp = this.getContentPane();
	
	//*****宣告物件*****//
	JLabel oldimage = new JLabel("Old Image");
	JLabel newimage = new JLabel("New Image");
	JLabel codebook = new JLabel("Codebook");
	JLabel threshold = new JLabel("Threshold");
	JLabel time_output = new JLabel();
	JComboBox cb = new JComboBox(codebooknum);
	JTextField th = new JTextField("0.0001");
	public static JPanel inputimage = new JPanel();
	public static JPanel outputimage = new JPanel();
	JButton loadimage = new JButton("Load Image");
	JButton start = new JButton("Start");
	public static JFileChooser open = new JFileChooser();
	
	View()
	{
		//*****設定表單*****//
		this.setSize(1350, 600);
		this.setLayout(null);
		this.setTitle("LBG");
		
		//*****設定物件*****//
		oldimage.setBounds(20, 10, 100, 30);
		inputimage.setBounds(20, 40, 512, 512);
		newimage.setBounds(550, 10, 100, 30);
		outputimage.setBounds(550, 40, 512, 512);
		codebook.setBounds(1100, 40, 100, 30);
		cb.setBounds(1200, 40, 100, 30);
		threshold.setBounds(1100, 100, 100, 30);
		th.setBounds(1200, 100, 100, 30);
		loadimage.setBounds(1100, 160, 180, 30);
		start.setBounds(1100, 220, 180, 30);
		time_output.setBounds(1100, 280, 150, 30);
		
		//*****設定Panel邊框*****//
		inputimage.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		outputimage.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		//*****物件加入介面*****//
		cp.add(oldimage);
		cp.add(inputimage);
		cp.add(newimage);
		cp.add(outputimage);
		cp.add(codebook);
		cp.add(threshold);
		cp.add(cb);
		cp.add(th);
		cp.add(loadimage);
		cp.add(start);
		cp.add(time_output);
		
		//*****設定表單屬性*****//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//*****載入圖片按鈕*****//
		loadimage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				loadfile.loadfile();
			}});
		
		//*****執行按鈕*****//
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(th.getText() != "" && check)
				{
					long T = System.currentTimeMillis();
					value.cb = Integer.parseInt(cb.getSelectedItem().toString());
					value.th = Double.parseDouble(th.getText());
					Main.main();
					time_output.setText("運行時間:"+(((double)System.currentTimeMillis()-(double)T)/1000));
				}
			}});
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new View();
	}

}
