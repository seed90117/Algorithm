package Input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import InterFace.View;

public class drawpanel {

	static Graphics gp;
	
	public static void drawoldpanel()
	{
		gp = View.inputimage.getGraphics();
		Graphics2D g2d = (Graphics2D)gp;
		g2d.drawImage(loadfile.image, 0, 0, null);
	}
	
	public static void drawcolor(int x, int y, int r, int g, int b)
	{
		gp = View.outputimage.getGraphics();
		Color color = new Color(r,g,b);
		gp.setColor(color);
		gp.fillRect(x, y, 1, 1);
	}
}
