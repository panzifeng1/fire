<template>
<div>
   <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>设备管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备列表</el-breadcrumb-item>
   </el-breadcrumb>

<el-card class="box-card">
 
    <el-row :gutter="20" style="width:900px">
       <el-col :span="5">
         <div class="grid-content bg-purple">
           <h4>全部设备</h4>
           <p>{{total}}</p>
         </div>
        </el-col>
       <el-col :span="5">
         <div class="grid-content bg-purple">
          <el-badge is-dot class="item1" type="success">
            <h4>在线</h4>
          </el-badge>
          <p>{{onlineNum}}</p>
         </div>
        </el-col>
       <el-col :span="5">
         <div class="grid-content bg-purple">
            <el-badge is-dot class="item1" type="danger"><h4>离线</h4></el-badge>
             <p>{{uplineNum}}</p>
         </div>
        </el-col>
       <el-col :span="5">
         <div class="grid-content bg-purple">
           <el-badge is-dot class="item1" type="primary"><h4>未激活</h4></el-badge>
            <p>{{nolineNum}}</p>
          </div>
        </el-col>
        <div class="upload">
           <el-link  icon="el-icon-refresh" :underline="false" style="font-size: 24px" @click="getEquipList()"></el-link>
         
        </div>
    </el-row>

 
</el-card>

 <el-card>
 
<div style="margin: 15px 0;">
  <!-- queryInfo.query:搜索关键字 -->
  <el-input placeholder="请输入内容" v-model="queryInfo.query" class="input-with-select">
    <el-select v-model="select" slot="prepend" placeholder="请选择" style="width:110px">
      <el-option label="设备号" value="1"></el-option>
      <el-option label="设备名" value="2"></el-option>
      <el-option label="设备类型" value="3"></el-option>
    </el-select>
    <el-button slot="append" icon="el-icon-search" @click="getEquipList"></el-button>
  </el-input>
</div>
    <el-button type="primary" @click="addDialogVisible = true">添加设备</el-button>

    <!-- 设备列表 -->
    <template>
     <el-table :data="equiplist"  border  style="width: 100%">
      <el-table-column type="index" label="序号"  width="100px" align="center"></el-table-column>
      <el-table-column prop="num" label="设备号"   width="120px" align="center"> </el-table-column>
      <el-table-column  prop="name"  label="设备名" width="120px" align="center"> </el-table-column>
      
      <el-table-column prop="deviceType.typeName" label="设备类型" width="120px" align="center"> </el-table-column>
    
      <el-table-column label="状态" width="120px" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.status == '0' ">
             <div class="status-point" style=" background-color:#009FCC" /> 未激活
          </div>
          <div v-if="scope.row.status == '1'">
             <div class="status-point" style=" background-color:#FF0000" /> 离线
          </div>
          <div v-if="scope.row.status == '2' ">
             <div class="status-point" style=" background-color:#67C23A" /> 在线
          </div>
        </template>
     </el-table-column>
<el-table-column  prop="note"  label="说明"  width="200px" align="center"></el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}} -->
        <!-- 通过scope.row.id获取某个设备的id -->
          <el-link type="primary" @click="gotoLooklist(scope.row.id)">查看</el-link>  
          <el-link type="primary" @click="showEditDialog(scope.row)">编辑</el-link>  
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
    <!-- prop:验证规则 -->
   <el-form-item label="设备号" prop="num">
     <el-input v-model="addForm.num"></el-input>
   </el-form-item>
   <el-form-item label="设备名" prop="name">
     <el-input v-model="addForm.name"></el-input>
   </el-form-item>

   <el-form-item label="设备类型" prop="type">
    <template>
  <el-select v-model="addForm.type" clearable placeholder="请选择部门类型">
    <el-option
      v-for="item in typeLists"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
</template>
  </el-form-item> 
   
   <el-form-item label="说明" prop="note">
    <el-input type="textarea" v-model="addForm.note"></el-input>
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
  width="50%" @close="editDialogClosed">
  <!-- model:双向数据绑定 rules：验证规则 ref：当前表单的引用对象  -->
<el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
  <el-form-item label="设备号" prop="num">
    <el-input v-model="editForm.num"></el-input>
  </el-form-item>
   <el-form-item label="设备名"  prop="name">
    <el-input v-model="editForm.name"></el-input>
  </el-form-item>
   <el-form-item label="设备类型" prop="typeName">
    <el-input v-model="editForm.typeName"></el-input>
  </el-form-item>
   <el-form-item label="说明" prop="note">
    <el-input type="textarea" v-model="editForm.note"></el-input>
  </el-form-item>
