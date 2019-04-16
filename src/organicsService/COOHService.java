package organicsService;

import java.util.LinkedList;

public class COOHService implements TransformService{

	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2) {
			if(cNumber*2==hNumber&&cNumber>=3) {
				
			}
		}
		
		return bonds;
	}

}
