import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
//import App from './App.vue'
import HelloWorld from "./components/HelloWorld.vue";
import JsonResult from "./components/JsonResultRenderer";
import { EventBus } from "./event-bus";

Vue.config.productionTip = false;
Vue.use(VueAxios, axios);

/*new Vue({
  render: h => h(App),
}).$mount('#app');*/

(function(global) {
  const apps = new Map();

  const createComponent = (id, type) => {
    var compType = null;
    switch (type) {
      case "button-counter":
        compType = HelloWorld;
        break;
      case "json-result":
        compType = JsonResult;
        break;
      default:
        throw "Unknown component " + type;
    }
    var comp = new Vue({
      render: h =>
        h(compType, {
          props: {
            compId: id
          }
        })
    });

    comp.$mount("#" + id);
    apps.set(id, comp);
  };

  // Send GWT events to vue.js components.
  const emitEvent = (id, topic, data) => {
    EventBus.$emit(id + topic, data);
  };

  // Subscribe to vue.js events from GWT.
  const onEvent = (id, topic, callback) => {
    EventBus.$on(id + topic, callback);
  };

  const Middleware = {
    apps,
    emitEvent,
    onEvent,
    createComponent
  };

  global.middleware = Middleware;
})(window);
