<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预警管理</el-breadcrumb-item>
      <el-breadcrumb-item>人员管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图 -->
    <el-card>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item  >
        <el-input  placeholder="请输入员工名称" clearable @clear="getUserList"></el-input>
      </el-form-item>
     <el-form-item >
      <el-select placeholder="请选择所属部门" v-model="apartment">
    </el-select>
    </el-form-item>
    <el-form-item>
    <el-button type="primary" >查询</el-button>
  </el-form-item>
  <el-form-item>
    <el-button type="default" >清空</el-button>
  </el-form-item>
  <el-form-item>
   <el-button type="primary" @click="addDialogVisible = true">添加员工</el-button>
  </el-form-item>
</el-form>
<el-table :data="userlist" border stripe>
        <el-table-column label="序号" type="index" align="center" prop=""></el-table-column>
        <el-table-column label="名字" prop="name" align="center"></el-table-column>
        <el-table-column label="编号" prop="num" align="center"></el-table-column>
        <el-table-column label="部门" prop="department" align="center"></el-table-column>
        <el-table-column label="手机" prop="phone" align="center"></el-table-column>
         <el-table-column label="就职时间" prop="createTime" align="center"></el-table-column>
         <el-table-column label="操作" width="180px" align="center">
                    <template slot-scope="scope">
                        <!-- 修改按钮 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row)"></el-button>
                        <!-- 删除按钮 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserByid(scope.row.id)"></el-button>
                        <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                    </template>
                </el-table-column>
      </el-table>

      <!-- 分页 -->
   <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.page"
      :page-sizes="[1, 5, 10, 20]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    </el-card>

    <!-- 添加用户的对话框 -->
        <el-dialog
        title="添加用户"
        :visible.sync="addDialogVisible"
        width="50%" @close="addDialogClosed">
           <!-- 内容主体区域 -->
          <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="110px">
            <el-form-item label="人员编号" prop="num">
              <el-input v-model="addForm.num"></el-input>
            </el-form-item>
            <el-form-item label="人员姓名" prop="name">
              <el-input v-model="addForm.name"></el-input>
            </el-form-item>
            <el-form-item label="所属部门" prop="department">
              <el-select placeholder="请选择所属部门" v-model="addForm.department"></el-select>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="addForm.phone"></el-input>
            </el-form-item>
          </el-form>
           <!-- 底部区域 -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addUser">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 修改员工的对话框 -->
        <el-dialog
        title="修改员工"
        :visible.sync="editDialogVisible"
        width="50%" @close="editDialogClosed">
           <!-- 内容主体区域 -->
           <!-- 内容主体区域 -->
          <el-form :model="editForm" :rules="editFormRules" label-width="110px" ref="editFormRef">
            <el-form-item label="ID">
              <el-input v-model="editForm.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="员工姓名" prop="name">
              <el-input v-model="editForm.name"></el-input>
            </el-form-item>
            <el-form-item label="员工编号" prop="num">
              <el-input v-model="editForm.num"></el-input>
            </el-form-item>
            <el-form-item label="所属部门" prop="department">
              <el-input v-model="editForm.department"></el-input>
            </el-form-item>
            <el-form-item label="员工信息" prop="note">
              <el-input type="textarea" v-model="editForm.note"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
              <el-input v-model="editForm.phone"></el-input>
            </el-form-item>
          </el-form>
           <!-- 底部区域 -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="editUserInfo">确 定</el-button>
          </span>
        </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      apartment: '',
      // 获取员工列表的参数对象
      queryInfo: {
        page: 1,
        pageSize: 10
      },
      // 员工总数
      total: 0,
      // 员工列表
      userlist: [],
      // 控制添加员工对话框的显示与隐藏
      addDialogVisible: false,
      // 添加员工的表单数据
      addForm: {
        num: '',
        name: '',
        note: '',
        department: '',
        phone: ''
      },
      // 添加表单的验证规则对象
      addFormRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入人员编码', trigger: 'blur' }
        ],
        note: [
          { required: true, message: '请输入人员描述信息', trigger: 'blur' }
        ]
      },
      editForm: {
        id: '',
        name: '',
        num: '',
        phone: '',
        apartment: '',
        note: ''
      },
      editFormRules: {
        name: [
          { required: true, message: '请输入员工姓名', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '请输入员工编号', trigger: 'blur' }
        ],
        department: [
          { required: true, message: '请输入所属部门', trigger: 'blur' }
        ],
        note: [
          { required: true, message: '请输入员工信息', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入员工联系方式', trigger: 'blur' }
        ]
      },
      // 控制修改员工对话框的显示与隐藏
      editDialogVisible: false,
      // 删除员工的ID
      id: ''
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    async getUserList() {
      const { data: res } = await this.$http.get('/management/list', { params: this.queryInfo })
      if (res.code !== 200) return this.$mssage.console.error('获取员工列表失败！')
      this.userlist = res.data
      this.total = res.total
      console.log(res)
    },
    // 监听pageSize 改变的事件
    handleSizeChange(newSize) {
      console.log(newSize)
      this.queryInfo.pageSize = newSize
      this.getUserList()
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      console.log(newPage)
      this.queryInfo.page = newPage
      // 重新获取数据
      this.getUserList()
    },
    // 监听添加用户对话框关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮，添加新用户
    addUser() {
      // 添加预验证
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        const { data: res } = await this.$http.post('/management/insert', this.addForm)

        if (res.code !== 200) {
          this.$message.error('添加人员失败！')
        }

        this.$message.success('添加人员成功！')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getUserList()
      })
    },
    // 展示编辑员工的对话框
    async showEditDialog(row) {
      console.log(row)
      this.editForm = row
      this.editDialogVisible = true
    },
    // 监听修改员工对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 修改员工信息并提交
    editUserInfo() {
      // 添加预验证
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起修改员工的网络请求
        const { data: res } = await this.$http.post('/management/update', this.editForm)

        if (res.code !== 200) {
          this.$message.error('修改员工失败！')
        }

        this.$message.success('修改员工成功！')
        // 隐藏修改员工的对话框
        this.editDialogVisible = false
        // 重新获取员工列表数据
        this.getUserList()
      })
    },
    // 根据用户id删除对应的员工信息
    async removeUserByid(id) {
      // 弹框询问员工是否删除数据
      console.log(id)
      const confirmResult = await this.$confirm('此操作将永久删除该员工, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // 如果用户确认删除，则返回值为字符串confirm
      // 如果用户取消了删除，则返回值为字符串cancel
      // console.log(confirmResult)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      const { data: res } = await this.$http.delete('/management/delete/' + id)

      if (res.code !== 200) {
        return this.$message.error('删除员工失败！')
      }

      this.$message.success('删除员工成功！')
      this.getUserList()
    }
  }
    return {}
  },
  created() {},
  methods: {}
}
</script>

<style lang="less" scoped>
</style>
