import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/page/Login'
import Home from '@/components/page/Home'
import Article from '@/components/page/Article'
import Question from '@/components/page/Question'
import ArticleDetail from '@/components/page/ArticleDetail'
import QuestionDetail from '@/components/page/QuestionDetail'
import User from '@/components/page/User'
import MyInformation from '@/components/page/MyInformation'
import UserCenter from '@/components/common/UserCenter'
import UserSetting from '@/components/common/UserSetting'
import PublishArticle from '@/components/page/PublishArticle'
import PublishQuestion from '@/components/page/PublishQuestion'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
    	path:'/home',
    	name:'Home',
    	component:Home
    },
    {
      path:'/article',
      name:'Article',
      component:Article
    },
    {
      path:'/question',
      name:'Question',
      component:Question
    },
    {
      path:'/article/detail/:id',
      name:'ArticleDetail',
      component:ArticleDetail
    },
    {
      path:'/question/detail/:id',
      name:'QuestionDetail',
      component:QuestionDetail
    },
    {
      path:'/user/:name',
      name:'User',
      component:User
    },
    {
      path:'/myinformation',
      component:MyInformation,
      children: [
      {
        path:'',
        component:UserCenter
      },
      {
        path:'setting',
        component:UserSetting
      }
      ]
    },
    {
      path:'/publisharticle',
      name:'PublishArticle',
      component:PublishArticle
    },
    {
      path:'/publishQuestion',
      name:'PublishQuestion',
      component:PublishQuestion
    }
  ]
})
