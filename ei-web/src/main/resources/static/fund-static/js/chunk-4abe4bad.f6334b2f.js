(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4abe4bad","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"291b":function(e,t,a){},"2c2d":function(e,t,a){"use strict";var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"upload-box"},[e.hiddenUpload?a("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return a("h3",{key:t.uid},[a("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[a("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},n=[],o=(a("fc02"),a("a450"),a("e680"),a("7f43")),s=a.n(o),l={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,a=e||this.value;""!=a&&null!=a||(a="-1");var i=this.contextPath+"attachController/getFileList?fieldToken="+a;s()({url:i,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var a=e.name.substring(e.name.lastIndexOf(".")+1),i="rp"===a,n="vsd"===a;(i||n)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var a=this;s()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=t)}))},handleError:function(e,t,a){},handleSuccess:function(e,t,a){var i=e.data.fieldToken;void 0==i&&(i=this.value);var n=this.updatePath.split("=");""!=n[1]&&""==i&&(i=n[1]),this.$emit("update-value",i),this.fileLists=a,this.getFileData(i)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},r=l,d=(a("e116"),a("4ac2")),c=Object(d["a"])(r,i,n,!1,null,"e22390a6",null);t["a"]=c.exports},"309a":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticClass:"btnBox",staticStyle:{"margin-top":"20px"}},[a("button",{on:{click:e.addInfo}},[a("i",{staticClass:"iconfont iconjiahao"}),e._v("\n      新增\n    ")]),e._v(" "),a("button",{on:{click:e.editInfo}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      编辑\n    ")]),e._v(" "),a("button",{on:{click:e.delInfo}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      删除\n    ")])]),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"200",prop:"meetingTitle",label:"事项标题"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"===t.row.status?a("div",[e._v(e._s(t.row.meetingTitle))]):e._e(),e._v(" "),"0"!==t.row.status?a("el-link",{attrs:{type:"primary"},on:{click:function(a){return e.lookDetail(t.row)}}},[e._v(e._s(t.row.meetingTitle))]):e._e()]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"100",prop:"meetingTypeName",label:"事项类别"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",width:"110",prop:"hapDate",label:"发生日期"}}),e._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"200",prop:"attaFile",label:"附件"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",[t.row.attaFile?a("upLoad",{attrs:{"context-path":e.url.upApi,"hidden-upload":!0},model:{value:t.row.attaFile,callback:function(a){e.$set(t.row,"attaFile",a)},expression:"scope.row.attaFile"}}):e._e()],1)]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"statusName",label:"状态"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.tableData.currPage,"page-sizes":[5,10,15,20],"page-size":e.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.submitLoading,expression:"submitLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{title:e.modalTitle,visible:e.dialogVisible,"element-loading-background":"rgba(0, 0, 0, 0)",width:"90%"},on:{"update:visible":function(t){e.dialogVisible=t},close:e.dialogClose}},[e.lookStatus?e._e():a("formPage",{attrs:{dialogVisible:e.dialogVisible,status:e.status,userTree:e.userTree,"is-edit":!0},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}}),e._v(" "),e.lookStatus?a("div",{staticStyle:{padding:"0px 20px"}},[a("el-tabs",{model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"数据信息",name:"first"}},[a("formPage",{attrs:{dialogVisible:e.dialogVisible,status:e.status,"is-edit":!1,userTree:e.userTree},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}})],1),e._v(" "),a("el-tab-pane",{attrs:{label:"流程图",name:"second"}},[a("div",[a("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:e.liuchengUrl,frameborder:"0"}})])]),e._v(" "),a("el-tab-pane",{attrs:{label:"处理过程",name:"third"}},[a("div",[a("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:e.processUrl,frameborder:"0"}})])])],1)],1):e._e(),e._v(" "),e.lookStatus?e._e():a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitDialogInfo("save")}}},[e._v("保 存")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitDialogInfo("submit")}}},[e._v("提 交")])],1),e._v(" "),e.lookStatus?a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("关 闭")])],1):e._e()],1),e._v(" "),a("el-dialog",{attrs:{title:"人员选择",visible:e.choicePersonVisible,width:"70%"},on:{"update:visible":function(t){e.choicePersonVisible=t},close:e.choicePersonClose}},[a("choicePerson",{attrs:{contextPath:e.url.upApi},on:{"update-value":e.surePerson},model:{value:e.userId,callback:function(t){e.userId=t},expression:"userId"}})],1)],1)},n=[],o=(a("aa18"),a("982e"),a("fc02"),a("a450"),a("1bc7"),a("7562")),s=a("2c2d"),l=a("15b4"),r=a("e19c"),d=a("fc12"),c={name:"Index",props:{projInfo:{type:Object,default:function(){return{}}}},watch:{projInfo:function(e){this.dialogInfo.projName=e.projObjectName,this.dialogInfo.inveName=e.inveName}},data:function(){return{tableData:{data:[],pageSize:5,currPage:1,totalCount:10},modalTitle:"",dialogVisible:!1,dialogInfo:{},contractList:[],currencyList:[],selectList:[],choicePersonVisible:!1,userId:"",userTree:[],submitLoading:!1,eventTypeList:[],url:r["a"],status:"edit",liuchengUrl:"",processUrl:"",lookStatus:!1,activeName:"first",buttonList:[]}},mounted:function(){var e=this;this.getParentId(1017,"contractList"),this.getParentId(11016,"eventTypeList"),this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],this.getTableList(),o["a"].userTreeList().then((function(t){e.userTree[0]=t.data.data.model}))},components:{upLoad:s["a"],choicePerson:d["a"],formPage:l["default"]},methods:{setButtonList:function(){var e=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(t){"三会议案审查"===t.name&&t.buttons.forEach((function(t){"604020501"===t.code&&e.buttonList.push(t.code),"604020502"===t.code&&e.buttonList.push(t.code),"604020503"===t.code&&e.buttonList.push(t.code)}))}))},getParentId:function(e,t){var a=this,i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var n=[];if(i){var s=i.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){s.includes(e.value)&&n.push(e)}))}else n=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=n}else o["a"].getCodeByParentId({parentId:e}).then((function(n){var o=[];if(i){var s=i.split(",");n.data.data.options.forEach((function(e){s.includes(e.value)&&o.push(e)}))}else o=n.data.data.options;a[t]=o,sessionStorage.setItem("code".concat(e),JSON.stringify(n.data.data.options))}))},getTableList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1;o["a"].selectFpThreemeetingInfo({pageSize:t,currPage:a,projId:this.$route.params.projId}).then((function(t){e.tableData=t.data}))},addInfo:function(){this.modalTitle="新增三会议案审查",this.dialogVisible=!0,this.dialogInfo.pkId="",this.dialogInfo.projName=this.projInfo.projObjectName,this.dialogInfo.inveName=this.projInfo.inveName,this.status="add"},editInfo:function(){var e=this;this.modalTitle="编辑三会议案审查",1!==this.selectList.length?this.$message({offset:150,type:"warning",message:"请选择最少一条数据"}):(this.status="edit","0"===this.selectList[0].status?(this.dialogVisible=!0,o["a"].threemeetingInfoDatailsProj({pkId:this.selectList[0].pkId}).then((function(t){e.dialogInfo=t.data.data})).then((function(t){e.dialogInfo.projMemberModel&&e.dialogInfo.projMemberModel.length>0&&(e.$set(e.dialogInfo,"userId",e.dialogInfo.projMemberModel.map((function(e){return e.memberId})).join(",")),e.$set(e.dialogInfo,"userName",e.dialogInfo.projMemberModel.map((function(e){return e.memberName})).join(",")),o["a"].userTreeList().then((function(t){return e.userTree[0]=t.data.data.model,e.userTree})).then((function(t){var a=e.dialogInfo.projMemberModel.map((function(e){return e.memberId}));t.forEach((function(e){a.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){a.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){a.includes(e.userId)||(e.disabled=!0)}))}))})),e.userTree=t})))}))):this.$message({offset:150,type:"warning",message:"非草稿状态不能编辑"}))},delInfo:function(){var e=this;if(0===this.selectList.length)this.$message({offset:150,type:"warning",message:"请至少选择一条数据"});else{var t=[];if(this.selectList.forEach((function(a){"0"===a.status?t.push(a.pkId):e.$message({offset:150,type:"warning",message:"非草稿数据无法删除"})})),t.length===this.selectList.length){var a=this.selectList.map((function(e){return e.pkId}));this.$confirm("确定要删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){o["a"].deleteFpThreemeetingInfoProj({pkId:a.join(",")}).then((function(t){"0"===t.data.status&&(e.$message({offset:150,type:"success",message:"成功删除"}),e.getTableList(e.tableData.pageSize,1))}))})).catch((function(){}))}}},handleSizeChange:function(e){this.getTableList(e,1)},handleCurrentChange:function(e){this.getTableList(this.tableData.pageSize,e)},handleSelectionChange:function(e){this.selectList=e},dialogClose:function(){this.dialogInfo={},this.lookStatus=!1},submitDialogInfo:function(e){var t=this;if(this.dialogInfo.projId=this.$route.params.projId,void 0!==this.dialogInfo.userName&&null!==this.dialogInfo.userName&&""!==this.dialogInfo.userName){this.dialogInfo.userNames=this.dialogInfo.userName.split(","),this.dialogInfo.userIds=this.dialogInfo.userId.split(",");var a=[];this.dialogInfo.userNames.forEach((function(e,i){a.push({memberId:t.dialogInfo.userIds[i],memberName:t.dialogInfo.userNames[i]})})),this.dialogInfo.projMemberModel=a}else{a=[];this.dialogInfo.projMemberModel=a}if("save"===e){if(this.dialogInfo.tag="1",""===this.dialogInfo.meetingTitle||void 0===this.dialogInfo.meetingTitle||null===this.dialogInfo.meetingTitle)return void this.$message({offset:150,type:"warning",message:"请填写事项标题"})}else{if(this.dialogInfo.tag="0",""===this.dialogInfo.meetingTitle||void 0===this.dialogInfo.meetingTitle||null===this.dialogInfo.meetingTitle)return void this.$message({offset:150,type:"warning",message:"请填写事项标题"});if(""===this.dialogInfo.meetingType||void 0===this.dialogInfo.meetingType||null===this.dialogInfo.meetingType)return void this.$message({offset:150,type:"warning",message:"请选择事项类别"})}this.dialogInfo.carrier=this.$route.params.projId,this.dialogInfo.pkId?(this.submitLoading=!0,o["a"].updateFpThreemeetingInfoProjMc(this.dialogInfo).then((function(e){"0"===e.data.status&&"成功"===e.data.msg?(t.dialogVisible=!1,t.$message({offset:150,type:"success",message:"编辑成功"}),t.submitLoading=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage)):(t.$message({offset:150,type:"warning",message:e.data.msg}),t.submitLoading=!1)}))):(this.submitLoading=!0,o["a"].addFpThreemeetingInfoProjMc(this.dialogInfo).then((function(e){"0"===e.data.status&&"成功"===e.data.msg?(t.dialogVisible=!1,t.$message({offset:150,type:"success",message:"新增成功"}),t.submitLoading=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage)):(t.$message({offset:150,type:"warning",message:e.data.msg}),t.submitLoading=!1)})))},selectContract:function(e){var t=this;o["a"].numberDatails({projId:this.$route.params.projId,userType:e}).then((function(e){var a=e.data.data.map((function(e){return e.userId}));t.dialogInfo.userName=e.data.data.map((function(e){return e.userName})).join(","),t.userId=a.join(",")}))},choicePersonClose:function(){},cancelPerson:function(){this.choicePersonVisible=!1},surePerson:function(e,t){this.choicePersonVisible=!1,this.dialogInfo.userName=t.join(",")},lookDetail:function(e){var t=this;this.modalTitle="查看三会议案审查",this.lookStatus=!0,o["a"].threemeetingInfoDatailsProj({pkId:e.pkId}).then((function(e){t.dialogInfo=e.data.data,t.dialogVisible=!0,o["a"].getDefIdProcessinstanceId({processId:t.dialogInfo.processInstId}).then((function(e){t.liuchengUrl="/sysweb/action/workflowProcess!flowGraphics.action?defId=".concat(e.data,"&instId=").concat(t.dialogInfo.processInstId,"&taskId=").concat(t.dialogInfo.taskId,"&frameInd=2"),t.processUrl="/sysweb/action/showListview.action?listViewId=fixflowProcessInfosListView&instId=".concat(t.dialogInfo.processInstId,"&listViewFileName=fixflowProcessInfosListView&frameInd=2")}))})).then((function(e){t.dialogInfo.projMemberModel&&t.dialogInfo.projMemberModel.length>0&&(t.$set(t.dialogInfo,"userId",t.dialogInfo.projMemberModel.map((function(e){return e.memberId})).join(",")),t.$set(t.dialogInfo,"userName",t.dialogInfo.projMemberModel.map((function(e){return e.memberName})).join(",")),o["a"].userTreeList().then((function(e){return t.userTree[0]=e.data.data.model,t.userTree})).then((function(e){var a=t.dialogInfo.projMemberModel.map((function(e){return e.memberId}));e.forEach((function(e){a.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){a.includes(e.userId)||(e.disabled=!0),e.children.length>0&&e.children.forEach((function(e){a.includes(e.userId)||(e.disabled=!0)}))}))})),t.userTree=e,t.dialogVisible=!0,t.lookStatus=!0})))}))}}},u=c,f=(a("9e00"),a("4ac2")),h=Object(f["a"])(u,i,n,!1,null,"14cbe258",null);t["default"]=h.exports},"9e00":function(e,t,a){"use strict";a("291b")},c059:function(e,t,a){},e116:function(e,t,a){"use strict";a("c059")}}]);