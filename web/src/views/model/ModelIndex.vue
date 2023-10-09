<template>
  <div class="header">
    <div>项目名称</div>
    <div><a-input placeholder="搜索"/></div>
    <div>
      <a-avatar-group>
        <a-avatar/>
        <a-avatar/>
        <a-avatar/>
      </a-avatar-group>
    </div>
  </div>
  <a-layout>
    <a-layout-sider theme="light" width="300px">
      <a-card title="数据表" :body-style="{padding:'3px'}">
        <template #extra><a href="#" @click="doAdd">
          <PlusCircleOutlined/>
        </a></template>
        <TableList ref="tableList" :tableMap="tableMap"/>
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
  components: {TableList, PlusCircleOutlined},
  data() {
    return {
      tableMap: new Map(), // 数据表定义
      onlineMap: new Map(),
    }
  },
  created() {

  },
  methods:{
    doAdd(){
      this.$refs.tableList.doAdd()
    }
  }
}
</script>
<style>
.header{
  display: flex;
  justify-content: space-between;
  padding: 5px 30px 5px 30px;
  justify-items: center;
}
</style>