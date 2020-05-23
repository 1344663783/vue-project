<template>
  <div class="login_container">
    <div class="login_box">
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" class="login_form">
        <el-form-item prop="username">
        <el-input v-model="loginForm.username" prefix-icon="iconfont icon-yonghu"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" prefix-icon="iconfont icon-icon_huabanfuben"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="login">提交</el-button>
          <el-button size="mini" @click="resetForm('loginFormRef')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        loginForm:{
          username:'admin',
          password:'123456'
        },
        loginFormRules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 6, message: '长度在 3 到 6 个字符', trigger: 'blur' }
          ],
          password:[
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ]
          }
      }
    },
    methods: {
      resetForm (loginFormRef) {
        this.$refs[loginFormRef].resetFields();
      },
      login () {
        this.$refs.loginFormRef.validate(async (valid) => {
          if (!valid)return;
          const {data: res} = await this.$http.post("login",this.loginForm);
          if (res.code !== 20000){
            // console.log("登录失败")
            this.$message.error('登录失败');
          }else{
            this.$message.success('登录成功');
            window.sessionStorage.setItem("token",res.result.token);
            this.$router.push("/home")
          }

        })
      }
    }
  }
</script>
<style lang="less" scoped>
.login_container{
  height: 100%;
  background-color: bisque;
}
  .login_box{
    height: 200px;
    width: 300px;
    background-color: #ffffff;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    .login_form{
      padding: 10px;
    }
  }
  .login_form /deep/ .el-input__inner{
      height: 30px;
  }
</style>
