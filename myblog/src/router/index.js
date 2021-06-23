import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Register from "../views/Register";
import RestPwd from "../views/RestPwd";
import MainBlog from "../views/MainBlog";
import UserBlog from "../views/UserBlog";
import BlogDetail from "../views/BlogDetail";
import EditBlog from "../views/EditBlog";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: {name: "MainBlog"}
    },
    {
      path: '/login',
      name: "Login",
      component: Login
    },
    {
      path: '/register',
      name: "Register",
      component: Register
    },
    {
      path: "/restPwd",
      name: "RestPwd",
      component: RestPwd
    },
    {
      path: "/MainBlog",
      name: "MainBlog",
      component: MainBlog
    },
    {
      path: "/userBolg/:username",
      name: "userBolg",
      component: UserBlog
    },
    {
      path:"/blogDetail/:blogId",
      name:"BlogDetail",
      component:BlogDetail
    },
    //编辑博客的路由
    {
     path:"/blog/:blogId/edit",
      name:"EditBlog",
      component:EditBlog,
      meta:{
       requireAuth:true
      }
    },
    //添加博客的路由
    {
      path:"/blog/add",
      name:"BlogEdit",
      component:EditBlog,
      meta:{ //需要登录
        requireAuth: true
      }
    }
  ]
})
