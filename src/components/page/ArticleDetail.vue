<template>
  <div class="background">
  	<home-header></home-header>
    <article-content></article-content>
  </div>
</template>

<script>
import HomeHeader from '@/components/common/HomeHeader'
import ArticleContent from '@/components/common/ArticleContent'
import axios from 'axios'
export default {
  name: 'ArticleDetail',
  components:{
    HomeHeader,
    ArticleContent
  },
  /*mounted(){
    axios.get("https://cnodejs.org/api/v1/topic/5433d5e4e737cbe96dcef312")
    .then(response=>{
      if(response.data){
        this.data = response.data.data;
      }
    })
    .catch(error=>{
      console.log(error);
    })
  },
  data(){
    return {
      data:{}
    }
  }*/
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.background{
	
}
</style>