<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  beforeMount(){
       var user = localStorage.getItem('user', "")
       var log = localStorage.getItem('logStatus', "")
      if (log != "not login" && user != "") {
        this.$store.commit("updateName", user)
        this.$store.commit("updateStatus", log)
      }
  }
}
</script>

<style>

</style>
