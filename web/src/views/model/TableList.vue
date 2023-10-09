<template>
  <div class="tableList">
    <div class="tableAct">
      <div>
        <a-checkbox v-model:checked="ctl.batchFlag"/>
      </div>
      <div>
        批量
      </div>
      <div>
        <a-popconfirm title="是否要删除" @confirm="doBatchDelete">
        <a v-show="ctl.batchFlag">批量删除</a>
        </a-popconfirm>
      </div>
    </div>
    <div v-for="(item, idx) in Array.from(tableMap.values())" :key="idx" class="tableRow">
      <div>
        <a-checkbox v-show="ctl.batchFlag" :value="item.tableId" v-model:checked="item._checked"/>
      </div>
      <div>
        <div>{{ item.label }}</div>
        <div>{{ item.code }}</div>
      </div>
      <div>
        <a-space>
          <a @click="">
            <EditOutlined/>
          </a>
          <a>
            <DeleteOutlined/>
          </a>
        </a-space>
      </div>
    </div>
    <div>{{tableMap}}</div>
  </div>
  <TableEdit ref="tableEdit"/>
</template>
<script>
import {EditOutlined, DeleteOutlined} from "@ant-design/icons-vue";
import {message} from "ant-design-vue";
import TableEdit from "./TableEdit.vue";


export default {
  name: "TableList",
  components: {TableEdit, EditOutlined, DeleteOutlined},
  props: {
    tableMap: {
      type: Map
    }
  },
  data() {
    return {
      ctl: {
        batchFlag: false
      },
      formData: {},
      checkedList:[]
    }
  },
  methods: {
    doAdd() {
      this.$refs.tableEdit.doOpen({})
    },
    doEdit(table){
      this.$refs.tableEdit.doOpen(table)
    },
    doSave() {

    },
    doDelete(idList) {

    },
    doBatchDelete(){
      message.success('deleted')
      message.info("22")
      message.warn('2234')
    }
  }

}
</script>

<style scoped>
.tableList .tableRow,.tableAct {
  display: flex;
  padding: 5px;
}
.tableList .tableRow:hover {
  background-color: lightgray;
}

.tableList div div {
  padding-left: 5px;
  display: block;
}

.tableList div:last-child {
  margin-left: auto;
}

.tableList div div:last-child {
  color: darkgrey;
}

</style>