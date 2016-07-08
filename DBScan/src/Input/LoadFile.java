package Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import InterFace.View;
import Value.value;

public class LoadFile {

	static BufferedReader br;
	
	public static void loadfile()
	{
		String tmp =null;
		FileReader fr = null;
		
		//*****�w�]�ɮ׶}�Ҧ�m*****//
		View.open.setCurrentDirectory(new java.io.File("//Users//kevin//Documents//Algorithm_Data//DBScan_data"));
		
		//*****�]�w�}�ɵ���Title*****//
		View.open.setDialogTitle("����ɮ�");
		
		//*****�P�_�O�_���U�T�w*****//
		if(View.open.showDialog(View.eps, "�T�w") == JFileChooser.APPROVE_OPTION)
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
		
		try
		{
			//*****Ū���Ĥ@��*****//
			tmp = br.readLine();
			tmparray = tmp.split(" ");
			
			//*****�]�w�Ѽ�*****//
			value.total = Integer.parseInt(tmparray[0]);
			value.x = new double[value.total];
			value.y = new double[value.total];
			
			//*****Ū�����*****//
			while((tmp = br.readLine()) != null)
			{
				tmparray = tmp.split(" ");
				value.x[i] = Double.valueOf(tmparray[0]);
				value.y[i] = Double.valueOf(tmparray[1]);
				//value.x[i] = Double.valueOf(tmparray[2]);
				i++;
			}
		}catch(Exception ex){
			Logger.getLogger(LoadFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
