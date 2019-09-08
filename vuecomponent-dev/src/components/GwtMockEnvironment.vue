<template>
  <div>
    <h2>GWT mock environment</h2>
    <table>
      <tr>
        <td>Synced button</td>
        <td>
          <button
            class="gwt-Button sendButton"
            @click="incrementCounter"
          >
            Other button was clicked {{ count }} times.
          </button>
        </td>
      </tr>
      <tr>
        <td>Search</td>
        <td>
          <input
            v-model="query"
            placeholder="Search string"
            @keyup.enter="queryChanged"
          >
        </td>
      </tr>
    </table>
  </div>
</template>

<script lang="ts">
import { EventBus } from '../../../vuecomponent/src/event-bus'
import { Component, Vue, Prop } from 'vue-property-decorator'

@Component({
  name: 'GwtMockEnvironment'
})
export default class GwtMockEnvironment extends Vue {
  @Prop()
  // Component ID is needed for sending and receiving component-specific events via the event bus.
  compId!: String;
  query: String = '';
  count = 0;

  mounted () {
    EventBus.$on('buttonCountervue-send-clicked', (data: any) => {
      this.count++
    })
  }

  incrementCounter () {
    EventBus.$emit('buttonCountergwt-send-clicked', 'Whatever')
  }

  queryChanged () {
    EventBus.$emit('jsonResultsgwt-query-changed', this.query)
  }
}
</script>
