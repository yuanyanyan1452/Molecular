package organicsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;
import organicsUtil.BondType;
import organicsUtil.HaloType;

/*
 * 卤代烃
 * 烃中的氢原子被F、Cl、Br、I替代即氟氯溴碘代烃
 */
public class HalohydrocarbonService {
	public static String[] transformMoleFormula(String moleFormula,HaloType haloType) {
		
		int[] result=InputMoleFormula.getNumber(moleFormula);
		int cNumber=result[0];
		int hNumber=result[1];
		int xNumber=result[2];
		ArrayList<String> bonds=new ArrayList<String>();
		BondType bondType=BondType.CFTeBond;
		switch(haloType) {
		case F:bondType=BondType.CFTeBond;break;
		case Cl:bondType=BondType.CClTeBond;break;
		case Br:bondType=BondType.CBrTeBond;break;
		case I:bondType=BondType.CITeBond;;break;
		default:break;
		}
		//甲基卤
		if(cNumber==1&&hNumber==3&&xNumber==1) {
			bonds.addAll(Arrays.asList(GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.Methyl)));
			switch(haloType) {
			case F:bonds.add("c1F1"+BondType.CFTeBond);break;
			case Cl:bonds.add("c1Cl1"+BondType.CFTeBond);break;
			case Br:bonds.add("c1Br1"+BondType.CBrTeBond);break;
			case I:bonds.add("c1I1"+BondType.CITeBond);break;
			default:break;
			}
		}else if(cNumber*2+2==(hNumber+xNumber)) {//卤代烷烃
				HashMap<Integer,Integer>map=new HashMap<Integer,Integer>();
				for(int i=0;i<cNumber;i++)map.put(i, 4);
				//每个碳都是四个键
				for(int i=0;i<cNumber;i++) {
					if(i<=cNumber-2) {
						bonds.add("C"+(i+1)+"C"+(i+2)+BondType.CCSingleBond);
						map.put(i, map.get(i)-1);
						map.put(i+1, map.get(i+1)-1);
					}
				}
				if(xNumber<=3) {
					for(int i=0;i<xNumber;i++) {
						bonds.add("C1"+haloType+bondType);
						map.put(0,map.get(0)-1);
					}
					while(map.get(0)!=1) {
						bonds.add("C1H"+BondType.CHTeBond);
						map.put(0,map.get(0)-1);
					}
					for(int i=1;i<=cNumber-2;i++) {
						bonds.add("C"+(i+1)+"H"+BondType.CHTeBond);
						bonds.add("C"+(i+1)+"H"+BondType.CHTeBond);
					}
					for(int i=0;i<3;i++)
						bonds.add("C"+cNumber+"H"+BondType.CHTeBond);
				}else {
					for(int i=0;i<3;i++) {
						bonds.add("C1"+haloType+bondType);
						xNumber--;
					}
					for(int i=2;xNumber>0&&i<=cNumber-1;i++) {
						bonds.add("C"+(i)+haloType+bondType);
						map.put(i-1,map.get(i-1)-1);
						xNumber--;
						if(xNumber<=0)break;
						else {
							bonds.add("C"+(i)+haloType+bondType);
							map.put(i-1,map.get(i-1)-1);
							xNumber--;
						}
					}
					for(int i=2;i<=cNumber-1;i++) {
						if(map.get(i-1)>0) {
							bonds.add("C"+i+"H"+BondType.CHTeBond);
							map.put(i-1,map.get(i-1)-1);
						}
					}
					while(xNumber>0) {
						bonds.add("C"+cNumber+haloType+bondType);
						xNumber--;
						map.put(cNumber-1,map.get(cNumber-1)-1);
					}
					while(map.get(cNumber-1)>0) {
						bonds.add("C"+cNumber+"H"+BondType.CHTeBond);
						map.put(cNumber-1,map.get(cNumber-1)-1);
					}
				}
			}else if(cNumber*2==(hNumber+xNumber)) {//卤代烯烃
				bonds.add("C1C2"+BondType.CCDoubleBond);
				//碳之间的键
				for(int i=2;i<=cNumber-1;i++) {
					if(i==2) {
						bonds.add("C2C3"+BondType.CC120SingleBond);//紧跟双键旁边的碳原子是120度的单键
						continue;
					}
					bonds.add("C"+i+"C"+(i+1)+BondType.CCTeSingleBond);
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
								case F:bondType=BondType.CF120Bond;break;
								case Cl:bondType=BondType.CCl120Bond;break;
								case Br:bondType=BondType.CBr120Bond;break;
								case I:bondType=BondType.CI120Bond;;break;
								default:break;
							}
						}
						bonds.add("C"+i+haloType+bondType);
						map.put(i, map.get(i)-1);
						xNumber--;
						bondType=help;
					}
				}
				//再安装氢原子
				for(int i=1;i<=cNumber;i++) {
					while(map.get(i)!=0&&hNumber!=0) {
						if(i==1||i==2) {
							bonds.add("C"+i+"H"+BondType.CH120Bond);
						}else bonds.add("C"+i+"H"+BondType.CHTeBond);
						map.put(i, map.get(i)-1);
						hNumber--;
					}
				}
			}else if(cNumber*2-2==(hNumber+xNumber)) {//卤代炔烃
				//先将碳碳键分配掉
				for(int i=1;i<=cNumber-1;i++) {
					if(i==1) {
						bonds.add("C"+i+"C"+(i+1)+BondType.CCTripleBond);
					}else if(i==2){
						bonds.add("C"+i+"C"+(i+1)+BondType.CC180SingleBond);
					}else {
						bonds.add("C"+i+(i+1)+"C"+BondType.CCTeSingleBond);
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
						if(i==1) {
							switch(haloType) {
								case F:bonds.add("C"+i+haloType+BondType.CF180Bond);break;
								case Cl:bonds.add("C"+i+haloType+BondType.CCl180Bond);break;
								case Br:bonds.add("C"+i+haloType+BondType.CBr180Bond);break;
								case I:bonds.add("C"+i+haloType+BondType.CI180Bond);break;
								default:
							}
						}else {
							bonds.add("C"+i+haloType+bondType);
						}
						xNumber--;map.put(i, map.get(i)-1);
					}
				}
				for(int i=1;i<=cNumber;i++) {
					while(hNumber>0&&map.get(i)>0) {
						if(i==1)bonds.add("C"+i+"H"+BondType.CH180Bond);
						else {
							bonds.add("C"+i+"H"+BondType.CHTeBond);
						}
						hNumber--;map.put(i, map.get(i)-1);
					}
				}
			}else if(cNumber*2-6==(hNumber+xNumber)) {//卤代芳香烃
				String[] temp=GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.BenzeneRing);
				for(String str:temp)bonds.add(str);
				for(int i=1;i<=6;i++) {
					if(xNumber>0) {
						switch(haloType) {
							case F:bonds.add("C"+i+haloType+BondType.CF120Bond);break;
							case Cl:bonds.add("C"+i+haloType+BondType.CCl120Bond);break;
							case Br:bonds.add("C"+i+haloType+BondType.CBr120Bond);break;
							case I:bonds.add("C"+i+haloType+BondType.CI120Bond);break;
							default:break;
						}
						xNumber--;
					}else {
						bonds.add("C"+i+"H"+BondType.CH120Bond);
					}
				}
			}
		String[] res=new String[bonds.size()];
		return bonds.toArray(res);
	}

}
