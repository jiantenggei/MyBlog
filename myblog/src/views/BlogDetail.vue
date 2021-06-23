<template>
  <div>
    <Header></Header>
    <div class="content">
      <h1>{{ blog.title }}</h1>
      <div>
        <div class="new-meta-box">
          <div class="new-meta-item"></div>
          <i class="el-icon-user-solid" aria-hidden="true"></i>
          <h5>{{ blog.author }}</h5>
        </div>
        <div class="new-meta-item date">
          <i class="el-icon-s-order" aria-hidden="true"></i>
          <a class="notlink">
            <p>{{ blog.gmtCreate }}</p>
          </a>
        </div>
      </div>
      <el-button-group class="button">
        <el-button v-show="ownBlog" type="primary" icon="el-icon-edit" @click="toEdit"></el-button>
        <el-button v-show="ownBlog" type="danger" icon="el-icon-delete" @click="toDelete"></el-button>
      </el-button-group>
      <el-divider>
        <i class="el-icon-reading"></i>
      </el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "../components/Header";
import Footer from "../components/Footer";
import "github-markdown-css";

export default {
  name: "BlogDetail",
  components: {Header, Footer},
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: "",
        gmtStatus: null,
        gmtCreate: "",
        author: "",
        userId: "",
        avatar: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
      },
      ownBlog: false
    }
  },
  methods: {
    toEdit() {
      this.$router.push({name: "BlogEdit", params: {blogId: this.blog.id}});
    },
    toDelete() {
      this.$confirm("是否删除该博文？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(()=>{
            this.$axios.delete("/blog/deleteById/"+this.blog.id,{
               headers: {
                  Authorization: localStorage.getItem("token")
                }
            }).then(res=>{
              this.$router.push({name:"MainBlog"})
            });
            this.$message({
               type: "success",
              message: "删除成功!"
            })
      }).catch(()=>{
          this.$message({
            type: "info",
            message: "已取消删除"
          });
      })
    }
  },
  created() {
    let blogId = this.$route.params.blogId
    if (blogId) {
      this.$axios.get("/blog/getOneById/" + blogId).then(res => {
        let blog = res.data.data
        this.blog = blog
        console.log(this.blog)
        let MardownIt = require("markdown-it");
        let md = new MardownIt();

        let result = md.render(blog.content);

        this.blog.content = result;
        // 判断是否为该作者，是才能编辑
        this.ownBlog = blog.userId == this.$store.getters.getUserInfo.id;
      })
    }
  }
}
</script>

<style scoped>
.button {
  padding-left: 800px;
}

.new-meta-item a {
  text-decoration: none;
  color: rgba(68, 68, 68, 0.65);
  padding-left: 0;
  padding-right: 4px;
}

.new-meta-box {
  padding-left: 100px;
  -webkit-transition: all 0.1s ease;
  font-size: 0.8125rem;
  padding-top: px;
  padding-bottom: 2px;
  display: flex;
  align-items: center;
  float: left;
  flex-wrap: wrap;
}

.new-meta-item {
  color: rgba(68, 68, 68, 0.65);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 2px;
  margin: 0 8px 0 0;
  border-radius: 4px;
}

.content {
  padding: 20px 15px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
}

.edit {
  color: skyblue;
  text-decoration: none;
}

.edit:hover {
  color: red;
  scale: 1;
}

h1 {
  padding: calc(var(--sp) / 2) 0 var(--sp);
  text-align: center;
  transition: color .6s;
  color: var(--t);
  margin: 2.75rem 0 1rem;
  font-family: Product Sans, Oswald, -apple-system, BlinkMacSystemFont, Segoe UI, Helvetica, Arial, sans-serif;
  font-weight: 600;
  line-height: 1.5;
}
</style>
