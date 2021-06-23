<template>

  <div class="m-content">

    <div class="nav-left">
      <el-image style="width: 100px; height: 100px" :src="url" lazy>
      </el-image>
    </div>

    <div >
      <!-- <el-avatar :size="50" :src="user.avatar"></el-avatar>
            <div>{{user.username}}</div> -->
      <div >
        <el-menu style="border-bottom: solid 0px #ffffff;"  :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="'1" >
            <el-link type="info" href="/blog">主页</el-link>
          </el-menu-item>
          <el-menu-item index="'2" >
            <el-link type="success" @click="toEdit">发表博文</el-link>
          </el-menu-item>
          <el-menu-item  index="'3">
            <el-link type="warning" @click="toUser">个人文章</el-link>
          </el-menu-item>

        </el-menu>
      </div>

    </div>
     <div >
      <span v-show="!isLogin"><el-link type="primary"  @click="toLogin">登录</el-link></span>
      <span v-show="isLogin"><el-link type="primary"><el-popover
        width="40"
        placement="bottom"
        trigger="click"
      >
					<el-avatar :size="50" :src="user.avatar"></el-avatar>
					<el-button slot="reference">{{ user.username }}</el-button>
          <ul style="font-size: 5px">
            <li>{{user.username}}</li>
             <li>{{user.lastLogin}}</li>
             <li>{{user.email}}</li>
          </ul>
				</el-popover></el-link> </span>
      &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;
     <span  v-show="!isLogin"><el-link type="primary" @click="toRegister">注册</el-link></span>
      <span v-show="isLogin"><el-link type="danger" @click="logout">退出</el-link></span>
    </div>


  </div>

</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
      user: {
        username: "请先登录",
        avatar: "https://cdn.jsdelivr.net/gh/HimitZH/CDN/images/HCODE.png",
        lastLogin:"",
        email:null,
      },
      isLogin: false,
      url: "https://cdn.jsdelivr.net/gh/HimitZH/CDN/images/HCODE.png"
    };
  },
  methods: {
    //退出登录
    logout() {
      this.$axios.get("user/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        this.$store.commit("rmUserInfo")
        this.$router.push({name: "MainBlog"})
      })
    },
    toUser() {
      this.$router.push({path: '/userBolg/' + this.user.username})
    },
    toLogin(){
      this.$router.push({name:"Login"})
    },
    toRegister(){
      this.$router.push({name:"Register"})
    },
    toEdit() {
      console.log(this.$store.getters.getUserInfo.username)
      if (this.$store.getters.getUserInfo.username){
         this.$router.push({path:"/blog/add"})
      }else{
          this.$router.push({name:"Login"})
      }

    }
  },
  created() {
    if (this.$store.getters.getUserInfo.username) {
      this.user.username = this.$store.getters.getUserInfo.username
      // this.user.avatar = this.$store.getters.getUserInfo.avatar
      this.isLogin = true
      this.$axios.get("/user/getUserByName/"+this.user.username).then(res=>{
        this.user = res.data.data
        console.log(this.user)
      })
    }
  }
};
</script>

<style >
.m-content{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.-menu--horizontal{

}
</style>
