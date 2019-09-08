<template>
  <div>
    <ag-grid-vue
      :grid-options="gridOptions"
      :row-data="rowData"
      :row-data-changed="onRowDataChanged"
      class="ag-theme-balham grid"
      row-selection="multiple"
      row-buffer="100"
    />
  </div>
</template>

<script lang="ts">
import Parent from './Parent.vue'
import { AgGridVue } from 'ag-grid-vue'
import { Component, Inject, Vue } from 'vue-property-decorator'
import IGithubApi from '../services/IGithubApi'
import ColumnDefinitions from './ColumnDefinitions'

@Component({
  name: 'JsonResult',
  components: {
    AgGridVue
  }
})
export default class JsonResultRenderer extends Parent {
  // Inject services
  @Inject()
  githubApi!: IGithubApi

  rowData: any = []
  query: String = 'tetris+language:assembly'
  gridOptions: any

  fetchJSon () {
    this.rowData = []
    this.githubApi.fetchResults(this.query)
      .then(response => {
        console.log(response.data)
        this.rowData = response.data.items
      })
  }
  onRowDataChanged () {
    Vue.nextTick(() => {
      this.gridOptions.api.sizeColumnsToFit()
    })
  }

  created () {
    this.gridOptions = {}
    this.gridOptions.columnDefs = ColumnDefinitions.createColDefs()
    this.fetchJSon()
  }

  mounted () {
    this.onEvent('gwt-query-changed', (data: any) => {
      this.query = data
      this.fetchJSon()
    })
  }
}
</script>

<style scoped>
.grid {
  height: 455px;
}

@import "../../node_modules/ag-grid-community/dist/styles/ag-grid.css";
@import "../../node_modules/ag-grid-community/dist/styles/ag-theme-balham.css";
</style>
