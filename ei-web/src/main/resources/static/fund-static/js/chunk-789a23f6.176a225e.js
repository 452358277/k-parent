(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-789a23f6","chunk-76eb0f72","chunk-76eb0f72","chunk-76eb0f72"],{"26d7":function(t,e,a){"use strict";a("abe8")},"58b9":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"upload-box"},[t.hiddenUpload?a("div",{staticClass:"fileView"},t._l(t.fileLists,(function(e){return a("h3",{key:e.uid},[a("a",{attrs:{href:t.contextPath+"attachController/download?fileId="+e.uid,target:"_blank"}},[t._v(t._s(e.name))])])})),0):t._e(),t._v(" "),t.hiddenUpload?t._e():a("div",{staticClass:"el-upload-box"},[a("el-upload",{ref:"upload",attrs:{limit:t.limit,action:t.updatePath,"auto-upload":!0,name:t.upLoadName,data:t.extraData,"file-list":t.fileLists,accept:t.acceptFormat,"on-success":t.handleSuccess,"on-exceed":t.handleExceed,"on-preview":t.handlePreview,"on-remove":t.handleRemove,"on-error":t.handleError,"on-change":t.handleChange,"before-remove":t.beforeRemove,"before-upload":t.beforeUpload}},[a("el-button",{class:{active:t.hiddenUpload},attrs:{size:t.size,type:"primary"}},[t._v("\n        "+t._s(t.uploadTitle)+"\n      ")]),t._v(" "),t.isShowTip?a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v(t._s(t.TipTitle))]):t._e()],1)],1)])},l=[],i=(a("fc02"),a("a450"),a("e680"),a("7f43")),c=a.n(i),n={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(t){null!==t&&void 0!==t&&""!==t&&this.getFileData()},hiddenUpload:function(){this.getFileData()}},computed:{updatePath:function(){var t="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return t}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(){var t=this,e=this.value;""!=e&&null!=e||(e="-1");var a=this.contextPath+"attachController/getFileList?fieldToken="+e;c()({url:a,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0==t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(t){},handleChange:function(t,e){var a=t.name.substring(t.name.lastIndexOf(".")+1),s="rp"===a,l="vsd"===a;(s||l)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(t,e){this.$message.warning("当前限制选择 ".concat(t.length," 个文件，本次选择了 ").concat(t.length," 个文件，共选择了 ").concat(t.length+e.length," 个文件"))},handleRemove:function(t,e){var a=this;c()({url:this.contextPath+"attachController/delete?fileId="+t.uid,method:"get"}).then((function(t){"0"===t.data.status&&0==e.length&&(a.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))},handleError:function(t,e,a){},handleSuccess:function(t,e,a){var s=t.data.fieldToken;void 0==s&&(s=this.value);var l=this.updatePath.split("=");""!=l[1]&&""==s&&(s=l[1]),this.$emit("update-value",s)},beforeRemove:function(t,e){return this.$confirm("确定移除 ".concat(t.name,"？"))},handlePreview:function(t){window.open(this.contextPath+"attachController/download?fileId="+t.uid)}}},r=n,o=(a("abf3"),a("4ac2")),p=Object(o["a"])(r,s,l,!1,null,"36d367e4",null);e["default"]=p.exports},"9c99":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{padding:"24px 4px"}},[a("div",{staticClass:"self-check-box"},[a("div",{staticClass:"self-check-title"},[t._v("江苏省政府投资基金产业基金投资项目政策合规性自查表")]),t._v(" "),a("div",{staticClass:"self-check-about self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("基金概况")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("①基金名称：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("②基金管理人（如为双管理人请全部填写）：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("③基金认缴出资额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("④基金已实缴金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑤投资期限：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"}})],1),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑥投资期起算日：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("el-date-picker",{attrs:{type:"date","value-format":"yyyy-MM-dd",placeholder:"选择日期"}})],1)],1)],1),t._v(" "),a("div",{staticClass:"self-check-project self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("投资项目")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑦项目编号：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text",placeholder:"基金编号加三位数项目顺序号"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑧项目名称：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑨项目是否属于基金：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[t.selfCheckInfo.projInfoModel?a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1):t._e()]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑩是否属于SPV基金：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"0"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"1"}},[t._v("否")])],1)])],1)],1),t._v(" "),a("div",{staticClass:"self-check-money self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("投资金额")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("\n            ⑪本期投资金额：\n          ")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑫基金对项目累计投资金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑬本期投资金额占基金认缴出资总额的比例")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑭基金对项目累计投资金额占基金认缴出资总额的比例")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑮基金对项目的累计出资比例")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑯基金是否为最大出资人或股东")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)])],1)],1),t._v(" "),a("div",{staticClass:"self-check-field self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("投资领域")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑰项目所属行业/领域：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑱是否属于基金主投领域：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑲已完成投资项目中非主投领域项目合计投资金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("⑳已完成投资项目中非主投领域项目合计投资金额占本基金认缴出资总额的比例：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1)],1),t._v(" "),a("div",{staticClass:"self-check-area self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("投资地域")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉑项目是否属于江苏省内：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉒项目属第12条江苏省内企业的何种情形：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box",staticStyle:{display:"flex","align-items":"center","justify-content":"center","flex-direction":"column"}},[a("el-radio",{attrs:{label:"1"}},[t._v("第(1)条")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("第(2)条")]),t._v(" "),a("el-radio",{attrs:{label:"3"}},[t._v("第(3)条")]),t._v(" "),a("el-radio",{staticStyle:{"margin-right":"30px"},attrs:{label:"4"}},[t._v("第(4)条\n            ")])],1)])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉓基金已完成投资项目属于情形（一）投资金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉔基金已完成投资项目属于情形（一）投资金额占本基金认缴出资总额的比例：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉕基金已完成投资项目属于情形（二）、（三）、（四）投资金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉖基金已完成投资项目属于情形（二）、（三）、（四）投资金额占本基金认缴出资总额的比例：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉗基金已完成对江苏省外企业投资项目的总投资金额：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉘基金已完成对江苏省外企业投资项目的总投资金额占本基金认缴出资总额的比例：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1)],1),t._v(" "),a("div",{staticClass:"self-check-relation-transaction self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("关联交易")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉙是否关联交易：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉚关联关系说明：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box",staticStyle:{overflow:"auto"}},[a("purvar-upload",{attrs:{"context-path":t.url.upApi}})],1)])],1)],1),t._v(" "),a("div",{staticClass:"self-check-investment-restriction self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("投资限制")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉛是否列入《政府核准的投资项目目录》：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉜属于《产业结构调整指导目录》何种类别：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉝是否列入《市场准入负面清单草案（试点版》：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉞是否属于第13条所列业务：")])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-form-box"},[a("el-radio",{attrs:{label:"1"}},[t._v("是")]),t._v(" "),a("el-radio",{attrs:{label:"2"}},[t._v("否")])],1)])],1)],1),t._v(" "),a("div",{staticClass:"self-check-else self-check-item-box"},[a("div",{staticClass:"self-check-part-name"},[t._v("其他说明")]),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㉟有无其他违反本基金协议的情形：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㊱其他需要说明的情况：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}})])],1),t._v(" "),a("el-row",{attrs:{gutter:0,type:"flex",justify:"center",align:"center"}},[a("el-col",{attrs:{span:6}},[a("div",{staticClass:"self-check-name"},[t._v("㊲自查结论：")])]),t._v(" "),a("el-col",{attrs:{span:18}},[a("div",{staticClass:"self-check-form-box"},[a("input",{staticClass:"self-check-input",attrs:{type:"text"}}),t._v("\n\n            基金管理人盖章：\n          ")])])],1)],1)]),t._v(" "),a("div",{staticClass:"btn-box"},[a("el-button",{on:{click:t.putInfo}},[t._v("提交")]),t._v(" "),a("el-button",{on:{click:t.saveCheckInfo}},[t._v("保存")]),t._v(" "),a("el-button",[t._v("取消")])],1),t._v(" "),t._m(0)])},l=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"explain"},[a("div",{staticClass:"text",attrs:{id:"u5817"}},[a("p",{staticStyle:{"font-size":"18px","line-height":"20px"}},[a("span",{staticStyle:{"font-family":"'Arial Negreta', 'Arial Normal', 'Arial'","font-weight":"700"}},[t._v("说明：")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("1．自查依据：")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（1）《中共中央关于全面深化改革若干重大问题的决定》")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（2）《国务院关于实行市场准入负面清单制度的意见》（国发〔2015〕55号）")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（3）《国家发展改革委、商务部关于印发〈市场准入负面清单草案（试点版）〉的通知》（发改经体〔2016〕442号，如有更新，请依据最新版本）")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（4）《产业结构调整指导目录》（2014修正）（如有更新，请依据最新版本）")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（5）《政府核准的投资项目目录》（2016年本）（如有更新，请依据最新版本）")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（6）《政府投资基金暂行管理办法》（财预〔2015〕210号）")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（7）《江苏省政府投资基金管理办法》")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("（8）《江苏省政府投资基金现代产业并购基金管理办法》")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("2．表格“⑬⑭⑮⑳㉔㉖㉘”一栏，请填写具体计算公式。")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("3．表格“⑲⑳㉓㉔㉕㉖㉗㉘”一栏中的“已完成投资项目”指已签署投资协议的投资金额。")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("4．请提交项目简要说明，应至少包括项目基本情况；如果自查表中“⑱是否属于本基金主投资领域”填写为“否”、“㉙是否关联交易”填写为“是”、“㉝是否列入《市场准入负面清单草案（试点版》”填写为“是”、“㉛是否列入《政府核准的投资项目目录》”填写为“是”、“㉜属于《产业结构调整指导目录》何种类别”填写为“非鼓励类”，满足任意一条，应做出特别说明。")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("5．请将随表报送的项目材料名称填入附件《项目材料清单目录》。")])]),t._v(" "),a("p",{staticStyle:{"font-size":"13px","line-height":"22px"}},[a("span",{staticStyle:{"font-family":"'Arial Normal', 'Arial'","font-weight":"400"}},[t._v("6．请随自查表报送《产业基金已报合规性审查项目情况表》，并加盖公章。")])])])])}],i=a("58b9"),c=(a("698e"),a("e19c")),n={name:"SelfCheckTable",components:{"purvar-upload":i["default"]},data:function(){return{selfCheckInfo:{},enteId:12362,url:c["a"]}},mounted:function(){this.getCheckSelfInfo()},methods:{}},r=n,o=(a("26d7"),a("4ac2")),p=Object(o["a"])(r,s,l,!1,null,"44556a18",null);e["default"]=p.exports},abe8:function(t,e,a){},abf3:function(t,e,a){"use strict";a("eec34")},eec34:function(t,e,a){}}]);