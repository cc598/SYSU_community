<template>
  <div>
    <home-header></home-header>
    <question-content></question-content>
  </div>
</template>

<script>
import HomeHeader from "@/components/common/HomeHeader"
import QuestionContent from "@/components/common/QuestionContent"
export default {
  name: 'QuestionDetail',
  components:{
    HomeHeader,
    QuestionContent
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>