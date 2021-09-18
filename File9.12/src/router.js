import Vue from 'vue'
import Router from 'vue-router'
// import Login from './components/Login.vue'
import Home from './components/Home.vue'
import Welcome from './components/Welcome.vue'
import Users from './components/user/Users.vue'
import Parts from './components/user/Parts.vue'
import Logs from './components/user/Logs.vue'
import News from './components/warn/News.vue'
import Persons from './components/warn/Persons.vue'
import Deploys from './components/warn/Deploys.vue'
import Lists from './components/equipment/Lists.vue'
import Looklists from './components/equipment/Looklists.vue'

Vue.use(Router)

const router = new Router({
  routes: [
    // { path: '/', redirect: '/login' },
    { path: '/', redirect: '/home' },
    // { path: '/login', component: Login },
    {
      path: '/home',
      component: Home,
      redirect: '/welcome',
      children: [
        { path: '/welcome', component: Welcome },
        { path: '/parts', component: Parts },
        { path: '/logs', component: Logs },
        { path: '/users', component: Users },
        { path: '/news', component: News },
        { path: '/deploys', component: Deploys },
        { path: '/persons', component: Persons },
        { path: '/list', component: Lists },
        { path: '/looklist', component: Looklists }


      ]
    }
  ]
})

// 挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//   // to 将要访问的路径
//   // from 代表从哪个路径跳转而来
//   // next 是一个函数，表示放行
//   //     next()  放行    next('/login')  强制跳转

//   if (to.path === '/login') return next()
//   // 获取token
//   const tokenStr = window.sessionStorage.getItem('token')
//   if (!tokenStr) return next('/login')
//   next()
// })

export default router
