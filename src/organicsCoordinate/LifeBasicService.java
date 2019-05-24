package organicsCoordinate;
import java.util.LinkedList;

import organicsUtil.Mole;

public class LifeBasicService implements TransformService {
	
	@Override
	public LinkedList<Mole> transformMoleFormula(String moleFormula){
		LinkedList<Mole> moles=new LinkedList<Mole>();
		switch(moleFormula) {
		case "葡萄糖":
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
