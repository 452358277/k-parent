(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4bcee8fe"],{"17a8":function(t,e,a){"use strict";a("3870")},3870:function(t,e,a){},"7dea":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",{staticClass:"personHeadInfo",staticStyle:{"margin-top":"20px"},attrs:{align:"center"}},[a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n        项目名称\n      ")]),t._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[t._v("\n          "+t._s(t.value.projName)+"\n        ")])]),t._v(" "),a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n        出资主体\n      ")]),t._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[t._v("\n          "+t._s(t.value.inveName)+"\n        ")])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        投资金额(万元)\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.intendedAmount)+"\n      ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        累计付款金额(人民币)(万元)\n        "),a("br"),t._v(" "),a("span",{staticClass:"mustIn",staticStyle:{"font-size":"11px"}},[t._v("(不含本期)")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",[t._v(t._s(t.value.sumInveAmount))])]):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",[t._v(t._s(t.value.sumInveAmount))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        本期付款金额(万元)\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择",size:"small"},model:{value:t.value.payCurrency,callback:function(e){t.$set(t.value,"payCurrency",e)},expression:"value.payCurrency"}},t._l(t.currencyList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1),t._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:t.value.actualPayAmount,callback:function(e){t.$set(t.value,"actualPayAmount",e)},expression:"value.actualPayAmount"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.actualPayAmount)+t._s(t.value.payCurrencyName)+"\n      ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        本期付款金额折算人民币(万元)\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:t.value.rmbActualPayAmount,callback:function(e){t.$set(t.value,"rmbActualPayAmount",e)},expression:"value.rmbActualPayAmount"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.rmbActualPayAmount)+"\n      ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        未付款金额(人民币)(万元)\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(Number(t.value.intendedAmount-t.value.sumInveAmount-(t.value.rmbActualPayAmount?t.value.rmbActualPayAmount:"0")).toFixed(4))+"\n      ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        付款期数\n      ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",{staticStyle:{display:"flex","align-items":"center"}},[a("span",[t._v("共")]),t._v(" "),a("el-input",{staticStyle:{width:"80px",margin:"0px 4px"},attrs:{size:"small",maxlength:"10"},model:{value:t.value.totalPeriod,callback:function(e){t.$set(t.value,"totalPeriod",e)},expression:"value.totalPeriod"}}),t._v(" "),a("span",[t._v("期，")]),t._v(" "),a("span",[t._v("第")]),t._v(" "),a("el-input",{staticStyle:{width:"80px",margin:"0px 4px"},attrs:{size:"small",maxlength:"10"},model:{value:t.value.currentPeriod,callback:function(e){t.$set(t.value,"currentPeriod",e)},expression:"value.currentPeriod"}}),t._v(" "),a("span",[t._v("期。")])],1)]):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",{staticStyle:{display:"flex","align-items":"center"}},[a("span",[t._v("共")]),t._v("\n          "+t._s(t.value.totalPeriod)+"\n          "),a("span",[t._v("期，")]),t._v(" "),a("span",[t._v("第")]),t._v("\n          "+t._s(t.value.currentPeriod)+"\n          "),a("span",[t._v("期。")])])]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        截止缴款日期\n      ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.value.endTime,callback:function(e){t.$set(t.value,"endTime",e)},expression:"value.endTime"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.endTime)+"\n      ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        收款人名称\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small"},model:{value:t.value.payeeName,callback:function(e){t.$set(t.value,"payeeName",e)},expression:"value.payeeName"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[t._v("\n        "+t._s(t.value.payeeName)+"\n      ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        收款账号\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"30"},model:{value:t.value.receivableAccount,callback:function(e){t.$set(t.value,"receivableAccount",e)},expression:"value.receivableAccount"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.receivableAccount)+"\n      ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        开户行\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"30"},model:{value:t.value.openBank,callback:function(e){t.$set(t.value,"openBank",e)},expression:"value.openBank"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n        "+t._s(t.value.openBank)+"\n      ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        备注\n      ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"500"},model:{value:t.value.remark,callback:function(e){t.$set(t.value,"remark",e)},expression:"value.remark"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[t._v("\n        "+t._s(t.value.remark)+"\n      ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[t.dialogVisible?a("upLoad",{attrs:{"context-path":t.url.upApi,"hidden-upload":!t.isEdit},model:{value:t.value.descFile,callback:function(e){t.$set(t.value,"descFile",e)},expression:"value.descFile"}}):t._e()],1)],1)],1)},n=[],l=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),i=a("e19c"),o=a("2c2d"),c={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0},dialogVisible:{type:Boolean,default:!0},status:{type:String,default:"edit"}},data:function(){return{currencyList:[],url:i["a"]}},components:{upLoad:o["a"]},mounted:function(){this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}]},methods:{getParentId:function(t,e){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var n=[];if(s){var i=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){i.includes(t.value)&&n.push(t)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=n}else l["a"].getCodeByParentId({parentId:t}).then((function(n){var l=[];if(s){var i=s.split(",");n.data.data.options.forEach((function(t){i.includes(t.value)&&l.push(t)}))}else l=n.data.data.options;a[e]=l,sessionStorage.setItem("code".concat(t),JSON.stringify(n.data.data.options))}))}}},r=c,u=(a("17a8"),a("4ac2")),d=Object(u["a"])(r,s,n,!1,null,"15a136d3",null);e["default"]=d.exports},9300:function(t,e,a){},"9e4e":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("formPage",{attrs:{"is-edit":!1},model:{value:t.dialogInfo,callback:function(e){t.dialogInfo=e},expression:"dialogInfo"}}),t._v(" "),a("div",{attrs:{id:"myDiv"}},[a("div",{staticClass:"processTitle"},[t._v("审批信息")]),t._v(" "),t.iframeShows?a("iframe",{attrs:{src:t.src,width:"100%",id:"contentFrame_sp",frameborder:"0",scrolling:"no"}}):t._e()]),t._v(" "),a("process",{attrs:{"content-path":t.url.urlApi},on:{action:t.action}})],1)},n=[],l=(a("e680"),a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("0817")),i=a("2c2d"),o=a("e19c"),c=a("7562"),r=a("7dea"),u={name:"Index",data:function(){return{url:o["a"],iframeShows:!1,queryInfo:{},dialogInfo:{},contractList:[],currencyList:[]}},mounted:function(){this.queryInfo=this.$route.query,this.src="".concat(o["a"].processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.iframeShows=!0,this.getParentId(103,"currencyList"),this.getInfoDetail()},components:{process:l["a"],upLoad:i["a"],formPage:r["default"]},methods:{getParentId:function(t,e){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var n=[];if(s){var l=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){l.includes(t.value)&&n.push(t)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=n}else c["a"].getCodeByParentId({parentId:t}).then((function(n){var l=[];if(s){var i=s.split(",");n.data.data.options.forEach((function(t){i.includes(t.value)&&l.push(t)}))}else l=n.data.data.options;a[e]=l,sessionStorage.setItem("code".concat(t),JSON.stringify(n.data.data.options))}))},getInfoDetail:function(){var t=this;c["a"].fundShareDetail({pqId:this.queryInfo.pqId,fundId:this.queryInfo.fundId,projId:this.queryInfo.projId}).then((function(e){t.dialogInfo={inveCurrentAmount:e.data.data.inveCurrentAmount,sumInveAmount:e.data.data.sumRmbActualPayAmount,actualPayAmount:e.data.data.actualPayAmount,payCurrency:e.data.data.payCurrency,payCurrencyName:e.data.data.payCurrencyName,appDt:e.data.data.appDt.slice(0,10),totalPeriod:e.data.data.totalPeriod,currentPeriod:e.data.data.currentPeriod,remark:e.data.data.remark,pqId:e.data.data.pqId,descFile:e.data.data.descFile,investorId:e.data.data.investorId}}))},selectContract:function(){},action:function(){var t={};if(t.fundId=this.queryInfo.fundId,t.projId=this.queryInfo.projId,t.pqId=this.queryInfo.pqId,t.currId=this.queryInfo.finance,t.investorId=this.dialogInfo.investorId,t.isProj="2","edit"===this.queryInfo.steps){if(""===this.dialogInfo.sumInveAmount||null===this.dialogInfo.sumInveAmount||void 0===this.dialogInfo.sumInveAmount)return void this.$message({offset:150,type:"warning",message:"请填写累计付款金额"});if(isNaN(Number(this.dialogInfo.sumInveAmount)))return void this.$message({offset:150,type:"warning",message:"请正确填写累计付款金额,格式为数字"});if(""===t.actualPayAmount||void 0===t.actualPayAmount||null===t.actualPayAmount)return void this.$message({offset:150,type:"warning",message:"请填写本期付款金额"});if(isNaN(Number(t.actualPayAmount)))return void this.$message({offset:150,type:"warning",message:"请正确填写本期付款金额,格式为数字"});if(""===t.rmbActualPayAmount||void 0===t.rmbActualPayAmount||null===t.rmbActualPayAmount)return void this.$message({offset:150,type:"warning",message:"请填写本期付款金额折算人民币"});if(isNaN(Number(t.rmbActualPayAmount)))return void this.$message({offset:150,type:"warning",message:"请正确填写本期付款金额折算为人民币,格式为数字"});if(""===t.payeeName||void 0===t.payeeName||null===t.payeeName)return void this.$message({offset:150,type:"warning",message:"请填写收款人名称"});if(""===t.receivableAccount||void 0===t.receivableAccount||null===t.receivableAccount)return void this.$message({offset:150,type:"warning",message:"请填写收款账号"});if(""===t.openBank||void 0===t.openBank||null===t.openBank)return void this.$message({offset:150,type:"warning",message:"请填写开户行"})}c["a"].fundShareInfoStartWork(t).then((function(t){}))}}},d=u,v=(a("d3b5"),a("4ac2")),p=Object(v["a"])(d,s,n,!1,null,"1be61550",null);e["default"]=p.exports},d3b5:function(t,e,a){"use strict";a("9300")}}]);