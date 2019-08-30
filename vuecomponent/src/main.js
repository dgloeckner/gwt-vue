import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false;

//new Vue({
//  render: h => h(App),
//}).$mount('#app');

(function(global) {

  const app = new Vue({
    render: h => h(App),
  });

  // Called from GWT
  const sendClicked = () => {
    // eslint-disable-next-line no-console
      console.log('Send clicked VUE');
    app.$emit('send-clicked');
  }

  const addCallback = (callback) => {
    global.middleware.callback = callback;
  }

  const Middleware = {
      app,
      sendClicked,
      addCallback
    }

  global.middleware = Middleware;
  //console.log('Middleware was set ' + global.middleware.app);
  //console.log(global.middleware.app.$mount)
})(window);