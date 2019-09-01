<template>
  <div id="app">
    <GwtMockEnvironment/>
    <h2>Vue components</h2>
    <button-counter compId="buttonCounter"></button-counter>
    <JsonResult compId="jsonResults"/>
  </div>
</template>

<script>
// Import components
import ButtonCounter from '../../vuecomponent/src/components/ButtonCounter'
import JsonResult from '../../vuecomponent/src/components/JsonResultRenderer'
import GwtMockEnvironment from './components/GwtMockEnvironment';

export default {
  name: 'app',
  components: {
    ButtonCounter,
    JsonResult,
    GwtMockEnvironment
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
