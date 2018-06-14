<template>
  <div >
  	<el-tabs type="border-card" class="switch-content">
      <el-tab-pane label="我的文章">
        <div >
          <div class="item" v-for="article in user.articles">
            <span class="the-title">{{article.title}}
            </span>
            <span class="time">{{article.releaseTime}}</span>
            <!-- <span class="comment-num">评论:{{article.comments.length}}</span> -->
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="我的提问">
        <div>
          <div class="item" v-for="question in user.questions">
            <span class="the-title">{{question.title}}
            </span>
            <span class="time">{{question.releaseTime}}</span>
            <!-- <span class="comment-num">回答:{{question.answers.length}}</span> -->
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from 'axios'
import store from '@/vuex/store';
// import { mapState } from 'vuex';
export default {
  name: 'UserCenter',
  computed:{
  selected() {
        return this.$store.getters.User
      }
  },
  data(){
    return{
      user:{},
      userUrl:'',
      username:''
    }
  },
  beforeMount(){
    this.user = this.selected
    console.log("user")
    console.log(this.user)
    // axios.get(userUrl,{
    //   params:{
    //     name:username
    //   }
    // })
    // .then(response=>{
    //   if(response.data){
    //     this.data=response.data.data;
    //   }
    // })
    // .catch(error=>{
    //   console.log(error);
    // })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.switch-content{
    position: absolute;
    margin-top: 80px;
    margin-left: 360px;
    height: 498px;
    width: 721px;
}
.item{
  position: relative;
  height: 20px;
  padding-bottom: 5px;
  border-bottom: 1px dotted #E9E9E9;
}
.the-title{
  display: inline-block;
  max-width: 70%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.time,.comment-num{
  font-size: 12px;
  color: #999;
}
.time{
  padding-left: 10px;
}
.comment-num{
  float: right;
  padding:5px;
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
</style>