<template>
<div>
   <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>设备管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备列表</el-breadcrumb-item>
   </el-breadcrumb>
  <el-card>
    <el-button type="primary" @click="addDialogVisible = true">添加设备</el-button>

    <!-- 设备列表 -->
    <template>
     <el-table :data="equiplist"  border  style="width: 100%">
      <el-table-column type="index" label="序号"  width="100px" align="center"></el-table-column>
      <el-table-column prop="num" label="设备号"   width="120px" align="center"> </el-table-column>
      <el-table-column  prop="name"  label="设备名" width="120px" align="center"> </el-table-column>
      <el-table-column prop="type.name" label="设备类型" width="120px" align="center"> </el-table-column>
      <el-table-column  prop="status" label="状态"  width="120px" align="center">  </el-table-column>  <el-table-column  prop="note"  label="说明"  width="200px" align="center">  </el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}} -->
        <!-- 通过scope.row.id获取某个设备的id -->
          <el-link type="primary">查看</el-link>  
          <el-link type="primary" @click="showEditDialog(scope.row.id)">编辑</el-link>  
          <el-link type="primary" @click="remmoveEquipById(scope.row.id)">删除</el-link> 
        </template>
      </el-table-column>
    </el-table>
   </template> 

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

  <!-- 添加设备弹框-->
  <el-dialog
  title="添加设备"
  :visible.sync="addDialogVisible"
  width="50%" @close="addDialogClosed">
  <!-- 内容主体区域 -->
  <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px" >
   <el-form-item label="设备号" prop="equipnum">
     <el-input v-model="addForm.equipnum"></el-input>
   </el-form-item>
   <el-form-item label="设备名" prop="equipname">
     <el-input v-model="addForm.equipname"></el-input>
   </el-form-item>
  <el-form-item label="设备类型" prop="equiptype">
    <el-radio-group v-model="addForm.equiptype">
      <el-radio label="一般设备"></el-radio>
      <el-radio label="海康威视"></el-radio>
      <el-radio label="大华"></el-radio>
    </el-radio-group>
  </el-form-item>
  <!-- <el-form-item label="状态" prop="equipstatus">
    <el-radio-group v-model="addForm.equipstatus">
      <el-radio label="0:未启用"></el-radio>
      <el-radio label="1:离线"></el-radio>
      <el-radio label="2:在线"></el-radio>
    </el-radio-group>
  </el-form-item> -->
   <el-form-item label="说明" prop="equipexplain">
    <el-input type="textarea" v-model="addForm.equipexplain"></el-input>
  </el-form-item>
  </el-form>
  <!-- 底部区域 -->
  <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addEquip" >确 定</el-button>
  </span>
</el-dialog>


<!-- 编辑设备的 对话框 -->
<el-dialog
  title="编辑设备"
  :visible.sync="editDialogVisible"
  width="50%">
  <!-- model:双向数据绑定 rules：验证规则 ref：当前表单的引用对象  -->
<el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
  <el-form-item label="设备号" prop="num">
    <el-input v-model="editForm.num"></el-input>
  </el-form-item>
   <el-form-item label="设备名" prop="name">
    <el-input v-model="editForm.name"></el-input>
  </el-form-item>
   <el-form-item label="设备类型" prop="type.name">
    <el-input v-model="editForm.type.name"></el-input>
  </el-form-item>
</el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editDialogVisible = false">确 定</el-button>
  </span>
</el-dialog>


