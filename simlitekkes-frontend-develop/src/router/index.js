/**
 * Vue Router
 *
 * @library
 *
 * https://router.vuejs.org/en/
 */

// Lib imports
import Vue from 'vue'
import VueAnalytics from 'vue-analytics'
import Router from 'vue-router'
import Meta from 'vue-meta'

// Routes
import paths from './paths'

// function route (path, view, name) {
//   return {
//     name: name || view,
//     path,
//     component: (resovle) => import(
//       `@/views/${view}.vue`
//     ).then(resovle)
//   }
// }

Vue.use(Router)

// Create a new router
const router = new Router({
  mode: 'history',
  // routes: paths.map(path => route(path.path, path.view, path.name)).concat([
  //   { path: '*', redirect: '/' }
  // ]),
  routes: paths,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    }
    if (to.hash) {
      return { selector: to.hash }
    }
    return { x: 0, y: 0 }
  }
})

Vue.use(Meta)

// Bootstrap Analytics
// Set in .env
// https://github.com/MatteoGabriele/vue-analytics
if (process.env.GOOGLE_ANALYTICS) {
  Vue.use(VueAnalytics, {
    id: process.env.GOOGLE_ANALYTICS,
    router,
    autoTracking: {
      page: process.env.NODE_ENV !== 'development'
    }
  })
}

router.beforeEach((to, from, next) => {
  console.log("url guard called..")
  const loggedIn = localStorage.getItem('user')
  const userData = JSON.parse(loggedIn)

  // console.log("router to : ", to)
  // if (to.name === 'USER') {
  //   console.log("match with name")
  // }
  // if (to.meta.kode === 'USER') {
  //   console.log("match with meta kode")
  // }

  if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
    next('/')
  }
  if (to.matched.some(record => record.meta.loginForm) && loggedIn) {
    next('/home')
  }
  
  if (!to.matched.length) {
    next('/404');
  } 
  if (to.matched.length && loggedIn && to.fullPath != '/403' && userData.menus[0].menuCode) {
    console.log("checking privilege menu..")
    let isAllow = false
    for (var i in userData.menus) {
      // console.log(userData.menus[i].menuCode + " - " + to.name)
      if (to.name === userData.menus[i].menuCode) {
        isAllow = true
      }
    }
    console.log("menu is allow : " + isAllow)
    if (!isAllow){
      console.log("access rejected!", next())  
      next('/403')
    }
    console.log("menu is allow : " + isAllow) 
  }
  next()
})

export default router
