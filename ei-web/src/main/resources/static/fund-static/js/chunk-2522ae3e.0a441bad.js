(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2522ae3e"],{25442:function(e,t,a){},"49fb":function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("a450"),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_0__),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("fc02"),core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1___default=__webpack_require__.n(core_js_modules_es6_regexp_split__WEBPACK_IMPORTED_MODULE_1__),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("e680"),core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2___default=__webpack_require__.n(core_js_modules_es6_number_constructor__WEBPACK_IMPORTED_MODULE_2__),axios__WEBPACK_IMPORTED_MODULE_3__=__webpack_require__("7f43"),axios__WEBPACK_IMPORTED_MODULE_3___default=__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_3__);__webpack_exports__["a"]={name:"Process",props:{allcommshow:{type:Number,default:1},validateFun:Function,contentPath:{type:String,default:"/ei-web/"},contextPath:{type:String,default:"/ei-web/"},extraData:{type:Object,default:function(){return{result:!0,extendParam:""}}}},data:function(){return{isShowBtn:!1,btnId:"",btnName:"",btnJson:"",taskProcessDescValue:"",submitCount:0,commandes:null,instId:"",taskId:"",formUri:"",formUriView:"",pshow:"",phid:"",theRequest:{},extendParam:"",json:{},websock:""}},watch:{taskId:function(e){var t=this;axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/getProcessCommandJSON?taskId="+this.taskId+"&AppSSOSessionID="+localStorage.getItem("AppSSOSessionID"),{taskId:this.taskId}).then((function(e){t.commandes=e.data})).catch((function(e){})),this.showAndHidForm(this.pshow,this.phid)}},mounted:function(){window.location;var e=this.$route.query;this.theRequest=e,this.instId=this.theRequest.instId,this.taskId=this.theRequest.taskId,this.formUri=this.theRequest.formUri,this.formUriView=this.theRequest.formUriView},destroyed:function(){this.websocketclose()},methods:{initWebSocket:function(){var e="ws://".concat(window.location.host,"/sys-web/websocket/").concat(sessionStorage.getItem("userId"),"000");this.websock=new WebSocket(e),this.websock.onmessage=this.websocketonmessage,this.websock.onopen=this.websocketonopen,this.websock.onerror=this.websocketonerror,this.websock.onclose=this.websocketclose},websocketonopen:function(){},websocketonerror:function(){this.initWebSocket()},websocketonmessage:function(e){console.log("接受到的消息："+e.data)},websocketsend:function(e){this.websock.send(e)},websocketclose:function(e){console.log("断开连接",e),1006===e.code&&this.initWebSocket()},showAndHidForm:function(e,t){if(null!=e&&""!==e)for(var a=e.split(","),s=0;s<a.length;s++)$("#"+a[s]).show();if(null!=t&&""!==t)for(var n=t.split(","),o=0;o<n.length;o++)$("#"+n[o]).attr("style","display:none").hide()},beforeCommandShow:function(){return!0},btnHandel:function(e){var t=e.fixParam_BeforeHandleJS,a=(e.type,"");this.json=e,console.log("undefined"!==typeof t&&null!=t&&""!=t,"fixParam_BeforeHandleJS"===e.id),"退回"===e.name||"不同意"===e.name?this.nextTask():"undefined"!==typeof t&&null!=t&&""!=t||"fixParam_BeforeHandleJS"===e.id?(this.$emit("action"),a="",this.extendParam=""):this.nextTask(),"undefined"!==typeof a&&null!=a&&""!=a||(a="")},nextTask:function(e){var t=this.json;this.doProcessTask("",t.name,t.id,t.type,t,this.extendParam)},doProcessTask:function(e,t,a,s,n,o){document.documentElement.scrollTop=0,this.processTask(t,a,s,n,o)},processTask:function(e,t,a,s,n){var o=this;this.confirmMessage("确认进行"+e+"操作？",(function(e){if(e){document.documentElement.scrollTop=0;var i=o.taskProcessDescValue;if(i.length>2e3)return void o.alertMessage("处理意见超长，最大长度为2000字符！");var r={instId:o.instId,taskId:o.taskId,commandId:t,commandType:a,processDesc:encodeURIComponent(o.taskProcessDescValue),formParam:n},c=!!s.fixParam_taskProcessDescFlag&&s.fixParam_taskProcessDescFlag,l=s.fixParam_taskProcessDescMessage?s.fixParam_taskProcessDescMessage:"";if("true"===c&&""===r.processDesc)return""===l&&(l="请填写处理意见，最大长度为2000字符！"),void o.alertMessage(l);if("rollBackTaskByExpression"===a){if(""===r.processDesc)return void o.alertMessage("请填写意见！")}else try{var d=saveDataMy();if(!d)return}catch(h){}if("modifyForm"===a){var u=s.fixParam_title;u&&""!==u||(u="修改");var p={toobarTitle:u},m=s.fixParam_url;return m&&""!==m||(m=o.formUri),m=appendParams(m,{submitBtnShow:"false"}),void o.getPageWin("layout",window).openWindow(800,600,p.toobarTitle,m,p,(function(){parent.refreshCenterRegion()}))}if("pending"===a){p={toobarTitle:"请选择转办人"},m=s.fixParam_url;if(m){var _=s.fixParam_width,f=s.fixParam_height;_||(_=800),f||(f=600),o.getPageWin("layout",window).openWindow(_,f,p.toobarTitle,m,p,(function(){var e=o.getPageWin("layout",window).invokeResult;e&&e.result&&(e.selectUserId&&(r["pendingUserId"]=e.selectUserId),o.execProcessTask(r))}))}else o.execProcessTask(r);return}if("rollBackTaskByTaskId"===a){p={toobarTitle:"请选择任务"},m="../jsps/process/listRollBackTaskList.jsp?taskId="+o.taskId+"&instId="+o.instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(800,600,p.toobarTitle,m,p,(function(){})),!1}if("rollBack"==a){p={toobarTitle:"请选择节点"},m="../jsps/process/listRollBackNodeList.jsp?taskId="+taskId+"&instId="+instId+"&commandId="+t+"&commandType="+a+"&processDesc="+encodeURIComponent(encodeURIComponent($("#taskProcessDesc").val()))+"&bizKey="+$("#keyValues").val();return openWindow(480,320,p.toobarTitle,m,p,(function(){})),!1}if("submit"!=a){u=s.fixParam_title;u&&""!=u||(u="修改");p={toobarTitle:u},m=s.fixParam_url;if(m){_=s.fixParam_width,f=s.fixParam_height;_||(_=800),f||(f=600),o.getPageWin("layout",window).openWindow(_,f,p.toobarTitle,m,p,(function(){var e=getPageWin("layout",window).invokeResult;if(e&&e.result){if(e.taskProcessDesc){var t=$("#taskProcessDesc").val();t=""==t?e.taskProcessDesc:t+","+e.taskProcessDesc,$("#taskProcessDesc").val(t)}o.execProcessTask(r)}}))}else o.execProcessTask(r);return}if(!o.validate())return void alertMessage("表单验证不通过,请重新填写");o.execProcessTask(r)}}))},confirmMessage:function(e,t){this.$confirm("".concat(e),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(t).catch((function(){}))},alertMessage:function(e,t){this.$confirm("".concat(e),"提示",{confirmButtonText:"确定",type:"warning"}).then((function(){t()})).catch((function(){}))},getPageWin:function(e,t){var a=this.getParentPageWin(e,t);return a},getParentPageWin:function(e,t){var a=t;return("undefined"===typeof a.pageId||a.pageId!=e)&&null!=a.parent?a.location.href!=a.parent.location.href?this.getPageWin(e,a.parent):null:a},closeLayUIOpen:function(){localStorage.setItem("closeKey",1),void 0!=parent.layer?parent.layer.close(parent.layer.getFrameIndex(window.name)):void 0!=parent.parent.layer?parent.parent.layer.close(parent.parent.layer.getFrameIndex(parent.name)):void 0!=parent.parent.parent.layer?parent.parent.parent.layer.close(parent.parent.parent.layer.getFrameIndex(parent.parent.name)):parent.parent.parent.parent.layer.close(parent.parent.parent.parent.layer.getFrameIndex(parent.parent.parent.name))},execProcessTask:function(e){var t=this,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:this.submitCount;a++;var s=e.instId;1===a&&axios__WEBPACK_IMPORTED_MODULE_3___default.a.get(this.contentPath+"workflowProcess/processTask?"+"instId=".concat(e.instId,"&taskId=").concat(e.taskId,"&commandId=").concat(e.commandId,"&commandType=").concat(e.commandType,"&processDesc=").concat(e.processDesc,"&formParam=").concat(JSON.stringify(e.formParam)),e).then((function(e){var a=e.data;a.result?t.alertMessage(a.successMessage,(function(){t.websocketsend("fixFlow,".concat(s));var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})):(t.submitCount=0,t.alertMessage(a.errorMessage,(function(){var e=t.getListviewWin(window);null==e?t.closeLayUIOpen():t.getListviewWin(window).closeWindow()})))}))},getListviewWin:function(e){var t=e;return"undefined"===typeof t.listviewId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getListviewWin(t.parent):null:t},taskProcessDescCheck:function(){var e="",t=0,a=$("#taskProcessDesc").val();return e=""==a?'{"result":false,"errorMsg":"请填写退回理由！"}':'{"result":true,"extendParam":'+t+"}",e},saveData2:function saveData2(fn,data2){try{var mark=this.saveDataBefore();if(!mark)return}catch(e){}var result=this.validate(),editWin=getEditWin(window),inJCEdit=null!=editWin;"false"==parameterJsonObj.inJCEdit&&(inJCEdit=!1),result&&($.messager.progress({title:"正在处理,请稍等..."}),$("#dataForm").form("submit",{url:"../action/bizObjectBase!save.action",async:!1,onSubmit:function(){return!0},success:function success(data){$.messager.progress("close"),execProcessTask(data2);var dataObj=eval("("+data+")");if(dataObj.result){"new"==dealMark&&(keyValues=dataObj.attribute.keyValues,dealMark="update",document.getElementsByName("keyValues")[0].value=keyValues,document.getElementsByName("dealMark")[0].value=dealMark);try{saveDataAfter()}catch(e){}}if(inJCEdit){if(getEditWin(window).assetExist=!0,getEditWin(window).keyValues=dataObj.attribute.keyValues,null!=fn)return fn(dataObj)}else if($.messager.progress("close"),dataObj.result){var listviewWin=getListviewWin(window);null!=listviewWin&&listviewWin.refresh(),$.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.successMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}else $.messager.defaults.ok="确定",$.messager.alert("提示",dataObj.errorMessage,"info",(function(){if(null!=fn)return fn(dataObj)}))}}))},getEditWin:function(e){var t=e;return"undefined"===typeof t.integrationEditorId&&null!=t.parent?t.location.href!=t.parent.location.href?this.getEditWin(t.parent):null:t},validate:function(){return!0},saveDataBefore:function(){return!0}}}},a552:function(e,t,a){"use strict";a("25442")},d68bb:function(e,t,a){"use strict";a("e677")},e677:function(e,t,a){},ec89:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{"padding-top":"20px"}},[e.dialogData.personName?a("formPage",{ref:"formPage",attrs:{"is-edit":e.isEdit,node:e.query.node,steps:e.query.steps},model:{value:e.dialogData,callback:function(t){e.dialogData=t},expression:"dialogData"}}):e._e(),e._v(" "),a("div",{attrs:{id:"myDiv"}},[e.isApproval?a("approval",{attrs:{"inst-id":e.query.instId,"frame-ind":e.query.frameInd}}):e._e()],1),e._v(" "),e.isApproval?a("process",{ref:"process",attrs:{"content-path":"/ei-web/"},on:{action:e.action}}):e._e()],1)},n=[],o=(a("1bc7"),a("a395")),i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"processTitle"},[a("div",{staticClass:"title"},[e._v("审批信息")]),e._v(" "),e.instId?a("iframe",{attrs:{src:e.src,width:"100%",frameborder:"0",scrolling:"no"}}):e._e()])},r=[],c={name:"ApprovalInfo",props:{instId:{type:String,default:null},frameInd:{type:String,default:""}},computed:{src:function(){return"/sysweb/action/showListview.action?listViewId=fixflowProcessInfosListView&instId=".concat(this.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.frameInd)}}},l=c,d=(a("d68bb"),a("4ac2")),u=Object(d["a"])(l,i,r,!1,null,"df1b9266",null),p=u.exports,m=a("fec6"),_=a("fa8a"),f={name:"TemplateProcess",components:{formPage:o["default"],approval:p,process:m["a"]},data:function(){return{query:{},dialogData:{},isApproval:!0,isEdit:!1}},created:function(){this.query=this.$route.query,console.log(this.query),this.isApproval="view"!==this.query.node,"view"===this.query.steps&&(this.isApproval=!1),this.isEdit="edit"===this.query.steps,this.initPage()},methods:{initPage:function(){var e=this;Object(_["Lb"])({iraId:this.query.iraId}).then((function(t){"成功"===t.msg&&(e.dialogData=t.data,e.dialogData.inveName=e.dialogData.regName,e.$set(e.dialogData,"person",t.data.list.map((function(e){return e.commUserId})).join(",")),e.$set(e.dialogData,"personName",t.data.list.map((function(e){return e.commUserName})).join(",")),console.log(e.dialogData,"111111"))}))},action:function(){var e=this;this.dialogData.list.forEach((function(e){delete e.createDt,delete e.updateDt})),this.$refs.formPage.checkValue()&&(this.dialogData.isStartFlow=!0,this.dialogData.processStatus="1",Object(_["Mb"])(this.dialogData).then((function(t){e.$refs.process.nextTask()})))}}},h=f,v=Object(d["a"])(h,s,n,!1,null,"1217ac76",null);t["default"]=v.exports},fec6:function(e,t,a){"use strict";var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.instId,expression:"instId"}],attrs:{id:"instId",type:"hidden"},domProps:{value:e.instId},on:{input:function(t){t.target.composing||(e.instId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.taskId,expression:"taskId"}],attrs:{id:"taskId",type:"hidden"},domProps:{value:e.taskId},on:{input:function(t){t.target.composing||(e.taskId=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUri,expression:"formUri"}],attrs:{id:"formUri",type:"hidden"},domProps:{value:e.formUri},on:{input:function(t){t.target.composing||(e.formUri=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.formUriView,expression:"formUriView"}],attrs:{id:"formUriView",type:"hidden"},domProps:{value:e.formUriView},on:{input:function(t){t.target.composing||(e.formUriView=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.pshow,expression:"pshow"}],attrs:{id:"pshow",type:"hidden"},domProps:{value:e.pshow},on:{input:function(t){t.target.composing||(e.pshow=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.phid,expression:"phid"}],attrs:{id:"phid",type:"hidden"},domProps:{value:e.phid},on:{input:function(t){t.target.composing||(e.phid=t.target.value)}}}),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.contentPath,expression:"contentPath"}],attrs:{id:"contentPath",type:"hidden"},domProps:{value:e.contentPath},on:{input:function(t){t.target.composing||(e.contentPath=t.target.value)}}}),e._v(" "),a("table",{staticClass:"addtable",staticStyle:{"margin-top":"0px","margin-bottom":"10px"},attrs:{id:"formToolBarTable",width:"100%",border:"0",cellspacing:"0",cellpadding:"0"}},[e._m(0),e._v(" "),a("tr",{staticStyle:{border:"1px solid #D9D9D9"}},[a("td",{staticClass:"td_name",staticStyle:{width:"20%",padding:"0px","font-size":"13px","text-align":"center","vertical-align":"middle",color:"#997528",background:"#eae3d8"}},[e._v("处理意见")]),e._v(" "),a("td",{staticClass:"td_value",staticStyle:{width:"82%",padding:"6px"},attrs:{colspan:"3"}},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:e.taskProcessDescValue,expression:"taskProcessDescValue"}],staticStyle:{width:"100%",height:"70px","border-radius":"4px"},attrs:{id:"taskProcessDesc",name:"taskProcessDesc"},domProps:{value:e.taskProcessDescValue},on:{input:function(t){t.target.composing||(e.taskProcessDescValue=t.target.value)}}})])]),e._v(" "),a("tr",[a("td",{staticClass:"td_name",staticStyle:{width:"18%"}}),e._v(" "),a("td",{staticClass:"td_value drawablel",staticStyle:{width:"82%"},attrs:{id:"processToolBar",colspan:"3"}},[1===e.allcommshow?e._l(e.commandes,(function(t){return a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name))])})):e._e(),e._v(" "),0===!e.allcommshow?e._l(e.commandes,(function(t){return t.fixParam_BeforeShowJS?a("button",{staticClass:"el-button el-button--primary el-button--small",attrs:{id:t.id,type:"primary"},on:{click:function(a){return e.btnHandel(t)}}},[e._v(e._s(t.name)+"\n          ")]):e._e()})):e._e()],2)])])])},n=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("tr",[a("td",{attrs:{colspan:"4"}},[a("div",{staticClass:"panel-header panel-title",staticStyle:{"border-left":"0px","border-right":"0px","border-top":"0px","margin-top":"1px","margin-bottom":"1px"}},[e._v("流程审批\n        ")])])])}],o=a("49fb"),i=o["a"],r=(a("a552"),a("4ac2")),c=Object(r["a"])(i,s,n,!1,null,"57dba195",null);t["a"]=c.exports}}]);