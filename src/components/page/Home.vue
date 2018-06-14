<template>
  <div class="background">
  	<home-header></home-header>
  	<article-and-question></article-and-question>
  </div>
</template>

<script>
import HomeHeader from '../common/HomeHeader'
import ArticleAndQuestion from '../common/ArticleAndQuestion'
export default {
  name: 'Home',
  components:{
    HomeHeader,
    ArticleAndQuestion
  },
  methods: {

  },
  mounted(){
      // console.log(this.$store)
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
	background-color: #eee;
	padding-bottom: 50px;
  height: 100%;
}
</style>
