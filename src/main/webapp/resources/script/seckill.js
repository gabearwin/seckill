/**
 * 存放主要交互逻辑js代码
 */

/**
 * JavaScript模块化
 * @type {{URL: {}, validatePhone: seckill.validatePhone, detail: {init: seckill.detail.init}}}
 */
var seckill = {
    // 封装秒杀相关ajax的URL
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    // 验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    handleSeckill: function (seckillId, node) {
        // 获取秒杀地址，控制显示逻辑，执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {//开启秒杀
                    // 获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl = " + killUrl);
                    // 使用one而不是click，是绑定一次点击事件
                    $('#killBtn').one('click', function () {
                        // 执行秒杀请求
                        // 1.先禁用按钮
                        $(this).addClass('disabled');
                        // 2.发送秒杀请求执行秒杀
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                console.log("状态：" + stateInfo);
                                // 3.显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {//秒杀未开启，可能由于客户电脑计时问题
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    // 重新计算计时逻辑
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result = ' + result);
            }
        });
    },
    countDown: function (seckillId, nowTime, startTime, endTime) {
        console.log(seckillId + '_' + nowTime + '_' + startTime + '_' + endTime);
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            // 秒杀结束
            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            // 秒杀未开始，计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                // 时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                // 时间完成后的回调函数
            }).on('finish.countdown', function () {
                // 获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
    // 详情页秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            // 取出参数
            var killPhone = $.cookie('killPhone');

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
                        // 手机号写入cookie，并设置过期时间为7天
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        // 刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            } else {// 已经登录
                var seckillId = params['seckillId'];
                var startTime = params['startTime'];
                var endTime = params['endTime'];
                $.get(seckill.URL.now(), {}, function (result) {
                    if (result && result['success']) {
                        var nowTime = result['data'];
                        seckill.countDown(seckillId, nowTime, startTime, endTime);
                    } else {
                        console.log('result:' + result);
                    }
                });
            }
        }
    }
}