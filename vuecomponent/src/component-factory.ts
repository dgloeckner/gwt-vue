import Vue from 'vue';

import ButtonCounter from './components/ButtonCounter.vue';
import JsonResult from './components/JsonResultRenderer.vue';
import SubscribedLabel from './components/SubscribedLabel.vue';
import GithubApiService from './services/HttpGithubApi';
import IGithubApi from './services/IGithubApi';

export default class ComponentFactory {
  githubApi: IGithubApi = new GithubApiService()

  create(id: string, type: string): Vue {
    var compType: any;
    switch (type) {
      case "button-counter":
        compType = ButtonCounter;
        break;
      case "json-result":
        compType = JsonResult;
        break;
      case "subscribed-label":
        compType = SubscribedLabel;
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
        githubApi: this.githubApi
      }
    });

    comp.$mount("#" + id);
    return comp
  }
}
