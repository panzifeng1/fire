<template>
  <div>
    <!-- 卡片视图 -->
    <el-card>
     <el-button type="primary" icon="el-icon-plus" @click="addDialogVisible = true">新增关联</el-button>
     <div class="list">
       <template>
        <el-table   border  style="width: 100%">
        <el-table-column prop="num" label="关联人"   width="120px" align="center"> </el-table-column>
        <el-table-column  prop="name"  label="消息类型" width="120px" align="center"> </el-table-column>
        <el-table-column prop="type.name" label="通知方式" width="120px" align="center"> </el-table-column>
        <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}} -->
        <!-- 通过scope.row.id获取某个设备的id -->
          <el-link type="primary">编辑</el-link>  
          <el-link type="primary">删除</el-link> 
        </template>
      </el-table-column>
    </el-table>
   </template> 
     </div>
    </el-card>
<!-- 添加设备弹框-->
  <el-dialog
  title="新增关联"
  :visible.sync="addDialogVisible"
  width="50%"  @close="addDialogClosed">
  <!-- 内容主体区域 -->
  <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px" >
    <!-- prop:验证规则 --> 
     <el-form-item label="设备类型" prop="linkman">
       <template>
         <el-select v-model="addForm.linkMan" clearable placeholder="请选择关联人"  @change="selectLinkMan">
           <el-option
              v-for="item in linkList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
         </el-select>
       </template>
  </el-form-item>
  <el-form-item label="消息类型" prop="newtype">
  <template>
  <el-select
    v-model="addForm.newType"
    multiple
    collapse-tags
    style="margin-left: 20px;"
    placeholder="请选择消息类型">
    <el-option
      v-for="item in typeList"
      :key="item.type"
      :label="item.typeName"
      :value="item.type">
    </el-option>
  </el-select>
</template>
</el-form-item>
<el-form-item label="通知方式" prop="informway">
  <template>
  <el-select
    v-model="addForm.informWay"
    multiple
    collapse-tags
    style="margin-left: 20px;"
    placeholder="请选择通知方式">
    <el-option
      v-for="item in wayList"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
</template>
</el-form-item>

</el-form>
  <!-- 底部区域 -->
  <span slot="footer" class="dialog-footer">
    <el-button @click="addDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addLink" >确 定</el-button>
  </span>
</el-dialog>
   
  </div>
</template>

<script>
export default {
  name: 'Sett',
  data() {
    return {
      // 控制添加告警设置对话框的显示与隐藏
      addDialogVisible: false,
      // 新增关联的表单数据
      addForm: {
        // 关联人
        linkMan: '',
        // 消息类型
        newType: '',
        // 通知方式
        informWay: ''
      },
      // 存放新增关联人弹框里的关联人数组
      linkList: [],
      // 存放消息类型
      typeList: [],
      // 存放通知方式
      wayList: [],
      // 新增关联的表单验证对象
      addFormRules: {
        linkman: [
          {
            required: true,
            message: '请选择关联人',
            trigger: 'blur'
          }
        ],
        newtype: [
          {
            required: true,
            message: '请选择消息类型',
            trigger: 'blur'
          }
        ],
        informway: [
          {
            required: true,
            message: '请选择通知方式',
            trigger: 'blur'
          }
        ]
      },
      // 存放新增关联的请求参数
      queryInfo: {
        managementId: '',
        deviceId: '',
        msgCfg: '',
        msgType: ''
      }
    }
  },
  created() {
    // 页面加载时就获取关联人列表
    this.getLinkList()
    // 获取消息类型
    this.getTypeLst()
    // 获取通知方式列表
    this.getWayList()
  },
  methods: {
    // 获取关联人列表的方法
    async getLinkList() {
      const { data: res } = await this.$http.get('device/listManagement')
      console.log('关联人列表：', res)
      this.linkList = res.data
    },
    async getTypeLst() {
      const { data: res } = await this.$http.get('device/listMsgType')
      console.log('消息类型列表：', res)
      this.typeList = res.data
    },
    async getWayList() {
      const { data: res } = await this.$http.get('device/listInformType')
      console.log('通知方式列表：', res)
      this.wayList = res.data
    },
    selectLinkMan() {},
    // 添加关联人 “确定”按钮 表单预校验,看你表单有没有全填
    addLink() {
      // 拿到整个表单的引用对象 进行预校验
      this.$refs.addFormRef.validate(async valid => {
        console.log(valid)
        if (!valid) return
        // 预校验通过了，可以发起真正的添加设备的网络请求
        const { data: res } = await this.$http.post(
          'device/addManagement',
          this.queryInfo
        )
        console.log(res)
        this.$message.success('新增关联成功')
        // 添加用户成功后，隐藏添加设备的对话框
        this.addDialogVisible = false
        //添加设备后要重新刷新列表获取列表数据(重新调用getEquipList函数)
        // this.getEquipList()
      })
    },

    // 监听添加关联人弹框的关闭事件，对话框一关闭，重置整个表单
    addDialogClosed() {
      // 表单重置
      this.$refs.addFormRef.resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
</style>