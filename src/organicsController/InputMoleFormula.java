package organicsController;
import java.util.Scanner;
import java.util.LinkedList;
public class InputMoleFormula {
	/*test
	 * 获取输入的分子式字符串
	 * return:string
	 */
	public static String getMoleFormula() {
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		sc.close();
		return str;
	}
	/*
	 * 获得分子式中碳原子数量和氢原子数量和
	 * 卤代烃中的X原子数量或者醇中的氧原子X数量
	 * return：LinkedList<Integer>
	 * 将原子数量按照输入分子式的顺序排列
	 * 所有的location都是数组下标即从0开始
	 */
	public static LinkedList<Integer> getNumber(String moleFormula) {
		LinkedList<Integer> list=new LinkedList<Integer>();
		int locationH=0;
		int len=moleFormula.length();
		for(int i=0;i<len;i++) {
			char temp=moleFormula.charAt(i);
			if(temp=='H'||temp=='h') {
				locationH=i;
				break;
			}
		}
		int cNumber=1==locationH?1:Integer.parseInt(moleFormula.substring(1, locationH));
		list.add(cNumber);
		String leftString=moleFormula.substring(locationH+1);
		if(leftString.matches("[0-9]{0,}")) {
			int hNumber=Integer.valueOf(leftString);
			list.add(hNumber);
			return list;
		}else {
			int hNumber=0;
			int locationX=0;
			for(int i=locationH;i<len;i++) {
				char temp=moleFormula.charAt(i);
				if(temp=='F'||temp=='f'||temp=='I'||temp=='i') {
					locationX=i;
					hNumber=(locationH+1)==(locationX)?1:Integer.parseInt(moleFormula.substring(locationH+1,locationX));
					break;
				}else if(temp=='C'||temp=='c') {
					temp=moleFormula.charAt(i+1);
					if(temp=='l'||temp=='L') {
						locationX=i+1;
						hNumber=(locationH+1)==(locationX-1)?1:Integer.parseInt(moleFormula.substring(locationH+1,locationX-1));
						break;
					}
				}else if(temp=='B'||temp=='b') {
					temp=moleFormula.charAt(i+1);
					if(temp=='r'||temp=='R') {
						locationX=i+1;
						hNumber=(locationH+1)==(locationX-1)?1:Integer.parseInt(moleFormula.substring(locationH+1,locationX-1));
						break;
					}
				}else if(temp=='O'||temp=='o') {
					locationX=i;
					hNumber=(locationH+1)==(locationX)?1:Integer.parseInt(moleFormula.substring(locationH+1,locationX));
					break;
				}
			}
			int xNumber=(locationX+1)==len?1:Integer.parseInt(moleFormula.substring(locationX+1));
			list.add(hNumber);
			list.add(xNumber);
			return list;
		}
		
	}

}
