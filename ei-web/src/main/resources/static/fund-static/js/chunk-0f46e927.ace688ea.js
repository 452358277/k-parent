(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0f46e927"],{"400d":function(a,t,e){"use strict";e.r(t);var i=function(){var a=this,t=a.$createElement,e=a._self._c||t;return e("div",{staticClass:"scan-box"},[a.scanStatus?e("header",{staticClass:"scan-top-box"},[e("div",{staticClass:"scan-title"},[a._v("基金及项目信息扫描中，请稍后...")]),a._v(" "),e("div",{staticClass:"scan-head"},[e("div",{staticClass:"scan-percentage-progress-box"},[e("div",{staticClass:"scan-percentage-progress active"}),a._v(" "),e("div",{staticClass:"percnet-text"},[a._v(a._s(a.allInfo.score))])]),a._v(" "),e("div",{staticClass:"scan-problem"},[a._v("\n        发现\n        "),e("span",[a._v(a._s(a.allInfo.problemNum))]),a._v("问题\n      ")]),a._v(" "),e("div",{staticClass:"scan-btn",on:{click:a.cancelScan}},[a._v("取消")])])]):a._e(),a._v(" "),a.scanStatus?a._e():e("header",{staticClass:"scan-top-box"},[e("div",{staticClass:"scan-title"},[a._v("安全从体检开始")]),a._v(" "),e("div",{staticClass:"scan-head"},[e("div",{staticClass:"start-scan",on:{click:a.startScan}},[a._v("全面体检")]),a._v(" "),e("div",{staticClass:"last-scan"},[a._v("最近一次得分"+a._s(a.allInfo.lastScore)+"分")])])]),a._v(" "),a.processWidth?e("div",{staticClass:"scan-progress-bar"},[e("div",{staticClass:"progress-bar scan-success progress-bar-striped active",style:"width: "+a.processWidth+"%"},[e("div",{staticClass:"progress-value"})])]):a._e(),a._v(" "),a._l(a.allInfo.scanInfo,(function(t){return a.scanStatus?e("div",{staticClass:"scan-result-item"},[e("div",{staticClass:"scan-result-info"},["fund"==t.type?e("div",{staticClass:"scan-result-type",class:t.grade},[e("div",{staticClass:"iconfont iconpanel"}),a._v(" "),e("div",{staticClass:"scan-result-type-name"},[a._v(a._s(t.headInfo))])]):a._e(),a._v(" "),"businessRisk"==t.type?e("div",{staticClass:"scan-result-type",class:t.grade},[e("div",{staticClass:"iconfont icondian"}),a._v(" "),e("div",{staticClass:"scan-result-type-name"},[a._v(a._s(t.headInfo))])]):a._e(),a._v(" "),"executiveTurnover"==t.type?e("div",{staticClass:"scan-result-type",class:t.grade},[e("div",{staticClass:"iconfont iconren-dark"}),a._v(" "),e("div",{staticClass:"scan-result-type-name"},[a._v(a._s(t.headInfo))])]):a._e(),a._v(" "),"dataAbnormity"==t.type?e("div",{staticClass:"scan-result-type",class:t.grade},[e("div",{staticClass:"iconfont icondunpai"}),a._v(" "),e("div",{staticClass:"scan-result-type-name"},[a._v(a._s(t.headInfo))])]):a._e(),a._v(" "),"complianceCheck"==t.type?e("div",{staticClass:"scan-result-type",class:t.grade},[e("div",{staticClass:"iconfont icontianzige"}),a._v(" "),e("div",{staticClass:"scan-result-type-name"},[a._v(a._s(t.headInfo))])]):a._e(),a._v(" "),e("div",{staticClass:"scan-result-content"},[t.scanFinish?e("div",{staticClass:"scan-result-title"},[a._v("\n          检测到\n          "),e("span",[a._v(a._s(t.headInfoNum))]),a._v("\n          "+a._s(t.headInfoText)+"\n        ")]):a._e(),a._v(" "),t.scanFinish?a._e():e("div",{staticClass:"scan-result-title"},[a._v(a._s(t.headInfoText))]),a._v(" "),t.headInfoShow?e("div",{staticClass:"scan-result-detail"},a._l(t.headInfoDetail,(function(i){return e("div",{staticClass:"scan-detail-info"},[!i.detailShow&&i.detailNum>0?e("div",{staticClass:"iconfont iconhao",on:{click:function(e){return a.showDetail(i.id,t.type)}}}):a._e(),a._v(" "),i.detailShow&&i.detailNum>0?e("div",{staticClass:"iconfont icon-hao",on:{click:function(e){return a.showDetail(i.id,t.type)}}}):a._e(),a._v(" "),e("div",{staticClass:"scan-content-text"},[a._v("\n              发现\n              "),0==!i.detailNum?e("span",{staticStyle:{color:"#f00",margin:"0px 2px"}},[a._v(a._s(i.detailNum))]):a._e(),a._v(" "),0==i.detailNum?e("span",[a._v(a._s(i.detailNum))]):a._e(),a._v("\n              项"+a._s(i.detail)+"\n              "),i.detailShow?e("div",{staticClass:"scan-content-text-list"},a._l(i.detailList,(function(t){return e("div",{staticClass:"scan-content-text-list-info"},[a._v(a._s(t))])})),0):a._e()])])})),0):a._e()]),a._v(" "),t.headInfoShow&&a.scanFinish?e("div",{staticClass:"scan-openorclose cursorPointer",on:{click:function(e){return a.showHeadInfo(t.type)}}},[e("div",{staticClass:"iconfont iconshouqi"})]):a._e(),a._v(" "),!t.headInfoShow&&a.scanFinish?e("div",{staticClass:"scan-openorclose cursorPointer",on:{click:function(e){return a.showHeadInfo(t.type)}}},[e("div",{staticClass:"iconfont icondownarrow-blue"})]):a._e()])]):a._e()}))],2)},s=[],n={name:"Scan",data:function(){return{scanStatus:!1,allInfo:{lastScore:100,score:100,problemNum:0,scanInfo:[{type:"fund",grade:"security",headInfo:"资产资金",headInfoNum:2,headInfoShow:!1,headInfoText:"资产资金问题",headCheckFinish:!0,headInfoDetail:[{detail:"项目的银行实际出资金额不等于拟出资金额",detailNum:1,detailShow:!1,id:"1",detailList:["项目的银行实际出资金额不等于拟出资金额"]},{detail:"笔基金大额资金支付",detailNum:1,detailShow:!1,id:"2",detailList:["出资金额不等于拟出资金额笔项目的银行实际出资金额不等于拟出资金额"]}]},{type:"businessRisk",headInfo:"经营风险",grade:"security",headInfoNum:0,headInfoShow:!1,headCheckFinish:!0,headInfoText:"经营风险数据",headInfoDetail:[{detail:"基金发生工商经营风险",detailNum:0,detailShow:!1,id:"3",detailList:[]},{detail:"基金发生法律诉讼风险",detailNum:0,detailShow:!1,id:"4",detailList:[]},{detail:"基金实际控制人被处罚",detailNum:0,detailShow:!1,id:"5",detailList:[]},{detail:"基金合伙人股份被冻结",detailNum:0,detailShow:!1,id:"6",detailList:[]}]},{type:"executiveTurnover",headInfo:"高管变化",grade:"security",headInfoNum:1,headInfoShow:!1,headCheckFinish:!0,headInfoText:"高管变化",headInfoDetail:[{detail:"基金关键人事",detailNum:1,detailShow:!1,id:"7",detailList:["金融事业部人员变动"]},{detail:"基金合伙人或者股东",detailNum:0,detailShow:!1,id:"8",detailList:[]},{detail:"基金成为其他公司股东",detailNum:0,detailShow:!1,id:"9",detailList:[]}]},{type:"dataAbnormity",headInfo:"数据异常",grade:"security",headInfoNum:0,headInfoShow:!1,headCheckFinish:!0,headInfoText:"项目及基金异常数据",headInfoDetail:[{detail:"基金IRR与计划数据有重大偏差",detailNum:0,detailShow:!1,id:"10",detailList:[]},{detail:"基金发生重要事项且未备案",detailNum:0,detailShow:!1,id:"11",detailList:[]},{detail:"基金的估值发生重大变化",detailNum:0,detailShow:!1,id:"12",detailList:[]}]},{type:"complianceCheck",headInfo:"合规检查",grade:"security",headInfoNum:0,headInfoShow:!1,headCheckFinish:!0,headInfoText:"合规异常项目",headInfoDetail:[{detail:"基金投资外省比例超过40%",detailNum:0,detailShow:!1,id:"10",detailList:[]},{detail:"基金投资负面清单项目",detailNum:0,detailShow:!1,id:"11",detailList:[]}]}]},apiInfo:{lastScore:"98",score:"86",problemNum:"0",scanInfo:[{type:"fund",grade:"security",headInfo:"资产资金",headInfoNum:"2",headInfoShow:!1,headInfoText:"资产资金问题",headCheckFinish:!0,headInfoDetail:[{detail:"项目的银行实际出资金额不等于拟出资金额",detailNum:"1",detailShow:!1,id:"1",detailList:["项目的银行实际出资金额不等于拟出资金额"]},{detail:"笔基金大额资金支付",detailNum:"1",detailShow:!1,id:"2",detailList:["出资金额不等于拟出资金额笔项目的银行实际出资金额不等于拟出资金额"]}]},{type:"businessRisk",headInfo:"经营风险",grade:"security",headInfoNum:"0",headInfoShow:!1,headCheckFinish:!0,headInfoText:"经营风险数据",headInfoDetail:[{detail:"基金发生工商经营风险",detailNum:"0",detailShow:!1,id:"3",detailList:[]},{detail:"基金发生法律诉讼风险",detailNum:"0",detailShow:!1,id:"4",detailList:[]},{detail:"基金实际控制人被处罚",detailNum:"0",detailShow:!1,id:"5",detailList:[]},{detail:"基金合伙人股份被冻结",detailNum:"0",detailShow:!1,id:"6",detailList:[]}]},{type:"executiveTurnover",headInfo:"高管变化",grade:"security",headInfoNum:"1",headInfoShow:!1,headCheckFinish:!0,headInfoText:"高管变化",headInfoDetail:[{detail:"基金关键人事",detailNum:"1",detailShow:!1,id:"7",detailList:["金融事业部人员变动"]},{detail:"基金合伙人或者股东",detailNum:"0",detailShow:!1,id:"8",detailList:[]},{detail:"基金成为其他公司股东",detailNum:"0",detailShow:!1,id:"9",detailList:[]}]},{type:"dataAbnormity",headInfo:"数据异常",grade:"security",headInfoNum:"0",headInfoShow:!1,headCheckFinish:!0,headInfoText:"项目及基金异常数据",headInfoDetail:[{detail:"基金IRR与计划数据有重大偏差",detailNum:"0",detailShow:!1,id:"10",detailList:[]},{detail:"基金发生重要事项且未备案",detailNum:"0",detailShow:!1,id:"11",detailList:[]},{detail:"基金的估值发生重大变化",detailNum:"0",detailShow:!1,id:"12",detailList:[]}]},{type:"complianceCheck",headInfo:"合规检查",grade:"security",headInfoNum:"0",headInfoShow:!1,headCheckFinish:!0,headInfoText:"合规异常项目",headInfoDetail:[{detail:"基金投资外省比例超过40%",detailNum:"0",detailShow:!1,id:"10",detailList:[]},{detail:"基金投资负面清单项目",detailNum:"0",detailShow:!1,id:"11",detailList:[]}]}]},processWidth:0,scanFinish:!0}},created:function(){},methods:{checkScan:function(){for(var a=0;a<this.allInfo.scanInfo.length;a++){var t=this.allInfo.scanInfo[a].headInfoNum;this.allInfo.scanInfo[a].grade=0==t?"security":1==t?"normal":"danger"}},showHeadInfo:function(a){for(var t=0;t<this.allInfo.scanInfo.length;t++)a==this.allInfo.scanInfo[t].type&&(this.allInfo.scanInfo[t].headInfoShow?this.allInfo.scanInfo[t].headInfoShow=!1:this.allInfo.scanInfo[t].headInfoShow=!0)},showDetail:function(a,t){for(var e=a,i=t,s=0;s<this.allInfo.scanInfo.length;s++)if(i==this.allInfo.scanInfo[s].type)for(var n=0;n<this.allInfo.scanInfo[s].headInfoDetail.length;n++)e==this.allInfo.scanInfo[s].headInfoDetail[n].id&&(this.allInfo.scanInfo[s].headInfoDetail[n].detailShow?this.allInfo.scanInfo[s].headInfoDetail[n].detailShow=!1:this.allInfo.scanInfo[s].headInfoDetail[n].detailShow=!0)},startScan:function(){for(var a=0;a<this.allInfo.scanInfo.length;a++)this.allInfo.scanInfo[a].headInfoText="正在进行安全扫描，请等待...";this.scanStatus=!0,this.scanFinish=!1,this.scanProcess()},cancelScan:function(){this.scanStatus=!1,this.scanFinish=!0},scanProcess:function(){var a=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5e3;this.processWidth=!0,this.allInfo.score=100,this.allInfo.problemNum=0;for(var e=this.apiInfo.scanInfo.length,i=t/e,s=function(t){setTimeout((function(){a.processWidth=100/e*(t+1),a.allInfo.scanInfo[t].headInfoText=a.apiInfo.scanInfo[t].headInfoText,a.allInfo.scanInfo[t].scanFinish=!a.apiInfo.scanInfo[t].scanFinish,a.allInfo.scanInfo[t].headInfoShow=!0;var i=a.allInfo.scanInfo[t].headInfoNum;a.allInfo.scanInfo[t].grade=0==i?"security":1==i?"normal":"danger",a.allInfo.problemNum+=parseInt(a.allInfo.scanInfo[t].headInfoNum),a.allInfo.score-=5*parseInt(a.allInfo.scanInfo[t].headInfoNum),a.allInfo.lastScore=a.allInfo.score}),i*t)},n=0;n<e;n++)s(n);setTimeout((function(){a.scanFinish=!0,a.processWidth=0}),t)}}},o=n,d=(e("f138"),e("4ac2")),l=Object(d["a"])(o,i,s,!1,null,"16996897",null);t["default"]=l.exports},8028:function(a,t,e){},f138:function(a,t,e){"use strict";e("8028")}}]);