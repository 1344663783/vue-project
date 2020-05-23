<template>
  <el-container>
    <el-header><div>恒大房产管理系统</div><el-button @click="logout">退出</el-button></el-header>
    <el-container>
      <el-aside :width="isCollapse===true? '64px':'200px'">
        <div class="toggle-bnt" @click="toggle">|||</div>
        <el-menu :collapse="isCollapse" :collapse-transition="false" router>
          <el-submenu :index="'/'+item.path" v-for="item in menu" :key="item.id" >
            <template slot="title">
              <i :class="item.icon"></i>
              <span>{{item.name}}</span>
            </template>
            <el-menu-item :index="'/'+itemChildren.path" v-for="itemChildren in item.children" :key="itemChildren.id">
              <span>{{itemChildren.name}}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
  export default {
    data(){
      return{
        menu:[],
        isCollapse:false
      }
    },
    created(){
      this.getMenuList();
    },
    methods:{
      async getMenuList(){
        const {data: res} = await this.$http.get("/menu")
        if (res.code === 20000 ){
          this.menu = res.result
        }else{
          this.$message.error(message)
        }
      },
      logout(){
        window.sessionStorage.clear()
        this.$router.push('/login')
      },
      toggle(){
        this.isCollapse= !this.isCollapse
      }
    }
  }
</script>
<style lang="less" scoped>
  .el-header{
    background-color: #D5D5D5;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .el-aside{
  background-color: #CCCCCC;
  }
  .el-main{
    background-color: #F7F7F7
  }
  .el-container{
    height: 100%;
  }
  .logout{
    margin-top: 10px;
    float: right;
  }
  .iconfont{
    margin-right: 10px;
  }
.toggle-bnt{
  text-align: center;
  line-height: 24px;
  cursor: pointer;
}


</style>
