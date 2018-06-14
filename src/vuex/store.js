import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
	state: {
		logStatus: 'not login',
		user:{},
		username: "",
		password: "",
		apiUrl: "http://111.231.194.22:8080/SYSUCommunity/",
		selectedArticle:{},
		selectedQuestion:{},
		Questions:[],
		Articles:[]
	},
	mutations: {
		updateUser(state, _user){
			state.user=_user;
		},
		updateStatus(state, _to='login'){
			state.logStatus = _to;
		},
		updateName(state, _to){
			state.username = _to;
		},
		updateName(state, _to){
			state.password = _to;
		},
		updateSA(state,_to){
			state.selectedArticle = _to
		},
		updateSQ(state,_to){
			state.selectedQuestion = _to
		},
		updateAs(state,_to){
			state.Articles = _to
		},
		updateQs(state,_to){
			state.Questions = _to
		},
		clearAll(state){
			state.username = ""
			state.password = ""
			state.user = {}
			state.logStatus = "not login"
			state.selectedArticle = {}
			state.selectedQuestion = {}
			state.Questions = []
			state.Articles = []
		}
	},
	actions: {
		setCurrentUser({commit},user){
			commit('updateUser',user);
		},
		setStatus({commit}){
			commit('updateStatus')
		},
		setUser({commit}, to){
			commit('updateName', to)
		},
		setPass({commit}, to){
			commit('updateName', to)
		},
		setSelectedA({commit}, to){
			commit('updateSA', to)
		},
		setSelectedQ({commit}, to){
			commit('updateSQ', to)
		},
		setQuestions({commit}, to){
			commit('updateQs', to)
		},
		setArticles({commit}, to){
			commit('updateAs', to)
		}
	},
	getters:{
		Status: state => {
			return state.logStatus
		  },
		Url: state => {
			return state.apiUrl
		},
		User: state => {
			return state.user
		},
		Username: state => {
			return state.username
		},
		Pass: state => {
			return state.password
		},
		Articles: state => {
			return state.Articles
		},
		Questions: state => {
			return state.Questions
		},
		selectedArticle: state => {
			return state.selectedArticle
		},
		selectedQuestion: state => {
			return state.selectedQuestion
		},
	}
});