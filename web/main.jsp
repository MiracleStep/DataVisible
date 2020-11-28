<%--
  Created by IntelliJ IDEA.
  User: suxiang
  Date: 2020/11/26
  Time: 下午11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="servlet.testcode" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>第一个 ECharts 实例</title>
    <script src="https://cdn.staticfile.org/jquery/2.2.4/jquery.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<%--<!-- 为ECharts准备一个具备大小（宽高）的Dom -->--%>
<div id="main" style="width: 600px;height:400px;"></div>
<div id="message"></div>
<script type="text/javascript">
    var websocket = null;
    var message = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket");
    }
    else {
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        data.push(event.data);
        message = event.data;
        data.shift();
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }



    //-----------------------------------------------------------------------------------
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        websocket.send(message);
        message=null;
    }




    //------------------------------------------------------------------------------------
    var myChart = echarts.init(document.getElementById('main'));
    var data = [123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231,123,234,456,231];
    function addData() {
        send();
    }



    let option = {
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: {}
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:'成交',
                type:'line',
                smooth:false,
                symbol: 'none',
                stack: 'a',
                areaStyle: {
                    normal: {}
                },
                data: data
            }
        ]
    };
        // myChart.showLoading();  // 开启 loading 效果
        // myChart.hideLoading();
        setInterval(function () {
        addData();
        myChart.setOption({
            xAxis: {
                data: {}
            },
            series: [{
                name:'成交',
                data: data
            }]
        });
    }, 2000);

    // 基于准备好的dom，初始化echarts实例

    myChart.setOption(option)
</script>
</body>
</html>
