<template>
  <div class="content-container">
  	<div class="article-container">
  		<div class="area-title">
  		文章
  		<el-button type="success" size="small" class="article-btn" @click="clickToArticle">发表文章</el-button>
		</div>
  		<div class="article-list">
  			<div class="article-item" v-for="article in articleInHome">
  				<div :to="{name:'User',params:{name:article.author}}">
  					<img :src="article.author.headImg_url" class="article-touxiang">
  				</div>
  				<div class="article-title" tag="h4" @click="goArticleDetail(article)">
  				{{article.title}}</div>
  				<div tag="span" class="article-author">{{article.author}}</div>
  				<span class="article-time">{{article.releaseTime}}</span>
  				<span class="comment">评论数：{{article.comments.length}}</span>
  			</div>
  			
  		</div>
  	</div>

  	<div class="question-container">
  		<div class="area-title">
  		问答
  		<el-button type="success" size="small" class="question-btn"
  		@click="clickToQuestion">提问</el-button>
  		</div>
	  		<div class="question-list">
	  			<div class="question-item" v-for="question in questionInHome">
	  				<div :to="{name:'User',params:{name:question.author}}">
	  					<img :src="question.author.headImg_url" class="question-touxiang">
	  				</div>
	  				<div class="question-title" tag="h4" @click="goQuestionDetail(question)">
	  				{{question.title}}</div>
	  				<span class="reply">回答数：{{question.answers.length}}</span>
	  			</div>
	  			
	  		</div>
  	</div>
  </div>
</template>

<script>
import fetch from '@/utils/fetch'
import axios from 'axios'
export default {
  name: 'ArticleAndQuestion',
  data(){
  	return{
		  data: null,
  		articleInHome:[],
		questionInHome:[],
	  }

  },
  methods:{
	goArticleDetail(article){
		// console.log("article\n")
		// console.log(article)
		this.$store.commit("updateSA", article)
		// console.log("selected article\n")
		// console.log(this.$store.getters.selectedArticle)
		this.$router.push({ name: 'ArticleDetail', params: {id:article.art_id}})
	},
	goQuestionDetail(question){
		// console.log('ques')
		// console.log(question)
		this.$store.dispatch("setSelectedQ", question)
		this.$router.push({ name: 'QuestionDetail', params: {id:question.question_id}})
	},
  	clickToArticle(){
  		this.$router.push("/publisharticle")
  	},
  	clickToQuestion(){
  		this.$router.push("/publishQuestion")
  	}
  },
  beforeCreate(){
	  
	//   console.log(this.$store.Url+"ArticleServlet")
	 for(let i = 1; i <= 5; i++){
		//  fetch({
 		// 	method: 'get',
 		// 	url: "/ArticleServlet?method=show&art_id="+i
		// 	})
		 axios.get(this.$store.getters.Url+"/ArticleServlet?method=show&art_id="+i)
  		.then(response=>{
			// console.log(response)
  			if(response.data){
			 	 let data = response.data;
				//   console.log(this.data)
				 this.articleInHome.push(data)
  			}
  		})
  		.catch(error=>{
  			console.log(error)
		  })
		  
		 axios.get(this.$store.getters.Url+"/QuestionServlet?method=show&ques_id="+i)
  		.then(response=>{
			// console.log(response)
  			if(response.data){
			 	 let data = response.data;
				//    console.log(data)
				 this.questionInHome.push(data)
  			}
  		})
  		.catch(error=>{
  			console.log(error)
  		})
	 }
	
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content-container{
	position:relative;
	width: 800px;
	margin:0 auto ;
	padding-top:60px;
}
.article-container,.question-container{
	background-color: #fff;
	box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
}
.area-title{
	margin-top: 40px;
	height: 40px;
	line-height: 40px;
	font-size: 20px;
	padding-left: 20px;
	border-bottom: 1px dotted #E9E9E9;
}
.article-btn,.question-btn{
	float:right;
	margin:5px 5px;
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
.question-touxiang{
	position: absolute;
	height: 30px;
	left: 10px;
}
.question-item{
	height: 30px;
    padding: 10px 20px 10px 55px;
    border-bottom: 1px dotted #E9E9E9;
}
.question-title{
	margin: 5px;
	height: 20px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: inline-block;
}
.reply{
	float:right;
	font-size:10px;
	color: #888;
	margin-top: 18px
}

</style>
