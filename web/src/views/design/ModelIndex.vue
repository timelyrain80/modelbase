<template>
  <div>
    <a-page-header :title="project.projectName" @back="doBack()">
      <template #extra>
        <div>
          <a-avatar-group>
            <a-avatar v-for="(item) in onlineUserList" :title="item.name">{{ item.name }}</a-avatar>
          </a-avatar-group>
        </div>
      </template>
    </a-page-header>
    <a-layout style="height: 100%">
      <a-layout-sider theme="light" width="300px">
        <a-card title="数据表">
          <template #extra>
            <a href="#" @click="checkAndOpenTable('')">
              <PlusCircleOutlined/>
            </a>
          </template>
          <TableItem v-for="(item,idx) in tableList"
                     :key="idx"
                     :table="item"
                     :checkable="true"
                     :selected="item.tableId === currentTable.tableId"
                     @select="checkAndOpenTable(item.tableId)"/>

        </a-card>
      </a-layout-sider>
      <a-layout>
        <a-layout-content>
          <TableEdit :project-id="project.id"
                     :table-data="currentTable"
                     ref="tableEditPanel"/>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </div>
</template>

<script setup>
import {PlusCircleOutlined} from "@ant-design/icons-vue";
import TableEdit from "./TableEdit.vue";
import TableItem from "@/views/design/TableItem.vue";
import projectReq from "@/request/Project";
import {computed, onMounted, ref} from "vue";
import projectStore from "@/stores/stores";
import {message, Modal} from "ant-design-vue";
import {useRoute, useRouter} from "vue-router";
import sseReq from "@/request/sse";

const project = ref({id: ''}) // 项目信息
const sse = ref(null) // 项目信息
const currentTable = ref({}) // 当前选中的table定义
// 计算属性
const tableList = computed(() => {
  return projectStore.tableStore().list()
})
const onlineUserList = computed(() => {
  return projectStore.onlineUserStore().list()
})
// 模板引用
const tableEditPanel = ref()
onMounted(() => {
  doQuery()
  sse.value = sseReq.open(useRoute().params.id)
})

function doBack() {
  useRouter().push({path: '/index'})
}

function doQuery() {
  const id = useRoute().params.id
  projectReq.detail(id).then(r => {
    project.value = r.data.project

    if (r.data.tableList) {
      r.data.tableList.forEach(t => {
        projectStore.tableStore().set(t)
      })
    }
    if (r.data.fieldList) {
      r.data.fieldList.forEach(t => {
        projectStore.fieldStore().set(t)
      })
    }
    // 设置临时的默认属性
    projectStore.onlineUserStore().set({userId: '1', name: 'kang'})
    projectStore.onlineUserStore().set({userId: '2', name: 'xiao'})
    projectStore.projectDataTypeStore().set({
      label: 'varchar',
      value: 'varchar',
      showSize: true,
      showPrecision: false,
    })
    projectStore.projectDataTypeStore().set({
      label: 'int',
      value: 'int',
      showSize: false,
      showPrecision: false,
    })
  }, e => {
    console.error(e)
    message.error(e.title)
  })
}

function openTable(tableId) {
  const data = {}
  if(tableId) {
    const tb = projectStore.tableStore().get(tableId);
    if (tb) {
      Object.assign(data, tb)
    }
    currentTable.value = data;
  }else{
    currentTable.value = data;
    tableEditPanel.value.doEdit({})
  }
}

function checkAndOpenTable(tableId) {
  // if (tableEditPanel.value.checkModified()) {
  //   Modal.confirm({
  //     title: '数据表已修改',
  //     content: '是否放弃修改的内容?',
  //     onOk: () => {
  //       openTable(tableId)
  //     }
  //   })
  // } else {
  //   openTable(tableId)
  // }
  openTable(tableId)
}

</script>

<style scoped>

</style>