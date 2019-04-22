/*
醛类的解析类
*/
function transformMoleFormula(cNumber,hNumber,oNumber){
  var bonds=new Array();
  if (cNumber * 2 == hNumber && oNumber == 1) {
    for (var i = 1; i <= cNumber - 1; i++) {
      bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1 && i == cNumber) {
        bonds.push("C" + i + "H" + "CH120SingleBond");
      } else if (i == 1 && i != cNumber) {
        for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      } else if (i == cNumber) {
        break;
      } else {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2) - 2 == hNumber && cNumber >= 3) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120DoubleBen");
      else if (i == 2) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else  {
       bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
      }
    }
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CH120SingleBond");
      } else if (i == 2) bonds.push("C" + i + "H" + "CH120SingleBond");
      else {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2 - 4) == hNumber && cNumber >= 3) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC180TripleBond");
      else if (i == 2 ) bonds.push("C" + i + "C" + (i + 1) + "CC180SingleBond");
      else  {
      bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
      }
    }
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "H" + "CH180SingleBond");
      else if (i == 2) continue;
      else for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
    }
  }
  if(bonds!=undefined&&bonds.length!=0){
    bonds.push("C" + cNumber + "O1" + "CO120DoubleBond");
    bonds.push("C" + cNumber + "H" + "CH120SingleBond");
  }
  return bonds;
}

module.exports={
  transformMoleFormula:transformMoleFormula
}