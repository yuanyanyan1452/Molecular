package organicsCoordinateService;

import java.util.LinkedList;

import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

/*
 * 酸
 */
public class COOHService implements TransformService{

	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==2) {
			if(cNumber*2==hNumber) {
			}else if((cNumber*2-2)==hNumber) {
			}else if((cNumber*2-4)==hNumber) {
			}else if((cNumber*2-8)==hNumber&&cNumber>=7) {
			}
		}
		return moles;
	}

}
