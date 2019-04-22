var sX = 120; var sY = 100;
//定下键长(待补充)
var ccLength = 154 / 3;
var chLength = 109 / 3;
var coDoublelength = 120 / 3;
function drawOrganics(context,bonds,cNumber,hNumber,oNumber,type){
  if(cNumber>=4)context.scale(0.5,0.5);
  //先输出有机物的名称
  context.setTextAlign("center");
  context.setFontSize(20);
  context.fillText(type, 150, 40);
  context.draw();
  //定下起点碳原子
  context.arc(sX, sY, 10, 0, 2 * Math.PI);
  context.setFillStyle("#38261a");
  context.fill();
  context.draw(true);
  var cMap=new Map();
  for(var i=1;i<=cNumber;i++){
    var direc=[1,1,1,1];
    cMap.set(i,direc);//每个碳都是四个方向
  }
  for(var i=1;i<=oNumber;i++){
    cMap.set("O"+i,[1,1]);//每个氧是两个方向，关键字是O1，O2之类的
  }
  for(var i=0;i<bonds.length;i++){
    var bond=bonds[i];
    var sMole=bond.charAt(0);
    if(sMole!="H"){
      var sSe = bond.charAt(1);
      var eMole = bond.charAt(2);
      var eSe = isNaN(bond.charAt(3)) ? 0 : bond.charAt(3);//如果是氢原子则没有序号
    }else{
      var sSe = 0;
      var eMole = bond.charAt(1);
      var eSe = isNaN(bond.charAt(2)) ? 0 : bond.charAt(2);//如果是氢原子则没有序号
    }
      sSe=sSe*1;eSe=eSe*1;//转变为number类型
    if(sMole=="C"&&eMole=="C"){
      context.moveTo(sX+ccLength * (eSe - 2), sY);//前一个碳为起点
      context.lineTo(sX + ccLength * (eSe - 1), sY);
      context.stroke();
      context.draw(true);
      cMap.get(sSe).splice(0,1,"0");
      cMap.get(eSe).splice(2,1,"0");
      if(bond.includes("Double")){//碳碳双键的话就多画一个
        context.moveTo(sX + ccLength * (eSe - 2), sY+5);//前一个碳为起点
        context.lineTo(sX + ccLength * (eSe - 1), sY+5);
        context.stroke();
        context.draw(true);
        cMap.get(sSe).splice(3, 1, "0");
        cMap.get(eSe).splice(3, 1, "0");
      }else if(bond.includes("Triple")){//碳碳三键的话就多画两个
        context.moveTo(sX + ccLength * (eSe - 2), sY + 5);//前一个碳为起点
        context.lineTo(sX + ccLength * (eSe - 1), sY + 5);
        context.stroke();
        context.draw(true);
        context.moveTo(sX + ccLength * (eSe - 2), sY - 5);//前一个碳为起点
        context.lineTo(sX + ccLength * (eSe - 1), sY - 5);
        context.stroke();
        context.draw(true);
        cMap.get(sSe).splice(1, 1, "0");
        cMap.get(eSe).splice(3, 1, "0");
        cMap.get(sSe).splice(1, 1, "0");
        cMap.get(eSe).splice(3, 1, "0");
      }
      context.arc(sX + ccLength * (eSe - 1),sY, 10, 0, 2 * Math.PI);
      context.setFillStyle("#38261a");
      context.fill();
      context.draw(true);
    }else if(sMole=="C"&&eMole=="H"){
      var currentDire=cMap.get(sSe);
      for(var j=0;j<4;j++){
        if(currentDire[j]==1){
          drawH(sSe, j, context, cMap);
          break;
        }
      }
    }else if(sMole=="C"&&eMole=="O"){
      if(bond.includes("Double")){
        context.moveTo(sX + ccLength * (sSe - 1), sY);
        context.lineTo(sX + ccLength * (sSe - 1), sY - coDoublelength);
        context.stroke();
        context.draw(true);
        context.moveTo(sX + ccLength * (sSe - 1) + 5, sY);
        context.lineTo(sX + ccLength * (sSe - 1) + 5, sY - coDoublelength);
        context.stroke();
        context.draw(true);
        context.arc(sX + ccLength * (sSe - 1), sY - coDoublelength, 10, 0, 2 * Math.PI);
        context.setFillStyle("#5ea8f3");
        context.fill();
        context.draw(true);
        cMap.get(sSe).splice(1, 1, "0");
        cMap.get(sSe).splice(3, 1, "0");
        cMap.get("O"+eSe).splice(0,1,"0");
        cMap.get("O" + eSe).splice(1, 1, "0");
      }else{
        context.moveTo(sX + ccLength * (sSe - 1), sY );//第一个碳为起点
        context.lineTo(sX + ccLength * (sSe)-ccLength/2, sY );
        context.stroke();
        context.draw(true);
        context.arc(sX + ccLength * (sSe)-ccLength/2, sY, 10, 0, 2 * Math.PI);
        context.setFillStyle("#5ea8f3");
        context.fill();
        context.draw(true);
        cMap.get(sSe).splice(0, 1, "0");
        cMap.get("O"+eSe).splice(1,1,"0");
      }
    }else if(sMole=="O"&&eMole=="C"){
      context.moveTo(sX + ccLength * (eSe - 1)-ccLength/2, sY);//碳的后一半为起点
      context.lineTo(sX + ccLength * (eSe-1), sY);
      context.stroke();
      context.draw(true);
      context.arc(sX + ccLength * (eSe-1), sY, 10, 0, 2 * Math.PI);
      context.setFillStyle("#38261a");
      context.fill();
      context.draw(true);
      cMap.get(eSe).splice(2, 1, "0");
      cMap.get("O" + sSe).splice(0, 1, "0");
    }else if(sMole=="H"&&eMole=="O"){
      context.moveTo(sX-2*ccLength,sY);
      context.arc(sX-2*ccLength,sY,10,0,2*Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      context.moveTo(sX - 2 * ccLength, sY);
      context.lineTo(sX,sY);
      context.stroke();
      context.draw(true);
      context.arc(sX-ccLength,sY,10,0,2*Math.PI);
      context.setFillStyle("#5ea8f3");
      context.fill();
      context.draw(true);
      cMap.get("O"+eSe).splice(1,1,"0");
    }else if(sMole=="O"&&eMole=="H"){
      context.moveTo(sX+ccLength*(cNumber),sY);
      context.lineTo(sX + ccLength * (cNumber)+chLength,sY);
      context.stroke();
      context.draw(true);
      context.arc(sX + ccLength * (cNumber) + chLength, sY,10,0,2*Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      cMap.get("O1").splice(0,1,"0");
    }
  }
}
//画完氢之后记得减掉碳上面一个空闲键
function drawH(cSe,leftBond,context,cMap){
  var cX = sX + ccLength * (cSe - 1);
  context.moveTo(cX, sY);
  switch(leftBond){
    case 0:
      context.lineTo(cX+chLength, sY);
      context.stroke();
      context.draw(true);
      context.arc(cX + chLength, sY, 10, 0, 2 * Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      break;
    case 1:
      context.lineTo(cX , sY - chLength);
      context.stroke();
      context.draw(true);
      context.arc(cX, sY - chLength, 10, 0, 2 * Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      break;
    case 2:
      context.lineTo(cX - chLength, sY);
      context.stroke();
      context.draw(true);
      context.arc(cX -chLength, sY, 10, 0, 2 * Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      break;
    case 3:
      context.lineTo(cX , sY + chLength);
      context.stroke();
      context.draw(true);
      context.arc(cX, sY + chLength, 10, 0, 2 * Math.PI);
      context.setFillStyle("#7CCD7C");
      context.fill();
      context.draw(true);
      break;
    default:
      break;
  }
  cMap.get(cSe).splice(leftBond, 1, "0");
}
function drawBenRing(context){
  context.moveTo(sX,sY);
  context.lineTo(sX-5,sY-5*Math.sqrt(3));
  context.stroke();
  context.draw();
}
module.exports={
  drawOrganics: drawOrganics
}