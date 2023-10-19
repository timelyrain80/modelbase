<template>
  <div>
    <a-page-header :title="project.name" @back="doBack()">
      <template #extra>
        <div>
          <a-avatar-group>
            <a-avatar v-for="(item) in onlines" :title="item.name">{{ item.name }}</a-avatar>
          </a-avatar-group>
        </div>
      </template>
    </a-page-header>
    <a-layout style="height: 100%">
      <a-layout-sider theme="light" width="300px">
        <a-card title="数据表">
          <template #extra><a href="#" @click="doAddTable">
            <PlusCircleOutlined/>
          </a></template>
          <TableList ref="tableList" :tableMap="tableMap" @table-selected="doSelectedTable"/>
        </a-card>
      </a-layout-sider>
      <a-layout>
        <a-layout-content>
          <TableEdit :data-type="dataType" :table-data="currentTable"/>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script>

import TableList from "./TableList.vue";
import FieldEdit from "./FieldEdit.vue";
import {PlusCircleOutlined} from "@ant-design/icons-vue";
import TableEdit from "./TableEdit.vue";

export default {
  name: "ModelIndex",
  components: {TableEdit, FieldEdit, TableList, PlusCircleOutlined},
  data() {
    return {
      id: undefined,
      project: {},
      tableMap: new Map(),
      currentTable: {},
      onlines: [{name: 'kang'}, {name: 'xiaojuan'}],
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
    }
  },
  created() {
    // 查询
    this.id = this.$route.params.id
    this.project.name = 'kkk'
    this.tableMap.set("ddddd", {
      label: '中文',
      code: 'prf_setting',
      fieldData: [{
        fieldId: '1', label: '主键', code: 'code', pk: true, shown: false
      }],
    })
    this.tableMap.set("aaaa", {
      label: '中文222222',
      code: 'prf_setting',
      fieldData: [{
        fieldId: '1', label: '主键', code: 'code', pk: true, shown: false
      }],
    })
  },
  methods: {
    doBack() {
      this.$router.push({path: '/index'})
    },
    doAddTable() {
      this.$refs.tableList.doAdd()
    },
    doSelectedTable(t) {
      let tmp = {}
      Object.assign(tmp, t)
      this.currentTable = tmp;
    }
  }

}
</script>

<style scoped>
.box {
  display: flex;
}
</style>