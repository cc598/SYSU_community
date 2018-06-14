<template>
  <div class="background">
  	<home-header></home-header>
    <user-info></user-info>
  </div>
</template>

<script>
import HomeHeader from '../common/HomeHeader'
import UserInfo from '../common/UserInfo'
import axios from 'axios';
export default {
  name: 'User',
  components:{
    HomeHeader,
    UserInfo
  },
  data(){
    return {
      user: null
    }
  },
  beforeMount(){
    
  }
  ,
  mounted(){
      // console.log(this.$store)
      if(this.$store.getters.Status == 'not login'){
        this.$message({
          type: 'warning',
          message:"请先登录！"
          })

        this.$router.replace("/")
      }
      // axios({
      //   method:'get',
      //   url:this.this.$store.getters.Url+"profileServlet",
      // }).then(res=>{
      //   console.log(res)
      // }).catch(err=>{console.log(err)})
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>