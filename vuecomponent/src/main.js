import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'

Vue.config.productionTip = false;
Vue.use(VueAxios, axios);

/*new Vue({
  render: h => h(App),
}).$mount('#app');*/

(function(global) {

  const app = new Vue({
    render: h => h(App),
  });

  // Called from GWT
  const emitEvent = (topic, data) => {
    // eslint-disable-next-line no-console
      console.log('Send clicked VUE');
    app.$emit(topic, data);
  }

  const addCallback = (callback) => {
    global.middleware.callback = callback;
  }

  const Middleware = {
      app,
      emitEvent,
      addCallback
    }

  global.middleware = Middleware;
  //console.log('Middleware was set ' + global.middleware.app);
  //console.log(global.middleware.app.$mount)
})(window);