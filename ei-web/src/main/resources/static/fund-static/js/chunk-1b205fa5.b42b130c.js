(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1b205fa5","chunk-58f38bc6"],{"4e34":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"detail"},[a("el-dialog",{attrs:{title:"详情页",visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-row",t._l(t.data,(function(e,i,o){return a("el-col",{key:o,attrs:{span:12}},[a("div",{staticClass:"left"},[t._v(t._s(t._f("getTitle")(i)))]),t._v(" "),a("div",{staticClass:"right"},[t._v(t._s(e))])])})),1),t._v(" "),a("el-row",[a("el-col",{staticStyle:{height:"auto"},attrs:{span:24}},[a("div",{staticClass:"left"},[t._v("备注 ")]),t._v(" "),a("div",{staticClass:"right",staticStyle:{width:"70%"}},[a("el-input",{staticStyle:{marginBottom:"5px"},attrs:{type:"textarea",disabled:"true"},model:{value:t.remark,callback:function(e){t.remark=e},expression:"remark"}})],1)])],1),t._v(" "),a("el-row",{staticStyle:{borderTop:"0"}},[a("el-col",{staticStyle:{height:"auto"},attrs:{span:24}},[a("div",{staticClass:"left"},[t._v("附件")]),t._v(" "),a("div",{staticClass:"right"},[t.dialogFormVisible?a("up-load",{attrs:{hiddenUpload:!0},model:{value:t.fileToken,callback:function(e){t.fileToken=e},expression:"fileToken"}}):t._e()],1)])],1)],1)],1)},o=[],l=a("f8f6"),n={components:{upLoad:l["default"]},filters:{getTitle:function(t){return"orgName"==t?"机构名称":"orgTypeName"==t?"机构类型":"orderNo"==t?"信用代码/注册号":"contact"==t?"联系人":"phoneNo"==t?"联系电话":"contactEmail"==t?"邮箱":"orgAdd"==t?"地址":"qualification"==t?"资质":"succCase"==t?"成功案例":"honor"==t?"企业荣誉":void 0}},data:function(){return{dialogFormVisible:!1,data:{},fileToken:"",remark:"",isshowDesc:!1}},methods:{showDialog:function(t){this.dialogFormVisible=!this.dialogFormVisible,this.data={},this.data.orgName=t[0].orgName,this.data.orgTypeName=t[0].orgTypeName,this.data.orderNo=t[0].orderNo,this.data.contact=t[0].contact,this.data.phoneNo=t[0].phoneNo,this.data.contactEmail=t[0].contactEmail,this.data.orgAdd=t[0].orgAdd,this.data.qualification=t[0].qualification,this.data.succCase=t[0].succCase,this.data.honor=t[0].honor,this.remark=t[0].remark,this.fileToken=null==t[0].approveFile?"":t[0].approveFile}}},s=n,d=a("4ac2"),r=Object(d["a"])(s,i,o,!1,null,null,null);e["default"]=r.exports},"5af9":function(t,e,a){"use strict";a("a48c")},a48c:function(t,e,a){},f8f6:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileList,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileList,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v(t._s(t.uploadTitle))]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},o=[],l=(a("fc02"),a("a450"),a("e680"),a("7f43")),n=a.n(l),s={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!1},hiddenUpload:{type:Boolean,default:!1},tipTitle:{type:String,default:"只能上传jpg / png文件， 且不超过500kb"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:1},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/oa-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{updatePath:this.contextPath+"attachController/upload?fieldToken="+this.value,fileList:[]}},created:function(){var t=this;setTimeout((function(){t.$nextTick((function(){t.updatePath=t.contextPath+"attachController/upload?fieldToken="+t.value,t.getFileData()}))}),500)},methods:{getFileData:function(){var t=this,e=this.value;""!=e&&null!=e||(e="-1"),n()({url:this.contextPath+"attachController/getFileList?fieldToken="+e,method:"get"}).then((function(e){"0"===e.data.status?e.data.data.fileList&&t.$nextTick((function(){t.fileList=e.data.data.fileList,0==t.fileList.length&&t.$emit("update-value","")})):t.$message({type:"info",duration:1e3,message:e.data.msg})}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),i="rp"===a,o="vsd"===a;(i||o)&&(this.$message({message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;n()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&0==e.length&&a.$emit("update-value","")}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var i=t.data.fieldToken;void 0==i&&(i=this.value);var o=this.updatePath.split("=");""!=o[1]&&""==i&&(i=o[1]),this.updatePath=this.contextPath+"attachController/upload?fieldToken="+i,this.$emit("update-value",i)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},d=s,r=(a("5af9"),a("4ac2")),c=Object(r["a"])(d,i,o,!1,null,"9e912a16",null);e["default"]=c.exports}}]);