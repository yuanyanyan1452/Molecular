package organicsService;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ServiceController {
/*
 * 根据分子式判断调用哪一个具体的service
 * 具体的service如下：
 * HydrocarbonService：只含碳氢的
 * HalohydrocarbonService：卤代烃
 */
	public static String[]  serviceDispatcher(String moleFormula) {
		/*
		 * HydrocarbonService:只含碳氢的
		 */
		String[] bonds=null;
		if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
			bonds=HydrocarbonService.transformMoleFormula(moleFormula);
		}else if(matchHalohydrocarbon(moleFormula)) {
			bonds=HalohydrocarbonService.transformMoleFormula(moleFormula);
		}
		return bonds;
	}
	//和卤代烃匹配
	public static boolean matchHalohydrocarbon(String moleFormula) {
		Pattern pattern=Pattern.compile("[Cc]([1-9]{1}\\d)?[Hh]([1-9]{1}\\d)?");
		Matcher matcher=pattern.matcher(moleFormula);
		if(matcher.lookingAt()) {
			int i=matcher.end();
			String x=moleFormula.substring(i+1);
			if(x.equals("F")||x.equals("Cl")||x.equals("Br")||x.equals("I")||x.equals("f")||x.equals("cl")||x.equals("br")||x.equals("i")) {
				return true;
			}
		}
		return false;
	}
}
