<script lang="ts">
import { AgGridVue } from 'ag-grid-vue'
import { Component, Vue, Prop } from 'vue-property-decorator'
import { EventBus } from '../event-bus'

// Base component.
@Component({})
export default class Parent extends Vue {
  @Prop()
  // Component ID is needed for sending and receiving component-specific events via the event bus.
  compId!: string

  // Subscribe to a specific event from GWT for this component.
  onEvent (topic: string, callback: Function) {
    EventBus.$on(this.compId + topic, callback)
  }

  // Notify GWT.
  emitEvent (topic: string, data: any) {
    EventBus.$emit(this.compId + topic, data)
  }
}
</script>
