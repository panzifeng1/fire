<template>
  <div>
    <!-- 卡片视图 -->
    <!-- <el-card>
      <template>
     <el-table :data="meslist"  border  style="width: 100%">
      <el-table-column prop="type" label="消息类型"   width="120px" align="center"> </el-table-column>
      <el-table-column  prop="name"  label="内容" width="120px" align="center"> </el-table-column>
      <el-table-column prop="type.name" label="时间" width="120px" align="center"> </el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope"> -->
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}}
        通过scope.row.id获取某个设备的id -->
          <!-- <el-link type="primary">查看</el-link>  
        </template>
      </el-table-column>
    </el-table>
   </template> 
    </el-card> -->
 <el-card>
     <!-- 设备列表 -->
    <template>
     <el-table :data="equiplist"  border  style="width: 100%">
       <!-- <el-table-column type="index" label="序号"  width="100px" align="center"></el-table-column> -->
      <el-table-column prop="type" label="消息类型"  width="100px" align="center"></el-table-column>
      <el-table-column prop="msg" label="内容"   width="120px" align="center"> </el-table-column>
      <el-table-column  prop="createTime"  label="时间" width="120px" align="center"> </el-table-column>
      <!-- <el-table-column  prop="note"  label="说明"  width="200px" align="center"></el-table-column> -->
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}} -->
        <!-- 通过scope.row.id获取某个设备的id -->
          <el-link type="primary" @click="gotoLooklist(scope.row.id)">查看</el-link>  
          <!-- <el-link type="primary" @click="showEditDialog(scope.row)">编辑</el-link>   -->
          <!-- <el-link type="primary" @click="remmoveEquipById(scope.row.id)">删除</el-link>  -->
        </template>
      </el-table-column>
    </el-table>
   </template> 
    </el-card>
   
  </div>
</template>

<script>
export default {
  //   name: 'Message',
  //   data() {
  //     return {
  //       // 消息列表参数对象
  //       queryInfoer: {
  //         // query: '',
  //         // deviceName: '',
  //         deviceId: ''
  //         // type: '',
  //         // time: ''
  //       },
  //       //  存放消息列表
  //       meslist: []
  //     }
  //   },
  //   created() {
  //     this.getMesList()
  //   },
  //   methods: {
  //     async getMesList() {
  //       const { data: res } = await this.$http.get('msgManage/select', {
  //         params: this.queryInfoer
  //       })
  //       console.log('消息列表：', res)
  //       this.meslist = res.data
  //     }
  //   }
  // }
  name: 'Message',
  data() {
    return {
      equiplist: [],
      //  获取设备列表参数对象
      queryInfo: {
        // deviceName: '',
        deviceId: ''
        // type: '',
        // time: ''
      }
      // type: '',
      // msg: '',
      // note: '',
      // createTime: ''
      // 总数
      // total: 0,
    }
  },
  created() {
    // 发起请求
    this.getEquipList()
  },
  methods: {
    // 定义函数
    async getEquipList() {
      const { data: res } = await this.$http.get('/msgManage/select', {
        params: this.queryInfo
      })
      this.equiplist = res.data
      // this.total = res.total
      // this.onlineNum = res.onLine
      // this.uplineNum = res.offLine
      // this.nolineNum = res.notActive
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
    }
  }
}
</script>

<style lang="less" scoped>
</style>