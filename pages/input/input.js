// pages/input/input.js
var hydrocarbonService = require("../../utils/hydrocarbonService.js");
var alkane = require("../../utils/alkane.js");
var olefin = require("../../utils/olefin.js");
var alkyne=require("../../utils/alkyne.js");
var benRing=require("../../utils/benRing.js");
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
    if(n!=0)this.setData({oNumber:n});
  },
  transformService:function(e){
    var cNumber = this.data.cNumber;
    var hNumber=this.data.hNumber;
    var oNumber=this.data.oNumber;
    var bonds=new Array();
    //每次绘画之前清除画布和错误信息
    const context = wx.createCanvasContext('Canvas');
    context.draw();
    this.setData({ noOrganics: "" });
    if(oNumber==0){
      bonds = hydrocarbonService.transformMoleFormula(cNumber, hNumber);
      if (bonds.length==0) this.setData({ noOrganics: "Ooops!no such Organics." });
      else{
        if ((cNumber * 2 + 2) == hNumber) {//烷烃
          alkane.drawAlkane(context, bonds, cNumber);
        } else if (cNumber * 2 == hNumber) {//一烯烃 
          olefin.drawOlefin(context, bonds, cNumber);
        } else if ((cNumber * 2 - 2) == hNumber) {//一炔烃
          alkyne.drawAlkyne(context,bonds,cNumber);
        } else if ((cNumber * 2 - 6) == hNumber && cNumber >= 6) {//coding
          benRing.drawBenRing(context,bonds,cNumber);
        }
        
      }
      
      
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