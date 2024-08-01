<template>
  <div v-show="false" id="wrap">
    <audio id="audio" src="@/api/audio/notice.mp3" preload="auto" muted type="audio/mp3" controls="controls">
      <source src="@/api/audio/notice.mp3">
      <span id="audioId">播放音乐</span>
    </audio>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters([
      "userId"
    ]) },
  created() {
    this.initWebSocket();
  },
  destroyed() {
    this.websock.close(); // 离开路由之后断开websocket连接
  },
  methods: {
    initWebSocket() {
      const wsuri = "ws://localhost:8080/d41d8cd98f00b204e9800998ecf8427e/" + this.userId;
      if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
      } else {
        // console.log(wsuri)
        this.websock = new WebSocket(wsuri);
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onopen = this.websocketonopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onclose = this.websocketclose;
      }
    },
    // 连接建立之后执行send方法发送数据
    websocketonopen() {
      // let actions = {"test":"我已在线"};
      // this.websocketsend(JSON.stringify(actions));
    },
    // 连接建立失败重连
    websocketonerror() {
      this.initWebSocket();
    },
    // 数据接收
    websocketonmessage(e) {
      // this.$modal.msg(e.data);
      if (e.data != "连接成功") {
        this.$notify({
          title: "您有新的消息",
          message: e.data,
          type: "warning",
          duration: 0,
          position: "bottom-right"
        });
        let audio = document.querySelector("audio");// 用这种标签名称获取的方式就不会报错了，，，，
        // setInterval(() => {
        audio.currentTime = 0;// 从头开始播放
        audio.muted = false;// 取消静音
        audio.play();// 音频播放
        // },2000)
        // const redata = JSON.parse(e.data);
      }
      console.log(e.data);
    },
    // 数据发送
    websocketsend(Data) {
      this.websock.send(Data);
    },
    // 关闭
    websocketclose(e) {
      console.log("断开连接", e);
    }
    // 监听鼠标移动的事件
  }
};
</script>
