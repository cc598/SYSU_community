<template>
  <div class="background">
  	<home-header></home-header>
  	<articles-brief></articles-brief>
  </div>
</template>

<script>
import HomeHeader from '../common/HomeHeader'
import ArticlesBrief from '../common/ArticlesBrief'
export default {
  name: 'Article',
  components:{
    HomeHeader,
    ArticlesBrief
  },
  mounted(){
      console.log(this.$store)
      if(this.$store.getters.Status == 'not login'){
        this.$message({
          type: 'warning',
          message:"请先登录！"
          })

        this.$router.replace("/")
      }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.background{
  height: 100%;
}
</style>