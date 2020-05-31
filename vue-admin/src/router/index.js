import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/login.vue'
import Home from '../components/home.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/user/User.vue'
import Promise from '../components/promise/Promise.vue'
import Role from '../components/role/Role.vue'
Vue.use(VueRouter)

const routes = [
  {path:'/',redirect:'/login'},
  {path:'/login',component:Login},
  { path:'/home',
    component:Home,
    redirect:'/welcome',
    children:[
      {path:'/welcome',component:Welcome},
      {path:'/user',component:User},
      {path:'/promise',component:Promise},
      {path:'/role',component:Role},
      ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
  //to 到哪里
  //from 从哪来
  //next 放行
  if (to.path === '/login') return next()
  let token = window.sessionStorage.getItem("token")
  if(!token) return next('/login')
  next()
})

export default router
