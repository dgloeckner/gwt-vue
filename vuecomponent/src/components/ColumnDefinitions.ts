export default class ColumnDefinitions {
  static createColDefs() {
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
      {
        headerName: "Size",
        field: "size",
        sortable: true,
        resizable: true
      },
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
  }
}
