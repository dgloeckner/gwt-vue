<script lang="ts">
import { AgGridVue } from 'ag-grid-vue'
import { Component, Vue, Prop } from 'vue-property-decorator'
import { EventBus } from '../event-bus'

interface TopicWithCallback {
    topic : string;
    callback: Function;
}

// Base component.
@Component({})
export default class Parent extends Vue {
  subscribedCallbacks: Array<TopicWithCallback> = []

  @Prop()
  // Component ID is needed for sending and receiving component-specific events via the event bus.
  compId!: string

  // Subscribe to a specific event from GWT for this component.
  onEvent (topic: string, callback: Function) {
    const eventTopic = this.compId + topic
    this.subscribedCallbacks.push({ topic: eventTopic, callback })
    EventBus.$on(eventTopic, callback)
  }

  // Subscribe to a global event from GWT for this component.
  onGlobalEvent (topic: string, callback: Function) {
    this.subscribedCallbacks.push({ topic, callback })
    EventBus.$on(topic, callback)
  }

  // Notify GWT.
  emitEvent (topic: string, data: any) {
    EventBus.$emit(this.compId + topic, data)
  }

  beforeDestroy () {
    // Unregister all subscribed callbacks
    for (let scb of this.subscribedCallbacks) {
      EventBus.$off(scb.topic, scb.callback)
    }
  }
}
</script>
