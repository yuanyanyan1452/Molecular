package organicsCoordinateService;
import java.util.HashMap;
import java.util.LinkedList;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.InputMoleFormula;
import organicsUtil.Mole;

public class OHService implements TransformService {
	public LinkedList<Mole> transformMoleFormula(String moleFormula) {
		LinkedList<Integer>numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int oNumber=numbers.get(2);
		final double tetra=109.5;
		LinkedList<Mole>moles=new LinkedList<Mole>();
		HydrocarbonService hs=new HydrocarbonService();
		if((cNumber*2+2)==hNumber&&oNumber==1) {//连烷烃
			moles=hs.transformMoleFormula("C"+cNumber+"H"+(2*cNumber+2));
			HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
			for(Mole m:moles)molesMap.put(m.name, m);
			if(cNumber%2==0) {
				molesMap.remove("H"+(2*cNumber+2));
				Mole temp=molesMap.get("C"+cNumber);
				molesMap.put("O1", new Mole("O1",temp.x-Counter.sin(180-tetra)*BondLen.COSinLen,0,temp.z+Counter.cos(180-tetra)*BondLen.COSinLen));
				temp=molesMap.get("O1");
				molesMap.put("h1", new Mole("h1",temp.x-Counter.cos(180-tetra)*BondLen.OHSinLen,0,temp.z-Counter.sin(180-tetra)*BondLen.OHSinLen));
			}else {
				molesMap.remove("H"+(2*cNumber+2));
				Mole temp=molesMap.get("C"+cNumber);
				molesMap.put("O1", new Mole("O1",temp.x,0,temp.z+BondLen.COSinLen));
				temp=molesMap.get("O1");
				molesMap.put("h1", new Mole("h1",temp.x-BondLen.OHSinLen,0,temp.z));
			}
			moles.clear();
			moles.addAll(molesMap.values());
		}else if(cNumber*2==hNumber&&oNumber==1&&cNumber>=2) {//连烯烃
			moles=hs.transformMoleFormula("C"+cNumber+"H"+(cNumber*2));
			HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
			for(Mole m:moles)molesMap.put(m.name, m);
			molesMap.remove("H"+(cNumber*2));
			molesMap.put("O1", new Mole("O1",Counter.sin(180-tetra)*BondLen.COSinLen,0,-Counter.cos(180-tetra)*BondLen.COSinLen));
			Mole temp=molesMap.get("O1");
			molesMap.put("h1", new Mole("h1",temp.x+Counter.sin(tetra-90)*BondLen.OHSinLen,0,temp.z+BondLen.OHSinLen*Counter.cos(tetra-90)));
			moles.clear();
			moles.addAll(molesMap.values());
		}else if((cNumber*2-2)==hNumber) {//连炔烃
			moles=hs.transformMoleFormula("C"+cNumber+"H"+(cNumber*2-2));
			HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
			for(Mole m:moles)molesMap.put(m.name, m);
			molesMap.remove("H"+(cNumber*2-2));
			molesMap.put("O1", new Mole("O1",Counter.sin(180-tetra)*BondLen.COSinLen,0,-Counter.cos(180-tetra)*BondLen.COSinLen));
			Mole temp=molesMap.get("O1");
			molesMap.put("h1", new Mole("h1",temp.x+Counter.sin(tetra-90)*BondLen.OHSinLen,0,temp.z+BondLen.OHSinLen*Counter.cos(tetra-90)));
			moles.clear();
			moles.addAll(molesMap.values());
		}else if((cNumber*2-6)==hNumber&&oNumber==1) {//连苯环
			moles=hs.transformMoleFormula("C"+cNumber+"H"+(cNumber*2-6));
			HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
			for(Mole m:moles)molesMap.put(m.name, m);
			molesMap.remove("H"+(cNumber*2-6));
			molesMap.put("O1", new Mole("O1",Counter.sin(180-tetra)*BondLen.COSinLen,0,-Counter.cos(180-tetra)*BondLen.COSinLen));
			Mole temp=molesMap.get("O1");
			molesMap.put("h1", new Mole("h1",temp.x+Counter.sin(tetra-90)*BondLen.OHSinLen,0,temp.z+BondLen.OHSinLen*Counter.cos(tetra-90)));
			moles.clear();
			moles.addAll(molesMap.values());
		}
		return moles;
	}
	
}
