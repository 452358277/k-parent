(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1081f740","chunk-d6a4d36c"],{"15cf":function(e,t,a){},"2fd3":function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      资金对象\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:e.value.inveRole,callback:function(t){e.$set(e.value,"inveRole",t)},expression:"value.inveRole"}},e._l(e.inveRoleList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}}),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      资金类型\n    ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:e.value.capitalType,callback:function(t){e.$set(e.value,"capitalType",t)},expression:"value.capitalType"}},e._l(e.capitalTypeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.capitalType)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      基金名称\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",remote:"","reserve-keyword":"",placeholder:"请输入关键词","remote-method":e.searchProjList,loading:e.loading},on:{change:e.getProj},model:{value:e.value.createId,callback:function(t){e.$set(e.value,"createId",t)},expression:"value.createId"}},e._l(e.invePersonList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n      "+e._s(e.value.investorIdName)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      企业/个人名称\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"samll",maxlength:"20"},model:{value:e.value.personName,callback:function(t){e.$set(e.value,"personName",t)},expression:"value.personName"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.personName)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),e._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      联系人\n    ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{maxlength:"20",size:"small"},model:{value:e.value.contact,callback:function(t){e.$set(e.value,"contact",t)},expression:"value.contact"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.contact)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      联系方式\n    ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{maxlength:"20",size:"small"},model:{value:e.value.phone,callback:function(t){e.$set(e.value,"phone",t)},expression:"value.phone"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.phone)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      出资时间\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",size:"small",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:e.value.startDate,callback:function(t){e.$set(e.value,"startDate",t)},expression:"value.startDate"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveAmount)+e._s(e.value.inveCurrencyName)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      出资金额(万元)\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择",size:"small"},model:{value:e.value.inveCurrency,callback:function(t){e.$set(e.value,"inveCurrency",t)},expression:"value.inveCurrency"}},e._l(e.currencyList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1),e._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:e.value.inveAmount,callback:function(t){e.$set(e.value,"inveAmount",t)},expression:"value.inveAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.rmbIntendedAmount)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      折算为人民币(万元)\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{maxlength:"18",size:"small"},model:{value:e.value.inveAmount,callback:function(t){e.$set(e.value,"inveAmount",t)},expression:"value.inveAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveAmount)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      支付方式\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择",size:"small"},model:{value:e.value.payType,callback:function(t){e.$set(e.value,"payType",t)},expression:"value.payType"}},e._l(e.payTypeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.rmbIntendedAmount)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{align:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      备注\n    ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{rows:3,size:"small",type:"textarea"},model:{value:e.value.remark,callback:function(t){e.$set(e.value,"remark",t)},expression:"value.remark"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[e._v("\n      "+e._s(e.value.remark)+"\n    ")])],1)],1)},s=[],n=(a("8dee"),a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),i={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0}},data:function(){return{loading:!1,invePersonList:[],payTypeList:[{value:"1",label:"银行转账"},{value:"2",label:"现金"}],inveRoleList:[{value:"1",label:"LP"},{value:"2",label:"GP"},{value:"3",label:"管理公司"},{value:"4",label:"其他"}],currencyList:[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}],capitalTypeList:[{vlaue:"1",label:"出资"},{vlaue:"2",label:"管理费"},{vlaue:"3",label:"退出本金"},{vlaue:"4",label:"退出收益"},{vlaue:"5",label:"补偿款"},{vlaue:"6",label:"往来款"},{vlaue:"7",label:"理财收益"},{vlaue:"8",label:"补贴收益"},{vlaue:"9",label:"其他"}]}},mounted:function(){},methods:{getParentId:function(e,t){var a=this,l=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var s=[];if(l){var i=l.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&s.push(e)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=s}else n["a"].getCodeByParentId({parentId:e}).then((function(s){var n=[];if(l){var i=l.split(",");s.data.data.options.forEach((function(e){i.includes(e.value)&&n.push(e)}))}else n=s.data.data.options;a[t]=n,sessionStorage.setItem("code".concat(e),JSON.stringify(s.data.data.options))}))},searchProjList:function(e){var t=this;e?(this.loading=!0,n["a"].QCCSearchData({keyword:e.replace(/\s+/g,"")}).then((function(e){var a=JSON.parse(e.data.data.value);t.loading=!1,null==a.Result||(t.invePersonList=a.Result.map((function(e){return{value:e.CreditCode,label:e.Name}})))}))):this.loading=!1},getProj:function(e){var t=this;n["a"].QCCDataGetDetailsByName({keyword:e}).then((function(e){var a=JSON.parse(e.data.data.value).Result;return null===a?(t.$message({offset:150,type:"warning",message:"查询无结果，请重新选择合适数据"}),!1):a})).then((function(e){return t.$set(t.value,"socode",e.CreditCode?e.CreditCode:"..."),t.$set(t.value,"code1",e.OrgNo?e.OrgNo:"..."),t.$set(t.value,"people",e.OperName?e.OperName:"..."),t.$set(t.value,"orgCode",e.OrgNo?e.OrgNo:"..."),t.$set(t.value,"date",e.StartDate.substr(0,10)?e.StartDate.substr(0,10):"..."),t.$set(t.value,"address",e.Address?e.Address:"..."),t.$set(t.value,"investorIdName",e.Name?e.Name:"..."),n["a"].saveEntBaseInfo(e)})).then((function(e){"系统异常"!==e.data.msg?(t.value.projObject=e.data.msg,t.value.investorId=e.data.msg):(t.$message({offset:150,type:"warning",message:"网络波动，请重新选择"}),t.value.socode="",t.value.code1="",t.value.people="",t.value.date="",t.value.address="",t.value.investorIdName="")})).catch((function(e){console.error(e)}))}}},o=i,c=(a("cb37"),a("4ac2")),r=Object(c["a"])(o,l,s,!1,null,"02b940e2",null);t["default"]=r.exports},4830:function(e,t,a){"use strict";a("5282")},5282:function(e,t,a){},c45b:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{padding:"20px",background:"#fff","padding-top":"20px","margin-top":"20px"}},[a("div",{staticClass:"btnBox"},[a("button",{on:{click:e.addInfo}},[a("i",{staticClass:"iconfont iconjiahao"}),e._v("\n      新增\n    ")]),e._v(" "),a("button",{on:{click:function(t){return e.editInfo(e.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      编辑\n    ")]),e._v(" "),a("button",{on:{click:function(t){return e.delInfo(e.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      删除\n    ")])]),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"200",prop:"investorIdName",label:"投资人名称"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveTypeName",label:"资金对象"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveRoleName",label:"资金类型"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"GB",label:"发生金额"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("money")(t.row.inveAmount))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"200",prop:"userName",label:"发生币种"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("money")(t.row.inveAmount))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveCurrencyName",label:"人民币金额(万元)"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("money")(t.row.inveAmount))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"schedule",label:"时间"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.tableData.currPage,"page-sizes":[5,10,20,30,40,50,60,70],"page-size":e.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.submitLoading,expression:"submitLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{title:e.modalTitle,visible:e.dialogVisible,"element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0)",width:"90%"},on:{"update:visible":function(t){e.dialogVisible=t},close:e.dialogClose}},[a("formPage",{attrs:{"is-edit":e.isEdit},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}}),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitDialogInfo("save")}}},[e._v("保 存")])],1)],1)],1)},s=[],n=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),i=a("2fd3"),o={name:"Index",components:{formPage:i["default"]},data:function(){return{tableData:{data:[],pageSize:5,currPage:1,totalCount:10},modalTitle:"",dialogVisible:!1,dialogInfo:{},contractList:[],currencyList:[],submitLoading:!1,selectList:[],isEdit:!0}},mounted:function(){},methods:{getParentId:function(e,t){var a=this,l=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var s=[];if(l){var i=l.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&s.push(e)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=s}else n["a"].getCodeByParentId({parentId:e}).then((function(s){var n=[];if(l){var i=l.split(",");s.data.data.options.forEach((function(e){i.includes(e.value)&&n.push(e)}))}else n=s.data.data.options;a[t]=n,sessionStorage.setItem("code".concat(e),JSON.stringify(s.data.data.options))}))},getTableList:function(e,t){var a=this;n["a"].selectFundShareInfo({pageSize:e,currPage:t,fundId:"60007"}).then((function(e){a.tableData=e.data}))},addInfo:function(){this.modalTitle="新增出资信息",this.dialogVisible=!0},editInfo:function(){var e=this;1!==this.selectList.length?this.$message({offset:150,type:"warning",message:"请选择一条数据编辑"}):(this.modalTitle="编辑出资信息",n["a"].selectFundShareInfoDetail({pkId:this.selectList[0].pkId}).then((function(t){e.dialogInfo=t.data.data})),this.dialogVisible=!0)},delInfo:function(e){var t=this;if(0===e.length)this.$message({offset:150,type:"warning",message:"请至少选择一条数据删除"});else{var a=this.selectList.map((function(e){return e.pkId})).join(",");n["a"].deleteFundShareInfo({pkId:a}).then((function(e){"0"===e.data.status&&"成功"===e.data.msg&&(t.$message({offset:150,type:"success",message:"删除成功"}),t.getTableList(t.tableData.pageSize,1))}))}},handleSizeChange:function(e){this.getTableList(e,1)},handleCurrentChange:function(e){this.getTableList(this.tableData.pageSize,e)},handleSelectionChange:function(e){this.selectList=e},dialogClose:function(){this.dialogInfo={}},submitDialogInfo:function(e){var t=this;this.dialogInfo.fundId="60007",this.dialogInfo.pkId?n["a"].updateFundShareInfo(this.dialogInfo).then((function(e){"0"!==e.data.status&&"成功"!==e.data.msg||(t.$message({offset:150,type:"success",message:"成功编辑"}),t.dialogVisible=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage))})):n["a"].addFundShareInfo(this.dialogInfo).then((function(e){"0"!==e.data.status&&"成功"!==e.data.msg||(t.$message({offset:150,type:"success",message:"成功新增"}),t.dialogVisible=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage))}))}}},c=o,r=(a("4830"),a("4ac2")),u=Object(r["a"])(c,l,s,!1,null,"717fb16e",null);t["default"]=u.exports},cb37:function(e,t,a){"use strict";a("15cf")}}]);