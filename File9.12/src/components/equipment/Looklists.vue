<template>
<div>
   <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>设备管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备列表</el-breadcrumb-item>
      <el-breadcrumb-item>设备详情</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
    <div class="head">
       <el-link  icon="el-icon-back" :underline="false" style="font-size: 24px" 
       @click="gotoLists"></el-link>
      <h1>设备:{{this.equitpName}}</h1>

    </div>
    

       <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="实例信息" name="first">
         <Information></Information>
        </el-tab-pane>
        <el-tab-pane label="预置点设置" name="second">
          <Preset></Preset>
       </el-tab-pane>
        <el-tab-pane label="消息" name="third">
           <Message></Message>
       </el-tab-pane>
        <el-tab-pane label="告警设置" name="fourth">
           <Notice></Notice>
        </el-tab-pane>
        <el-tab-pane label="可视化" name="fifth">
           <Visual></Visual>
       </el-tab-pane>
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
import Information from './equipDetails/Information'
import Preset from './equipDetails/Preset'
import Message from './equipDetails/Message'
import Notice from './equipDetails/Notice'
import Visual from './equipDetails/Visual'
export default {
  data() {
    return {
      // 某设备的id
      equitpId: this.$route.params.id,
      // 某设备名
      equitpName: '',
      // 某设备状态
      equitpStatu: '',
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
  components: { Information, Preset, Message, Notice, Visual },
  created() {
    this.getEuqipment()
    // 发起请求
    // const { data: res } = this.$http.get('device/seeDevice/' + 'this.equitpId')
    // console.log(res)
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    gotoLists() {
      //   this.$router.go(-1)

      this.$router.push({ path: '/list' })
    },
    async getEuqipment() {
      var id = this.equitpId
      console.log('id:', id)
      const { data: res } = await this.$http.get('device/seeDevice/' + id)
      console.log('res:', res)
      this.equitpName = res.data.name
      this.equitpStatu = res.status
      console.log('设备名：', this.equitpName)
      console.log('设备状态', this.equitpStatu)
    }
  }
}
</script>

<style scoped>
.head h1 {
  display: inline-block;
  margin: 10px;
}
</style>
