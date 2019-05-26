package organicsUtil;
import java.util.HashMap;
public class MoleProperty {
	
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
	
}
