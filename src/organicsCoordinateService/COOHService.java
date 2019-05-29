package organicsCoordinateService;

import java.util.HashMap;
import java.util.LinkedList;

import organicsUtil.BondLen;
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
		HydrocarbonService hs=new HydrocarbonService();
		if(oNumber==2) {
			if(cNumber*2==hNumber) {//连烷烃
				HashMap<String,Mole> molesMap=HydroSampleServiceLeft.hydroSampleService(cNumber);
				molesMap.remove("H1");
				molesMap.remove("H2");
				molesMap.remove("H3");
				molesMap.put("O1", new Mole("O1",Math.sqrt(3)/2*BondLen.CODouLen,0,-BondLen.CODouLen/2));
				molesMap.put("O2", new Mole("O2",-Math.sqrt(3)/2*BondLen.COSinLen,0,-BondLen.COSinLen/2));
				Mole O2=molesMap.get("O2");
				molesMap.put("H1", new Mole("H1",O2.x+BondLen.OHSinLen/2,0,O2.z-Math.sqrt(3)/2*BondLen.OHSinLen));
				moles.addAll(molesMap.values());
			}else if((cNumber*2-2)==hNumber) {//连烯烃
				moles=hs.transformMoleFormula("C"+cNumber+"H"+(2*cNumber));
				HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
				for(Mole m:moles)molesMap.put(m.name, m);
				molesMap.remove("H"+(2*cNumber));
				molesMap.remove("H"+(2*cNumber-1));
				molesMap.remove("H"+(2*cNumber-2));
				molesMap.put("O1", new Mole("O1",Math.sqrt(3)/2*BondLen.CODouLen,0,-BondLen.CODouLen/2));
				molesMap.put("O2", new Mole("O2",-Math.sqrt(3)/2*BondLen.COSinLen,0,-BondLen.COSinLen/2));
				Mole O2=molesMap.get("O2");
				molesMap.put("h1", new Mole("h1",O2.x+BondLen.OHSinLen/2,0,O2.z-Math.sqrt(3)/2*BondLen.OHSinLen));
				moles.clear();
				moles.addAll(molesMap.values());
			}else if((cNumber*2-4)==hNumber) {//连炔烃
				moles=hs.transformMoleFormula("C"+cNumber+"H"+(2*cNumber-2));
				HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
				for(Mole m:moles)molesMap.put(m.name, m);
				molesMap.remove("H"+(2*cNumber-2));
				molesMap.remove("H"+(2*cNumber-3));
				molesMap.remove("H"+(2*cNumber-4));
				molesMap.put("O1", new Mole("O1",Math.sqrt(3)/2*BondLen.CODouLen,0,-BondLen.CODouLen/2));
				molesMap.put("O2", new Mole("O2",-Math.sqrt(3)/2*BondLen.COSinLen,0,-BondLen.COSinLen/2));
				Mole O2=molesMap.get("O2");
				molesMap.put("h1", new Mole("h1",O2.x+BondLen.OHSinLen/2,0,O2.z-Math.sqrt(3)/2*BondLen.OHSinLen));
				moles.clear();
				moles.addAll(molesMap.values());
			}else if((cNumber*2-8)==hNumber&&cNumber>=7) {//连苯环
				moles=hs.transformMoleFormula("C"+cNumber+"H"+(2*cNumber-6));
				HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
				for(Mole m:moles)molesMap.put(m.name, m);
				molesMap.remove("H"+(2*cNumber-6));
				molesMap.remove("H"+(2*cNumber-7));
				molesMap.remove("H"+(2*cNumber-8));
				molesMap.put("O1", new Mole("O1",Math.sqrt(3)/2*BondLen.CODouLen,0,-BondLen.CODouLen/2));
				molesMap.put("O2", new Mole("O2",-Math.sqrt(3)/2*BondLen.COSinLen,0,-BondLen.COSinLen/2));
				Mole O2=molesMap.get("O2");
				molesMap.put("h1", new Mole("h1",O2.x+BondLen.OHSinLen/2,0,O2.z-Math.sqrt(3)/2*BondLen.OHSinLen));
				moles.clear();
				moles.addAll(molesMap.values());
			}
		}
		return moles;
	}

}
