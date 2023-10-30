<template>
  <div>
    <a-page-header :title="project.projectName" @back="doBack()">
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
          <template #extra>
            <a href="#" @click="checkAndOpenTable('')">
              <PlusCircleOutlined />
            </a>
          </template>
          <TableItem v-for="(item,idx) in Array.from(tableMap.values())"
                     :table="item"
                     :checkable="true"
                     :selected="item.tableId === currentTable.table.tableId"
                     @select="checkAndOpenTable(item.tableId)"/>

        </a-card>
      </a-layout-sider>
      <a-layout>
        <a-layout-content>
          <TableEdit :project-id="this.project.id"
                     :data-type="dataType"
                     :table-data="currentTable"
                     ref="tableEditPanel"/>
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
import TableItem from "@/views/design/TableItem.vue";
import {message, Modal} from 'ant-design-vue';
import project from "@/request/Project";

export default {
  name: "ModelIndex",
  components: {Modal, TableItem, TableEdit, FieldEdit, TableList, PlusCircleOutlined},
  data() {
    return {
      id: undefined,
      project: {},
      tableMap: new Map(),
      fieldMap: new Map(),
      currentTable: {
        table: {},
        fieldList: []
      },
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
    this.doQuery()
  },
  methods: {
    doQuery() {
      project.detail(this.id).then(r => {
        this.project = r.data.project

        if (r.data.tableList) {
          r.data.tableList.forEach(t => {
            this.tableMap.set(t.tableId, t)
          })
        }
        if (r.data.fieldList) {
          r.data.fieldList.forEach(t => {
            this.fieldMap.set(t.fieldId, t)
          })
        }

      }, e => {
        console.error(e)
        message.error(e.title)
      })
    },

    doBack() {
      this.$router.push({path: '/index'})
    },
    openTable(tableId) {
      let data = {}
      data.table = this.tableMap.get(tableId)
      if (!data.table) {
        data.table = {}
      }
      data.fieldList = Array.from(this.fieldMap.values()).filter(t => t.tableId == tableId)
      this.currentTable = data;
    },
    checkAndOpenTable(tableId) {
      if (this.$refs.tableEditPanel.checkModified()) {
        Modal.confirm({
          title: '数据表已修改',
          content: '是否放弃修改的内容?',
          onOk: () => {
            this.openTable(tableId)
          }
        })
      } else {
        this.openTable(tableId)
      }
    }
  }

}
</script>

<style scoped>

</style>