package organicsController;

import java.util.regex.Pattern;

import organicsService.CHOService;
import organicsService.COCService;
import organicsService.COOHService;
import organicsService.COOService;
import organicsService.COService;
import organicsService.HalohydrocarbonService;
import organicsService.HydrocarbonService;
import organicsService.LifeBasicService;
import organicsService.OHService;
import organicsUtil.HaloType;

import java.util.regex.Matcher;
import java.util.LinkedList;
public class BondSingleServiceController {
 /*
 * 根据分子式判断调用哪一个具体的service
 * 具体的service如下：
 * HydrocarbonService：只含碳氢的
 * HalohydrocarbonService：卤代烃
 * 应做的优化：应将有机物名称的添加放到每一个具体的service中来做
 */
	private static HaloType haloType;
	public static LinkedList<String>  serviceDispatcher(String moleFormula) {
		LinkedList<String> bonds=new LinkedList<String>();
		LinkedList<String> temp=new LinkedList<String>();
		if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
			HydrocarbonService hydrocarbonService=new HydrocarbonService();
			bonds=hydrocarbonService.transformMoleFormula(moleFormula);
		}else if(matchHalohydrocarbon(moleFormula)) {
			bonds=HalohydrocarbonService.transformMoleFormula(moleFormula,haloType);
		}else if(moleFormula.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]([1-9]{1}[0-9]{0,})?")) {
			//醇
			OHService oHService=new OHService();
			temp=oHService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
			//醛
			CHOService choService=new CHOService();
			temp=choService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
			//酮
			COService coService=new COService();
			temp=coService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
			//酸
			COOHService coohService=new COOHService();
			temp=coohService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
			//醚
			COCService cocService=new COCService();
			temp=cocService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
			//酯
			COOService cooService=new COOService();
			temp=cooService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.addAll(temp);
			}
		}else {//按照有机物名称来解析
			LifeBasicService lifeBasicService=new LifeBasicService();
			temp=lifeBasicService.transformMoleFormula(moleFormula);
			if(!temp.isEmpty()) {
				bonds.add("#"+moleFormula);
				bonds.addAll(temp);
			}
		}
		return bonds;
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