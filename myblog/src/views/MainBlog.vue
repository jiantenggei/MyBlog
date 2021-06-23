<template>
  <div>
    <Header></Header>
    <!--   //这里是一个时间线-->
    <div >
      <el-row :gutter="20">
        <el-col :span="5">
          <div class="grid-content">
            <el-input v-model="input" placeholder="请输入博客名字" style="margin-top: 20px"></el-input>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content">
            <el-button type="primary" style="margin-top: 20px;margin-left: auto " @click="search">搜索</el-button>
          </div>
        </el-col>
        <el-col :span="5">
          <div class>
            <el-button type="danger" style="margin-top: 20px;margin-left: auto " @click="reset">重置</el-button>
          </div>
        </el-col>
      </el-row>



    <el-timeline style="padding:10px 0" >
      <!--      这里去循环遍历 分页查询得结果-->
      <el-timeline-item placement="top" v-for="(blog,index) in blogs " :key="index">
        <!--       一个个的博客卡片-->
        <el-card>
          <h2>
            <router-link class="link" :to="{name:'BlogDetail',params:{blogId:blog.id}}">{{ blog.title }}</router-link>
          </h2>
          <div class="new-meta-box">
            <div class="new-meta-item">
              <!--              小user 图标-->
              <i class="el-icon-user-solid" aria-hidden="true"></i>
              <router-link class="link" :to="{name: 'userBolg',params: {username:blog.author}}">{{ blog.author }}
              </router-link>
              <div class="new-meta-item">
                <i class="el-icon-s-order" aria-hidden="true"></i>
                <a class="notlink">
                  <p>{{ blog.gmtCreate }}</p>
                </a>
              </div>
            </div>
            <!--              这里是分割线条-->
            <el-divider></el-divider>
            <blockquote>
              <p><i class="el-icon-collection-tag">简介：{{ blog.description }}</i></p>
            </blockquote>
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>
    <el-pagination class="pager" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="pageNo" :page-sizes="[4, 8]" :page-size="pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
  </div>
    <Footer></Footer>
  </div>

</template>

<script>
import Header from "../components/Header";
import Footer from "../components/Footer";

export default {
  name: "MainBlog",
  components: {Header, Footer},
  data() {
    return {
      blogs: {},
      pageNo: 1,
      total: 10,
      pageSize: 8,
      input: "",
    }
  },
  methods: {
    getPage() {
      this.$axios.post("/blog/getBlogs", {
        pageNo: this.pageNo,
        pageSize: this.pageSize,
        blogName: this.input
      }).then(res => {
        console.log(res)
        this.blogs = res.data.data.records
        this.pageNo = res.data.data.current
        this.pageSize = res.data.data.size
        this.total = res.data.data.total

      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val
      this.getPage()
    },
    // 当当前页改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNo = val
      this.getPage()
    },
    search() {
      console.log(this.input)
      this.pageNo = 1
      this.getPage()
    },
    reset() {
      this.pageNo = 1
      this.pageNo = 1
      this.total = null
      this.pageSize = 8,
        this.input = ""
      this.getPage()
    }
  },
  created() {
    this.getPage()

  }
}
</script>

<style scoped>
.fenge .el-divider{
 margin: 2px 0 !important;
}
.new-meta-item a{
    text-decoration: none;
    color: rgba(68,68,68,0.65);
    padding-left: 0;
    padding-right: 4px;
}
.new-meta-box{
    -webkit-transition: all 0.1s ease;
    font-size: 0.8125rem;
    padding-top:10px;
    padding-bottom: 2px;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
}
.new-meta-item{
    color: rgba(68,68,68,0.65);
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 2px;
    margin: 0 8px 0 0;
    border-radius: 4px;
}
blockquote{
    margin-left: 2px;
    text-align: left;
    font-size: 0.9375rem;
    margin-top: 0.5rem;
    margin-bottom: 0.5rem;
    position: relative;
    width: 95%;
    padding: 0.7em 1.2em;
    background: #e8f4fd;
    border-left: 4px solid #2196f3;
    border-radius: 4px;
}
.pager {
  margin: 0 auto;
  text-align: center;
}
.link{
  color: skyblue;
  text-decoration: none;
}
.link:hover{
  color: #ff5722;
}


</style>
