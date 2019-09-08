import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import ButtonCounter from "./components/ButtonCounter.vue";
import JsonResult from "./components/JsonResultRenderer.vue";
import { EventBus } from "./event-bus";
import IGithubApi from './services/IGithubApi';
import GithubApiService from './services/HttpGithubApi';

Vue.config.productionTip = false;
Vue.use(VueAxios, axios);

// Extend Window type with our property.
declare global {
  interface Window { Middleware: any; }
}

// Define the middleware for Vue / GWT interaction.
(function(window) {
  const components = new Map();

  const githubApi: IGithubApi = new GithubApiService()

  const createComponent = (id: any, type: any) => {
    var compType: any;
    switch (type) {
      case "button-counter":
        compType = ButtonCounter;
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
        }),
      provide: {
        // Here we bind services for dependency injection.
        // Those can be mocked for testing.
        githubApi
      }
    });

    comp.$mount("#" + id);
    components.set(id, comp);
  };

  // Send GWT events to vue.js components.
  const emitEvent = (id: string, topic: string, data: any) => {
    EventBus.$emit(id + topic, data);
  };

  // Subscribe to vue.js events from GWT.
  const onEvent = (id: string, topic: string, callback: Function) => {
    EventBus.$on(id + topic, callback);
  };

  const Middleware = {
    components,
    emitEvent,
    onEvent,
    createComponent
  };

  // Register the middleware in global scope.
  window.Middleware = Middleware;
})(window);
