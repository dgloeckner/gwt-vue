<template>
  <div>
    <h2>GWT mock environment</h2>
    <table>
      <tr>
        <td>Synced button</td>
        <td>
          <button v-on:click="incrementCounter" class="gwt-Button sendButton">Other button was clicked {{ count }} times.</button>
        </td>
      </tr>
      <tr>
        <td>Search</td>
        <td>
          <input v-model="query" @keyup.enter="queryChanged"
            placeholder="Search string" />
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { EventBus } from "../../../vuecomponent/src/event-bus";

export default {
  name: "GwtMockEnvironment",
  // Component ID is needed for sending and receiving component-specific events via the event bus.
  props: ["compId"],
  data: function() {
    return { 
        query: null,
        count: 0
    };
  },
  methods: {
    incrementCounter: function(event) {
      EventBus.$emit("buttonCountergwt-send-clicked", "Whatever");
    },
    queryChanged: function(event) {
        EventBus.$emit("jsonResultsgwt-query-changed", this.query);
    }
  },
  mounted() {
    EventBus.$on("buttonCountervue-send-clicked", data => {
      console.log("Here2");
      this.count++;
    });
  }
};
</script>