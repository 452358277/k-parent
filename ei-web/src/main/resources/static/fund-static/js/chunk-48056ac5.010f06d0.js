(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-48056ac5","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"2c2d":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileLists,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:t.limit,action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileLists,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v("\n        "+t._s(t.uploadTitle)+"\n      ")]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},n=[],s=(a("fc02"),a("a450"),a("e680"),a("7f43")),l=a.n(s),o={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(t){null!==t&&void 0!==t&&""!==t?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(t){}},computed:{updatePath:function(){var t="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return t}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(t){var e=this,a=t||this.value;""!=a&&null!=a||(a="-1");var i=this.contextPath+"attachController/getFileList?fieldToken="+a;l()({url:i,method:"get"}).then((function(t){"0"===t.data.status&&t.data.data.fileList&&e.$nextTick((function(){e.fileLists=t.data.data.fileList,t.data.data.fileList.length>0&&(e.fileList=t.data.data.fileList),0===e.fileLists.length&&(e.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),i="rp"===a,n="vsd"===a;(i||n)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;l()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&(0==e.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=e)}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var i=t.data.fieldToken;void 0==i&&(i=this.value);var n=this.updatePath.split("=");""!=n[1]&&""==i&&(i=n[1]),this.$emit("update-value",i),this.fileLists=a,this.getFileData(i)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},c=o,d=(a("e116"),a("4ac2")),u=Object(d["a"])(c,i,n,!1,null,"e22390a6",null);e["a"]=u.exports},3801:function(t,e,a){"use strict";a("4fb0")},"4fb0":function(t,e,a){},c059:function(t,e,a){},e116:function(t,e,a){"use strict";a("c059")},f36d:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"tableContent",staticStyle:{padding:"0px"}},[t.buttonList.includes("602020201")||t.buttonList.includes("602030201")?a("div",{staticClass:"btnBox"},[t.isEdit?t._e():a("button",{on:{click:t.editInfo}},[t._v("编辑")]),t._v(" "),t.isEdit?a("button",{on:{click:t.saveInfo}},[t._v("保存")]):t._e(),t._v(" "),t.isEdit?a("button",{on:{click:t.cancelInfo}},[t._v("取消")]):t._e()]):t._e(),t._v(" "),a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[t._v("历史沿革")]),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件说明\n      ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{rows:4,size:"small",type:"textarea",maxlength:"1000"},model:{value:t.dialogInfo.fundDesc,callback:function(e){t.$set(t.dialogInfo,"fundDesc",e)},expression:"dialogInfo.fundDesc"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("div",[t._v("\n          "+t._s(t.dialogInfo.fundDesc)+"\n        ")])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"hidden-upload":!t.isEdit,"context-path":t.url.upApi},model:{value:t.dialogInfo.fundDescFile,callback:function(e){t.$set(t.dialogInfo,"fundDescFile",e)},expression:"dialogInfo.fundDescFile"}})],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[t._v("增值服务")]),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件说明\n      ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{rows:4,size:"small",type:"textarea",maxlength:"1000"},model:{value:t.dialogInfo.vaService,callback:function(e){t.$set(t.dialogInfo,"vaService",e)},expression:"dialogInfo.vaService"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("div",[t._v("\n          "+t._s(t.dialogInfo.vaService)+"\n        ")])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"hidden-upload":!t.isEdit,"context-path":t.url.upApi},model:{value:t.dialogInfo.vaServiceFile,callback:function(e){t.$set(t.dialogInfo,"vaServiceFile",e)},expression:"dialogInfo.vaServiceFile"}})],1)],1)],1)])},n=[],s=(a("a450"),a("1bc7"),a("7562")),l=a("2c2d"),o=a("e19c"),c={name:"Index",components:{upLoad:l["a"]},data:function(){return{dialogInfo:{},url:o["a"],isEdit:!1,buttonList:[]}},mounted:function(){this.getDetailInfo()},methods:{setButtonList:function(){var t=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(e){"历史沿革及服务"===e.name&&e.buttons.forEach((function(e){"602020201"===e.code&&t.buttonList.push(e.code),"602030201"===e.code&&t.buttonList.push(e.code)}))}))},getDetailInfo:function(){var t=this;s["a"].selectFundDescInfo({fundId:this.$route.params.fundId}).then((function(e){t.dialogInfo=e.data.data}))},editInfo:function(){this.isEdit=!this.isEdit},saveInfo:function(){var t=this;this.dialogInfo.fundId=this.$route.params.fundId,s["a"].updateFundDescInfo(this.dialogInfo).then((function(e){"0"===e.data.status?(t.getDetailInfo(),t.$message({offset:150,type:"success",message:"保存成功"})):t.$message({offset:150,type:"warning",message:e.data.msg})})),this.isEdit=!this.isEdit},cancelInfo:function(){this.isEdit=!this.isEdit,this.getDetailInfo()}}},d=c,u=(a("3801"),a("4ac2")),f=Object(u["a"])(d,i,n,!1,null,"c19d2ff0",null);e["default"]=f.exports}}]);