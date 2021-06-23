<template>
  <div>
    <div class="box">

      <div class="pop-up">
        <div id="small-dialog" class="mfp-hide book-form">
          <h3>用&emsp;户&emsp;注&emsp;册 </h3>

          <el-form :model="ruleForm" label-width="80px" ref="ruleForm" :rules="rules">
            <el-form-item label="用户名:">
              <el-input v-model.number="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码:" prop="pass">
              <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
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
						<el-button type="primary" plain style="margin-top: 40px;" @click="toRegister('ruleForm')">注册</el-button>
						<el-button type="warning" plain style="margin-top: 40px;margin-right: 20px;" @click="resetForm('ruleForm')">重置</el-button>
						<el-button type="success" plain style="margin-top: 40px;" @click="toLogin">返回</el-button>
					</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        name: "",
        pass: '',
        checkPass: '',
        email: ''
      },
      rules: {
        name: [{required: true, message: "请输入用户名", trigger: "blur"},
          {min: 3, max: 15, message: "长度3~15个字符", trigger: "blur"}],
        pass: [
          {validator: validatePass, trigger: 'blur', required: true}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur', required: true}
        ],

      }
    }
  },
  methods: {
    //返回到登录函数
    toLogin() {
      this.$router.push({name: "MainBlog"})
    },
    //清空表单函数：
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },

    toRegister(formName){
      this.$refs[formName].validate(valid => {
        if(valid) {
          this.$axios.post("/user/register", {
            username: this.ruleForm.name,
            password: this.ruleForm.pass,
            email: this.ruleForm.email
          }).then(res => {
            const status = res.data.status;
            const _this = this
            if (status == 200) {
              this.$notify({
                title: res.data.msg,
                message: "恭喜注册成功,5秒后将自动返回登录页面~",
                type: 'success',
                duration: 5000, onClose() {
                  _this.$router.push({name:'Login'})
                }
              });

            } else {
              this.$notify({
                title: res.data.msg,
               type:"error"
              });
            }

          })
        }
    })
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
