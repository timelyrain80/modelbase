<template>
  <a-form ref="tableForm" :model="tableData" :rules="tableRules">
    <a-page-header>
      <template #title>
        <span v-if="!ctl.editTable">{{ tableData.label }}</span>
        <a-form-item name="label" v-else label="表名称">
          <a-input v-model:value="tableData.label" placeholder="表名称" @change="this.tableData._changed = true"/>
        </a-form-item>
      </template>
      <template #subTitle>
        <a-space>
          <span v-if="!ctl.editTable">{{ tableData.code }}</span>
          <a-form-item name="code" v-else label="表编码">
            <a-input v-model:value="tableData.code" placeholder="表编码" @change="this.tableData._changed = true"/>
          </a-form-item>
          <a @click="ctl.editTable = !ctl.editTable" v-if="!ctl.editTable">
            <FormOutlined/>
          </a>
        </a-space>
      </template>
      <template #extra>
        <a-button @click="doReset">重置</a-button>
        <a-button @click="doSave" type="primary">保存</a-button>
      </template>
    </a-page-header>
    <a-divider/>
    <FieldEdit :table-data="tableData" :data-type="dataType"/>
  </a-form>
  {{ JSON.stringify(this.tableData) }}
</template>

<script>
import {FormOutlined} from "@ant-design/icons-vue";
import {message} from "ant-design-vue";
import FieldEdit from "@/views/model/FieldEdit.vue";

export default {
  name: "TableEdit",
  components: {FieldEdit, FormOutlined},
  props: {
    dataType: {
      type: Object
    },
    tableData: {
      type: Object
    }
  },
  data() {
    return {
      ctl: {editTable: false},
      tableRules: {
        label: [{required: true, message: '表名不能为空'}],
        code: [{required: true, message: '表编码不能为空'}]
      },
    }
  },
  methods: {
    doReset() {
      this.$refs.tableForm.resetFields()
      this.ctl.editTable = false
      this.tableData._changed = false

    },

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
      this.$refs.tableForm.validate().then(() => {
        message.success('saved')
        if (!this.formData.tableId) {
          this.formData.tableId = new Date().getTime()
        }
        this.$emit('on-save', this.formData)
        this.doClose()
      }).catch(err => {

      })
    }
  },
  created() {
    console.info("table edit panel created ")
  }
}
</script>

<style scoped>

</style>