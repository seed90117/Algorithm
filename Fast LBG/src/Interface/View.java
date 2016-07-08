package Interface;

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
import javax.swing.JTextField;

import IO.Loadfile;
import Program.Main;

public class View extends JFrame {

	public static boolean check= false;//判斷有無讀檔
	static String[] codebooknum = new String[]{"2","4","8","16","32","64","128","256","512"};//下拉式選單內選項
	
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告物件*****//
	JLabel oldimage = new JLabel("Old Image");
	JLabel newimage = new JLabel("New Image");
	JLabel codebook = new JLabel("Codebook");
	JLabel threshold = new JLabel("Threshold");
	JLabel time_output = new JLabel();
	public static JComboBox codebook_input = new JComboBox(codebooknum);//Codebook大小
	JTextField threshold_input = new JTextField("0.0001");//門檻值
	public static JPanel inputimage = new JPanel();
	public static JPanel outputimage = new JPanel();
	JButton loadimage = new JButton("Load Image");
	JButton start = new JButton("Start");
	public static JFileChooser open = new JFileChooser();
	
	View()
	{
		//*****設定介面*****//
		this.setSize(1300, 600);
		this.setLayout(null);
		this.setTitle("LBG");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//*****設定物件�*****//
		oldimage.setBounds(10, 10, 100, 30);
		inputimage.setBounds(10, 40, 512, 512);
		newimage.setBounds(540, 10, 100, 30);
		outputimage.setBounds(540, 40, 512, 512);
		codebook.setBounds(1060, 40, 100, 30);
		codebook_input.setBounds(1130, 40, 100, 30);
		threshold.setBounds(1060, 100, 100, 30);
		threshold_input.setBounds(1130, 100, 100, 30);
		loadimage.setBounds(1060, 160, 180, 30);
		start.setBounds(1060, 220, 180, 30);
		time_output.setBounds(1060, 280, 150, 30);
		
		//*****設定Panel框線*****//
		inputimage.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		outputimage.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		//*****物件加入介面�*****//
		cp.add(oldimage);
		cp.add(inputimage);
		cp.add(newimage);
		cp.add(outputimage);
		cp.add(codebook);
		cp.add(threshold);
		cp.add(codebook_input);
		cp.add(threshold_input);
		cp.add(loadimage);
		cp.add(start);
		cp.add(time_output);
		
		//*****讀檔按鈕事件*****//
		loadimage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Loadfile.loadfile();
			}});
		
		//*****演算法開始按鈕事件*****//
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(threshold_input.getText() != "" && check)
				{
					Main.threshold = Double.parseDouble(threshold_input.getText());
					Main.codebook = Integer.parseInt(codebook_input.getSelectedItem().toString());
					
					long T = System.currentTimeMillis();//紀錄開始時間
					Main.main();
					time_output.setText("運行時間：" + String.valueOf(((double)System.currentTimeMillis()-(double)T)/1000));
				}
			}});
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}

}
