<template>
  <div class="background">
  	<home-header></home-header>
  	<questions-brief></questions-brief>
  </div>
</template>

<script>
import HomeHeader from '../common/HomeHeader'
import QuestionsBrief from '../common/QuestionsBrief'
export default {
  name: 'Question',
  components:{
    HomeHeader,
    QuestionsBrief
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.background{
  height: 100%;
}
</style>