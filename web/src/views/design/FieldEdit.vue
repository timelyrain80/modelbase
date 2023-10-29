<template>
  <div>
    <div class="action-bar">
      <a-button @click="this.tableData.fieldData.push({})">添加</a-button>
      <a-button @click="this.tableData.fieldData.push({})">删除</a-button>
    </div>
    <a-table :pagination="false" :columns="fieldHeader" :data-source="tableData.fieldData" :draggable="true"
             size="small">
      <template #bodyCell="{text, record, index, column}">
        <div v-if="column.dataIndex==='fieldId'">
          <a-space>
            {{ index + 1 }}
            <a-checkbox v-model:checked="record._checked"/>
            <div>
              <a @click="record.shown = !record.shown">
                <FullscreenOutlined v-if="record.shown"/>
              </a>
              <a @click="record.shown = !record.shown">
                <FullscreenExitOutlined v-if="!record.shown"/>
              </a>
            </div>
          </a-space>
        </div>
        <a-form-item v-else-if="column.dataIndex === 'label'" :rules="{required:true,message:'222'}"
                     :name="['fieldData',index,'label']">
          <template #tooltip>
            11
          </template>
          <a-input v-model:value="record.label"/>
        </a-form-item>

        <a-input v-else-if="column.dataIndex === 'code'" v-model:value="record.code"/>
        <a-checkbox v-else-if="column.dataIndex === 'pk'" v-model:checked="record.pk"/>
        <a-checkbox v-else-if="column.dataIndex === 'notNull'" v-model:checked="record.notNull"/>
        <a-checkbox v-else-if="column.dataIndex === 'autoIncrease'" v-model:checked="record.autoIncrease"/>
        <a-select v-else-if="column.dataIndex === 'dataType'" v-model:value="record.dataType" style="width: 100px"
                  :options="Object.values(this.dataType)"/>
        <a-input-number v-else-if="column.dataIndex === 'size'" v-model:value="record.size" style="width: 80px"
                        v-if="shown(record)[0]"/>
        <a-input-number v-else-if="column.dataIndex === 'precision'" v-model:value="record.precision"
                        v-if="shown(record)[1]"/>
        <a-input v-else-if="column.dataIndex === 'comment'" v-model:value="record.comment"/>
        <a-input v-else-if="column.dataIndex === 'dict'" v-model:value="record.dict"/>
      </template>
    </a-table>
  </div>
</template>

<script>
import {FullscreenExitOutlined, FullscreenOutlined} from "@ant-design/icons-vue";

export default {
  name: "FieldEdit",
  components: {FullscreenOutlined, FullscreenExitOutlined},
  props: {
    tableData: {
      type: Object
    },
    dataType: {
      type: Object
    }
  },
  data() {
    return {
      fieldHeader: [
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
    }
  },
  methods: {
    shown(field) {
      const t = this.dataType[field.dataType]
      if (t) {
        return [t.showSize, t.showPrecision]
      } else {
        return [false, false]
      }
    },
  }
}
</script>

<style scoped>
.ant-form-item {
  margin-bottom: 0;
}
</style>