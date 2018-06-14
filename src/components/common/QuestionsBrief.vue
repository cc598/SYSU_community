<template>
  <div>
  	    <div class="question-description">
	      <p class="area-title">问答</p>
	      <p class="area-description">你可以在这里问你想知道的校内事</p>
	    </div>
  	<div class="content-container">
  	<div class="question-container">
	  		<div class="question-list">

	  			<div class="question-item" v-for="question in itemInPage">
	  				<div :to="{name:'User',params:{name:question.author}}">
	  					<img :src="question.author.headImg_Url" class="question-touxiang">
	  				</div>
	  				<div class="question-title" @click="goQuestionDetail(question)"
            :to="{name:'QuestionDetail',params:{id:question.id}}" tag="h4"
	  				>{{question.title}}</div>
	  				<span class="reply">回答数：{{question.answers.length}}</span>
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
  name: 'QuestionsBrief',
  data(){
  	return{
			data:[],
  		itemInPage:[],
  		currentPage:1,
  		pageUrl:this.$store.getters.Url+'/QuestionServlet'
  	}
	},
	computed:{
      replyItem(){
        return this.data.slice((this.currentPage-1)*10,(this.currentPage-1)*10+10);
      }
  },
  methods:{
		goQuestionDetail(question){
		// console.log('ques')
		// console.log(question)
		this.$store.dispatch("setSelectedQ", question)
		this.$router.push({ name: 'QuestionDetail', params: {id:question.question_id}})
		},
  	handleCurrentChange(val){
  		this.currentPage = val;
  		axios.get(this.$store.getters.Url+'/QuestionServlet',{
  			params:{
  				method: 'findByKey',
          keyword: ''
  			}
  		})
  		.then(response=>{
  			if(response.data){
  				this.data = response.data;
          // console.log(this.data)
          this.itemInPage = this.replyItem
  			}else{
  				alert("获取失败")
  			}
  		})
  		.catch(error=>{
  			console.log(error)
  		})
  	}
  },
  mounted(){
  	this.handleCurrentChange(1);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.question-description{
  position: absolute;
  top:60px;
  width: 100%;
  height:120px;
  background-color: rgb(226, 199, 131);
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
.question-container{
  position: relative;
  width: 800px;
  margin: 5px auto;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
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
.pegination{
  padding: 15px 180px;
}
</style>