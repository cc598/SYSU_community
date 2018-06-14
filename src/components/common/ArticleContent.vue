<template>
  <div>
    <i class="el-icon-document wenhao"></i>
  	<div class="content-container">
      <div class="article-container" v-if="data">
        <div class="article-detail">
          <h2 class="article-title">{{data.title}}</h2>
          <div class="look-and-comment">
            <span>评论：{{data.comments.length}}</span>
              <!-- 浏览：{{data.visit_count}}， -->
          </div>
          <div class="author">
            <img :src="data.author" class="touxiang">
            <div class="name-and-time">
            <span class="author-name">{{data.author}}</span>
            <span class="publish-time">{{data.releaseTime}}</span>
            </div>
          </div>
          <div class="message" v-html="data.content">
           
          </div>
        </div>
        <div class="article-comment">
          <h2 class="huitie">回帖</h2>
          <div class="reply" v-for="reply in data.comments">
            <div class="replyer">
              <img :src="reply.author" class="touxiang">
                <div class="name-and-time">
                <span class="replyer-name">{{reply.author}}</span>
                <span class="reply-time">{{reply.releaseTime}}</span>
                </div>
            </div>
            <div class="reply-message" v-html="reply.content">
             
            </div>
          </div>
          
          <div class="to-reply">
            <el-input
              type="textarea" v-model="form.content"
              :rows="4" class="input-area"
              placeholder="请输入内容"
            >
            </el-input>
            <el-button type="primary" class="submit-btn" @click="submitComment" size="small">提交评论</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ArticleContent',
  data(){
    return{
      data:{},
      articleUrl: this.$store.getters.Url+'ArticleServlet',
      currentPage:1,
      form: {
        content: "",
        method: 'add',
        art_id: this.$route.params.id
      }
    }
  },
  computed:{
      replyItem(){
        return this.data.comments.slice((this.currentPage-1)*10,(this.currentPage-1)*10+10);
      },
      selected() {
        return this.$store.getters.selectedArticle
      }
  },
  methods: {
    submitComment() {
      console.log(this.$route.params.id)
      this.$confirm('即将提交评论, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post(this.$store.getters.Url+'CommentServlet',this.form)
          .then(response=>{
            this.data.comments.push(response.data)
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
    // console.log(this.articleUrl)
    this.data = this.selected
    // console.log(this.data)
  },
  beforeDestroy() {
    this.$store.dispatch("setSelectedA", {})
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.reply-message *{
  max-width: 750px;
}
.content-container{
  width: 100%;
  background-color:rgb(154, 206, 199);
  margin:60px auto 0;
  padding-top: 20px;
  padding-bottom: 80px;
}
.article-container{
  width: 800px;
  margin: 0 auto;
}
.article-detail{
  position: relative;
  padding:15px;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
  margin-bottom: 15px;
}
.article-title{
  margin: 0;
}
.author,.replyer{
  position: relative;
  width:100%;
  height: 70px;
  background-color: #f8f8f8;
}
.touxiang{
  position: absolute;
  left: 10px;
  top:10px;
  height:50px;
}
.look-and-comment{
  position: relative;
  right: 0;
  font-size:12px;
  color: #888;
  height:20px;
}
.look-and-comment>span{
  position: absolute;
  right: 0;
}
.author-name,.replyer-name{
  font-size: 12px;
  color: #01AAED;
  font-weight: bold;

}
.publish-time,.reply-time{
   font-size: 12px;
   color: #999;
}
.name-and-time{
  display: inline-block;
  margin-left: 80px;
  margin-top: 10px;
}
.message{
  margin:20px 0;
}
.article-comment{
  position: relative;
  padding:15px;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgba(0,0,0,.05);
}
.huitie{
  margin:0;
  border-bottom: solid #888 ;
}
.reply{
  position: relative;
  width: 100%;
  padding:20px 0;
  border-bottom: 2px dotted #E9E9E9;
}
.reply-message{
  padding:10px 10px;
}

.pagination{
  padding-top: 10px;
  text-align: center;
}
.to-reply{
  height: 150px;
  padding:20px 20px;
}
.input-area{
  margin:0 auto;
}
.submit-btn{
  margin-top: 15px;
}
.wenhao{
	font-size: 40px;
	display: block;
	text-align: center;
}
</style>