<template>
  <div>
    <div class="box">
      <div class="pop-up">
        <div id="small-dialog" class="mfp-hide book-form">
          <h3>找&emsp;回&emsp;密&emsp;码 </h3>

          <el-form :model="ruleForm" label-width="80px" ref="ruleForm">
            <el-form-item label="用户名:" prop="name" :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur' }]">
              <el-input v-model.number="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item
              prop="email"
              label="邮箱"
              :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]"
            >
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
          </el-form>
          <span>
						<el-button type="primary" plain style="margin-top: 50px; width: 500px"
                       @click="restPwd('ruleForm')">找回密码</el-button>
						<el-button type="warning" plain style="margin-top: 40px;margin-right: 20px;" @click="resetForm('ruleForm')">重置</el-button>
						<el-button type="success" plain style="margin-top: 40px;margin-right: 15px" @click="toLogin">返回</el-button>
					</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RestPwd",
  data() {
    return {
      ruleForm: {
        name: "",
        email: ""
      },
    }
  },
  methods: {
    //返回到登录函数
    toLogin() {
      this.$router.push({name: "Login"})
    },
    //清空表单函数：
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    //找回密码
    restPwd(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post("/user/resetPwd", {
            username: this.ruleForm.name,
            email: this.ruleForm.email
          }).then(res => {
            const status = res.data.status
            const _this = this
            if (status == 200) {
              this.$notify({
                title: res.data.data,
                message:res.data.msg+"\n3秒后返回首页",
                type: 'success',
                duration: 3000,
                 onClose() {
                  _this.$router.push({name:'Login'})
                }
              });
            }else{
              this.$notify({
                title: "错误！",
                message:res.data.msg,
                type: 'error',
                duration: 5000,
              });
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped>
.box {
  max-width: 600px;
  height: 500px;
  border: 2px solid skyblue;
  box-shadow: darkgrey 10px 10px 30px 5px;
  border-radius: 15px;
  margin: 5% auto;
}


#small-dialog {
  background: #fff;
  text-align: left;
  max-width: 500px;
  margin: 40px;
  position: relative;
}

.el-button--warning {
  width: 230px;
}

.el-button--success {
  width: 230px;
}

.el-button--primary {
  color: #FFF;
  background-color: #409EFF;
  border-color: #409EFF;
  width: 500px;
}

.el-button + .el-button {
  margin-left: 0 !important;
}

span {
  margin-top: 50px;

}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: 'Montserrat', sans-serif;
}


input {
  -webkit-writing-mode: horizontal-tb !important;
  text-rendering: auto;
  color: -internal-light-dark(black, white);
  letter-spacing: normal;
  word-spacing: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: textfield;
  background-color: -internal-light-dark(rgb(255, 255, 255), rgb(59, 59, 59));
  -webkit-rtl-ordering: logical;
  cursor: text;
  margin: 0em;
  font: 400 13.3333px Arial;
  padding: 1px 2px;
  border-width: 2px;
  border-style: inset;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
}
</style>
