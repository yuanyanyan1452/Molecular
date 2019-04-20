function transformMoleFormula(cNumber,hNumber){
  var bonds= new Array();
  var map=new Array();
  var count=0;
  //烷烃
  if ((cNumber * 2 + 2) == hNumber) {
    //先分配碳碳键
    for (var i = 1; i <= cNumber - 1; i++,count++) {
      bonds[count]="C" + i + "C" + (i + 1) + "CCTeSingleBond";
    }
    //再分配各个碳上面剩下的空闲的键
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1 && i == cNumber) map[i]=4;
      else if (i == 1 && i < cNumber) map[i]=3;
      else if (i == cNumber) map[i]=3;
      else map[i]=2;
    }
    //分配氢键
    for (var i = 1; i <= cNumber; i++) {
      while (map[i] > 0) {
        bonds[count]="C" + i + "H" + "CHTeSingleBond";
        map[i]=map[i] - 1;
        count++;
      }
    }
  } else if (cNumber * 2 == hNumber&&cNumber>=2) {//一烯烃 
    //先分配碳碳键
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds[count] = "C" + i + "C" + (i + 1) + "CC120DoubleBond";
      else if (i == 2) {
        bonds[count] = "C" + i + "C" + (i + 1) + "CC120SingleBond";
      } else {
        bonds[count] = "C" + i + "C" + (i + 1) + "CCTeSingleBond";
      }
      count++;
    }
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1) map[i] = 2;
      else if (i == 2 && i == cNumber) map[i] = 2;
      else if (i == 2 && i < cNumber) map[i] = 1;
      else if (i == cNumber) map[i] = 3;
      else map[i] = 2;
    }
    for (var i = 1; i <= cNumber; i++) {
      while (map[i] > 0) {
        if (i == 1 || i == 2) {
          bonds[count] = "C" + i + "H" + "CH120SingleBond";
        } else {
          bonds[count] = "C" + i + "H" + "CHTeSingleBond";
        }
        map[i] = map[i] - 1;
        count++;
      }
    }
  }
  return bonds;

}
module.exports = {
  transformMoleFormula: transformMoleFormula,
}