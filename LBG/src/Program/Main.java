package Program;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Input.*;
import Value.value;

public class Main {

	static Set<Integer> cp;
	static double MSE = 1;
	
	
	public static void main()
	{
		value.mse = 0;
		boolean check = true;
		value.group = new int[loadfile.image.getHeight()*loadfile.image.getWidth()];
		value.centerx = new int[value.cb];
		value.centery = new int[value.cb];
		value.centerr = new int[value.cb];
		value.centerg = new int[value.cb];
		value.centerb = new int[value.cb];
		rdpoint();
		
		while(check)
		{
			grouping();//分群
			calculateMSE();//計算MSE
			if((Math.abs(MSE-value.mse)/MSE) < value.th)//比較門檻值
			{
				check = false;	
			}
			value.mse = MSE;//儲存新的MSE
			reflashcenterpoint();//重新尋找群心點
		}
		
		replace();//輸出圖片
	}
	
	//*****隨機產生群心點*****//
	public static void rdpoint()
	{
		Random rd = new Random();
		cp = new HashSet<Integer>();
		for(int i=0; i<value.cb;i++)
		{
			cp.add(rd.nextInt(loadfile.image.getHeight()*loadfile.image.getWidth()));
		}
		point();
	}
	
	//*****將群心點紀錄並取得群心點RGB值*****//
	public static void point()
	{
		int num = 0,count = 0;
		for(int x=0; x<loadfile.image.getWidth();x++)
		{
			for(int y=0; y<loadfile.image.getHeight();y++)
			{
				count++;
				if(cp.contains(count) == true)
				{
					value.centerx[num] = x;
					value.centery[num] = y;
					value.centerr[num] = (loadfile.image.getRGB(x, y)& 0xFF0000) >> 16;
					value.centerg[num] = (loadfile.image.getRGB(x, y)& 0xFF00) >> 8;
					value.centerb[num] = (loadfile.image.getRGB(x, y)& 0xFF);
					num++;
				}
			}
		}
	}
	
	//*****分群*****//
	public static void grouping()
	{
		int count = 0;
		double[] t = new double[value.cb];
		
		for(int x=0; x<loadfile.image.getWidth();x++)
		{
			for(int y=0; y<loadfile.image.getHeight();y++)
			{
				for(int i=0;i<value.cb;i++)
				{
					t[i] = rgbdistance(x,y,value.centerr[i],value.centerg[i],value.centerb[i]);
				}
				value.group[count] = compare(t);
				count++;
			}
		}
	}
	
	//*****MSE計算*****//
	public static void calculateMSE()
	{
		double[] mse = new double[value.cb];
		double msesum = 0;
		//*****各組分別計算RGB值總合*****//
		for(int i=0;i<value.cb;i++)
		{
			int count = 0;
			for(int x=0; x<loadfile.image.getWidth();x++)
			{
				for(int y=0; y<loadfile.image.getHeight();y++)
				{
					if(value.group[count] == i)
					{
						mse[i] += rgbdistance(x,y,value.centerr[i],value.centerg[i],value.centerb[i]);
					}
				}
			}
		}
		
		//*****各組RGB值加總*****//
		for(int i=0;i<value.cb;i++)
		{
			msesum += mse[i];
		}
		
		MSE = msesum/(loadfile.image.getHeight()*loadfile.image.getWidth());
	}
	
	//*****計算RBG距離*****//
	public static double rgbdistance(int x, int y, int r, int g, int b)
	{
		double re = 0;
		int R,G,B;
		R = (loadfile.image.getRGB(x, y)& 0xFF0000) >> 16;
		G = (loadfile.image.getRGB(x, y)& 0xFF00) >> 8;
		B = (loadfile.image.getRGB(x, y)& 0xFF);
		re = (Math.pow(R-r,2)+Math.pow(G-g,2)+Math.pow(B-b,2));
		return re;
	}
	
	//*****計算RBG*****//
	public static double rgbdistance(String type, int x, int y)
	{
		int rgb=0;
		if(type == "r")
		{
			rgb = (loadfile.image.getRGB(x, y)& 0xFF0000) >> 16;
		}
		
		if(type == "g")
		{
			rgb = (loadfile.image.getRGB(x, y)& 0xFF00) >> 8;
		}
		
		if(type == "b")
		{
			rgb = (loadfile.image.getRGB(x, y)& 0xFF);
		}
		return rgb;
	}
	
	//*****比較距離大小*****//
	public static int compare(double[] dis)
	{
		int ans = 0;
		for(int i=0;i<dis.length;i++)
		{
			if(i == 0)
			{
				ans = i;
			}
			if(i != 0 && dis[i]<dis[ans])
			{
				ans = i;
			}
		}
		return ans;
	}
	
	//*****新群心點取代舊群心點*****//
	public static void reflashcenterpoint()
	{
		int rsum,gsum,bsum,count,n;
		//*****各組分別計算RGB值總合*****//
		for(int i=0;i<value.cb;i++)
		{
			rsum = 0;
			gsum = 0;
			bsum = 0;
			count = 0;
			n = 0;
			for(int x=0; x<loadfile.image.getWidth();x++)
			{
				for(int y=0; y<loadfile.image.getHeight();y++)
				{
					if(value.group[count] == i)
					{
						rsum += rgbdistance("r",x,y);
						gsum += rgbdistance("g",x,y);
						bsum += rgbdistance("b",x,y);
						n++;
					}
					count++;
				}
			}
			if(n != 0)
			{
				value.centerr[i] = rsum/n;
				value.centerg[i] = gsum/n;
				value.centerb[i] = bsum/n;
			}
		}
	}
	
	//*****群心點顏色取代該群內每點顏色*****//
	public static void replace()
	{
		for(int i=0;i<value.cb;i++)
		{
			int count = 0;
			for(int x=0; x<loadfile.image.getWidth();x++)
			{
				for(int y=0; y<loadfile.image.getHeight();y++)
				{
					if(value.group[count] == i)
					{
						drawpanel.drawcolor(x, y, value.centerr[i], value.centerg[i], value.centerb[i]);
					}
					count++;
				}
			}
		}
	}
}
