<template>
  <div>
    <div class="show-question">
       <i class="el-icon-question wenhao"></i>
   	   <p class="question-description">{{questionContent.title}}</p>
    </div>
    <div class="content-container">
      <div class="reply-container" v-if="questionContent">
        <div class="reply-header">{{questionContent.answers.length}}个回复</div>
        <div class="reply-area">
          <div class="reply-message">
            <router-link :to="{name:'User',params:{name:questionContent.author}}">
              <img :src="questionContent.author.headImg_url" class="touxiang">
            </router-link>
            <div class="name-and-time">
              <span class="name">{{questionContent.author}}</span>
              <span class="time">{{questionContent.releaseTime}}</span>
            </div>
            <div class="message">
              {{questionContent.content}}
            </div>
          </div>
          <div class="reply-message" v-for="reply in questionContent.answers">
            <router-link :to="{name:'User',params:{name:questionContent.author}}">
              <img :src="reply.author.headImg_url" class="touxiang">
            </router-link>
            <div class="name-and-time">
              <span class="name">{{reply.author}}</span>
              <span class="time">{{reply.releaseTime}}</span>
            </div>
            <div class="message">
              {{reply.content}}
            </div>
          </div>
          
        </div>
        <div class="to-reply">
           <el-input
              type="textarea"
              v-model="form.content"
              :rows="4" class="input-area"
              placeholder="请输入内容"
            >
            </el-input>
            <el-button type="primary" class="submit-btn" @click="submitAnswer()" size="small">提交评论</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'QuestionContent',
  data(){
    return{
      questionContent:{},
      questionUrl: this.$store.getters.Url+'QuestionServlet',
      currentPage:1,
      form: {
        content: "",
        method: "add",
        ques_id: this.$route.params.id
      }
    }
  },
  computed:{
    replyItem(){
      return this.questionContent.answers.slice((this.currentPage-1)*10,(this.currentPage-1)*10+10);
    },
      selected() {
        return this.$store.getters.selectedQuestion
      }
  },
    methods:{
      submitAnswer() {
      this.$confirm('即将提交回答, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post(this.$store.getters.Url+'AnswerServlet',this.form)
          .then(response=>{
            this.questionContent.answers.push(response.data)
          })
              this.$message({
              type: 'success',
              message: '提交成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });          
        }).finally(()=> {
            this.form.content = ""
        })
    }
    },
  beforeMount(){
    this.questionContent = this.selected
    console.log(this.questionContent)
  },
  beforeDestroy() {
    this.$store.dispatch("setSelectedQ", {})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.show-question{
  position: absolute;
  top:60px;
  width: 100%;
  height:120px;
  background-color: rgb(254, 181, 77);
}
.question-description{
  font-size: 22px;
  color: #fff;
  text-align: center;
}
.wenhao{
	font-size: 40px;
	display: block;
	text-align: center;
}
.content-container{
  width:100%;
  position: absolute;
  top:180px;
  background-color: #ddd;
  padding-bottom: 50px;
}
.reply-container{
  margin:0 auto;
  width: 800px;
  padding: 10px 0;
}
.reply-header{
  height: 30px;
  padding: 10px;
  line-height: 30px;
  background-color: #f6f6f6;
  border-radius: 3px 3px 0 0;
}
.reply-area{
  width: 100%;
  background-color: #fff;
}
.reply-message{
  position: relative;
  min-height: 70px;
  padding:10px;
  background-color: #fff;
  border-bottom:  2px dotted #E9E9E9 ;
}
.touxiang{
  height: 40px;
  position: absolute;
  top:20px;
  left: 20px;
}
.name-and-time{
  font-size: 14px;
  margin-top: 10px;
  margin-left: 60px;
}
.name{
  color: #01AAED;
  font-weight: bold
}
.time{
   font-size: 12px;
   color: #999;
}
.message{
  padding: 5px 5px 5px 60px;
}
.pagination{
  padding-top: 10px;
  text-align: center;
}
.to-reply{
  background-color: #fff;
  height: 150px;
  padding:20px 20px;
}
.input-area{
  margin:0 auto;
}
.submit-btn{
  margin-top: 15px;
}
</style>