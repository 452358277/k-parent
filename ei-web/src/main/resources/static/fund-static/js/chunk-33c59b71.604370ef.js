(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-33c59b71","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"2c2d":function(t,e,a){"use strict";var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileLists,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:t.limit,action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileLists,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v("\n        "+t._s(t.uploadTitle)+"\n      ")]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},l=[],n=(a("fc02"),a("a450"),a("e680"),a("7f43")),i=a.n(n),o={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(t){null!==t&&void 0!==t&&""!==t?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(t){}},computed:{updatePath:function(){var t="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return t}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(t){var e=this,a=t||this.value;""!=a&&null!=a||(a="-1");var s=this.contextPath+"attachController/getFileList?fieldToken="+a;i()({url:s,method:"get"}).then((function(t){"0"===t.data.status&&t.data.data.fileList&&e.$nextTick((function(){e.fileLists=t.data.data.fileList,t.data.data.fileList.length>0&&(e.fileList=t.data.data.fileList),0===e.fileLists.length&&(e.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),s="rp"===a,l="vsd"===a;(s||l)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;i()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&(0==e.length?(a.$emit("update-value",""),a.fileLists=[],a.fileList=[]):a.fileLists=e)}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var s=t.data.fieldToken;void 0==s&&(s=this.value);var l=this.updatePath.split("=");""!=l[1]&&""==s&&(s=l[1]),this.$emit("update-value",s),this.fileLists=a,this.getFileData(s)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},c=o,r=(a("e116"),a("4ac2")),u=Object(r["a"])(c,s,l,!1,null,"e22390a6",null);e["a"]=u.exports},5327:function(t,e,a){"use strict";a("5a28")},"5a28":function(t,e,a){},"8aa0":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",{staticClass:"personHeadInfo",staticStyle:{"margin-top":"20px"},attrs:{align:"center"}},[a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n      项目名称\n    ")]),t._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[t._v("\n        "+t._s(t.value.projName)+"\n      ")])]),t._v(" "),a("el-col",{staticClass:"personHeadTitle",attrs:{span:3}},[t._v("\n      出资主体\n    ")]),t._v(" "),a("el-col",{staticClass:"personHeadItem",attrs:{span:9}},[a("div",[t._v("\n        "+t._s(t.value.inveName)+"\n      ")])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      退出立项名称\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请选择",size:"small"},on:{change:t.getQuitProj},model:{value:t.value.quitProjId,callback:function(e){t.$set(t.value,"quitProjId",e)},expression:"value.quitProjId"}},t._l(t.quitProjList,(function(t){return a("el-option",{key:t.quitProjId,attrs:{label:t.quitName,value:t.quitProjId}})})),1)],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[t._v("\n      "+t._s(t.value.quitName)+"\n    ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      退出立项日期\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n     "+t._s(t.value.quitDate)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      退出方式\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.quitWayName)+"\n    ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      投时估值(万元)\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.whenCurrency)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      退出时估值(万元)\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.quitCurrency)+"\n    ")])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      当前股比%\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t.value.nowShare?a("div",[t._v("\n        "+t._s(t.value.nowShare)+"%\n      ")]):t._e()]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      回收金额(万元)\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.backMony)+"\n    ")])],1),t._v(" "),"2"===t.value.quitWay?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      转让出资额(万元)\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t._v("\n      "+t._s(t.value.overAmount)+"\n    ")]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      转让股比%\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t.value.overShare?a("div",[t._v("\n        "+t._s(t.value.overShare)+"%\n      ")]):t._e()])],1):t._e(),t._v(" "),"2"===t.value.quitWay?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      剩余股比%\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[t.value.residueShare?a("div",[t._v("\n        "+t._s(t.value.residueShare)+"%\n      ")]):t._e()]),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):t._e(),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      退出理由\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"600"},model:{value:t.value.exitReason,callback:function(e){t.$set(t.value,"exitReason",e)},expression:"value.exitReason"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("pre",[t._v(t._s(t.value.exitReason))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      备注\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"600"},model:{value:t.value.remark,callback:function(e){t.$set(t.value,"remark",e)},expression:"value.remark"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("pre",[t._v(t._s(t.value.remark))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      附件\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[t.dialogVisible?a("upLoad",{attrs:{"context-path":t.url.upApi,"hidden-upload":!t.isEdit},model:{value:t.value.attaFile,callback:function(e){t.$set(t.value,"attaFile",e)},expression:"value.attaFile"}}):t._e()],1)],1),t._v(" "),"UserTask_8"===t.node&&"view"!==t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      评估结果\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"context-path":t.url.upApi,"hidden-upload":!1},model:{value:t.value.quitAtta,callback:function(e){t.$set(t.value,"quitAtta",e)},expression:"value.quitAtta"}})],1)],1):t._e(),t._v(" "),"UserTask_8"===t.node&&"view"===t.steps?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      评估结果\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"context-path":t.url.upApi,"hidden-upload":!0},model:{value:t.value.quitAtta,callback:function(e){t.$set(t.value,"quitAtta",e)},expression:"value.quitAtta"}})],1)],1):t._e(),t._v(" "),"UserTask_10"===t.node?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      评估结果\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"context-path":t.url.upApi,"hidden-upload":!0},model:{value:t.value.quitAtta,callback:function(e){t.$set(t.value,"quitAtta",e)},expression:"value.quitAtta"}})],1)],1):t._e()],1)},l=[],n=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),i=a("2c2d"),o=a("e19c"),c={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0},node:{type:String,default:""},steps:{type:String,default:""},dialogVisible:{type:Boolean,default:!0}},data:function(){return{currencyList:[],quitTypeList:[],url:o["a"],quitProjList:[]}},components:{upLoad:i["a"]},mounted:function(){this.currencyList=[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],this.getParentId(262,"quitTypeList"),this.getProjList()},methods:{getParentId:function(t,e){var a=this,s=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var l=[];if(s){var i=s.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){i.includes(t.value)&&l.push(t)}))}else l=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=l}else n["a"].getCodeByParentId({parentId:t}).then((function(l){var n=[];if(s){var i=s.split(",");l.data.data.options.forEach((function(t){i.includes(t.value)&&n.push(t)}))}else n=l.data.data.options;a[e]=n,sessionStorage.setItem("code".concat(t),JSON.stringify(l.data.data.options))}))},getProjList:function(){var t=this;n["a"].selectProjAppInfoQuit({pageSize:1e3,currPage:1,status:"2",projId:this.$route.params.projId?this.$route.params.projId:this.$route.query.projId}).then((function(e){t.quitProjList=[],e.data.data.forEach((function(e,a){"0"!==e.stopStatus&&"-1"!==e.stopStatus||t.quitProjList.push(e)}))}))},getQuitProj:function(t){var e=this;n["a"].selectProjAppInfoQuitDetail({quitProjId:t}).then((function(t){"0"===t.data.status&&(t.data.data.exitReason=t.data.data.quitCause,e.$emit("update-value",t.data.data))}))}}},r=c,u=(a("5327"),a("4ac2")),d=Object(u["a"])(r,s,l,!1,null,"064c1762",null);e["default"]=d.exports},c059:function(t,e,a){},e116:function(t,e,a){"use strict";a("c059")}}]);