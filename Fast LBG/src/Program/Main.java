package Program;

import java.util.Random;

import IO.Drawpanel;
import IO.Loadfile;
import Interface.View;
import Value.data;
import Value.rgb;

public class Main {

	//*****儲存突變資料變數*****//
	public static data imagedata[];
	
	//*****演算法參數*****//
	public static double threshold;
	public static int codebook;
	static Random random = new Random();
	static int checkpoint[];
	static rgb codebookpoint[];
	static int totalpoint;
	static boolean run = true;
	static double MSE = 0;//舊
	static double mse = 0;//新
	
	public static void main()
	{	
		//*****初始化參數*****//
		codebook = Integer.parseInt(View.codebook_input.getSelectedItem().toString());
		codebookpoint = new rgb[codebook];
		totalpoint = Loadfile.image.getWidth()*Loadfile.image.getHeight();
		checkpoint = new int[totalpoint];
		
		//*****找尋初始群星點*****//
		findseedpoint();
		
		//*****將每一點分群*****//
		grouppoint();
		
		//*****計算MSE*****//
		calculateMSE(true);
		
		while(run)
		{
			if((MSE-mse)<=threshold)//是否低於門檻值
			{
				run = false;
			}
			else 
			{
				MSE = mse;//更新MSE
				nextseedpoint();//尋找新群心點
				grouppoint();//分群
			}
		}
		outputimage();//輸出壓縮後圖片
	}
	
	//*****找尋初始群星點*****//
	public static void findseedpoint()
	{
		int num;
		boolean check;
		
		for(int i=0;i<codebook;i++)
		{
			num = random.nextInt(totalpoint);
			check = true;
			while(check)
			{
				if(checkpoint[num] == 1)
				{
					num = random.nextInt(totalpoint);
				}
				else
				{
					codebookpoint[i] = new rgb();
					codebookpoint[i].R = imagedata[num].R;
					codebookpoint[i].G = imagedata[num].G;
					codebookpoint[i].B = imagedata[num].B;
					checkpoint[num] = 1;
					check = false;
				}
			}
		}
	}
	
	//*****計算新的群星點*****//
	public static void nextseedpoint()
	{
		int count[] = new int[codebook];
		double tmppointr[] = new double[codebook];
		double tmppointg[] = new double[codebook];
		double tmppointb[] = new double[codebook];
		
		//*****加總所有群組內點RGB值*****//
		for(int i=0;i<totalpoint;i++)//所有點
		{
			tmppointr[imagedata[i].group] += imagedata[i].R;
			tmppointg[imagedata[i].group] += imagedata[i].G;
			tmppointb[imagedata[i].group] += imagedata[i].B;
			count[imagedata[i].group]++;
		}
		
		//*****新的群心點*****//
		for(int i=0;i<codebook;i++)
		{
			codebookpoint[i].R = (int)(tmppointr[i]/count[i]);
			codebookpoint[i].G = (int)(tmppointg[i]/count[i]);
			codebookpoint[i].B = (int)(tmppointb[i]/count[i]);
		}
	}
	
	//*****將每一點分群*****//
	public static void grouppoint()
	{
		mse = 0;
		for(int i=0;i<totalpoint;i++)//所有點
		{
			double mindis = 0;
			int minindex = 0;
			
			for(int j=0;j<codebook;j++)//群心點
			{
				//*****計算RBG距離*****//
				double tmp = rgbdistance(imagedata[i].R, imagedata[i].G, imagedata[i].B, codebookpoint[j].R, codebookpoint[j].G, codebookpoint[j].B);
				if(j == 0)//第一次執行預設值
				{
					mindis = tmp;
					minindex = j;
				}
				else
				{
					if(mindis > tmp)
					{
						mindis = tmp;
						minindex = j;
					}
				}
			}
			imagedata[i].group = minindex;//第i個點分群
			
			//*****計算MSE*****//
			mse += rgbdistance(imagedata[i].R, imagedata[i].G, imagedata[i].B, imagedata[minindex].R, imagedata[minindex].G, imagedata[minindex].B);
		}
	}
	
	//*****計算MSE*****//
	public static void calculateMSE(boolean type)
	{
		double tmpmse =0;
		for(int i=0;i<totalpoint;i++)//所有點
		{
			tmpmse += rgbdistance(imagedata[i].R, imagedata[i].G, imagedata[i].B, codebookpoint[imagedata[i].group].R, codebookpoint[imagedata[i].group].G, codebookpoint[imagedata[i].group].B);
		}
		if(type)//初始MSE
		{
			MSE = tmpmse/totalpoint;
		}
		else 
		{
			mse = tmpmse/totalpoint;
		}
	}
	
	//*****輸出壓縮圖案*****//
	public static void outputimage()
	{
		for(int i=0;i<totalpoint;i++)//所有點
		{
			Drawpanel.drawcolor(imagedata[i].X, imagedata[i].Y, codebookpoint[imagedata[i].group].R, codebookpoint[imagedata[i].group].G, codebookpoint[imagedata[i].group].B);
		}
	}
	
	//*****計算RGB距離*****//
	public static double rgbdistance(int r1,int b1,int g1,int r2,int b2,int g2)
	{
		return ((r1-r2)*(r1-r2))+((g1-g2)*(g1-g2))+((b1-b2)*(b1-b2));
	}
}
