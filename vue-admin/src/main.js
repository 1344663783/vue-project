import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import global from './assets/global.css'
import './assets/fonts/iconfont.css'
import axios from 'axios'
Vue.prototype.$http=axios
axios.defaults.baseURL="http://localhost:8080/api"
axios.interceptors.request.use(config=>{
  config.headers.Authorization = window.sessionStorage.getItem("token")
  return config;
})
Vue.config.productionTip = false
Vue.use(ElementUI);


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
