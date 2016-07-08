package IO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import Interface.View;

public class Loadfile {

public static BufferedImage image;
	
	public static void loadfile()
	{
		String tmp;
		
		//*****設定檔案選擇器開啟位置*****//
		View.open.setCurrentDirectory(new java.io.File("//Users//kevin//Documents//Algorithm_Data//LBG-Image"));
		
		//*****設定檔案選擇器Title*****//
		View.open.setDialogTitle("開啟檔案");
		
		//*****是否按下確定*****//
		if(View.open.showDialog(View.outputimage, "確定") == JFileChooser.APPROVE_OPTION)
		{
			//*****取得選擇檔案位置*****//
			tmp = View.open.getSelectedFile().getPath();
			
			//*****轉成File*****//
			File file = new File(tmp);
			
			//*****讀出檔案放入影像物件*****//
			try
			{
				image = ImageIO.read(file);
			}
			catch (FileNotFoundException ex)
			{
				Logger.getLogger(Loadfile.class.getName()).log(Level.SEVERE, null , ex);
			}
			catch(IOException ex)
			{
				Logger.getLogger(Loadfile.class.getName()).log(Level.SEVERE, null , ex);
			}
			
			Drawpanel.drawoldpanel();
			View.check = true;
		}
				
	}
}
