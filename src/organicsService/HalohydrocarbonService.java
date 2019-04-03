package organicsService;

import funcGroupUtil.FuncGroupType;
import funcGroupUtil.GetFuncGroupStrFormula;

/*
 * 卤代烃
 * 烃中的氢原子被F、Cl、Br、I替代即氟氯溴碘代烃
 */
public class HalohydrocarbonService {
	public static String[] transformMoleFormula(String moleFormula) {
		String[] bonds=null;
		//甲基卤
		if(moleFormula.matches("[Cc][Hh](3)[]")) {
			bonds=GetFuncGroupStrFormula.getFuncGroupStrFormula(FuncGroupType.Methyl);
		}
		return bonds;
	}

}
