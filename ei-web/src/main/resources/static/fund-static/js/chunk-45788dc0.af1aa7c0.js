(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-45788dc0","chunk-2d230643"],{4349:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",[t._v("测试标签页")]),t._v(" "),a("t-value",{on:{sendValue:t.getValue},model:{value:t.title,callback:function(e){t.title=e},expression:"title"}}),t._v(" "),a("div",{on:{click:t.startIn}},[t._v("导入")])],1)},s=[],i=a("eba1"),u=a("698e"),o={name:"Test",components:{"t-value":i["default"]},data:function(){return{title:"测试标题",titleList:[]}},mounted:function(){this.setTagsViewTitle(),this.getProjInfo()},methods:{getProjInfo:function(){var t=this;u["a"].projInfo({projId:this.$route.params.projId}).then((function(e){t.projInfo=e.data.data,"1"===e.data.status&&t.$message({offset:150,type:"warning",message:e.data.msg})}))},setTagsViewTitle:function(){this.tempRoute=Object.assign({},this.$route);var t=Object.assign({},this.tempRoute,{title:"".concat(title)});this.$store.dispatch("tagsView/updateVisitedView",t)},getValue:function(t){},startIn:function(){u["a"]}}},l=o,c=a("4ac2"),r=Object(c["a"])(l,n,s,!1,null,"8ac9de92",null);e["default"]=r.exports},eba1:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{on:{click:t.pushValue}},[t._v(t._s(t.value))])},s=[],i={name:"TestModel",props:["value"],methods:{pushValue:function(){this.$emit("sendValue",this.value)}}},u=i,o=a("4ac2"),l=Object(o["a"])(u,n,s,!1,null,"d8e532d4",null);e["default"]=l.exports}}]);