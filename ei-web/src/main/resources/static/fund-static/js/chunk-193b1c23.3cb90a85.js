(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-193b1c23"],{7379:function(e,t,r){"use strict";var a=r("2e91"),l=r("e19c");t["a"]={adminList:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"admin/list"),params:e})},commonTCodeGetCodeByParentId:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"commonTCode/getCodeByParentId?parentId=").concat(e)})},adminAdd:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"admin/add"),params:e})},adminUpdate:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"admin/update"),params:e})},adminDelete:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"admin/delete?adminIds=").concat(e)})},adminDeleteNew:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"admin/deleteByList"),params:e})},expertList:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"expert/list"),params:e})},expertAdd:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"expert/add"),params:e})},expertUpdate:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"expert/update"),params:e})},expertToEdit:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"expert/toEdit"),params:e})},expertDelete:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"expert/delete?ids=").concat(e)})},adminInvestList:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"adminInvest/list"),params:e})},adminInvestAdd:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"adminInvest/add"),params:e})},adminInvestUpdate:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"adminInvest/update"),params:e})},adminInvestDelete:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"adminInvest/delete?adminIds=").concat(e)})},QCCSearchData:function(e){return Object(a["a"])({methods:"Get",url:"".concat(l["a"].urlApi,"BPI/FUND/QCCSearchData?keyword=").concat(e.keyword),params:e})},QCCDataGetDetailsByName:function(e){return Object(a["a"])({methods:"Get",url:"".concat(l["a"].urlApi,"BPI/FUND/QCCDataGetDetailsByName?keyword=").concat(e.keyword),params:e})},cooOrgList:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"cooOrg/list"),params:e})},cooOrgAdd:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"cooOrg/add"),params:e})},cooOrgUpdate:function(e){return Object(a["a"])({methods:"post",url:"".concat(l["a"].urlApi,"cooOrg/update"),params:e})},cooOrgDelete:function(e){return Object(a["a"])({methods:"get",url:"".concat(l["a"].urlApi,"cooOrg/delete?orgIds=").concat(e)})}}},d395:function(e,t,r){},f0d9:function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"addOrModify"},[r("el-dialog",{attrs:{visible:e.dialogFormVisible,title:e.isAddOrMOdify?"专家库新增":"专家库编辑"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[r("div",{staticClass:"title"}),e._v(" "),r("el-form",{ref:"ruleForm",attrs:{model:e.ruleForm,rules:e.rules}},[r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"姓名",prop:"expertName"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.expertName,callback:function(t){e.$set(e.ruleForm,"expertName",t)},expression:"ruleForm.expertName"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("el-form-item",{attrs:{label:"性别",prop:"sex"}},[r("el-select",{attrs:{placeholder:"请选择",size:"small"},model:{value:e.ruleForm.sex,callback:function(t){e.$set(e.ruleForm,"sex",t)},expression:"ruleForm.sex"}},e._l(e.sexOptions,(function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"出生年月",prop:"birthday"}},[r("el-date-picker",{attrs:{size:"small",type:"date",placeholder:"选择日期","value-format":"yyyy-MM-dd"},model:{value:e.ruleForm.birthday,callback:function(t){e.$set(e.ruleForm,"birthday",t)},expression:"ruleForm.birthday"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple-light"},[r("el-form-item",{attrs:{label:"毕业院校",prop:"university"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.university,callback:function(t){e.$set(e.ruleForm,"university",t)},expression:"ruleForm.university"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"学历",prop:"education"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.education,callback:function(t){e.$set(e.ruleForm,"education",t)},expression:"ruleForm.education"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"专业",prop:"major"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.major,callback:function(t){e.$set(e.ruleForm,"major",t)},expression:"ruleForm.major"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"单位名称",prop:"company"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.company,callback:function(t){e.$set(e.ruleForm,"company",t)},expression:"ruleForm.company"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"职务",prop:"position"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.position,callback:function(t){e.$set(e.ruleForm,"position",t)},expression:"ruleForm.position"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"通讯地址",prop:"contractAddr"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.contractAddr,callback:function(t){e.$set(e.ruleForm,"contractAddr",t)},expression:"ruleForm.contractAddr"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"技术职称",prop:"techTitle"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.techTitle,callback:function(t){e.$set(e.ruleForm,"techTitle",t)},expression:"ruleForm.techTitle"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"单位电话",prop:"companyPhone"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.companyPhone,callback:function(t){e.$set(e.ruleForm,"companyPhone",t)},expression:"ruleForm.companyPhone"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"传真",prop:"fax"}},[r("el-input",{attrs:{type:"number",size:"small"},model:{value:e.ruleForm.fax,callback:function(t){e.$set(e.ruleForm,"fax",t)},expression:"ruleForm.fax"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"E-mail",prop:"email"}},[r("el-input",{attrs:{size:"small"},model:{value:e.ruleForm.email,callback:function(t){e.$set(e.ruleForm,"email",t)},expression:"ruleForm.email"}})],1)],1)]),e._v(" "),r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"手机",prop:"mobilePhone"}},[r("el-input",{attrs:{type:"number",size:"small"},model:{value:e.ruleForm.mobilePhone,callback:function(t){e.$set(e.ruleForm,"mobilePhone",t)},expression:"ruleForm.mobilePhone"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("div",{staticClass:"grid-content bg-purple"},[r("el-form-item",{attrs:{label:"专长领域",prop:"label"}},[r("el-select",{attrs:{multiple:"","collapse-tags":"",placeholder:"请选择",size:"small"},model:{value:e.ruleForm.label,callback:function(t){e.$set(e.ruleForm,"label",t)},expression:"ruleForm.label"}},e._l(e.LabelOptions,(function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple waichureason"},[r("el-form-item",{attrs:{label:"专长领域描述"}},[r("el-input",{attrs:{type:"textarea",rows:"3",cols:"110",size:"small"},model:{value:e.ruleForm.speDetail,callback:function(t){e.$set(e.ruleForm,"speDetail",t)},expression:"ruleForm.speDetail"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple waichureason"},[r("el-form-item",{attrs:{label:"受教育经历"}},[r("el-input",{attrs:{type:"textarea",rows:"3",cols:"110"},model:{value:e.ruleForm.educationDetail,callback:function(t){e.$set(e.ruleForm,"educationDetail",t)},expression:"ruleForm.educationDetail"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple waichureason"},[r("el-form-item",{attrs:{label:"工作履历"}},[r("el-input",{attrs:{type:"textarea",rows:"3",cols:"110"},model:{value:e.ruleForm.workDetail,callback:function(t){e.$set(e.ruleForm,"workDetail",t)},expression:"ruleForm.workDetail"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple waichureason"},[r("el-form-item",{attrs:{label:"主要业绩或研究成果"}},[r("el-input",{attrs:{type:"textarea",rows:"3",cols:"110"},model:{value:e.ruleForm.performance,callback:function(t){e.$set(e.ruleForm,"performance",t)},expression:"ruleForm.performance"}})],1)],1)])],1),e._v(" "),r("el-row",[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"grid-content bg-purple waichureason"},[r("el-form-item",{attrs:{label:"曾参与项目决策或评审"}},[r("el-input",{attrs:{type:"textarea",rows:"3",cols:"110"},model:{value:e.ruleForm.projDetail,callback:function(t){e.$set(e.ruleForm,"projDetail",t)},expression:"ruleForm.projDetail"}})],1)],1)])],1)],1),e._v(" "),r("div",{staticClass:"submit"},[r("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("提交")])],1)],1)],1)},l=[],o=(r("fc02"),r("1bc7"),r("e680"),r("7379")),s={computed:{assetSum:function(){return Number(this.adminAssetDTO.asset1)+Number(this.adminAssetDTO.asset2)+Number(this.adminAssetDTO.asset3)}},watch:{selectedItem:function(e,t){0==e.length?this.isDisable=!0:this.isDisable=!1}},data:function(){return{isDisable:!0,selectedItem:[],isAddOrMOdify:"",dialogFormVisible:!1,formLabelWidth:"120",sexOptions:[],LabelOptions:[{label:"财务",value:1},{label:"法务",value:2},{label:"投资",value:3},{label:"咨询",value:4},{label:"行研",value:5},{label:"政策",value:6}],ruleForm:{expertName:"",sex:"",birthday:"",university:"",education:"",major:"",company:"",position:"",contractAddr:"",techTitle:"",companyPhone:"",fax:"",email:"",mobilePhone:"",label:"",speDetail:"",educationDetail:"",workDetail:"",performance:"",projDetail:""},rules:{expertName:[{required:"true",message:"名称必填",trigger:"blur"}],org:[{required:"true",message:"组织形式必填",trigger:"change"}],regCapital:[{required:"true",message:"注册资本必填",trigger:"blur"}],regDate:[{required:"true",message:"注册时间",trigger:"change"}],regAdd:[{required:"true",message:"注册地址",trigger:"blur"}],regCode:[{required:"true",message:"信用代码/注册号必填",trigger:"blur"}],officeAdd:[{required:"true",message:"办公地址必填",trigger:"blur"}],legal:[{required:"true",message:"法人代表",trigger:"blur"}],contact:[{required:"true",message:"联系人必填",trigger:"blur"}],phoneNo:[{required:"true",message:"联系电话必填",trigger:"blur"}]}}},methods:{handleSelectionChange:function(e){this.selectedItem=e},showDialog:function(e,t){var r=this;if(this.dialogFormVisible=!this.dialogFormVisible,o["a"].commonTCodeGetCodeByParentId(1028).then((function(e){r.LabelOptions=[],e.data.data.options.forEach((function(e){var t={};t.label=e.label,t.value=Number(e.value),r.LabelOptions.push(t)}))})),o["a"].commonTCodeGetCodeByParentId(2603).then((function(e){r.sexOptions=[],e.data.data.options.forEach((function(e){var t={};t.label=e.label,t.value=Number(e.value),r.sexOptions.push(t)}))})),"111"==e){for(var a in this.ruleForm)this.$set(this.ruleForm,a,"");this.$set(this.ruleForm,"label",[]),this.isAddOrMOdify=!0}else if("112"==e){var l=[];t[0].label&&Number(t[0].label.split(",")[0])&&t[0].label.split(",").forEach((function(e){l.push(Number(e))})),this.ruleForm.expertName=t[0].expertName,this.ruleForm.sex=null==t[0].sex?"":Number(t[0].sex),this.ruleForm.birthday=t[0].birthday,this.ruleForm.university=t[0].university,this.ruleForm.education=t[0].education,this.ruleForm.major=t[0].major,this.ruleForm.company=t[0].company,this.ruleForm.position=t[0].position,this.ruleForm.contractAddr=t[0].contractAddr,this.ruleForm.techTitle=t[0].techTitle,this.ruleForm.companyPhone=t[0].companyPhone,this.ruleForm.fax=t[0].fax,this.ruleForm.email=t[0].email,this.ruleForm.mobilePhone=t[0].mobilePhone,this.ruleForm.label=l,this.ruleForm.speDetail=t[0].speDetail,this.ruleForm.educationDetail=t[0].educationDetail,this.ruleForm.workDetail=t[0].workDetail,this.ruleForm.performance=t[0].performance,this.ruleForm.projDetail=t[0].projDetail,this.ruleForm.expertId=t[0].expertId,this.isAddOrMOdify=!1}this.$nextTick((function(){r.$refs.ruleForm.clearValidate()}))},submitForm:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return!1;var r={};r=t.ruleForm,r.label=t.ruleForm.label.join(","),t.isAddOrMOdify?o["a"].expertAdd(r).then((function(e){"0"==e.data.status?t.$message.success("新增成功"):t.$message.warning("接口错误"),t.dialogFormVisible=!t.dialogFormVisible,t.$emit("refresh")})):t.isAddOrMOdify||o["a"].expertUpdate(r).then((function(e){"0"==e.data.status?t.$message.success("更新成功"):t.$message.warning("接口错误"),t.dialogFormVisible=!t.dialogFormVisible,t.$emit("refresh")}))}))}}},i=s,n=(r("feff"),r("4ac2")),u=Object(n["a"])(i,a,l,!1,null,null,null);t["default"]=u.exports},feff:function(e,t,r){"use strict";r("d395")}}]);