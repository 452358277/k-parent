(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ae9f2a06","chunk-ad1dce30","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"17a8":function(e,t,a){"use strict";a("3870")},"2c2d":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"upload-box"},[e.hiddenUpload?a("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return a("h3",{key:t.uid},[a("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[a("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},n=[],i=(a("fc02"),a("a450"),a("e680"),a("7f43")),o=a.n(i),l={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,a=e||this.value;""!=a&&null!=a||(a="-1");var s=this.contextPath+"attachController/getFileList?fieldToken="+a;o()({url:s,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var a=e.name.substring(e.name.lastIndexOf(".")+1),s="rp"===a,n="vsd"===a;(s||n)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var a=this;o()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=t)}))},handleError:function(e,t,a){},handleSuccess:function(e,t,a){var s=e.data.fieldToken;void 0==s&&(s=this.value);var n=this.updatePath.split("=");""!=n[1]&&""==s&&(s=n[1]),this.$emit("update-value",s),this.fileLists=a,this.getFileData(s)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},r=l,c=(a("e116"),a("4ac2")),u=Object(c["a"])(r,s,n,!1,null,"e22390a6",null);t["a"]=u.exports},3870:function(e,t,a){},"54c2":function(e,t,a){},7749:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("formPage",{attrs:{"is-edit":"edit"===e.steps,status:"edit"},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}}),e._v(" "),"view"!==e.steps?a("div",{staticClass:"processTitle",attrs:{id:"myDiv"}},[a("div",{staticClass:"bg-purple-quote"},[e._v("审批信息")]),e._v(" "),e.iframeShows?a("iframe",{attrs:{src:e.src,width:"100%",id:"contentFrame_sp",frameborder:"0",scrolling:"no"}}):e._e()]):e._e(),e._v(" "),"view"!==e.steps?a("process",{ref:"process",attrs:{"content-path":e.url.urlApi},on:{action:e.action}}):e._e()],1)},n=[],i=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("91e8")),o=a("2c2d"),l=a("e19c"),r=a("7562"),c=a("7dea"),u={name:"Index",data:function(){return{url:l["a"],iframeShows:!1,queryInfo:{},dialogInfo:{},contractList:[],currencyList:[],steps:""}},mounted:function(){this.queryInfo=this.$route.query,this.src="".concat(l["a"].processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.iframeShows=!0,this.getParentId(103,"currencyList"),this.steps=this.$route.query.steps,this.pqId=this.queryInfo.bizKey?this.queryInfo.bizKey:this.queryInfo.pqId,this.getInfoDetail()},components:{process:i["a"],upLoad:o["a"],formPage:c["default"]},methods:{getParentId:function(e,t){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var n=[];if(s){var i=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&n.push(e)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=n}else r["a"].getCodeByParentId({parentId:e}).then((function(n){var i=[];if(s){var o=s.split(",");n.data.data.options.forEach((function(e){o.includes(e.value)&&i.push(e)}))}else i=n.data.data.options;a[t]=i,sessionStorage.setItem("code".concat(e),JSON.stringify(n.data.data.options))}))},getInfoDetail:function(){var e=this;r["a"].paymentRequestDetaiInfo({pqId:this.pqId}).then((function(t){e.dialogInfo=t.data.data}))},selectContract:function(){},action:function(){var e=this,t={};"edit"===this.steps?(t=this.dialogInfo,t.isStartFlow=!0,t.financeType="1",r["a"].paymentRequestSaveInveMoney(t).then((function(t){"0"===t.data.status&&e.$refs.process.nextTask()}))):(t.projId=this.dialogInfo.projectOrFundId,t.pqId=this.queryInfo.pqId,t.currId=this.queryInfo.finance,t.investorId=this.dialogInfo.investorId,t.fundId=this.dialogInfo.investorId,t.isProj="1",r["a"].fundShareInfoStartWork(t).then((function(t){e.$refs.process.nextTask()})))}}},d=u,p=(a("d106"),a("4ac2")),f=Object(p["a"])(d,s,n,!1,null,"b51e0ca4",null);t["default"]=f.exports},"7dea":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{staticClass:"personHeadInfo",staticStyle:{"margin-top":"20px"},attrs:{align:"center"}},[a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[e._v("\n        项目名称\n      ")]),e._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[e._v("\n          "+e._s(e.value.projName)+"\n        ")])]),e._v(" "),a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[e._v("\n        出资主体\n      ")]),e._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[e._v("\n          "+e._s(e.value.inveName)+"\n        ")])])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        投资金额(万元)\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.intendedAmount)+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        累计付款金额(人民币)(万元)\n        "),a("br"),e._v(" "),a("span",{staticClass:"mustIn",staticStyle:{"font-size":"11px"}},[e._v("(不含本期)")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",[e._v(e._s(e.value.sumInveAmount))])]):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",[e._v(e._s(e.value.sumInveAmount))])])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        本期付款金额(万元)\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择",size:"small"},model:{value:e.value.payCurrency,callback:function(t){e.$set(e.value,"payCurrency",t)},expression:"value.payCurrency"}},e._l(e.currencyList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1),e._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:e.value.actualPayAmount,callback:function(t){e.$set(e.value,"actualPayAmount",t)},expression:"value.actualPayAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.actualPayAmount)+e._s(e.value.payCurrencyName)+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        本期付款金额折算人民币(万元)\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.value.rmbActualPayAmount,callback:function(t){e.$set(e.value,"rmbActualPayAmount",t)},expression:"value.rmbActualPayAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.rmbActualPayAmount)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        未付款金额(人民币)(万元)\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(Number(e.value.intendedAmount-e.value.sumInveAmount-(e.value.rmbActualPayAmount?e.value.rmbActualPayAmount:"0")).toFixed(4))+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        付款期数\n      ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",{staticStyle:{display:"flex","align-items":"center"}},[a("span",[e._v("共")]),e._v(" "),a("el-input",{staticStyle:{width:"80px",margin:"0px 4px"},attrs:{size:"small",maxlength:"10"},model:{value:e.value.totalPeriod,callback:function(t){e.$set(e.value,"totalPeriod",t)},expression:"value.totalPeriod"}}),e._v(" "),a("span",[e._v("期，")]),e._v(" "),a("span",[e._v("第")]),e._v(" "),a("el-input",{staticStyle:{width:"80px",margin:"0px 4px"},attrs:{size:"small",maxlength:"10"},model:{value:e.value.currentPeriod,callback:function(t){e.$set(e.value,"currentPeriod",t)},expression:"value.currentPeriod"}}),e._v(" "),a("span",[e._v("期。")])],1)]):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("div",{staticStyle:{display:"flex","align-items":"center"}},[a("span",[e._v("共")]),e._v("\n          "+e._s(e.value.totalPeriod)+"\n          "),a("span",[e._v("期，")]),e._v(" "),a("span",[e._v("第")]),e._v("\n          "+e._s(e.value.currentPeriod)+"\n          "),a("span",[e._v("期。")])])]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        截止缴款日期\n      ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.value.endTime,callback:function(t){e.$set(e.value,"endTime",t)},expression:"value.endTime"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.endTime)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        收款人名称\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small"},model:{value:e.value.payeeName,callback:function(t){e.$set(e.value,"payeeName",t)},expression:"value.payeeName"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n        "+e._s(e.value.payeeName)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        收款账号\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"30"},model:{value:e.value.receivableAccount,callback:function(t){e.$set(e.value,"receivableAccount",t)},expression:"value.receivableAccount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.receivableAccount)+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        开户行\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"30"},model:{value:e.value.openBank,callback:function(t){e.$set(e.value,"openBank",t)},expression:"value.openBank"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.value.openBank)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        备注\n      ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"500"},model:{value:e.value.remark,callback:function(t){e.$set(e.value,"remark",t)},expression:"value.remark"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n        "+e._s(e.value.remark)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        附件\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[e.dialogVisible?a("upLoad",{attrs:{"context-path":e.url.upApi,"hidden-upload":!e.isEdit},model:{value:e.value.descFile,callback:function(t){e.$set(e.value,"descFile",t)},expression:"value.descFile"}}):e._e()],1)],1)],1)},n=[],i=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),o=a("e19c"),l=a("2c2d"),r={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0},dialogVisible:{type:Boolean,default:!0},status:{type:String,default:"edit"}},data:function(){return{currencyList:[],url:o["a"]}},components:{upLoad:l["a"]},mounted:function(){this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}]},methods:{getParentId:function(e,t){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var n=[];if(s){var o=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){o.includes(e.value)&&n.push(e)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=n}else i["a"].getCodeByParentId({parentId:e}).then((function(n){var i=[];if(s){var o=s.split(",");n.data.data.options.forEach((function(e){o.includes(e.value)&&i.push(e)}))}else i=n.data.data.options;a[t]=i,sessionStorage.setItem("code".concat(e),JSON.stringify(n.data.data.options))}))}}},c=r,u=(a("17a8"),a("4ac2")),d=Object(u["a"])(c,s,n,!1,null,"15a136d3",null);t["default"]=d.exports},"91e8":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.instId,expression:"instId"}],attrs:{id:"instId",type:"hidden"},domProps:{value:e.instId},on:{input:function(t){t.target.composing||(e.instId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.taskId,expression:"taskId"}],attrs:{id:"taskId",type:"hidden"},domProps:{value:e.taskId},on:{input:function(t){t.target.composing||(e.taskId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUri,expression:"formUri"}],attrs:{id:"formUri",type:"hidden"},domProps:{value:e.formUri},on:{input:function(t){t.target.composing||(e.formUri=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUriView,expression:"formUriView"}],attrs:{id:"formUriView",type:"hidden"},domProps:{value:e.formUriView},on:{input:function(t){t.target.composing||(e.formUriView=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.pshow,expression:"pshow"}],attrs:{id:"pshow",type:"hidden"},domProps:{value:e.pshow},on:{input:function(t){t.target.composing||(e.pshow=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.phid,expression:"phid"}],attrs:{id:"phid",type:"hidden"},domProps:{value:e.phid},on:{input:function(t){t.target.composing||(e.phid=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.contentPath,expression:"contentPath"}],attrs:{id:"contentPath",type:"hidden"},domProps:{value:e.contentPath},on:{input:function(t){t.target.composing||(e.contentPath=t.target.value)}}}),e._v(" "),a("table",{staticClass:"addtable",staticStyle:{"margin-top":"0px","margin-bottom":"10px"},attrs:{id:"formToolBarTable",width:"100%",border:"0",cellspacing:"0",cellpadding:"0"}},[e._m(0),e._v(" "),a("tr",{staticStyle:{border:"1px solid #D9D9D9"}},[a("td",{staticClass:"td_name",staticStyle:{width:"20%",padding:"0px","font-size":"13px","text-align":"center","vertical-align":"middle",color:"#997528",background:"#eae3d8"}},[e._v("处理意见")]),e._v(" "),a("td",{staticClass:"td_value",staticStyle:{width:"82%",padding:"6px"},attrs:{colspan:"3"}},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:e.taskProcessDescValue,expression:"taskProcessDescValue"}],staticStyle:{width:"100%",height:"70px","border-radius":"4px"},attrs:{name:"taskProcessDesc",id:"taskProcessDesc"},domProps:{value:e.taskProcessDescValue},on:{input:function(t){t.target.composing||(e.taskProcessDescValue=t.target.value)}}})])]),e._v(" "),a("tr",[a("td",{staticClass:"td_name",staticStyle:{width:"18%"}}),e._v(" "),a("td",{staticClass:"td_value drawablel",staticStyle:{width:"82%"},attrs:{colspan:"3",id:"processToolBar"}},[1===e.allcommshow?e._l(e.commandes,(function(t){return a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name))])})):e._e(),e._v(" "),0===!e.allcommshow?e._l(e.commandes,(function(t){return t.fixParam_BeforeShowJS?a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name)+"\n          ")]):e._e()})):e._e()],2)])])])},n=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("tr",[a("td",{attrs:{colspan:"4"}},[a("div",{staticClass:"panel-header panel-title",staticStyle:{"border-left":"0px","border-right":"0px","border-top":"0px","margin-top":"1px","margin-bottom":"1px"}},[e._v("流程审批\n        ")])])])}],i=a("e1da"),o=i["a"],l=(a("ac8e"),a("4ac2")),r=Object(l["a"])(o,s,n,!1,null,"3b8cec4e",null);t["a"]=r.exports},ac8e:function(e,t,a){"use strict";a("c73f")},c059:function(e,t,a){},c73f:function(e,t,a){},d106:function(e,t,a){"use strict";a("54c2")},e116:function(e,t,a){"use strict";a("c059")},e1da:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a450"),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("fc02"),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1___default=__webpack_require__.n(core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("e680"),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2___default=__webpack_require__.n(core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__),axios__WEBPACK_IMPORTED_MODULE_3__=__webpack_require__("7f43"),axios__WEBPACK_IMPORTED_MODULE_3___default=__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_3__);__webpack_exports__["a"]={name:"process",props:{allcommshow:{type:Number,default:1},validateFun:Function,contentPath:{type:String,default:"/ei-web/"},contextPath:{type:String,default:"/ei-web/"},extraData:{type:Object,default:function(){return{result:!0,extendParam:""}}}},watch:{taskId:function(e){var t=this;axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/getProcessCommandJSON?taskId="+this.taskId,{taskId:this.taskId}).then((function(e){t.commandes=e.data})).catch((function(e){})),this.showAndHidForm(this.pshow,this.phid)}},data:function(){return{isShowBtn:!1,btnId:"",btnName:"",btnJson:"",taskProcessDescValue:"",submitCount:0,commandes:null,instId:"",taskId:"",formUri:"",formUriView:"",pshow:"",phid:"",theRequest:{},extendParam:"",json:{},websock:""}},mounted:function(){window.location;var e=this.$route.query;this.theRequest=e,this.instId=this.theRequest.instId,this.taskId=this.theRequest.taskId,this.formUri=this.theRequest.formUri,this.formUriView=this.theRequest.formUriView,this.initWebSocket()},destroyed:function(){this.websocketclose()},methods:{initWebSocket:function(){var e="ws://".concat(window.location.host,"/sys-web/websocket/").concat(sessionStorage.getItem("userId"),"000");this.websock=new WebSocket(e),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror,this.websock.onclose=this.websocketclose},websocketonopen:function(){},websocketonerror:function(){this.initWebSocket()},websocketonmessage:function(e){console.log("接受到的消息："+e.data)},websocketsend:function(e){this.websock.send(e)},websocketclose:function(e){console.log("断开连接",e),1006===e.code&&this.initWebSocket()},showAndHidForm:function(e,t){if(null!=e&&""!==e)for(var a=e.split(","),s=0;s<a.length;s++)$("#"+a[s]).show();if(null!=t&&""!==t)for(var n=t.split(","),i=0;i<n.length;i++)$("#"+n[i]).attr("style","display:none").hide()},beforeCommandShow:function(){return!0},btnHandel:function(e){var t=e.fixParam_BeforeHandleJS,a=(e.type,"");this.json=e,console.log("undefined"!=typeof t&&null!=t&&""!=t,"fixParam_BeforeHandleJS"===e.id),"退回"===e.name||"不同意"===e.name?this.nextTask():"undefined"!=typeof t&&null!=t&&""!=t||"fixParam_BeforeHandleJS"===e.id?(this.$emit("action"),a="",this.extendParam=""):this.nextTask(),"undefined"!=typeof a&&null!=a&&""!=a||(a="")},nextTask:function(e){var t=this.json;this.doProcessTask("",t.name,t.id,t.type,t,this.extendParam)},doProcessTask:function(e,t,a,s,n,i){document.documentElement.scrollTop=0,this.processTask(t,a,s,n,i)},processTask:function(e,t,a,s,n){var i=this;this.confirmMessage("确认进行"+e+"操作？",(function(e){if(e){document.documentElement.scrollTop=0;var o=i.taskProcessDescValue;if(o.length>2e3)return void i.alertMessage("处理意见超长，最大长度为2000字符！");var l={instId:i.instId,taskId:i.taskId,commandId:t,commandType:a,processDesc:encodeURIComponent(i.taskProcessDescValue),formParam:n},r=!!s.fixParam_taskProcessDescFlag&&s.fixParam_taskProcessDescFlag,c=s.fixParam_taskProcessDescMessage?s.fixParam_taskProcessDescMessage:"";if("true"===r&&""===l.processDesc)return""===c&&(c="请填写处理意见，最大长度为2000字符！"),void i.alertMessage(c);if("rollBackTaskByExpression"===a){if(""===l.processDesc)return void i.alertMessage("请填写意见！")}else try{var u=saveDataMy();if(!u)return}catch(m){}if("modifyForm"===a){var d=s.fixParam_title;d&&""!==d||(d="修改");var p={toobarTitle:d},f=s.fixParam_url;return f&&""!==f||(f=i.formUri),f=appendParams(f,{submitBtnShow:"false"}),void i.getPageWin("layout",window).openWindow(800,600,p.toobarTitle,f,p,(function(){parent.refreshCenterRegion()}))}if("pending"===a){p={toobarTitle:"请选择转办人"},f=s.fixParam_url;if(f){var _=s.fixParam_width,v=s.fixParam_height;_||(_=800),v||(v=600),i.getPageWin("layout",window).openWindow(_,v,p.toobarTitle,f,p,(function(){var e=i.getPageWin("layout",window).invokeResult;e&&e.result&&(e.selectUserId&&(l["pendingUserId"]=e.selectUserId),i.execProcessTask(l))}))}else i.execProcessTask(l);return}if("rollBackTaskByTaskId"===a){p={toobarTitle:"请选择任务"},f="../jsps/process/listRollBackTaskList.jsp?taskId="+i.taskId+"&instId="+i.instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(800,600,p.toobarTitle,f,p,(function(){})),!1}if("rollBack"==a){p={toobarTitle:"请选择节点"},f="../jsps/process/listRollBackNodeList.jsp?taskId="+taskId+"&instId="+instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(480,320,p.toobarTitle,f,p,(function(){})),!1}if("submit"!=a){d=s.fixParam_title;d&&""!=d||(d="修改");p={toobarTitle:d},f=s.fixParam_url;if(f){_=s.fixParam_width,v=s.fixParam_height;_||(_=800),v||(v=600),i.getPageWin("layout",window).openWindow(_,v,p.toobarTitle,f,p,(function(){var e=getPageWin("layout",window).invokeResult;if(e&&e.result){if(e.taskProcessDesc){var t=$("#taskProcessDesc").val();t=""==t?e.taskProcessDesc:t+","+e.taskProcessDesc,$("#taskProcessDesc").val(t)}i.execProcessTask(l)}}))}else i.execProcessTask(l);return}if(!i.validate())return void alertMessage("表单验证不通过,请重新填写");i.execProcessTask(l)}}))},confirmMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.defaults.cancel="关闭",$.messager.confirm("提示",e,t)},alertMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.alert("提示",e,"info",t)},getPageWin:function(e,t){var a=this.getParentPageWin(e,t);return a},getParentPageWin:function(e,t){var a=t;return("undefined"===typeof a.pageId||a.pageId!=e)&&null!=a.parent?a.location.href!=a.parent.location.href?this.getPageWin(e,a.parent):null:a},closeLayUIOpen:function(){localStorage.setItem("closeKey",1),void 0!=parent.layer?parent.layer.close(parent.layer.getFrameIndex(window.name)):void 0!=parent.parent.layer?parent.parent.layer.close(parent.parent.layer.getFrameIndex(parent.name)):void 0!=parent.parent.parent.layer?parent.parent.parent.layer.close(parent.parent.parent.layer.getFrameIndex(parent.parent.name)):parent.parent.parent.parent.layer.close(parent.parent.parent.parent.layer.getFrameIndex(parent.parent.parent.name))},execProcessTask:function(e){var t=this,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:this.submitCount;a++;var s=e.instId;1===a&&axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/processTask?"+"instId=".concat(e.instId,"&taskId=").concat(e.taskId,"&commandId=").concat(e.commandId,"&commandType=").concat(e.commandType,"&processDesc=").concat(e.processDesc,"&formParam=").concat(JSON.stringify(e.formParam)),e).then((function(e){var a=e.data;a.result?t.alertMessage(a.successMessage,(function(){t.websocketsend("fixFlow,".concat(s));var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})):(t.submitCount=0,t.alertMessage(a.errorMessage,(function(){var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})))}))},getListviewWin:function(e){var t=e;return"undefined"===typeof t.listviewId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getListviewWin(t.parent):null:t},taskProcessDescCheck:function(){var e="",t=0,a=$("#taskProcessDesc").val();return e=""==a?'{"result":false,"errorMsg":"请填写退回理由！"}':'{"result":true,"extendParam":'+t+"}",e},saveData2:function saveData2(fn,data2){try{var mark=this.saveDataBefore();if(!mark)return}catch(e){}var result=this.validate(),editWin=getEditWin(window),inJCEdit=null!=editWin;"false"==parameterJsonObj.inJCEdit&&(inJCEdit=!1),result&&($.messager.progress({title:"正在处理,请稍等..."}),$("#dataForm").form("submit",{url:"../action/bizObjectBase!save.action",async:!1,onSubmit:function(){return!0},success:function success(data){$.messager.progress("close"),execProcessTask(data2);var dataObj=eval("("+data+")");if(dataObj.result){"new"==dealMark&&(keyValues=dataObj.attribute.keyValues,dealMark="update",document.getElementsByName("keyValues")[0].value=keyValues,document.getElementsByName("dealMark")[0].value=dealMark);try{saveDataAfter()}catch(e){}}if(inJCEdit){if(getEditWin(window).assetExist=!0,getEditWin(window).keyValues=dataObj.attribute.keyValues,null!=fn)return fn(dataObj)}else if($.messager.progress("close"),dataObj.result){var listviewWin=getListviewWin(window);null!=listviewWin&&listviewWin.refresh(),$.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.successMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}else $.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.errorMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}}))},getEditWin:function(e){var t=e;return"undefined"===typeof t.integrationEditorId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getEditWin(t.parent):null:t},validate:function(){return!0},saveDataBefore:function(){return!0}}}}}]);