</div>
</template>
<script>
export default {
  data() {
    return {
      // 获取设备列表参数对象
      queryInfo: {
        query: '',
        // 当前页数
        page: 1,
        // 每页显示条数
        pageSize: 10
      },
      // 添加设备参数对象
      equipInfo: {
        num: '',
        name: '',
        typeId: '',
        note: ''
      },
      equiplist: [],
      total: 0,
      // 控制添加设备对话框的显示与隐藏
      addDialogVisible: false,
      // 添加设备的表单数据
      addForm: {
        equipnum: '',
        equipname: '',
        equiptype: '',
        equipstatus: '',
        equipexplain: ''
      },
      // // 添加设备表单的验证规则对象
      addFormRules: {
        equipnum: [
          {
            required: true,
            message: '请输入设备号',
            trigger: 'blur'
          }
        ],
        equipname: [
          {
            required: true,
            message: '请输入设备名',
            trigger: 'blur'
          }
        ],
        equiptype: [
          {
            required: true,
            message: '请选择设备类型',
            trigger: 'blur'
          }
        ],
        equipstatus: [
          {
            required: true,
            message: '请选择设备状态',
            trigger: 'blur'
          }
        ],
        equipexplain: [
          {
            required: true,
            message: '请输入说明',
            trigger: 'blur'
          }
        ]
      },
      // 控制 编辑设备 对话框 的显示与隐藏
      editDialogVisible: false,
      // 根据id查询到的设备信息对象（用来保存设备信息）
      editForm: {}
    }
  },
  created() {
    // 发起请求
    this.getEquipList()
  },
  methods: {
    // 定义函数
    async getEquipList() {
      const { data: res } = await this.$http.get('device/listDevice', {
        params: this.queryInfo
      })
      this.equiplist = res.data
      this.total = res.total
      console.log(res)
    },
    // 监听pageSize 改变的事件
    handleSizeChange(newSize) {
      console.log(newSize)
      this.queryInfo.pageSize = newSize
      this.getEquipList()
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      console.log(newPage)
      this.queryInfo.page = newPage
      // 重新获取数据
      this.getEquipList()
    },
    // 监听设备添加对话框的关闭事件
    addDialogClosed() {
      // 表单重置
      this.$refs.addFormRef.resetFields()
    },
    // 添加设备 “确定”按钮 表单预校验,看你表单有没有全填
    addEquip() {
      // 拿到整个表单的引用对象
      this.$refs.addFormRef.validate(async valid => {
        console.log(valid)
        if (!valid) return
        // 预校验通过了，可以发起真正的添加设备的网络请求
        const { data: res } = await this.$http.post(
          'device/addDevice',
          this.equipInfo
        )
        console.log(res)
        this.$message.success('添加用户成功')
        // 添加用户成功后，隐藏添加设备的对话框
        this.addDialogVisible = false
        //添加设备后要重新刷新列表获取列表数据(重新调用getEquipList函数)
        this.getEquipList()
      })
    },
    // 根据id删除对应的用户信息
    async remmoveEquipById(id) {
      // console.log(id)
      // 弹框询问用户是否删除
      const confirmResult = await this.$confirm(
        '此操作将永久删除该设备, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      // .catch(() => {
      //   this.$message({
      //     type: 'info',
      //     message: '已取消删除'
      //   })
      // })
      // 如果用户确认删除，则返回值为字符串 confirm,如果用户取消删除，返回值为字符串 cancel
      // console.log(confirmResult)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      // console.log('用户确认了删除')
      // 如果返回值是confirm 则发起请求
      const { data: res } = await this.$http.delete('device/deleteDevice/' + id)
      // if(res.meta.status !== 200){
      //   return this.$message.error('删除用户失败')
      // }
      this.$message.success('删除用户成功')
      // 成功之后重现刷新设备列表(重新调用getEquipList())
      this.getEquipList()
    },
    // 展示编辑设备的 对话框
    // 根据id查询用户的信息(点击编辑按钮时，要显示设备基本信息 通过scope.row.id获取某个设备的id)
    async showEditDialog(id) {
      console.log(id)

      // 动态数据 用字符串拼接
      const { data: res } = await this.$http.get('device/seeDevice/' + 'id')
      console.log(res)
      this.editForm = res.data
      this.editDialogVisible = true
    }
  }
}
</script>
<style lang="less" scoped>
.el-link {
  margin: 0 10px;
}
</style>
