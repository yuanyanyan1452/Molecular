package organicsService;

import java.util.HashMap;

import organicsUtil.BondType;
import organicsUtil.FuncGroupType;
import organicsUtil.GetFuncGroupStrFormula;
import organicsUtil.HaloType;
import organicsUtil.InputMoleFormula;

import java.util.LinkedList;
/*
 * 卤代烃
 * 烃中的氢原子被F、Cl、Br、I替代即氟氯溴碘代烃
 */
public class HalohydrocarbonService {
	public static LinkedList<String> transformMoleFormula(String moleFormula,HaloType haloType) {
		
		LinkedList<Integer> numbers=InputMoleFormula.getNumber(moleFormula);
		int cNumber=numbers.get(0);
		int hNumber=numbers.get(1);
		int xNumber=numbers.get(2);
		LinkedList<String> bonds=new LinkedList<String>();
		BondType bondType=BondType.CFTeSingleBond;
		switch(haloType) {
		case F:bondType=BondType.CFTeSingleBond;break;
		case Cl:bondType=BondType.CClTeSingleBond;break;
		case Br:bondType=BondType.CBrTeSingleBond;break;
		case I:bondType=BondType.CITeSingleBond;;break;
		default:break;
		}
		//甲基卤
		if(cNumber==1&&hNumber==3&&xNumber==1) {
			bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.Methyl));
			switch(haloType) {
			case F:bonds.add("c1 F1 "+BondType.CFTeSingleBond);break;
			case Cl:bonds.add("c1 Cl1 "+BondType.CFTeSingleBond);break;
			case Br:bonds.add("c1 Br1 "+BondType.CBrTeSingleBond);break;
			case I:bonds.add("c1 I1 "+BondType.CITeSingleBond);break;
			default:break;
			}
		}else if(cNumber*2+2==(hNumber+xNumber)) {//卤代烷烃
				HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
				for(int i=0;i<cNumber;i++)map.put(i, 4);
				//每个碳都是四个键
				for(int i=0;i<cNumber;i++) {
					if(i<=cNumber-2) {
						bonds.add("C"+(i+1)+" C"+(i+2)+" "+BondType.CCTeSingleBond);
						map.put(i, map.get(i)-1);
						map.put(i+1, map.get(i+1)-1);
					}
				}
				if(xNumber<=3) {
					for(int i=0;i<xNumber;i++) {
						bonds.add("C1 "+haloType+" "+bondType);
						map.put(0,map.get(0)-1);
					}
					while(map.get(0)!=0) {
						bonds.add("C1 H "+BondType.CHTeSingleBond);
						map.put(0,map.get(0)-1);
					}
					for(int i=1;i<=cNumber-2;i++) {
						bonds.add("C"+(i+1)+" H "+BondType.CHTeSingleBond);
						bonds.add("C"+(i+1)+" H "+BondType.CHTeSingleBond);
					}
					for(int i=0;i<3;i++)
						bonds.add("C"+cNumber+" H "+BondType.CHTeSingleBond);
				}else {
					for(int i=0;i<3;i++) {
						bonds.add("C1 "+haloType+" "+bondType);
						xNumber--;
					}
					for(int i=2;xNumber>0&&i<=cNumber-1;i++) {
						bonds.add("C"+(i)+" "+haloType+" "+bondType);
						map.put(i-1,map.get(i-1)-1);
						xNumber--;
						if(xNumber<=0)break;
						else {
							bonds.add("C"+(i)+" "+haloType+" "+bondType);
							map.put(i-1,map.get(i-1)-1);
							xNumber--;
						}
					}
					for(int i=2;i<=cNumber-1;i++) {
						if(map.get(i-1)>0) {
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
							map.put(i-1,map.get(i-1)-1);
						}
					}
					while(xNumber>0) {
						bonds.add("C"+cNumber+" "+haloType+" "+bondType);
						xNumber--;
						map.put(cNumber-1,map.get(cNumber-1)-1);
					}
					while(map.get(cNumber-1)>0) {
						bonds.add("C"+cNumber+" H "+BondType.CHTeSingleBond);
						map.put(cNumber-1,map.get(cNumber-1)-1);
					}
				}
			}else if(cNumber*2==(hNumber+xNumber)) {//卤代烯烃
				bonds.add("C1 C2 "+BondType.CC120DoubleBond);
				//碳之间的键
				for(int i=2;i<=cNumber-1;i++) {
					if(i==2) {
						bonds.add("C2 C3 "+BondType.CC120SingleBond);//紧跟双键旁边的碳原子是120度的单键
						continue;
					}
					bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				//分配一下碳原子上面还剩的可以安装原子的键
				HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						map.put(i, 2);
					}else if(i==2&&cNumber==2) {
						map.put(i,2);
					}else if(i==2) {
						map.put(i, 1);
					}else if(i>2&&i<cNumber) {
						map.put(i, 2);
					}else {
						map.put(i,3);
					}
				}
				//碳和其他原子之间的键
				//先安装卤原子
				BondType help=bondType;
				for(int i=1;i<=cNumber;i++) {
					while(map.get(i)!=0&&xNumber!=0) {
						if(i==1||i==2) {
							switch(haloType) {
								case F:bondType=BondType.CF120SingleBond;break;
								case Cl:bondType=BondType.CCl120SingleBond;break;
								case Br:bondType=BondType.CBr120SingleBond;break;
								case I:bondType=BondType.CI120SingleBond;;break;
								default:break;
							}
						}
						bonds.add("C"+i+" "+haloType+" "+bondType);
						map.put(i, map.get(i)-1);
						xNumber--;
						bondType=help;
					}
				}
				//再安装氢原子
				for(int i=1;i<=cNumber;i++) {
					while(map.get(i)!=0&&hNumber!=0) {
						if(i==1||i==2) {
							bonds.add("C"+i+" H "+BondType.CH120SingleBond);
						}else bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						map.put(i, map.get(i)-1);
						hNumber--;
					}
				}
			}else if(cNumber*2-2==(hNumber+xNumber)) {//卤代炔烃
				//先将碳碳键分配掉
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1) {
						bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180TripleBond);
					}else if(i==2){
						bonds.add("C"+i+" C"+(i+1)+" "+BondType.CC180SingleBond);
					}else {
						bonds.add("C"+i+" C"+(i+1)+" "+BondType.CCTeSingleBond);
					}
				}
				HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
				//分配碳原子上面剩下的键
				for(int i=1;i<=cNumber;i++) {
					if(i==1) {
						map.put(i, 1);
					}else if(i==2) {
						if(i==cNumber) {
							map.put(i, 1);
						}else {
							map.put(i, 0);
						}
					}else if(i>2&&i<cNumber) {
						map.put(i, 2);
					}else {
						map.put(i, 3);
					}
				}
				//分配氢和卤原子
				for(int i=1;i<=cNumber;i++) {
					while(xNumber>0&&map.get(i)>0) {
						if(i==1||i==2) {
							switch(haloType) {
								case F:bonds.add("C"+i+" "+haloType+" "+BondType.CF180SingleBond);break;
								case Cl:bonds.add("C"+i+" "+haloType+" "+BondType.CCl180SingleBond);break;
								case Br:bonds.add("C"+i+" "+haloType+" "+BondType.CBr180SingleBond);break;
								case I:bonds.add("C"+i+" "+haloType+" "+BondType.CI180SingleBond);break;
								default:
							}
						}else {
							bonds.add("C"+i+" "+haloType+" "+bondType);
						}
						xNumber--;map.put(i, map.get(i)-1);
					}
				}
				for(int i=1;i<=cNumber;i++) {
					while(hNumber>0&&map.get(i)>0) {
						if(i==1||i==2)bonds.add("C"+i+" H "+BondType.CH180SingleBond);
						else {
							bonds.add("C"+i+" H "+BondType.CHTeSingleBond);
						}
						hNumber--;map.put(i, map.get(i)-1);
					}
				}
			}else if(cNumber*2-6==(hNumber+xNumber)) {//卤代芳香烃
				bonds.addAll(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing));
				for(int i=6;i<=cNumber-1;i++) {
					if(i==6)bonds.add("c"+i+" "+"C"+(i+1)+" "+BondType.CC120SingleBond);
					else bonds.add("C"+i+" "+"C"+(i+1)+" "+BondType.CCTeSingleBond);
				}
				for(int i=1;i<=6;i++) {
					if(i==6&&cNumber>6)break;
					if(xNumber>0) {
						switch(haloType) {
							case F:bonds.add("C"+i+" "+haloType+" "+BondType.CF120SingleBond);break;
							case Cl:bonds.add("C"+i+" "+haloType+" "+BondType.CCl120SingleBond);break;
							case Br:bonds.add("C"+i+" "+haloType+" "+BondType.CBr120SingleBond);break;
							case I:bonds.add("C"+i+" "+haloType+" "+BondType.CI120SingleBond);break;
							default:break;
						}
						xNumber--;
					}else {
						bonds.add("C"+i+" H "+BondType.CH120SingleBond);
					}
				}
				int count=2;
				for(int i=7;i<=cNumber;i++) {
					if(i<cNumber)count=2;
					else count=3;
					for(int j=0;j<count;j++) {
						if(xNumber>0) {
							switch(haloType) {
								case F:bonds.add("C"+i+" "+haloType+" "+BondType.CF120SingleBond);break;
								case Cl:bonds.add("C"+i+" "+haloType+" "+BondType.CCl120SingleBond);break;
								case Br:bonds.add("C"+i+" "+haloType+" "+BondType.CBr120SingleBond);break;
								case I:bonds.add("C"+i+" "+haloType+" "+BondType.CI120SingleBond);break;
								default:break;
							}
							xNumber--;
						}else {
							bonds.add("C"+i+" H "+BondType.CH120SingleBond);
						}
					}
				}
			}
		return bonds;
	}

}
