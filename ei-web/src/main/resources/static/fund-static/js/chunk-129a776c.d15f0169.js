(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-129a776c","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"084b":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出决策名称\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",maxlength:"30"},model:{value:e.dialogInfo.appName,callback:function(t){e.$set(e.dialogInfo,"appName",t)},expression:"dialogInfo.appName"}})],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出日期\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.dialogInfo.quitDt,callback:function(t){e.$set(e.dialogInfo,"quitDt",t)},expression:"dialogInfo.quitDt"}})],1),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出方式\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-radio-group",{model:{value:e.dialogInfo.quitWay,callback:function(t){e.$set(e.dialogInfo,"quitWay",t)},expression:"dialogInfo.quitWay"}},[a("el-radio",{attrs:{label:"1"}},[e._v("全部退出")]),e._v(" "),a("el-radio",{attrs:{label:"2"}},[e._v("部分退出")])],1)],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      基金认缴总规模(万元)\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.dialogInfo.fundSubscribed)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      基金实缴规模(万元)\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.dialogInfo.fundPaidIn)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      股比%\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.dialogInfo.shareRatio,callback:function(t){e.$set(e.dialogInfo,"shareRatio",t)},expression:"dialogInfo.shareRatio"}})],1),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      回收金额(万元)\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.dialogInfo.backAmount,callback:function(t){e.$set(e.dialogInfo,"backAmount",t)},expression:"dialogInfo.backAmount"}})],1)],1),e._v(" "),"2"===e.dialogInfo.quitWay?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出认缴出资额(万元)\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.dialogInfo.exitAmount,callback:function(t){e.$set(e.dialogInfo,"exitAmount",t)},expression:"dialogInfo.exitAmount"}})],1),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出部分所占股比%\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.dialogInfo.exitShareRatio,callback:function(t){e.$set(e.dialogInfo,"exitShareRatio",t)},expression:"dialogInfo.exitShareRatio"}})],1)],1):e._e(),e._v(" "),"2"===e.dialogInfo.quitWay?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      剩余部分所占股比%\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"10"},model:{value:e.dialogInfo.restShareRatio,callback:function(t){e.$set(e.dialogInfo,"restShareRatio",t)},expression:"dialogInfo.restShareRatio"}})],1),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):e._e(),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      退出理由\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:e.dialogInfo.exitReason,callback:function(t){e.$set(e.dialogInfo,"exitReason",t)},expression:"dialogInfo.exitReason"}})],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      决策内容\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:e.dialogInfo.decisionDetail,callback:function(t){e.$set(e.dialogInfo,"decisionDetail",t)},expression:"dialogInfo.decisionDetail"}})],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      备注\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:e.dialogInfo.remark,callback:function(t){e.$set(e.dialogInfo,"remark",t)},expression:"dialogInfo.remark"}})],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      附件\n    ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"context-path":e.url.upApi},model:{value:e.dialogInfo.attaFile,callback:function(t){e.$set(e.dialogInfo,"attaFile",t)},expression:"dialogInfo.attaFile"}})],1)],1),e._v(" "),a("div",{staticClass:"processTitle",attrs:{id:"myDiv"}},[a("div",{staticClass:"bg-purple-quote"},[e._v("审批信息")]),e._v(" "),e.iframeShows?a("iframe",{attrs:{id:"contentFrame_sp",src:e.src,width:"100%",frameborder:"0",scrolling:"no"}}):e._e()]),e._v(" "),a("process",{ref:"process",attrs:{"content-path":e.url.urlApi},on:{action:e.action}})],1)},n=[],i=(a("e680"),a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("91e8")),o=a("e19c"),l=a("7562"),r=a("2c2d"),c={name:"ExitDecisionProcess",components:{process:i["a"],upLoad:r["a"]},data:function(){return{src:"",queryInfo:{},iframeShows:!1,url:o["a"],dialogInfo:{},extraData:{},appInfolist:[]}},mounted:function(){this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],this.getParentId(262,"quitTypeList"),this.getDetail(),this.queryInfo=this.$route.query,this.src="".concat(this.url.processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.iframeShows=!0},methods:{getParentId:function(e,t){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var n=[];if(s){var i=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&n.push(e)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=n}else l["a"].getCodeByParentId({parentId:e}).then((function(n){var i=[];if(s){var o=s.split(",");n.data.data.options.forEach((function(e){o.includes(e.value)&&i.push(e)}))}else i=n.data.data.options;a[t]=i,sessionStorage.setItem("code".concat(e),JSON.stringify(n.data.data.options))}))},getDetail:function(){var e=this;l["a"].selectfundQuitApplyDetail({appId:this.$route.query.bizKey}).then((function(t){e.dialogInfo=t.data.data}))},action:function(){var e=this;null!==this.dialogInfo.appName&&void 0!==this.dialogInfo.appName&&""!==this.dialogInfo.appName.trim()?null!==this.dialogInfo.quitDt&&void 0!==this.dialogInfo.quitDt&&""!==this.dialogInfo.quitDt?null!==this.dialogInfo.quitWay&&void 0!==this.dialogInfo.quitWay&&""!==this.dialogInfo.quitWay?null!==this.dialogInfo.shareRatio&&void 0!==this.dialogInfo.shareRatio&&""!==this.dialogInfo.shareRatio.trim()&&isNaN(Number(this.dialogInfo.shareRatio))?this.$message({offset:150,type:"warning",message:"请正确填写股比,格式为数字"}):null!==this.dialogInfo.backAmount&&void 0!==this.dialogInfo.backAmount&&""!==this.dialogInfo.backAmount.trim()&&isNaN(Number(this.dialogInfo.backAmount))?this.$message({offset:150,type:"warning",message:"请正确填写回收金额,格式为数字"}):null!==this.dialogInfo.exitAmount&&void 0!==this.dialogInfo.exitAmount&&""!==this.dialogInfo.exitAmount.trim()&&isNaN(Number(this.dialogInfo.exitAmount))?this.$message({offset:150,type:"warning",message:"请正确填写退出认缴出资额,格式为数字"}):null!==this.dialogInfo.exitShareRatio&&void 0!==this.dialogInfo.exitShareRatio&&""!==this.dialogInfo.exitShareRatio.trim()&&isNaN(Number(this.dialogInfo.exitShareRatio))?this.$message({offset:150,type:"warning",message:"请正确填写退出部分所占股比,格式为数字"}):null!==this.dialogInfo.restShareRatio&&void 0!==this.dialogInfo.restShareRatio&&""!==String(this.dialogInfo.restShareRatio).trim()&&isNaN(Number(this.dialogInfo.restShareRatio))?this.$message({offset:150,type:"warning",message:"请正确填写剩余部分所占股比,格式为数字"}):(this.dialogInfo.tag="3",l["a"].updatefundQuitApplyList(this.dialogInfo).then((function(t){"0"===t.data.status&&"成功"===t.data.msg?e.$refs.process.nextTask():e.$message({offset:150,type:"warning",message:t.data.msg})}))):this.$message({offset:150,type:"warning",message:"请选择退出方式"}):this.$message({offset:150,type:"warning",message:"请选择退出日期"}):this.$message({offset:150,type:"warning",message:"请填写退出决策名称"})}}},d=c,u=(a("7c1d"),a("4ac2")),f=Object(u["a"])(d,s,n,!1,null,"2d5aba8d",null);t["default"]=f.exports},"20f0":function(e,t,a){},"2c2d":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"upload-box"},[e.hiddenUpload?a("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return a("h3",{key:t.uid},[a("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[a("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},n=[],i=(a("fc02"),a("a450"),a("e680"),a("7f43")),o=a.n(i),l={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,a=e||this.value;""!=a&&null!=a||(a="-1");var s=this.contextPath+"attachController/getFileList?fieldToken="+a;o()({url:s,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var a=e.name.substring(e.name.lastIndexOf(".")+1),s="rp"===a,n="vsd"===a;(s||n)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var a=this;o()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=t)}))},handleError:function(e,t,a){},handleSuccess:function(e,t,a){var s=e.data.fieldToken;void 0==s&&(s=this.value);var n=this.updatePath.split("=");""!=n[1]&&""==s&&(s=n[1]),this.$emit("update-value",s),this.fileLists=a,this.getFileData(s)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},r=l,c=(a("e116"),a("4ac2")),d=Object(c["a"])(r,s,n,!1,null,"e22390a6",null);t["a"]=d.exports},"7c1d":function(e,t,a){"use strict";a("20f0")},"91e8":function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.instId,expression:"instId"}],attrs:{id:"instId",type:"hidden"},domProps:{value:e.instId},on:{input:function(t){t.target.composing||(e.instId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.taskId,expression:"taskId"}],attrs:{id:"taskId",type:"hidden"},domProps:{value:e.taskId},on:{input:function(t){t.target.composing||(e.taskId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUri,expression:"formUri"}],attrs:{id:"formUri",type:"hidden"},domProps:{value:e.formUri},on:{input:function(t){t.target.composing||(e.formUri=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUriView,expression:"formUriView"}],attrs:{id:"formUriView",type:"hidden"},domProps:{value:e.formUriView},on:{input:function(t){t.target.composing||(e.formUriView=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.pshow,expression:"pshow"}],attrs:{id:"pshow",type:"hidden"},domProps:{value:e.pshow},on:{input:function(t){t.target.composing||(e.pshow=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.phid,expression:"phid"}],attrs:{id:"phid",type:"hidden"},domProps:{value:e.phid},on:{input:function(t){t.target.composing||(e.phid=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.contentPath,expression:"contentPath"}],attrs:{id:"contentPath",type:"hidden"},domProps:{value:e.contentPath},on:{input:function(t){t.target.composing||(e.contentPath=t.target.value)}}}),e._v(" "),a("table",{staticClass:"addtable",staticStyle:{"margin-top":"0px","margin-bottom":"10px"},attrs:{id:"formToolBarTable",width:"100%",border:"0",cellspacing:"0",cellpadding:"0"}},[e._m(0),e._v(" "),a("tr",{staticStyle:{border:"1px solid #D9D9D9"}},[a("td",{staticClass:"td_name",staticStyle:{width:"20%",padding:"0px","font-size":"13px","text-align":"center","vertical-align":"middle",color:"#997528",background:"#eae3d8"}},[e._v("处理意见")]),e._v(" "),a("td",{staticClass:"td_value",staticStyle:{width:"82%",padding:"6px"},attrs:{colspan:"3"}},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:e.taskProcessDescValue,expression:"taskProcessDescValue"}],staticStyle:{width:"100%",height:"70px","border-radius":"4px"},attrs:{name:"taskProcessDesc",id:"taskProcessDesc"},domProps:{value:e.taskProcessDescValue},on:{input:function(t){t.target.composing||(e.taskProcessDescValue=t.target.value)}}})])]),e._v(" "),a("tr",[a("td",{staticClass:"td_name",staticStyle:{width:"18%"}}),e._v(" "),a("td",{staticClass:"td_value drawablel",staticStyle:{width:"82%"},attrs:{colspan:"3",id:"processToolBar"}},[1===e.allcommshow?e._l(e.commandes,(function(t){return a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name))])})):e._e(),e._v(" "),0===!e.allcommshow?e._l(e.commandes,(function(t){return t.fixParam_BeforeShowJS?a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name)+"\n          ")]):e._e()})):e._e()],2)])])])},n=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("tr",[a("td",{attrs:{colspan:"4"}},[a("div",{staticClass:"panel-header panel-title",staticStyle:{"border-left":"0px","border-right":"0px","border-top":"0px","margin-top":"1px","margin-bottom":"1px"}},[e._v("流程审批\n        ")])])])}],i=a("e1da"),o=i["a"],l=(a("ac8e"),a("4ac2")),r=Object(l["a"])(o,s,n,!1,null,"3b8cec4e",null);t["a"]=r.exports},ac8e:function(e,t,a){"use strict";a("c73f")},c059:function(e,t,a){},c73f:function(e,t,a){},e116:function(e,t,a){"use strict";a("c059")},e1da:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a450"),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("fc02"),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1___default=__webpack_require__.n(core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("e680"),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2___default=__webpack_require__.n(core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__),axios__WEBPACK_IMPORTED_MODULE_3__=__webpack_require__("7f43"),axios__WEBPACK_IMPORTED_MODULE_3___default=__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_3__);__webpack_exports__["a"]={name:"process",props:{allcommshow:{type:Number,default:1},validateFun:Function,contentPath:{type:String,default:"/ei-web/"},contextPath:{type:String,default:"/ei-web/"},extraData:{type:Object,default:function(){return{result:!0,extendParam:""}}}},watch:{taskId:function(e){var t=this;axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/getProcessCommandJSON?taskId="+this.taskId,{taskId:this.taskId}).then((function(e){t.commandes=e.data})).catch((function(e){})),this.showAndHidForm(this.pshow,this.phid)}},data:function(){return{isShowBtn:!1,btnId:"",btnName:"",btnJson:"",taskProcessDescValue:"",submitCount:0,commandes:null,instId:"",taskId:"",formUri:"",formUriView:"",pshow:"",phid:"",theRequest:{},extendParam:"",json:{},websock:""}},mounted:function(){window.location;var e=this.$route.query;this.theRequest=e,this.instId=this.theRequest.instId,this.taskId=this.theRequest.taskId,this.formUri=this.theRequest.formUri,this.formUriView=this.theRequest.formUriView,this.initWebSocket()},destroyed:function(){this.websocketclose()},methods:{initWebSocket:function(){var e="ws://".concat(window.location.host,"/sys-web/websocket/").concat(sessionStorage.getItem("userId"),"000");this.websock=new WebSocket(e),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror,this.websock.onclose=this.websocketclose},websocketonopen:function(){},websocketonerror:function(){this.initWebSocket()},websocketonmessage:function(e){console.log("接受到的消息："+e.data)},websocketsend:function(e){this.websock.send(e)},websocketclose:function(e){console.log("断开连接",e),1006===e.code&&this.initWebSocket()},showAndHidForm:function(e,t){if(null!=e&&""!==e)for(var a=e.split(","),s=0;s<a.length;s++)$("#"+a[s]).show();if(null!=t&&""!==t)for(var n=t.split(","),i=0;i<n.length;i++)$("#"+n[i]).attr("style","display:none").hide()},beforeCommandShow:function(){return!0},btnHandel:function(e){var t=e.fixParam_BeforeHandleJS,a=(e.type,"");this.json=e,console.log("undefined"!=typeof t&&null!=t&&""!=t,"fixParam_BeforeHandleJS"===e.id),"退回"===e.name||"不同意"===e.name?this.nextTask():"undefined"!=typeof t&&null!=t&&""!=t||"fixParam_BeforeHandleJS"===e.id?(this.$emit("action"),a="",this.extendParam=""):this.nextTask(),"undefined"!=typeof a&&null!=a&&""!=a||(a="")},nextTask:function(e){var t=this.json;this.doProcessTask("",t.name,t.id,t.type,t,this.extendParam)},doProcessTask:function(e,t,a,s,n,i){document.documentElement.scrollTop=0,this.processTask(t,a,s,n,i)},processTask:function(e,t,a,s,n){var i=this;this.confirmMessage("确认进行"+e+"操作？",(function(e){if(e){document.documentElement.scrollTop=0;var o=i.taskProcessDescValue;if(o.length>2e3)return void i.alertMessage("处理意见超长，最大长度为2000字符！");var l={instId:i.instId,taskId:i.taskId,commandId:t,commandType:a,processDesc:encodeURIComponent(i.taskProcessDescValue),formParam:n},r=!!s.fixParam_taskProcessDescFlag&&s.fixParam_taskProcessDescFlag,c=s.fixParam_taskProcessDescMessage?s.fixParam_taskProcessDescMessage:"";if("true"===r&&""===l.processDesc)return""===c&&(c="请填写处理意见，最大长度为2000字符！"),void i.alertMessage(c);if("rollBackTaskByExpression"===a){if(""===l.processDesc)return void i.alertMessage("请填写意见！")}else try{var d=saveDataMy();if(!d)return}catch(_){}if("modifyForm"===a){var u=s.fixParam_title;u&&""!==u||(u="修改");var f={toobarTitle:u},p=s.fixParam_url;return p&&""!==p||(p=i.formUri),p=appendParams(p,{submitBtnShow:"false"}),void i.getPageWin("layout",window).openWindow(800,600,f.toobarTitle,p,f,(function(){parent.refreshCenterRegion()}))}if("pending"===a){f={toobarTitle:"请选择转办人"},p=s.fixParam_url;if(p){var m=s.fixParam_width,h=s.fixParam_height;m||(m=800),h||(h=600),i.getPageWin("layout",window).openWindow(m,h,f.toobarTitle,p,f,(function(){var e=i.getPageWin("layout",window).invokeResult;e&&e.result&&(e.selectUserId&&(l["pendingUserId"]=e.selectUserId),i.execProcessTask(l))}))}else i.execProcessTask(l);return}if("rollBackTaskByTaskId"===a){f={toobarTitle:"请选择任务"},p="../jsps/process/listRollBackTaskList.jsp?taskId="+i.taskId+"&instId="+i.instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(800,600,f.toobarTitle,p,f,(function(){})),!1}if("rollBack"==a){f={toobarTitle:"请选择节点"},p="../jsps/process/listRollBackNodeList.jsp?taskId="+taskId+"&instId="+instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(480,320,f.toobarTitle,p,f,(function(){})),!1}if("submit"!=a){u=s.fixParam_title;u&&""!=u||(u="修改");f={toobarTitle:u},p=s.fixParam_url;if(p){m=s.fixParam_width,h=s.fixParam_height;m||(m=800),h||(h=600),i.getPageWin("layout",window).openWindow(m,h,f.toobarTitle,p,f,(function(){var e=getPageWin("layout",window).invokeResult;if(e&&e.result){if(e.taskProcessDesc){var t=$("#taskProcessDesc").val();t=""==t?e.taskProcessDesc:t+","+e.taskProcessDesc,$("#taskProcessDesc").val(t)}i.execProcessTask(l)}}))}else i.execProcessTask(l);return}if(!i.validate())return void alertMessage("表单验证不通过,请重新填写");i.execProcessTask(l)}}))},confirmMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.defaults.cancel="关闭",$.messager.confirm("提示",e,t)},alertMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.alert("提示",e,"info",t)},getPageWin:function(e,t){var a=this.getParentPageWin(e,t);return a},getParentPageWin:function(e,t){var a=t;return("undefined"===typeof a.pageId||a.pageId!=e)&&null!=a.parent?a.location.href!=a.parent.location.href?this.getPageWin(e,a.parent):null:a},closeLayUIOpen:function(){localStorage.setItem("closeKey",1),void 0!=parent.layer?parent.layer.close(parent.layer.getFrameIndex(window.name)):void 0!=parent.parent.layer?parent.parent.layer.close(parent.parent.layer.getFrameIndex(parent.name)):void 0!=parent.parent.parent.layer?parent.parent.parent.layer.close(parent.parent.parent.layer.getFrameIndex(parent.parent.name)):parent.parent.parent.parent.layer.close(parent.parent.parent.parent.layer.getFrameIndex(parent.parent.parent.name))},execProcessTask:function(e){var t=this,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:this.submitCount;a++;var s=e.instId;1===a&&axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/processTask?"+"instId=".concat(e.instId,"&taskId=").concat(e.taskId,"&commandId=").concat(e.commandId,"&commandType=").concat(e.commandType,"&processDesc=").concat(e.processDesc,"&formParam=").concat(JSON.stringify(e.formParam)),e).then((function(e){var a=e.data;a.result?t.alertMessage(a.successMessage,(function(){t.websocketsend("fixFlow,".concat(s));var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})):(t.submitCount=0,t.alertMessage(a.errorMessage,(function(){var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})))}))},getListviewWin:function(e){var t=e;return"undefined"===typeof t.listviewId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getListviewWin(t.parent):null:t},taskProcessDescCheck:function(){var e="",t=0,a=$("#taskProcessDesc").val();return e=""==a?'{"result":false,"errorMsg":"请填写退回理由！"}':'{"result":true,"extendParam":'+t+"}",e},saveData2:function saveData2(fn,data2){try{var mark=this.saveDataBefore();if(!mark)return}catch(e){}var result=this.validate(),editWin=getEditWin(window),inJCEdit=null!=editWin;"false"==parameterJsonObj.inJCEdit&&(inJCEdit=!1),result&&($.messager.progress({title:"正在处理,请稍等..."}),$("#dataForm").form("submit",{url:"../action/bizObjectBase!save.action",async:!1,onSubmit:function(){return!0},success:function success(data){$.messager.progress("close"),execProcessTask(data2);var dataObj=eval("("+data+")");if(dataObj.result){"new"==dealMark&&(keyValues=dataObj.attribute.keyValues,dealMark="update",document.getElementsByName("keyValues")[0].value=keyValues,document.getElementsByName("dealMark")[0].value=dealMark);try{saveDataAfter()}catch(e){}}if(inJCEdit){if(getEditWin(window).assetExist=!0,getEditWin(window).keyValues=dataObj.attribute.keyValues,null!=fn)return fn(dataObj)}else if($.messager.progress("close"),dataObj.result){var listviewWin=getListviewWin(window);null!=listviewWin&&listviewWin.refresh(),$.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.successMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}else $.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.errorMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}}))},getEditWin:function(e){var t=e;return"undefined"===typeof t.integrationEditorId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getEditWin(t.parent):null:t},validate:function(){return!0},saveDataBefore:function(){return!0}}}}}]);