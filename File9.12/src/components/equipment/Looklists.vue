<template>
<div>
   <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>设备管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备列表</el-breadcrumb-item>
      <el-breadcrumb-item>设备详情</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
     <h1>设备:</h1>
     <p>balabalabalabalabala</p >

       <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="实例信息" name="first">实例信息</el-tab-pane>
        <el-tab-pane label="预置点设置" name="second">预置点设置</el-tab-pane>
        <el-tab-pane label="消息" name="third">消息</el-tab-pane>
        <el-tab-pane label="告警设置" name="fourth">告警设置</el-tab-pane>
        <el-tab-pane label="可视化" name="fifth">可视化</el-tab-pane>
       </el-tabs>
  </el-card>
  <p></p >
  <div class="block">
  <!-- <span class="demonstration">单选选择任意一级选项</span> -->
  <el-cascader
    :options="options"
    :props="{ checkStrictly: true }"
    clearable></el-cascader>
</div>
</div>

</template>

<script>
export default {
  data() {
    return {
      activeName: 'second',
      options: [
        {
          value: 'shebeihao',
          label: '设备号'
        },
        {
          value: 'shebeiming',
          label: '设备名'
        },
        {
          value: 'shebeileixing',
          label: '设备类型',
          children: [
            {
              value: 'normal',
              label: '一般设备'
            },
            {
              value: 'haikangweishi',
              label: '海康威视'
            },
            {
              value: 'dahua',
              label: '大华'
            }
          ]
        }
      ]
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style scoped>
</style>
