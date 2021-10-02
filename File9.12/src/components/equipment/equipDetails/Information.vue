<template>
  <div>
    <!-- 卡片视图 -->
    <el-card  class="big">
      <div class="equipInfo">
        <el-descriptions title="实例信息" border>
         <el-descriptions-item label="设备号">{{this.equipMessage.num}}</el-descriptions-item>
         <el-descriptions-item label="设备名称">{{this.equipMessage.name}}</el-descriptions-item>
         <el-descriptions-item label="设备类型">{{this.equipMessage.type}}</el-descriptions-item>
         <el-descriptions-item label="所属部门">{{this.equipMessage.department}}</el-descriptions-item>
         <el-descriptions-item label="创建时间">{{this.equipMessage.createTime}}</el-descriptions-item>
         <el-descriptions-item label="最后在线时间">{{this.equipMessage.lastOnlineTime}}</el-descriptions-item>
         <el-descriptions-item label="设备地点">{{this.equipMessage.geoPoint}}</el-descriptions-item>
         <!-- <el-descriptions-item label="备注">
           <el-tag size="small">学校</el-tag>
         </el-descriptions-item> -->
         <el-descriptions-item label="说明">{{this.equipMessage.note}}</el-descriptions-item>
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
    return {
      equitpId: this.$route.params.id,
      equipMessage: {
        // 某设备名
        name: '',
        // 某设备状态
        statu: '',
        // 设备号
        num: '',
        // 类型
        type: '',
        // 部门
        department: '',
        note: '',
        createTime: '',
        lastOnlineTime: '',
        geoPoint: ''
      }
    }
  },
  created() {
    this.getEuqipment()
  },
  methods: {
    async getEuqipment() {
      var id = this.equitpId
      console.log('id:', id)
      const { data: res } = await this.$http.get('device/seeDevice/' + id)
      console.log('res:', res)
      this.equipMessage.num = res.data.num
      this.equipMessage.name = res.data.name
      this.equipMessage.type = res.data.deviceType.typeName
      this.equipMessage.note = res.data.note
      this.equipMessage.department = res.data.department
      this.equipMessage.createTime = res.data.createTime
      this.equipMessage.lastOnlineTime = res.data.lastOnlineTime
      this.equipMessage.geoPoint = res.data.geoPoint
      // console.log('设备名：', this.equitpName)
      // console.log('设备状态', this.equitpStatu)
    }
  }
}
</script>

<style lang="less" scoped>
</style>