package Program;

import java.util.Random;

import OutPut.DrawPanel;
import Value.value;

public class Main {

	//*****�ŧi�ܼ�*****//
	public static double[] x;//X軸值
	public static double[] y;//Y軸值
	public static int total;//總資料量
	public static int time;//代數
	public static int outputlayer;//輸出層
	public static double eps;//半徑
	public static double a;//縮小因素
	public static double n;//學習速率
	
	public static double[] oplx;
	public static double[] oply;
	static value[] opl;
	
	public static void main()
	{
		Random rd = new Random();
		//Set<Integer> checkpoint = new HashSet<>();
		//*****��l�ƿ�X�h*****//
		oplx = new double[outputlayer*outputlayer];
		oply = new double[outputlayer*outputlayer];
		opl = new value[outputlayer*outputlayer];
		for(int i=0;i<oplx.length;i++)//初始XY象限值
		{
			int c = rd.nextInt(total);
			/*while(checkpoint.add(c) == false)
			{
				c = rd.nextInt(total);
			}*/
			oplx[i] = x[c];
			oply[i] = y[c];
			
		}
		int count =0;
		for(int x=0;x<outputlayer;x++)//網路拓撲
		{
			for(int y=0;y<outputlayer;y++)
			{
				opl[count] = new value(); 
				opl[count].x = x;
				opl[count].y = y;
				count++;
			}
		}
		
		for(int t=0;t<time;t++)
		{
			//*****尋找優秀神經元*****//
			for(int xy=0;xy<total;xy++)
			{
				double[] dis = new double[oplx.length];
				for(int o=0;o<oplx.length;o++)
				{
					dis[o] = XYdistance(xy,o);
				}
				int best = minvalue(dis);//優秀神經元
				for(int o=0;o<oplx.length;o++)
				{
					double K = colse(TPdistance(best,o));
					double w1 = n*(x[xy]-oplx[o])*K;
					double w2 = n*(y[xy]-oply[o])*K;
					oplx[o] += w1;
					oply[o] += w2;
				}
			}
			
			//*****縮小半徑*****//
			eps = a*eps;
			
			//*****縮小學習速率*****//
			n = a*n;
			
			//*****輸出*****//
			outputline();
		}
		//outputline();
	}
	//*****輸出*****//
	public static void outputline()
	{
		DrawPanel.drawpanel();
		for(int i=0;i<outputlayer*outputlayer;i++)
		{
			for(int j=0;j<outputlayer*outputlayer;j++)
			{
				if(TPdistance(i,j) == 1)
				{
					
					DrawPanel.drawline(i, j);
				}
			}
		}
	}
	
	//*****鄰近函數*****//
	public static double colse(double dis)
	{
		double tmp = 0;
		tmp = Math.exp((-1)*Math.pow(dis/eps, 2));
		return tmp;
	}
	
	//*****XY距離公式*****//
	public static double XYdistance(int pointA, int pointB)
	{
		double re=0;
		re = Math.sqrt(Math.pow(x[pointA]-oplx[pointB], 2)+Math.pow(y[pointA]-oply[pointB], 2));
		return re;
	}
	
	//*****拓撲距離公式*****//
	public static double TPdistance(int pointA, int pointB)
	{
		double re=0;
		re = Math.sqrt(Math.pow(opl[pointA].x-opl[pointB].x, 2)+Math.pow(opl[pointA].y-opl[pointB].y, 2));
		return re;
	}
	
	//*****找出最小值*****//
	public static int minvalue(double[] dis)
	{
		int tmp = 0;
		for(int i=0;i<dis.length;i++)
		{
			if(dis[i]<dis[tmp])
			{
				tmp = i;
			}
		}
		return tmp;
	}
}
