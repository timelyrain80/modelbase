<template>
  <a-page-header>
    <template #title>
      {{ tableData.label }}
    </template>
    <template #subTitle>
      {{ tableData.code }}
    </template>
    <template #extra>
      <a-button @click="doEdit(tableData)" type="primary">{{ title }}</a-button>
    </template>
  </a-page-header>
  <a-modal :title="title" v-model:open="isEdit" @ok="doSave" :mask-closable="false">
    <a-form :model="formData" ref="form1">
      <a-form-item label="表名称" :rules="{required: true, message: '表名称必填'}" prop="label">
        <a-input v-model:value="formData.label"/>
      </a-form-item>
      <a-form-item label="表编码" :rules="{required: true, message: '表编码必填'}" prop="code">
        <a-input v-model:value="formData.code"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import {FormOutlined} from "@ant-design/icons-vue";
import {message} from "ant-design-vue";
import tableReq from "@/request/table";
import {ref, defineProps, computed, onMounted} from "vue";

const props = defineProps({tableData: Object, projectId: String})
// 响应对象
const formData = ref({})
const isEdit = ref(false)

// ref
const form1 = ref(null)

// 计算属性
const title = computed(() => {
  return props.tableData.tableId ? '修改' : '新增'
})

onMounted(() => {
  formData.value = props.tableData
})

function doSave() {
  form1.value.validate().then(ok => {
    tableReq.save({projectId: props.projectId, table: formData.value}).then(ok=>{
      Object.assign(props.tableData, ok.data)
      isEdit.value = false
    }, err=>{

    })

  })
}

function doEdit(table) {
  const temp = {}
  Object.assign(temp, table)
  Object.assign(props.tableData, table)

  formData.value = temp
  isEdit.value = true
}

defineExpose({doEdit, isEdit})
// export default {
//   name: "TableEdit",
//   components: {FieldEdit, FormOutlined},
//   props: {
//     projectId: {
//       type: String
//     },
//     dataType: {
//       type: Object
//     },
//     tableData: {
//       type: Object
//     },
//   },
//   data() {
//     return {
//       ctl: {editTable: false},
//       tableRules: {
//         label: [{required: true, message: '表名不能为空'}],
//         code: [{required: true, message: '表编码不能为空'}]
//       },
//     }
//   },
//   methods: {
//     checkModified() {
//       let modified = false
//       modified = modified || this.tableData._changed
//
//       return modified
//     },
//     doReset() {
//       this.$refs.tableForm.resetFields()
//       this.$refs.fieldForm.resetFields()
//       this.tableData.table._changed = false
//       this.tableData.fieldList.forEach(t => {
//         t._changed = false
//       })
//     },
//     doSave() {
//       // 两个表单一起校验
//       Promise.all([this.$refs.tableForm.validate(), this.$refs.fieldForm.validate()]).then((s) => {
//         if (!this.tableData.fieldList || this.tableData.fieldList.length === 0) {
//           message.error('数据表至少要添加一个字段')
//           return
//         }
//         const savedData = {}
//         savedData.projectId = this.projectId
//         savedData.table = this.tableData.table
//         savedData.fieldList = this.tableData.fieldList
//         savedData.deleteFieldIdList = this.tableData.deleteFieldIdList
//         table.save(savedData).then(r => {
//
//         }, e => {
//           console.error(e)
//           message.error(e.title)
//         })
//         this.$emit('save', this.formData)
//       }).catch(err => {
//
//       })
//     }
//   },
//   created() {
//
//   }
// }
</script>

<style scoped>

</style>