/*
炔烃的canvas绘画
待解决：画布的大小有限，当碳原子很多的时候无法展示
*/
function drawAlkyne(context, bonds, cNumber) {
  var sX = 50; var sY = 100;
  //起点碳原子
  context.arc(sX, sY, 10, 0, 2 * Math.PI);
  context.setFillStyle("#38261a");
  context.fill();
  context.draw();
  //确定方向
  var threeDirec = 1;
  var twoDirec = 1;
  bonds.forEach(function (item, array) {
    var res = new RegExp("[CH]", "g");
    var array = item.split(res);
    //先解决其余的碳原子
    if (array[2] != "") {
      var temp = sX + 154 * (array[2] - 1) / 3;
      context.moveTo(sX, sY);
      context.lineTo(temp, sY);
      context.stroke();
      context.draw(true);
      if(array[1]==1){
        context.moveTo(sX, sY-5);
        context.lineTo(temp, sY-5);
        context.stroke();
        context.draw(true);
        context.moveTo(sX, sY+5);
        context.lineTo(temp, sY+5);
        context.stroke();
        context.draw(true);
      }
      context.arc(temp, sY, 10, 0, 2 * Math.PI);
      context.setFillStyle("#38261a");
    } else {//解决氢原子
      var temp = 109 / 3;
      var cX = sX + 154 * (array[1] - 1) / 3;
      if (array[1] == 1 || array[1] == cNumber) {//三个氢
        switch (threeDirec % 3) {
          case 1:
            if (threeDirec > 3 ) {
              context.moveTo(cX, sY)
              context.lineTo(cX, sY+temp);
              context.stroke();
              context.draw(true);
              context.arc(cX, sY+temp, 10, 0, 2 * Math.PI);
            } else {
              context.moveTo(cX, sY)
              context.lineTo(cX-temp, sY);
              context.stroke();
              context.draw(true);
              context.arc(cX-temp, sY, 10, 0, 2 * Math.PI);
            }
            break;
          case 2:
              if(cNumber==2){
                context.moveTo(cX, sY)
                context.lineTo(cX+temp, sY);
                context.stroke();
                context.draw(true);
                context.arc(cX+temp, sY, 10, 0, 2 * Math.PI);
              }else{
                context.moveTo(cX, sY)
                context.lineTo(cX, sY - temp);
                context.stroke();
                context.draw(true);
                context.arc(cX, sY - temp, 10, 0, 2 * Math.PI);
              }
              
            break;
          case 0:
            context.moveTo(cX, sY)
            context.lineTo(cX+temp, sY);
            context.stroke();
            context.draw(true);
            context.arc(cX+temp, sY, 10, 0, 2 * Math.PI);
            break;
          default:
            break;
        }
        context.setFillStyle("#7CCD7C");
        threeDirec++;
      } else {//2个氢原子
        switch (twoDirec % 2) {
          case 1:
            context.moveTo(cX, sY);
            context.lineTo(cX, sY - temp);
            context.stroke();
            context.draw(true);
            context.arc(cX, sY - temp, 10, 0, 2 * Math.PI);
            break;
          case 0:
            context.moveTo(cX, sY);
            context.lineTo(cX, sY + temp);
            context.stroke();
            context.draw(true);
            context.arc(cX, sY + temp, 10, 0, 2 * Math.PI);
            break;
          default:
            break;
        }
        context.setFillStyle("#7CCD7C");
        twoDirec++;
      }
    }
    context.fill();
    context.draw(true);
  });
}
module.exports = {
  drawAlkyne: drawAlkyne,
}