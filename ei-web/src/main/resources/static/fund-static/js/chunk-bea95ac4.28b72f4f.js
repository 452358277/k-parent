(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-bea95ac4"],{"344b":function(t,e,a){"use strict";e["a"]={getIsReady:function(){return!0},loadFinish:function(){return!0},loadFinishAfter:function(){}}},da74:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[t._v("尽调基本信息")]),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        计划名称\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"20"},model:{value:t.dialogInfo.planName,callback:function(e){t.$set(t.dialogInfo,"planName",e)},expression:"dialogInfo.planName"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        尽调范围\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-checkbox-group",{attrs:{size:"small"},model:{value:t.dialogInfo.surveyContentId,callback:function(e){t.$set(t.dialogInfo,"surveyContentId",e)},expression:"dialogInfo.surveyContentId"}},t._l(t.investigationList,(function(e){return a("el-checkbox",{key:e.value,attrs:{label:e.value}},[t._v(t._s(e.label))])})),1)],1)],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        计划开始时间\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.dialogInfo.startDate,callback:function(e){t.$set(t.dialogInfo,"startDate",e)},expression:"dialogInfo.startDate"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        计划结束时间\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.dialogInfo.endDate,callback:function(e){t.$set(t.dialogInfo,"endDate",e)},expression:"dialogInfo.endDate"}})],1)],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        尽调类型\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",size:"small"},model:{value:t.dialogInfo.interior,callback:function(e){t.$set(t.dialogInfo,"interior",e)},expression:"dialogInfo.interior"}},t._l(t.investigationTypeList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1),t._v(" "),"2"!==t.dialogInfo.interior?a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        尽调小组成员\n        "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("div",[t._v("\n          "+t._s(t.dialogInfo.userName)+"\n          "),a("el-button",{attrs:{size:"small"},on:{click:t.selectPerson}},[t._v("选择成员")])],1)])],1):t._e(),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件说明\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{staticStyle:{width:"100%"},attrs:{size:"small",type:"textarea",rows:4,maxlength:"300"},model:{value:t.dialogInfo.attaDetail,callback:function(e){t.$set(t.dialogInfo,"attaDetail",e)},expression:"dialogInfo.attaDetail"}})],1)],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n        附件\n      ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{model:{value:t.dialogInfo.attaFile,callback:function(e){t.$set(t.dialogInfo,"attaFile",e)},expression:"dialogInfo.attaFile"}})],1)],1)],1),t._v(" "),"1"!==t.dialogInfo.interior?a("div",{staticClass:"dialog-item"},[a("div",{staticClass:"dialog-item-title"},[t._v("尽调机构信息")]),t._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:t.tableData2.data,"header-cell-style":{background:"#eae3d8",color:"#997528",fontSize:"12px",textAlign:"center"},border:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{label:"尽调机构",align:"left","min-width":"230"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",remote:"","reserve-keyword":"",placeholder:"请输入关键词","remote-method":t.searchMethod,loading:t.loading},on:{change:function(a){return t.mechanismValueChange(e)}},model:{value:e.row.orgId,callback:function(a){t.$set(e.row,"orgId",a)},expression:"scope.row.orgId"}},t._l(t.mechanismList,(function(t){return a("el-option",{key:t.orgId,attrs:{label:t.orgName,value:t.orgId}})})),1)]}}],null,!1,2397250616)}),t._v(" "),a("el-table-column",{attrs:{label:"机构类型",align:"center","min-width":"120",prop:"orgTypeName"}}),t._v(" "),a("el-table-column",{attrs:{label:"联系人",align:"center","min-width":"120",prop:"contact"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n          "+t._s(e.row.contact)+"\n        ")]}}],null,!1,1683384573)}),t._v(" "),a("el-table-column",{attrs:{label:"联系方式",align:"center","min-width":"120",prop:"phoneNo"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n          "+t._s(e.row.phoneNo)+"\n        ")]}}],null,!1,4131173024)}),t._v(" "),a("el-table-column",{attrs:{label:"尽调费用(万元)",align:"center","min-width":"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-input",{attrs:{size:"small",placeholder:"填写数字"},model:{value:e.row.money,callback:function(a){t.$set(e.row,"money",a)},expression:"scope.row.money"}})]}}],null,!1,2006141609)}),t._v(" "),a("el-table-column",{attrs:{width:"120",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return t.delMechanism(e)}}},[t._v("删除")])]}}],null,!1,1265422938)},[a("template",{slot:"header"},[a("el-button",{attrs:{size:"small"},on:{click:t.addMechanism}},[t._v("新增")])],1)],2)],1)],1):t._e(),t._v(" "),a("div",{staticClass:"processTitle",attrs:{id:"myDiv"}},[a("div",{staticClass:"bg-purple-quote"},[t._v("审批信息")]),t._v(" "),t.iframeShows?a("iframe",{attrs:{src:t.src,width:"100%",id:"contentFrame_sp",frameborder:"0",scrolling:"no"}}):t._e()]),t._v(" "),"edit"===t.steps||"apply"===t.steps?a("process",{attrs:{"content-path":t.url.urlApi},on:{action:t.action}}):t._e()],1)},s=[],n=(a("aa18"),a("1bc7"),a("8dee"),a("fc02"),a("0817")),l=a("2c2d"),i=a("344b"),r=a("e19c"),c=a("7562"),d={name:"DueDiligenceReportBackProcess",components:{upLoad:l["a"],process:n["a"]},data:function(){return{src:"",queryInfo:{},iframeShows:!1,planId:this.$route.query.planId,url:r["a"],dialogInfo:{},tableData2:{data:[]},investigationList:[],investigationTypeList:[],mechanismList:[],loading:!1,personVisible:"",steps:""}},mounted:function(){i["a"].getIsReady(),i["a"].loadFinish(),i["a"].loadFinishAfter(),this.queryInfo=this.$route.query,this.iframeShows=!0,this.src="".concat(r["a"].processUrl,"action/showListview.action?listViewId=fixflowProcessInfosListView&instId=").concat(this.queryInfo.instId,"&listViewFileName=fixflowProcessInfosListViewFirst&frameInd=").concat(this.queryInfo.frameInd),this.getPageDetail(),this.getParentId(3434,"investigationTypeList"),this.getParentId(4343,"investigationList"),this.steps=this.$route.query.steps},methods:{getPageDetail:function(){var t=this;c["a"].detailInfoUrveyPlan({planId:this.$route.query.bizKey}).then((function(e){if(t.dialogInfo=e.data.data,e.data.data.listSurverCooorg&&e.data.data.listSurverCooorg.length>0){var a=e.data.data.listSurverCooorg.map((function(e){return e.cooOrgModel.partnerorGaid=e.cooOrgModel.orgId,e.cooOrgModel.orgId=e.cooOrgModel.orgName,t.$set(e.cooOrgModel,"money",e.money),e.cooOrgModel.createDt="",e.cooOrgModel.updateDt="",t.$forceUpdate(),e.cooOrgModel}));t.tableData2.data=a}else t.tableData2.data=[];null===t.dialogInfo.surveyContent?t.$set(t.dialogInfo,"surveyContentId",[]):t.$set(t.dialogInfo,"surveyContentId",t.dialogInfo.surveyContent.split(",")),t.dialogInfo.userName=t.dialogInfo.list.map((function(t){return t.planName})).join(),t.dialogInfo.userId=t.dialogInfo.list.map((function(t){return t.planUseId})).join()}))},getParentId:function(t,e){var a=this;sessionStorage.getItem("code".concat(t))?this[e]=JSON.parse(sessionStorage.getItem("code".concat(t))):c["a"].getCodeByParentId({parentId:t}).then((function(o){a[e]=o.data.data.options,sessionStorage.setItem("code".concat(t),JSON.stringify(o.data.data.options))}))},searchMethod:function(t){var e=this;c["a"].cooOrgGetlist({keyWord:t.replace(/\s+/g,"")}).then((function(t){e.mechanismList=t.data.data.list}))},mechanismValueChange:function(t){var e=this,a=t.$index,o=[];if(this.tableData2.data.forEach((function(t){o.push(t.orgId)})),o[a]="",o.includes(this.tableData2.data[a].orgId))return this.$message({offset:150,type:"warning",message:"请勿选择同一尽调机构"}),void(this.tableData2.data[a].orgId="");var s={};this.mechanismList.forEach((function(o,n){o.orgId===t.row.orgId&&(s=o,e.tableData2.data[a].orgTypeName=s.orgTypeName,e.tableData2.data[a].contact=s.contact,e.tableData2.data[a].phoneNo=s.phoneNo,e.tableData2.data[a].partnerorGaid=t.row.orgId)}))},selectPerson:function(){this.personVisible=!0},addMechanism:function(){var t={orgId:"",orgTypeName:"",phoneNo:"",contact:"",money:""};this.tableData2.data.push(t)},action:function(){if(this.dialogInfo.status="1",this.dialogInfo.isStartFlow=!0,this.dialogInfo.list=this.tableData2.data,this.dialogInfo.userIds=this.dialogInfo.userIds?this.dialogInfo.userIds:"",this.dialogInfo.list.forEach((function(t){delete t.createDt,delete t.updateDt})),this.dialogInfo.surveyContent=this.dialogInfo.surveyContentId.join(","),""!==this.dialogInfo.planName&&void 0!==this.dialogInfo.planName&&null!==this.dialogInfo.planName){if("2"===this.dialogInfo.interior){if(0===this.dialogInfo.list.length||void 0===this.dialogInfo.list||null===this.dialogInfo.list)return void this.$message({offset:150,type:"warning",message:"请至少添加一条尽调机构信息"})}else if("1"===this.dialogInfo.interior){if(""===this.dialogInfo.userIds||void 0===this.dialogInfo.userIds||null===this.dialogInfo.userIds)return void this.$message({offset:150,type:"warning",message:"请选择参与尽调人员"})}else{if(""===this.dialogInfo.userIds||void 0===this.dialogInfo.userIds||null===this.dialogInfo.userIds)return void this.$message({offset:150,type:"warning",message:"请选择参与尽调人员"});if(0===this.dialogInfo.list.length||void 0===this.dialogInfo.list||null===this.dialogInfo.list)return void this.$message({offset:150,type:"warning",message:"请至少添加一条尽调机构信息"})}c["a"].saveInfoUrveyPlan(this.dialogInfo).then((function(t){"0"===t.data.status&&t.data.msg}))}else this.$message({offset:150,type:"warning",message:"请填写计划名称"})}}},u=d,f=a("4ac2"),g=Object(f["a"])(u,o,s,!1,null,"45ae010e",null);e["default"]=g.exports}}]);