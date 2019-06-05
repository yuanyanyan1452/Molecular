			package organicsController;

import java.util.regex.Pattern;

import organicsBondService.CHOService;
import organicsBondService.COCService;
import organicsBondService.COOHService;
import organicsBondService.COOService;
import organicsBondService.COService;
import organicsBondService.HalohydrocarbonService;
import organicsBondService.HydrocarbonService;
import organicsBondService.LifeBasicService;
import organicsBondService.OHService;
import organicsUtil.HaloType;
import organicsUtil.MoleProperty;

import java.util.regex.Matcher;
import java.util.LinkedList;
public class BondSingleServiceController {
	private static HaloType haloType;
	public static LinkedList<String>  serviceDispatcher(String moleName,String inputType) {
		LinkedList<String> bonds=new LinkedList<String>();
		if(inputType.equals("按分子式")) {
			if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {//烃
				HydrocarbonService hydrocarbonService=new HydrocarbonService();
				bonds=hydrocarbonService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
			}else if(matchHalohydrocarbon(moleName)) {//卤代烃
				bonds=HalohydrocarbonService.transformMoleFormula(moleName,haloType);
				if(!bonds.isEmpty())return bonds;
			}else if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]([1-9]{1}[0-9]{0,})?")) {
				/*
				//醛
				CHOService choService=new CHOService();
				bonds=choService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
				*/
				//醇
				OHService oHService=new OHService();
				bonds=oHService.transformMoleFormula(moleName);
				if(!bonds.isEmpty()) return bonds;
				
				//酮
				COService coService=new COService();
				bonds=coService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
				//酸
				COOHService coohService=new COOHService();
				bonds=coohService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
				//醚
				COCService cocService=new COCService();
				bonds=cocService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
				//酯
				COOService cooService=new COOService();
				bonds=cooService.transformMoleFormula(moleName);
				if(!bonds.isEmpty())return bonds;
			}
		}else {//按照有机物名称来解析
			if(MoleProperty.nameByLocalName.containsKey(moleName)) {
				String temp=MoleProperty.nameByLocalName.get(moleName);
				moleName=temp.split(" ")[0];
				String type=temp.split(" ")[1];
				switch(type) {
					case "醛":
						CHOService choService=new CHOService();
						bonds=choService.transformMoleFormula(moleName);
						if(!bonds.isEmpty())return bonds;
						break;
					case "醚":
						COCService cocService=new COCService();
						bonds=cocService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "酸":
						COOHService coohService=new COOHService();
						bonds=coohService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "酯":
						COOService cooService=new COOService();
						bonds=cooService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "酮":
						COService coService=new COService();
						bonds=coService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "卤代烃":
						bonds=HalohydrocarbonService.transformMoleFormula(moleName, haloType);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "烃":
						HydrocarbonService hydrocarbonService=new HydrocarbonService();
						bonds=hydrocarbonService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
					case "醇":
						OHService ohService=new OHService();
						bonds=ohService.transformMoleFormula(moleName);
						if(!bonds.isEmpty()) return bonds;
						break;
				}
			}else {
				if(MoleProperty.nameByLocalName.containsKey(moleName)) {
					String temp=MoleProperty.nameByLocalName.get(moleName);
					moleName=temp.split(" ")[0];
					String type=temp.split(" ")[1];
					switch(type) {
						case "醛":
							CHOService choService=new CHOService();
							bonds=choService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "醚":
							COCService cocService=new COCService();
							bonds=cocService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "酸":
							COOHService coohService=new COOHService();
							bonds=coohService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "酯":
							COOService cooService=new COOService();
							bonds=cooService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "酮":
							COService coService=new COService();
							bonds=coService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "卤代烃":
							bonds=HalohydrocarbonService.transformMoleFormula(moleName, haloType);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "烃":
							HydrocarbonService hydrocarbonService=new HydrocarbonService();
							bonds=hydrocarbonService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
						case "醇":
							OHService ohService=new OHService();
							bonds=ohService.transformMoleFormula(moleName);
							if(!bonds.isEmpty()) return bonds;
							break;
					}
				}else {
					LifeBasicService lifeBasicService=new LifeBasicService();
					bonds=lifeBasicService.transformMoleFormula(moleName);
					if(!bonds.isEmpty()) return bonds;
				}
			}
		}
		return bonds;
	}
	//和卤代烃匹配
	private static boolean matchHalohydrocarbon(String moleName) {
		Pattern pattern=Pattern.compile("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?");
		Matcher matcher=pattern.matcher(moleName);
		if(matcher.lookingAt()) {
			int i=matcher.end();
			String x=moleName.substring(i,i+1);
			if(x.equals("F")||x.equals("f")) {
				haloType=HaloType.F;
				return true;
			}else if(x.equals("I")||x.equals("i")) {
				haloType=HaloType.I;
				return true;
			}
			if(moleName.length()>i+1) {
				x=moleName.substring(i,i+2);
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
	
	//判断是否是中学化学常见有机物
	public static boolean checkRange(String moleName) {
		if(MoleProperty.nameByLocalName.containsKey(moleName))return true;
		if(BondSingleServiceController.serviceDispatcher(moleName, "按中文名").isEmpty()&&BondSingleServiceController.serviceDispatcher(moleName, "按分子式").isEmpty())
			return false;
		else return true;
	}
}