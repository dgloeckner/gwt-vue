<template>
  <div>
    <ag-grid-vue
      class="ag-theme-balham grid"
      :gridOptions="gridOptions"
      :rowData="rowData"
      :rowDataChanged="onRowDataChanged"
      rowSelection="multiple"
      rowBuffer="100"
    ></ag-grid-vue>
  </div>
</template>
    
<script>
import { AgGridVue } from "ag-grid-vue";
import { Vue } from "vue";
import { EventBus } from '../event-bus';

export default {
  name: "JsonResult",
  props: ['compId'],
  data: function() {
    return {
      count: 0,
      gridOptions: null,
      rowData: null,
      query: "tetris+language:assembly"
    };
  },
  methods: {
    fetchJSon: function(event) {
      const url = `https://api.github.com/search/repositories?q=${this.query}&sort=stars&order=desc`;
      this.rowData = null;
      this.axios
        .get(url)
        .then(response => {
          console.log(response.data);
          this.rowData = response.data.items;
        });
    },
    createColDefs() {
      return [
        {
          headerName: "Description",
          field: "description",
          sortable: true,
          checkboxSelection: true,
          resizable: true,
          editable: true
        },
        {
          headerName: "Created",
          field: "created_at",
          sortable: true,
          resizable: true
        },
        {
          headerName: "Pushed",
          field: "pushed_at",
          sortable: true,
          resizable: true
        },
        { headerName: "Size", field: "size", sortable: true, resizable: true },
        {
          headerName: "Forks",
          field: "forks",
          sortable: true,
          resizable: true
        },
        {
          headerName: "Watchers",
          field: "watchers",
          sortable: true,
          filter: true,
          resizable: true
        },
        {
          headerName: "Score",
          field: "score",
          sortable: true,
          filter: true,
          resizable: true
        }
      ];
    },
    onRowDataChanged() {
      Vue.nextTick(() => {
        this.gridOptions.api.sizeColumnsToFit();
      });
    }
  },
  created() {
    this.gridOptions = {};
    this.gridOptions.columnDefs = this.createColDefs();
    this.fetchJSon(null);
  },
  mounted() {
    // Subscribe to a specific event from GWT for this component.
    EventBus.$on(this.compId + "gwt-query-changed", data => {
      this.query = data;
      this.fetchJSon(null);
    });
  },
  components: {
    AgGridVue
  }
};
</script>

<style scoped>
.grid {
  height: 455px;
}
@import "../../node_modules/ag-grid-community/dist/styles/ag-grid.css";
@import "../../node_modules/ag-grid-community/dist/styles/ag-theme-balham.css";
</style>