package organicsBondService;
import java.util.LinkedList;

import organicsUtil.BondType;
public class LifeBasicService implements TransformService {
	
	@Override
	public LinkedList<String> transformMoleFormula(String moleFormula){
		LinkedList<String> bonds=new LinkedList<String>();
		switch(moleFormula) {
		case "葡萄糖":
			//碳链
			for(int i=1;i<=5;i++) {
				bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			//加上氧
			for(int i=1;i<=6;i++) {
				if(i==6)bonds.add("C"+i+" O"+i+" "+BondType.CO120DoubleBond);
				else bonds.add("C"+i+" O"+i+" "+BondType.COTeSingleBond);
			}
			//加上氢
			for(int i=1;i<=6;i++) {
				if(i==1) {
					bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}else if(i==6) {
					bonds.add("C"+i+" H "+BondType.CH120SingleBond);
				}else bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
			}
			for(int i=1;i<=5;i++) {
				bonds.add("O"+i+" H "+BondType.OH90Bond);
			}
			break;
		case "果糖":
			//碳链
			for(int i=1;i<=5;i++) {
				if(i==5)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			//加上氧
			for(int i=1;i<=6;i++) {
				if(i==5)bonds.add("C"+i+" O"+i+" "+BondType.CO120DoubleBond);
				else bonds.add("C"+i+" O"+i+" "+BondType.COTeSingleBond);
			}
			//加上氢
			for(int i=1;i<=6;i++) {
				if(i==1||i==6) {
					bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
					bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}else if(i!=5)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
			}
			for(int i=1;i<=6;i++) {
				if(i!=5)bonds.add("O"+i+" H "+BondType.OH90Bond);
			}
			break;
		case "油脂":
			//碳链-主链
			for(int i=1;i<=38;i++) {
				if(i==18)bonds.add("C"+i+" O2 "+BondType.CO120SingleBond);
				else if(i==21)bonds.add("C"+i+" O3 "+BondType.COTeSingleBond);
				else if(i==22)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			bonds.add("O2 C19 "+BondType.COTeSingleBond);
			bonds.add("O3 C22 "+BondType.CO120SingleBond);
			//碳链-支链
			bonds.add("C20 O5 "+BondType.COTeSingleBond);
			bonds.add("O5 C40 "+BondType.CO180SingleBond);
			for(int i=40;i<=56;i++) {
				if(i==40)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
			}
			//加上氧
			bonds.add("C18 O1 "+BondType.CO120DoubleBond);
			bonds.add("C22 O4 "+BondType.CO120DoubleBond);
			bonds.add("C40 O6 "+BondType.CO120DoubleBond);
			//加上氢-主链
			for(int i=1;i<=39;i++) {
				if(i==1||i==39) {
					for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}else if(i==20)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				else if(i!=18&&i!=22) {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
			}
			//加上氢-支链
			for(int i=41;i<=57;i++) {
				if(i==57) {
					for(int j=0;j<3;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}else {
					for(int j=0;j<2;j++)bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
				}
			}
			break;
		case "蔗糖":
			//碳链
			for(int i=1;i<=11;i++) {
				if(i==1||i==7)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				else if(i>=2&&i<=5)bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
				else bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC120SingleBond);
			}
			//加氧
			for(int i=1;i<=12;i++) {
				if(i==1||i==7)bonds.add("C"+i+" O"+i+" "+BondType.COTeSingleBond);
				else if(i==12)bonds.add("C"+i+" O10 "+BondType.COTeSingleBond);
			}
			break;
		default:break;	
		
		}
		return bonds;
	}
}
