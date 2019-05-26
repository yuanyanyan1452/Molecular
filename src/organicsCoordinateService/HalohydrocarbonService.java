package organicsCoordinateService;

import organicsUtil.HaloType;
import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

import java.util.LinkedList;
/*
 * 卤代烃
 * 烃中的氢原子被F、Cl、Br、I替代即氟氯溴碘代烃
 */
public class HalohydrocarbonService {
	public static LinkedList<Mole> transformMoleFormula(String moleFormula,HaloType haloType) {
		
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int xNumber=numbers.get(2);
		LinkedList<Mole> moles=new LinkedList<Mole>();
		if(cNumber==1&&hNumber==3&&xNumber==1) {//甲基卤
		}else if(cNumber*2+2==(hNumber+xNumber)) {//卤代烷烃
		}else if(cNumber*2==(hNumber+xNumber)) {//卤代烯烃
		}else if(cNumber*2-2==(hNumber+xNumber)) {//卤代炔烃
		}else if(cNumber*2-6==(hNumber+xNumber)) {//卤代芳香烃			
		}
		return moles;
		
	}

}
