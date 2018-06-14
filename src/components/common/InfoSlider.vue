<template>
  <div >
  	<el-menu
      default-active="/myinformation"
      class="el-menu-vertical-demo slider"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
      >
      <el-menu-item index="/user/1">
        <i class="el-icon-location"></i>
        <span slot="title">我的主页</span>
      </el-menu-item>
      <el-menu-item index="/myinformation">
        <i class="el-icon-menu"></i>
        <span slot="title">用户中心</span>
      </el-menu-item>
      <el-menu-item index="/myinformation/setting">
        <i class="el-icon-setting"></i>
        <span slot="title">基本设置</span>
      </el-menu-item>
      <el-menu-item index="/" @click="logout">
        <i class="el-icon-setting"></i>
        <span slot="title">退出登录</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  
  name: 'InfoSlider',
  data(){
    return {
      url:"http://111.231.194.22:8080/SYSUCommunity/UserServlet?method=quit"
    }
  },
  methods: {
    logout() {
      axios.get(this.url)
      this.$store.commit("clearAll")
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.slider{
	position: absolute;
	margin-top: 80px;
	width: 200px;
	height:500px;
	margin-left: 150px;
	background-color: #393D49;
}
.el-menu-item:focus{
	background-color:#393D49;
}
.el-menu-item:hover{
	background-color: #5b5f6b;
}
</style>