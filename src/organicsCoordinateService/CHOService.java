package organicsCoordinateService;

import java.util.HashMap;
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
		int cNumber=list.get(0);
		int hNumber=list.get(1);
		int oNumber=list.get(2);
		LinkedList<Mole> moles=new LinkedList<Mole>();
		if(oNumber==1) {//一元醛
		if(cNumber*2==hNumber) {//该醛基连烷烃
			moles.addAll(HydroSampleServiceRight.hydroSampleService(cNumber, cNumber, hNumber));
			if(cNumber%2==1) {
				Mole temp=moles.get(cNumber-1);
				moles.add(new Mole("O1",temp.x-Counter.sin(tetra-60)*BondLen.CODouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CODouLen));
				moles.add(new Mole("H1",temp.x-Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CHSinLen));
			}else {
				Mole temp=moles.get(cNumber-1);
				moles.add(new Mole("O1",temp.x-Math.sqrt(3)/2*BondLen.CODouLen,0,temp.z+BondLen.CODouLen/2));
				moles.add(new Mole("H1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
			}
		}else if((cNumber*2)-2==hNumber&&cNumber>=3) {//该醛基连烯烃
			HashMap<String,Mole> molesMap=HydroSampleServiceLeft.hydroSampleService(cNumber-1);
			molesMap.remove("H"+(2*cNumber));
			molesMap.remove("H"+(2*cNumber-1));
			molesMap.remove("H"+(2*cNumber-2));
			molesMap.put("c1", new Mole("c1",Math.sqrt(3)/2*BondLen.CCDouLen,0,-BondLen.CCDouLen/2));
			Mole temp=molesMap.get("c1");
			molesMap.replace("H1", new Mole("H1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
			molesMap.replace("H2",new Mole("H2",temp.x,0,temp.z-BondLen.CHSinLen));
			molesMap.replace("H3", new Mole("H3",-Math.sqrt(3)/2*BondLen.CHSinLen,0,-BondLen.CHSinLen/2));
			if((cNumber-1)%2==1) {
				temp=molesMap.get("C"+(cNumber-1));
				molesMap.put("o1",new Mole("o1",temp.x-Counter.sin(tetra-60)*BondLen.CODouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CODouLen));
				molesMap.put("h1",new Mole("h1",temp.x-Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CHSinLen));
			}else {
				temp=molesMap.get("C"+(cNumber-1));
				molesMap.put("o1",new Mole("o1",temp.x-Math.sqrt(3)/2*BondLen.CODouLen,0,temp.z+BondLen.CODouLen/2));
				molesMap.put("h1",new Mole("h1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
			}
			moles.addAll(molesMap.values());
		}else if((cNumber*2-4)==hNumber&&cNumber>=3) {//该醛基连炔烃
			HashMap<String,Mole> molesMap=HydroSampleServiceLeft.hydroSampleService(cNumber-1);
			molesMap.remove("H"+(2*cNumber));
			molesMap.remove("H"+(2*cNumber-1));
			molesMap.remove("H"+(2*cNumber-2));
			molesMap.remove("H2");
			molesMap.remove("H3");
			molesMap.put("c1", new Mole("c1",0,0,-BondLen.CCTriLen));
			Mole temp=molesMap.get("c1");
			molesMap.replace("H1", new Mole("H1",temp.x,0,temp.z-BondLen.CHSinLen));
			if((cNumber-1)%2==1) {
				temp=molesMap.get("C"+(cNumber-1));
				molesMap.put("o1",new Mole("o1",temp.x-Counter.sin(tetra-60)*BondLen.CODouLen,0,temp.z-Counter.cos(tetra-60)*BondLen.CODouLen));
				molesMap.put("h1",new Mole("h1",temp.x-Counter.sin(120-tetra)*BondLen.CHSinLen,0,temp.z+Counter.cos(120-tetra)*BondLen.CHSinLen));
			}else {
				temp=molesMap.get("C"+(cNumber-1));
				molesMap.put("o1",new Mole("o1",temp.x-Math.sqrt(3)/2*BondLen.CODouLen,0,temp.z+BondLen.CODouLen/2));
				molesMap.put("h1",new Mole("h1",temp.x+Math.sqrt(3)/2*BondLen.CHSinLen,0,temp.z+BondLen.CHSinLen/2));
			}
			moles.addAll(molesMap.values());
		}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//芳香酚(一个苯环一个醛基)//该醛基连苯环
			HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
			HydrocarbonService hs=new HydrocarbonService();
			moles=hs.transformMoleFormula("C"+cNumber+"H"+(2*cNumber-6));
			for(Mole m:moles) {
				molesMap.put(m.name, m);
			}
			molesMap.remove("H"+(2*cNumber-6));
			molesMap.remove("H"+(2*cNumber-7));
			molesMap.remove("H"+(2*cNumber-8));
			molesMap.put("o1", new Mole("o1",Math.sqrt(3)/2*BondLen.CODouLen,0,-BondLen.CODouLen/2));
			molesMap.put("h1", new Mole("h1",-Math.sqrt(3)/2*BondLen.CHSinLen,0,-BondLen.CHSinLen/2));
			moles.clear();
			moles.addAll(molesMap.values());
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
