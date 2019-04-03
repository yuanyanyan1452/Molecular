package organicsService;
import java.util.Scanner;

public class InputMoleFormula {
	/*
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
	 * 获得分子式中碳原子数量和氢原子数量
	 * return：int[] result
	 * result[0]:cNumber
	 * result[1]:hNumber
	 */
	public static int[] getNumber(String moleFormula) {
		int location=0;
		for(int i=0;i<moleFormula.length();i++) {
			char temp=moleFormula.charAt(i);
			if(temp=='H'||temp=='h') {
				location=i;
				break;
			}
		}
		int cNumber=Integer.parseInt(moleFormula.substring(1, location));
		int hNumber=Integer.parseInt(moleFormula.substring(location+1));
		int[] result=new int[2];
		result[0]=cNumber;
		result[1]=hNumber;
		return result;
	}

}
