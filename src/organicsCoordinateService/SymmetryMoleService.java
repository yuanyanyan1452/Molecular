package organicsCoordinateService;

import organicsUtil.Mole;

public class SymmetryMoleService {
	//获得对称点的坐标
	public static void symmetry(Mole moleA,Mole moleCenter,Mole moleB) {
		moleB.setX(moleCenter.x*2-moleA.x);
		moleB.setY(moleCenter.y*2-moleA.y);
		moleB.setZ(moleCenter.z*2-moleA.z);
	}
}
