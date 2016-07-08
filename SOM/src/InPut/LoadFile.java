package InPut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import InterFace.View;
import OutPut.DrawPanel;
import Program.Main;

public class LoadFile {

	static BufferedReader br;
	
	public static void loadfile()
	{
		String tmp =null;
		FileReader fr = null;
		
		//*****預設檔案開啟位置*****//
		View.open.setCurrentDirectory(new java.io.File("//Users//kevin//Documents//Algorithm_Data//SOM_Data"));
		
		//*****設定開檔視窗Title*****//
		View.open.setDialogTitle("選取檔案");
		
		//*****判斷是否按下確定*****//
		if(View.open.showDialog(View.output_input, "確定") == JFileChooser.APPROVE_OPTION)
		{
			//*****取得選取檔案*****//
			File filepath = View.open.getSelectedFile();
			
			//*****暫存檔案*****//
			tmp = filepath.getPath().toString();
			
			//*****檔案選取*****//
			try
			{
				fr = new FileReader(tmp);
			}
			catch (FileNotFoundException ex)
			{
				Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null , ex);
			}
			
			br = new BufferedReader(fr);
			View.b = true;
			getdata();
		}
	}
	public static void getdata()
	{
		String tmp;
		String[] tmparray;
		int i =0;
		double size=0;
		
		try
		{
			//*****讀取第一行*****//
			tmp = br.readLine();
			tmparray = tmp.split(" ");
			
			//*****設定參數*****//
			Main.total = Integer.parseInt(tmparray[0]);
			Main.x = new double[Main.total];
			Main.y = new double[Main.total];
			
			//*****讀取資料*****//
			while((tmp = br.readLine()) != null)
			{
				tmparray = tmp.split(" ");
				Main.x[i] = Double.valueOf(tmparray[0]);
				Main.y[i] = Double.valueOf(tmparray[1]);
				//*****取得XY軸最大值*****//
				if(Main.x[i] > size)
				{
					size = Main.x[i];
				}
				if(Main.y[i] > size)
				{
					size = Main.y[i];
				}
				i++;
			}
			
			//*****取得放大率*****//
			DrawPanel.size = View.show.getHeight()/size;
			
		}catch(Exception ex){
			Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
