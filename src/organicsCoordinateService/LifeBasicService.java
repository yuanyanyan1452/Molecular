package organicsCoordinateService;
import java.util.HashMap;
import java.util.LinkedList;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.Mole;

public class LifeBasicService implements TransformService {
	
	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula){
		LinkedList<Mole> moles=new LinkedList<Mole>();
		HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
		final double tetra=109.5;
		switch(moleFormula) {
		case "葡萄糖":
			HydrocarbonService hs=new HydrocarbonService();
			moles=hs.transformMoleFormula("C6H14");
			for(Mole m:moles)molesMap.put(m.name, m);
			molesMap.remove("H14");
			molesMap.remove("H13");
			molesMap.remove("H12");
			Mole temp=molesMap.get("C6");
			molesMap.put("O6", new Mole("O6",temp.x+BondLen.CODouLen/2*Math.sqrt(3),0,temp.z+BondLen.CODouLen/2));
			molesMap.put("H12", new Mole("H12",temp.x-BondLen.CODouLen/2*Math.sqrt(3),0,temp.z+BondLen.CODouLen/2));
			temp=molesMap.get("H1");
			molesMap.put("O1", new Mole("O1",temp.x,temp.y,temp.z));
			temp=molesMap.get("H5");
			molesMap.put("O2", new Mole("O2",temp.x,temp.y,temp.z));
			temp=molesMap.get("H7");
			molesMap.put("O3", new Mole("O3",temp.x,temp.y,temp.z));
			temp=molesMap.get("H9");
			molesMap.put("O4", new Mole("O4",temp.x,temp.y,temp.z));
			temp=molesMap.get("H11");
			molesMap.put("O5", new Mole("O5",temp.x,temp.y,temp.z));
			molesMap.remove("H1");
			molesMap.remove("H5");
			molesMap.remove("H7");
			molesMap.remove("H9");
			molesMap.remove("H11");
			temp=molesMap.get("O1");
			molesMap.put("H1", new Mole("H1",temp.x+BondLen.OHSinLen*Counter.sin(tetra-90),0,temp.z+BondLen.OHSinLen*Counter.cos(tetra-90)));
			temp=molesMap.get("O2");
			molesMap.put("H5", new Mole("H5",temp.x,0,temp.z+BondLen.OHSinLen));
			temp=molesMap.get("O3");
			molesMap.put("H7", new Mole("H7",temp.x,0,temp.z+BondLen.OHSinLen));
			temp=molesMap.get("O4");
			molesMap.put("H9", new Mole("H9",temp.x,0,temp.z+BondLen.OHSinLen));
			temp=molesMap.get("O5");
			molesMap.put("H11", new Mole("H11",temp.x,0,temp.z+BondLen.OHSinLen));
			moles.clear();
			moles.addAll(molesMap.values());
			break;
		case "果糖":
			break;
		case "油脂":
			break;
		case "蔗糖":
			break;
		default:break;	
		
		}
		return moles;
	}
}
