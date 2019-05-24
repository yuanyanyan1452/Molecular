package organicsUtil;

public class Mole {
	//原子的名称和坐标
	public String name;
	public double x;
	public double y;
	public double z;

	
	public Mole(String name,double x,double y,double z) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Mole(String name) {
		this.name=name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setX(double x) {
		this.x=x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public void setZ(double z) {
		this.z=z;
	}
	public String toString() {
		return this.name+" "+this.x+" "+this.y+" "+this.z;
	}
}
