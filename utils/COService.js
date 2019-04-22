/*
醚类的解析类
*/
function transformMoleFormula(cNumber, hNumber, oNumber) {
  var bonds = new Array();
  if (oNumber == 1 && cNumber >= 2) {
    if ((cNumber * 2 + 2) == hNumber) {
      for (var i = 1; i <= cNumber - 1; i++) {
        if (i == 1) bonds.push("C" + i + "O1" + "COTeSingleBond");
        else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
      }
      bonds.push("O1C2" + "COTeSingleBond");
      for (var i = 1; i <= cNumber; i++) {
        if (i == 1 || i == cNumber) {
          for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        } else {
          for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        }
      }
    } else if (cNumber * 2 == hNumber&&cNumber>=3) {
      for (var i = 1; i <= cNumber - 1; i++) {
        if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC120DoubleBond");
        else if (i == 2) bonds.push("C" + i + "O1" + "CO120SingleBond");
        else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
      }
      bonds.push("O1C3" + "COTeSingleBond");
      for (var i = 1; i <= cNumber; i++) {
        if (i == 1) {
          for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CH120SingleBond");
        } else if (i == 2) bonds.push("C" + i + "H" + "CH120SingleBond");
        else if (i == cNumber) {
          for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        } else {
          for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        }
      }
    } else if ((cNumber * 2 - 2) == hNumber) {
      for (var i = 1; i <= cNumber - 1; i++) {
        if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC180TripleBond");
        else if (i == 2) bonds.push("C" + i + "O1" + "CO180SingleBond");
        else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
      }
      bonds.push("O1C3" + "COTeSingleBond");
      for (var i = 1; i <= cNumber; i++) {
        if (i == 1) bonds.push("C" + i + "H" + "CH180SingleBond");
        else if (i == cNumber) {
          for (var j = 0; j < 3; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        } else if (i != 2) {
          for (var j = 0; j < 2; j++) bonds.push("C" + i + "H" + "CHTeSingleBond");
        }
      }
    }
  }
  return bonds;
}
module.exports = {
  transformMoleFormula: transformMoleFormula
}