package organicsUtil;

public class Mole {
	//原子的名称和坐标
	public String name;
	public double x;
	public double y;
	public double z;
	public String type;
	public double size;

	
	public Mole(String name,double x,double y,double z) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.z=z;
		init(name);
	}
	public Mole(String name) {
		this.name=name;
		init(name);
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
		return this.type+" "+this.size+" "+this.name+" "+this.x+" "+this.y+" "+this.z;
	}
	public void init(String name) {
		for(int i=0;i<name.length();i++) {
			char temp=name.charAt(i);
			if(temp>=48&&temp<=57) {
				String s=name.substring(0, i).toUpperCase();
				this.type=s;
				this.size=MoleProperty.sizeByType.get(s);
				break;
			}
		}
	}
}
