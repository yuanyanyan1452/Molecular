package organicsCoordinateService;
import java.util.HashMap;
import java.util.LinkedList;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.InputMoleFormula;
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
			HashMap<String,Mole> molesMap=HydroSampleServiceLeft.hydroSampleService(cNumber);
			moles.addAll(molesMap.values());
			return moles;
		}else if(cNumber*2==hNumber) {//一烯烃 
			moles.addAll(HydroSampleServiceRight.hydroSampleService(cNumber-1,cNumber,hNumber));
			if((cNumber-1)%2==1) {
				Mole temp=moles.get(cNumber-1-1);
				moles.add(new Mole("C1",temp.x-Counter.sin(tetra-60)*BondLen.CCDouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CCDouLen));
				moles.add(new Mole("H3",temp.x-Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CHSinLen));
				temp=moles.get(moles.size()-2);
				moles.add(new Mole("H2",temp.x-Counter.sin(180-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(180-tetra)*BondLen.CHSinLen));
				moles.add(new Mole("H1",temp.x+Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z-Counter.cos(120-tetra)*BondLen.CHSinLen));
			}else {
				Mole temp=moles.get(cNumber-1-1);
				moles.add(new Mole("C1",temp.x-Math.sqrt(3)/2*BondLen.CCDouLen,0,temp.z+BondLen.CCDouLen/2));
				moles.add(new Mole("H3",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
				temp=moles.get(moles.size()-1-1);
				moles.add(new Mole("H2",temp.x,0,temp.z+BondLen.CHSinLen));
				moles.add(new Mole("H1",temp.x-Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z-BondLen.CHSinLen/2));
			}
		}else if((cNumber*2-2)==hNumber) {//一炔烃
			moles.addAll(HydroSampleServiceRight.hydroSampleService(cNumber-1, cNumber, hNumber));
			if((cNumber-1)%2==1) {
				Mole temp=moles.get(cNumber-1-1);
				moles.add(new Mole("C1",temp.x-BondLen.CCTriLen*Counter.sin(180-tetra),0,temp.z+BondLen.CCTriLen*Counter.cos(180-tetra)));
				temp=moles.get(moles.size()-1);
				moles.add(new Mole("H1",temp.x-BondLen.CHSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CHSinLen*Counter.cos(180-tetra)));
			}else {
				Mole temp=moles.get(cNumber-1-1);
				moles.add(new Mole("C1",temp.x,0,temp.z+BondLen.CCTriLen));
				temp=moles.get(moles.size()-1);
				moles.add(new Mole("H1",temp.x,0,temp.z+BondLen.CHSinLen));
			}
		}else if((cNumber*2-6)==hNumber&&cNumber>=6) {//芳香烃
			moles.addAll(HydroSampleServiceRight.hydroSampleService(cNumber-5, cNumber, hNumber));
			if((cNumber-5)%2==1) {
				Mole temp=moles.get(cNumber-5-1);//C6
				Mole c6=temp;
				moles.add(new Mole("C5",temp.x-Counter.sin(tetra-60)*BondLen.CCDouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CCDouLen));
				moles.add(new Mole("C1",temp.x-Counter.sin(120-tetra)*BondLen.CCSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CCSinLen));
				temp=moles.get(moles.size()-1-1);//C5
				moles.add(new Mole("C4",temp.x-Counter.sin(180-tetra)*BondLen.CCSinLen,0,temp.z+Counter.cos(180-tetra)*BondLen.CCSinLen));
				moles.add(new Mole("H5",temp.x+Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z-Counter.cos(120-tetra)*BondLen.CHSinLen));
				temp=moles.get(moles.size()-1-1);//C4
				moles.add(new Mole("C3",temp.x-Counter.sin(120-tetra)*BondLen.CCDouLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CCDouLen));
				moles.add(new Mole("H4",temp.x-Counter.sin(tetra-60)*BondLen.CHSinLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CHSinLen));
				temp=moles.get(moles.size()-1-1);//C3
				moles.add(new Mole("C2",temp.x+BondLen.CCSinLen*Counter.sin(tetra-60),0,temp.z+BondLen.CCSinLen*Counter.cos(tetra-60)));
				moles.add(new Mole("H3",temp.x-BondLen.CHSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CHSinLen*Counter.cos(180-tetra)));
				temp=moles.get(moles.size()-1-1);//C2
				moles.add(new Mole("H2",temp.x-BondLen.CHSinLen*Counter.sin(120-tetra),0,temp.z+BondLen.CHSinLen*Counter.cos(120-tetra)));
				double x=BondLen.CHSinLen*Counter.sin(tetra-60)-BondLen.CCDouLen*Counter.sin(120-tetra);
				double z=BondLen.CCDouLen*Counter.cos(120-tetra)+BondLen.CHSinLen*Counter.cos(tetra-60);
				moles.add(new Mole("H1",c6.x+x,0,c6.z+z));
			}else {
				Mole temp=moles.get(cNumber-5-1);//C6
				moles.add(new Mole("C5",temp.x-Math.sqrt(3)/2*BondLen.CCDouLen,0,temp.z+BondLen.CCDouLen/2));
				moles.add(new Mole("C1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
				temp=moles.get(moles.size()-1);//C1
				Mole temp2=moles.get(moles.size()-1-1);//C5
				moles.add(new Mole("H1",temp.x+BondLen.CHSinLen/2,0,temp.z-BondLen.CHSinLen/2*Math.sqrt(3)));
				moles.add(new Mole("C4",temp2.x,0,temp2.z+BondLen.CCSinLen));
				moles.add(new Mole("H5",temp2.x-BondLen.CHSinLen/2*Math.sqrt(3),0,temp2.z-BondLen.CHSinLen/2));
				temp=moles.get(moles.size()-1-1);//C4
				moles.add(new Mole("C3",temp.x+BondLen.CCDouLen/2*Math.sqrt(3),0,temp.z+BondLen.CCDouLen/2));
				moles.add(new Mole("H4",temp.x-BondLen.CHSinLen/2*Math.sqrt(3),0,temp.z+BondLen.CHSinLen/2));
				temp=moles.get(moles.size()-1-1);//C3
				moles.add(new Mole("C2",temp.x+BondLen.CCSinLen/2*Math.sqrt(3),0,temp.z-BondLen.CCSinLen/2));
				moles.add(new Mole("H3",temp.x,0,temp.z+BondLen.CHSinLen));
				temp=moles.get(moles.size()-1-1);//C2
				moles.add(new Mole("H2",temp.x+BondLen.CHSinLen/2*Math.sqrt(3),0,temp.z+BondLen.CHSinLen/2));
			}
		}
		return moles;
	}
}
