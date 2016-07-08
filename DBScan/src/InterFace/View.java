package InterFace;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Input.LoadFile;
import Output.DrawPanel;
import Program.Main;
import Value.value;

public class View extends JFrame {

	public static boolean b = false;
	
	//*****宣告界面*****//
	Container cp = this.getContentPane();
	
	//*****宣告物件*****//
	public static JLabel eps = new JLabel("eps:");
	JLabel minpts = new JLabel("minpts:");
	JTextField eps_input = new JTextField("7");
	JTextField minpts_input = new JTextField("15");
	JButton loadfile = new JButton("Load File");
	JButton drawpoint = new JButton("Draw Point");
	JButton start = new JButton("Start");
	public static JPanel show = new JPanel();
	public static JFileChooser open = new JFileChooser();
	
	View()
	{
		//*****設定表單*****//
		this.setSize(1300, 800);
		this.setLayout(null);
		this.setTitle("DBScan");
		
		//*****設定物件位置*****//
		eps.setBounds(1050, 30, 100, 30);
		eps_input.setBounds(1100, 30, 150, 30);
		minpts.setBounds(1050, 80, 100, 30);
		minpts_input.setBounds(1100, 80, 150, 30);
		loadfile.setBounds(1050, 130, 200, 30);
		drawpoint.setBounds(1050, 180, 200, 30);
		start.setBounds(1050, 230, 200, 30);
		show.setBounds(20, 20, 1000, 700);
		
		//*****設定JPanel邊框*****//
		show.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//*****將物件加入介面*****//
		cp.add(eps);
		cp.add(eps_input);
		cp.add(minpts);
		cp.add(minpts_input);
		cp.add(loadfile);
		cp.add(drawpoint);
		cp.add(start);
		cp.add(show);
		
		//*****設定表單屬性*****//
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//*****載入檔案按鈕*****//
		loadfile.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoadFile.loadfile();
			}});
		
		//*****JPanel上畫點按鈕*****//
		drawpoint.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b)
				{
					DrawPanel.drawdatapoint();
				}
			}});
		
		//*****執行按鈕*****//
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b && eps_input.getText() != "" && minpts_input.getText() != "")
				{
					value.eps = Integer.parseInt(eps_input.getText());
					value.minpts = Integer.parseInt(minpts_input.getText());
					Main.main();
				}
			}});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new View();
	}

}
