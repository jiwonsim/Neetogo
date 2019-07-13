<template>
  <div id="app">
    
    <img onclick="click()" class="img_btn" width="300" src="./assets/dooriburn_logo.jpg">
    <router-view></router-view>
   
  </div>
</template>

<script>
export default {
  name: 'app',
  methods : {
    
  },
  data () {
    return {
      msg: 'Welcome to Neetogo'
    }
  }
}
</script>

<style>

.img_btn {
  background-color: transparent;
  border-radius: 50%;
  border: none;
  outline: none;
  cursor: pointer;
}
img {
  border-radius: 50%; 
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
