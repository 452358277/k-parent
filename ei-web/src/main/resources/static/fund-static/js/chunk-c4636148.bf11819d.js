(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c4636148"],{5259:function(t,e,a){},"58e7":function(t,e,a){"use strict";a("5259")},bd90:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同名称\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-input",{attrs:{size:"small",maxlength:"20",disabled:!t.isEdit},model:{value:t.dialogInfo.fileTitle,callback:function(e){t.$set(t.dialogInfo,"fileTitle",e)},expression:"dialogInfo.fileTitle"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      合同类别\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},on:{change:t.selectContract},model:{value:t.dialogInfo.fileType,callback:function(e){t.$set(t.dialogInfo,"fileType",e)},expression:"dialogInfo.fileType"}},t._l(t.contractList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1)],1),t._v(" "),"3"!==t.dialogInfo.fileType?a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      协议金额(万元)\n      "),a("span",{staticClass:"mustIn"},[t._v("*")])]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择币种",size:"small"},model:{value:t.dialogInfo.signAmountCurr,callback:function(e){t.$set(t.dialogInfo,"signAmountCurr",e)},expression:"dialogInfo.signAmountCurr"}},t._l(t.currencyList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1),t._v(" "),a("el-input",{staticStyle:{width:"100%","margin-left":"4px"},attrs:{maxlength:"10",autocomplete:"off",size:"small"},model:{value:t.dialogInfo.signAmount,callback:function(e){t.$set(t.dialogInfo,"signAmount",e)},expression:"dialogInfo.signAmount"}})],1),t._v(" "),a("el-col",{staticClass:"col-title",attrs:{span:4}}),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:8}})],1):t._e(),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      重点关注条款\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:t.dialogInfo.majorTerm,callback:function(e){t.$set(t.dialogInfo,"majorTerm",e)},expression:"dialogInfo.majorTerm"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("div",[a("pre",[t._v(t._s(t.dialogInfo.majorTerm))])])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      备注\n    ")]),t._v(" "),t.isEdit?a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("el-input",{attrs:{size:"small",rows:4,type:"textarea",maxlength:"300"},model:{value:t.dialogInfo.remark,callback:function(e){t.$set(t.dialogInfo,"remark",e)},expression:"dialogInfo.remark"}})],1):t._e(),t._v(" "),t.isEdit?t._e():a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("pre",[t._v(t._s(t.dialogInfo.remark))])])],1),t._v(" "),a("el-row",{staticClass:"infoRow",attrs:{type:"flex",align:"center",justify:"center"}},[a("el-col",{staticClass:"col-title",attrs:{span:4}},[t._v("\n      附件\n    ")]),t._v(" "),a("el-col",{staticClass:"col-content",attrs:{span:20}},[a("upLoad",{attrs:{"context-path":t.url.upApi},model:{value:t.dialogInfo.contractFile,callback:function(e){t.$set(t.dialogInfo,"contractFile",e)},expression:"dialogInfo.contractFile"}})],1)],1)],1)},s=[],n=(a("aa18"),a("982e"),a("1bc7"),a("fc02"),a("7562")),o={name:"Index",model:{prop:"value",event:"update-value"},props:{value:{type:Object,default:function(){return{}}},isEdit:{type:Boolean,default:!0}},data:function(){return{contractList:[]}},mounted:function(){this.getParentId(9017,"contractList")},methods:{getParentId:function(t,e){var a=this,l=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(t))){var s=[];if(l){var o=l.split(",");JSON.parse(sessionStorage.getItem("code".concat(t))).forEach((function(t){o.includes(t.value)&&s.push(t)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(t)));this[e]=s}else n["a"].getCodeByParentId({parentId:t}).then((function(s){var n=[];if(l){var o=l.split(",");s.data.data.options.forEach((function(t){o.includes(t.value)&&n.push(t)}))}else n=s.data.data.options;a[e]=n,sessionStorage.setItem("code".concat(t),JSON.stringify(s.data.data.options))}))}}},i=o,c=(a("58e7"),a("4ac2")),r=Object(c["a"])(i,l,s,!1,null,"7c3164a6",null);e["default"]=r.exports}}]);