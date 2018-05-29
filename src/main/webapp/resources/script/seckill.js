/**
 * 存放主要交互逻辑js代码
 */

/**
 * JavaScript模块化
 * @type {{URL: {}, validatePhone: seckill.validatePhone, detail: {init: seckill.detail.init}}}
 */
var seckill = {
    // 封装秒杀相关ajax的URL
    URL: {},
    // 验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    // 详情页秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            // 取出参数
            var killPhone = $.cookie('killPhone');
            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];

            // 验证是否登录
            if (!seckill.validatePhone(killPhone)) {// 验证未登录
                var killPhoneModal = $('#killPhoneModal');
                // 显示弹出层
                killPhoneModal.modal({
                    show: true,// 显示弹出层
                    backdrop: 'static',// 禁止位置关闭
                    keyboard: false // 关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log('inputPhone = ' + inputPhone); //TODO
                    if (seckill.validatePhone(inputPhone)) {
                        // 电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        // 刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            // todo 已登录
        }
    }
}