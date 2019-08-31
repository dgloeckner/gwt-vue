<template>
  <div>
    <button v-on:click="incrementCounter" class="gwt-Button sendButton">{{ msg }} {{ count }} times.</button>
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
      count: 0
    };
  },
  methods: {
    incrementCounter: function(event) {
      middleware.callback("Hello from vue button");
    }
  },
  mounted() {
    if (middleware) {
       middleware.app.$on("send-clicked", (data) => {
         this.count++;
       });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button {
  border: 1px solid red;
}
</style>
