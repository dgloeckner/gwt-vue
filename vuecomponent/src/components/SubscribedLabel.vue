<template>
  <div class="subscribed-label">
    {{ text }}
  </div>
</template>

<script lang="ts">
import Parent from './Parent.vue'
import { Component } from 'vue-property-decorator'

@Component({
  name: 'SubscribedLabel'
})
export default class SubscribedLabel extends Parent {
  text: string = '';

  mounted () {
    this.onGlobalEvent('label-text-changed', (text: string) => {
      this.text = text
      // Log this to demonstrate that we're not leaking subscriptions.
      console.log('Text changed...')
    })
  }
}
</script>

<style scoped>
.subscribed-label {
  height: 100px;
  background-color: red;
}
</style>
