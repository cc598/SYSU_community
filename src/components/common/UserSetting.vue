<template>
  <div >
  	<el-tabs type="border-card" class="switch-content">
      <el-tab-pane label="我的资料">
      	<div>
      		<el-input placeholder="请输入ID" v-model="username" class="input">
		      <template slot="prepend">新用户名</template>
		    </el-input>
		    <el-input placeholder="请输入邮箱" v-model="email" class="input">
		      <template slot="prepend">新的邮箱</template>
		    </el-input>
		     <el-button type="success" class="btn" :disabled="pane1full" @click="updateInfo(1)">确认修改</el-button>
      	</div>
      </el-tab-pane>
      <!-- <el-tab-pane label="我的头像">
      	<el-button type="primary" icon="el-icon-picture" :disabled="pane2full" @click="updateInfo(2)">上传</el-button>
      </el-tab-pane> -->
      <el-tab-pane label="我的密码">
      	<div>
      		<el-input  class="input" v-model="oldpass">
		      <template slot="prepend">当前密码</template>
		    </el-input>
		    <el-input  class="input" v-model="newpass">
		      <template slot="prepend">新密码</template>
		    </el-input>
		    <el-input  class="input" v-model="confirmpass">
		      <template slot="prepend">确认密码</template>
		    </el-input>
		    <el-button type="success" class="btn" :disabled="pane3full" @click="updateInfo(3)">确认修改</el-button>
      	</div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from 'axios'
export default {
	name: 'UserSetting',
	data() {
		return {
			username:"",
			email:"",
			icon: null,
			sex: "",
			oldpass:"",
			newpass:"",
			confirmpass:"",
			oldInfos: {},
		}
	},
	computed: {
		pane1full() {
			return (this.username != ""  && this.email != "")?false:true;
		},
		pane2full() {
			return (this.icon != null)?false:true;
		},
		pane3full() {
			return (this.oldpass != "" && this.newpass != "" && this.confirmpass === this.newpass)?false:true;
		}
	},
	methods: {
		updateInfo(state) {
			
        this.$confirm('即将提交更新, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
						var newInfo = {}
						newInfo.user_id = this.oldInfos.user_id
						if (state == 1) {
							newInfo.username = this.username
							newInfo.email = this.email
							this.oldInfos.username = this.username
							this.oldInfos.email = this.email
						} else if (state == 3) {
							newInfo.password = this.newpass
							this.oldInfos.password = this.newpass
						}
						// console.log(newInfo)
						
						axios.post(this.$store.getters.Url+"Edit",newInfo
						)
						.then(response=>{
							console.log(response)
							if(response.data == 'success') {
								this.$message({
								type: 'success',
								message: '提交成功!'
								
							});
							this.$router.go("/home")
							}
						}).catch(error=>{
							console.log(error)
						})
          
        }).catch((err) => {
          console.log(err)
          this.$message({
            type: 'info',
            message: '已取消提交'
          });          
        }).finally(()=> {
					this.$store.commit('updateUser',this.oldInfos)
        })
		}
	},
	mounted(){
		axios.get(this.$store.getters.Url+"profileServlet")
		.then(response=>{
			console.log("olddata")
			console.log(response.data)
			if(response.data) {
				this.oldInfos = response.data
			}
		})
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
.input{
	margin: 5px 5px;
	width: 400px;
}
.btn{
	display: block;
	margin:5px;
}
</style>