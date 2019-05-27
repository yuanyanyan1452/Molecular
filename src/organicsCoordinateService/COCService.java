package organicsCoordinateService;

import java.util.HashMap;
import java.util.LinkedList;

import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

/*
 * 醚
 */
public class COCService implements TransformService{

	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Integer>numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		if(oNumber==1&&cNumber>=2) {
			if((cNumber*2+2)==hNumber) {
				moles=HydroSampleServiceRight.hydroSampleService(cNumber-1, cNumber, hNumber);
				HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
				for(Mole m:moles) {
					molesMap.put(m.name, m);
				}
				if((cNumber-1)%2==0) {
					
				}else {
					
				}
			}else if(cNumber*2==hNumber) {
			}else if((cNumber*2-2)==hNumber) {
			}
		}
		return moles;
	}

}
