<template>
  <div >
  	<home-header></home-header>
  	<info-slider></info-slider>
  	<router-view/>
  </div>
</template>

<script>
import HomeHeader from '../common/HomeHeader'
import InfoSlider from '../common/InfoSlider'
export default {
  name: 'MyInformation',
  components:{
    HomeHeader,
    InfoSlider
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

</style>