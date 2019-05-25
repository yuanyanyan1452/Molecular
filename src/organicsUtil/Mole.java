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
		if(name.startsWith("CL")||name.startsWith("cl")) {
			this.type="CL";
			this.size=9.9;
		}else if(name.startsWith("H")||name.startsWith("h")) {
			this.type="H";
			this.size=3.7;
		}else if(name.startsWith("O")||name.startsWith("o")) {
			this.type="O";
			this.size=7.4;
		}else if(name.startsWith("F")||name.startsWith("f")) {
			this.type="F";
			this.size=7.1;
		}else if(name.startsWith("C")||name.startsWith("c")) {
			this.type="C";
			this.size=7.7;
		}else if(name.startsWith("BR")||name.startsWith("br")){
			this.type="BR";
			this.size=19.6;//溴原子呈-1价时的原子半径
		}else if(name.startsWith("I")||name.startsWith("i")) {
			this.type="I";
			this.size=14;
		}
	}
}
