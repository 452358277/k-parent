(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-aaa2fd72"],{7379:function(t,e,a){"use strict";var r=a("2e91"),n=a("e19c");e["a"]={adminList:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"admin/list"),params:t})},commonTCodeGetCodeByParentId:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"commonTCode/getCodeByParentId?parentId=").concat(t)})},adminAdd:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"admin/add"),params:t})},adminUpdate:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"admin/update"),params:t})},adminDelete:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"admin/delete?adminIds=").concat(t)})},adminDeleteNew:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"admin/deleteByList"),params:t})},expertList:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"expert/list"),params:t})},expertAdd:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"expert/add"),params:t})},expertUpdate:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"expert/update"),params:t})},expertToEdit:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"expert/toEdit"),params:t})},expertDelete:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"expert/delete?ids=").concat(t)})},adminInvestList:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"adminInvest/list"),params:t})},adminInvestAdd:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"adminInvest/add"),params:t})},adminInvestUpdate:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"adminInvest/update"),params:t})},adminInvestDelete:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"adminInvest/delete?adminIds=").concat(t)})},QCCSearchData:function(t){return Object(r["a"])({methods:"Get",url:"".concat(n["a"].urlApi,"BPI/FUND/QCCSearchData?keyword=").concat(t.keyword),params:t})},QCCDataGetDetailsByName:function(t){return Object(r["a"])({methods:"Get",url:"".concat(n["a"].urlApi,"BPI/FUND/QCCDataGetDetailsByName?keyword=").concat(t.keyword),params:t})},cooOrgList:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"cooOrg/list"),params:t})},cooOrgAdd:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"cooOrg/add"),params:t})},cooOrgUpdate:function(t){return Object(r["a"])({methods:"post",url:"".concat(n["a"].urlApi,"cooOrg/update"),params:t})},cooOrgDelete:function(t){return Object(r["a"])({methods:"get",url:"".concat(n["a"].urlApi,"cooOrg/delete?orgIds=").concat(t)})}}},cc66:function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"table"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:""},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"70"}}),t._v(" "),a("el-table-column",{attrs:{prop:"adminName",label:"名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"orgName",label:"组织形式"}}),t._v(" "),a("el-table-column",{attrs:{prop:"isCooName",label:"是否合作过"}}),t._v(" "),a("el-table-column",{attrs:{prop:"contact",label:"联系人"}}),t._v(" "),a("el-table-column",{attrs:{prop:"phoneNo",label:"联系电话"}}),t._v(" "),a("el-table-column",{attrs:{prop:"email",label:"联系邮箱",width:"130"}}),t._v(" "),a("el-table-column",{attrs:{prop:"messAddress",label:"通讯地址"}}),t._v(" "),a("el-table-column",{attrs:{prop:"regCapital",label:"注册资本(万元)"}}),t._v(" "),a("el-table-column",{attrs:{prop:"regDate",label:"注册时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"regAdd",label:"注册地址"}}),t._v(" "),a("el-table-column",{attrs:{prop:"isFit",label:"是否符合征集"}})],1),t._v(" "),a("div",[a("el-pagination",{attrs:{"current-page":t.queryParams.currPage,background:"",small:"","page-sizes":[10,20,30,40],layout:" prev, pager, next,total ,sizes ,jumper",total:t.totalPage},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)},n=[],o=a("7379"),l={props:{queryParams:{type:Object,required:!0}},data:function(){return{tableData:[],totalPage:0}},mounted:function(){this.getTableList()},methods:{getTableList:function(){var t=this,e={params:this.queryParams};o["a"].adminList(e).then((function(e){t.tableData=e.data.data,t.totalPage=e.data.totalCount,t.$parent.allItem=t.totalPage}))},handleSizeChange:function(t){this.queryParams.pageSize=t,this.getTableList()},handleCurrentChange:function(t){this.queryParams.currPage=t,this.getTableList()},handleSelectionChange:function(t){this.$emit("transferSelectItem",t)}}},c=l,u=a("4ac2"),s=Object(u["a"])(c,r,n,!1,null,null,null);e["default"]=s.exports}}]);