<template>
  <a-modal v-model:open="ctl.open" @ok="doSave">
    <a-input v-model:value="formData.label"/>
  </a-modal>
</template>

<script>
import {message} from "ant-design-vue";

export default {
  name: "TableEdit",
  components: {},
  data() {
    return {
      ctl: {open: false},
      formData: {}
    }
  },
  methods: {
    doOpen(table) {
      this.formData = table
      this.ctl.open = true
    },
    doSave(){
      message.success('saved')
    }

  }
}
</script>

<style scoped>

</style>