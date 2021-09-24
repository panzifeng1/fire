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
import Information from './components/equipment/equipDetails/Information.vue'
import Message from './components/equipment/equipDetails/Message.vue'
import Notice from './components/equipment/equipDetails/Notice.vue'
import Preset from './components/equipment/equipDetails/Preset.vue'
import Visual from './components/equipment/equipDetails/Visual.vue'
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
        { path: '/looklist/:id', component: Looklists },
        { path: '/looklist/:id/information', component: Information },
        { path: '/looklist/:id/message', component: Message },
        { path: '/looklist/:id/notice', component: Notice },
        { path: '/looklist/:id/preset', component: Preset },
        { path: '/looklist/:id/visual', component: Visual },
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
