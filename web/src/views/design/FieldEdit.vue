<template>
  <div>
    <div class="action-bar" v-if="shown">
      <a-button @click="ctrl.open=true">添加</a-button>
      <a-button @click="">删除</a-button>
    </div>
    <a-table v-if="shown" :pagination="false" :columns="header"
             :data-source="fieldList"
             size="small">
      <template #bodyCell="{text, record, index, column}">
        <div v-if="column.dataIndex==='fieldId'">
          <a-space>
            {{ index + 1 }}
            <a-checkbox value="record.id"/>
          </a-space>
        </div>
        <div v-else>{{ text }}</div>
      </template>
    </a-table>
  </div>
  <a-modal v-model:open="ctrl.open" title="编辑字段信息" @ok="saveField">
    <a-divider/>
    <a-form :model="formData" ref="form1" :rules="formRules">
      <a-form-item label="字段名称" name="label">
        <a-input v-model:value="formData.label"/>
      </a-form-item>
      <a-form-item label="字段编码" name="code">
        <a-input v-model:value="formData.code"/>
      </a-form-item>
      <a-form-item>
        <a-checkbox v-model:checked="formData.pk"/>
        主键
        <a-checkbox v-model:checked="formData.notNull"/>
        非空
        <a-checkbox v-model:checked="formData.autoIncrease"/>
        自增长
      </a-form-item>
      <a-form-item :rules="{required:true, message: '不能为空'}"
                   :name="['fieldList', index, 'dataType']">
        <a-select v-model:value="formData.dataType" style="width: 100px"
                  :options="Object.values(this.dataType)"/>
      </a-form-item>
      <a-form-item>
        <a-input-number v-model:value="formData.size" style="width: 80px"
                        v-if="shown(formData)[0]"/>
        <a-input-number v-model:value="formData.precision"
                        v-if="shown(formData)[1]"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>

import {computed, ref} from "vue";
import store from "@/stores/stores.js"

// 定义属性
const props = defineProps({projectId: String, tableId: String})
// 定义计算属性
const fieldList = computed(() => {
  return store.fieldStore().list(props.tableId)
})
const shown = computed(() => {
  console.info('props', props)
  return props.tableId != null && props.tableId
})

// 定义响应对象
const ctrl = ref({open: false})
const formData = ref({})
const form1 = ref(null)

function saveField() {
  console.info('saveField')
  form1.value.validate().then(ok => {

  })
}

const header = [
  {title: ' ', width: 80, dataIndex: 'fieldId'},
  {title: '显示名称 *', width: 250, dataIndex: 'label'},
  {title: '编码 *', width: 180, dataIndex: 'code'},
  {title: '主键', width: 60, dataIndex: 'pk'},
  {title: '非空', width: 60, dataIndex: 'notNull'},
  {title: '自增', width: 60, dataIndex: 'autoIncrease'},
  {title: '数据类型 *', width: 100, dataIndex: 'dataType'},
  {title: '长度', width: 80, dataIndex: 'size'},
  {title: '小数位', width: 80, dataIndex: 'precision'},
  {title: '注释', dataIndex: 'comment'},
  {title: '字典', dataIndex: 'dict'},
]
const formRules = {
  label: [{required: true, message: '名称不能为空'}],
  code: [{required: true, message: '名称不能为空'}],
}
</script>

<style scoped>

</style>