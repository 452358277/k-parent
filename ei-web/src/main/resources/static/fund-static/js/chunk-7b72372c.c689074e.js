(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7b72372c","chunk-0a4cf008","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"1df9":function(t,e,a){},"257a":function(t,e,a){"use strict";a("f1f3")},"2c2d":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileLists,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:t.limit,action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileLists,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v("\n        "+t._s(t.uploadTitle)+"\n      ")]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},s=[],l=(a("fc02"),a("a450"),a("e680"),a("7f43")),n=a.n(l),o={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(t){null!==t&&void 0!==t&&""!==t?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(t){}},computed:{updatePath:function(){var t="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return t}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(t){var e=this,a=t||this.value;""!=a&&null!=a||(a="-1");var i=this.contextPath+"attachController/getFileList?fieldToken="+a;n()({url:i,method:"get"}).then((function(t){"0"===t.data.status&&t.data.data.fileList&&e.$nextTick((function(){e.fileLists=t.data.data.fileList,t.data.data.fileList.length>0&&(e.fileList=t.data.data.fileList),0===e.fileLists.length&&(e.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),i="rp"===a,s="vsd"===a;(i||s)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;n()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&(0==e.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=e)}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var i=t.data.fieldToken;void 0==i&&(i=this.value);var s=this.updatePath.split("=");""!=s[1]&&""==i&&(i=s[1]),this.$emit("update-value",i),this.fileLists=a,this.getFileData(i)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},c=o,r=(a("e116"),a("4ac2")),u=Object(r["a"])(c,i,s,!1,null,"e22390a6",null);e["a"]=u.exports},8276:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      申请名称\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:t.value.appId,callback:function(e){t.$set(t.value,"appId",e)},expression:"value.appId"}},t._l(t.quitApplyList,(function(t){return a("el-option",{key:t.appId,attrs:{label:t.appName,value:t.appId}})})),1)],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[t._v("\n      "+t._s(t.value.appName)+"\n    ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同名称\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"20"},model:{value:t.value.fileTitle,callback:function(e){t.$set(t.value,"fileTitle",e)},expression:"value.fileTitle"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.fileTitle)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同类别\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",size:"small"},model:{value:t.value.fileType,callback:function(e){t.$set(t.value,"fileType",e)},expression:"value.fileType"}},t._l(t.contractTypeList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.fileTypeName)+"\n    ")])],1),t._v(" "),"1"===t.value.fileType?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      协议金额(万元)\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._e(),t._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:t.value.thisAgreeAmont,callback:function(e){t.$set(t.value,"thisAgreeAmont",e)},expression:"value.thisAgreeAmont"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.thisAgreeAmont)+t._s(t.value.currencyName)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):t._e(),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      重点关注条款\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:t.value.majorTerm,callback:function(e){t.$set(t.value,"majorTerm",e)},expression:"value.majorTerm"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("pre",[t._v(t._s(t.value.majorTerm))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      备注\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:t.value.remark,callback:function(e){t.$set(t.value,"remark",e)},expression:"value.remark"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("pre",[t._v(t._s(t.value.remark))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      附件\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[t.dialogVisible?a("upload",{attrs:{"context-path":t.url.upApi,"hidden-upload":!t.isEdit},model:{value:t.value.contractFile,callback:function(e){t.$set(t.value,"contractFile",e)},expression:"value.contractFile"}}):t._e()],1)],1),t._v(" "),"UserTask_7"===t.node&&"view"!==t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同签订日期\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.value.signDt,callback:function(e){t.$set(t.value,"signDt",e)},expression:"value.signDt"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):t._e(),t._v(" "),"UserTask_7"===t.node&&"view"!==t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      最终合同附件\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[t.dialogVisible?a("upload",{attrs:{"context-path":t.url.upApi,"hidden-upload":!1},model:{value:t.value.finalFile,callback:function(e){t.$set(t.value,"finalFile",e)},expression:"value.finalFile"}}):t._e()],1)],1):t._e(),t._v(" "),"UserTask_7"===t.node&&"view"===t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同签订日期\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.signDt)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):t._e(),t._v(" "),"UserTask_7"===t.node&&"view"===t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      最终合同附件\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[t.dialogVisible?a("upload",{attrs:{"context-path":t.url.upApi,"hidden-upload":!0},model:{value:t.value.finalFile,callback:function(e){t.$set(t.value,"finalFile",e)},expression:"value.finalFile"}}):t._e()],1)],1):t._e()],1)},s=[],l=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("e19c")),n=a("2c2d"),o=a("7562"),c={name:"FormPage",components:{upload:n["a"]},model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0},dialogVisible:{type:Boolean,default:!0},isYY:{type:Boolean,default:!1},node:{type:String,default:""},steps:{type:String,default:""}},data:function(){return{projectInfo:{},url:l["a"],quitApplyList:[],contractTypeList:[]}},watch:{dialogVisible:function(t){!0===t&&this.selectfundQuitApplyList()}},mounted:function(){this.selectfundQuitApplyList(),this.getParentId(1024,"contractTypeList")},methods:{getParentId:function(t,e){var a=this,i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var s=[];if(i){var l=i.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){l.includes(t.value)&&s.push(t)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=s}else o["a"].getCodeByParentId({parentId:t}).then((function(s){var l=[];if(i){var n=i.split(",");s.data.data.options.forEach((function(t){n.includes(t.value)&&l.push(t)}))}else l=s.data.data.options;a[e]=l,sessionStorage.setItem("code".concat(t),JSON.stringify(s.data.data.options))}))},selectfundQuitApplyList:function(){var t=this;o["a"].selectfundQuitApplyList({pageSize:1e3,currPage:1,status:"2",projId:this.$route.params.projId?this.$route.params.projId:"60054"}).then((function(e){t.quitApplyList=e.data.data}))}}},r=c,u=(a("d1f2"),a("4ac2")),d=Object(u["a"])(r,i,s,!1,null,"0beb7292",null);e["default"]=d.exports},c059:function(t,e,a){},c6ca:function(t,a,i){"use strict";i.r(a);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"btnBox",staticStyle:{"margin-top":"20px"}},[t.buttonList.includes("602041201")?a("button",{on:{click:t.addInfo}},[a("i",{staticClass:"iconfont iconjiahao"}),t._v("\n      新增\n    ")]):t._e(),t._v(" "),t.buttonList.includes("602041202")?a("button",{on:{click:function(e){return t.editInfo(t.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),t._v("\n      编辑\n    ")]):t._e(),t._v(" "),t.buttonList.includes("602041203")?a("button",{on:{click:function(e){return t.delInfo(t.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),t._v("\n      删除\n    ")]):t._e()]),t._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:t.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"center",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{align:"left",width:"200",prop:"fileTitle",label:"合同名称"},scopedSlots:t._u([{key:"default",fn:function(e){return["0"===e.row.status?a("div",[t._v(t._s(e.row.fileTitle))]):t._e(),t._v(" "),"0"!==e.row.status?a("el-link",{attrs:{type:"primary"},on:{click:function(a){return t.lookDetail(e.row)}}},[t._v(t._s(e.row.fileTitle))]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left",width:"200",prop:"appName",label:"退出决策名称"}}),t._v(" "),a("el-table-column",{attrs:{align:"center","min-width":"100",prop:"quitDt",label:"退出日期"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.quitDt.slice(0,10))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"100",prop:"quitWayName",label:"退出方式"}}),t._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"120",label:"认缴出资额(万元)"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(t._f("money")(e.row.fundSubscribed))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"130",prop:"fileTitle",label:"实缴出资额(万元)"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(t._f("money")(e.row.fundPaidIn))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"90",prop:"shareRatio",label:"股比%"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.shareRatio?e.row.shareRatio:"")+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"right",width:"120",label:"回收金额(万元)"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s("null"===e.row.backAmount?"":e.row.backAmount)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"140",prop:"fileTitle",label:"退出认缴出资额(万元)"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s("null"===e.row.exitAmount?"":e.row.exitAmount)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"130",prop:"exitShareRatio",label:"退出部分所占股比%"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s("null"===e.row.exitShareRatio?"":e.row.exitShareRatio)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"160",prop:"restShareRatio",label:"剩余部分所占股比%"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s("null"===e.row.restShareRatio?"":e.row.restShareRatio)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"200",prop:"decisionDetail",label:"决策内容"}}),t._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"120",prop:"statusName",label:"状态"}})],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.tableData.currPage,"page-sizes":[5,10,15,20],"page-size":t.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.tableData.totalCount},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}),t._v(" "),a("el-dialog",{directives:[{name:"loading",rawName:"v-loading",value:t.submitLoading,expression:"submitLoading"}],attrs:{title:t.modalTitle,"element-loading-background":"rgba(0, 0, 0, 0)",visible:t.dialogVisible,width:"90%"},on:{"update:visible":function(e){t.dialogVisible=e},close:t.dialogClose}},[t.lookStatus?t._e():a("formPage",{attrs:{"dialog-visible":t.dialogVisible},model:{value:t.dialogInfo,callback:function(e){t.dialogInfo=e},expression:"dialogInfo"}}),t._v(" "),t.lookStatus?a("div",{staticStyle:{padding:"0px 20px"}},[t.lookStatus?a("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"数据信息",name:"first"}},[a("formPage",{attrs:{"dialog-visible":t.dialogVisible,"is-edit":t.isEdit},model:{value:t.dialogInfo,callback:function(e){t.dialogInfo=e},expression:"dialogInfo"}})],1),t._v(" "),a("el-tab-pane",{attrs:{label:"流程图",name:"second"}},[a("div",[a("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:t.liuchengUrl,frameborder:"0"}})])]),t._v(" "),a("el-tab-pane",{attrs:{label:"处理过程",name:"third"}},[a("div",[a("iframe",{staticStyle:{width:"100%",height:"50vh"},attrs:{src:t.processUrl,frameborder:"0"}})])])],1):t._e()],1):t._e(),t._v(" "),t.lookStatus?t._e():a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(e){return t.submitDialogInfo("save")}}},[t._v("保 存")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(e){return t.submitDialogInfo("submit")}}},[t._v("提 交")])],1),t._v(" "),t.lookStatus?a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("关 闭")])],1):t._e()],1)],1)},l=[],n=(i("e680"),i("aa18"),i("982e"),i("fc02"),i("a450"),i("1bc7"),i("7562")),o=i("2c2d"),c=i("8276"),r={name:"Index",components:{upLoad:o["a"],formPage:c["default"]},data:function(){return{tableData:{data:[],pageSize:5,currPage:1,totalCount:10},modalTitle:"",dialogVisible:!1,dialogInfo:{},contractList:[],currencyList:[],submitLoading:!1,selectList:[],activeName:"first",lookStatus:!1,liuchengUrl:"",processUrl:"",isEdit:!1,buttonList:[]}},mounted:function(){this.getParentId(224,"contractList"),this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],this.getTableList()},methods:{setButtonList:function(){var t=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(e){"退出合同签署"===e.name&&e.buttons.forEach((function(e){"602041201"===e.code&&t.buttonList.push(e.code),"602041202"===e.code&&t.buttonList.push(e.code),"602041203"===e.code&&t.buttonList.push(e.code)}))}))},getParentId:function(t,e){var a=this,i=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var s=[];if(i){var l=i.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){l.includes(t.value)&&s.push(t)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=s}else n["a"].getCodeByParentId({parentId:t}).then((function(s){var l=[];if(i){var n=i.split(",");s.data.data.options.forEach((function(t){n.includes(t.value)&&l.push(t)}))}else l=s.data.data.options;a[e]=l,sessionStorage.setItem("code".concat(t),JSON.stringify(s.data.data.options))}))},getTableList:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:5,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1;n["a"].selectProjContractFileQuit({pageSize:e,currPage:a,projId:this.$route.params.projId}).then((function(e){t.tableData=e.data}))},addInfo:function(){this.modalTitle="新增退出合同签署",this.dialogVisible=!0},editInfo:function(t){var e=this;1===t.length?"0"===t[0].status?(this.modalTitle="编辑退出合同签署",n["a"].projContractFileQuitDetail({fileId:t[0].fileId}).then((function(t){e.dialogInfo=t.data.data,e.dialogVisible=!0}))):this.$message({offset:150,type:"warning",message:"审核中的数据无法编辑"}):this.$message({offset:150,type:"warning",message:"请选择一条数据进行编辑"})},delInfo:function(t){var e=this;if(0===t.length)this.$message({offset:150,type:"warning",message:"请至少选一条数据删除"});else{for(var a=[],i=0;i<this.selectList.length;i++){if("0"!==this.selectList[i].status)return void this.$message({offset:150,type:"warning",message:"进行审核的数据无法删除"});a.push(this.selectList[i].fileId)}this.$confirm("确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){n["a"].deleteProjContractFileQuit({fileId:a.join(",")}).then((function(t){"0"===t.data.status&&(e.$message({offset:150,type:"success",message:"成功删除"}),e.getTableList(e.tableData.pageSize,1))}))})).catch((function(){}))}},handleSizeChange:function(t){this.getTableList(t,1)},handleCurrentChange:function(){this.getTableList(this.tableData.pageSize,e)},handleSelectionChange:function(t){this.selectList=t},dialogClose:function(){this.dialogInfo={}},submitDialogInfo:function(t){var e=this;if("submit"===t){if(this.dialogInfo.tag="0",""===this.dialogInfo.appId||null===this.dialogInfo.appId||void 0===this.dialogInfo.appId)return void this.$message({offset:150,type:"warning",message:"请选择申请名称"});if(null===this.dialogInfo.fileTitle||void 0===this.dialogInfo.fileTitle||""===this.dialogInfo.fileTitle.trim())return void this.$message({offset:150,type:"warning",message:"请填写合同名称"});if(""===this.dialogInfo.fileType||null===this.dialogInfo.fileType||void 0===this.dialogInfo.fileType)return void this.$message({offset:150,type:"warning",message:"请选择合同类别"});if("1"===this.dialogInfo.fileType){if(""===this.dialogInfo.thisAgreeAmont||null===this.dialogInfo.thisAgreeAmont||void 0===this.dialogInfo.thisAgreeAmont)return void this.$message({offset:150,type:"warning",message:"请填写协议金额"});if(isNaN(Number(this.dialogInfo.thisAgreeAmont)))return void this.$message({offset:150,type:"warning",message:"请正确填写协议金额，格式为数字"})}}else if(this.dialogInfo.tag="1",""===this.dialogInfo.appId||null===this.dialogInfo.appId||void 0===this.dialogInfo.appId)return void this.$message({offset:150,type:"warning",message:"请选择申请名称"});this.dialogInfo.projId=this.$route.params.projId,this.submitLoading=!0,this.dialogInfo.fileId?n["a"].updateProjContractFileQuit(this.dialogInfo).then((function(t){"0"===t.data.status&&"成功"===t.data.msg?(e.$message({offset:150,type:"success",message:"编辑成功"}),e.getTableList(e.tableData.pageSize,e.tableData.currPage),e.dialogVisible=!1,e.submitLoading=!1):(e.$message({offset:150,type:"warning",message:t.data.msg}),e.getTableList(e.tableData.pageSize,e.tableData.currPage),e.submitLoading=!1)})):n["a"].addProjContractFileQuit(this.dialogInfo).then((function(t){"0"===t.data.status&&"成功"===t.data.msg?(e.dialogInfo.fileId?e.$message({offset:150,type:"success",message:"新增成功"}):e.$message({offset:150,type:"success",message:"编辑成功"}),e.getTableList(e.tableData.pageSize,e.tableData.currPage),e.dialogVisible=!1,e.submitLoading=!1):(e.$message({offset:150,type:"warning",message:t.data.msg}),e.getTableList(e.tableData.pageSize,e.tableData.currPage),e.submitLoading=!1)}))},selectContract:function(t){},handleClick:function(){},lookDetail:function(t){var e=this;this.lookStatus=!0,this.modalTitle="查看退出合同",n["a"].projContractFileQuitDetail({fileId:t.fileId}).then((function(t){e.dialogInfo=t.data.data,e.dialogVisible=!0,n["a"].getDefIdProcessinstanceId({processId:e.dialogInfo.processInstId}).then((function(t){e.liuchengUrl="/sysweb/action/workflowProcess!flowGraphics.action?defId=".concat(t.data,"&instId=").concat(e.dialogInfo.processInstId,"&taskId=").concat(e.dialogInfo.taskId,"&frameInd=2"),e.processUrl="/sysweb/action/showListview.action?listViewId=fixflowProcessInfosListView&instId=".concat(e.dialogInfo.processInstId,"&listViewFileName=fixflowProcessInfosListView&frameInd=2")}))}))}}},u=r,d=(i("257a"),i("4ac2")),f=Object(d["a"])(u,s,l,!1,null,"5ecc02c1",null);a["default"]=f.exports},d1f2:function(t,e,a){"use strict";a("1df9")},e116:function(t,e,a){"use strict";a("c059")},f1f3:function(t,e,a){}}]);