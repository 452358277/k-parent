(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3366b358","chunk-ee68beae"],{"329b":function(e,t,a){e.exports={theme:"#b28147"}},5246:function(e,t,a){"use strict";a("329b")},5476:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"purvar_form"},[a("el-col",[a("el-form",{ref:"formRule",attrs:{model:e.value,rules:e.formRule}},[a("el-row",{staticClass:"removeBorder"},[a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"",prop:""}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:4}},[a("span",[e._v("季度")]),e._v(" "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),e.isEdit?a("el-col",{attrs:{span:20}},[a("el-date-picker",{attrs:{type:"year",size:"small",format:"yyyy",clearable:!1,"value-format":"yyyy",placeholder:"选择年"},model:{value:e.value.year,callback:function(t){e.$set(e.value,"year",t)},expression:"value.year"}}),e._v(" "),a("el-select",{attrs:{size:"small",filterable:"",placeholder:"请选择"},model:{value:e.value.quarter,callback:function(t){e.$set(e.value,"quarter",t)},expression:"value.quarter"}},e._l(e.quarterList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):a("el-col",{attrs:{span:20}},[e._v("\n                "+e._s(e.value.year)+"年  "+e._s(e.quarter(e.value.quarter))+"\n              ")])],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"totalEmployees"}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("员工人数")]),e._v(" "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.totalEmployees,callback:function(t){e.$set(e.value,"totalEmployees",t)},expression:"value.totalEmployees"}}):a("div",[e._v(e._s(e.value.totalEmployees))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"partTime",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("兼职人数")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.partTime,callback:function(t){e.$set(e.value,"partTime",t)},expression:"value.partTime"}}):a("div",[e._v(e._s(e.value.partTime))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"doctoral",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("博士人数")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.doctoral,callback:function(t){e.$set(e.value,"doctoral",t)},expression:"value.doctoral"}}):a("div",[e._v(e._s(e.value.doctoral))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"master",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("硕士人数")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.master,callback:function(t){e.$set(e.value,"master",t)},expression:"value.master"}}):a("div",[e._v(e._s(e.value.master))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"bachelor",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("本科人数")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.bachelor,callback:function(t){e.$set(e.value,"bachelor",t)},expression:"value.bachelor"}}):a("div",[e._v(e._s(e.value.bachelor))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"other",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("其它人员数量")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.other,callback:function(t){e.$set(e.value,"other",t)},expression:"value.other"}}):a("div",[e._v(e._s(e.value.other))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"returnees",rules:{validator:e.checkNumberInt2,trigger:["blur","change"]}}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("海归人数")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.returnees,callback:function(t){e.$set(e.value,"returnees",t)},expression:"value.returnees"}}):a("div",[e._v(e._s(e.value.returnees))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"",prop:"developer"}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:8}},[a("span",[e._v("科研人员")]),e._v(" "),a("span",{staticClass:"mustIn"},[e._v("*")])]),e._v(" "),a("el-col",{attrs:{span:16}},[e.isEdit?a("el-input",{staticStyle:{width:"100%"},attrs:{maxlength:"15",size:"small"},model:{value:e.value.developer,callback:function(t){e.$set(e.value,"developer",t)},expression:"value.developer"}}):a("div",[e._v(e._s(e.value.developer))])],1)],1)],1)],1),e._v(" "),a("el-col",{attrs:{span:24}},[a("el-form-item",{attrs:{label:"",prop:"secrecyLevel"}},[a("el-row",{staticClass:"purvar_form_item"},[a("el-col",{staticClass:"purvar_form_item_title",attrs:{span:4}},[a("span",[e._v("海归人员信息")])]),e._v(" "),a("el-col",{attrs:{span:20}},[e.isEdit?a("el-input",{attrs:{type:"textarea",size:"small",maxlength:"1000",autosize:{minRows:4,maxRows:12},placeholder:"请输入内容"},model:{value:e.value.returneesInfo,callback:function(t){e.$set(e.value,"returneesInfo",t)},expression:"value.returneesInfo"}}):a("pre",[e._v(e._s(e.value.returneesInfo))])],1)],1)],1)],1)],1)],1)],1)],1)},r=[],s=a("cf72"),o=(a("b68b"),a("fc02"),a("e680"),a("61f7"));function n(e,t,a){Object(o["d"])(t)?a():a(new Error("请输入正确的整数"))}function i(e,t,a){t?Object(o["d"])(t)?a():a(new Error("请输入正确的整数")):a()}var u={name:"Index",mixins:[s["a"]],model:{prop:"value",event:"update-value"},props:{size:{type:String,default:"small"},value:{type:Object,default:function(){return{list:[{}]}}},isEdit:{type:Boolean,default:!0},isParent:{type:Boolean,default:!1}},data:function(){return{formRule:{totalEmployees:[{required:!0,message:"请填写员工人数",trigger:["blur","change"]},{validator:n,trigger:["blur","change"]}],developer:[{required:!0,message:"请填写科研人员",trigger:["blur","change"]},{validator:n,trigger:["blur","change"]}]},quarterList:[{value:"01",label:"第一季度"},{value:"02",label:"第二季度"},{value:"03",label:"第三季度"},{value:"04",label:"第四季度"}],loading:!1}},computed:{},mounted:function(){this.initPage()},methods:{initPage:function(){},checkNumberInt:function(e,t,a){n(e,t,a)},checkNumberInt2:function(e,t,a){i(e,t,a)},checkValue:function(){var e;return this.$refs["formRule"].validate((function(t){return t?(e=!0,!0):(console.log("error submit!!"),e=!1,!1)})),e},clearValidate:function(){this.$refs.formRule.clearValidate()},quarter:function(e){return"01"===e?"第一季度":"02"===e?"第二季度":"03"===e?"第三季度":"04"===e?"第四季度":void 0}}},c=u,v=(a("5246"),a("4ac2")),p=Object(v["a"])(c,l,r,!1,null,null,null);t["default"]=p.exports},b68b:function(e,t,a){"use strict";a.d(t,"b",(function(){return r})),a.d(t,"a",(function(){return s})),a.d(t,"c",(function(){return o}));var l=a("5d16");function r(e){return Object(l["a"])({url:"/BPI/FUND/QCCSearchData",method:"get",params:e})}function s(e){return Object(l["a"])({url:"/BPI/FUND/QCCDataGetDetailsByName",method:"get",params:e})}function o(e){return Object(l["a"])({url:"/BPI/FUND/saveEntBaseInfo",method:"post",data:e})}},cf72:function(e,t,a){"use strict";a("aa18"),a("982e"),a("1bc7"),a("fc02");var l=a("5d16");a("3269"),a("d0f2");function r(e){return Object(l["a"])({url:"/commonTCode/getCodeByParentId",method:"get",params:{parentId:e}})}t["a"]={data:function(){return{headerStyle:{background:"#f8f8f8",color:"#666"}}},methods:{getParentId:function(e,t){var a=this,l=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;if(sessionStorage.getItem("code".concat(e))){var s=[];if(l){var o=l.split(",");JSON.parse(sessionStorage.getItem("code".concat(e))).forEach((function(e){o.includes(e.value)&&s.push(e)}))}else s=JSON.parse(sessionStorage.getItem("code".concat(e)));this[t]=s}else r(e).then((function(r){var s=[];if(l){var o=l.split(",");console.log(r),r.data.options.forEach((function(e){o.includes(e.value)&&s.push(e)}))}else s=r.data.options;a[t]=s,sessionStorage.setItem("code".concat(e),JSON.stringify(r.data.options))}))},getParentName:function(e,t){var a=[],l="";if(sessionStorage.getItem("code".concat(e)))return a=JSON.parse(sessionStorage.getItem("code".concat(e))),a.forEach((function(e){e.value===t&&(l=e.label)})),l;r(e).then((function(r){sessionStorage.setItem("code".concat(e),JSON.stringify(r.data.options)),a=r.data.options,a.forEach((function(e){e.value===t&&(l=e.label)}))}))}}}}}]);