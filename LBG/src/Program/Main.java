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
			grouping();//���s
			calculateMSE();//�p��MSE
			if((Math.abs(MSE-value.mse)/MSE) < value.th)//������e��
			{
				check = false;	
			}
			value.mse = MSE;//�x�s�s��MSE
			reflashcenterpoint();//���s�M��s���I
		}
		
		replace();//��X�Ϥ�
	}
	
	//*****�H�����͸s���I*****//
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
	
	//*****�N�s���I�����è��o�s���IRGB��*****//
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
	
	//*****���s*****//
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
	
	//*****MSE�p��*****//
	public static void calculateMSE()
	{
		double[] mse = new double[value.cb];
		double msesum = 0;
		//*****�U�դ��O�p��RGB���`�X*****//
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
		
		//*****�U��RGB�ȥ[�`*****//
		for(int i=0;i<value.cb;i++)
		{
			msesum += mse[i];
		}
		
		MSE = msesum/(loadfile.image.getHeight()*loadfile.image.getWidth());
	}
	
	//*****�p��RBG�Z��*****//
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
	
	//*****�p��RBG*****//
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
	
	//*****����Z���j�p*****//
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
	
	//*****�s�s���I���N�¸s���I*****//
	public static void reflashcenterpoint()
	{
		int rsum,gsum,bsum,count,n;
		//*****�U�դ��O�p��RGB���`�X*****//
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
	
	//*****�s���I�C����N�Ӹs���C�I�C��*****//
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
