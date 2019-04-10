package organicsService;

import java.util.regex.Pattern;

import organicsUtil.HaloType;

import java.util.regex.Matcher;

public class ServiceController {
 /*
 * 根据分子式判断调用哪一个具体的service
 * 具体的service如下：
 * HydrocarbonService：只含碳氢的
 * HalohydrocarbonService：卤代烃
 */
	static HaloType haloType;
	public static String[]  serviceDispatcher(String moleFormula) {
		String[] bonds=null;
		if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
			HydrocarbonService hydrocarbonService=new HydrocarbonService();
			bonds=hydrocarbonService.transformMoleFormula(moleFormula);
		}else if(matchHalohydrocarbon(moleFormula)) {
			bonds=HalohydrocarbonService.transformMoleFormula(moleFormula,haloType);
		}else if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]")) {
			OHService oHService=new OHService();
			bonds=oHService.transformMoleFormula(moleFormula);
		}
		return bonds;
	}
	//和卤代烃匹配
	public static boolean matchHalohydrocarbon(String moleFormula) {
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
