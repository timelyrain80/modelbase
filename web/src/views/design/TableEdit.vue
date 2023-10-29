<template>
  <a-form ref="tableForm" :model="tableData.table" :rules="tableRules">
    <a-page-header>
      <template #title>
        <span v-if="!ctl.editTable && this.tableData.table.tableId">{{ tableData.table.label }}</span>
        <a-form-item name="label" v-else label="表名称">
          <a-input v-model:value="tableData.table.label" placeholder="表名称"
                   @change="this.tableData.table._changed = true"/>
        </a-form-item>
      </template>
      <template #subTitle>
        <a-space>
          <span v-if="!ctl.editTable && this.tableData.table.tableId ">{{ tableData.table.code }}</span>
          <a-form-item name="code" v-else label="表编码">
            <a-input v-model:value="tableData.table.code" placeholder="表编码"
                     @change="this.tableData.table._changed = true"/>
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
  </a-form>
  <a-form ref="fieldForm" :model="tableData">
    <a-divider/>
    <FieldEdit :table-data="tableData" :data-type="dataType"/>
  </a-form>
  {{ JSON.stringify(this.tableData) }}
</template>

<script>
import {FormOutlined} from "@ant-design/icons-vue";
import {message} from "ant-design-vue";
import FieldEdit from "@/views/design/FieldEdit.vue";
import table from "@/request/table";

export default {
  name: "TableEdit",
  components: {FieldEdit, FormOutlined},
  props: {
    projectId: {
      type: String
    },
    dataType: {
      type: Object
    },
    tableData: {
      type: Object
    },
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
    checkModified() {
      let modified = false
      modified = modified || this.tableData._changed

      return modified
    },
    doReset() {
      this.$refs.tableForm.resetFields()
      this.tableData._changed = false
    },
    doSave() {
      // 两个表单一起校验
      Promise.all([this.$refs.tableForm.validate(), this.$refs.fieldForm.validate()]).then((s) => {
        if (!this.tableData.fieldList || this.tableData.fieldList.length === 0) {
          message.error('数据表至少要添加一个字段')
          return
        }
        const savedData = {}
        savedData.projectId = this.projectId
        savedData.table = this.tableData.table
        savedData.fieldList = this.tableData.fieldList
        savedData.deleteFieldIdList = this.tableData.deleteFieldIdList
        table.save(savedData).then(r => {

        }, e => {
          console.error(e)
          message.error(e.title)
        })
        this.$emit('save', this.formData)
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