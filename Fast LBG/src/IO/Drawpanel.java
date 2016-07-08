package IO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Interface.View;
import Program.Main;
import Value.data;


public class Drawpanel {

	static Graphics gp;
	
	//*****顯示輸入影像*****//
	public static void drawoldpanel()
	{
		gp = View.inputimage.getGraphics();//Panel轉成畫布
		//Graphics2D g2d = (Graphics2D)gp;//轉換成2D畫布
		//g2d.drawImage(Loadfile.image, 0, 0, null);//顯示圖片
		
		Main.imagedata = new data[Loadfile.image.getWidth()*Loadfile.image.getHeight()];//宣告主要陣列變數陣列大小
		int num = 0;//計數陣列小
		for(int i=0;i<Loadfile.image.getWidth();i++)
		{
			for(int j=0;j<Loadfile.image.getHeight();j++)
			{
				Main.imagedata[num] = new data();
				Main.imagedata[num].X = i;
				Main.imagedata[num].Y = j;
				Main.imagedata[num].R = (Loadfile.image.getRGB(i, j)& 0xFF0000) >> 16;//R
				Main.imagedata[num].G = (Loadfile.image.getRGB(i, j)& 0xFF00) >> 8;//G
				Main.imagedata[num].B = (Loadfile.image.getRGB(i, j)& 0xFF);//B
				Color color = new Color(Main.imagedata[num].R,Main.imagedata[num].G,Main.imagedata[num].B);
				gp.setColor(color);//設定顏色
				gp.fillRect(i, j, 1, 1);//打點
				num++;
			}
		}
	}
	
	//*****顯示輸出影像*****//
	public static void drawcolor(int x, int y, int r, int g, int b)
	{
		gp = View.outputimage.getGraphics();//Panel轉成畫布
		Color color = new Color(r,g,b);
		gp.setColor(color);//設定顏色
		gp.fillRect(x, y, 1, 1);//打點
	}
}
