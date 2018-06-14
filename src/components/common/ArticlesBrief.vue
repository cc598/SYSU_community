<template>
  <div>
    <div class="article-description">
      <p class="area-title">专栏</p>
      <p class="area-description">你可以在这里发你想说的</p>
    </div>
    <div class="content-container">
      <div class="article-container">
      
      <div class="article-list">
        <div class="article-item" v-for="article in articleInPage">
          <div :to="{name:'User',params:{name:article.author.name}}">
            <img :src="article.author.headImg_url" class="article-touxiang">
          </div>
          <div class="article-title" @click="goArticleDetail(article)">
          {{article.title}}</div>
          <div tag="span" class="article-author" 
          :to="{name:'User',params:{name:article.author}}">
          {{article.author}}</div>
          <span class="article-time">{{article.releaseTime}}</span>
          <span class="comment">评论数：{{article.comments.length}}</span>
        </div>
        <el-pagination class="pegination"
          background
          :page-size="10"
          :current-page="currentPage"
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="100">
        </el-pagination>
      </div>
    </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ArticlesBrief',
  data(){
    return {
      data:[],
      articleInPage:[],
      currentPage:1,
      articlePageUrl:this.$store.getters.Url+'/ArticleServlet',
      // 'http://192.168.191.2:8080/SYSUCommunity/ArticleServlet'
    }
  },
  computed:{
      replyItem(){
        return this.data.slice((this.currentPage-1)*10,(this.currentPage-1)*10+10);
      }
  },
  methods:{
    goArticleDetail(article){
		// console.log("article\n")
    // console.log(article)
    this.$store.dispatch("setSelectedA", article)
		this.$router.push({ name: 'ArticleDetail', params: {id:article.art_id}})
	  },
    handleCurrentChange(val){
      this.currentPage = val;
      this.articleInPage = this.replyItem
      // axios.get(this.$store.getters.Url+'/ArticleServlet',{
      //   params:{
      //     method: 'findByKey',
      //     keyword: ''
      //   }
      // })
      // .then(response=>{
      //   if(response.data){
      //     this.data = response.data;
      //     // console.log(this.data)
      //     this.articleInPage = this.replyItem
      //   }else{
      //     alert("获取失败")
      //   }
      // })
      // .catch(error=>{
      //   console.log(error)
      // })
    }
  },
  mounted(){
    this.data = this.$store.getters.Articles
    this.handleCurrentChange(1);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.article-description{
  position: absolute;
  top:60px;
  width: 100%;
  height:120px;
  background-color: rgb(154, 206, 199);
}
.area-title{
  font-size: 20px;
  color: #fff;
  text-align: center;
}
.area-description{
  font-size: 14px;
  color: #fff;
  text-align: center;
}
.content-container{
  position:absolute;
  height: 100%;
  width: 100%;
  margin:180px auto 0;
  background-color: #eee;
  padding-bottom: 30px;
}
.article-container{
  position: relative;
  width: 800px;
  margin: 5px auto;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
}
.article-item{
  height: 40px;
  padding:20px 20px 10px 80px;
  border-bottom: 1px dotted #E9E9E9;
}

.article-touxiang{
  position: absolute;
  height: 40px;
  left: 20px;
}
.article-title{
  margin: 0px;
  height: 20px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.article-author{
  font-size:10px;
  color: #aaa;
}
.article-time{
  font-size:10px;
  color: #888;
  margin-left: 20px;
}
.comment{
  float:right;
  font-size:10px;
  color: #888;
}
.pegination{
  padding: 15px 180px;
}
</style>