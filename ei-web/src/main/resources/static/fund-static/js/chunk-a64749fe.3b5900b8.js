(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a64749fe","chunk-0276981e"],{"346a":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      投资人名称\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请输入关键词"},on:{change:e.getProj},model:{value:e.value.investorId,callback:function(t){e.$set(e.value,"investorId",t)},expression:"value.investorId"}},e._l(e.invePersonList,(function(e){return a("el-option",{key:e.investorId,attrs:{label:e.investorName,value:e.investorId}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.investorIdName)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      投资人类型\n    ")]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:e.value.inveType,callback:function(t){e.$set(e.value,"inveType",t)},expression:"value.inveType"}},e._l(e.inveTypeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveTypeName)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      投资角色\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:e.value.inveRole,callback:function(t){e.$set(e.value,"inveRole",t)},expression:"value.inveRole"}},e._l(e.inveRoleList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveRoleName)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      投资人属性\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",size:"small",placeholder:"请选择"},model:{value:e.value.inveNature,callback:function(t){e.$set(e.value,"inveNature",t)},expression:"value.inveNature"}},e._l(e.inveNatureList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveNatureName)+"\n    ")])],1),e._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      认缴金额(万元)\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择",size:"small"},model:{value:e.value.inveCurrency,callback:function(t){e.$set(e.value,"inveCurrency",t)},expression:"value.inveCurrency"}},e._l(e.currencyList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1),e._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:e.value.inveAmount,callback:function(t){e.$set(e.value,"inveAmount",t)},expression:"value.inveAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.inveAmount)+e._s(e.value.inveCurrencyName)+"\n    ")]),e._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[e._v("\n      折算为人民币(万元)\n      "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{maxlength:"10",size:"small"},model:{value:e.value.rmbIntendedAmount,callback:function(t){e.$set(e.value,"rmbIntendedAmount",t)},expression:"value.rmbIntendedAmount"}})],1):e._e(),e._v(" "),e.isEdit?e._e():a("el-col",{staticClass:"col-content",attrs:{span:8}},[e._v("\n      "+e._s(e.value.rmbIntendedAmount)+"\n    ")])],1),e._v(" "),e._e()],1)},l=[],s=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),i={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0}},data:function(){return{loading:!1,invePersonList:[],inveTypeList:[{value:"1",label:"个人"},{value:"2",label:"机构"}],inveRoleList:[{value:"1",label:"LP"},{value:"2",label:"GP"},{value:"3",label:"管理公司"},{value:"4",label:"其他"}],inveNatureList:[{value:"1",label:"中央财政"},{value:"2",label:"国家基金"},{value:"3",label:"省级财政"},{value:"4",label:"省级基金"},{value:"5",label:"市县财政"},{value:"6",label:"国有企业"},{value:"7",label:"国有金融机构"},{value:"8",label:"民营企业"},{value:"9",label:"自然人"}],currencyList:[{value:"1",label:"人民币"},{value:"2",label:"美元"},{value:"3",label:"欧元"}]}},mounted:function(){this.getParentId(888,"inveNatureList"),this.getInvePerson()},methods:{getParentId:function(e,t){var a=this,n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var l=[];if(n){var i=n.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&l.push(e)}))}else l=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=l}else s["a"].getCodeByParentId({parentId:e}).then((function(l){var s=[];if(n){var i=n.split(",");l.data.data.options.forEach((function(e){i.includes(e.value)&&s.push(e)}))}else s=l.data.data.options;a[t]=s,sessionStorage.setItem("code".concat(e),JSON.stringify(l.data.data.options))}))},getInvePerson:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:2e3,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:1;s["a"].fundShareInfoSelectInveInfo({pageSize:t,currPage:a,investorType:"",keyword:""}).then((function(t){"成功"===t.data.msg&&(e.invePersonList=t.data.data)}))},searchProjList:function(e){var t=this;e?(this.loading=!0,s["a"].QCCSearchData({keyword:e}).then((function(e){var a=JSON.parse(e.data.data.value);t.loading=!1,null==a.Result||(t.invePersonList=a.Result.map((function(e){return{value:e.CreditCode,label:e.Name}})))}))):this.loading=!1},getProj:function(e){var t=this;this.invePersonList.forEach((function(a){a.investorId===e&&(t.value.investorName=a.investorName)}))}}},o=i,c=(a("945d"),a("4ac2")),u=Object(c["a"])(o,n,l,!1,null,"05a6522c",null);t["default"]=u.exports},6888:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{background:"#fff","padding-top":"20px","margin-top":"20px"}},["5"===e.fundInfo.projStatus||"15"===e.fundInfo.projStatus||"7"===e.fundInfo.projStatus||"8"===e.fundInfo.projStatus||"9"===e.fundInfo.projStatus?a("div",{staticClass:"btnBox"},[e.buttonList.includes("602040701")?a("button",{on:{click:e.addInfo}},[a("i",{staticClass:"iconfont iconjiahao"}),e._v("\n      新增\n    ")]):e._e(),e._v(" "),e.buttonList.includes("602040702")?a("button",{on:{click:function(t){return e.editInfo(e.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      编辑\n    ")]):e._e(),e._v(" "),e.buttonList.includes("602040703")?a("button",{on:{click:function(t){return e.delInfo(e.selectList)}}},[a("i",{staticClass:"iconfont iconbianji"}),e._v("\n      删除\n    ")]):e._e()]):a("div",{staticStyle:{"margin-top":"20px"}}),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-bottom":"20px"},attrs:{data:e.tableData.data,"header-cell-style":{background:"#FEFAFA",color:"#333",fontWeight:"bold",fontSize:"12px",textAlign:"center"},size:"small",border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{type:"index",label:"序号",align:"center",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"230",prop:"investorIdName",label:"投资人名称"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveTypeName",label:"投资人名称"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveRoleName",label:"投资角色"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"gB",label:"股比%"}}),e._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"200",prop:"userName",label:"认缴金额(万元)"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("money")(t.row.inveAmount))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"inveCurrencyName",label:"币种"}}),e._v(" "),a("el-table-column",{attrs:{align:"right","min-width":"200",prop:"userName",label:"累计出资金额(万元)"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(e._f("money")(t.row.totalFinancial))+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left","min-width":"200",prop:"userName",label:"累计出资币种"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.totalFinCurrencyName)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"schedule",label:"出资进度"}}),e._v(" "),a("el-table-column",{attrs:{align:"left",width:"110",prop:"schedule",label:"更新时间"}})],1),e._v(" "),a("el-pagination",{attrs:{"current-page":e.tableData.currPage,"page-sizes":[5,10,20,30,40,50,60,70],"page-size":e.tableData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.tableData.totalCount},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.submitLoading,expression:"submitLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{title:e.modalTitle,visible:e.dialogVisible,"element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0)",width:"90%"},on:{"update:visible":function(t){e.dialogVisible=t},close:e.dialogClose}},[a("formPage",{attrs:{"is-edit":e.isEdit},model:{value:e.dialogInfo,callback:function(t){e.dialogInfo=t},expression:"dialogInfo"}}),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitDialogInfo("save")}}},[e._v("保 存")])],1)],1)],1)},l=[],s=(a("aa18"),a("982e"),a("fc02"),a("a450"),a("1bc7"),a("7562")),i=a("346a"),o={name:"Index",components:{formPage:i["default"]},data:function(){return{tableData:{data:[],pageSize:5,currPage:1,totalCount:10},modalTitle:"",dialogVisible:!1,dialogInfo:{},contractList:[],currencyList:[],submitLoading:!1,selectList:[],isEdit:!0,buttonList:[]}},mounted:function(){this.getTableList()},methods:{setButtonList:function(){var e=this;this.buttonList=[],this.$route.meta.buttons.forEach((function(t){"基金出资"===t.name&&t.buttons.forEach((function(t){"602040701"===t.code&&e.buttonList.push(t.code),"602040702"===t.code&&e.buttonList.push(t.code),"602040703"===t.code&&e.buttonList.push(t.code)}))}))},getParentId:function(e,t){var a=this,n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var l=[];if(n){var i=n.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){i.includes(e.value)&&l.push(e)}))}else l=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=l}else s["a"].getCodeByParentId({parentId:e}).then((function(l){var s=[];if(n){var i=n.split(",");l.data.data.options.forEach((function(e){i.includes(e.value)&&s.push(e)}))}else s=l.data.data.options;a[t]=s,sessionStorage.setItem("code".concat(e),JSON.stringify(l.data.data.options))}))},getTableList:function(e,t){var a=this;s["a"].selectFundShareInfo({pageSize:e,currPage:t,fundId:this.$route.params.fundId}).then((function(e){a.tableData=e.data}))},addInfo:function(){this.modalTitle="新增出资信息",this.dialogVisible=!0},editInfo:function(){var e=this;1!==this.selectList.length?this.$message({offset:150,type:"warning",message:"请选择一条数据编辑"}):(this.modalTitle="编辑出资信息",s["a"].selectFundShareInfoDetail({pkId:this.selectList[0].pkId}).then((function(t){e.dialogInfo=t.data.data})),this.dialogVisible=!0)},delInfo:function(e){var t=this;if(0===e.length)this.$message({offset:150,type:"warning",message:"请至少选择一条数据删除"});else{var a=this.selectList.map((function(e){return e.pkId})).join(",");s["a"].deleteFundShareInfo({pkId:a}).then((function(e){"0"===e.data.status&&"成功"===e.data.msg&&(t.$message({offset:150,type:"success",message:"删除成功"}),t.getTableList(t.tableData.pageSize,1))}))}},handleSizeChange:function(e){this.getTableList(e,1)},handleCurrentChange:function(e){this.getTableList(this.tableData.pageSize,e)},handleSelectionChange:function(e){this.selectList=e},dialogClose:function(){this.dialogInfo={}},submitDialogInfo:function(e){var t=this;this.dialogInfo.fundId=this.$route.params.fundId,this.dialogInfo.pkId?s["a"].updateFundShareInfo(this.dialogInfo).then((function(e){"0"!==e.data.status&&"成功"!==e.data.msg||(t.$message({offset:150,type:"success",message:"成功编辑"}),t.dialogVisible=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage))})):s["a"].addFundShareInfo(this.dialogInfo).then((function(e){"0"!==e.data.status&&"成功"!==e.data.msg||(t.$message({offset:150,type:"success",message:"成功新增"}),t.dialogVisible=!1,t.getTableList(t.tableData.pageSize,t.tableData.currPage))}))}}},c=o,u=(a("e8ae"),a("4ac2")),r=Object(u["a"])(c,n,l,!1,null,"4ea43930",null);t["default"]=r.exports},"945d":function(e,t,a){"use strict";a("e98b")},bdc4:function(e,t,a){},e8ae:function(e,t,a){"use strict";a("bdc4")},e98b:function(e,t,a){}}]);