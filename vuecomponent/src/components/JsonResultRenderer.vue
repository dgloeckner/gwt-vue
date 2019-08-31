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

export default {
  name: "JsonResult",
  data: function() {
    return {
      count: 0,
      gridOptions: null,
      rowData: null
    };
  },
  methods: {
    fetchJSon: function(event) {
      this.toppings = null;
      this.axios
        .get(
          "https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc"
        )
        .then(response => {
          console.log(response.data);
          this.rowData = response.data.items;
        });
    },
    createColDefs() {
      return [
        { headerName: "Description", field: "description", sortable: true, checkboxSelection: true,  resizable: true, editable: true },
        { headerName: "Created", field: "created_at", sortable: true,  resizable: true },
        { headerName: "Pushed", field: "pushed_at", sortable: true,  resizable: true  },
        { headerName: "Size", field: "size", sortable: true,  resizable: true  },
        { headerName: "Forks", field: "forks", sortable: true,  resizable: true  },
        { headerName: "Watchers", field: "watchers", sortable: true, filter: true,  resizable: true  },
        { headerName: "Score", field: "score", sortable: true, filter: true,  resizable: true  }
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
  components: {
    AgGridVue
  }
};
</script>

<style scoped>
.grid {
  height: 455px;
}
</style>