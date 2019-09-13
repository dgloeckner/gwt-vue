import Vue from "vue"
import axios from "axios"
import VueAxios from "vue-axios"
import { EventBus } from "./event-bus"
import ComponentFactory from './component-factory'

Vue.config.productionTip = false
Vue.use(VueAxios, axios)

// Extend Window type with our property.
declare global {
  interface Window { Middleware: any; }
}

// Define the middleware for Vue / GWT interaction.
(function (window) {

  const components: Map<string, Vue> = new Map()

  const componentFactory = new ComponentFactory()

  const createComponent = (id: string, type: string) => {
    const comp = componentFactory.create(id, type)
    components.set(id, comp)
    return comp
  }

  const disposeComponent = (id: string) => {
    const comp = components.get(id)
    if (comp) {
      console.log('Destroying component ' + id)
      comp.$destroy()
    }
  }

  // Send GWT events to a specific vue.js component.
  const emitEvent = (id: string, topic: string, data: any) => {
    EventBus.$emit(id + topic, data);
  }

  // Send GWT events to vue.js components.
  const emitGlobalEvent = (topic: string, data: any) => {
    EventBus.$emit(topic, data);
  }

  // Subscribe to vue.js events from GWT.
  const onEvent = (id: string, topic: string, callback: Function) => {
    EventBus.$on(id + topic, callback);
  }

  const Middleware = {
    emitEvent,
    emitGlobalEvent,
    onEvent,
    createComponent,
    disposeComponent
  }

  // Register the middleware in global scope.
  window.Middleware = Middleware
})(window)
