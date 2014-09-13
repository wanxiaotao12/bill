/**
 * Created by .
 * User: hujunliang
 * Date: 14-3-3
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 *
 * @param formId
 * @param customRules
 * @param options
 * @param msgTipOptions
 * @param ajaxRequesterOptions
 */

(function($) {
    var namespace = $.jdns("com.jd.core.widget");

    var Formatter = com.jd.core.Formatter;
    var Validator = com.jd.core.Validator;
    var AjaxRequester = com.jd.core.AjaxRequester;

    /**
     * 表单校验器
     *
     * 表单元素上扩展的属性:
     *  1) validateRules: 定义表单元素所使用的校验规则，多个校验规则以|分割
     *      格式为：validateRules="required|minLen[10]"
     *  2) errorMessages: 用于覆盖系统默认消息提示的扩展属性, 多个以|分割。
     *      默认消息提示参考FormValidator.errorMessages对象
     *      格式为：errorMessages="required[姓名不能为空]|minLen[姓名最大长度不能超过10个字符]"
     *  3) validateGroup: 用于划分表单元素组， 使用name查询表单元素时只能获取同一个validateGroup
     *      内的同名元素，如果为找到该属性，查找范围将扩展到Form表单范围内
     *      格式为：validateGroup="groupName"
     *
     * 注：
     *  1) 在使用该组件式，确保每一个的表单域ID唯一；
     *  2) 非特殊情况（一对多关系存在表单中）,除checkbox, radio外，保持每个表单域的name唯一
     *  3) 如果出现2中的特殊情况需要对多关系中的表单域划分组， 即用validateGroup标识区分
     *
     * @param formId {String} 要校验的表单ID
     * @param customRules {Object} [option] 自定义校验规则。如果系统提供的校验规则不能满足需求，
     *      可以自定义扩展校验规则，具体格式见FormValidator.rules
     * @param options {Object} [option] 可选项，具体见FormValidator.defaultOptions
     * @param msgTipOptions {Object} [option] 消息提示框相关可选项, 具体见MessageTooltip.defaultOptions
     * @param ajaxRequesterOptions {Object} [option] ajax异步请求相关可选项, 如果同步请求提交表单
     *      或异步提交时指定了options.ajaxRequester，则可以忽略此参数。具体见AjaxRequester.defaultOptions
     */
    var FormValidator = namespace.FormValidator
            = function(formId, customRules, options, msgTipOptions, ajaxRequesterOptions) {
        this.formId = formId;
        this.jqForm = $("#" + formId);

        this.allRules = $.extend(true, {},
                FormValidator.rules, customRules);
        this.options = $.extend(true, {},
                FormValidator.defaultOptions, options);
        this.stopOnError = this.options.stopWhileFieldError;

        this.msgTipOptions = $.extend(true, {},
                FormValidator.defaultMsgTipOptions, msgTipOptions);

        this.ajaxOptions = ajaxRequesterOptions || {};

        if (this.jqForm.length > 0) {
            this._initialize();
        }
    }

    FormValidator.prototype = {
        constructor: FormValidator,

        _initialize : function() {
            this._bindFormEvent();
            this._bindFieldEvent();
        },

        _bindFormEvent: function() {
            var oThis = this;
            this.jqForm.unbind("submit").
                    bind("submit", function() {
                return oThis.options.sync && oThis.validateForm();
            });

            if (!Validator.isEmpty(this.options.submitBtnId)) {
                $("#" + this.options.submitBtnId).unbind("click").
                        bind("click", function() {
                    if (oThis.options.sync) {
                        $(oThis.jqForm).submit();
                    } else {
                        oThis.sendAjaxRequest();
                    }
                });
            }

            if (!Validator.isEmpty(this.options.resetBtnId)) {
                var jqResetBtn = $("#" + this.options.resetBtnId);
                if (!jqResetBtn.is("input[type=reset]")) {
                    jqResetBtn.unbind("click").bind("click", function() {
                        oThis.resetForm();
                    })
                }
            }
        },

        _bindFieldEvent: function() {
            var oThis = this;
            this.jqForm.find("[validateRules]").each(function() {
                var jqField = $(this);
                if (jqField.is(":checkbox")
                        || jqField.is(":radio")) {
                    jqField.unbind("click").click(function() {
                        var jqElements = $.jdGetJqField(
                                jqField.attr("name"),
                                oThis._findContext(jqField));
                        if (!jqField.is(":checked")) {
                            oThis.validateField(jqElements);
                        } else {
                            oThis._closeMessageToolTip(jqElements);
                        }
                    })
                } else if (jqField.is("select")) {
                    jqField.unbind("change").change(function() {
                        oThis._closeMessageToolTip(jqField);
                        oThis.validateField(jqField);
                    })
                } else {
                    jqField.unbind("blur").blur(function() {
                        oThis.validateField(jqField);
                    })

                    jqField.unbind("focus").focus(function() {
                        oThis._closeMessageToolTip(jqField);
                    })
                }
            });
        },

        _findContext: function(jqField, defaultContext) {
            if (!defaultContext) {
                defaultContext = this.jqForm;
            }
            var context = jqField.closest("[validateGroup]");
            return context.length > 0 ? context : defaultContext;
        },

        _closeMessageToolTip : function(jqField/* jQuery */) {
            if (!jqField || jqField.length == 0) {
                return;
            }
            var messageToolTip = jqField.last().data("messageToolTip");
            if (messageToolTip) {
                messageToolTip.close();
            }
        },

        /**
         * 异步提交表单
         */
        sendAjaxRequest: function() {
            if (this.validateForm()) {//校验成功时
                //当为异步提交表单时，可以指定自定义的AjaxRequester,如果没有指定则
                //系统会自动根据自定义的ajaxOptions与表单action和表单数据来创建异步请求器

                //根据form标签上的url, param, method以及自定义的ajaxOptions构建AjaxRequester
                var url = $("#" + this.formId).attr("action");
                var param = $("#" + this.formId).serialize();

                if (!(this.options.ajaxRequester
                        instanceof AjaxRequester)) {
                    this.options.ajaxRequester =
                            new AjaxRequester(url, param, this.ajaxOpions);

                } else {
                    this.options.ajaxRequester.setOptions({
                        url : url,
                        data : param
                    })
                }

                this.options.ajaxRequester.send();
            }
        },

        resetForm: function() {
            var oThis = this;

            var rselectTextarea = /^(?:select|textarea)/i;
            var rinput = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week|radio|checkbox)$/i;

            $("#" + this.formId).map(function() {
                    return this.elements ? $.makeArray(this.elements) : this;
                }).filter(function() {
                    return this.name && !this.disabled &&
                         ( rselectTextarea.test(this.nodeName) ||
                                 rinput.test(this.type) );
                }).each(function() {
                    var jqField = $(this);

                    if (jqField.is(":radio") || jqField.is(":checkbox")) {
                        this.checked = false;
                    } else if (this.nodeName == "select") {
                        this.selectedIndex = 0;
                    } else {
                        $(this).val("");
                    }

                    oThis._closeMessageToolTip(jqField);
            });
        },

        validateForm : function() {
            var oThis = this;
            var uniqueNames = [];

            var hasError = false;
            $("#" + this.formId).find(
                    "[validateRules]").each(function() {

                var jqField = $(this);
                var fieldName = jqField.attr("name");
                if (!$.jdInArray(uniqueNames, fieldName)) {

                    uniqueNames.push(fieldName);
                    var jqElements = jqField;
                    if (jqField.is(":checkbox")
                            || jqField.is(":radio")) {
                        jqElements = $.jdGetJqField(fieldName,
                                oThis._findContext(jqField));
                    }

                    var errorMessages = oThis.validateField(jqElements);
                    if (errorMessages && errorMessages.length > 0) {
                        hasError = true;
                    }
                }
            });

            if (Validator.isFunction(this.options.validateCallback)) {
                var rst = this.options.validateCallback(oThis);
                if (!rst) {
                    hasError = true;
                }
            }

            return !hasError;
        },

        /**
         * 表单域规则校验方法
         *
         * @param jqField  表单域对应的jQuery对象
         * @return {Array} 存放错误消息提示文本的数组。
         *      如果校验成功，返回null或空数组
         */
        validateField : function(jqField/* jQuery */) {
            if (jqField.length == 0) {
                return null;
            }

            // 校验规则使用扩展属性validate来提供， 其值是由|链接的多个校验规则
            // 具体格式如：validate=required|min[20]|dateRange[2010/01/01,2014/03/04]
            var validateRules = jqField.attr("validateRules");  // TODO Jame 考虑复选框和单选框
            if (Validator.isEmpty(validateRules)
                    || !Validator.isString(validateRules)) {
                return null;
            }

            validateRules = validateRules.split(/\s*\|\s*/);
            var errorMessages = jqField.attr("errorMessages");  // TODO Jame 考虑复选框和单选框

            var fieldErrors = new Array();
            for (var i = 0; i < validateRules.length; i ++) {
                var fieldError = this._doValidateField(
                        jqField, validateRules[i], errorMessages);
                if (!Validator.isEmpty(fieldError)) {
                    fieldErrors.push(fieldError);
                    if (this.stopOnError) {
                        break;
                    }
                }
            }

            if (fieldErrors.length > 0) {
            	if (this.options.showErrorMsg) {
            		this.options.showErrorMsg(jqField.last(), fieldErrors.join(";"));
            	} else {
            		this.showErrorMsg(jqField.last(), fieldErrors.join(";"));
            	}
            }

            return fieldErrors;
        },

        /**
         *
         * @param jqField 表单域对应的jQuery对象
         * @param validateRule
         * @param customRules
         */
        _doValidateField : function(jqField/* jQuery */, validateRule/* String */, errorMessages/* String */) {
            if (Validator.isEmpty(jqField)
                    || Validator.isEmpty(validateRule)) {
                return null;
            }

            var fieldValue = this._getFieldValue(jqField);

            var validateRules = validateRule.split(/\s*\[\s*|\s*,\s*|\s*\]\s*/);

            var ruleName = validateRules.shift();
            var ruleMethod = this.allRules[ruleName];
            if (Validator.isFunction(ruleMethod)) {
                validateRules.unshift(fieldValue);
                if (ruleName == "required"
                        || !Validator.isEmpty(fieldValue)) {
                    var valid = ruleMethod.apply(
                            FormValidator.rules, validateRules);
                    if (!valid) {
                        return this._getErrorMsg(errorMessages, ruleName, validateRules)
                    }
                }
            }

            return null;
        },

        showErrorMsg : function(jqField/* jQuery */, errorMsg/* String */) {
            var customMsgTipOptions = $.extend(
                    true, {}, this.msgTipOptions);

            customMsgTipOptions.element = jqField;

            var messageToolTip = jqField.data("messageToolTip");
            if (messageToolTip) {
                messageToolTip.setMessage(errorMsg);
            } else {
                messageToolTip = new com.jd.core.widget.
                	MessageTooltip(errorMsg, customMsgTipOptions);
            }

            messageToolTip.open();

            jqField.data("messageToolTip", messageToolTip);
        },
        
        closeErrorMsgs: function() {
        	this.jqForm.find("[validateRules]").each(function() {
                var jqField = $(this);
                var messageToolTip = jqField.data("messageToolTip");
                if (messageToolTip) {
                	messageToolTip.close();
                }
        	});
        },

        _getErrorMsg : function(errorMessages/* String */, 
        		ruleName/* String */, formatArgs/* Array */) {
            if (Validator.isEmpty(ruleName)) {
                return null;
            }

            var errorMsg = null;

            var errorMsgRegExp = new RegExp(ruleName + "\\[(.*)\\]");
            var matchResult = errorMsgRegExp.exec(errorMessages);
            if (matchResult && matchResult.length > 1) {
                errorMsg = matchResult[1];
            }

            return FormValidator.errorMessages.
                    _getErrorMsg(errorMsg, ruleName, formatArgs);
        },

        _getFieldValue : function(jqField/* jQuery */) {
            var fieldValue = null;

            var fieldType = jqField.prop("type");
            switch (fieldType) {
                case "radio":
                case "checkbox":
                    if (jqField.is("input:checked")) {
                        fieldValue = jqField.filter(":checked").val();
                    }
                    break;
                case "text":
                case "password":
                case "textarea":
                case "file":
                case "select-one":
                case "select-multiple":
                    fieldValue = jqField.val();
                    break;
                default:
                    fieldValue = this.options.
                            defaultGetFieldValue(jqField);
                    break;

            }

            return fieldValue;
        }
    }

    FormValidator.defaultOptions = {
        submitBtnId : null,
        resetBtnId : null,
        sync : true,
        ajaxRequester : null, //sync为false时， 可以指定自定义的异步请求器
        showErrorMsg : null, // function(jqField, errorMsg){}//是否显示错误提示
        stopWhileFieldError : true, //当表单域上出现校验错误时是否短路该字段上的剩余校验
        defaultGetFieldValue : function(jqField) {
            return jqField.getVal();
        },

        validateCallback : function(formValidator) {
            return true;
        }
    }

    FormValidator.errorMessages = {
        required : "此项必填项",
        minSize : "长度应大于等于${1}",
        maxSize : "长度应小于等于${1}",
        lengthRange : "长度应在${1}至${2}之间" ,
        min : "值应大于等于${1}",
        max : "值应小于等于${1}",
        numberRange : "值应在${1}至${2}之间",
        dateRange : "日期应在${1}至${2}之间",
        dateTimeRange : "时间应在${1}至${2}之间",
        equals : "值应等于{1}",
        idCard : "身份证格式错误",
        phone : "电话格式错误",
        mobilePhone : "手机号格式错误",
        email : "邮箱格式错误",
        integer : "应该填写整型数字",
        number : "应该填写数值类型",
        date : "日期格式不正确",
        dateTime : "时间格式不正确",
        ipv4 : "IP格式错误",
        url : "URL格式错误",
        postcode : "邮编格式错误",

        _getErrorMsg: function(errorMsg, rule, args) {
            errorMsg = errorMsg || this[rule];
            if (!Validator.isNull(errorMsg)) {
                return Formatter.format(args, errorMsg);
            }

            return null;
        }
    }

    FormValidator.rules = {
        /**
         * 判断指定的值是否为非空(undefined,null,空字符串)
         *
         * @param value {String} 指定的用于判空的对象
         * @return {boolean} 如果指定的值对象是非空,返回true,否则返回false
         */
        /* Boolean */
        required : function(value/* String */) {
            return !Validator.isEmpty(value);
        },

        /**
         * 判定指定字符串的长度是否大于较小长度值
         *
         * @param value {String} 指定的用于判定较长度的字符串, 如果value不是字符串类型, 将返回false
         * @param minLen {String} 用于比较的较小长度, 如果minLen不为且不能转换为数值类型且
         *              不能标识表单域的ID或name时,将返回false
         * @return {boolean} 如果指定字符串长度大于较小长度返回true,否则返回false
         */
        /* Boolean */
        minSize : function(value/* String */, minLen/* String */) {
            if (!Validator.isString(value)) {
                return false;
            }

            // 如果指定的minLen不为数值类型，则试图将其作为ID或name获取对应表单域的值
            if (!Validator.asNumber(minLen)) {
                minLen = $.jdGetFieldValue(minLen);
            }

            if (!Validator.asNumber(minLen)) {
                return false;
            }

            if (Validator.isString(minLen)) {
                minLen = parseInt(minLen);
            }

            return $.trim(value).length >= minLen;
        },

        /**
         * 判定指定字符串的长度是否小于较大长度值
         *
         * @param value {String} 指定的用于判定较长度的字符串, 如果value不是字符串类型,将返回false
         * @param maxLen {String} 用于比较的较大长度, 如果maxLen不为且不能转换为数值类型,将返回false
         * @return {boolean} 如果指定字符串长度小于较小长度返回true,否则返回false
         */
        /* Boolean */
        maxSize : function(value/* String */, maxLen/* String */) {
            if (!Validator.isString(value)) {
                return false;
            }

            // 如果指定的maxLen不为数值类型，则试图将其作为ID或name获取对应表单域的值
            if (!Validator.asNumber(maxLen)) {
                maxLen = $.jdGetFieldValue(maxLen);
            }

            if (!Validator.asNumber(maxLen)) {
                return false;
            }

            if (Validator.isString(maxLen)) {
                maxLen = parseInt(maxLen);
            }

            return $.trim(value).length <= maxLen;
        },

        /**
         *
         * @param value
         * @param minLen
         * @param maxLen
         */
        /* Boolean */
        lengthRange : function(value/* String */, minLen/* String */, maxLen/* String */) {
            return this.minSize(value, minLen)
                    && this.maxSize(value, maxLen);
        },

        /**
         * 数字类型值比较，当前值大于指定的较小值时返回true, 否则返回false
         *
         * @param value {String} 指定的当前值，如果value不为且不能转换为数值类型,将返回false
         * @param min {String} 指定的较小值，如果min不为且不能转换为数值类型,将返回false
         * @return {boolean} 如果指定当前值大于较小值返回true, 否则返回false
         */
        /* Boolean */
        min : function(value/* String */, min/* String */) {
            if (!Validator.asNumber(value)) {
                return false;
            }

            // 如果指定的min不为数值类型，则试图将其作为ID或name获取对应表单域的值
            if (!Validator.asNumber(min)) {
                min = $.jdGetFieldValue(min);
            }

            if (!Validator.asNumber(min)) {
                return false;
            }

            if (Validator.isString(value)) {
                value = parseInt(value);
            }

            if (Validator.isString(min)) {
                min = parseInt(min);
            }

            return value >= min;
        },

        /**
         * 数字类型值比较，当前值小于指定的较大值时返回true, 否则返回false
         *
         * @param value 指定的当前值，如果value不为且不能转换为数值类型,将返回false
         * @param max 指定的较大值，如果min不为且不能转换为数值类型,将返回false
         * @return 如果指定当前值小于较大值返回true, 否则返回false
         */
        /* Boolean */
        max : function(value/* String */, max/* String */) {
            if (!Validator.asNumber(value)) {
                return false;
            }

            // 如果指定的max不为数值类型，则试图将其作为ID或name获取对应表单域的值
            if (!Validator.asNumber(max)) {
                max = $.jdGetFieldValue(max);
            }

            if (!Validator.asNumber(max)) {
                return false;
            }

            if (Validator.isString(value)) {
                value = parseInt(value);
            }

            if (Validator.isString(max)) {
                max = parseInt(max);
            }

            return value <= max;
        },

        /**
         *
         * @param value
         * @param min
         * @param max
         */
        /* Boolean */
        numberRange : function(value/* String */, 
        		min/* String */, max/* String */) {
            return this.min(value, min)
                    && this.max(value, max);
        },

        minDate : function(value/* String */, minDate/* String */) {
            if (!Validator.isDate(value)) {
                return false;
            }

            if (!Validator.isNull(minDate)) {
                if (!Validator.isDate(minDate)) {
                    minDate = $.jdGetFieldValue(minDate);
                }
            }

            if (Validator.isDate(minDate)) {
                return Validator.dateCompare(minDate, value);
            }

            return false;
        },

        maxDate : function(value/* String */, maxDate/* String */) {
            if (!Validator.isDate(value)) {
                return false;
            }

            if (!Validator.isNull(maxDate)) {
                if (!Validator.isDate(maxDate)) {
                    maxDate = $.jdGetFieldValue(maxDate);
                }
            }

            if (Validator.isDate(maxDate)) {
                return Validator.dateCompare(value, maxDate);
            }

            return false;
        },

        /**
         *
         * @param value
         * @param minDate
         * @param maxDate
         */
        /* Boolean */
        dateRange : function(value/* String */, 
        		minDate/* String */, maxDate/* String */) {
            return this.minDate(value, minDate)
                    && this.maxDate(value, maxDate);
        },

        minDateTime : function(value/* String */, minDateTime/* String */) {
            if (!Validator.isDate(value)) {
                return false;
            }

            if (!Validator.isNull(minDateTime)) {
                if (!Validator.isDate(minDateTime)) {
                    minDateTime = $.jdGetFieldValue(minDateTime);
                }
            }

            if (Validator.isDate(minDateTime)) {
                return Validator.dateCompare(minDateTime, value);
            }

            return false;
        },

        maxDateTime : function(value/* String */, maxDateTime/* String */) {
            if (!Validator.isDateTime(value)) {
                return false;
            }

            if (!Validator.isNull(maxDateTime)) {
                if (!Validator.isDateTime(maxDateTime)) {
                    maxDateTime = $.jdGetFieldValue(maxDateTime);
                }
            }

            if (Validator.isDateTime(maxDateTime)) {
                return Validator.dateCompare(value, maxDateTime);
            }

            return false;
        },
        /**
         *
         * @param value
         * @param minDateTime
         * @param maxDateTime
         */
        /* Boolean */
        dateTimeRange : function(value/* String */, 
        		minDateTime/* String */, maxDateTime/* String */) {
            return this.minDateTime(value, minDateTime)
                    && this.maxDateTime(value, maxDateTime);
        },

        /**
         *
         * @param value
         * @param other
         */
        /* Boolean */
        equals : function(value/* String */, other/* String */) {
            var equal = value == other;

            if (!equal) {
                var fieldValue = $.jdGetFieldValue(other);
                equal = value == fieldValue;
            }

            return equal;
        },

        /**
         * 身份证格式:
         *      1) 15位数字组成
         *      2) 18位数字组成
         *      3) 17位数字+X|x
         * @param value
         */
        /* Boolean */
        idCard : function(value/* String */) {
            var idCardRegEx = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            return Validator.doRegExpMatch(value, idCardRegEx);
        },

        /**
         * 电话格式:
         *      1) 1到3位数字 + [.]|[-] +
         * @param value
         */
        /* Boolean */
        phone : function(value/* String */) {
            var phoneRegEx = /^([\+][0-9]{1,3}([ \.\-])?)?([\(][0-9]{1,6}[\)])?([0-9 \.\-]{1,32})(([A-Za-z \:]{1,11})?[0-9]{1,4}?)$/;
            return Validator.doRegExpMatch(value, phoneRegEx);
        },

        /**
         * 手机号码格式:
         *      1) 13开头的11位数字
         *      2) 15开头的11位数字
         *      3) 18开头的11位数字
         * @param value
         */
        /* Boolean */
        mobilePhone : function(value/* String */) {
            return Validator.doRegExpMatch(value, /^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$/);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        email : function(value/* String */) {
            var emailRegEx = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
            return Validator.doRegExpMatch(value, emailRegEx);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        integer : function(value/* String|Number */) {
            var integerRegEx = /^[\-\+]?\d+$/;
            return Validator.doRegExpMatch(value, integerRegEx);
        },

        /**
         * 验证整数或浮点数
         * @param value
         */
        /* Boolean */
        number : function(value/* String|Number */) {
            var numberRegEx = /^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/;
            return Validator.doRegExpMatch(value, numberRegEx);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        date : function(value/* String */) {
            return Validator.isDate(value);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        dateTime : function(value/* String */) {
            return Validator.isDateTime(value);
        },

        /**
         * IP格式: 由点分割的四组小于等于255的数字组成
         * @param value
         */
        /* Boolean */
        ipv4 : function(value/* String */) {
            var ipv4RegEx = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
            return Validator.doRegExpMatch(value, ipv4RegEx);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        url : function(value/* String */) {
            var urlRegEx = /^((https?|ftp):\/\/)?(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
            return Validator.doRegExpMatch(value, urlRegEx);
        },

        /**
         *
         * @param value
         */
        /* Boolean */
        postcode : function(value/* String */) {
            return Validator.doRegExpMatch(value, /^[1-9][0-9]{5}$/);
        },

        /**
         *
         * @param value
         * @param myRegExp
         */
        /* Boolean */
        regExp : function(value/* String */, myRegExp/* RegExp|String */) {
            return Validator.doRegExpMatch(value, myRegExp);
        }
    }

    FormValidator.defaultMsgTipOptions = {
        pos: "right",
        zIndex: 3000,
        messageTextClass : "errorTip",
        offset: {
            left:10,
            top: -3
        },
        timeout: 3000,
        singleton: false,
        autoClose:false
    }

})(jQuery);




