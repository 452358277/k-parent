(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-73df8448"],{af8e:function(e,_,r){"use strict";r.r(_);var t=function(){var e=this,_=e.$createElement,r=e._self._c||_;return r("div",{staticClass:"select"},[r("el-select",{staticStyle:{width:"100%"},attrs:{multiple:"",filterable:"",remote:"","reserve-keyword":"",placeholder:e.placeholder,"remote-method":e.remoteMethod,loading:e.loading},on:{change:e.changeTag,"remove-tag":e.removeTag,"visible-change":e.visibleChange},model:{value:e.selectValue,callback:function(_){e.selectValue=_},expression:"selectValue"}},e._l(e.list,(function(e,_){return r("el-option",{key:_,attrs:{label:e.label,value:e.value}})})),1)],1)},a=[],o=r("fee7"),s=o["a"],l=r("4ac2"),n=Object(l["a"])(s,t,a,!1,null,null,null);_["default"]=n.exports},fee7:function(module,__webpack_exports__,__webpack_require__){"use strict";var core_js_modules_es7_symbol_async_iterator__WEBPACK_IMPORTED_MODULE_0__=__webpack_require__("9f60"),core_js_modules_es7_symbol_async_iterator__WEBPACK_IMPORTED_MODULE_0___default=__webpack_require__.n(core_js_modules_es7_symbol_async_iterator__WEBPACK_IMPORTED_MODULE_0__),core_js_modules_es6_symbol__WEBPACK_IMPORTED_MODULE_1__=__webpack_require__("94f0"),core_js_modules_es6_symbol__WEBPACK_IMPORTED_MODULE_1___default=__webpack_require__.n(core_js_modules_es6_symbol__WEBPACK_IMPORTED_MODULE_1__),core_js_modules_es6_string_iterator__WEBPACK_IMPORTED_MODULE_2__=__webpack_require__("0c84"),core_js_modules_es6_string_iterator__WEBPACK_IMPORTED_MODULE_2___default=__webpack_require__.n(core_js_modules_es6_string_iterator__WEBPACK_IMPORTED_MODULE_2__),core_js_modules_es6_array_from__WEBPACK_IMPORTED_MODULE_3__=__webpack_require__("2843"),core_js_modules_es6_array_from__WEBPACK_IMPORTED_MODULE_3___default=__webpack_require__.n(core_js_modules_es6_array_from__WEBPACK_IMPORTED_MODULE_3__),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_4__=__webpack_require__("a450"),core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_4___default=__webpack_require__.n(core_js_modules_es6_function_name__WEBPACK_IMPORTED_MODULE_4__),core_js_modules_es6_regexp_to_string__WEBPACK_IMPORTED_MODULE_5__=__webpack_require__("4057"),core_js_modules_es6_regexp_to_string__WEBPACK_IMPORTED_MODULE_5___default=__webpack_require__.n(core_js_modules_es6_regexp_to_string__WEBPACK_IMPORTED_MODULE_5__),core_js_modules_es6_regexp_replace__WEBPACK_IMPORTED_MODULE_6__=__webpack_require__("8dee"),core_js_modules_es6_regexp_replace__WEBPACK_IMPORTED_MODULE_6___default=__webpack_require__.n(core_js_modules_es6_regexp_replace__WEBPACK_IMPORTED_MODULE_6__),axios__WEBPACK_IMPORTED_MODULE_7__=__webpack_require__("7f43"),axios__WEBPACK_IMPORTED_MODULE_7___default=__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_7__);function _createForOfIteratorHelper(e,_){var r="undefined"!==typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(!r){if(Array.isArray(e)||(r=_unsupportedIterableToArray(e))||_&&e&&"number"===typeof e.length){r&&(e=r);var t=0,a=function(){};return{s:a,n:function(){return t>=e.length?{done:!0}:{done:!1,value:e[t++]}},e:function(e){throw e},f:a}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var o,s=!0,l=!1;return{s:function(){r=r.call(e)},n:function(){var e=r.next();return s=e.done,e},e:function(e){l=!0,o=e},f:function(){try{s||null==r.return||r.return()}finally{if(l)throw o}}}}function _unsupportedIterableToArray(e,_){if(e){if("string"===typeof e)return _arrayLikeToArray(e,_);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?_arrayLikeToArray(e,_):void 0}}function _arrayLikeToArray(e,_){(null==_||_>e.length)&&(_=e.length);for(var r=0,t=new Array(_);r<_;r++)t[r]=e[r];return t}__webpack_exports__["a"]={model:{prop:"value",event:"update-value"},props:{value:[String,Array],placeholder:{type:String,default:"请输入关键词"},contextPath:{type:String,default:"/yhhr-web/"},multiple:{type:Boolean,default:!0}},data:function(){return{selectValue:[],listCopy:[],list:[],selectList:[],loading:!1,states:[],keyWord:"",selectOption:[],hasList:[]}},watch:{value:function(e){var _={},r=e.reduce((function(e,r){return!_[r.value]&&(_[r.value]=e.push(r)),e}),[]);this.list=r}},created:function(){this.list=JSON.parse(JSON.stringify(this.value));var e,_=[],r=_createForOfIteratorHelper(this.value);try{for(r.s();!(e=r.n()).done;){var t=e.value;_.push(t.value)}}catch(a){r.e(a)}finally{r.f()}this.selectValue=_},methods:{changeTag:function(e){this.$forceUpdate();var _=[];if(this.multiple){var r,t=_createForOfIteratorHelper(this.listCopy);try{for(t.s();!(r=t.n()).done;){var a,o=r.value,s=_createForOfIteratorHelper(this.selectValue);try{for(s.s();!(a=s.n()).done;){var l=a.value;o.CreditCode===l&&_.push(o)}}catch(u){s.e(u)}finally{s.f()}}}catch(u){t.e(u)}finally{t.f()}var n=_.map((function(e){var _=e.CreditCode;return""==e.CreditCode&&(_=e.Name),{value:_,label:e.Name}})),i=JSON.parse(JSON.stringify(this.value));return i.push.apply(i,n),void this.$emit("update-value",i)}e.length>1&&this.selectValue.splice(0,1)},removeTag:function(e){for(var _=JSON.parse(JSON.stringify(this.value)),r=0;r<_.length;r++)_[r].value==e&&_.splice(r,1);this.$emit("update-value",_)},remoteMethod:function remoteMethod(query){var _this=this;if(this.keyWord=query,""!==query){this.loading=!0;var api=this.contextPath;axios__WEBPACK_IMPORTED_MODULE_7___default.a.get("".concat(api,"BPI/FUND/QCCSearchData?keyword=").concat(query.replace(/\s+/g,""))).then((function(response){var data=eval("("+response.data.data.value+")"),result=data.Result;null==result?(_this.list=[],_this.loading=!1):(_this.loading=!1,_this.listCopy=result,_this.list=result.map((function(e){var _=e.CreditCode;return""==e.CreditCode&&(_=e.Name),{value:_,label:e.Name}})))})).catch((function(e){console.error(e)}))}else this.list=[]},visibleChange:function(e){}}}}}]);