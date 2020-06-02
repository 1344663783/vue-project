module.exports = {
  devServer: {
    port: 8888,
    open: true
  },
  chainWebpack: config=>{
    config.when(process.env.NODE_ENV === 'production',config=>{
      config.entry('app').clear().add('./src/main-prod.js')
    })

    config.when(process.env.NODE_ENV === 'development',config=>{
      config.entry('app').clear().add('./src/main-dev.js')
    })
  }


}
