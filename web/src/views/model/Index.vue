<template>
  <a-layout style="height: 100%">
    <a-layout-sider theme="light">
      <a-card title="数据表">
        <template #extra><a href="#"><PlusCircleOutlined /></a></template>
        <TableList :tableMap="tableMap"/>
      </a-card>
    </a-layout-sider>
    <a-layout>
      <a-layout-content>2</a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import TableList from "./TableList.vue";
import {PlusCircleOutlined} from "@ant-design/icons-vue";
export default {
  name: 'Model',
  props: {
    projectId: {
      type: String
    }
  },
  components:{TableList,PlusCircleOutlined},
  data() {
    return {
      tableMap: new Map()
    }
  },
  created() {
    let i = 1;
    setInterval(() => {
      i = i + 1
      this.tableMap.set(i, {
        tableId: i,
        label: '中文表名' + i,
        code: 'table_name' +i
      })
    }, 1000)
  }
}
</script>
<style>

</style>