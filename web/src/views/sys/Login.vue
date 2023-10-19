<template>
  <div>
    <div class="login-panel">
      <a-form ref="loginForm" :model="formData" label-align="right" layout="horizontal" :label-col="{span:5}" :rules="rules" @finish="doLogin">
        <a-form-item label="用户名" name="username">
          <a-input v-model:value="formData.username">
            <template #prefix>
              <UserOutlined/>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item label="密码" name="password">
          <a-input v-model:value="formData.password" type="password">
            <template #prefix>
              <LockOutlined/>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item name="remember">
          <a-checkbox v-model:checked="formData.remember">记住登录</a-checkbox>
        </a-form-item>
        <a-form-item>
          <div>
            <a-button type="primary" html-type="submit" style="width: 100%">登录</a-button>
          </div>
          <div style="justify-content: space-between; display: flex">
            <a>注册</a><a href="">忘记密码</a>
          </div>
        </a-form-item>


      </a-form>
    </div>
  </div>
</template>

<script>
import {UserOutlined,LockOutlined} from "@ant-design/icons-vue";
import session from "../../utils/sessionStorage.js"
export default {
  name: "Login.vue",
  components:{UserOutlined, LockOutlined},
  data() {
    return {
      rules: {
        password: {
          required: true,
          message: '请输入密码'
        },
        username: {
          required: true,
          message: '请输入用户名'
        }

      },
      formData: {}
    }
  },
  methods: {
    doLogin() {
      this.$refs.loginForm.validate().then(() => {
        session.user.setToken(this.formData.password)
        this.$router.push({name: 'index'})
      })

    },
    doReg() {

    }
  }

}
</script>

<style scoped>
.login-panel {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

}
</style>