<template>
<div>
   <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统维护</el-breadcrumb-item>
      <el-breadcrumb-item>部门维护</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-form :inline="true" class="demo-form-inline">
      <el-form-item  >
        <el-input  placeholder="请输入部门号"></el-input>
      </el-form-item>
        <el-form-item  >
        <el-input  placeholder="请输入部门名"></el-input>
       </el-form-item>
     <!-- <el-form-item >
      <el-select placeholder="请选择状态">
      <el-option :value="1" label="开启" ></el-option>
      <el-option :value="2" label="关闭" ></el-option>
    </el-select>
    </el-form-item> -->
     <el-form-item>
    <el-button type="primary" >查询</el-button>
    </el-form-item>
    </el-form>
  </el-card>
</div>
</template>
<script>
export default{
  data(){
    return{

    }
  },
  created(){

  },
  method(){
    
  }
}
</script>
<style lang="less" scoped></style>
