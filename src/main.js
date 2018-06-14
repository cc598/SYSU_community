// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
axios.defaults.withCredentials=true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.prototype.$http = axios;
Vue.config.productionTip = false;

Vue.use(ElementUI);
/* eslint-disable no-new */
import store from './vuex/store.js'
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  axios,
  store
})
