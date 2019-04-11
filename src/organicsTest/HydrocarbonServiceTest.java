package organicsTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import organicsUtil.BondType;

//对于碳氢化合物的测试
class HydrocarbonServiceTest {
	LinkedList<String> bonds=new LinkedList<String>();
	@Test
	void testTransformMoleFormula() {
		//烷烃
		bonds=BaseTest.test("ch4");
		for(int i=0;i<bonds.size();i++) {
			assertEquals(bonds.get(i),"C1HCHTeSingleBond");
		}
		bonds=BaseTest.test("c2h6");
		assertEquals(bonds.get(0),"C1C2CCTeSingleBond");
		for(int i=1;i<bonds.size();i++) {
			if(i<=3)assertEquals(bonds.get(i),"C1HCHTeSingleBond");
			else assertEquals(bonds.get(i),"C2HCHTeSingleBond");
		}
		bonds=BaseTest.test("c100h202");
		for(int i=1;i<=99;i++) {
			assertEquals(bonds.get(i-1),"C"+i+"C"+(i+1)+"CCTeSingleBond");
		}
		for(int i=99;i<=101;i++) {
			assertEquals(bonds.get(i),"C1HCHTeSingleBond");
		}
		int count=102;
		for(int i=2;i<=99;i++) {
			assertEquals(bonds.get(count),"C"+i+"H"+"CHTeSingleBond");
			assertEquals(bonds.get(++count),"C"+i+"H"+"CHTeSingleBond");
			count++;
		}
		for(int i=1;i<=3;i++) {
			assertEquals(bonds.get(count),"C100HCHTeSingleBond");
			count++;
		}
		//一烯烃
		bonds=BaseTest.test("c2h4");
		assertEquals(bonds.get(0),"C1C2CC120DoubleBond");
		for(int i=1;i<=4;i++) {
			if(i==1||i==2)assertEquals(bonds.get(i),"C1HCH120SingleBond");
			else assertEquals(bonds.get(i),"C2HCH120SingleBond");
		}
		bonds=BaseTest.test("c3h6");
		for(int i=0;i<bonds.size();i++) {
			String temp=bonds.get(i);
			if(i==0)assertEquals(temp,"C1C2CC120DoubleBond");
			else if(i==1)assertEquals(temp,"C2C3CC120SingleBond");
			else if(i==2||i==3)assertEquals(temp,"C1HCH120SingleBond");
			else if(i==4)assertEquals(temp,"C2HCH120SingleBond");
			else {
				assertEquals(temp,"C3HCHTeSingleBond");
			}
		}
		bonds=BaseTest.test("c100h200");
		count=3;
		for(int i=0;i<bonds.size();i++) {
			String temp=bonds.get(i);
			if(i==0)assertEquals(temp,"C1C2CC120DoubleBond");
			else if(i==1)assertEquals(temp,"C2C3CC120SingleBond");
			else if(i<=98)assertEquals(temp,"C"+(i+1)+"C"+(i+2)+BondType.CCTeSingleBond);
			else if(i<=100)assertEquals(temp,"C1HCH120SingleBond");
			else if(i==101)assertEquals(temp,"C2HCH120SingleBond");
			else {
				assertEquals(temp,"C"+count+"H"+BondType.CHTeSingleBond);
				if(i+1<bonds.size()) {
					i++;temp=bonds.get(i);
				}else break;
				assertEquals(temp,"C"+count+"H"+BondType.CHTeSingleBond);
				if(count<100)
					count++;
			}
		}
	}

}
