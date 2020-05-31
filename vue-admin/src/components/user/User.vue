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
            <el-input placeholder="请输入内容" v-model="query.username" clearable @clear="getUserList">
              <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4"><el-button type="primary" @click="addDialog=true" >添加用户</el-button></el-col>
        </el-row>

        <el-table :data="userList" border>
          <el-table-column type="index"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="sex" :formatter="formatSex" label="性别"></el-table-column>
          <el-table-column prop="age" label="年龄"></el-table-column>
          <el-table-column prop="city" label="所在城市"></el-table-column>
          <el-table-column  label="操作">
            <template slot-scope="scope">
              <el-button type="primary" icon="el-icon-edit" @click="enditUserTo(scope.row)"></el-button>
              <el-button type="danger" icon="el-icon-delete" @click="deleteUser(scope.row)"></el-button>
              <el-tooltip class="item" effect="dark" content="分配角色" placement="top" :enterable="false">
                <el-button type="warning" icon="el-icon-setting" @click="setRole(scope.row)"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>

        <el-dialog title="用户添加" :visible.sync="addDialog" width="300px" @close="closeAddForm('addFormRef')">
          <el-form :model="addForm" :rules="addRules" ref="addFormRef" >
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="addForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password">
              <el-input v-model="addForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('addFormRef')">提交</el-button>
              <el-button @click="resetForm('addFormRef')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

        <el-dialog title="用户修改" :visible.sync="editDialog" width="300px">
          <el-form :model="editForm" :rules="editRules" ref="editFormRef" >
            <el-form-item label="用户名：" prop="username">
              <el-input v-model="editForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitEditForm('editFormRef')">提交</el-button>
              <el-button @click="resetEidtForm('editFormRef')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

        <el-dialog title="分配角色" :visible.sync="roleDialog" width="30%">
          <p>当前用户：{{userInfo.username}}</p>
          <p>当前角色：{{userInfo.roleName}}</p>
          <el-select v-model="roleName" placeholder="请选择">
            <el-option
              v-for="item in roleList"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleName">
            </el-option>
          </el-select>
          <span slot="footer" class="dialog-footer">
        <el-button @click="roleDialog = false">取 消</el-button>
        <el-button type="primary" @click="submitRole">确 定</el-button>
      </span>
        </el-dialog>
      </el-card>

    </div>
</template>
<script>
export default {
  data(){
    return{
      roleList:[],
      roleName:'',
      userInfo:'',
      roleDialog:false,
      editDialog:false,
      addDialog:false,
      addForm:{
        username:'',
        password:''
      },

      editForm:{},

      query:{
        pageNum:1,
        username:''
      },
      userList:[],

      addRules: {
        username: [{required: true, message: '请输入用户名称', trigger: 'blur' },
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' },
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }],
      },
      editRules:{
        username: [{required: true, message: '请输入用户名称', trigger: 'blur' },
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }],
      }
    }
  },
  created () {
    this.getUserList()
  },
  methods:{
    async setRole(row){
      this.roleName=''
      this.userInfo=row
      const  {data: res} =await this.$http.get("/user/roles")
      if (res.code ===20000){
        this.roleList = res.result
      }
      this.roleDialog= true
    },
    async submitRole(){
      if (!this.roleName) return
     const {data: res} = await this.$http.get('/user/updateRole',{
       params:{
        userId:this.userInfo.id,
         roleName:this.roleName
       }
     })
      if (res.code === 20000){
        this.roleDialog=false
        this.getUserList()
        this.$message.success('角色分配成功')
      }else{
        this.$message.error('角色分配失败')
      }
    },
    async deleteUser(row){
      const confirm = await this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error=>error)

      console.log(confirm)
      if (confirm === 'confirm'){
         const {data: res} =await this.$http.get('/user/delete/'+row.id)
          if (res.code === 20000){
            this.getUserList()
            this.$message.success('删除成功')
          }else {
            this.$message.error('删除失败')
          }
      }
    },
    resetEidtForm(editFormRef){
      this.$refs[editFormRef].resetFields();
    },
    submitEditForm(editFormRef){
      this.$refs[editFormRef].validate(async (valid)=>{
        if (valid){
         const {data: res} = await this.$http.post('/user/editUser',this.editForm)
          if (res.code === 20000){
            this.editDialog = false
            this.getUserList()
            this.$.message.success('修改成功')
          }else{
            this.$.message.error('修改失败')
          }
        }
      })
    },
    async enditUserTo(row){
      this.editDialog=true
      const {data: res}  = await this.$http.get('/user/edit/'+row.id)
      if (res.code ===20000){
        this.editForm= res.result
      }else{
        this.$message.error('获取信息失败')
      }
    },
    closeAddForm(addFormRef){
      this.$refs[addFormRef].resetFields();
    },
    submitForm(addFormRef) {
      this.$refs[addFormRef].validate(async (valid) => {
        if (valid) {
          const {data: res} = await this.$http.post('/user/add',this.addForm);
          if (res.code === 20000) {
            this.$message.success('添加成功')
            this.addDialog=false;
            this.getUserList()
          }else{
            this.$message.error('添加失败')
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(addFormRef) {
      this.$refs[addFormRef].resetFields();
    },
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
