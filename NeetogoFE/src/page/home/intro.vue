<template>
  <div>
     <h1>{{ msg }}</h1>
    <h3>You can start NEETOGO by clicking <a href="/location" class="start_btn">here</a></h3>
    <h2>Essential Links</h2>
    <ul>
      <li><a href="https://github.com/jiwonsim/Neetogo" target="_blank">Neetogo</a></li>
      <li><a href="https://github.com/dooriburn" target="_blank">Dooriburn</a></li>
      
    </ul>
    <h2>CreatedBy</h2>
    <ul>
      <li><a href="https://github.com/jiwonsim" target="_blank">jiwonsim</a></li>
    </ul>
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
.start_btn {
  background: none;
  border: none;
  cursor: pointer;
  margin: 0;
  padding: 0; 
  font-size: 20px;
  font-weight: bold;
  color:slateblue;
  border: none;
  outline: none;
}
</style>
