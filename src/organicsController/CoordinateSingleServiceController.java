package organicsController;

import java.util.regex.Pattern;

import organicsCoordinateService.COCService;
import organicsCoordinateService.COService;
import organicsCoordinateService.HalohydrocarbonService;
import organicsCoordinateService.CHOService;
import organicsCoordinateService.COOHService;
import organicsCoordinateService.COOService;
import organicsCoordinateService.HydrocarbonService;
import organicsCoordinateService.LifeBasicService;
import organicsCoordinateService.OHService;
import organicsUtil.HaloType;
import organicsUtil.Mole;
import organicsUtil.MoleProperty;

import java.util.regex.Matcher;
import java.util.LinkedList;
//只返回单种有机物的调度器
public class CoordinateSingleServiceController {
	private static HaloType haloType;
	public static LinkedList<Mole>  serviceDispatcher(String moleName,String inputType) {
		LinkedList<Mole> moles=new LinkedList<Mole>();
		if(inputType.equals("按分子式")) {
			if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?")) {
				HydrocarbonService hydrocarbonService=new HydrocarbonService();
				moles=hydrocarbonService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;
			}else if(matchHalohydrocarbon(moleName)) {
				moles=HalohydrocarbonService.transformMoleFormula(moleName, haloType);
				if(!moles.isEmpty()) return moles;
			}else if(moleName.matches("[Cc]([1-9]{1}[0-9]{0,})?[Hh]([1-9]{1}[0-9]{0,})?[Oo]([1-9]{1}[0-9]{0,})?")) {
				/*
				//醛
				CHOService choService=new CHOService();
				moles=choService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;
				*/

				//醇
				OHService ohService=new OHService();
				moles=ohService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;

				//酮
				COService coService=new COService();
				moles=coService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;

				//酸
				COOHService coohService=new COOHService();
				moles=coohService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;

				//醚
				COCService cocService=new COCService();
				moles=cocService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;

				//酯
				COOService cooService=new COOService();
				moles=cooService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;

			}
		}else if(inputType.equals("按中文名")){//按照有机物名称来解析
			if(MoleProperty.nameByLocalName.containsKey(moleName)) {
				String temp=MoleProperty.nameByLocalName.get(moleName);
				moleName=temp.split(" ")[0];
				String type=temp.split(" ")[1];
				switch(type) {
					case "醛":
						CHOService choService=new CHOService();
						moles=choService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "醚":
						COCService cocService=new COCService();
						moles=cocService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "酸":
						COOHService coohService=new COOHService();
						moles=coohService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "酯":
						COOService cooService=new COOService();
						moles=cooService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "酮":
						COService coService=new COService();
						moles=coService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "卤代烃":
						moles=HalohydrocarbonService.transformMoleFormula(moleName, haloType);
						if(!moles.isEmpty()) return moles;
						break;
					case "烃":
						HydrocarbonService hydrocarbonService=new HydrocarbonService();
						moles=hydrocarbonService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
					case "醇":
						OHService ohService=new OHService();
						moles=ohService.transformMoleFormula(moleName);
						if(!moles.isEmpty()) return moles;
						break;
				}
			}else {
				LifeBasicService lifeBasicService=new LifeBasicService();
				moles=lifeBasicService.transformMoleFormula(moleName);
				if(!moles.isEmpty()) return moles;
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