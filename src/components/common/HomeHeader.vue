<template>
  <div id="header">
    <div class="header-container">
      <ul class="left-nav-group">
        <router-link tag="li" to="/home" class="left-nav-item">首页</router-link>
        <router-link tag="li" to="/article" class="left-nav-item">专栏</router-link>
        <router-link tag="li" to="/question" class="left-nav-item">问答</router-link>
      </ul>
      <el-input v-model="search" prefix-icon="el-icon-search" placeholder="请输入内容"
      class="search-box" size="small"></el-input>
      <router-link tag="i" to="/myinformation" class="iconfont icon-shenfenzheng person"></router-link>
    </div>
  </div>
</template>

<script>
export default {
   name:'HomeHeader',
   data(){
    return {
      search:''
    }
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#header{
  position: fixed;
  left:0;
  top:0;
  z-index: 100;
  width: 100%;
  height:60px;
  background-color: #393D49;
}
.header-container{
  position: relative;
  width:1000px;
  min-width: 700px;
  margin:0 auto;
}
.left-nav-group{
  padding:0;
  margin:0 0 0 200px;
  display: inline-block;
}
.left-nav-item{
    position: relative;
    display: inline-block;
    vertical-align: middle;
    line-height: 60px;
    font-size: 15px;
    list-style: none;
    color: #fff;
    padding:0 12px;
    cursor: pointer;
}
.search-box{
  width:200px;
  margin-left: 100px;
}
.person{
  position: absolute;
  top:10px;
  right: 0;
  font-size:40px;
  color: #ccc;
  cursor: pointer;
}
</style>
