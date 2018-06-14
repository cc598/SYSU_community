<template>
  <div class="background">
  	<home-header></home-header>
  	<div class="box">
  		<el-form ref="form" :model="form" >
  			<el-input v-model="form.title" placeholder="请输入标题"
  			class="input"></el-input>
				<el-input v-model="form.classification" placeholder="请输入文章分类"
  			class="input"></el-input>
  			<el-input v-model="form.content" type="textarea" 
  			:autosize="{ minRows: 4, maxRows: 15}"
  			placeholder="请输入内容"
  			class="input"></el-input>
  			<el-button type="primary" @click="onSubmit">立即发布</el-button>
  			<el-button @click="cancel">取消</el-button>
  		</el-form>
  	</div>
  </div>
</template>

<script>
// 输入框不得为空！
import HomeHeader from '../common/HomeHeader'
import axios from 'axios'
export default {
  name: 'PublishArticle',
  components:{
    HomeHeader,
  },
  data(){
  	return {
  		form:{
  			title:'',
				content:'',
				method:'add',
				classification:'',
  		},
  		articlePostUrl:this.$store.getters.Url+'/ArticleServlet'
  	}
  },
  methods:{
  	onSubmit(){
			var that = this
		  this.$confirm('即将创建文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post(this.articlePostUrl,this.form)
  		.then(response=>{
  			
  		})
  		.catch(error=>{
  			console.log(error)
  		})
          this.$message({
            type: 'success',
            message: '提交成功!'
		  });
		  that.$router.go(-1)
        }).catch(() => {
          
          this.$message({
            type: 'info',
            message: '已取消提交'
          });          
        }).finally(()=> {

        })
  		
  	},
  	cancel(){
  		this.$router.go(-1);
  	}
  },
  mounted(){
    //   console.log(this.$store)
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
.box{
	width: 800px;
	padding: 50px;
	margin: 60px auto;
	background-color: #fff;
}
.background{
	position: relative;
	background-color: #ddd;
	height: 100%;
}
.input{
	margin:5px 0;
}
</style>