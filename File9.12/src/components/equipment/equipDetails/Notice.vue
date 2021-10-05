<template>
  <div>
    <!-- 卡片视图 -->
    <el-card>
    
       <el-tabs v-model="actName" @tab-click="handClick">
        <el-tab-pane label="告警设置" name="one">
         <Sett></Sett>
        </el-tab-pane>
        <el-tab-pane label="告警记录" name="two">
          <Record></Record>
       </el-tab-pane>
       
       </el-tabs>
  

    </el-card>

   
  </div>
</template>

<script>
import Sett from './notices/Sett'
// import Sett from './notices/Sett'
import Record from './notices/Record'
export default {
  name: 'Notice',
  components: { Sett, Record },
  data() {
    return {
      actName: 'one'
    }
  },
  created() {},
  methods: {
    handClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style lang="less" scoped>
</style>