(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4a2c76c2","chunk-2d0deb05"],{"5f0c":function(e,t,i){"use strict";var a="http://172.29.201.151:8080",n={getEntBaseInfo:a+"/ei-web/entBaseInfo/entInfoDetail",platFuncPermissionList:a+"/ei-web/platFuncPermissionList",platInfoDetail:a+"/ei-web/platformInfo/platInfoDetail",roleList:a+"/ei-web/roleList",assInfoList:a+"/ei-web/assInfoList",ledgerMagList:a+"/ei-web/ledgerMagList",paymentRequestList:a+"/ei-web/paymentRequestList",founderOaApplyInfoList:a+"/ei-web/founderOaApplyInfoList",getYear:a+"/ei-web/getYear",quarterReportJJ:a+"/ei-web/quarterReportJJ",quarterReportQY:a+"/ei-web/quarterReportQY",quarterReportRS:a+"/ei-web/quarterReportRS",projAppInfoOthList:a+"/ei-web/projAppInfoOthList",EntInvestmentList:a+"/ei-web/EntInvestmentList",fundInfoList:a+"/ei-web/fundInfoList",getProjListByFundId:a+"/ei-web/getProjListByFundId",QuotaConfList:a+"/ei-web/QuotaConfList",QuotaConfDetail:a+"/ei-web/QuotaConfDetail",updateTableDataInfo:a+"/ei-web/updateTableDataInfo",entLogo:a+"/ei-web/entBaseInfo/entLogo",getDeleteTableDataById:a+"/ei-web/deleteTableDataById",deleteTableDataByIdWL:a+"/ei-web/deleteTableDataByIdWL",otherThreeMeetingList:a+"/ei-web/otherThreeMeetingList",getLoginInfo:a+"/ei-web/getLoginInfo",getCodeByParentId:a+"/ei-web/commonTCode/getCodeByParentId",projectFuncPermissionList:a+"/ei-web/projectFuncPermissionList"};t["a"]=n},6560:function(e,t,i){},"875e":function(e,t,i){"use strict";i.d(t,"c",(function(){return a})),i.d(t,"a",(function(){return o})),i.d(t,"b",(function(){return l}));i("a450"),i("fc02"),i("8dee"),i("3269");function a(e,t,i,a,o){var l=top.document.documentElement.clientHeight-50,c=n();"IE10"==c&&(l=top.document.documentElement.clientHeight-50),""!=a&&a>l&&(a=l),layer.open({type:2,title:e,shadeClose:!1,offset:"auto",shade:[.8,"#393D49"],area:[i,a],tips:[1,"#c00"],scrollbar:!1,content:t,end:o})}function n(){var e=navigator.userAgent,t=e.indexOf("Opera")>-1,i=e.indexOf("compatible")>-1&&e.indexOf("MSIE")>-1&&!t,a=e.indexOf("Windows NT 6.1; Trident/7.0;")>-1&&!i,n=e.indexOf("Firefox")>-1,o=e.indexOf("Safari")>-1&&-1==e.indexOf("Chrome"),l=e.indexOf("Chrome")>-1&&e.indexOf("Safari")>-1;if(i){var c=new RegExp("MSIE (\\d+\\.\\d+);");c.test(e);var s=parseFloat(RegExp["$1"]);return 7==s?"IE7":8==s?"IE8":9==s?"IE9":10==s?"IE10":11==s?"IE11":"0"}return n?"FF":t?"Opera":o?"Safari":l?"Chrome":a?"Edge":void 0}function o(e,t){var i=e;return-1==e.indexOf("?")?i+="?":i+="&",$.isPlainObject(t)?i+=$.param(t):i+=t,i}function l(e){var t=decodeURIComponent((new RegExp("[?|&]"+e+"=([^&;]+?)(&|#|;|$)").exec(location.href)||[,""])[1].replace(/\+/g,"%20"))||null;return t?t.indexOf("/")>-1?t.split("/")[0]:t:""}},c1f2:function(e,t,i){"use strict";i("6560")},e8cd:function(e,t,i){"use strict";i.r(t);var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"fund-contanier tableCommon"},[i("ul",{staticClass:"editNav"},[i("li",{on:{click:e.add_new}},[e._m(0)]),e._v(" "),i("li",{class:1==e.checkLength?"red":"",on:{click:e.edit_click}},[e._m(1)]),e._v(" "),i("li",{class:1==e.checkLength?"red":"",on:{click:e.printer_click}},[e._m(2)]),e._v(" "),i("li",{class:0!==e.multipleSelection.length?"red":"",on:{click:e.delete_click}},[e._m(3)])]),e._v(" "),i("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{align:"center",type:"selection",width:"55"}}),e._v(" "),i("el-table-column",{attrs:{label:"No",align:"center",type:"index",width:"55"}}),e._v(" "),i("el-table-column",{attrs:{label:"项目名称","header-align":"center",align:"left",width:""},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",{staticStyle:{"margin-left":"10px",color:"blue","text-decoration":"underline",cursor:"pointer",width:"100%",display:"inline-block"},on:{click:function(i){return e.cellClick(t.row)}}},[e._v(e._s(t.row.projFullName))])]}}])}),e._v(" "),i("el-table-column",{attrs:{prop:"groupName","header-align":"center",align:"left",width:"240",label:"所属平台"}}),e._v(" "),i("el-table-column",{attrs:{prop:"meetingDate","header-align":"center",align:"center",width:"160",label:"会议召开时间"}}),e._v(" "),i("el-table-column",{attrs:{prop:"status","header-align":"center",align:"center",width:"100",label:"状态"}})],1),e._v(" "),i("el-dialog",{attrs:{title:"提示",visible:e.dialogVisible,width:"30%","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("span",[e._v("是否删除")]),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:e.delete_click_ack}},[e._v("确 定")])],1)]),e._v(" "),i("div",{staticClass:"paginationNav paginationCommon"},[i("div",{staticClass:"title"},[e._v("共 查询到 "+e._s(e.recordsTotal)+" 条记录 共 "+e._s(e.totalPage)+" 页")]),e._v(" "),i("div",{staticClass:"first-page",on:{click:e.indexBtn}},[e._v("首页")]),e._v(" "),i("el-pagination",{attrs:{"current-page":e.currentPage,total:e.recordsTotal,"page-size":e.pageSize,layout:"prev, pager, next",background:"","prev-text":"上一页","next-text":"下一页"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"prev-click":e.prevClick,"next-click":e.nextClick}}),e._v(" "),i("div",{staticClass:"end-page",on:{click:e.backBtn}},[e._v("末页")])],1)],1)},n=[function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("a",{attrs:{href:"javascript:;"}},[i("i",{staticClass:"el-icon-circle-plus-outline"}),e._v("新增")])},function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("a",{attrs:{href:"javascript:;"}},[i("i",{staticClass:"el-icon-edit"}),e._v("编辑")])},function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("a",{attrs:{href:"javascript:;"}},[i("i",{staticClass:"el-icon-printer"}),e._v("打印")])},function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("a",{attrs:{href:"javascript:;"}},[i("i",{staticClass:"el-icon-circle-close-outline"}),e._v("删除")])}],o=i("5f0c"),l=i("875e"),c={props:["pkId","groupId"],data:function(){return{tableData:[],currentPage:1,pageSize:5,totalPage:0,shouYePage:1,recordsTotal:null,checkLength:0,multipleSelection:[],dialogVisible:!1}},created:function(){this.getTableData()},methods:{getTableData:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5,t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1,i=this,a=(new Date).getTime();this.$axios({url:"".concat(o["a"].otherThreeMeetingList,"?pkId=").concat(this.pkId,"&groupId=").concat(this.groupId,"&pageSize=").concat(e,"&currPage=").concat(t,"&t=").concat(a),method:"get"}).then((function(e){200===e.status&&(i.tableData=e.data.data,i.recordsTotal=e.data.totalCount,i.totalPage=e.data.totalPage)}))},indexBtn:function(){this.currentPage=this.shouYePage,this.getTableData(this.pageSize,this.currentPage)},backBtn:function(){this.currentPage=this.totalPage,this.getTableData(this.pageSize,this.currentPage)},prevClick:function(e){this.currentPage=e,this.getTableData(this.pageSize,this.currentPage)},nextClick:function(e){this.currentPage=e,this.getTableData(this.pageSize,this.currentPage)},handleSizeChange:function(e){this.pageSize=e},handleCurrentChange:function(e){this.getTableData(this.pageSize,e),this.currentPage=e},handleSelectionChange:function(e){1===e.length?this.checkLength=1:this.checkLength=0,this.multipleSelection=e},add_new:function(){var e=this,t={toobarTitle:"新增",openMethod:"2"},i="/eiweb",a="YHKG_DS_EI",n=Object(l["a"])(i+"/action/bizObjectBase.action",{keyValues:"",tableName:"OTHER_THREE_MEETING",dealMark:"new",sequenceName:"SEQ_OTHER_THREE_MEETING",springJndiId:a,resultJsp:"../WEB-INF/views/oa/threeMeeting/addThreeMeeting.jsp",className:"com.founder.ei.bizobject.OtherThreeMeetingObj",openMethod:t.openMethod});Object(l["c"])(t.toobarTitle,n,"1000px","600px",(function(){e.getTableData()}))},edit_click:function(){var e=this;if(0==this.checkLength)layer.msg("请选中一项",{icon:6});else if("草稿"==this.multipleSelection[0].status){this.multipleSelection[0].processInstId;var t=this.multipleSelection[0].meetingId,i={toobarTitle:"编辑",openMethod:"2"},a="/eiweb",n="YHKG_DS_EI",o="";o=Object(l["a"])(a+"/action/bizObjectBase.action",{keyValues:t,tableName:"OTHER_THREE_MEETING",dealMark:"update",sequenceName:"",springJndiId:n,resultJsp:"../WEB-INF/views/oa/threeMeeting/addThreeMeeting.jsp",className:"com.founder.ei.bizobject.OtherThreeMeetingObj",openMethod:i.openMethod}),Object(l["c"])(i.toobarTitle,o,"1000px","600px",(function(){e.getTableData()}))}else layer.msg("只有[草稿]状态的申请才能编辑!",{icon:6})},printer_click:function(){if(0==this.checkLength)layer.msg("请选中一项",{icon:6});else{var e="/eiweb",t="YHKG_DS_EI",i=this.multipleSelection[0].meetingId,a=Object(l["a"])(e+"/action/bizObjectBase.action",{keyValues:i,tableName:"OTHER_THREE_MEETING",dealMark:"view",sequenceName:"",springJndiId:t,isPrint:"Y",resultJsp:"../WEB-INF/views/oa/threeMeeting/printThreeMeeting.jsp",className:"com.founder.ei.bizobject.OtherThreeMeetingObj"});window.open(a)}},recall_click:function(){var e=this,t=this,i="/oaweb";if(0==this.checkLength)layer.msg("请选择需要撤回的任务",{icon:6});else{this.multipleSelection[0].processInstId;var a=this.multipleSelection[0].applyStatus,n=this.multipleSelection[0].applyNo;if(1==a){var o=this.multipleSelection[0].instanceModel.taskinstanceId,l="".concat(i,"/applyContract/execProcessRollBackTask.action?taskId=").concat(o,"&bizKey=").concat(n);this.$confirm("此操作将永久撤回该文件, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.$axios({method:"post",url:l,data:{}}).then((function(e){200===e.status&&(void 0===e.data.resultMsg?(layer.msg("撤回成功",{icon:6}),t.getTableData()):layer.msg("".concat(e.data.resultMsg),{icon:6}))}))})).catch((function(){e.$message({offset:150,type:"info",message:"已取消删除"})}))}else layer.msg("您申请的单据已经有签署信息，撤回后重新提交，则签署人需要重新签署！",{icon:6})}},delete_click:function(){var e=!0;if(0!=this.multipleSelection.length){for(var t=0;t<this.multipleSelection.length;t++)-1==this.multipleSelection[t].status.indexOf("草稿")&&(layer.msg("只能草稿下才能删除",{icon:6}),e=!1);e&&(this.dialogVisible=!0)}else layer.msg("请选中一条数据",{icon:6})},delete_click_ack:function(){var e=this,t=this,i=this.multipleSelection,a=[],n="";for(var l in i)a.push(i[l].meetingId);n=a.join(",");var c="OTHER_THREE_MEETING",s="MEETING_ID",r="".concat(o["a"].getDeleteTableDataById,"?tableName=").concat(c,"&keyValue=").concat(n,"&keyName=").concat(s);this.$axios({url:r,method:"PUT"}).then((function(i){"0"===i.data.status&&(layer.msg("删除成功",{icon:6}),e.dialogVisible=!1,t.getTableData())})).catch((function(e){}))},export_click:function(){this.$axios({method:"get",url:"".concat(o["a"].getLoginInfo)}).then((function(e){if(200===e.status){var t=e.data.data.appUserModel;if(t){var i=t.id,a=t.orgid,n=t.deptid,o="/oaweb",c=Object(l["a"])(o+"/action/exportListview.action",{listViewFileName:"contractInfoManager",listViewId:"contractInfo",fromKeyValues:"",fromKeyValuesJson:"",fromFilter:"",loginId:i,orgId:a,deptId:n,queryFilter:"",permissionId:"90701"});window.location.href=c}}}))},handleClose:function(e){},cellClick:function(e,t,i,a){var n={toobarTitle:"详情",openMethod:"2"},o="/eiweb",c="YHKG_DS_EI",s="",r="",d="",u="",h="",p=e.processInstId,g=e.meetingId,f=e.instanceModel;p&&null!=p&&null!=f?(s=e.instanceModel.processdefinitionId,r=e.instanceModel.taskinstanceId,d=e.instanceModel.formuri,u=e.instanceModel.formuriview,h=Object(l["a"])(o+"/editor/editor.jsp",{dealMark:"view",showToolBar:!1,xmlFileName:"workflowEditorConfig",groupId:2,defId:s,taskId:r,instId:p,bizKey:g,formUri:encodeURIComponent(d),formUriView:encodeURIComponent(u)})):h=Object(l["a"])(o+"/action/bizObjectBase.action",{keyValues:g,tableName:"OTHER_THREE_MEETING",dealMark:"view",sequenceName:"",springJndiId:c,resultJsp:"../WEB-INF/views/oa/threeMeeting/viewThreeMeeting.jsp",className:"com.founder.ei.bizobject.OtherThreeMeetingObj",openMethod:n.openMethod}),Object(l["c"])(n.toobarTitle,h,"1000px","600px",(function(){}))}}},s=c,r=(i("c1f2"),i("4ac2")),d=Object(r["a"])(s,a,n,!1,null,null,null);t["default"]=d.exports}}]);