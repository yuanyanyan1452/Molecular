function transformMoleFormula(cNumber,hNumber,oNumber){
  var bonds=new Array();
  //饱和醇
  if ((cNumber * 2 + 2) == hNumber && oNumber == 1) {
    //先分配碳碳键
    for (var i = 1; i <= cNumber - 1; i++) {
      bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for(var i=1;i<=cNumber;i++){
      if(i==cNumber){
        for(var j=0;j<3;j++)bonds.push("C"+i+"H"+"CHTeSingleBond");
      }else{
        for(var j=0;j<2;j++)bonds.push("C"+i+"H"+"CHTeSingleBond");
      }
    }
    bonds.push("C"+cNumber+"O1"+"COTeSingleBond");
    bonds.push("O1H"+"OH90SingleBond");
  } else if (cNumber * 2 == hNumber && oNumber == 1 && cNumber >= 2) {//不饱和度为1的醇
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C1C2" + "CC120DoubleBond");
      else if (i == 2) bonds.push("C2C3" + "CC120SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for(var i=1;i<=cNumber;i++){
      if(i==1){
        for(var j=0;j<2;j++)bonds.push("C"+i+"H"+"CH120SingleBond");
      }else if(i==2)bonds.push("C"+i+"H"+"CH120SingleBond");
      else{
        for(var j=0;j,2;j++)bonds.push("C"+i+"H"+"CHTeSingleBond");
      }
    }
    if(cNumber==2){
      bonds.push("C"+cNumber+"O1"+"CO120SingleBond");
      bonds.push("O1H"+"OH90SingleBond");
    }else{
      bonds.push("C" + cNumber + "O1" + "COTeSingleBond");
      bonds.push("O1H" + "OH90SingleBond");
    }
  } else if ((cNumber * 2 - 2) == hNumber) {//炔
    for (var i = 1; i <= cNumber - 1; i++) {
      if (i == 1) bonds.push("C" + i + "C" + (i + 1) + "CC180TripleBond");
      else if (i == 2) bonds.push("C" + i + "C" + (i + 1) + "CC180SingleBond");
      else bonds.push("C" + i + "C" + (i + 1) + "CCTeSingleBond");
    }
    for(var i=1;i<=cNumber;i++){
      if(i==1)bonds.push("C"+i+"H"+"CH180SingleBond");
      else if(i>2){
        for(var j=0;j<2;j++)bonds.push("C"+i+"H"+"CHTeSingleBonds");
      }
    }
    if(cNumber==2){
      bonds.push("C" + cNumber + "O1" + "CO180SingleBond");
      bonds.push("O1H" + "OH90SingleBond");
    }else{
      bonds.push("C" + cNumber + "O1" + "COTeSingleBond");
      bonds.push("O1H" + "OH90SingleBond");
    }
  }
  return bonds;
}

module.exports={
  transformMoleFormula:transformMoleFormula
}
