package organicsController;

import java.util.regex.Pattern;

import organicsCoordinate.CHOService;
import organicsCoordinate.HydrocarbonService;
import organicsUtil.HaloType;
import organicsUtil.Mole;

import java.util.regex.Matcher;
import java.util.LinkedList;
public class ServiceController {
 /*
 * 根据分子式判断调用哪一个具体的service
 * 具体的service如下：
 * HydrocarbonService：只含碳氢的
 * HalohydrocarbonService：卤代烃
 * 应做的优化：应将有机物名称的添加放到每一个具体的service中来做
 */
	private static HaloType haloType;
	public static LinkedList<Mole>  serviceDispatcher(String moleFormula) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Mole> temp=new LinkedList<Mole>();
		double offset=0;//多种有机物时的横向偏移距离
		if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
			HydrocarbonService hydrocarbonService=new HydrocarbonService();
			temp=hydrocarbonService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				moles.addAll(temp);
				offset+=500;
			}
		}else if(matchHalohydrocarbon(moleFormula)) {
		}else if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]([1-9]{1}[0-9]{0,})?")) {
			//醛
			CHOService choService=new CHOService();
			temp=choService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				offsetService(offset,temp);
				moles.addAll(temp);
			}
			/*
			//醇
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
			//酮
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
			//酸
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
			//醚
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
			//酯
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
			*/
		}else {//按照有机物名称来解析
			if(!temp.isEmpty()) {
				moles.addAll(temp);
			}
		}
		return moles;
	}
	//和卤代烃匹配
	private static boolean matchHalohydrocarbon(String moleFormula) {
		Pattern pattern=Pattern.compile("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?");
		Matcher matcher=pattern.matcher(moleFormula);
		if(matcher.lookingAt()) {
			int i=matcher.end();
			String x=moleFormula.substring(i,i+1);
			if(x.equals("F")||x.equals("f")) {
				haloType=HaloType.F;
				return true;
			}else if(x.equals("I")||x.equals("i")) {
				haloType=HaloType.I;
				return true;
			}
			if(moleFormula.length()>i+1) {
				x=moleFormula.substring(i,i+2);
				if(x.equals("Cl")||x.equals("cl")||x.equals("CL")||x.equals("cL")) {
					haloType=HaloType.Cl;
					return true;
				}else if(x.equals("Br")||x.equals("br")||x.equals("BR")||x.equals("bR")) {
					haloType=HaloType.Br;
					return true;
				}
			}
		}
		return false;
	}
	public static void offsetService(double offset,LinkedList<Mole> moles) {
		if(offset==0)return;
		for(Mole m:moles) {
			m.setX(m.x+offset);
		}
	}
}
;