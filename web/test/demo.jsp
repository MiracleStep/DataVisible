<script type="text/javascript">
    var websocaket =null;
    var message = null;
    if('WebSocket' in window){
        websocaket = new WebSocket("ws://localhost:8080/WebSocketTest");//用于创建 WebSocket 对象。WebSocketTest对应的是java类的注解值
    }
    else {
        alert("当前浏览器不支持");
    }
    //连接发生错误的时候回调方法；
    websocaket.onerror=function(){
        alert("连接错误");
    }
    //连接成功时建立回调方法；
    websocaket.onopen=function(){
        alert("连接成功");
    }
    //收到消息的回调方法
    websocaket.onmessage=function(msg){
        message = msg;
        websocaket.send(message);
    }
    //连接关闭的回调方法
    websocaket.onclose=function(){
        alert("关闭成功");
    }
    //关闭websocket
    //
    function closea() {
        websocaket.close();
        alert("点击关闭");
    }
    function setdivInnerHTML(innerHTML) {
        document.getElementById('div').innerHTML += innerHTML + '<br/>';
    }
    function send() {
        websocaket.send(message);//给后台发送数据
    }
</script>