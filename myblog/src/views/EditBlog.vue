<template>
  <div>
    <Header></Header>
    <div class="edit-content">
      <el-form :model="reluForm" :rules="rules" ref="ruleForm" label-width="100px" class="el-date-editor--monthrange">
        <el-form-item label="标题" prop="title">
          <el-input v-model="reluForm.title"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="description">
          <el-input v-model="reluForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="reluForm.content"></mavon-editor>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"> 提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import Footer from "../components/Footer";

export default {
  name: "EditBlog",
  components: {Header, Footer},
  data() {
    return {
      reluForm: {
        id:"",
        title: "",
        description: "",
        content: "",
        author: this.$store.getters.getUserInfo.username
      },
      rules: {
        title: [
          {required: true, message: "请输入文章标题", trigger: "blur"},
          {min: 3, max: 25, message: "长度在3~25个字符", trigger: "blur"}
        ],
        description: [
          {required: true, message: "请输入文章摘要", trigger: "blur"}
        ],
        content: [
          {required: true, trigger: "blur"}
        ]
      }
    }
  },
  methods: {
    //重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.post("/blog/edit", this.reluForm, {
            headers: {
              Authorization: localStorage.getItem("token")
            }
          }).then(res => {
            this.$alert("操作成功", "消息提示", {
              confirmButtonText: "确定",
              callback: action => {
               this.$message({
                  type: "info",
                  message:"操作成功！已返回首页！"
               });
               this.$router.push({name:"MainBlog"})
              }
            })
          })
        }else{
           console.log("error submit!!");
          return false;
        }
      })
    }
  },
  created() {
    let blogId = this.$route.params.blogId
    if (blogId) {
      this.$axios.get("/blog/getOneById/" + blogId).then(res => {
        let blog = res.data.data
        console.log(blog)
        this.reluForm.id = blog.id
        this.reluForm.title = blog.title
        this.reluForm.description = blog.description
        this.reluForm.content = blog.content
      })
    }
  }
}
</script>

<style scoped>
.edit-content {
  margin-top: 50px;
  text-align: center;
  clear: both;
}
</style>
