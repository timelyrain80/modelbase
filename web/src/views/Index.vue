<template>
  <div>
    <div>
      <a-space>
        <router-link to="/index/test1">1111</router-link>
        <router-link to="/index/test2">222</router-link>
      </a-space>
    </div>
    <router-view v-slot="{ Component }">
      <keep-alive>
        <component :is="Component" />
      </keep-alive>
    </router-view>


  </div>
</template>

<script>
export default {
  name: "Index.vue"
}
</script>

<style scoped>

</style>