</el-form>
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
      select: '',
      // 添加设备中选择设备类型的下拉框
      typeLists: [
        {
          id: '0',
          name: '一般设备'
        },
        {
          id: '1',
          name: '海康威视'
        },
        {
          id: '2',
          name: '大华'
        }
      ],
      id: '',
      // 获取设备列表参数对象
      queryInfo: {
        query: '',
        // 当前页数
        page: 1,
        // 每页显示条数
        pageSize: 5
      },
      // 添加设备参数对象
      equipInfo: {
        num: '',
        name: '',
        type: '',
        note: ''
      },
      equiplist: [],
      // 总数
      total: 0,
      // 设备状态
      // statusNum: 0,
      // 在线设备数
      onlineNum: 0,
      // 离线设备数
      uplineNum: 0,
      // 未激活设备数
      nolineNum: 0,
      // 控制添加设备对话框的显示与隐藏
      addDialogVisible: false,
      // 添加设备的表单数据
      addForm: {
        num: '',
        name: '',
        type: '',
        // equipstatus: '',
        note: ''
      },
      // // 添加设备表单的验证规则对象
      addFormRules: {
        num: [
          {
            required: true,
            message: '请输入设备号',
            trigger: 'blur'
          }
        ],
        name: [
          {
            required: true,
            message: '请输入设备名',
            trigger: 'blur'
          }
        ],
        type: [
          {
            required: true,
            message: '请选择设备类型',
            trigger: 'blur'
          }
        ],
        // equipstatus: [
        //   {
        //     required: true,
        //     message: '请选择设备状态',
        //     trigger: 'blur'
        //   }
        // ],
        note: [
          {
            required: true,
            message: '请输入说明',
            trigger: 'blur'
          }
        ]
      },
      // 控制 编辑设备 对话框 的显示与隐藏
      editDialogVisible: false,
      // 查询到的设备信息对象（用来保存设备信息）
      editForm: {
        id: '',
        num: '',
        name: '',
        typeName: '',
        typeId: '',
        note: '',
        status: ''
      },
      editName: '',
      // 编辑设备对话框验证规则对象
      editFormRules: {
        num: [{ required: true, message: '请输入设备号', trigger: 'blur' }],
        name: [{ required: true, message: '请输入设备名', trigger: 'blur' }],
        typeName: [
          { required: true, message: '请输入设备类型', trigger: 'blur' }
        ],
        note: [{ required: true, message: '请输入设备说明', trigger: 'blur' }]
      }
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
      this.onlineNum = res.onLine
      this.uplineNum = res.offLine
      this.nolineNum = res.notActive
      console.log('设备列表：', res)
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
      // 拿到整个表单的引用对象 进行预校验
      this.$refs.addFormRef.validate(async valid => {
        console.log(valid)
        if (!valid) return
        // 预校验通过了，可以发起真正的添加设备的网络请求
        const { data: res } = await this.$http.post(
          'device/addDevice',
          this.addForm
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
    async showEditDialog(data) {
      console.log(data.id)
      console.log(data)
      // this.editForm = data
      this.editForm.id = data.id
      this.editForm.num = data.num
      this.editForm.name = data.name
      this.editForm.typeName = data.deviceType.typeName
      this.editForm.typeId = data.deviceType.typeId
      this.editForm.note = data.note
      this.editForm.status = data.status
      // this.editName = data.name
      // this.editForm = scope.row
      // 动态数据 用字符串拼接
      // const { data: res } = await this.$http.get('device/seeDevice/' + 'id')
      // console.log(res)
      // this.editForm = res.data
      this.editDialogVisible = true
    },
    editDialogClosed() {
      // 重置整个表单(点击取消按钮后)
      this.$refs.editFormRef.resetFields()
    },
    // 编辑设备信息并提交
    editUserInfo() {
      //   // 点击确认按钮进行预验证
      this.$refs.editFormRef.validate(async valid => {
        console.log(valid) //true时通过
        if (!valid) return //校验不通过 直接return
        // 通过了 发起修改管理员信息的数据请求
        const { data: res } = await this.$http.put(
          'device/updateDevice',
          // 提交一个用户的信息
          {
            id: this.editForm.id,
            num: this.editForm.num,
            name: this.editForm.name,
            note: this.editForm.note,
            status: this.editForm.status,
            type: this.editForm.typeId
          }
        )
        console.log(res)
        console.log(res.id)
        this.editDialogVisible = false
        this.getEquipList()
        this.$message.success('更新设备成功')
      })
    },
    // 点击 查看 跳转到查看页面
    gotoLooklist(id) {
      // /looklist/id
      this.$router.replace('/looklist/' + id)
    }
  }
}
</script>
<style lang="less" scoped>
.el-link {
  margin: 0 10px;
}
// .block {
//   margin: 20px 0;
//   display: inline-block;
// }
// .el-select .el-input {
//   width: 130px;
// }
.el-select .el-input {
  width: 200px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.box-card {
  margin: 10px 0;
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.text {
  width: 800px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #ffffff;
}
.bg-purple {
  background: #ffff;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.item1 {
  // background-color: green;
  margin-right: 40px;
}
.box-card h4 {
  margin: 0px;
  padding: 0px;
}
.upload {
  margin: 10px;
}
.status-point {
  display: inline-block;
  width: 6px;
  height: 6px;
  border-radius: 50%;
}
</style>
