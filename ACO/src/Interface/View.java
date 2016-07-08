package Interface;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IO.Drawpanel;
import IO.Loadfile;
import Program.Main;

public class View extends JFrame {

	public static boolean checkloading = false;
	public static double  totaldistance = 0;//執行次數總和
	public static double  totaltime = 0;//執行時間總和
	public static int runtime = 0;
	public static int runcount;
	
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告介面*****//
	JLabel antamount = new JLabel("Ant Amount:"); //螞蟻數量
	JLabel pheromone = new JLabel("Pheromone:");//起始費洛蒙
	JLabel time = new JLabel("Time:");//執行時間
	JLabel A = new JLabel("a:");//貪婪法權重
	JLabel B = new JLabel("B:");//費洛蒙權重
	JLabel q = new JLabel("q:");//路徑選擇
	JLabel p = new JLabel("p:");//費洛蒙衰退參數
	JLabel computerrun = new JLabel("執行次數：");//Computer Run
	JLabel avgcost = new JLabel("平均距離：");//平均距離
	JLabel avgtime = new JLabel("平均時間：");//平均時間
	public static JLabel time_output = new JLabel("花費時間：");
	public static JLabel cost_output = new JLabel("最佳距離：");
	public static JTextField antamount_input = new JTextField("10");
	JTextField pheromone_input = new JTextField("0.00001");
	JTextField time_input = new JTextField("100");
	JTextField A_input = new JTextField("1");
	JTextField B_input = new JTextField("2");
	JTextField q_input = new JTextField("0.9");
	JTextField p_input = new JTextField("0.1");
	JTextField computerrun_input = new JTextField("100");
	JButton loadfile = new JButton("Load File");
	JButton drawpanel = new JButton("Draw Panel");
	JButton start = new JButton("Starts");
	public static JPanel show = new JPanel();
	public static JFileChooser open = new JFileChooser();
	public static JCheckBox iscomputerrun = new JCheckBox("Computer Run");
	
	
	View()
	{
		//*****設定介面*****//
		this.setSize(1000, 800);
		this.setLayout(null);
		this.setTitle("Ant Colony System Algorithm");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//*****設定物件位置*****//
		antamount.setBounds(730, 30, 100, 30);
		antamount_input.setBounds(810, 30, 150, 30);
		pheromone.setBounds(730, 80, 100, 30);
		pheromone_input.setBounds(810, 80, 150, 30);
		time.setBounds(750, 130, 100, 30);
		time_input.setBounds(810, 130, 150, 30);
		A.setBounds(760, 180, 100, 30);
		A_input.setBounds(810, 180, 150, 30);
		B.setBounds(760, 230, 100, 30);
		B_input.setBounds(810, 230, 150, 30);
		q.setBounds(760, 280, 100, 30);
		q_input.setBounds(810, 280, 150, 30);
		p.setBounds(760, 330, 100, 30);
		p_input.setBounds(810, 330, 150, 30);
		loadfile.setBounds(750, 380, 200, 30);
		drawpanel.setBounds(750, 430, 200, 30);
		start.setBounds(750, 480, 200, 30);
		show.setBounds(20, 20, 700, 700);
		time_output.setBounds(750, 510, 250, 30);
		cost_output.setBounds(750, 540, 250, 30);
		iscomputerrun.setBounds(740, 570, 200, 30);
		computerrun.setBounds(750, 600, 100, 30);
		computerrun_input.setBounds(810, 600, 100, 30);
		avgcost.setBounds(750, 630, 250, 30);
		avgtime.setBounds(750, 660, 250, 30);
		
		
		//*****設定JPanel框線*****//
		show.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//*****物件放入介面*****//
		cp.add(antamount);
		cp.add(antamount_input);
		cp.add(pheromone);
		cp.add(pheromone_input);
		cp.add(time);
		cp.add(time_input);
		cp.add(A);
		cp.add(A_input);
		cp.add(B);
		cp.add(B_input);
		cp.add(q);
		cp.add(q_input);
		cp.add(p);
		cp.add(p_input);
		cp.add(loadfile);
		cp.add(drawpanel);
		cp.add(start);
		cp.add(show);
		cp.add(time_output);
		cp.add(cost_output);
		cp.add(computerrun);
		cp.add(computerrun_input);
		cp.add(avgcost);
		cp.add(iscomputerrun);
		cp.add(avgtime);
		
		//*****讀取檔案按鈕事件*****//
		loadfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Loadfile.loadfile();
			}
		});
		
		//*****畫點按鈕事件*****//
		drawpanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkloading)
				{
					Drawpanel.drawpanel();
				}
			}
		});
		
		//*****執行演算法按鈕事件*****//
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkloading)
				{
					Main.antcount = Integer.parseInt(antamount_input.getText());
					Main.T = Integer.parseInt(time_input.getText());
					Main.pheromone = Double.parseDouble(pheromone_input.getText());
					Main.A = Double.parseDouble(A_input.getText());
					Main.B = Double.parseDouble(B_input.getText());
					Main.q = Double.parseDouble(q_input.getText());
					Main.p = Double.parseDouble(p_input.getText());
					if(iscomputerrun.isSelected())
					{
						totaldistance = 0;
						totaltime = 0;
						runcount = 1;
						runtime = Integer.parseInt(computerrun_input.getText());
						for(int i=0;i<runtime;i++)
						{
							Drawpanel.drawpanel();
							Main.main();
							totaldistance += Main.bestdistance;
							totaltime += Main.costtime;
							avgcost.setText("平均距離："+String.valueOf(totaldistance/(double)runtime));
							avgtime.setText("平均時間："+String.valueOf(totaltime/(double)runtime));
							runcount++;
						}
					}
					else 
					{
						Drawpanel.drawpanel();
						Main.main();
					}
				}
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View();
	}

}
