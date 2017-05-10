// { "framework": "Weex" }
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	var __weex_template__ = __webpack_require__(1)
	var __weex_style__ = __webpack_require__(2)
	var __weex_script__ = __webpack_require__(3)

	__weex_define__('@weex-component/348514ad39a5341753d31c425a7e6643', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/348514ad39a5341753d31c425a7e6643',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "children": [
	    {
	      "type": "div",
	      "children": [
	        {
	          "type": "text",
	          "classList": [
	            "bigText"
	          ],
	          "attr": {
	            "vfor": "item in lists",
	            "value": "测试代码"
	          }
	        },
	        {
	          "type": "text",
	          "attr": {
	            "vIf": "false",
	            "value": "aaaaaaa宝贝宝贝"
	          }
	        },
	        {
	          "type": "list",
	          "classList": [
	            "list"
	          ],
	          "events": {
	            "click": "fetch"
	          },
	          "attr": {
	            "loadmoreoffset": "10"
	          },
	          "children": [
	            {
	              "type": "cell",
	              "append": "tree",
	              "classList": [
	                "cell"
	              ],
	              "attr": {
	                "v-For": "num in lists"
	              },
	              "children": [
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": "aaa"
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                },
	                {
	                  "type": "div",
	                  "classList": [
	                    "panel"
	                  ],
	                  "children": [
	                    {
	                      "type": "text",
	                      "classList": [
	                        "text"
	                      ],
	                      "attr": {
	                        "value": function () {return 'aaa ' + (this.num)}
	                      }
	                    }
	                  ]
	                }
	              ]
	            }
	          ]
	        }
	      ]
	    }
	  ]
	}

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = {
	  "bigText": {
	    "fontSize": 16
	  },
	  "panel": {
	    "width": 600,
	    "height": 250,
	    "marginLeft": 75,
	    "marginTop": 35,
	    "marginBottom": 35,
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "rgb(162,217,192)",
	    "backgroundColor": "rgba(162,217,192,0.2)"
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = function(module, exports, __weex_require__){"use strict";

	var _stringify = __webpack_require__(4);

	var _stringify2 = _interopRequireDefault(_stringify);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var modal = __weex_require__("@weex-module/modal");
	var LOADMORE_COUNT = 4;
	module.exports = {
		data: function () {return {
			confirmData: "click",
			ajaxResponse: "before get json",
			lists: [1, 2, 3, 4, 5, 6, 7, 8, 9]
		}},
		methods: {
			fetch: function fetch(event) {
				var _this = this;

				modal.toast({ message: 'loadmore', duration: 1 });
				setTimeout(function () {
					var length = _this.lists.length;
					for (var i = length; i < length + LOADMORE_COUNT; ++i) {
						_this.lists.push(i + 1);
					}
				}, 800);
			},

			toast: function toast() {
				modal.toast({ 'message': 'I am toast!', 'duration': 3 });
			},
			comfirmClick: function comfirmClick() {
				var content = "this is content";
				var okTilte = "OK";
				var cencle = "CENCLE";
				var self = this;
				modal.confirm({
					message: content,
					okTitle: okTilte,
					cancleTitle: cencle
				}, function (result) {
					self.confirmData = (0, _stringify2.default)(result);
				});
			},
			getJsonData: function getJsonData() {
				var url = "http://1.nativeapp.sinaapp.com/jsons/slideImage.json";
				var self = this;
				stream.fetch({
					method: "GET",
					url: url,
					type: "json"
				}, function (data) {
					self.ajaxResponse = (0, _stringify2.default)(data);
				});
			}
		}
	};}
	/* generated by weex-loader */


/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = { "default": __webpack_require__(5), __esModule: true };

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	var core  = __webpack_require__(6)
	  , $JSON = core.JSON || (core.JSON = {stringify: JSON.stringify});
	module.exports = function stringify(it){ // eslint-disable-line no-unused-vars
	  return $JSON.stringify.apply($JSON, arguments);
	};

/***/ },
/* 6 */
/***/ function(module, exports) {

	var core = module.exports = {version: '2.4.0'};
	if(typeof __e == 'number')__e = core; // eslint-disable-line no-undef

/***/ }
/******/ ]);