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
	
	//原子的中文名-该中文名对应的分子式（对于一些复杂分子式即分子式的逻辑中未实现的需要特殊考虑）
	public static HashMap<String,String> nameByLocalName=new HashMap<String,String>(){
		private static final long serialVersionUID = 1L;
		{
			put("甲烷","CH4");
			put("乙烯","C2H4");
			put("乙炔","C2H2");
			put("苯","C6H6");
			put("苯酚","C6H6O");
			put("乙醛","C4H4O");
			put("乙酸","C2H4O2");
			put("溴乙烷","CH2Br");
			put("乙醇","C2H6O");
			put("乙醚","C2H5O");
			put("乙酸乙酯","C4H8O2");
			put("羧酸","C7H6O2");
			put("油酸","C18H34O2");
			put("甲酸","CH2O2");
			
		}
	};
	
}
