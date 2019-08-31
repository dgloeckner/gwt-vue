<template>
  <div id="app">
    <HelloWorld msg="vue.js - you clicked the GWT button "/>
    <JsonResult/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import JsonResult from './components/JsonResultRenderer'

export default {
  name: 'app',
  components: {
    HelloWorld,
    JsonResult
  }
}
</script>

<style>
  @import "../node_modules/ag-grid-community/dist/styles/ag-grid.css";
  @import "../node_modules/ag-grid-community/dist/styles/ag-theme-balham.css";
</style>
