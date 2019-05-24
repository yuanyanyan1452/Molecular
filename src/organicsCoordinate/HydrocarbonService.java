package organicsCoordinate;
import java.util.LinkedList;

import organicsController.InputMoleFormula;
import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.Mole;

/*
 * 只含碳氢的化合物，或者简称为烃
 * 分为烷烃烯烃炔烃芳香烃
 */
public class HydrocarbonService implements TransformService{
	public  LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		final double tetra=109.5;
		//烷烃
		if((cNumber*2+2)==hNumber) {
			//先确定碳链的位置
			for(int i=1;i<=cNumber;i++) {
				if(i==1)moles.add(new Mole("C1",0,0,0));
				else if(i%2==0) {//偶数序号的碳
					Mole temp=moles.get(i-1-1);
					moles.add(new Mole("C"+i,temp.x,0,temp.z+BondLen.CCSinLen));
				}else {
					Mole temp=moles.get(i-1-1);
					moles.add(new Mole("C"+i,temp.x-BondLen.CCSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CCSinLen*Counter.cos(180-tetra)));
				}
			}
			//再安上氢原子
			int hCount=1;//给氢原子标注序号使用的,每次安装完一个氢原子之后就要加一
			//算出所有的对称点
			LinkedList<Mole> centerMoles=new LinkedList<Mole>();
			for(int j=2;j<=cNumber;j+=2) {
				centerMoles.add(new Mole("center"+(j/2),-(j/2-1)*(Counter.sin(180-tetra)*BondLen.CCSinLen),0,moles.get(j-1).z-BondLen.CCSinLen/2));
			}
			for(int i=1;i<=cNumber;i++) {
				if(i==1) {
					moles.add(new Mole("H"+(hCount++),Counter.sin(180-tetra)*BondLen.CHSinLen,0,-BondLen.CHSinLen*Counter.cos(180-tetra)));
					moles.add(new Mole("H"+(hCount++),-Counter.sin(30)*moles.get(moles.size()-1).x,Counter.cos(30)*moles.get(moles.size()-1).x,moles.get(moles.size()-1).z));
					moles.add(new Mole("H"+(hCount++),moles.get(moles.size()-1).x,-moles.get(moles.size()-1).y,moles.get(moles.size()-1).z));
					if(i==cNumber) {
						Mole temp=moles.get(i-1);
						moles.add(new Mole("H"+hCount,temp.x,temp.y,temp.z+BondLen.CHSinLen));
					}
				}else if(i%2==0) {
					int size=moles.size();
					for(int j=0;j<2;j++) {
						Mole temp=new Mole("H"+(hCount++));
						SymmetryMoleService.symmetry(moles.get(size-1-j), centerMoles.get(i/2-1), temp);
						moles.add(temp);
					}
					if(i==cNumber) {
						Mole temp=moles.get(i-1);
						moles.add(new Mole("H"+(hCount),temp.x-BondLen.CHSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CHSinLen*Counter.cos(180-tetra)));
					}
				}else {
					Mole tempc=moles.get(i-1);
					for(int j=0;j<2;j++) {
						Mole temph=moles.get(cNumber+1+j);
						moles.add(new Mole("H"+(hCount++),tempc.x+temph.x,tempc.y+temph.y,tempc.z+temph.z));
					}
					if(i==cNumber) {
						Mole temp=moles.get(i-1);
						moles.add(new Mole("H"+hCount,temp.x,temp.y,temp.z+BondLen.CHSinLen));
					}
				}
			}
		}else if(cNumber*2==hNumber) {//一烯烃 
		}else if((cNumber*2-2)==hNumber) {//一炔烃
		}else if((cNumber*2-6)==hNumber&&cNumber>=6) {
		}
		return moles;
	}
	//test
	public static void main(String[] args) {
		HydrocarbonService h=new HydrocarbonService();
		LinkedList<Mole> moles=h.transformMoleFormula("CH4");
		for(int i=0;i<moles.size();i++)System.out.println(moles.get(i).toString());
	}
}
