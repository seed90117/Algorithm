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

import InPut.LoadFile;
import OutPut.DrawPanel;
import Program.Main;

public class View extends JFrame {

	public static boolean b = false;
	
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告物件*****//
	JLabel output = new JLabel("輸出層大小:");
	JLabel time = new JLabel("代數:");
	JLabel eps = new JLabel("半徑:");
	JLabel a = new JLabel("縮小因子:");
	JLabel n = new JLabel("學習速率:");
	JLabel time_output = new JLabel();
	public static JTextField output_input = new JTextField("32");
	JTextField time_input = new JTextField("120");
	JTextField eps_input = new JTextField("10");
	JTextField a_input = new JTextField("0.975");
	JTextField n_input = new JTextField("0.5");
	JButton loadfile = new JButton("Load File");
	JButton drawpanel = new JButton("Draw Panel");
	JButton start = new JButton("Starts");
	public static JPanel show = new JPanel();
	public static JFileChooser open = new JFileChooser();
	
	View()
	{
		//*****設定表單*****//
		this.setSize(1000, 800);
		this.setLayout(null);
		this.setTitle("SOM");
		
		//*****設定物件位置*****//
		output.setBounds(760, 30, 100, 30);
		output_input.setBounds(840, 30, 100, 30);
		time.setBounds(770, 80, 100, 30);
		time_input.setBounds(840, 80, 100, 30);
		eps.setBounds(770, 130, 100, 30);
		eps_input.setBounds(840, 130, 100, 30);
		a.setBounds(760, 180, 100, 30);
		a_input.setBounds(840, 180, 100, 30);
		n.setBounds(760, 230, 100, 30);
		n_input.setBounds(840, 230, 100, 30);
		loadfile.setBounds(750, 280, 200, 30);
		drawpanel.setBounds(750, 330, 200, 30);
		start.setBounds(750, 380, 200, 30);
		show.setBounds(20, 20, 700, 700);
		time_output.setBounds(750, 430, 150, 30);
		
		//*****設定JPanel邊框*****//
		show.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//*****新增物件至介面*****//
		cp.add(output);
		cp.add(output_input);
		cp.add(time);
		cp.add(time_input);
		cp.add(eps);
		cp.add(eps_input);
		cp.add(a);
		cp.add(a_input);
		cp.add(n);
		cp.add(n_input);
		cp.add(loadfile);
		cp.add(drawpanel);
		cp.add(start);
		cp.add(show);
		cp.add(time_output);
		
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
		drawpanel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b)
				{		
					DrawPanel.drawpanel();
				}
			}});
				
		//*****執行按鈕*****//
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b && output_input.getText() != "" && time_input.getText() != "" && eps_input.getText() != "" && a_input.getText() != "" && n_input.getText() != "")
				{
					long T = System.currentTimeMillis();
					Main.outputlayer = Integer.parseInt(output_input.getText());
					Main.time = Integer.parseInt(time_input.getText());
					Main.eps = Double.parseDouble(eps_input.getText());
					Main.a = Double.parseDouble(a_input.getText());
					Main.n = Double.parseDouble(n_input.getText());
					Main.main();
					time_output.setText("執行時間:"+(((double)System.currentTimeMillis()-(double)T)/1000));
				}
			}});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}

}
