(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ac297974","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"1f7f":function(t,e,a){},"2c2d":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileLists,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:t.limit,action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileLists,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v("\n        "+t._s(t.uploadTitle)+"\n      ")]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},s=[],l=(a("fc02"),a("a450"),a("e680"),a("7f43")),n=a.n(l),o={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(t){null!==t&&void 0!==t&&""!==t?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(t){}},computed:{updatePath:function(){var t="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return t}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(t){var e=this,a=t||this.value;""!=a&&null!=a||(a="-1");var i=this.contextPath+"attachController/getFileList?fieldToken="+a;n()({url:i,method:"get"}).then((function(t){"0"===t.data.status&&t.data.data.fileList&&e.$nextTick((function(){e.fileLists=t.data.data.fileList,t.data.data.fileList.length>0&&(e.fileList=t.data.data.fileList),0===e.fileLists.length&&(e.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),i="rp"===a,s="vsd"===a;(i||s)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;n()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&(0==e.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=e)}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var i=t.data.fieldToken;void 0==i&&(i=this.value);var s=this.updatePath.split("=");""!=s[1]&&""==i&&(i=s[1]),this.$emit("update-value",i),this.fileLists=a,this.getFileData(i)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},c=o,u=(a("e116"),a("4ac2")),r=Object(u["a"])(c,i,s,!1,null,"e22390a6",null);e["a"]=r.exports},8099:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"createBox"},[a("div",{staticClass:"btnBox"},[!t.isEdit&&t.buttonList.includes("50010301")?a("button",{on:{click:t.editKey}},[a("i",{staticClass:"iconfont iconbianji"}),t._v(" "),a("div",[t._v("编辑")])]):t._e(),t._v(" "),!t.isEdit&&t.buttonList.includes("50010301")?a("button",{on:{click:t.changeKey}},[a("i",{staticClass:"iconfont iconbianji"}),t._v(" "),a("div",[t._v("变更人士")])]):t._e(),t._v(" "),t.isEdit?a("button",{on:{click:t.saveEdit}},[a("i",{staticClass:"iconfont iconicon-"}),t._v(" "),a("div",[t._v("保存")])]):t._e(),t._v(" "),t.isEdit?a("button",{on:{click:t.cancelEdit}},[a("i",{staticClass:"iconfont iconquxiao"}),t._v(" "),a("div",[t._v("取消保存")])]):t._e(),t._v(" "),a("button",{on:{click:t.lookDetail}},[a("i",{staticClass:"iconfont iconyanjing1"}),t._v(" "),a("div",[t._v("查看明细")])])]),t._v(" "),a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("关键人士 "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v(t._s(t.value.lockName))]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n          "+t._s(t.value.lockName)+"\n        ")]):t._e(),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n          变更日期\n          "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n          "+t._s(t.value.remark)+"\n        ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{size:"small","value-format":"yyyy-MM-dd",type:"date",placeholder:"选择日期"},model:{value:t.value.remark,callback:function(e){t.$set(t.value,"remark",e)},expression:"value.remark"}})],1):t._e()],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("附件 "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upload",{attrs:{"hidden-upload":!t.isEdit,"context-path":t.url.upApi},model:{value:t.value.lockFile,callback:function(e){t.$set(t.value,"lockFile",e)},expression:"value.lockFile"}})],1)],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"关键人士明细表",visible:t.dialogVisible,width:"70%"},on:{"update:visible":function(e){t.dialogVisible=e},close:t.dialogClose}},[a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:t.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"80",prop:"lockName",label:"关键人士"}}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"130",label:"附件"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("upload",{attrs:{"hidden-upload":!0,"context-path":t.url.upApi},model:{value:e.row.lockFile,callback:function(a){t.$set(e.row,"lockFile",a)},expression:"scope.row.lockFile"}})]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"100",prop:"remark",label:"变更日期"}})],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.tableData.currPage,"page-sizes":[5,10,20,30,40,50,60,70],"page-size":t.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.tableData.totalCount},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("关 闭")])],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"关键人士变更",visible:t.changedialogVisible,width:"70%"},on:{"update:visible":function(e){t.changedialogVisible=e}}},[a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("关键人士 "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",type:"primary"},model:{value:t.change.lockName,callback:function(e){t.$set(t.change,"lockName",e)},expression:"change.lockName"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n            变更日期\n            "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{size:"small","value-format":"yyyy-MM-dd",type:"date",placeholder:"选择日期"},model:{value:t.change.remark,callback:function(e){t.$set(t.change,"remark",e)},expression:"change.remark"}})],1)],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("附件 "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upload",{attrs:{"hidden-upload":!1,"context-path":t.url.upApi},model:{value:t.change.lockFile,callback:function(e){t.$set(t.change,"lockFile",e)},expression:"change.lockFile"}})],1)],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(e){t.changedialogVisible=!1}}},[t._v("关 闭")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(e){return t.saveEdit("change")}}},[t._v("保 存")])],1)])],1)},s=[],l=(a("a450"),a("1bc7"),a("698e")),n=a("e19c"),o=a("2c2d"),c={name:"KeyPersonLocking",data:function(){return{isEdit:!1,keyValue:this.keyPersonLock,buttonList:[],value:{},url:n["a"],dialogVisible:!1,tableData:{data:[],pageSize:5,currPage:1,totalCount:0},changedialogVisible:!1,change:{}}},components:{upload:o["a"]},watch:{},mounted:function(){this.getDetailInfo()},methods:{getDetailInfo:function(){var t=this;l["a"].lockInfoDetail({fundId:this.$route.query.fundId}).then((function(e){null!==e.data.data&&(t.value=e.data.data)}))},setButtonList:function(t){var e=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(t){"关键人士锁定"===t.name&&t.buttons.forEach((function(t){"50010301"===t.code&&e.buttonList.push(t.code)}))}))},editKey:function(){this.isEdit=!0},dialogClose:function(){this.tableData={data:[],pageSize:5,currPage:1,totalCount:0}},saveEdit:function(t){var e=this,a={fundId:this.$route.query.fundId};if("change"===t){if(!this.change.lockName)return void this.$message({offset:150,type:"warning",message:"请填写关键人士"});if(!this.change.remark)return void this.$message({offset:150,type:"warning",message:"请选择变更日期"});if(!this.change.lockFile)return void this.$message({offset:150,type:"warning",message:"请上传附件"});Object.assign(a,this.change)}else{if(!this.value.lockName)return void this.$message({offset:150,type:"warning",message:"请填写关键人士"});if(!this.value.remark)return void this.$message({offset:150,type:"warning",message:"请选择变更日期"});if(!this.value.lockFile)return void this.$message({offset:150,type:"warning",message:"请上传附件"});Object.assign(a,this.value)}l["a"].saveLockInfo(a).then((function(t){"0"===t.data.status&&"成功"===t.data.msg?(e.$message({offset:150,message:"保存成功",type:"success"}),e.isEdit=!1,e.changedialogVisible=!1,e.getDetailInfo()):(e.$message({offset:150,type:"warning",message:t.data.msg}),e.getDetailInfo())})).catch((function(t){}))},cancelEdit:function(){this.isEdit=!1,this.getDetailInfo()},lookDetail:function(){this.dialogVisible=!0,this.getTableList()},getTableList:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1;l["a"].getLockList({fundId:this.$route.query.fundId,pageSize:e,currPage:a}).then((function(e){t.tableData=e.data}))},handleSizeChange:function(t){this.getTableList(t,1)},handleCurrentChange:function(t){this.getTableList(this.tableData.pageSize,t)},changeKey:function(){this.changedialogVisible=!0,this.change={},this.$set(this.change,"lockName",this.value.lockName)}}},u=c,r=(a("8fa9"),a("4ac2")),d=Object(r["a"])(u,i,s,!1,null,"2623c1b8",null);e["default"]=d.exports},"8fa9":function(t,e,a){"use strict";a("1f7f")},c059:function(t,e,a){},e116:function(t,e,a){"use strict";a("c059")}}]);