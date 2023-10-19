<template>
  <a-form ref="tableForm" :model="tableData" :rules="tableRules">
    <a-page-header>
      <template #title>
        <span v-if="!ctl.editTable">{{ tableData.label }}</span>
        <a-form-item name="label" v-else label="表名称">
          <a-input v-model:value="tableData.label" placeholder="表名称"/>
        </a-form-item>
      </template>
      <template #subTitle>
        <a-space>
          <span v-if="!ctl.editTable">{{ tableData.code }}</span>
          <a-form-item name="code" v-else label="表编码">
            <a-input v-model:value="tableData.code" placeholder="表编码"/>
          </a-form-item>
          <a @click="ctl.editTable = !ctl.editTable" v-if="!ctl.editTable">
            <FormOutlined/>
          </a>
        </a-space>
      </template>
      <template #extra>
        <a-button @click="doReset">重置</a-button>
        <a-button type="primary">保存</a-button>
      </template>
    </a-page-header>
    <a-divider/>
    <div class="action-bar">
      <a-button @click="this.tableData.fieldData.push({})">添加</a-button>
      <a-button @click="this.tableData.fieldData.push({})">删除</a-button>
    </div>
    <a-table :pagination="false" :columns="fieldHeader" :data-source="tableData.fieldData" :draggable="true"
             size="small">
      <template #bodyCell="{text, record, index, column}">
        <div v-if="column.dataIndex=='fieldId'">
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
        <a-form-item v-else-if="column.dataIndex == 'label'" :rules="{required:true,message:'222'}"
                     :name="['fieldData',index,'label']">
          <template #tooltip>
            11
          </template>
          <a-input v-model:value="record.label"/>
        </a-form-item>

        <a-input v-else-if="column.dataIndex == 'code'" v-model:value="record.code"/>
        <a-checkbox v-else-if="column.dataIndex == 'pk'" v-model:checked="record.pk"/>
        <a-checkbox v-else-if="column.dataIndex == 'notNull'" v-model:checked="record.notNull"/>
        <a-checkbox v-else-if="column.dataIndex == 'autoIncrease'" v-model:checked="record.autoIncrease"/>
        <a-select v-else-if="column.dataIndex == 'dataType'" v-model:value="record.dataType" style="width: 100px"
                  :options="Object.values(this.dataType)"/>
        <a-input-number v-else-if="column.dataIndex == 'size'" v-model:value="record.size" style="width: 80px"
                        v-if="shown(record)[0]"/>
        <a-input-number v-else-if="column.dataIndex == 'precision'" v-model:value="record.precision"
                        v-if="shown(record)[1]"/>
        <a-input v-else-if="column.dataIndex == 'comment'" v-model:value="record.comment"/>
        <a-input v-else-if="column.dataIndex == 'dict'" v-model:value="record.dict"/>
      </template>
    </a-table>
  </a-form>
  {{ JSON.stringify(this.tableData.fieldData) }}

</template>

<script>
import {FormOutlined, FullscreenExitOutlined, FullscreenOutlined} from "@ant-design/icons-vue";
import {message} from "ant-design-vue";

export default {
  name: "TableEdit",
  components: {FullscreenOutlined, FullscreenExitOutlined, FormOutlined},
  data() {
    return {
      ctl: {editTable: false},
      tableRules: {
        label: [{required: true, message: '表名不能为空'}],
        code: [{required: true, message: '表编码不能为空'}]
      },
      dataType: {
        varchar: {
          label: 'varchar',
          value: 'varchar',
          showSize: true,
          showPrecision: false,
        },
        int: {
          label: 'int',
          value: 'int',
          showSize: false,
          showPrecision: false,
        }
      },
      tableData: {
        label: '中文',
        code: 'prf_setting',
        fieldData: [{
          fieldId: '1', label: '主键', code: 'code', pk: true, shown: false
        }],
      },
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
    doReset() {
      this.$refs.tableForm.resetFields()
      this.ctl.editTable = false
    },
    doCheck() {


      // this.$refs.fieldForm.validate()
      // this.fieldData.forEach((t, idx) => {
      //   if (!t.label || t.label == '') {
      //     console.log(this.$refs['label_' + idx])
      //     console.log(this.$refs['label_' + 10])
      //
      //     this.$refs['label_' + idx].status = 'error'
      //   }
      // })
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
      message.success('saved')
      if (!this.formData.tableId) {
        this.formData.tableId = new Date().getTime()
      }
      this.$emit('on-save', this.formData)
      this.doClose()
    }
  },
}
</script>

<style scoped>
.ant-form-item {
  margin-bottom: 0;
}
</style>