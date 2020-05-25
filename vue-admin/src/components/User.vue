<template>
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-input placeholder="请输入内容">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4"><el-button type="primary">添加用户</el-button></el-col>
        </el-row>

        <el-table :data="userList" border>
          <el-table-column type="index"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="sex" :formatter="formatSex" label="性别"></el-table-column>
          <el-table-column prop="age" label="年龄"></el-table-column>
          <el-table-column prop="city" label="所在城市"></el-table-column>
          <el-table-column  label="操作">
            <template>
              <el-button type="primary" icon="el-icon-edit"></el-button>
              <el-button type="danger" icon="el-icon-delete"></el-button>
              <el-tooltip class="item" effect="dark" content="分配角色" placement="top" :enterable="false">
                <el-button type="warning" icon="el-icon-setting"></el-button>
              </el-tooltip>
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
      query:{
        pageNum:1,
        username:''
      },
      userList:[]
    }
  },
  created () {
    this.getUserList()
  },
  methods:{
    async getUserList(){
      const {data: res} = await this.$http.post('/user/list',this.query)
      if(res.code===20000){
        this.userList=res.result
      }else{
        this.$message.error('获取数据失败')
      }
    },
    formatSex(row){
      return row.sex===1? '男':'女'
    }
  },

}
</script>

<style lang="less" scoped>

</style>
