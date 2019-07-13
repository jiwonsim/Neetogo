import Vue from 'vue'
import Router from 'vue-router'
import intro from './page/home/intro.vue'
import location from './page/home/location.vue'


Vue.use(Router)

const baseRoutes = [
  {
    path : '/',
    name : 'Intro',
    component : intro
  },
  {
    path : '/location', 
    name : 'Location', 
    component : location
  }
]

export default new Router({
  mode : 'history',
  routes : baseRoutes
});