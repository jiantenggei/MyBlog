<template>
  <div>
    <div class="box">
      <el-container>
        <el-header style="font-size: 20px;color: #2e6dca"><p>欢迎登录</p></el-header>
        <el-main>
          <div class="demo-image__lazy">
          <el-image :key="url" :src="url" lazy></el-image>
        </div>
        <div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm form_item"
        >
          <el-form-item label="用户名" prop="username" >
            <el-input v-model="ruleForm.username" prefix-icon="el-icon-user-solid"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password" >
            <el-input type="password" v-model="ruleForm.password"  @keyup.enter="submitForm('ruleForm')" prefix-icon="el-icon-date"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" round  @click="submitForm('ruleForm')" class="button">登录</el-button>
            <el-button type="info" round  @click="resetForm('ruleForm')" class="button" style="margin-left: 20px;">重置</el-button>
          </el-form-item>
        </el-form>
        </div>
        </el-main>
      </el-container>
      <a class="text-center block font-color"><router-link :to="{name:'RestPwd'}">忘记密码？</router-link></a>
		<p class="text-center">
			没有账号？<router-link :to="{name:'Register'}" class="font-color">立即注册</router-link>
		</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required:true,message:"请输入用户名",trigger:"blur"},
          {min:3,max:15,message: "长度3~15个字符",trigger: "blur"}
        ],
        password: [
          { required:true,message:"请输入密码",trigger:"blur"},
          {min:3,max:15,message: "长度3~15个字符",trigger: "blur"}
        ]
      },
      url:"https://tenfei04.cfp.cn/creative/vcg/800/new/VCG41N1268135450.jpg"
    }
  },
  methods:{
    //重置表单
    resetForm(formName){
      this.$refs[formName].resetFields()
    },
    //提交表单
    submitForm(formName){
      this.$refs[formName].validate(valid=>{
        if (valid){
          this.$axios.post("/user/login",this.ruleForm).then(res =>{
            const userInf = res.data.data
            this.$store.commit("setToken",res.data.data.token)
            this.$store.commit("setUserInfo", userInf);
            // console.log(userInf)
            console.log(this.$store.getters.getUserInfo);
            this.$router.push({name:"MainBlog"})
          })
        }
        }
      )
    }

  }
}
</script>

<style scoped>
.font-color {
	    color: #2e6dca;
		  font-size:20px;
	}
	.block {
	    display: block !important;
	}
	.text-center {
	    text-align: center !important;
		font-size:15px;
	}
	a {
	    color: #333;
	    text-decoration: none;
	}
	p {
	    margin-top: 25px;
	}

.form_item >>> .el-form-item:nth-child(3) .el-form-item__content{
  margin-left: 50px !important;
  margin-top: 40px !important;
}
.box{
  max-width: 800px;
  height:  650px;
  border:2px solid skyblue;
  box-shadow: darkgrey 10px 10px 30px 5px ;
  border-radius: 15px;
  margin: 5% auto;
   background: white;
}
.button{
  margin-left:5px;
  width: 40%;
}
.el-header{
  border-radius: 15px;
}
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin: 100px auto;

}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.logo {
  height: 80%;
  margin-top: 5px;
}
.demo-ruleForm {
  width: 31rem;
  margin: 0 auto;
}
.demo-image__lazy{
  width: 250px;
  height: 250px;
  margin: 0 auto;

}
</style>
