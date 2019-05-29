package organicsUtil;
import java.util.HashMap;
public class MoleProperty {
	
	
	//原子的类型-该类型对应的原子半径大小
	public static HashMap<String,Double> sizeByType=new HashMap<String,Double>(){;
		private static final long serialVersionUID = 1L;
		{
			put("CL",9.9);
			put("H",3.7);
			put("O",7.4);
			put("F",7.1);
			put("C",7.7);
			put("BR",19.6);
			put("I",14.0);//溴原子呈-1价时的原子半径

		}
	};
	
	//原子的中文名-该中文名对应的分子式（对于一些复杂分子式即分子式的逻辑中未实现的需要特殊考虑）-有机物类型
	public static HashMap<String,String> nameByLocalName=new HashMap<String,String>(){
		private static final long serialVersionUID = 1L;
		{
			put("甲醛","CH2O 醛");
			put("乙醛","C2H4O 醛");
			put("丙醛","C3H6O 醛");
			put("丁醛","C4H8O 醛");
			
			put("丙烯醛","C3H4O 醛");
			put("丁烯醛","C4H6O 醛");
			
			put("苯甲醛","C7H6O 醛");
			put("苯乙醛","C8H8O 醛");
			put("苯丙醛","C9H10O 醛");
			
			put("乙醚","C2H6O 醚");
			put("丙醚","C3H8O 醚");
			put("丁醚","C4H10O 醚");
			
			put("乙基丙烯醚","C3H6O 醚");
			put("甲基丙炔醚","C3H4O 醚");
			put("苯甲醚","C7H8O 醚");
			put("苯乙醚","C8H10O 醚");
			
			put("甲酸","CH2O2 酸");
			put("乙酸","C2H4O2 酸");
			put("丙酸","C3H6O2 酸");
			put("丁酸","C4H8O2 酸");
			
			put("丙烯酸","C3H4O2 酸");
			put("丙炔酸","C3H2O2 酸");
			put("苯甲酸","C7H6O2 酸");
			put("苯乙酸","C8H8O2 酸");
			
			put("乙酸甲酯","C3H6O2 酯");
			put("乙酸乙酯","C4H8O2 酯");
			put("乙酸丙酯","C5H10O2 酯");
			put("乙酸甲酯","C3H6O2 酯");
			put("丙烯酸丁酯","C7H12O2 酯");
			put("乙酸苯酯","C8H8O2 酯");
			
			put("丙酮","C3H6O 酮");
			put("丁酮","C4H8O 酮");
			put("苯乙酮","C8H8O 酮");
			put("苯丁酮","C9H10O 酮");
			
			put("三氯甲烷","CHCl3 卤代烃");
			put("溴乙烷","C2H6Br 卤代烃");
			put("一氯丙烷","C3H8Cl 卤代烃");
			put("三氟甲烷","CHF3 卤代烃");
			
			put("甲烷","CH4 烃");
			put("乙烷","C2H6 烃");
			put("丙烷","C3H8 烃");
			put("丁烷","C4H10 烃");
			put("戊烷","C5H12 烃");
			put("己烷","C6H14 烃");
			put("庚烷","C7H16 烃");
			put("辛烷","C8H18 烃");
			
			put("乙烯","C2H4 烃");
			put("丙烯","C3H6 烃");
			put("丁烯","C4H8 烃");
			
			put("乙炔","C2H2 烃");
			put("丙炔","C3H4 烃");
			
			put("苯","C6H6 烃");
			put("甲苯","C7H8 烃");
			put("乙苯","C8H10 烃");
			
			put("乙醇","C2H6O 醇");
			put("丙醇","C3H8O 醇");
			put("丁醇","C4H10O 醇");
			put("乙烯醇","C2H4O 醇");
			put("丙烯醇","C3H6O 醇");
			put("丙炔醇","C3H4O 醇");
			
			
			
		}
	};
	
}
