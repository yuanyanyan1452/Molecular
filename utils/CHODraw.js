function drawCHO(context,bonds,cNumber,hNumber){
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
    var res = new RegExp("[CHO]", "g");
    var array = item.split(res);
    //先解决其余的碳原子
    if (array[2] != "") {
      if(array[1]==cNumber){
        var temp = sX + 154 * (cNumber - 1) / 3;
        var bondLength=120/3;
        context.moveTo(temp,sY);
        context.lineTo(temp,sY-bondLength);
        context.stroke();
        context.draw(true);
        context.moveTo(temp+5, sY);
        context.lineTo(temp+5, sY - bondLength);
        context.stroke();
        context.draw(true);
        if(cNumber==1){
          context.moveTo(sX, sY);
          context.lineTo(sX-109/3, sY);
          context.stroke();
          context.draw(true);
          context.arc(sX-109/3, sY, 10, 0, 2 * Math.PI);
          context.setFillStyle("#7CCD7C");
          context.fill();
          context.draw(true);
        }
        context.arc(temp, sY-bondLength, 10, 0, 2 * Math.PI);
        context.setFillStyle("#5ea8f3");
      }else{
        var temp = sX + 154 * (array[2] - 1) / 3;
        context.moveTo(sX, sY);
        context.lineTo(temp, sY);
        context.stroke();
        context.draw(true);
        context.arc(temp, sY, 10, 0, 2 * Math.PI);
        context.setFillStyle("#38261a");
      }
    } else {//解决氢原子
      var temp = 109 / 3;
      var cX = sX + 154 * (array[1] - 1) / 3;
      if (array[1] == 1 &&cNumber!=1) {//三个氢
        switch (threeDirec % 3) {
          case 1:
            if (threeDirec > 3 && cNumber == 1) {
              context.moveTo(cX, sY)
              context.lineTo(cX + temp, sY);
              context.stroke();
              context.draw(true);
              context.arc(cX + temp, sY, 10, 0, 2 * Math.PI);
            } else {
              context.moveTo(cX, sY)
              context.lineTo(cX, sY - temp);
              context.stroke();
              context.draw(true);
              context.arc(cX, sY - temp, 10, 0, 2 * Math.PI);
            }
            break;
          case 2:
            if (threeDirec > 3) {
              context.moveTo(cX, sY)
              context.lineTo(cX + temp, sY);
              context.stroke();
              context.draw(true);
              context.arc(cX + temp, sY, 10, 0, 2 * Math.PI);
            } else {
              context.moveTo(cX, sY)
              context.lineTo(cX - temp, sY);
              context.stroke();
              context.draw(true);
              context.arc(cX - temp, sY, 10, 0, 2 * Math.PI);
            }
            break;
          case 0:
            context.moveTo(cX, sY)
            context.lineTo(cX, sY + temp);
            context.stroke();
            context.draw(true);
            context.arc(cX, sY + temp, 10, 0, 2 * Math.PI);
            break;
          default:
            break;
        }
        context.setFillStyle("#7CCD7C");
        threeDirec++;
      } else if(array[1]==cNumber){
        if(cNumber==1){
          context.moveTo(cX, sY);
          context.lineTo(cX -temp, sY);
          context.stroke();
          context.draw(true);
          context.arc(cX -temp, sY, 10, 0, 2 * Math.PI);
          context.setFillStyle("#7CCD7C");
          context.fill();
          context.draw(true);
        }
        context.moveTo(cX, sY);
        context.lineTo(cX+temp, sY);
        context.stroke();
        context.draw(true);
        context.arc(cX+temp, sY, 10, 0, 2 * Math.PI);
        context.setFillStyle("#7CCD7C");
        context.fill();
        context.draw(true);

      }else{//2个氢原子
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
module.exports={
  drawCHO:drawCHO
}