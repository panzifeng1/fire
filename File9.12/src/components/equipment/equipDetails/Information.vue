<template>
  <div>
    <!-- 卡片视图 -->
    <el-card>
      <div class="equipInfo">
        <el-descriptions title="实例信息" border>
         <el-descriptions-item label="设备号">kooriookami</el-descriptions-item>
         <el-descriptions-item label="设备名称">18100000000</el-descriptions-item>
         <el-descriptions-item label="设备类型">苏州市</el-descriptions-item>
         <el-descriptions-item label="所属部门">苏州市</el-descriptions-item>
         <el-descriptions-item label="创建时间">苏州市</el-descriptions-item>
         <el-descriptions-item label="最后在线时间">苏州市</el-descriptions-item>
         <el-descriptions-item label="设备地点">苏州市</el-descriptions-item>
         <!-- <el-descriptions-item label="备注">
           <el-tag size="small">学校</el-tag>
         </el-descriptions-item> -->
         <el-descriptions-item label="说明">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
       </el-descriptions>
       </div>
       <div class="config">
         <h4>配置</h4>
       </div>
    </el-card>

   
  </div>
</template>

<script>
export default {
  name: 'Information',
  data() {
    return {}
  },
  created() {},
  methods: {}
}
</script>

<style lang="less" scoped>
</style>