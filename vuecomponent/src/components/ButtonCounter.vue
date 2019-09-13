<template>
  <div>
    <button
      class="gwt-Button sendButton"
      @click="incrementCounter"
    >
      Vue.js - GWT button was clicked {{ count }} times.
    </button>
  </div>
</template>

<script lang="ts">
import Parent from './Parent.vue'
import { Component } from 'vue-property-decorator'

@Component({
  name: 'HelloWorld'
})
export default class ButtonCounter extends Parent {
  count: number = 0;

  incrementCounter () {
    this.emitEvent('vue-send-clicked', 'Hello from vue button')
  }

  mounted () {
    this.onEvent('gwt-send-clicked', (data: any) => {
      this.count++
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button {
  border: 1px solid red;
}
</style>
