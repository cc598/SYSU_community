<template>
  <div>
  	<div class="content-container">
  		<div class="user-info">
  				<img :src="url+data.icon" class="touxiang">
  				<h1 class="name">{{data.username}}</h1>
  				<p class="personality">{{data.college}}</p>
				  <p class="email">{{data.email}}</p>
  		</div>
  		<div class="action">
	  		<div class="publish">
	  			<div class="action-title">他最近发表的文章</div>
	  			<div class="publish-box">
		  			<div class="publish-item" v-for="article in data.articles">
		  				<span class="item-title">{{article.title}}</span>
		  				<span class="item-time">{{article.releaseTime}}</span>
		  				<!-- <span class="item-reply">{{article.reply_count}}个评论</span> -->
		  			</div>
		  			
	  			</div>
	  		</div>
	  		<div class="attend">
	  			<div class="action-title">他最近发表的提问</div>
	  			<div class="publish-box">
		  			<div class="publish-item" v-for="question in data.questions">
		  				<span class="item-title">{{question.title}}</span>
		  				<span class="item-time">{{question.releaseTime}}</span>
		  				<!-- <span class="item-reply">{{question.reply_count}}个回答</span> -->
		  			</div>
		  			
	  			</div>
	  		</div>
  		</div>

  	</div>
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  data(){
  	return{
		url : this.$store.getters.Url,
  		data:{},
  		info_url:this.$store.getters.Url+"UserServlet?method=myAll",
  		currentname:''
  	}
  },
  computed:{
	  selected() {
        return this.$store.getters.User
      }
  },
  beforeMount(){
	  this.data = this.selected
  	// axios.get(this.info_url,{
  	// 	params:{
  	// 		name: this.currentname
  	// 	}
  	// })
  	// .then(response=>{
	// 	console.log(response)
  	// 	if(response.data){
  	// 		this.data = response.data;
  	// 	}
  	// })
  	// .catch(error=>{
  	// 	console.log(error)
  	// })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.content-container{
	width: 100%;
	height: 100%;
	position: absolute;
	top:60px;
	background-color: #ddd;
}
.user-info{
	height:200px;
	padding:20px 0;
	text-align: center;
	background-color: #fff;
	box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
	margin-bottom: 10px;
}

.touxiang{
	height: 120px;
	width: 120px;
	border-radius: 50%;
}
.name{
	font-size: 20px;
	font-weight: 600;
	margin-bottom: 0;
}
.personality{
	margin-top: 10px;
}
.action{
	position: relative;
	margin:0 auto;
	padding: 0;
}
.publish,.attend{
	width: 600px;
	float: left;
	height: 400px;
	background-color: #fff;
	margin:10px;
}
.action-title{
	height: 40px;
	line-height: 40px;
	padding-left: 20px;
	border-bottom: 1.5px dotted #E9E9E9;
}
.publish-box{
	padding:5px 15px;
}
.publish-item{
	position: relative;
    border-bottom: 1px dotted #E9E9E9;
    margin-bottom: 7px;
    padding-bottom: 7px;
    line-height: 14px;
}
.item-title{
	display: inline-block;
	max-width: 70%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    font-size: 14px;
}
.item-time,.item-reply{
	display: inline-block;
	font-size: 12px;
    color: #999;
}
.item-time{
	position: relative;
}
.item-reply{
	position: absolute;
	right: 0;
	top:2px;
}
.reply-item{
	 margin-top: 5px;
}
.reply-time{
	color: #999;
}
.title{
	color:#4F99CF;
	padding:0 8px;
}
.reply-message{
	margin-top: 10px;
    padding: 10px 15px;
    background-color: #F2F2F5;
    border-radius: 5px;
}
.reply-message>p{
	margin:0;
}
.reply-item-box{
	padding:5px 10px;
}
</style>