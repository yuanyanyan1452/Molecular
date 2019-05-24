package organicsCoordinate;

import java.util.LinkedList;

import organicsController.InputMoleFormula;
import organicsUtil.Mole;

/*
 * 酯
 */
public class COOService implements TransformService{

	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2) {
			if(cNumber*2==hNumber&&cNumber>=3) {
			}else if((cNumber*2-2)==hNumber&&cNumber>=4) {
			}else if((cNumber*2-4)==hNumber&&cNumber>=4) {
			}else if((cNumber*2-8)==hNumber&&cNumber>=8) {
			}
		}
		
		return moles;
	}
	
}
