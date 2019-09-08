import Vue from "vue";
import App from "./App.vue";
import axios from "axios";
import VueAxios from "vue-axios";
import Buefy from "buefy";
import "buefy/dist/buefy.css";

class AppBootstrap {
  constructor() {
    console.log("HERE1212");
    this.loadVueApp();
  }

  private loadVueApp(): void {
    Vue.config.productionTip = false;
    Vue.use(VueAxios, axios);
    Vue.use(Buefy);

    new Vue({
      render: h => h(App)
    }).$mount("#app");
  }

}

// eslint-disable-next-line no-new
new AppBootstrap()
