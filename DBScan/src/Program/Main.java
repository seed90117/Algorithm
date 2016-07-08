package Program;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Output.DrawPanel;
import Value.value;

public class Main {

	static List<Integer> seedlist = new ArrayList<Integer>();
	static List<Integer> seed = new ArrayList<Integer>();
	static Set<Integer> point = new HashSet<Integer>();
	public static void main()
	{
		//****初始化群組*****//
		value.group = new int[value.total];
		for(int i=0;i<value.total;i++)
		{
			value.group[i] = -1;
		}
		
		//*****尋找種子點*****//
		findseedpoint();
		
		//*****輸出資料到畫布*****//
		DrawPanel.drawoutput();
	}
	
	//*****找種子點*****//
	public static void findseedpoint()
	{
		int count;
		int color = 0;
		for(int i=0;i<value.total;i++)
		{
			count = 0;
			for(int j=0;j<value.total;j++)
			{
				if(comparedistance(pointdistance(value.x[i],value.y[i],value.x[j],value.y[j])) && i != j)
				{
					count++;
					if(point.add(j) == true)
					{
						seed.add(j);
					}
				}
			}
			if(compareminpts(count) && value.group[i] == -1)
			{
				value.group[i] = color;
				seedlist.add(i);
				grouping(color);
				System.out.println(color);
				color++;
			}
			seed.clear();
			point.clear();
		}
		value.colornum = seedlist.size();
	}
	
	//*****計算兩點距離*****//
	public static double pointdistance(double x1,double y1,double x2,double y2)
	{
		return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
	}
	
	//*****比較距離小於等於eps*****//
	public static boolean comparedistance(double d)
	{
		if(d <= value.eps)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//*****比較圓內資料點是否小於等於minpts*****//
	public static boolean compareminpts(int n)
	{
		if(n >= value.minpts)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void grouping(int a)
	{
		int count;
		for(int i=0;i<seed.size();i++)
		{
			count = 0;
			List<Integer> tmp = new ArrayList<Integer>();
			for(int n=0;n<value.total;n++)
			{
				//*****判斷不與自己跟種子點重複*****//
				if(seed.get(i) != n && n != a && comparedistance(pointdistance(value.x[seed.get(i)],value.y[seed.get(i)],value.x[n],value.y[n])))
				{
					count++;
					if(point.add(n) == true)
					{
						tmp.add(n);
					}
				}
			}
			if(compareminpts(count))
			{
				seed.addAll(tmp);
				for(int j=0;j<seed.size();j++)
				{
					value.group[seed.get(j)] = a;
				}
				
			}
			else
			{
				point.clear();
				point.addAll(seed);
				tmp.clear();
			}
		}
	}
	
	
}
