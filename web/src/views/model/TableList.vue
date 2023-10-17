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
    <div v-for="(item, idx) in Array.from(tableMap.values())" :key="idx"
         @click="doTableSelect(item)"
         :class="['tableRow',this.selectedTableId === item.tableId ? 'tableSelected' : '']">
      <div>
        <a-checkbox v-show="ctl.batchFlag" :value="item.tableId" v-model:checked="item._checked"/>
      </div>
      <div @click="doTableSelect(item)">
        <div>{{ item.label }}</div>
        <div>{{ item.code }}</div>
      </div>
      <div>
        <a-space>
          <a @click="doEdit(item)">
            <EditOutlined/>
          </a>
          <a-popconfirm title="确认删除" ok-text="是" cancel-text="否" @confirm="doDelete([item.tableId])">
            <a>
              <DeleteOutlined/>
            </a>
          </a-popconfirm>
        </a-space>
      </div>
    </div>
  </div>

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
      checkedList: [],
      selectedTableId: undefined
    }
  },
  methods: {
    doAdd() {
      this.$refs.tableEdit.doOpen({})
    },
    doEdit(table) {
      this.$refs.tableEdit.doOpen(table)
    },
    doSave() {

    },
    // 按id数组删除
    doDelete(idList) {
      if (!idList || idList.length === 0) {
        return
      }
      // todo req
      idList.forEach(t => {
        this.tableMap.delete(t)
      })
    },
    // 批量删除， 查找删除标记得到删除id数组，并调用删除方法
    doBatchDelete() {
      let idList = []
      Array.from(this.tableMap.values()).forEach(t => {
        if (t._checked) {
          idList.push(t.tableId)
        }
      })
      this.doDelete(idList)
    },
    // table
    onTableSave(table) {
      this.tableMap.set(table.tableId, table)
    },
    doTableSelect(table) {
      this.selectedTableId = table.tableId
      this.$emit('table-selected', table)
    }
  }

}
</script>

<style scoped>
.tableList .tableRow, .tableAct {
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

.tableSelected {
  background-color: lightgrey;
}

</style>