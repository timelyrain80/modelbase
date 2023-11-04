<template>
  <div>test page</div>
  <button @click="add">add</button>
  <div v-for="(item,idx) in tableList">{{ item.label }}</div>


</template>
<script setup>
import {tableStore} from "@/stores/stores";
import {computed} from "vue";

const tableList = computed(() => {
  return tableStore().list()
})

function add() {
  tableStore().set({tableId: Math.random(), label: Math.random()})
}

</script>
