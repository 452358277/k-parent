(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-913a51f6","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"1d6f":function(e,t,s){"use strict";s.r(t);var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("div",{staticClass:"btnBox",staticStyle:{"margin-top":"20px"}},[e.buttonList.includes("602041001")?s("button",{on:{click:e.addInfo}},[s("i",{staticClass:"iconfont iconjiahao"}),e._v("\n      新增\n    ")]):e._e(),e._v(" "),e.buttonList.includes("602041002")?s("button",{on:{click:e.editInfo}},[s("i",{staticClass:"iconfont iconbianji"}),e._v("\n      编辑\n    ")]):e._e(),e._v(" "),e.buttonList.includes("602041003")?s("button",{on:{click:e.delInfo}},[s("i",{staticClass:"iconfont iconbianji"}),e._v("\n      删除\n    ")]):e._e()]),e._v(" "),s("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""},on:{"selection-change":e.handleSelectionChange}},[s("el-table-column",{attrs:{type:"selection",align:"center",width:"55"}}),e._v(" "),s("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),e._v(" "),s("el-table-column",{attrs:{align:"left","min-width":"200",prop:"meetingTitle",label:"事项标题"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"===t.row.status?s("div",[e._v(e._s(t.row.meetingTitle))]):e._e(),e._v(" "),"0"!==t.row.status?s("el-link",{attrs:{type:"primary"},on:{click:function(s){return e.lookDetail(t.row)}}},[e._v(e._s(t.row.meetingTitle))]):e._e()]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"left","min-width":"100",prop:"meetingTypeName",label:"事项类别"}}),e._v(" "),s("el-table-column",{attrs:{align:"center",width:"110",prop:"hapDate",label:"发生日期"}}),e._v(" "),s("el-table-column",{attrs:{align:"left","min-width":"200",prop:"attaFile",label:"附件"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("div",[t.row.attaFile?s("upLoad",{attrs:{"context-path":e.url.upApi,"hidden-upload":!0},model:{value:t.row.attaFile,callback:function(s){e.$set(t.row,"attaFile",s)},expression:"scope.row.attaFile"}}):e._e()],1)]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"left",width:"110",prop:"statusName",label:"状态"}})],1),e._v(" "),s("el-pagination",{attrs:{"current-page":e.tableData.currPage,"page-sizes":[5,10,15,20],"page-size":e.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),s("el-dialog",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.submitLoading,expression:"submitLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{title:e.modalTitle,visible:e.dialogVisible,"element-loading-background":"rgba(0, 0, 0, 0)",width:"90%"},on:{"update:visible":function(t){e.dialogVisible=t},close:e.dialogClose}},[e.lookStatus?e._e():s("div",[s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          事项标题\n          "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[s("el-input",{attrs:{size:"small",maxlength:"20",readonly:"edit"===e.status},model:{value:e.dialogInfo.meetingTitle,callback:function(t){e.$set(e.dialogInfo,"meetingTitle",t)},expression:"dialogInfo.meetingTitle"}})],1),e._v(" "),s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          事项类别\n          "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[s("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},on:{change:e.selectContract},model:{value:e.dialogInfo.meetingType,callback:function(t){e.$set(e.dialogInfo,"meetingType",t)},expression:"dialogInfo.meetingType"}},e._l(e.contractList,(function(e){return s("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          发生日期\n        ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[s("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.dialogInfo.hapDate,callback:function(t){e.$set(e.dialogInfo,"hapDate",t)},expression:"dialogInfo.hapDate"}})],1),e._v(" "),s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          会议人员\n          "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n          "+e._s(e.dialogInfo.userName)+" "),s("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){e.choicePersonVisible=!0}}},[e._v("选择人员")])],1)],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          事项分类\n          "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[s("el-radio-group",{model:{value:e.dialogInfo.eventType,callback:function(t){e.$set(e.dialogInfo,"eventType",t)},expression:"dialogInfo.eventType"}},e._l(e.eventTypeList,(function(t){return s("el-radio",{key:t.value,attrs:{label:t.value}},[e._v(e._s(t.label))])})),1)],1)],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          事项内容\n          "),s("span",{staticClass:"mustIn"})]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[s("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:e.dialogInfo.meetSubject,callback:function(t){e.$set(e.dialogInfo,"meetSubject",t)},expression:"dialogInfo.meetSubject"}})],1)],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          处理方式\n        ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[s("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:e.dialogInfo.dealWay,callback:function(t){e.$set(e.dialogInfo,"dealWay",t)},expression:"dialogInfo.dealWay"}})],1)],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n          附件\n        ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[e.dialogVisible?s("upLoad",{attrs:{"context-path":e.url.upApi},model:{value:e.dialogInfo.attaFile,callback:function(t){e.$set(e.dialogInfo,"attaFile",t)},expression:"dialogInfo.attaFile"}}):e._e()],1)],1)],1),e._v(" "),s("div",{staticStyle:{padding:"0px 20px"}},[e.lookStatus?s("el-tabs",{on:{"tab-click":e.handleClick},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"数据信息",name:"first"}},[s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              事项标题\n              "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n              "+e._s(e.dialogInfo.meetingTitle)+"\n            ")]),e._v(" "),s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              事项类别\n              "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n              "+e._s(e.dialogInfo.meetingTypeName)+"\n            ")])],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              发生日期\n            ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[s("div",[e._v(e._s(e.dialogInfo.hapDate))])]),e._v(" "),s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              会议人员\n              "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n              "+e._s(e.dialogInfo.userName)+"\n            ")])],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              事项分类\n              "),s("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n              "+e._s(e.dialogInfo.eventTypeName)+"\n            ")])],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              事项内容\n              "),s("span",{staticClass:"mustIn"})]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n              "+e._s(e.dialogInfo.meetSubject)+"\n            ")])],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              处理方式\n            ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n              "+e._s(e.dialogInfo.dealWay)+"\n            ")])],1),e._v(" "),s("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[s("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n              附件\n            ")]),e._v(" "),s("el-col",{staticClass:"col-content",attrs:{span:20}},[e.dialogVisible?s("upLoad",{attrs:{"context-path":e.url.upApi,"hidden-upload":!0},model:{value:e.dialogInfo.attaFile,callback:function(t){e.$set(e.dialogInfo,"attaFile",t)},expression:"dialogInfo.attaFile"}}):e._e()],1)],1)],1),e._v(" "),s("el-tab-pane",{attrs:{label:"流程图",name:"second"}},[s("div",[s("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:e.liuchengUrl,frameborder:"0"}})])]),e._v(" "),s("el-tab-pane",{attrs:{label:"处理过程",name:"third"}},[s("div",[s("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:e.processUrl,frameborder:"0"}})])])],1):e._e()],1),e._v(" "),e.lookStatus?e._e():s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),s("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){return e.submitDialogInfo("save")}}},[e._v("保 存")]),e._v(" "),s("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){return e.submitDialogInfo("submit")}}},[e._v("提 交")])],1),e._v(" "),e.lookStatus?s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{size:"small"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("关 闭")])],1):e._e()]),e._v(" "),s("el-dialog",{attrs:{title:"人员选择",visible:e.choicePersonVisible,width:"70%"},on:{"update:visible":function(t){e.choicePersonVisible=t},close:e.choicePersonClose}},[s("choicePerson",{attrs:{"context-path":e.url.upApi},on:{"update-value":e.surePerson},model:{value:e.userId,callback:function(t){e.userId=t},expression:"userId"}})],1)],1)},i=[],n=(s("aa18"),s("982e"),s("fc02"),s("a450"),s("1bc7"),s("7562")),o=s("2c2d"),l=s("fc12"),r=s("e19c"),c={name:"Index",components:{upLoad:o["a"],choicePerson:l["a"]},data:function(){return{tableData:{data:[],pageSize:5,currPage:1,totalCount:10},modalTitle:"",dialogVisible:!1,dialogInfo:{},contractList:[],currencyList:[],selectList:[],choicePersonVisible:!1,userId:"",userTree:[],url:r["a"],submitLoading:!1,eventTypeList:[],status:"edit",liuchengUrl:"",processUrl:"",lookStatus:!1,activeName:"first",buttonList:[]}},mounted:function(){this.getParentId(1017,"contractList"),this.getParentId(11016,"eventTypeList"),this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],this.getTableList(),this.getUserTree()},methods:{setButtonList:function(){var e=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(t){"投后事项管理"===t.name&&t.buttons.forEach((function(t){"602041001"===t.code&&e.buttonList.push(t.code),"602041002"===t.code&&e.buttonList.push(t.code),"602041003"===t.code&&e.buttonList.push(t.code)}))}))},getUserTree:function(){var e=this;n["a"].userTreeList().then((function(t){e.userTree[0]=t.data.data.model}))},getParentId:function(e,t){var s=this,a=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var i=[];if(a){var o=a.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){o.includes(e.value)&&i.push(e)}))}else i=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=i}else n["a"].getCodeByParentId({parentId:e}).then((function(i){var n=[];if(a){var o=a.split(",");i.data.data.options.forEach((function(e){o.includes(e.value)&&n.push(e)}))}else n=i.data.data.options;s[t]=n,sessionStorage.setItem("code".concat(e),JSON.stringify(i.data.data.options))}))},getTableList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5,s=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1;n["a"].selectFpThreemeetingInfo({pageSize:t,currPage:s,projId:this.$route.params.projId}).then((function(t){e.tableData=t.data}))},addInfo:function(){this.modalTitle="新增投后事项管理",this.dialogVisible=!0,this.dialogInfo.pkId="",this.status="add",this.getUserTree()},editInfo:function(){var e=this;this.modalTitle="编辑投后事项管理",1!==this.selectList.length?this.$message({offset:150,type:"warning",message:"请选择最少一条数据"}):(this.status="edit","0"===this.selectList[0].status?(this.dialogVisible=!0,this.dialogInfo=this.selectList[0],this.userId=this.dialogInfo.projMemberModel.map((function(e){return e.memberId})).join(","),this.dialogInfo.userName=this.dialogInfo.projMemberModel.map((function(e){return e.memberName})).join(","),n["a"].userTreeList().then((function(t){return e.userTree[0]=t.data.data.model,e.userTree})).then((function(t){var s=e.dialogInfo.projMemberModel.map((function(e){return e.memberId}));t.forEach((function(e){s.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){s.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){s.includes(e.userId)||(e.disabled=!0)}))}))})),e.userTree=t}))):this.$message({offset:150,type:"warning",message:"审核中的数据无法编辑"}))},delInfo:function(){var e=this;if(0===this.selectList.length)this.$message({offset:150,type:"warning",message:"请至少选择一条数据"});else{var t=[];if(this.selectList.forEach((function(s){"0"===s.status?t.push(s.pkId):e.$message({offset:150,type:"warning",message:"审核中的数据无法删除"})})),t.length===this.selectList.length){var s=this.selectList.map((function(e){return e.pkId}));this.$confirm("确定要删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){n["a"].deleteFpThreemeetingInfo({pkId:s.join(",")}).then((function(t){"0"===t.data.status&&(e.$message({offset:150,type:"success",message:"成功删除"}),e.getTableList(e.tableData.pageSize,1))}))})).catch((function(){}))}}},handleSizeChange:function(e){this.getTableList(e,1)},handleCurrentChange:function(e){this.getTableList(this.tableData.pageSize,e)},handleSelectionChange:function(e){this.selectList=e},dialogClose:function(){this.dialogInfo={},this.lookStatus=!1,this.activeName="first"},submitDialogInfo:function(e){var t=this;if("submit"===e){if(""===this.dialogInfo.meetingTitle||void 0===this.dialogInfo.meetingTitle||null===this.dialogInfo.meetingTitle)return void this.$message({offset:150,type:"warning",message:"请填写事项标题"});if(""===this.dialogInfo.meetingType||void 0===this.dialogInfo.meetingType||null===this.dialogInfo.meetingType)return void this.$message({offset:150,type:"warning",message:"请选择事项类别"});if(""===this.dialogInfo.eventType||void 0===this.dialogInfo.eventType||null===this.dialogInfo.eventType)return void this.$message({offset:150,type:"warning",message:"请选择事项分类"})}else if(""===this.dialogInfo.meetingTitle||void 0===this.dialogInfo.meetingTitle||null===this.dialogInfo.meetingTitle)return void this.$message({offset:150,type:"warning",message:"请填写事项标题"});if(this.dialogInfo.projId=this.$route.params.projId,this.dialogInfo.userId=this.userId,null!==this.dialogInfo.userName&&void 0!==this.dialogInfo.userName&&""!==this.dialogInfo.userName){this.dialogInfo.userNames=this.dialogInfo.userName.split(","),null!==this.dialogInfo.userId&&void 0!==this.dialogInfo.userId&&""!==this.dialogInfo.userId&&(this.dialogInfo.userIds=this.dialogInfo.userId.split(","));var s=[];this.dialogInfo.userNames.forEach((function(e,a){s.push({memberId:t.dialogInfo.userIds[a],memberName:t.dialogInfo.userNames[a]})})),this.dialogInfo.projMemberModel=s,this.dialogInfo.tag="save"===e?"1":"0",this.dialogInfo.carrier=this.$route.params.projId,this.dialogInfo.pkId?(this.submitLoading=!0,n["a"].updateFpThreemeetingInfo(this.dialogInfo).then((function(s){"0"===s.data.status&&"成功"===s.data.msg?(t.dialogVisible=!1,t.$message({offset:150,type:"success",message:"save"===e?"编辑成功":"流程发起成功"}),t.submitLoading=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage)):(t.$message({offset:150,type:"warning",message:s.data.msg}),t.submitLoading=!1)}))):(this.submitLoading=!0,n["a"].addFpThreemeetingInfo(this.dialogInfo).then((function(s){"0"===s.data.status&&"成功"===s.data.msg?(t.dialogVisible=!1,t.$message({offset:150,type:"success",message:"save"===e?"新增成功":"流程发起成功"}),t.submitLoading=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage)):(t.$message({offset:150,type:"warning",message:s.data.msg}),t.submitLoading=!1)})))}else this.$message({offset:150,type:"warning",message:"请选择会议人员"})},selectContract:function(e){var t=this;n["a"].numberDatails({projId:this.$route.params.projId,userType:e}).then((function(e){var s=e.data.data.map((function(e){return e.userId}));t.dialogInfo.userName=e.data.data.map((function(e){return e.userName})).join(","),t.userId=s.join(",")}))},choicePersonClose:function(){},cancelPerson:function(){this.choicePersonVisible=!1},surePerson:function(e,t){this.choicePersonVisible=!1,this.dialogInfo.userName=t.join(","),this.dialogInfo.userId=e},handleClick:function(){},lookDetail:function(e){var t=this;this.dialogInfo=e,this.modalTitle="查看投后事项",this.userId=this.dialogInfo.projMemberModel.map((function(e){return e.memberId})).join(","),this.dialogInfo.userName=this.dialogInfo.projMemberModel.map((function(e){return e.memberName})).join(","),n["a"].userTreeList().then((function(e){return t.userTree[0]=e.data.data.model,t.userTree})).then((function(e){var s=t.dialogInfo.projMemberModel.map((function(e){return e.memberId}));e.forEach((function(e){s.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){s.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){s.includes(e.userId)||(e.disabled=!0)}))}))})),t.userTree=e,t.lookStatus=!0,t.dialogVisible=!0,n["a"].getDefIdProcessinstanceId({processId:t.dialogInfo.processInstId}).then((function(e){t.liuchengUrl="/sysweb/action/workflowProcess!flowGraphics.action?defId=".concat(e.data,"&instId=").concat(t.dialogInfo.processInstId,"&taskId=").concat(t.dialogInfo.taskId,"&frameInd=2"),t.processUrl="/sysweb/action/showListview.action?listViewId=fixflowProcessInfosListView&instId=".concat(t.dialogInfo.processInstId,"&listViewFileName=fixflowProcessInfosListView&frameInd=2")}))}))}}},u=c,d=(s("f2f2"),s("4ac2")),f=Object(d["a"])(u,a,i,!1,null,"4cb9df92",null);t["default"]=f.exports},"2c2d":function(e,t,s){"use strict";var a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"upload-box"},[e.hiddenUpload?s("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return s("h3",{key:t.uid},[s("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():s("div",{staticClass:"el-upload-box"},[s("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[s("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?s("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},i=[],n=(s("fc02"),s("a450"),s("e680"),s("7f43")),o=s.n(n),l={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,s=e||this.value;""!=s&&null!=s||(s="-1");var a=this.contextPath+"attachController/getFileList?fieldToken="+s;o()({url:a,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var s=e.name.substring(e.name.lastIndexOf(".")+1),a="rp"===s,i="vsd"===s;(a||i)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var s=this;o()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(s.$emit("update-value",""),s.fileLists=[],s.fileList=[]):s.fileLists=t)}))},handleError:function(e,t,s){},handleSuccess:function(e,t,s){var a=e.data.fieldToken;void 0==a&&(a=this.value);var i=this.updatePath.split("=");""!=i[1]&&""==a&&(a=i[1]),this.$emit("update-value",a),this.fileLists=s,this.getFileData(a)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},r=l,c=(s("e116"),s("4ac2")),u=Object(c["a"])(r,a,i,!1,null,"e22390a6",null);t["a"]=u.exports},"850d":function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAOCAYAAAAMn20lAAAAF0lEQVQYlWNscGD4z4AFMGETHJUgTQIAcsYB2/7VbKAAAAAASUVORK5CYII="},8758:function(e,t){e.exports="data:image/png;base64,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"},b64e:function(e,t,s){},c059:function(e,t,s){},c489:function(e,t,s){"use strict";s("b64e")},dece:function(e,t,s){},e116:function(e,t,s){"use strict";s("c059")},f2f2:function(e,t,s){"use strict";s("dece")},fc12:function(e,t,s){"use strict";var a=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"multiple-selection-component"},[a("div",{staticClass:"transfer-contanier clearfix"},[a("div",{staticClass:"inner clearfix"},[a("div",{staticClass:"tree-left"},[a("div",{staticClass:"button-box"},[a("el-input",{attrs:{size:"mini",clearable:"",placeholder:"输入姓名进行过滤"},model:{value:e.filterText,callback:function(t){e.filterText="string"===typeof t?t.trim():t},expression:"filterText"}}),e._v(" "),a("el-button",{staticStyle:{"margin-left":"5px"},attrs:{icon:"el-icon-search",size:"mini"}},[e._v("查询")])],1),e._v(" "),a("div",{staticClass:"tree-box"},[a("el-tree",{ref:"tree",staticClass:"filter-tree",attrs:{"empty-text":"暂无数据","show-checkbox":"","check-on-click-node":!0,"default-expand-all":!0,"expand-on-click-node":!1,props:e.defaultProps,data:e.treeData,"filter-node-method":e.filterNode,"node-key":"userId"},on:{"check-change":e.checkChange}})],1)]),e._v(" "),a("div",{staticClass:"box-center"},[a("div",{staticClass:"list-member"},[e._m(0),e._v(" "),a("ul",e._l(e.defaultMember,(function(t,s){return a("li",{key:t.userId,ref:"memberLi",refInFor:!0,class:{"li-focus":e.chooseNum==s},on:{click:function(a){return e.memberSelect(s,t.userId,t.userName)}}},[a("i",{staticClass:"select el-icon-check"}),e._v(" "),a("span",{staticStyle:{"padding-left":"17px"}},[e._v(e._s(t.userName))])])})),0)])]),e._v(" "),a("div",{staticClass:"list-right"},[a("div",{staticClass:"select"},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",clearable:"",size:"mini",placeholder:"请选择"},on:{change:e.handleChange},model:{value:e.valueSelect,callback:function(t){e.valueSelect=t},expression:"valueSelect"}},e._l(e.options,(function(t){return a("el-option",{key:t.groupId,attrs:{label:t.groupName,value:t.groupId}},[a("span",{staticStyle:{float:"left"}},[e._v(e._s(t.groupName))]),e._v(" "),a("span",{staticClass:"icon-nav",staticStyle:{float:"right",color:"#8492a6","font-size":"12px",display:"none"}},[a("i",{on:{click:function(s){return s.stopPropagation(),e.defaultSetting(t.groupId)}}},[e._v("默认")]),e._v(" "),a("i",{staticStyle:{"margin-left":"10px"},on:{click:function(s){return s.stopPropagation(),e.deleteGroupMember(t.groupId)}}},[e._v("删除")])])])})),1)],1),e._v(" "),a("div",{staticClass:"choose-member-content"},[a("div",{staticClass:"tag-box"},e._l(e.chooseMember,(function(t){return a("el-tag",{key:t.userId,staticStyle:{"margin-right":"10px"},attrs:{closable:"",size:"mini",type:"success"},on:{close:function(s){return e.tagClose(t.userId)}}},[e._v(e._s(t.name))])})),1)]),e._v(" "),a("div",{staticClass:"button-nav"},[a("el-button",{staticStyle:{"background-color":"#B28147"},attrs:{type:"primary",size:"mini",icon:"el-icon-delete"},on:{click:e.all_delete}},[e._v("全部删除")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#B28147"},attrs:{type:"primary",size:"mini",icon:"el-icon-edit"},on:{click:function(t){e.innerVisible=!0}}},[e._v("保存分组")])],1)])]),e._v(" "),a("el-dialog",{attrs:{title:"自定义分组","append-to-body":"",visible:e.innerVisible},on:{"update:visible":function(t){e.innerVisible=t}}},[a("div",{staticClass:"edit-box"},[a("el-input",{attrs:{placeholder:"请输入关键字搜索",clearable:""},model:{value:e.editInput,callback:function(t){e.editInput="string"===typeof t?t.trim():t},expression:"editInput"}}),e._v(" "),a("div",{staticClass:"setting"},[a("p",[e._v("\n            保存为默认分组\n            "),a("el-switch",{attrs:{"active-color":"#2a71c3","inactive-color":"#ccc"},model:{value:e.switchValue,callback:function(t){e.switchValue=t},expression:"switchValue"}})],1),e._v(" "),a("el-button",{staticStyle:{"margin-left":"10px","background-color":"#B28147"},attrs:{type:"primary",size:"mini",icon:"el-icon-success"},on:{click:e.save_group_name}},[e._v("确认")])],1)],1)]),e._v(" "),a("div",{staticClass:"button-bottom"},[a("div",{staticClass:"button",on:{click:e.handleSave}},[a("img",{attrs:{src:s("8758"),alt:""}}),e._v("保存\n      ")])])],1)])},i=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("h2",[a("img",{attrs:{src:s("850d"),alt:""}}),e._v("常选人员\n          ")])}],n=(s("1bc7"),s("a450"),s("fc02"),s("e680"),s("7f43")),o=s.n(n),l={name:"MultipleSelection",model:{prop:"value",event:"update-value"},props:{value:{type:String,default:""},contextPath:{type:String,default:"/ei-web/"},userId:[String,Number],selectType:{type:String,default:"role"},bid:{type:String,default:"75"}},data:function(){return{switchValue:!1,editInput:"",innerVisible:!1,dialogTableVisible:!1,chooseNum:null,filterText:"",treeData:[],defaultProps:{children:"children",label:"name"},defaultMember:[],chooseMember:[],options:[],valueSelect:"",groupsList:[],sChooseMemberArr:[],valueDefault:[],url_project_id:"",loading:!1}},computed:{myValue:function(){return""!==this.value?this.value.split(","):[]}},watch:{filterText:function(e){this.$refs.tree.filter(e)}},created:function(){this.url_project_id=this.bid,this.initDefaultData(),this.getTreeList()},mounted:function(){this.$nextTick((function(){var e=[];if(""!==this.value){e=this.value.split(","),this.$refs.tree.setCheckedKeys(e);for(var t=0;t<this.defaultMember.length;t++)for(var s=0;s<e.length;s++)this.defaultMember[t].id==e[s]&&(this.$refs.memberLi[t].className="li-focus");this.chooseMember=this.$refs.tree.getCheckedNodes();for(var a=0;a<this.chooseMember.length;a++)void 0!==this.chooseMember[a].children&&this.chooseMember.splice(a,1)}}))},methods:{getTreeList:function(){var e=this;o()({url:this.contextPath+"/getTreeList",method:"get"}).then((function(t){var s=t.data.data.model;e.treeData.push(s),e.$nextTick((function(){e.valueDefault=this.value.split(","),e.$refs.tree.setCheckedKeys(this.valueDefault)}))}))},initDefaultData:function(){var e=this;o()({url:this.contextPath+"/selectUser/init",method:"get"}).then((function(t){var s=t.data.data.groups;if(e.groupsList=t.data.data.groups,e.defaultMember=t.data.data.users,s.length>0){e.options=[];for(var a=0;a<s.length;a++){var i={groupName:"",groupId:""};i.groupName=s[a].groupName,i.groupId=s[a].groupId,e.options.push(i)}}}))},filterNode:function(e,t){return!e||-1!==t.name.indexOf(e)},memberSelect:function(e,t,s){if(this.$refs.memberLi[e].className.length<=0){this.$refs.memberLi[e].className="li-focus";var a={userId:"",name:""};a.userId=t,a.name=s,this.sChooseMemberArr.push(a),this.$refs.tree.setCheckedNodes(this.sChooseMemberArr)}else{this.$refs.memberLi[e].className="";for(var i=0;i<this.chooseMember.length;i++)this.chooseMember[i].userId==t&&this.chooseMember.splice(i,1);this.$refs.tree.setCheckedNodes(this.chooseMember)}},checkChange:function(e,t){this.chooseMember.length;if(t){if("PERSON"===e.type){for(var s=0;s<this.defaultMember.length;s++)this.defaultMember[s].userId==e.userId&&(this.$refs.memberLi[s].className="li-focus");var a={userId:"",name:""};a.userId=e.userId,a.name=e.name,this.sChooseMemberArr.push(a);var i={};this.chooseMember=this.sChooseMemberArr.reduce((function(e,t){return!i[t.userId]&&(i[t.userId]=e.push(t)),e}),[])}}else{for(var n=0;n<this.defaultMember.length;n++)this.defaultMember[n].userId==e.userId&&(this.$refs.memberLi[n].className="");for(var o=0;o<this.chooseMember.length;o++)this.chooseMember[o].userId==e.userId&&this.chooseMember.splice(o,1)}this.sChooseMemberArr=this.chooseMember},defaultHandleArr:function(e){this.$refs.tree.setCheckedNodes(e)},handleChange:function(){for(var e=this.groupsList.length,t=[],s=0;s<e;s++)this.groupsList[s].groupId===this.valueSelect&&(t=this.groupsList[s].groupMembers);var a=t.length;if(a>0)for(var i=0;i<a;i++){var n={userId:"",name:""};n.userId=t[i].memberId,n.name=t[i].memberName,this.sChooseMemberArr.push(n)}var o={};this.chooseMember=this.sChooseMemberArr.reduce((function(e,t){return!o[t.userId]&&(o[t.userId]=e.push(t)),e}),[]),this.$refs.tree.setCheckedNodes(this.chooseMember)},defaultSetting:function(e){var t=this;o()({url:this.contextPath+"/selectUser/setDefaultGroup?groupId=".concat(e),method:"get"}).then((function(e){t.$message({offset:150,message:e.data.msg,type:"success"}),t.initDefaultData()}))},deleteGroupMember:function(e){var t=this;o()({url:this.contextPath+"/selectUser/deleteGroup?groupId=".concat(e),method:"get"}).then((function(e){t.$message({offset:150,message:e.data.msg,type:"success"}),t.initDefaultData();var s=t.options.length;1==s&&(t.options=[])}))},tagClose:function(e){for(var t=0;t<this.chooseMember.length;t++)this.chooseMember[t].userId==e&&this.chooseMember.splice(t,1);this.$refs.tree.setCheckedNodes(this.chooseMember)},defaultDelete:function(e){for(var t=0;t<this.chooseMember.length;t++)this.chooseMember[t].userId==e&&this.chooseMember.splice(t,1);this.$refs.tree.setCheckedNodes(this.chooseMember)},all_delete:function(){this.$refs.tree.setCheckedKeys([])},save_group_name:function(){var e=this.editInput,t=this;if(""===e)this.$message({offset:150,message:"请输入关键字搜索"});else if(e.length>=20)this.$message({offset:150,message:"请输入小于20字符"});else{var s=[];this.defaultChoose(s,this.chooseMember,"edit-save");var a=JSON.stringify(s),i=new URLSearchParams;i.append("groupName",e),i.append("groupMembers",a),i.append("isDefault",this.switchValue);var n=this.contextPath+"/selectUser/saveGroup";o.a.post(n,i).then((function(e){t.innerVisible=!1,t.$message({offset:150,message:e.data.msg,type:"success"}),t.initDefaultData()})).catch((function(e){console.log(e)}))}},handleSave:function(){var e=[],t=[],s=[];this.all_delete();for(var a=0;a<this.chooseMember.length;a++)e.push(this.chooseMember[a].userId),s.push(this.chooseMember[a].name);var i=e.join(",");this.$emit("update-value",i,s),this.defaultChoose(t,this.chooseMember,"save");var n=JSON.stringify(t),l=new URLSearchParams;l.append("selectUsers",n);var r=this.contextPath+"/selectUser/saveSelectUser";o.a.post(r,l).then((function(e){}));var c=new URLSearchParams;c.append("roleId",i),c.append("userIds",this.userId)},defaultChoose:function(e,t,s){return"edit-save"==s?(t.forEach((function(t,s,a){var i={memberId:"",memberName:""};i.memberId=t.userId,i.memberName=t.name,e.push(i)})),e):(t.forEach((function(t,s,a){var i={userId:"",userName:""};i.userId=t.userId,i.userName=t.name,e.push(i)})),e)}}},r=l,c=(s("c489"),s("4ac2")),u=Object(c["a"])(r,a,i,!1,null,"9fec0eb0",null);t["a"]=u.exports}}]);