module.exports = {
  //baseUrl: "./",
  //css: {
  //  extract: false
  //},
  chainWebpack: config => {
    config
      .plugin("html")
      .tap(args => {
       // If GWT env variable is set we'll use a different entry point html.
        if (process.env.GWT) {
          args[0].template = "./public/parent.html"
        }
        return args
      })
  }
}