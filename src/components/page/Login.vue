<template>
  <div class="background">
  	<div class="log-box">
      <div class="upper">
        <h1 class="title">中大人社区</h1>
        <div class="describe">属于中大人自己的社区</div>
      </div>
      <div class="login-content">
        <el-form :model="form">
             <el-input class="input" v-model="form.username" placeholder="请输入账号"></el-input>
              <el-input class="input" v-model="form.password" placeholder="请输入密码"
              type="password"></el-input>
              <el-input class="input" v-model="form.captcha" placeholder="请输入验证码"
              ></el-input>
          <img id="cap" :src="Captcha" @click="updateCaptcha" alt="验证码加载失败"/>
          <div class="find-password">忘记密码</div>
          <el-button class="log-btn" type="primary" @click="onSubmit">登陆</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import store from '@/vuex/store';
import { mapActions } from 'vuex';
export default {
  name: 'Login',
  store,
  data() {
    return {
      Captcha: null,
      form:{
        username:'',
        password:'',
        captcha:''
      },
      loginCheckUrl:'http://111.231.194.22:8080/SYSUCommunity/LoginServlet'
    }
  },
  computed:{
    GetCaptcha() {
      return this.$store.getters.Url+"GetCaptcha?"+Math.random()*200
    }
  },
  methods: {
    updateCaptcha(){
      // this.Captcha = this.GetCaptcha
      document.getElementById("cap").src = this.$store.getters.Url+"GetCaptcha?"+Math.random()*200
    //   axios.get(this.$store.getters.Url+"GetCaptcha").then(response=>{
    //   if (response.data){
    //     console.log(response)
        
    //     // "data:image/gif;base64,"+response.data
    //   }
    // }).catch(err=>{
    //   console.log(err)
    // })
    },
    onSubmit(){
      if(this.form.username==''){
        this.$message.error('请输入账号');
        return;
      }else if(this.form.password==''){
        this.$message.error('请输入密码');
        return;
      }
      axios({
        method:'post',
        url:this.loginCheckUrl,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        params:{
          username:this.form.username,
          password:this.form.password,
          captcha: this.form.captcha
        }
      })
      .then(response=>{
        console.log(response)
        if(response.data=='success'){
          // this.setCurrent(form.username,response.accountData);首页处理数据
          this.$store.dispatch('setStatus')
          this.$store.dispatch('setUser', this.form.username)
          this.$store.dispatch('setPass', this.form.password)
          axios.get(this.$store.getters.Url+'/ArticleServlet',{
            params:{
              method: 'findByKey',
              keyword: ''
            }
          }).then(response=>{
            if (response.data) {
              this.$store.dispatch('setArticles', response.data)
            }else{
              alert("获取文章失败")
            }
          }).catch(error=>{
            console.log(error)
          })

          axios.get(this.$store.getters.Url+'/QuestionServlet',{
  			  params:{
  				method: 'findByKey',
          keyword: ''
            }
          })
          .then(response=>{
            if(response.data){
              this.$store.dispatch('setQuestions', response.data)
            }else{
              alert("获取问题失败")
            }
          })
          .catch(error=>{
            console.log(error)
          })
          // .finally(()=>{
          //   console.log("login questions")
          //   console.log(this.$store.getters.Questions)
          // })

          axios.get(this.$store.getters.Url+'/UserServlet',{
  			  params:{
  				method: 'myAll',
            }
          })
          .then(response=>{
            if(response.data){
              // console.log("login user info")
              // console.log(response.data)
              // console.log("login now")
              this.$store.dispatch('setCurrentUser', response.data)
            }else{
              alert("获取用户信息失败")
            }
          })
          .catch(error=>{
            console.log(error)
          })

          this.$router.push('/home');
        }else if(response.data=='fail'){
          this.$message.error('密码错误');
        }else {
          this.$message.info('请检查验证码输入正确');
        }
      })
      .catch(error=>{
        console.log(error)
      }).finally(()=>{
        this.updateCaptcha()
      })
    },
    ...mapActions(['setCurrent'])
  },
  beforeMount(){
    this.Captcha = this.GetCaptcha
    
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.log-box{
  position:absolute;
  background-color: #fff;
  height:350px;
  width: 350px;
  left: 50%;
  top:50%;
  margin-left: -175px;
  margin-top: -175px;
}
.title{
  text-align: center;
  color:rgb(0,142,0);
}
.describe{
  text-align: center;
  font-size: 22px;
  color:rgb(0,142,0);
}
.login-content{
  padding:15px 20px;
}
.input{
  margin:3px 0;
}
.log-btn{
  width: 100%;
  margin-top: 5px;
}
.find-password{
  float:right;
  width: 60px;
  right:0;
  font-size:12px;
  text-decoration: underline;
}
.background{
  height: 100%;
  width: 100%;
  background-color: #ddd;
}
</style>
