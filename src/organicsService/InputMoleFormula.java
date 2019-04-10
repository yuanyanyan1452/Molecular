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
	 * 获得分子式中碳原子数量和氢原子数量和
	 * 卤代烃中的X原子数量或者醇中的氧原子X数量
	 * return：int[] result
	 * result[0]:cNumber
	 * result[1]:hNumber
	 * result[2]:xNumber
	 */
	public static int[] getNumber(String moleFormula) {
		int location=0;
		int len=moleFormula.length();
		for(int i=0;i<len;i++) {
			char temp=moleFormula.charAt(i);
			if(temp=='H'||temp=='h') {
				location=i;
				break;
			}
		}
		int cNumber=1==location?1:Integer.parseInt(moleFormula.substring(1, location));
		int hNumber=0;
		int locationX=0;
		int locationO=0;
		for(int i=location;i<len;i++) {
			char temp=moleFormula.charAt(i);
			if(temp=='F'||temp=='f'||temp=='I'||temp=='i') {
				locationX=i;
				hNumber=(location+1)==(locationX)?1:Integer.parseInt(moleFormula.substring(location+1,locationX));
				break;
			}else if(temp=='C'||temp=='c') {
				temp=moleFormula.charAt(i+1);
				if(temp=='l'||temp=='L') {
					locationX=i+1;
					hNumber=(location+1)==(locationX-1)?1:Integer.parseInt(moleFormula.substring(location+1,locationX-1));
					break;
				}
			}else if(temp=='B'||temp=='b') {
				temp=moleFormula.charAt(i+1);
				if(temp=='r'||temp=='R') {
					locationX=i+1;
					hNumber=(location+1)==(locationX-1)?1:Integer.parseInt(moleFormula.substring(location+1,locationX-1));
					break;
				}
			}else if(temp=='O'||temp=='o') {
				locationX=i;
				hNumber=(location+1)==(locationX)?1:Integer.parseInt(moleFormula.substring(location+1,locationX));
				break;
			}
		}
		
		int xNumber=(locationX+1)==len?1:Integer.parseInt(moleFormula.substring(locationX+1));
		int[] result=new int[3];
		result[0]=cNumber;
		result[1]=hNumber;
		result[2]=xNumber;
		return result;
	}

}
