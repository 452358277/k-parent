(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2761ae42"],{"309ad":function(t,e,s){"use strict";s("ed80")},3270:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("stopProj",{attrs:{isStopLawWorks:t.isStopLawWorks,isStopGreat:t.isStopGreat,isEdit:t.isEdit},model:{value:t.dialogInfo,callback:function(e){t.dialogInfo=e},expression:"dialogInfo"}}),t._v(" "),s("div",{attrs:{id:"myDiv"}},[s("div",{staticClass:"processTitle"},[t._v("审批信息")]),t._v(" "),s("iframe",{attrs:{src:t.src,width:"100%",id:"contentFrame_sp",frameborder:"0",scrolling:"no"}})]),t._v(" "),s("process",{attrs:{"content-path":t.url.urlApi},on:{action:t.saveInfo}})],1)},l=[],o=s("7c4d"),n=s("e19c"),i=s("7562"),r=s("0817"),c={name:"DueDiligenceReportProcess",components:{stopProj:o["default"],process:r["a"]},data:function(){return{src:"",queryInfo:{},url:n["a"],dialogInfo:{},isEdit:!1,isStopLawWorks:!1,isStopGreat:!1}},mounted:function(){this.queryInfo=this.$route.query,this.src="".concat(n["a"].processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.getPageDetail(),"false"===this.queryInfo.isEdit?this.isEdit=!1:this.isEdit=!0,"UserTask_3"===this.queryInfo._node?this.isStopLawWorks=!0:this.isStopGreat=!1,"UserTask_4"===this.queryInfo._node?this.isStopGreat=!0:this.isStopLawWorks=!1},methods:{getPageDetail:function(){var t=this;i["a"].projectStopDetail({stopId:this.$route.query.bizKey}).then((function(e){t.dialogInfo=e.data.data}))},saveInfo:function(){i["a"].updateProjStopProject(this.dialogInfo).then((function(t){}))}}},p=c,u=(s("7525"),s("4ac2")),v=Object(u["a"])(p,a,l,!1,null,null,null);e["default"]=v.exports},"58be":function(t,e,s){},7525:function(t,e,s){"use strict";s("58be")},"7c4d":function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("el-row",{staticClass:"personHeadInfo",staticStyle:{"margin-top":"20px"},attrs:{align:"center"}},[s("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n      基金名称\n    ")]),t._v(" "),s("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[s("div",[t._v("\n        "+t._s(t.value.fundName)+"\n      ")])]),t._v(" "),s("el-col",{staticClass:"personHeadTitle",attrs:{span:3}}),t._v(" "),s("el-col",{staticClass:"personHeadItem",attrs:{span:9}})],1),t._v(" "),s("el-row",{staticClass:"personHeadInfo",staticStyle:{"margin-top":"20px"},attrs:{align:"center"}},[s("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n      项目名称\n    ")]),t._v(" "),s("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[s("div",[t._v("\n        "+t._s(t.value.projName)+"\n      ")])]),t._v(" "),s("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n      出资主体\n    ")]),t._v(" "),s("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[s("div",[t._v("\n        "+t._s(t.value.inveName)+"\n      ")])])],1),t._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      "+t._s("1"===t.value.stopType?"注销日期":"终止日期")+"\n    ")]),t._v(" "),t.isEdit?s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.value.stopDate,callback:function(e){t.$set(t.value,"stopDate",e)},expression:"value.stopDate"}})],1):t._e(),t._v(" "),t.isEdit?t._e():s("el-col",{staticClass:"col-content",attrs:{span:18}},[t._v("\n      "+t._s(t.value.stopDate)+"\n    ")])],1),t._v(" "),t.isStopLawWorks?s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      是否需要法务审核\n    ")]),t._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("el-radio",{attrs:{label:1},model:{value:t.value.stopLawWorks,callback:function(e){t.$set(t.value,"stopLawWorks",e)},expression:"value.stopLawWorks"}},[t._v("是")]),t._v(" "),s("el-radio",{attrs:{label:0},model:{value:t.value.stopLawWorks,callback:function(e){t.$set(t.value,"stopLawWorks",e)},expression:"value.stopLawWorks"}},[t._v("否")])],1)],1):t._e(),t._v(" "),t.isStopGreat?s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      是否重大事项\n    ")]),t._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("el-radio",{attrs:{label:1},model:{value:t.value.stopGreat,callback:function(e){t.$set(t.value,"stopGreat",e)},expression:"value.stopGreat"}},[t._v("重大事项")]),t._v(" "),s("el-radio",{attrs:{label:0},model:{value:t.value.stopGreat,callback:function(e){t.$set(t.value,"stopGreat",e)},expression:"value.stopGreat"}},[t._v("一般事项")])],1)],1):t._e(),t._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      事项分类\n      "),s("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("el-radio",{attrs:{label:0},model:{value:t.value.eventType,callback:function(e){t.$set(t.value,"eventType",e)},expression:"value.eventType"}},[t._v("一般事项")]),t._v(" "),s("el-radio",{attrs:{label:1},model:{value:t.value.eventType,callback:function(e){t.$set(t.value,"eventType",e)},expression:"value.eventType"}},[t._v("重大事项")])],1)],1),t._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      "+t._s("1"===t.value.stopType?"注销原因":"终止原因")+"\n      "),s("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"500"},model:{value:t.value.stopCause,callback:function(e){t.$set(t.value,"stopCause",e)},expression:"value.stopCause"}})],1):t._e(),t._v(" "),t.isEdit?t._e():s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("pre",[t._v(t._s(t.value.stopCause))])])],1),t._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:6}},[t._v("\n      附件\n    ")]),t._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:18}},[s("upLoad",{attrs:{"hidden-upload":!t.isEdit,"context-path":t.url.upApi},model:{value:t.value.stopFile,callback:function(e){t.$set(t.value,"stopFile",e)},expression:"value.stopFile"}})],1)],1)],1)},l=[],o=s("e19c"),n=s("2c2d"),i={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0},isStopLawWorks:{type:Boolean,default:!1},isStopGreat:{type:Boolean,default:!1}},components:{upLoad:n["a"]},data:function(){return{exitTypeList:[],url:o["a"]}},mounted:function(){},methods:{}},r=i,c=(s("309ad"),s("4ac2")),p=Object(c["a"])(r,a,l,!1,null,"20f8ae66",null);e["default"]=p.exports},ed80:function(t,e,s){}}]);