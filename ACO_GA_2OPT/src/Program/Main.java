package Program;

import IO.Drawpanel;
import InterFace.View;
import Value.parameter;

public class Main {
		
		static long time_start;//運行開始時間
		public static double costtime;
		
		public static void main()
		{
			//*****儲存演算法執行時間*****//
			time_start = System.currentTimeMillis();
			
			//*****Genetic Algorithm*****//
			parameter.opt = false;
			GA.GeneticAlgorithm();
			
			//*****Ant Colony Optimization*****//
			parameter.opt = true;
			parameter.ACOiteration = parameter.iteration;
			ACO.antcolonyoptimization();
			
			//*****計算所花費時間並輸出*****//
			costtime = ((double)System.currentTimeMillis()-(double)time_start)/1000;
			
			
			//*****輸出路徑*****//
			if(View.iscomputerrun.isSelected())
			{
				if(View.runtime == View.runcount)
				{
					for(int i=0;i<ACO.bestpath.length-1;i++)
					{
						Drawpanel.drawline(ACO.bestpath[i], ACO.bestpath[i+1]);
					}
					View.time_output.setText("執行時間："+String.valueOf(costtime));
					View.cost_output.setText("花費距離："+ACO.bestdistance);
				}
			}
			else
			{
				for(int i=0;i<ACO.bestpath.length-1;i++)
				{
					Drawpanel.drawline(ACO.bestpath[i], ACO.bestpath[i+1]);
				}
				View.time_output.setText("執行時間："+String.valueOf(costtime));
				View.cost_output.setText("花費距離："+ACO.bestdistance);
			}
		}
}
