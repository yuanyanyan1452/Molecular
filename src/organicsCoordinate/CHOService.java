package organicsCoordinate;

import java.util.LinkedList;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

/*
 * 一元醛
 * 二元醛
 */
public class CHOService implements TransformService{
	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		final double tetra=109.5;
		//脂肪族的醛
		LinkedList<Integer> list=InputMoleFormula.getNumber(moleFormula);
		LinkedList<Mole> moles=new LinkedList<Mole>();
		int cNumber=list.get(0);
		int hNumber=list.get(1);
		int oNumber=list.get(2);
		if(oNumber==1) {//一元醛
		if(cNumber*2==hNumber) {//该醛基连烷烃
			moles.addAll(HydroSampleService.hydroSampleService(cNumber, cNumber, hNumber));
			if((cNumber-1)%2==1) {
				Mole temp=moles.get(cNumber-1);
				moles.add(new Mole("O1",temp.x-Counter.sin(tetra-60)*BondLen.CODouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CODouLen));
				moles.add(new Mole("H1",temp.x-Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CHSinLen));
			}else {
				Mole temp=moles.get(cNumber-1);
				moles.add(new Mole("O1",temp.x-Math.sqrt(3)/2*BondLen.CODouLen,0,temp.z+BondLen.CODouLen/2));
				moles.add(new Mole("H1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
			}
		}else if((cNumber*2)-2==hNumber&&cNumber>=3) {//该醛基连烯烃
			
		}else if((cNumber*2-4)==hNumber&&cNumber>=3) {//该醛基连炔烃
		}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//芳香酚(一个苯环一个醛基)//该醛基连苯环
		}
		}else if(oNumber==2&&cNumber>=2) {//二元醛
			if((cNumber*2-2)==hNumber) {//中间连烷烃
			}else if((cNumber*2-4)==hNumber&&cNumber>=4) {//中间连烯烃
			}else if((cNumber*2-6)==hNumber&&cNumber>=4) {//中间连炔烃
			}else if((cNumber*2-10)==hNumber&&cNumber>=8) {////中间连苯环
			}
		}
		return moles;
	}
	
}
