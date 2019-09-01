<template>
  <div>
    <button v-on:click="incrementCounter" class="gwt-Button sendButton">GWT button was clicked {{ count }} times.</button>
  </div>
</template>

<script>
import { EventBus } from '../event-bus';

export default {
  name: "HelloWorld",
  // Component ID is needed for sending and receiving component-specific events via the event bus.
  props: ['compId'],
  data: function() {
    return {
      count: 0
    };
  },
  methods: {
    // Notify GWT.
    incrementCounter: function(event) {
      EventBus.$emit(this.compId + "vue-send-clicked", "Hello from vue button");
    }
  },
  mounted() {
    // Subscribe to a specific event from GWT for this component.
    EventBus.$on(this.compId + "gwt-send-clicked", data => {
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
