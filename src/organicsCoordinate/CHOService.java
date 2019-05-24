package organicsCoordinate;

import java.util.LinkedList;

import organicsController.InputMoleFormula;
import organicsUtil.BondLen;
import organicsUtil.Counter;
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
			if(cNumber==1) {//甲醛
				moles.add(new Mole("c1",0,0,0));
				moles.add(new Mole("o1",0,BondLen.CODouLen,0));
				moles.add(new Mole("H1",-(Counter.cos(30)*BondLen.CHSinLen),-(Counter.sin(30)*BondLen.CHSinLen),0));
				moles.add(new Mole("h1",-moles.get(2).x,moles.get(2).y,0));
			}else{
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						moles.add(new Mole("C1",0,0,0));
						moles.add(new Mole("H1",Counter.sin(180-tetra)*BondLen.CHSinLen,0,-BondLen.CHSinLen*Counter.cos(180-tetra)));
						moles.add(new Mole("H2",-Counter.sin(30)*moles.get(1).x,Counter.cos(30)*moles.get(1).x,moles.get(1).z));
						moles.add(new Mole("H3",moles.get(2).x,-moles.get(2).y,moles.get(1).z));
					}else if(i==2&&i==cNumber) {
						moles.add(new Mole("c1",0,0,BondLen.CCSinLen));
						moles.add(new Mole("o1",Counter.cos(30)*BondLen.CODouLen,0,BondLen.CCSinLen+BondLen.CODouLen*Counter.sin(30)));
						moles.add(new Mole("h1",-moles.get(5).x,0,moles.get(5).z));
					}else if(i==2&&3==cNumber) {
						moles.add(new Mole("C2",0,0,BondLen.CCSinLen));
						Mole center=new Mole("center",0,0,BondLen.CCSinLen/2);
						for(int j=0;j<3;j++) {
							if(j==0) {
								Mole moleA=moles.get(j+1);
								Mole centerVec=new Mole("centerVec");
								SymmetryMoleService.symmetry(moleA,center,centerVec);
								Mole moleB=new Mole("c1");
								VectorDisMoleService.vectorDisMole(moles.get(4), centerVec, BondLen.CCSinLen-BondLen.CHSinLen, moleB);
								moles.add(moleB);
							}else {
								Mole moleA=moles.get(j+1);
								Mole moleB=new Mole("H"+(3+j+1));
								SymmetryMoleService.symmetry(moleA,center,moleB);
								moles.add(moleB);
							}
						}
						moles.add(new Mole("o1",-Counter.cos(60)*BondLen.CODouLen+moles.get(5).x,0,moles.get(5).z+BondLen.CODouLen*Counter.sin(60)));
						moles.add(new Mole("h1",moles.get(8).x,0,moles.get(7).z-BondLen.CODouLen*Counter.sin(60)));
					}else {
						
					}
				}
				
				
				
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
