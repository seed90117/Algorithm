package Output;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import InterFace.View;
import Value.value;

public class DrawPanel {

	static Graphics g;
	
	public static void drawdatapoint()
	{
		//*****JPanel轉畫布*****//
		g = View.show.getGraphics();
		
		//*****清空畫布*****//
		g.setColor(Color.white);
						
		//*****清空範圍*****//
		g.fillRect(0, 0, View.show.getWidth(), View.show.getHeight());
		
		//*****設定顏色*****//
		g.setColor(Color.black);
		
		//*****畫布上打點*****//
		for(int i = 0; i < value.total; i++)
		{
			int x = (int)((value.x[i]));
			int y = (int)((value.y[i]));
			g.fillRect(x, y, 3, 3);
		}
	}
	
	public static void drawoutput()
	{
		Color c;
		Set<Color> st = new HashSet<Color>();
		//*****清空畫布*****//
		g.setColor(Color.white);
								
		//*****清空範圍*****//
		g.fillRect(0, 0, View.show.getWidth(), View.show.getHeight());
		
		//*****隨機產生不重複顏色*****//
		for(int i=0;i<value.colornum;i++)
		{
			c = makeRandomColor();
			if(st.add(c))
			{
				//*****設定顏色*****//
				g.setColor(c);
				
				//*****畫布上打點*****//
				for(int j = 0; j < value.total; j++)
				{
					if(value.group[j] == i)
					{
						int x = (int)((value.x[j]));
						int y = (int)((value.y[j]));
						g.fillRect(x, y, 3, 3);
					}
				}
			}
			else
			{
				i--;
			}
		}
	}
	
	public static Color makeRandomColor() {
		Random rd = new Random();
	    int red = rd.nextInt(256);
	    int green = rd.nextInt(256);
	    int blue = rd.nextInt(256);
	    return new Color(red, green, blue);
	}
}
