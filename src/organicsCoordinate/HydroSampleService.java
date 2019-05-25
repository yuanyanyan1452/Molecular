package organicsCoordinate;
import java.util.LinkedList;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.Mole;

public class HydroSampleService {
	//碳的序号和氢的序号从后往前标注，第一个参数是这个sample需要创建的碳原子的个数
	public static LinkedList<Mole> hydroSampleService(int cNumber,int cSize,int hSize){
		final double tetra=109.5;
		LinkedList<Mole> moles=new LinkedList<Mole>();
		//先确定碳链的位置
		for(int i=1;i<=cNumber;i++) {
			if(i==1)moles.add(new Mole("C"+(cSize--),0,0,0));
			else if(i%2==0) {//偶数序号的碳
				Mole temp=moles.get(i-1-1);
				moles.add(new Mole("C"+(cSize--),temp.x,0,temp.z+BondLen.CCSinLen));
			}else {
				Mole temp=moles.get(i-1-1);
				moles.add(new Mole("C"+(cSize--),temp.x-BondLen.CCSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CCSinLen*Counter.cos(180-tetra)));
			}
		}
		//再安上氢原子
		//算出所有的对称点
		LinkedList<Mole> centerMoles=new LinkedList<Mole>();
		for(int j=2;j<=cNumber;j+=2) {
			centerMoles.add(new Mole("center"+(j/2),-(j/2-1)*(Counter.sin(180-tetra)*BondLen.CCSinLen),0,moles.get(j-1).z-BondLen.CCSinLen/2));
		}
		for(int i=1;i<=cNumber;i++) {
			if(i==1) {
				moles.add(new Mole("H"+(hSize--),Counter.sin(180-tetra)*BondLen.CHSinLen,0,-BondLen.CHSinLen*Counter.cos(180-tetra)));
				if(i==cNumber)return moles;
				moles.add(new Mole("H"+(hSize--),-Counter.sin(30)*moles.get(moles.size()-1).x,Counter.cos(30)*moles.get(moles.size()-1).x,moles.get(moles.size()-1).z));
				moles.add(new Mole("H"+(hSize--),moles.get(moles.size()-1).x,-moles.get(moles.size()-1).y,moles.get(moles.size()-1).z));
				if(i==cNumber) {
					Mole temp=moles.get(i-1);
					moles.add(new Mole("H"+hSize,temp.x,temp.y,temp.z+BondLen.CHSinLen));
				}
			}else if(i%2==0) {
				if(i==cNumber) {
					return moles;
				}else {
					int size=moles.size();
					for(int j=0;j<2;j++) {
						Mole temp=new Mole("H"+(hSize--));
						SymmetryMoleService.symmetry(moles.get(size-1-j), centerMoles.get(i/2-1), temp);
						moles.add(temp);
					}
				}
			}else {
				if(i==cNumber) {
					return moles;
				}
				Mole tempc=moles.get(i-1);
				for(int j=0;j<2;j++) {
					Mole temph=moles.get(cNumber+1+j);
					moles.add(new Mole("H"+(hSize--),tempc.x+temph.x,tempc.y+temph.y,tempc.z+temph.z));
				}
				
			}
		}
		return moles;
	}
}
