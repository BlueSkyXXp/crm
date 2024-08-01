<template>
  <div class="login-container">
    <img :src="require('@/assets/images/login-background.jpg')" alt="gridBg" class="grid-bg">
    <!-- <div class="logo">
      <span style="
      color: #ffffff;
      font-size: 30px;
      font-weight: 700;
      line-height: 35px;">轻松管理客户信息</span><br/>
      <span style="
      color: #ffffff;
      font-size: 15px;
      line-height: 50px;"></span>
    </div> -->
    <div class="main-picture" />
    <div class="login-form">
      <div class="formTitle">
        <div class="formTitleBorder" />  欢迎登陆
      </div>
      <h3 class="login-title">客户关系管理系统</h3>
      <div class="title-border" />
      <el-form ref="loginForm" style="margin: 50px;" :model="loginForm" :rules="loginRules">
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
          >
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
          >
            <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item v-if="captchaEnabled" prop="code">
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%"
            @keyup.enter.native="handleLogin"
          >
            <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" class="login-code-img" @click="getCode">
          </div>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
        <el-form-item style="width:100%;">
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:100%;"
            @click.native.prevent="handleLogin"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
          <div v-if="register" style="float: right;">
            <router-link class="link-type" :to="'/register'">立即注册</router-link>
          </div>
        </el-form-item>

      </el-form>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: false,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set("rememberMe", this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(() => {});
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 38px;
}
.login-container {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background: #f9fcff;
  overflow: hidden;
  .grid-bg {
    width: 100%;
    height: 100%;
  }
  .logo {
    position: absolute;
    left: 22%;
    top: 20%;
    width: 400px;

    overflow: hidden;
    & > img {
      width: 100%;
      height: 100%;
    }
  }
  .main-picture {
    position: absolute;
    top: 29%;
    left: 19%;
    // width: 27%;
    // height: 60%;
    width: 600px;
    height: 528px;
    background: url(~@/assets/images/tu_login@2x.png);
    background-size: 100% 100%;
  }
  .login-form {
    position: absolute;
    right: 19%;
    top: 26%;
    width: 459px;
    height: 459px;
    box-shadow: 0px 0px 20px 0px #e1edff;
    background: #fff;
    border-radius: 12px;
    padding: 16px;
    .formTitle{
      position: relative;
      font-size: 20px;
      font-weight: 700;
      display: flex;
      align-items: center;
      margin-bottom: 16px;
      .formTitleBorder{
        margin-right: 16px;
        width: 5px;
        height: 20px;
        border-radius: 2px;
        background-color: #007aff;
      }
    }
    .login-title {
      line-height: 24px;
      font-size: 24px;
      color: #1e2129;
      font-family: AlibabaPuHuiTiB;
      margin: 30px 0;
      text-align: center;
    }
    .title-border {
      background: #0651c6;
      height: 3px;
      width: 100px;
      position: absolute;
      top: 105px;
      left: 50%;
      margin-left: -50px;
      border-radius: 3px;
    }
    .form {
      .input-item {
        margin: 0 28px 20px 28px;
        .custom-input {
          width: 100% !important;
          ::v-deep .el-input__inner {
            height: 40px !important;
            line-height: 40px !important;
          }
        }
      }
      .remember-info {
        margin: 0 28px 20px 28px;
        display: flex;
        vertical-align: top;
        .info {
          flex: 1;
          padding-left: 20px;
          color: #f94428;
          font-weight: 600;
          line-height: 20px;
          & > i {
            padding-right: 2px;
          }
        }
        ::v-deep .el-checkbox__input.is-checked .el-checkbox__inner {
          background: #0651c6;
          border-color: #0651c6;
        }
        ::v-deep .el-checkbox__input.is-checked + .el-checkbox__label {
          color: #666;
        }
      }
      .login-btn {
        height: 40px;
        font-size: 16px;
        line-height: 40px;
        text-align: center;
        border-radius: 4px;
        color: #fff;
        margin: 0 28px 20px 28px;
        background: #0651c6;
        cursor: pointer;
      }
    }
  }
}
</style>
