<template>
  <div>
    <button v-on:click="incrementCounter" class="gwt-Button sendButton">GWT button was clicked {{ count }} times.</button>
  </div>
</template>

<script>
import { EventBus } from '../event-bus';

export default {
  name: "HelloWorld",
  props: ['compId'],
  data: function() {
    return {
      count: 0
    };
  },
  methods: {
    incrementCounter: function(event) {
      middleware.callback("Hello from vue button");
    }
  },
  mounted() {
    // Subscribe to a specific event for this component.
    EventBus.$on(this.compId + "send-clicked", data => {
      this.count++;
    });
    
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button {
  border: 1px solid red;
}
</style>
