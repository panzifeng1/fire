<template>
  <div>
    <!-- 卡片视图 -->
    <el-card>
      <template>
     <el-table :data="equiplist"  border  style="width: 100%">
      <el-table-column prop="num" label="消息类型"   width="120px" align="center"> </el-table-column>
      <el-table-column  prop="name"  label="内容" width="120px" align="center"> </el-table-column>
      <el-table-column prop="type.name" label="时间" width="120px" align="center"> </el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template slot-scope="scope">
        <!-- 获取每个数组里的所有数据 -->
        <!-- {{scope.row}} -->
        <!-- 通过scope.row.id获取某个设备的id -->
          <el-link type="primary">查看</el-link>  
        </template>
      </el-table-column>
    </el-table>
   </template> 
    </el-card>

   
  </div>
</template>

<script>
export default {
  name: 'Message',
  data() {
    return {}
  },
  created() {},
  methods: {}
}
</script>

<style lang="less" scoped>
</style>