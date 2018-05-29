<%--
  Created by IntelliJ IDEA.
  User: xionggaoxiong_sx
  Date: 2018/5/29
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <%@include file="common/head.jsp" %>
    <title>秒杀详情页</title>

</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h2>${seckill.name}</h2>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <%--显示time图标--%>
                <span class="glyphicon glyphicon-time"/>
                <%--展示倒计时--%>
                <span class="glyphicon" id="seckill-box"/>
            </h2>
        </div>
    </div>
</div>

<%--登陆弹出层，输入电话--%>
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"/>
                    秒杀电话
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey" placeholder="请填写手机号:)"
                               class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--显示验证信息--%>
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<%--注意JavaScript标签不要直接关闭，否则下面的script不能加载。应该写成一对标签的形式--%>

<%--使用CDN获取公共js http://www.bootcdn.cn/--%>
<%--使用jQuery Cookie操作插件--%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--使用jQuery countdown倒计时插件--%>
<%--<script src="https://cdn.bootcss.com/jquery-countdown/2.0.2/jquery.countdown.min.js"></script>--%>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<%--开始编写交互逻辑--%>
<script src="/resources/script/seckill.js"></script>
<script type="text/javascript">
    $(function () {
        // 使用EL表达式传入参数
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},// 将时间转成毫秒
            endTime:${seckill.endTime.time}
        });
    });
</script>
</html>
