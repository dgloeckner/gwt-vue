<template>
  <div>
    <button v-on:click="incrementCounter" class="gwt-Button sendButton">{{ msg }} {{ count }} times.</button>
    <table>
      <tr v-for="item in lst" :key="item">
        <td>Item: {{ item }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data: function() {
    return {
      lst: [],
      count: 0
    };
  },
  methods: {
    incrementCounter: function(event) {
      middleware.callback("Hello from vue button");
    }
  },
  mounted() {
    middleware.app.$on("send-clicked", () => {
      this.count++;
      this.lst.push(this.count);
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
