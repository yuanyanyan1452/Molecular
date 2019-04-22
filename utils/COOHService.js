function transformMoleFormula(cNumber,hNumber,oNumber){
  var bonds=new Array();
  if (cNumber * 2 == hNumber) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1 && i == cNumber) bonds.push("C" + i + "H" + "CH120SingleBond");
      else if (i != 1 && i == cNumber) {
        for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      } else if (i != 1) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2 - 2) == hNumber) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else if (i == cNumber - 2) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else if (i == cNumber - 1) bonds.push("C" + i + "C" + (i + 1) + "CC120DoubleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for (var i = 1; i <= cNumber; i++) {
      if (i == cNumber) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CH120SingleBond");
      } else if (i == cNumber - 1) bonds.push("C" + i + "H" + "CH120SingleBond");
      else if (i != 1) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2 - 4) == hNumber) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else if (i == cNumber - 1) bonds.push("C" + i + "C" + (i + 1) + "CC180TripleBond");
      else if (i == cNumber - 2) bonds.push("C" + i + "C" + (i + 1) + "CC180SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for (var i = 1; i <= cNumber; i++) {
      if (i == cNumber) bonds.push("C" + i + "H" + "CH180SingleBond");
      else if (i != 1 && i != cNumber - 1) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  }
  bonds.push("C1O1CO120DoubleBond");
  bonds.push("O2C1CO120SingleBond");
  bonds.push("HO2OH90SingleBond");
  return bonds;
}
module.exports={
  transformMoleFormula: transformMoleFormula
}