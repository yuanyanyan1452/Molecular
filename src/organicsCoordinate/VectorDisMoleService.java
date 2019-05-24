package organicsCoordinate;

import organicsUtil.Mole;

public class VectorDisMoleService {
	public static void vectorDisMole(Mole moleA,Mole moleB,double dis,Mole moleC) {
		//根据确定的向量方向和距离求未知点坐标，详情见印象笔记
		double xDiff=moleB.x-moleA.x;
		double yDiff=moleB.y-moleA.y;
		double zDiff=moleB.z-moleA.z;
		double dis2=Math.sqrt(xDiff*xDiff+yDiff*yDiff+zDiff*zDiff);
		double k=dis/dis2;
		moleC.setX(k*xDiff+moleB.x);
		moleC.setY(k*yDiff+moleB.y);
		moleC.setZ(k*zDiff+moleB.z);
	}
}
