// pages/input/input.js
var hydrocarbonService = require("../../utils/hydrocarbonService.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cNumber:1,
    hNumber:1,
    oNumber:0
  },
  bindInput1:function(e){
    var n=e.detail.value;
    this.setData({cNumber:n});
  },
  bindInput2:function(e){
    var n=e.detail.value;
    this.setData({hNumber:n});
  },
  bindInput3:function(e){
    var n=e.detail.value;
    if(n!=0)this.setData({oNumber:n});
  },
  transformService:function(e){
    var bonds = hydrocarbonService.transformMoleFormula(this.data.cNumber, this.data.hNumber);
    const context = wx.createCanvasContext('Canvas');
    var cNumber=this.data.cNumber;
    console.log(cNumber);
    var sX=50;var sY=100;
    //起点碳原子
    context.arc(sX, sY, 10, 0, 2 * Math.PI);
    context.setFillStyle("#38261a");
    context.fill();
    context.draw();
    //确定方向
    var threeDirec = 1;
    var twoDirec=1;
    bonds.forEach(function(item,array){
      var res=new RegExp("[CH]","g");
      var array=item.split(res);
      //先解决其余的碳原子
      if(array[2]!=""){
        var temp = sX + 154 * (array[2] - 1) / 3;
        context.moveTo(sX, sY);
        context.lineTo(temp, sY);
        context.stroke();
        context.draw(true);
        context.arc( temp, sY,10, 0, 2 * Math.PI);
        context.setFillStyle("#38261a");
      }else{//解决氢原子
        var temp = 109 / 3;
        var cX=sX+154*(array[1]-1)/3;
        if(array[1]==1||array[1]==cNumber){//三个氢
          switch(threeDirec%3){
            case 1:
              context.moveTo(cX, sY)
              context.lineTo(cX, sY-temp);
              context.stroke();
              context.draw(true);
              context.arc(cX, sY-temp, 10, 0, 2 * Math.PI);
              break;
            case 2:
              if(threeDirec>3){
                context.moveTo(cX, sY)
                context.lineTo(cX+temp, sY);
                context.stroke();
                context.draw(true);
                context.arc(cX+temp, sY, 10, 0, 2 * Math.PI);
              }else{
                context.moveTo(cX, sY)
                context.lineTo(cX - temp, sY);
                context.stroke();
                context.draw(true);
                context.arc(cX - temp, sY, 10, 0, 2 * Math.PI);
              }
              break;
            case 0:
              context.moveTo(cX, sY)
              context.lineTo(cX, sY+temp);
              context.stroke();
              context.draw(true);
              context.arc(cX, sY+temp, 10, 0, 2 * Math.PI);
              break;
            default:
              break;
          }
          context.setFillStyle("#7CCD7C");
          threeDirec++;
        }else{//2个氢原子
          switch(twoDirec%2){
            case 1:
              context.moveTo(cX,sY);
              context.lineTo(cX,sY-temp);
              context.stroke();
              context.draw(true);
              context.arc(cX,sY-temp,10,0,2*Math.PI);
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
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})