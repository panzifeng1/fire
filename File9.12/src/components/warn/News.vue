<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预警管理</el-breadcrumb-item>
      <el-breadcrumb-item>消息管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图 -->
    <el-card>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item  >
        <el-input  placeholder="请输入教职工名称"></el-input>
      </el-form-item>
     <el-form-item >
      <el-select placeholder="请选择性别">
      <el-option :value="1" label="男" ></el-option>
      <el-option :value="2" label="女" ></el-option>
    </el-select>
    </el-form-item>
     <el-form-item >
      <el-select placeholder="请选择所属学院">
      <!-- <el-option label="" value=""></el-option>
      <el-option label="" value=""></el-option> -->
    </el-select>
    </el-form-item>
     <el-form-item >
      <el-select placeholder="请选择职称">
      <!-- <el-option label="" value=""></el-option>
      <el-option label="" value=""></el-option> -->
    </el-select>
    </el-form-item>
    <el-form-item>
    <el-button type="primary" >查询</el-button>
  </el-form-item>
  <el-form-item>
    <el-button type="default" >清空</el-button>
  </el-form-item>
  <!-- <el-form-item>
   <el-button type="primary">添加教职工</el-button>
  </el-form-item> -->
</el-form>
<el-table :data="userlist" border stripe>
        <el-table-column label="序号" type="index"></el-table-column>
        <el-table-column label="职工名称" prop=""  align="center"></el-table-column>
        <el-table-column label="职工号" prop=""  align="center"></el-table-column>
        <el-table-column label="职称" prop=""  align="center"></el-table-column>
        <el-table-column label="性别" prop=""  align="center"></el-table-column> 
         <el-table-column label="开始教学年份" prop="" align="center"></el-table-column>
         <el-table-column label="所属学院" prop="" align="center"></el-table-column>
          <el-table-column label="联系电话" prop="" align="center"></el-table-column>
        <el-table-column label="操作" width="180px"  align="center">
          </el-table-column>
      </el-table>
    </el-card>
    <!-- <el-card>
      <el-table :data="rightsList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="权限名称" prop="authName"></el-table-column>
        <el-table-column label="路径" prop="path"></el-table-column>
        <el-table-column label="权限等级" prop="level">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.level === '0'">一级</el-tag>
            <el-tag type="success" v-else-if="scope.row.level === '1'">二级</el-tag>
            <el-tag type="warning" v-else>三级</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card> -->
  </div>
</template>

<script>
// export default {
//   data() {
//     return {
//       // 权限列表
//       rightsList: []
//     }
//   },
//   created() {
//     // 获取所有的权限
//     this.getRightsList()
//   },
//   methods: {
//     // 获取权限列表
//     async getRightsList() {
//       const { data: res } = await this.$http.get('rights/list')
//       if (res.meta.status !== 200) {
//         return this.$message.error('获取权限列表失败！')
//       }

//       this.rightsList = res.data
//       console.log(this.rightsList)
//     }
//   }
// }
export default {
  data() {
    return {}
  },
  created() {},
  methods() {}
}
</script>

<style lang="less" scoped>
</style>
