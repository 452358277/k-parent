(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ac79af2","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"08ec":function(e,t,a){},"2c2d":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"upload-box"},[e.hiddenUpload?a("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return a("h3",{key:t.uid},[a("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[a("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},s=[],i=(a("fc02"),a("a450"),a("e680"),a("7f43")),o=a.n(i),l={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,a=e||this.value;""!=a&&null!=a||(a="-1");var n=this.contextPath+"attachController/getFileList?fieldToken="+a;o()({url:n,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var a=e.name.substring(e.name.lastIndexOf(".")+1),n="rp"===a,s="vsd"===a;(n||s)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var a=this;o()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=t)}))},handleError:function(e,t,a){},handleSuccess:function(e,t,a){var n=e.data.fieldToken;void 0==n&&(n=this.value);var s=this.updatePath.split("=");""!=s[1]&&""==n&&(n=s[1]),this.$emit("update-value",n),this.fileLists=a,this.getFileData(n)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},r=l,c=(a("e116"),a("4ac2")),d=Object(c["a"])(r,n,s,!1,null,"e22390a6",null);t["a"]=d.exports},"664c":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[e._v("尽调信息")]),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        计划名称\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",remote:"","reserve-keyword":"",placeholder:"请输入关键词查询","remote-method":e.searchPlan,loading:e.loading},on:{change:e.changePlan},model:{value:e.dialogInfo.planId,callback:function(t){e.$set(e.dialogInfo,"planId",t)},expression:"dialogInfo.planId"}},e._l(e.planList,(function(e){return a("el-option",{key:e.planId,attrs:{label:e.planName,value:e.planId}})})),1)],1),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        尽调范围\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.dialogInfo.surveyContentName)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        计划开始时间\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.dialogInfo.startDate)+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        计划结束时间\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.dialogInfo.endDate)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        尽调类型\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.dialogInfo.interiorName)+"\n      ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        小组成员\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n        "+e._s(e.dialogInfo.userName)+"\n      ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        是否召开投资咨询委员会\n        "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-radio",{attrs:{label:"0"},model:{value:e.dialogInfo.isMeeting,callback:function(t){e.$set(e.dialogInfo,"isMeeting",t)},expression:"dialogInfo.isMeeting"}},[e._v("否")]),e._v(" "),a("el-radio",{attrs:{label:"1"},model:{value:e.dialogInfo.isMeeting,callback:function(t){e.$set(e.dialogInfo,"isMeeting",t)},expression:"dialogInfo.isMeeting"}},[e._v("是")])],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        附件说明\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",type:"textarea",rows:4},model:{value:e.dialogInfo.rptDetail,callback:function(t){e.$set(e.dialogInfo,"rptDetail",t)},expression:"dialogInfo.rptDetail"}})],1)],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n        附件\n      ")]),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{model:{value:e.dialogInfo.rptFile,callback:function(t){e.$set(e.dialogInfo,"rptFile",t)},expression:"dialogInfo.rptFile"}})],1)],1)],1),e._v(" "),"1"!==e.dialogInfo.interior?a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[e._v("尽调机构信息")]),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData2.data,"header-cell-style":{background:"#eae3d8",color:"#997528",fontSize:"12px",textAlign:"center"},border:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{label:"尽调机构",align:"center","min-width":"230",prop:"orgName"}}),e._v(" "),a("el-table-column",{attrs:{label:"机构类型",align:"center","min-width":"120",prop:"orgTypeName"}}),e._v(" "),a("el-table-column",{attrs:{label:"联系人",align:"center","min-width":"120",prop:"contact"}}),e._v(" "),a("el-table-column",{attrs:{label:"联系方式",align:"center","min-width":"120",prop:"phoneNo"}}),e._v(" "),a("el-table-column",{attrs:{label:"尽调费用(万元)",align:"center","min-width":"120",prop:"money"}}),e._v(" "),a("el-table-column",{attrs:{label:"尽调参与人员",align:"center","min-width":"120",prop:"personnel"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{size:"small",maxlength:"20"},model:{value:t.row.personnel,callback:function(a){e.$set(t.row,"personnel",a)},expression:"scope.row.personnel"}})]}}],null,!1,1971265783)})],1)],1):e._e(),e._v(" "),"view"!==e.steps?a("div",{staticClass:"processTitle",attrs:{id:"myDiv"}},[a("div",{staticClass:"bg-purple-quote"},[e._v("审批信息")]),e._v(" "),e.iframeShows?a("iframe",{attrs:{src:e.src,width:"100%",id:"contentFrame_sp",frameborder:"0",scrolling:"no"}}):e._e()]):e._e(),e._v(" "),"view"!==e.steps?a("process",{ref:"process",attrs:{"content-path":e.url.urlApi},on:{action:e.action}}):e._e()],1)},s=[],i=(a("1bc7"),a("8dee"),a("91e8")),o=a("2c2d"),l=a("e19c"),r=a("7562"),c={name:"Index",components:{upLoad:o["a"],process:i["a"]},data:function(){return{src:"",queryInfo:{},iframeShows:!1,rptId:this.$route.query.rptId,url:l["a"],dialogInfo:{},tableData2:{data:[]},loading:!1,planList:[],steps:""}},mounted:function(){this.queryInfo=this.$route.query,this.iframeShows=!0,this.src="".concat(l["a"].processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.getPageDetail(),this.steps=this.$route.query.steps,this.node=this.$route.query.node},methods:{getPageDetail:function(){var e=this;r["a"].dueDiligenceRptDetail({rptId:this.rptId}).then((function(t){if(e.dialogInfo=t.data.data,e.dialogVisible=!0,e.planList=[t.data.data],e.$set(e.dialogInfo,"rptDetail",t.data.data.dueDiligenceRpt.rptDetail),e.$set(e.dialogInfo,"rptFile",t.data.data.dueDiligenceRpt.rptFile?t.data.data.dueDiligenceRpt.rptFile:""),e.$set(e.dialogInfo,"rptId",t.data.data.dueDiligenceRpt.rptId),e.$set(e.dialogInfo,"isMeeting",t.data.data.dueDiligenceRpt.isMeeting),e.dialogInfo.surveyPlanModel.createDt="",e.dialogInfo.surveyPlanModel.updateDt="",e.dialogInfo.dueDiligenceRpt.createDt="",e.dialogInfo.dueDiligenceRpt.updateDt="",t.data.data.listSurverCooorg&&t.data.data.listSurverCooorg.length>0){var a=t.data.data.listSurverCooorg.map((function(t){return e.$set(t.cooOrgModel,"partnerorGaid",t.cooOrgModel.orgId),e.$set(t.cooOrgModel,"money",t.money),e.$set(t.cooOrgModel,"personnel",t.personnel),t.cooOrgModel.createDt="",t.cooOrgModel.updateDt="",t.cooOrgModel}));e.tableData2.data=a}else e.tableData2.data=[];e.dialogInfo.userName=e.dialogInfo.list.map((function(e){return e.planName})).join(),e.dialogInfo.userId=e.dialogInfo.list.map((function(e){return e.planUseId})).join()}))},saveInfo:function(){r["a"].dueDiligenceRptUpdate(this.dialogInfo).then((function(e){}))},searchPlan:function(e){var t=this;r["a"].urveyPlanList({pageSize:"10",currPage:"1",keyword:e.replace(/\s+/g,""),type:!0}).then((function(e){t.planList=e.data.data}))},changePlan:function(e){var t=this,a={};this.planList.length>0&&this.planList.forEach((function(n){if(n.planId===e)if(a=n,t.dialogInfo=a,t.dialogInfo.rptId=t.rptId,t.dialogInfo.listSurverCooorg&&t.dialogInfo.listSurverCooorg.length>0){var s=a.listSurverCooorg.map((function(e){return t.$set(e.cooOrgModel,"partnerorGaid",e.cooOrgModel.orgId),t.planList.push(e.cooOrgModel),t.$set(e.cooOrgModel,"money",e.money),e.cooOrgModel.createDt="",e.cooOrgModel.updateDt="",e.cooOrgModel}));t.tableData2.data=s}else t.tableData2.data=[]}))},searchMechanism:function(e){},action:function(){var e=this;if(this.dialogInfo.list=this.tableData2.data,this.dialogInfo.surveyPlan=this.dialogInfo.planName,this.dialogInfo.surveyPlanId=this.dialogInfo.planId,this.dialogInfo.isStartFlow=!0,this.dialogInfo.status="1",""!==this.dialogInfo.planName&&null!==this.dialogInfo.planName&&void 0!==this.dialogInfo.planName){for(var t=0;t<this.dialogInfo.list.length;t++)if(""===this.dialogInfo.list[t].personnel||void 0===this.dialogInfo.list[t].personnel||null===this.dialogInfo.list[t].personnel)return void this.$message({offset:150,type:"warning",message:"请填写尽调参与人员"});""!==this.dialogInfo.isMeeting&&null!==this.dialogInfo.isMeeting&&void 0!==this.dialogInfo.isMeeting?r["a"].dueDiligenceRptSave(this.dialogInfo).then((function(t){e.$refs.process.nextTask()})):this.$message({offset:150,type:"warning",message:"请选择是否需要召开投资咨询委员会"})}else this.$message({offset:150,type:"warning",message:"请选择计划名称"})}}},d=c,u=(a("bc7f"),a("4ac2")),p=Object(u["a"])(d,n,s,!1,null,null,null);t["default"]=p.exports},"91e8":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.instId,expression:"instId"}],attrs:{id:"instId",type:"hidden"},domProps:{value:e.instId},on:{input:function(t){t.target.composing||(e.instId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.taskId,expression:"taskId"}],attrs:{id:"taskId",type:"hidden"},domProps:{value:e.taskId},on:{input:function(t){t.target.composing||(e.taskId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUri,expression:"formUri"}],attrs:{id:"formUri",type:"hidden"},domProps:{value:e.formUri},on:{input:function(t){t.target.composing||(e.formUri=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUriView,expression:"formUriView"}],attrs:{id:"formUriView",type:"hidden"},domProps:{value:e.formUriView},on:{input:function(t){t.target.composing||(e.formUriView=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.pshow,expression:"pshow"}],attrs:{id:"pshow",type:"hidden"},domProps:{value:e.pshow},on:{input:function(t){t.target.composing||(e.pshow=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.phid,expression:"phid"}],attrs:{id:"phid",type:"hidden"},domProps:{value:e.phid},on:{input:function(t){t.target.composing||(e.phid=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.contentPath,expression:"contentPath"}],attrs:{id:"contentPath",type:"hidden"},domProps:{value:e.contentPath},on:{input:function(t){t.target.composing||(e.contentPath=t.target.value)}}}),e._v(" "),a("table",{staticClass:"addtable",staticStyle:{"margin-top":"0px","margin-bottom":"10px"},attrs:{id:"formToolBarTable",width:"100%",border:"0",cellspacing:"0",cellpadding:"0"}},[e._m(0),e._v(" "),a("tr",{staticStyle:{border:"1px solid #D9D9D9"}},[a("td",{staticClass:"td_name",staticStyle:{width:"20%",padding:"0px","font-size":"13px","text-align":"center","vertical-align":"middle",color:"#997528",background:"#eae3d8"}},[e._v("处理意见")]),e._v(" "),a("td",{staticClass:"td_value",staticStyle:{width:"82%",padding:"6px"},attrs:{colspan:"3"}},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:e.taskProcessDescValue,expression:"taskProcessDescValue"}],staticStyle:{width:"100%",height:"70px","border-radius":"4px"},attrs:{name:"taskProcessDesc",id:"taskProcessDesc"},domProps:{value:e.taskProcessDescValue},on:{input:function(t){t.target.composing||(e.taskProcessDescValue=t.target.value)}}})])]),e._v(" "),a("tr",[a("td",{staticClass:"td_name",staticStyle:{width:"18%"}}),e._v(" "),a("td",{staticClass:"td_value drawablel",staticStyle:{width:"82%"},attrs:{colspan:"3",id:"processToolBar"}},[1===e.allcommshow?e._l(e.commandes,(function(t){return a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name))])})):e._e(),e._v(" "),0===!e.allcommshow?e._l(e.commandes,(function(t){return t.fixParam_BeforeShowJS?a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name)+"\n          ")]):e._e()})):e._e()],2)])])])},s=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("tr",[a("td",{attrs:{colspan:"4"}},[a("div",{staticClass:"panel-header panel-title",staticStyle:{"border-left":"0px","border-right":"0px","border-top":"0px","margin-top":"1px","margin-bottom":"1px"}},[e._v("流程审批\n        ")])])])}],i=a("e1da"),o=i["a"],l=(a("ac8e"),a("4ac2")),r=Object(l["a"])(o,n,s,!1,null,"3b8cec4e",null);t["a"]=r.exports},ac8e:function(e,t,a){"use strict";a("c73f")},bc7f:function(e,t,a){"use strict";a("08ec")},c059:function(e,t,a){},c73f:function(e,t,a){},e116:function(e,t,a){"use strict";a("c059")},e1da:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a450"),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("fc02"),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1___default=__webpack_require__.n(core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("e680"),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2___default=__webpack_require__.n(core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__),axios__WEBPACK_IMPORTED_MODULE_3__=__webpack_require__("7f43"),axios__WEBPACK_IMPORTED_MODULE_3___default=__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_3__);__webpack_exports__["a"]={name:"process",props:{allcommshow:{type:Number,default:1},validateFun:Function,contentPath:{type:String,default:"/ei-web/"},contextPath:{type:String,default:"/ei-web/"},extraData:{type:Object,default:function(){return{result:!0,extendParam:""}}}},watch:{taskId:function(e){var t=this;axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/getProcessCommandJSON?taskId="+this.taskId,{taskId:this.taskId}).then((function(e){t.commandes=e.data})).catch((function(e){})),this.showAndHidForm(this.pshow,this.phid)}},data:function(){return{isShowBtn:!1,btnId:"",btnName:"",btnJson:"",taskProcessDescValue:"",submitCount:0,commandes:null,instId:"",taskId:"",formUri:"",formUriView:"",pshow:"",phid:"",theRequest:{},extendParam:"",json:{},websock:""}},mounted:function(){window.location;var e=this.$route.query;this.theRequest=e,this.instId=this.theRequest.instId,this.taskId=this.theRequest.taskId,this.formUri=this.theRequest.formUri,this.formUriView=this.theRequest.formUriView,this.initWebSocket()},destroyed:function(){this.websocketclose()},methods:{initWebSocket:function(){var e="ws://".concat(window.location.host,"/sys-web/websocket/").concat(sessionStorage.getItem("userId"),"000");this.websock=new WebSocket(e),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror,this.websock.onclose=this.websocketclose},websocketonopen:function(){},websocketonerror:function(){this.initWebSocket()},websocketonmessage:function(e){console.log("接受到的消息："+e.data)},websocketsend:function(e){this.websock.send(e)},websocketclose:function(e){console.log("断开连接",e),1006===e.code&&this.initWebSocket()},showAndHidForm:function(e,t){if(null!=e&&""!==e)for(var a=e.split(","),n=0;n<a.length;n++)$("#"+a[n]).show();if(null!=t&&""!==t)for(var s=t.split(","),i=0;i<s.length;i++)$("#"+s[i]).attr("style","display:none").hide()},beforeCommandShow:function(){return!0},btnHandel:function(e){var t=e.fixParam_BeforeHandleJS,a=(e.type,"");this.json=e,console.log("undefined"!=typeof t&&null!=t&&""!=t,"fixParam_BeforeHandleJS"===e.id),"退回"===e.name||"不同意"===e.name?this.nextTask():"undefined"!=typeof t&&null!=t&&""!=t||"fixParam_BeforeHandleJS"===e.id?(this.$emit("action"),a="",this.extendParam=""):this.nextTask(),"undefined"!=typeof a&&null!=a&&""!=a||(a="")},nextTask:function(e){var t=this.json;this.doProcessTask("",t.name,t.id,t.type,t,this.extendParam)},doProcessTask:function(e,t,a,n,s,i){document.documentElement.scrollTop=0,this.processTask(t,a,n,s,i)},processTask:function(e,t,a,n,s){var i=this;this.confirmMessage("确认进行"+e+"操作？",(function(e){if(e){document.documentElement.scrollTop=0;var o=i.taskProcessDescValue;if(o.length>2e3)return void i.alertMessage("处理意见超长，最大长度为2000字符！");var l={instId:i.instId,taskId:i.taskId,commandId:t,commandType:a,processDesc:encodeURIComponent(i.taskProcessDescValue),formParam:s},r=!!n.fixParam_taskProcessDescFlag&&n.fixParam_taskProcessDescFlag,c=n.fixParam_taskProcessDescMessage?n.fixParam_taskProcessDescMessage:"";if("true"===r&&""===l.processDesc)return""===c&&(c="请填写处理意见，最大长度为2000字符！"),void i.alertMessage(c);if("rollBackTaskByExpression"===a){if(""===l.processDesc)return void i.alertMessage("请填写意见！")}else try{var d=saveDataMy();if(!d)return}catch(g){}if("modifyForm"===a){var u=n.fixParam_title;u&&""!==u||(u="修改");var p={toobarTitle:u},f=n.fixParam_url;return f&&""!==f||(f=i.formUri),f=appendParams(f,{submitBtnShow:"false"}),void i.getPageWin("layout",window).openWindow(800,600,p.toobarTitle,f,p,(function(){parent.refreshCenterRegion()}))}if("pending"===a){p={toobarTitle:"请选择转办人"},f=n.fixParam_url;if(f){var m=n.fixParam_width,_=n.fixParam_height;m||(m=800),_||(_=600),i.getPageWin("layout",window).openWindow(m,_,p.toobarTitle,f,p,(function(){var e=i.getPageWin("layout",window).invokeResult;e&&e.result&&(e.selectUserId&&(l["pendingUserId"]=e.selectUserId),i.execProcessTask(l))}))}else i.execProcessTask(l);return}if("rollBackTaskByTaskId"===a){p={toobarTitle:"请选择任务"},f="../jsps/process/listRollBackTaskList.jsp?taskId="+i.taskId+"&instId="+i.instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(800,600,p.toobarTitle,f,p,(function(){})),!1}if("rollBack"==a){p={toobarTitle:"请选择节点"},f="../jsps/process/listRollBackNodeList.jsp?taskId="+taskId+"&instId="+instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(480,320,p.toobarTitle,f,p,(function(){})),!1}if("submit"!=a){u=n.fixParam_title;u&&""!=u||(u="修改");p={toobarTitle:u},f=n.fixParam_url;if(f){m=n.fixParam_width,_=n.fixParam_height;m||(m=800),_||(_=600),i.getPageWin("layout",window).openWindow(m,_,p.toobarTitle,f,p,(function(){var e=getPageWin("layout",window).invokeResult;if(e&&e.result){if(e.taskProcessDesc){var t=$("#taskProcessDesc").val();t=""==t?e.taskProcessDesc:t+","+e.taskProcessDesc,$("#taskProcessDesc").val(t)}i.execProcessTask(l)}}))}else i.execProcessTask(l);return}if(!i.validate())return void alertMessage("表单验证不通过,请重新填写");i.execProcessTask(l)}}))},confirmMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.defaults.cancel="关闭",$.messager.confirm("提示",e,t)},alertMessage:function(e,t){$.messager.defaults.ok="确认",$.messager.alert("提示",e,"info",t)},getPageWin:function(e,t){var a=this.getParentPageWin(e,t);return a},getParentPageWin:function(e,t){var a=t;return("undefined"===typeof a.pageId||a.pageId!=e)&&null!=a.parent?a.location.href!=a.parent.location.href?this.getPageWin(e,a.parent):null:a},closeLayUIOpen:function(){localStorage.setItem("closeKey",1),void 0!=parent.layer?parent.layer.close(parent.layer.getFrameIndex(window.name)):void 0!=parent.parent.layer?parent.parent.layer.close(parent.parent.layer.getFrameIndex(parent.name)):void 0!=parent.parent.parent.layer?parent.parent.parent.layer.close(parent.parent.parent.layer.getFrameIndex(parent.parent.name)):parent.parent.parent.parent.layer.close(parent.parent.parent.parent.layer.getFrameIndex(parent.parent.parent.name))},execProcessTask:function(e){var t=this,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:this.submitCount;a++;var n=e.instId;1===a&&axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/processTask?"+"instId=".concat(e.instId,"&taskId=").concat(e.taskId,"&commandId=").concat(e.commandId,"&commandType=").concat(e.commandType,"&processDesc=").concat(e.processDesc,"&formParam=").concat(JSON.stringify(e.formParam)),e).then((function(e){var a=e.data;a.result?t.alertMessage(a.successMessage,(function(){t.websocketsend("fixFlow,".concat(n));var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})):(t.submitCount=0,t.alertMessage(a.errorMessage,(function(){var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})))}))},getListviewWin:function(e){var t=e;return"undefined"===typeof t.listviewId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getListviewWin(t.parent):null:t},taskProcessDescCheck:function(){var e="",t=0,a=$("#taskProcessDesc").val();return e=""==a?'{"result":false,"errorMsg":"请填写退回理由！"}':'{"result":true,"extendParam":'+t+"}",e},saveData2:function saveData2(fn,data2){try{var mark=this.saveDataBefore();if(!mark)return}catch(e){}var result=this.validate(),editWin=getEditWin(window),inJCEdit=null!=editWin;"false"==parameterJsonObj.inJCEdit&&(inJCEdit=!1),result&&($.messager.progress({title:"正在处理,请稍等..."}),$("#dataForm").form("submit",{url:"../action/bizObjectBase!save.action",async:!1,onSubmit:function(){return!0},success:function success(data){$.messager.progress("close"),execProcessTask(data2);var dataObj=eval("("+data+")");if(dataObj.result){"new"==dealMark&&(keyValues=dataObj.attribute.keyValues,dealMark="update",document.getElementsByName("keyValues")[0].value=keyValues,document.getElementsByName("dealMark")[0].value=dealMark);try{saveDataAfter()}catch(e){}}if(inJCEdit){if(getEditWin(window).assetExist=!0,getEditWin(window).keyValues=dataObj.attribute.keyValues,null!=fn)return fn(dataObj)}else if($.messager.progress("close"),dataObj.result){var listviewWin=getListviewWin(window);null!=listviewWin&&listviewWin.refresh(),$.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.successMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}else $.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.errorMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}}))},getEditWin:function(e){var t=e;return"undefined"===typeof t.integrationEditorId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getEditWin(t.parent):null:t},validate:function(){return!0},saveDataBefore:function(){return!0}}}}}]);