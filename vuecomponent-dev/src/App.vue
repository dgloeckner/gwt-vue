<template>
  <div id="app">
    <GwtMockEnvironment />
    <h2>Vue components</h2>
    <ButtonCounter compId='buttonCounter' />
    <JsonResultRenderer compId='jsonResults' />
  </div>
</template>

<script lang="ts">
import axios from 'axios'
import Vue from 'vue'
import { Component, Provide } from 'vue-property-decorator'
import ButtonCounter from '../../vuecomponent/src/components/ButtonCounter.vue'
import JsonResultRenderer from '../../vuecomponent/src/components/JsonResultRenderer.vue'
import GwtMockEnvironment from './components/GwtMockEnvironment.vue'
import IGithubApi from '../../vuecomponent/src/services/IGithubApi'
import GithubApiService from '../../vuecomponent/src/services/HttpGithubApi'

@Component({
  name: 'App',
  components: {
    ButtonCounter,
    JsonResultRenderer,
    GwtMockEnvironment
  }
})
export default class App extends Vue {
  // Here we bind services for dependency injection.
  // Those can be mocked for testing.
  @Provide()
  githubApi: IGithubApi = new GithubApiService();
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
