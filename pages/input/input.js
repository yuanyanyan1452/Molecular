// pages/input/input.js
var hydrocarbonService = require("../../utils/hydrocarbonService.js");
var choService=require("../../utils/CHOService.js")
var cocService=require("../../utils/COCService.js")
var coohService=require("../../utils/COOHService.js")
var cooService=require("../../utils/COOService.js")
var coService=require("../../utils/COService.js")
var ohService=require("../../utils/OHService.js")


var drawBen = require("../../utils/drawBen.js");
var draw=require("../../utils/draw.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cNumber:1,
    hNumber:1,
    oNumber:0,
    noOrganics:""
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
    this.setData({oNumber:n});
  },
  transformService:function(e){
    var cNumber = this.data.cNumber;
    var hNumber=this.data.hNumber;
    var oNumber=this.data.oNumber;
    var bonds=new Array();
    var type="";
    //每次绘画之前清除画布和错误信息
    var context = wx.createCanvasContext('Canvas');
    var context2 = wx.createCanvasContext('Canvas2');
    var context3=wx.createCanvasContext("Canvas3");
    this.setData({ noOrganics: "" });
    if(oNumber==0){
      bonds = hydrocarbonService.transformMoleFormula(cNumber, hNumber);
      if (bonds.length==0) this.setData({ noOrganics: "Ooops!没有对应的烃类有机物" });
      else{
        if ((cNumber * 2 - 6) == hNumber && cNumber >= 6) {//芳香烃
          drawBen.drawBenOrganics(context,bonds,cNumber);
        }else{
          type="烃类有机物";
          draw.drawOrganics(context, bonds, cNumber, hNumber,oNumber,type);
        }
      }
      this.setData({ oNumber: 0 });
    }else if(oNumber==1){
      if(cNumber*2>=hNumber){
        //醛的不饱和度至少要是1
        bonds = choService.transformMoleFormula(cNumber, hNumber, oNumber);
        if(bonds == undefined || bonds.length == 0) this.setData({ noOrganics: "Ooops!没有对应的醛类有机物" });
        else {
          if ((cNumber * 2 - 8) == hNumber && cNumber >= 7) {//芳香醛
            drawBen.drawBenOrganics(context, bonds, cNumber);
          } else {
            type = "脂肪族的醛";
            draw.drawOrganics(context, bonds, cNumber, hNumber,oNumber, type);
          }
        }
        //酮
        bonds=coService.transformMoleFormula(cNumber,hNumber,oNumber);
        if(bonds==undefined||bonds.length==0)this.setData({noOrganics:"Ooops!没有对应的酮类有机物"});
        else{
          type="脂肪族的酮";
          draw.drawOrganics(context3,bonds,cNumber,hNumber,oNumber,type);
        }
      }
      //醚
      bonds=cocService.transformMoleFormula(cNumber,hNumber,oNumber);
      if(bonds == undefined || bonds.length == 0) {
        // this.setData({ noOrganics: "Ooops!没有对应的醚类有机物" });
      }else{
        if ((cNumber * 2 - 6) == hNumber && cNumber >= 7) {//芳香醚
          drawBen.drawBenOrganics(context, bonds, cNumber);
        } else {
          type = "脂肪族的醚";
          draw.drawOrganics(context2, bonds, cNumber, hNumber, oNumber, type);
        }
      }
      //醇
      bonds=ohService.transformMoleFormula(cNumber,hNumber,oNumber);
      if(bonds==undefined||bonds.length==0){
      }else{
        type="脂肪族的醇";
        console.log(bonds);
        draw.drawOrganics(context3,bonds,cNumber,hNumber,oNumber,type);
      }
      this.setData({ oNumber: 1 });
    }else if(oNumber==2){
      bonds=coohService.transformMoleFormula(cNumber,hNumber,oNumber);
      if(bonds==undefined||bonds.length==0)this.setData({noOrganics:"Ooops!没有对应的酸类有机物"});
      else{
        type = "脂肪族的酸";
        draw.drawOrganics(context, bonds, cNumber, hNumber, oNumber, type);
      }
      bonds=cooService.transformMoleFormula(cNumber,hNumber,oNumber);
      if(bonds==undefined||bonds.length==0)this.setData({noOrganics:"Ooops!没有对应的酯类有机物"});
      else{
        type="脂肪族的酯";
        draw.drawOrganics(context2,bonds,cNumber,hNumber,oNumber,type);
      }
      this.setData({oNumber:2});
    }
    
    

    
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