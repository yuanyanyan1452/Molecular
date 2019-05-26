package organicsCoordinateService;
import java.util.HashMap;

import organicsUtil.BondLen;
import organicsUtil.Counter;
import organicsUtil.Mole;

public class HydroSampleServiceLeft {
	//官能团在左边。完整的烷烃部分。碳的序号和氢的序号从前往后标注，第一个参数是这个sample需要创建的碳原子的个数
	public static HashMap<String,Mole> hydroSampleService(int cNumber){
		final double tetra=109.5;
		HashMap<String,Mole> molesMap=new HashMap<String,Mole>();
		String name="";
		int hCount=1;
		//先确定碳链的位置
		for(int i=1;i<=cNumber;i++) {
			name="C"+i;
			if(i==1) {
				molesMap.put(name, new Mole(name,0,0,0));
			}else if(i%2==0) {//偶数序号的碳
				Mole temp=molesMap.get("C"+(i-1));
				molesMap.put(name,new Mole(name,temp.x,0,temp.z+BondLen.CCSinLen));
			}else {
				Mole temp=molesMap.get("C"+(i-1));
				molesMap.put(name,new Mole(name,temp.x-BondLen.CCSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CCSinLen*Counter.cos(180-tetra)));
			}
		}
		//再安上氢原子
		//算出所有的对称点
		HashMap<String,Mole> centerMolesMap=new HashMap<String,Mole>();
		for(int j=2;j<=cNumber;j+=2) {
			name="center"+(j/2);
			centerMolesMap.put(name,new Mole(name,-(j/2-1)*(Counter.sin(180-tetra)*BondLen.CCSinLen),0,molesMap.get("C"+j).z-BondLen.CCSinLen/2));
		}
		for(int i=1;i<=cNumber;i++) {
			if(i==1) {
				molesMap.put("H"+(hCount),new Mole("H"+(hCount++),Counter.sin(180-tetra)*BondLen.CHSinLen,0,-BondLen.CHSinLen*Counter.cos(180-tetra)));
				molesMap.put("H"+hCount,new Mole("H"+(hCount++),-Counter.sin(30)*molesMap.get("H1").x,Counter.cos(30)*molesMap.get("H1").x,molesMap.get("H1").z));
				molesMap.put("H"+hCount,new Mole("H"+(hCount++),molesMap.get("H2").x,-molesMap.get("H2").y,molesMap.get("H2").z));
				if(i==cNumber) {
					Mole temp=molesMap.get("C"+cNumber);
					molesMap.put("H"+hCount,new Mole("H"+hCount,temp.x,temp.y,temp.z+BondLen.CHSinLen));
				}
			}else if(i%2==0) {
				for(int j=0;j<2;j++) {
					Mole temp=new Mole("H"+(hCount));
					SymmetryMoleService.symmetry(molesMap.get("H"+(hCount-2)), centerMolesMap.get("center"+(i/2)), temp);
					molesMap.put("H"+(hCount++),temp);
				}
				if(i==cNumber) {
					Mole temp=molesMap.get("C"+cNumber);
					molesMap.put("H"+hCount,new Mole("H"+(hCount),temp.x-BondLen.CHSinLen*Counter.sin(180-tetra),0,temp.z+BondLen.CHSinLen*Counter.cos(180-tetra)));
				}
			}else {
				Mole tempc=molesMap.get("C"+i);
				for(int j=0;j<2;j++) {
					Mole temph=molesMap.get("H"+(j+2));
					molesMap.put("H"+hCount,new Mole("H"+(hCount++),tempc.x+temph.x,tempc.y+temph.y,tempc.z+temph.z));
				}
				if(i==cNumber) {
					Mole temp=molesMap.get("C"+cNumber);
					molesMap.put("H"+hCount,new Mole("H"+hCount,temp.x,temp.y,temp.z+BondLen.CHSinLen));
				}
			}
		}
		return molesMap;
	}
}
