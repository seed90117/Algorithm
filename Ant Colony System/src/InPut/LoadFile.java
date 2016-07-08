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
		
		//*****�w�]�ɮ׶}�Ҧ�m*****//
		View.open.setCurrentDirectory(new java.io.File("//Users//kevin//Documents//Algorithm_Data//Ant Algorithm_Data"));
		
		//*****�]�w�}�ɵ���Title*****//
		View.open.setDialogTitle("����ɮ�");
		
		//*****�P�_�O�_���U�T�w*****//
		if(View.open.showDialog(View.antamount_input, "�T�w") == JFileChooser.APPROVE_OPTION)
		{
			//*****���o����ɮ�*****//
			File filepath = View.open.getSelectedFile();
			
			//*****�Ȧs�ɮ�*****//
			tmp = filepath.getPath().toString();
			
			//*****�ɮ�Ū��*****//
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
			//*****Ū���Ĥ@��*****//
			tmp = br.readLine();
			tmparray = tmp.split(" ");
			
			//*****�]�w�Ѽ�*****//
			Main.total = Integer.parseInt(tmparray[0]);
			Main.x = new double[Main.total];
			Main.y = new double[Main.total];
			
			//*****Ū�����*****//
			while((tmp = br.readLine()) != null)
			{
				tmparray = tmp.split(" ");
				Main.x[i] = Double.valueOf(tmparray[0]);
				Main.y[i] = Double.valueOf(tmparray[1]);
				//*****����XY�b���̤j�Ʀr*****//
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
			
			//*****���o��j���v*****//
			DrawPanel.size = View.show.getHeight()/size;
			
		}catch(Exception ex){
			Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
