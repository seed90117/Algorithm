package Program;

import java.util.Random;

import Value.parameter;

public class GA {

	static int[][] parent_poola;
	static int[][] parent_poolb;
	static double[] fitness;
	static int[][] select_poola;
	static int[][] select_poolb;
	static Random random = new Random();
	public static double bestA;
	public static double bestB;
	public static double bestdis;
	
	public static void GeneticAlgorithm()
	{
		initial();//初始化母池
		for(int i=0;i<parameter.GAiteration;i++)
		{
			System.out.println("第"+i+"代");
			select();//基因放入選擇池
			crossover();//交配
			mutation();//突變
			updateparent();//更新母池
			//System.out.println("A: "+bestA+"   B: "+bestB);
		}
		parameter.A = bestA;
		parameter.B = bestB;
	}
	
	//*****初始化*****//
	public static void initial()
	{
		parent_poola = new int[parameter.gene][11];
		parent_poolb = new int[parameter.gene][11];
		fitness = new double[parameter.gene];
		select_poola = new int[parameter.gene][11];
		select_poolb = new int[parameter.gene][11];
		double tmp1,tmp2;
		for(int i=0;i<parent_poola.length;i++)
		{
			tmp1 = (double)((int)(random.nextDouble()*parameter.range*100))/100;
			tmp2 = (double)((int)(random.nextDouble()*parameter.range*100))/100;
			parent_poola[i] = decomaltobinary(tmp1*100);
			parent_poolb[i] = decomaltobinary(tmp2*100);
			parameter.A = tmp1;//設定a值
			parameter.B = tmp2;//設定β值
			parameter.ACOiteration = parameter.iteration/5;
			ACO.antcolonyoptimization();//執行ACO
			fitness[i] = ACO.bestdistance;//取得ACO最佳路徑
		}
	}
	
	//*****放入選擇池*****//
	public static void select()
	{
		int tmp1,tmp2;
		for(int i=0;i<parameter.gene;i++)
		{
			do {
				tmp1 = random.nextInt(parameter.gene);
				tmp2 = random.nextInt(parameter.gene);
			} while (tmp1 == tmp2);
			
			if(fitness[tmp1] > fitness[tmp2])
			{
				select_poola[i] = parent_poola[tmp1];
				select_poolb[i] = parent_poolb[tmp1];
			}
			else
			{
				select_poola[i] = parent_poola[tmp2];
				select_poolb[i] = parent_poolb[tmp2];
			}
		}
	}
	
	//*****交配*****//
	public static void crossover()
	{
		int tmp1,tmp2;
		for(int i=0;i<select_poola.length/2;i++)
		{
			do {
				tmp1 = random.nextInt(select_poola.length);
				tmp2 = random.nextInt(select_poolb.length);
			} while (tmp1 == tmp2);
			
			if(random.nextDouble() <= parameter.crossoverrate)
			{
				int tmp = random.nextInt(select_poola[tmp1].length);
				for(int j=tmp;j<select_poola[tmp1].length;j++)
				{
					int t = select_poola[tmp1][j];
					select_poola[tmp1][j] = select_poola[tmp2][j];
					select_poola[tmp2][j] = t;
					
					t = select_poolb[tmp1][j];
					select_poolb[tmp1][j] = select_poolb[tmp2][j];
					select_poolb[tmp2][j] = t;
				}
			}
		}
	}
	
	//*****突變*****//
	public static void mutation()
	{
		for(int i=0;i<select_poola.length;i++)
		{
			if(random.nextDouble() <= parameter.mutationrate)
			{
				int tmp = random.nextInt(select_poola[i].length);
				select_poola[i][tmp] = 1 - select_poola[i][tmp];
				select_poolb[i][tmp] = 1 - select_poolb[i][tmp];
			}
		}
	}
	
	//*****更新母池*****//
	public static void updateparent()
	{
		double tmp = -1;
		int bestindex = 0;
		for(int i=0;i<parameter.gene;i++)
		{
			parent_poola[i] = select_poola[i];
			parent_poolb[i] = select_poolb[i];
			parameter.A = binarytodecomal(select_poola[i]);//設定a值
			parameter.B = binarytodecomal(select_poolb[i]);//設定β值
			parameter.ACOiteration = parameter.iteration/5;
			ACO.antcolonyoptimization();//執行ACO
			fitness[i] = ACO.bestdistance;//取得ACO最佳路徑
			if(tmp != -1)
			{
				if(tmp > fitness[i])
				{
					tmp = fitness[i];
					bestindex = i;
				}
			}
			else
			{
				tmp = fitness[i];
				bestindex = i;
			}
			/*System.out.println("基因"+i);
			System.out.println("A:"+parameter.A);
			System.out.println("B:"+parameter.B);
			System.out.println("fitness:"+fitness[i]);
			System.out.println();*/
		}
		bestA = binarytodecomal(parent_poola[bestindex]);
		bestB = binarytodecomal(parent_poolb[bestindex]);
		bestdis = fitness[bestindex];
		System.out.println("Best A:"+bestA);
		System.out.println("Best B:"+bestB);
		System.out.println("Best fitness:"+fitness[bestindex]);
		System.out.println();
	}
	
	//*****十進制轉二進制*****//
	public static int[] decomaltobinary(double input)
	{
		int[] re = new int[11];
		double[] binary = {1024,512,256,128,64,32,16,8,4,2,1};
		for(int i=0;i<binary.length;i++)
		{
			double tmp = input - binary[i];
			if(tmp >= 0)
			{
				input -= binary[i];
				re[i] = 1;
			}
			else 
			{
				re[i] = 0;
			}
		}
		return re;
	}
	
	//*****二進制轉十進制*****//
	public static double binarytodecomal(int[] input)
	{
		double re = 0;
		double[] binary = {1024,512,256,128,64,32,16,8,4,2,1};
		for(int i=0;i<input.length;i++)
		{
			re += input[i]*binary[i];
		}
		return re/100;
	}
}
