package organicsController;

import java.util.regex.Pattern;

import organicsCoordinate.CHOService;
import organicsCoordinate.HydrocarbonService;
import organicsUtil.HaloType;
import organicsUtil.Mole;

import java.util.regex.Matcher;
import java.util.LinkedList;
//只返回单种有机物的调度器
public class CoordinateSingleServiceController {
	private static HaloType haloType;
	public static LinkedList<Mole>  serviceDispatcher(String moleName,String inputType) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		LinkedList<Mole> temp=new LinkedList<Mole>();
		if(inputType.equals("按分子式")) {
			if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
				HydrocarbonService hydrocarbonService=new HydrocarbonService();
				temp=hydrocarbonService.transformMoleFormula(moleName);
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
			}else if(matchHalohydrocarbon(moleName)) {
			}else if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]([1-9]{1}[0-9]{0,})?")) {
				//醛
				CHOService choService=new CHOService();
				temp=choService.transformMoleFormula(moleName);
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
				//醇
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
				//酮
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
				//酸
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
				//醚
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
				//酯
				if(!temp.isEmpty()) {
					moles.addAll(temp);
					return moles;
				}
			}
		}else if(inputType.equals("按中文名")){//按照有机物名称来解析
			if(!temp.isEmpty()) {
				moles.addAll(temp);
				return moles;
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
}
;