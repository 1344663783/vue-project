<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-table :data="roleList" border>
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row :class="['tableborder','vcenter']" v-for="(item,index) in scope.row.children" :key="index">
              <el-col :span="5" closable>
                <el-tag closable>{{item.name}}<i class="el-icon-caret-right"></i></el-tag>
              </el-col>
              <el-col :span="10" >
                <el-row :class="['tableborder','vcenter']"  v-for="(item1,index) in item.children" :key="index">
                  <el-col>
                    <el-tag closable @close="removePromise(scope.row,item1.id)">{{item1.name}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="roleName" label="角色名称"></el-table-column>
        <el-table-column prop="desc" label="角色描述"></el-table-column>
        <el-table-column  label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" @click="enditUserTo(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" @click="deleteUser(scope.row)"></el-button>
            <el-tooltip class="item" effect="dark" content="分配权限" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" @click="showPromiseDialog(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog title="分配权限" :visible.sync="promiseDialog" width="30%">
      <el-tree ref="treeRef"
        :data="promiseList"
        show-checkbox
        node-key="id"
        :default-checked-keys="checkedKeys"
        default-expand-all
        :props="defaultProps">
      </el-tree>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitPromise">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        checkedKeys:[],
        promiseList:[],
        promiseDialog:false,
        roleList:[],
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        roleId:''

      }
    },
    created(){
      this.getRoleList()
    },
    methods:{

      async submitPromise(){
          const str = [
            ...this.$refs.treeRef.getCheckedKeys(),
              ...this.$refs.treeRef.getHalfCheckedKeys()
          ]
       let idstr = str.join(",")
       const {data: res} =  await this.$http.get('/role/changePromise',{
          params:{
            roleId:this.roleId,
            menuId:idstr
          }
        })
        if (res.code === 20000){
          this.getRoleList()
          this.promiseDialog=false
        }
      },

      async showPromiseDialog(row){
        this.roleId = row.roleId
        const {data: res} = await this.$http.get('/menu/tree')
        const {data: res1} =await this.$http.get("/getRolePromise/"+row.roleId)
        if (res1.code === 20000){
          this.checkedKeys = res1.result
        }
        if (res.code ===20000){
          this.promiseList = res.result
        }
        this.promiseDialog=true
      },

      async removePromise(row,menuId){
        const {data: res} = await this.$http.get("/role/deletePromise",{
          params:{
            roleId:row.roleId,
            menuId:menuId
          }
        });

        if (res.code === 20000){
          // console.log(res.result)
          row.children = res.result.children
        }

      },
     async getRoleList(){
       const {data: res} = await this.$http.get('/user/roles')
      if (res.code === 20000){
        this.roleList = res.result
      }else{
        this.$message.error('获取数据失败')
      }
     }
    }

  }
</script>
<style lang="less" scoped>
  .el-col{
    margin: 7px;
  }
  .tableborder{
    border-bottom: 1px #eeeeee solid;
  }
  .vcenter{
    display: flex;
    align-items: center;
  }
</style>
