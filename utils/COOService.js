function transformMoleFormula(cNumber,hNumber,oNumber){
  var bonds=new Array();
  if (cNumber * 2 == hNumber && cNumber >= 3) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else if (i == 2) bonds.push("C" + i + "O1" + "CO120SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    bonds.push("O1C3" + "COTeSingleBond");
    bonds.push("C2O2" + "CO120DoubleBond");
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1 || i == cNumber) {
        for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      } else if (i != 2) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2 - 2) == hNumber && cNumber >= 4) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120DoubleBond");
      else if (i == 2) bonds.push("C" + i + "C" + (i + 1) + "CC120SingleBond");
      else if (i == 3) bonds.push("C" + i + "O1" + "CO120SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    bonds.push("O1C4" + "COTeSingleBond");
    bonds.push("C3O2" + "CO120DoubleBond");
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1) {
        for (var j = 0; j < 2; j++) {
          bonds.push("C" + i + "H" + "CH120SingleBond");
        }
      } else if (i == 2) bonds.push("C" + i + "H" + "CH120SingleBond");
      else if (i == cNumber) {
        for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      } else if (i != 3) {
        bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  } else if ((cNumber * 2 - 4) == hNumber && cNumber >= 4) {
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC180TripleBond");
      else if (i == 2) bonds.push("C" + i + "C" + (i + 1) + "CC180SingleBond");
      else if (i == 3) bonds.push("C" + i + "O1" + "CO120SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    bonds.push("O1C4" + "COTeSingleBond");
    bonds.push("C3O2" + "CO120DoubleBond");
    for (var i = 1; i <= cNumber; i++) {
      if (i == 1) bonds.push("C" + i + "H" + "CH180SingleBond");
      else if (i == cNumber) {
        for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      } else if (i != 2 && i != 3) {
        for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
      }
    }
  }
  return bonds;
}
module.exports={
  transformMoleFormula:transformMoleFormula
}