<template>
  <a-modal v-model:open="ctl.open" @ok="doSave">
    <template #title>
      {{ formData.tableId ? '修改数据表' : '新增数据表' }}
    </template>
    <a-form>
      <a-form-item label="表名称">
        <a-input v-model:value="formData.label"/>
      </a-form-item>
      <a-form-item label="表编码">
        <a-input v-model:value="formData.code"/>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="doClose">关闭</a-button>
      <a-button type="primary" @click="doSave">保存</a-button>
    </template>
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
      let temp = {}
      Object.assign(temp, table)
      this.formData = temp
      this.ctl.open = true
    },
    doClose() {
      this.formData = {}
      this.ctl.open = false
    },
    doSave() {
      message.success('saved')
      if(!this.formData.tableId){
        this.formData.tableId = new Date().getTime()
      }
      this.$emit('on-save', this.formData)
      this.doClose()
    }

  }
}
</script>

<style scoped>

</style>