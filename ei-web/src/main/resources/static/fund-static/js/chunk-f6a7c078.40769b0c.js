(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f6a7c078","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4","chunk-a6d221b4"],{"2c2d":function(e,t,i){"use strict";var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"upload-box"},[e.hiddenUpload?i("div",{staticClass:"fileView"},e._l(e.fileLists,(function(t){return i("h3",{key:t.uid},[i("a",{attrs:{href:e.contextPath+"attachController/download?fileId="+t.uid,target:"_blank"}},[e._v(e._s(t.name))])])})),0):e._e(),e._v(" "),e.hiddenUpload?e._e():i("div",{staticClass:"el-upload-box"},[i("el-upload",{ref:"upload",attrs:{limit:e.limit,action:e.updatePath,"auto-upload":!0,name:e.upLoadName,data:e.extraData,"file-list":e.fileLists,accept:e.acceptFormat,"on-success":e.handleSuccess,"on-exceed":e.handleExceed,"on-preview":e.handlePreview,"on-remove":e.handleRemove,"on-error":e.handleError,"on-change":e.handleChange,"before-remove":e.beforeRemove,"before-upload":e.beforeUpload}},[i("el-button",{class:{active:e.hiddenUpload},attrs:{size:e.size,type:"primary"}},[e._v("\n        "+e._s(e.uploadTitle)+"\n      ")]),e._v(" "),e.isShowTip?i("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v(e._s(e.TipTitle))]):e._e()],1)],1)])},n=[],o=(i("fc02"),i("a450"),i("e680"),i("7f43")),s=i.n(o),d={name:"GuluUpload",model:{prop:"value",event:"update-value"},props:{value:[String],isShowTip:{type:Boolean,default:!0},hiddenUpload:{type:Boolean,default:!1},TipTitle:{type:String,default:"最大可上传50M文件"},uploadTitle:{type:String,default:"点击上传"},limit:{type:Number,default:50},extraData:{type:Object,default:function(){return{bizTableName:"",bizTablePk:"",fieldName:""}}},size:{type:String,default:"small"},upLoadName:{type:String,default:"file"},contextPath:{type:String,default:"/ei-web/"},acceptFormat:{type:String,default:".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.rp,.vsd"}},data:function(){return{fileList:[],fileLists:[]}},watch:{value:function(e){null!==e&&void 0!==e&&""!==e?this.getFileData():(this.fileLists=[],this.fileList=[])},hiddenUpload:function(){},fileLists:function(e){}},computed:{updatePath:function(){var e="".concat(this.contextPath,"attachController/upload?fieldToken=").concat(this.value?this.value:"");return e}},created:function(){null!==this.value&&void 0!==this.value&&""!==this.value?this.getFileData():(this.fileLists=[],this.fileList=[])},methods:{getFileData:function(e){var t=this,i=e||this.value;""!=i&&null!=i||(i="-1");var a=this.contextPath+"attachController/getFileList?fieldToken="+i;s()({url:a,method:"get"}).then((function(e){"0"===e.data.status&&e.data.data.fileList&&t.$nextTick((function(){t.fileLists=e.data.data.fileList,e.data.data.fileList.length>0&&(t.fileList=e.data.data.fileList),0===t.fileLists.length&&(t.$emit("update-value",""),this.fileLists=[],this.fileList=[])}))}))},beforeUpload:function(e){},handleChange:function(e,t){var i=e.name.substring(e.name.lastIndexOf(".")+1),a="rp"===i,n="vsd"===i;(a||n)&&(this.$message({offset:150,message:"上传文件不能是 rp 、vsd 格式!",type:"warning"}),this.$refs.upload.clearFiles())},handleExceed:function(e,t){this.$message.warning("当前限制选择 ".concat(e.length," 个文件，本次选择了 ").concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleRemove:function(e,t){var i=this;s()({url:this.contextPath+"attachController/delete?fileId="+e.uid,method:"get"}).then((function(e){"0"===e.data.status&&(0==t.length?(i.$emit("update-value",""),i.fileLists=[],i.fileList=[]):i.fileLists=t)}))},handleError:function(e,t,i){},handleSuccess:function(e,t,i){var a=e.data.fieldToken;void 0==a&&(a=this.value);var n=this.updatePath.split("=");""!=n[1]&&""==a&&(a=n[1]),this.$emit("update-value",a),this.fileLists=i,this.getFileData(a)},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},handlePreview:function(e){window.open(this.contextPath+"attachController/download?fileId="+e.uid)}}},l=d,r=(i("e116"),i("4ac2")),f=Object(r["a"])(l,a,n,!1,null,"e22390a6",null);t["a"]=f.exports},b700:function(e,t,i){"use strict";i.r(t);var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticStyle:{"margin-top":"20px"}},["1"===e.dialogInfo.projStatus&&e.buttonList.includes("602040101")?i("div",{staticClass:"btnBox"},[e.isEdit?e._e():i("button",{on:{click:e.edit}},[e._v("编辑")]),e._v(" "),e.isEdit?i("button",{on:{click:e.save}},[e._v("保存")]):e._e(),e._v(" "),e.isEdit?i("button",{on:{click:e.cancel}},[e._v("取消编辑")]):e._e()]):e._e(),e._v(" "),i("detail",{ref:"detail",attrs:{"is-edit":e.isEdit,detail:!0},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}})],1)},n=[],o=(i("e680"),i("a450"),i("1bc7"),i("2559")),s=(i("fc02"),i("2ed9")),d=i("7562"),l=i("875e"),r={name:"Index",components:{detail:s["default"]},props:{fundInfo:{type:Object,default:function(){return{}}}},data:function(){return{dialogInfo:{},isEdit:!1,buttonList:[]}},watch:{fundInfo:function(e){this.dialogInfo=e}},mounted:function(){this.getFundDetail()},methods:{getFundDetail:function(){var e=this;d["a"].fundInfoDetail({fundId:this.$route.params.fundId,projId:this.$route.params.projId}).then((function(t){var i,a=t.data.data.model;null===a.gp||a.gp.split(",");a.projAppInfo=a.projAppInfo?a.projAppInfo:{},a.entBaseInfoModel=a.entBaseInfoModel?a.entBaseInfoModel:{},a.fundInveDescModel=a.fundInveDescModel?a.fundInveDescModel:{},e.dialogInfo=(i={registerStatus:a.registerStatus,isZhuce:a.registerStatus,currentCurrency:a.currentCurrency,createCode:a.fundName,fundName:a.fundName,groupId:a.groupId,isDirect:a.isDirect,currentCurrencyName:a.currentCurrencyName,isRecord:a.isRecord,recordCode:a.recordCode,shortName:a.shortName,inveId:a.projInfo.inveId,inveName:a.projInfo.inveName,fundStruct:a.fundStruct,fundStructName:a.fundStructName,platProp:a.platProp,platPropName:a.platPropName,subPlatProp:a.subPlatProp,subPlatPropName:a.subPlatPropName,isExcept:a.isExcept,socode:a.entBaseInfoModel.creditCode,address:a.entBaseInfoModel.addressDetails,date:a.entBaseInfoModel.termStart,gpIdList:null===a.gp?[]:a.gp.split(","),gp:a.gp,gpId:a.gpId,closeDt:a.closeDt,mcName:a.mcName,mcId:a.mcId,fundGenre:a.fundGenre,fundGenreName:a.fundGenreName,contact:a.contact,phoneNo:a.phoneNo,contactEmail:a.contactEmail,manDep:a.manDep,manDepName:a.manDepName,appDep:a.appDep,appDepName:a.appDepName,isExit:a.isExit,financeAmount:a.financeAmount,govFundSrc:a.govFundSrc,govFundSrcName:a.govFundSrcName,inveType:a.inveType,inveTypeName:a.inveTypeName,isLead:a.isLead,planAmount:a.planAmount,isFit:a.isFit,currentAmount:a.currentAmount,raiseAmount:a.raiseAmount,inveCurrentAmount:a.projAppInfo.inveCurrentAmount,projStatus:a.projInfo.projStatus,inveRaiseAmount:a.projAppInfo.inveRaiseAmount,inveStartDate:a.fundInveDescModel.inveStartDate,inveEndDate:a.fundInveDescModel.inveEndDate,mgtOrPaid:a.fundInveDescModel.mgtOrPaid,inveFeeRate:a.fundInveDescModel.inveFeeRate,mgtFeeRate:a.fundInveDescModel.mgtFeeRate,paybackFeeRate:a.fundInveDescModel.paybackFeeRate,mgtFeeDesc:a.fundInveDescModel.mgtFeeDesc,durationPeriod:a.fundInveDescModel.durationPeriod,invePeriod:a.fundInveDescModel.invePeriod,isRollOver:a.fundInveDescModel.isRollOver,rollOver:a.fundInveDescModel.rollOver,paybackPeriod:a.fundInveDescModel.paybackPeriod,isPaybackOver:a.fundInveDescModel.isPaybackOver,paybackOver:a.fundInveDescModel.paybackOver,durationDesc:a.fundInveDescModel.durationDesc,hurdleRate:a.fundInveDescModel.hurdleRate,inveStrategy:a.fundInveDescModel.inveStrategy,addPayBackOver:a.fundInveDescModel.addPayBackOver,mcFee:a.fundInveDescModel.mcFee,performanceFee:a.fundInveDescModel.performanceFee,isDurationOver:a.fundInveDescModel.isDurationOver,durationOver:a.fundInveDescModel.durationOver,incomeDistDesc:a.fundInveDescModel.incomeDistDesc,investmentStrategy:a.fundInveDescModel.investmentStrategy},Object(o["a"])(i,"groupId",a.groupId),Object(o["a"])(i,"oldSubPlatProp",a.oldSubPlatProp),Object(o["a"])(i,"oldSubPlatPropName",a.oldSubPlatPropName),Object(o["a"])(i,"inveLimit",a.inveLimit),Object(o["a"])(i,"basis",a.basis),Object(o["a"])(i,"descFile",a.descFile),Object(o["a"])(i,"socialCode",a.entBaseInfoModel.creditCode),Object(o["a"])(i,"regAdd",a.entBaseInfoModel.addressDetails),Object(o["a"])(i,"otherFile",a.otherFile),Object(o["a"])(i,"startDt",a.entBaseInfoModel.termStart),i),1===a.groupId&&e.$set(e.dialogInfo,"registerStatus","2"),e.setButtonList()}))},setButtonList:function(){var e=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(t){"基本信息"===t.name&&t.buttons.forEach((function(t){"602040101"===t.code&&e.buttonList.push(t.code)}))}))},save:function(){var e=this;if(""!==this.dialogInfo.isRecord&&void 0!==this.dialogInfo.isRecord&&null!==this.dialogInfo.isRecord)if("2"!==this.dialogInfo.isRecord||""!==this.dialogInfo.recordCode&&void 0!==this.dialogInfo.recordCode&&null!==this.dialogInfo.recordCode)if(""!==this.dialogInfo.fundName&&void 0!==this.dialogInfo.fundName&&null!==this.dialogInfo.fundName)if(""!==this.dialogInfo.inveId&&void 0!==this.dialogInfo.inveId&&null!==this.dialogInfo.inveId)if(""!==this.dialogInfo.fundStruct&&void 0!==this.dialogInfo.fundStruct&&null!==this.dialogInfo.fundStruct)if("2"===Object(l["b"])("groupId")||""!==this.dialogInfo.oldSubPlatProp&&void 0!==this.dialogInfo.oldSubPlatProp&&null!==this.dialogInfo.oldSubPlatProp)if(""!==this.dialogInfo.mcId&&void 0!==this.dialogInfo.mcId&&null!==this.dialogInfo.mcId){if(!this.dialogInfo.platProp||""===this.dialogInfo.platProp)return this.$message({offset:150,type:"warning",message:"请选择基金属性"}),!1;if("1"!==this.dialogInfo.platProp&&"3"!==this.dialogInfo.platProp||this.dialogInfo.subPlatProp)if(""!==this.dialogInfo.isFit&&void 0!==this.dialogInfo.isFit&&null!==this.dialogInfo.isFit)if(void 0!==this.dialogInfo.financeAmount&&null!==this.dialogInfo.financeAmount&&""!==String(this.dialogInfo.financeAmount).trim()&&isNaN(Number(this.dialogInfo.financeAmount)))this.$message({offset:150,type:"warning",message:"请正确填写省级财政出资金额，格式为数字"});else if(void 0!==this.dialogInfo.planAmount&&null!==this.dialogInfo.planAmount&&""!==String(this.dialogInfo.planAmount).trim()&&isNaN(Number(this.dialogInfo.planAmount)))this.$message({offset:150,type:"warning",message:"请正确填写基金目标规模，格式为数字"});else if(void 0!==this.dialogInfo.currentCurrency&&null!==this.dialogInfo.currentCurrency&&""!==String(this.dialogInfo.currentCurrency).trim())if(void 0!==this.dialogInfo.currentAmount&&null!==this.dialogInfo.currentAmount&&""!==String(this.dialogInfo.currentAmount).trim())if(isNaN(Number(this.dialogInfo.currentAmount)))this.$message({offset:150,type:"warning",message:"请正确填写基金认缴总规模，格式为数字"});else if(void 0!==this.dialogInfo.raiseAmount&&null!==this.dialogInfo.raiseAmount&&""!==String(this.dialogInfo.raiseAmount).trim()&&isNaN(Number(this.dialogInfo.raiseAmount)))this.$message({offset:150,type:"warning",message:"请正确填写基金实缴规模，格式为数字"});else if(void 0!==this.dialogInfo.inveRaiseAmount&&null!==this.dialogInfo.inveRaiseAmount&&""!==String(this.dialogInfo.inveRaiseAmount).trim()&&isNaN(Number(this.dialogInfo.inveRaiseAmount)))this.$message({offset:150,type:"warning",message:"请正确填写其中：基金实缴规模，格式为数字"});else if(Date.parse(this.dialogInfo.inveStartDate)>Date.parse(this.dialogInfo.inveEndDate))this.$message({offset:150,type:"warning",message:"投资期起算日不得晚于投资期截止日"});else if(void 0!==this.dialogInfo.inveCurrentAmount&&null!==this.dialogInfo.inveCurrentAmount&&""!==String(this.dialogInfo.inveCurrentAmount).trim())if(isNaN(Number(this.dialogInfo.inveCurrentAmount)))this.$message({offset:150,type:"warning",message:"请正确填写其中:本基金认缴规模，格式为数字"});else if(void 0!==this.dialogInfo.inveFeeRate&&null!==this.dialogInfo.inveFeeRate&&""!==String(this.dialogInfo.inveFeeRate).trim()&&isNaN(Number(this.dialogInfo.inveFeeRate)))this.$message({offset:150,type:"warning",message:"请正确填写投资期，格式为数字"});else if(void 0!==this.dialogInfo.mgtFeeRate&&null!==this.dialogInfo.mgtFeeRate&&""!==String(this.dialogInfo.mgtFeeRate).trim()&&isNaN(Number(this.dialogInfo.mgtFeeRate)))this.$message({offset:150,type:"warning",message:"请正确填写延长期，格式为数字"});else if(void 0!==this.dialogInfo.paybackFeeRate&&null!==this.dialogInfo.paybackFeeRate&&""!==String(this.dialogInfo.paybackFeeRate).trim()&&isNaN(Number(this.dialogInfo.paybackFeeRate)))this.$message({offset:150,type:"warning",message:"请正确填写退出期，格式为数字"});else if(void 0!==this.dialogInfo.durationPeriod&&null!==this.dialogInfo.durationPeriod&&""!==String(this.dialogInfo.durationPeriod).trim()&&isNaN(Number(this.dialogInfo.durationPeriod)))this.$message({offset:150,type:"warning",message:"请正确填写存续期，格式为数字"});else if(void 0!==this.dialogInfo.invePeriod&&null!==this.dialogInfo.invePeriod&&""!==String(this.dialogInfo.invePeriod).trim()&&isNaN(Number(this.dialogInfo.invePeriod)))this.$message({offset:150,type:"warning",message:"请正确填写投资期，格式为数字"});else if(void 0!==this.dialogInfo.rollOver&&null!==this.dialogInfo.rollOver&&""!==String(this.dialogInfo.rollOver).trim()&&isNaN(Number(this.dialogInfo.rollOver)))this.$message({offset:150,type:"warning",message:"请正确填写投资期延长年数，格式为数字"});else if(void 0!==this.dialogInfo.paybackPeriod&&null!==this.dialogInfo.paybackPeriod&&""!==String(this.dialogInfo.paybackPeriod).trim()&&isNaN(Number(this.dialogInfo.paybackPeriod)))this.$message({offset:150,type:"warning",message:"请正确填写退出期，格式为数字"});else if(void 0!==this.dialogInfo.paybackOver&&null!==this.dialogInfo.paybackOver&&""!==String(this.dialogInfo.paybackOver).trim()&&isNaN(Number(this.dialogInfo.paybackOver)))this.$message({offset:150,type:"warning",message:"请正确填写退出期延长年数，格式为数字"});else if(""!==this.dialogInfo.mgtFeeDesc&&void 0!==this.dialogInfo.mgtFeeDesc&&null!==this.dialogInfo.mgtFeeDesc)if(""!==this.dialogInfo.durationDesc&&void 0!==this.dialogInfo.durationDesc&&null!==this.dialogInfo.durationDesc)if(""!==this.dialogInfo.incomeDistDesc&&void 0!==this.dialogInfo.incomeDistDesc&&null!==this.dialogInfo.incomeDistDesc)if(""!==this.dialogInfo.inveStrategy&&void 0!==this.dialogInfo.inveStrategy&&null!==this.dialogInfo.inveStrategy){if(void 0!==this.dialogInfo.contactEmail&&null!==this.dialogInfo.contactEmail&&""!==String(this.dialogInfo.contactEmail).trim()){if(!1===this.checkEmail(this.dialogInfo.contactEmail))return void this.$message({offset:150,type:"warning",message:"请填写正确的邮箱格式"})}else if(""===String(this.dialogInfo.contactEmail).trim())return void this.$message({offset:150,type:"warning",message:"请勿填写空格"});this.dialogInfo.groupIds&&(this.dialogInfo.groupId=this.dialogInfo.groupIds),this.dialogInfo.fundId=this.$route.params.fundId,this.dialogInfo.projId=this.$route.params.projId,d["a"].saveNewFundFof(this.dialogInfo).then((function(t){"0"===t.data.status&&"成功"===t.data.msg?(e.$message({offset:150,type:"success",message:"修改成功"}),e.isEdit=!1,e.$emit("refreshData")):e.$message({offset:150,type:"warning",message:t.data.msg})}))}else this.$message({offset:150,type:"warning",message:"请填写投资方向及其目标"});else this.$message({offset:150,type:"warning",message:"请填写收益分配方式"});else this.$message({offset:150,type:"warning",message:"请填写存续期介绍"});else this.$message({offset:150,type:"warning",message:"请填写管理费提取方式"});else this.$message({offset:150,type:"warning",message:"请填写其中:本基金认缴规模"});else this.$message({offset:150,type:"warning",message:"请填写基金认缴总规模"});else this.$message({offset:150,type:"warning",message:"请选择基金认缴总规模币种"});else this.$message({offset:150,type:"warning",message:"请选择是否合规性审查"});else this.$message({offset:150,type:"warning",message:"请选择完整基金属性"})}else this.$message({offset:150,type:"warning",message:"请选择基金管理人"});else this.$message({offset:150,type:"warning",message:"请选择基金性质"});else this.$message({offset:150,type:"warning",message:"请选择基金形式"});else this.$message({offset:150,type:"warning",message:"请选择出资主体"});else this.$message({offset:150,type:"warning",message:"请输入/选择基金名称"});else this.$message({offset:150,type:"warning",message:"请填写备案号"});else this.$message({offset:150,type:"warning",message:"请选择基金备案状态"})},checkEmail:function(e){var t=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;return!!t.test(e)},edit:function(){var e=this;this.isEdit=!0,this.$nextTick((function(){e.$refs.detail.setInveFundList(),e.$refs.detail.selectSubPlatProp(e.dialogInfo.platProp),e.$refs.detail.searchInveNameList(e.dialogInfo.inveName),e.$refs.detail.fundManagementPeople(e.dialogInfo.mcName),"4"===e.dialogInfo.oldSubPlatProp?e.$refs.detail.getParentId(258,"oldSubPlatPropList","4"):"2"===e.dialogInfo.oldSubPlatProp?e.$refs.detail.getParentId(258,"oldSubPlatPropList","2"):"5"===e.dialogInfo.oldSubPlatProp&&e.$refs.detail.getParentId(258,"oldSubPlatPropList","5")}))},cancel:function(){this.isEdit=!1,this.$emit("refreshData")}}},f=r,u=i("4ac2"),g=Object(u["a"])(f,a,n,!1,null,"73b74e00",null);t["default"]=g.exports},c059:function(e,t,i){},e116:function(e,t,i){"use strict";i("c059")}}]);