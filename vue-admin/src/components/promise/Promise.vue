<template>
  <div>
    <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>系统管理</el-breadcrumb-item>
    <el-breadcrumb-item>权限管理</el-breadcrumb-item>
  </el-breadcrumb>
  <el-card>
    <el-table :data="promiseList" border>
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="name" label="菜单名称"></el-table-column>
      <el-table-column prop="path" label="菜单路径"></el-table-column>
      <el-table-column prop="level" label="菜单级别">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.level === '0'">一级</el-tag>
          <el-tag type="info" v-else>二级</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        promiseList:[]
      }
    },
    created(){
      this.getPromiseList();
    },
    methods:{
      async getPromiseList(){
        const {data: res} = await this.$http.get('/menu/list')
        console.log(res.result)
        this.promiseList = res.result
      }

    }
  }
</script>
<style lang="less" scoped>

</style>
