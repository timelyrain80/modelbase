<template>
  <div class="tableList">
    <div v-for="(item,idx) in tableMap" :key="idx">
      <div>
        <div>{{ item.label }}</div>
        <div>{{ item.code }}</div>
      </div>
      <div>
        <a-space>
          <a>
            <EditOutlined/>
          </a>
          <a>
            <DeleteOutlined/>
          </a>
        </a-space>
      </div>
    </div>

  </div>
</template>
<script>
import {EditOutlined, DeleteOutlined} from "@ant-design/icons-vue";

export default {
  name: "TableList.vue",
  components: {EditOutlined, DeleteOutlined},
  props: {
    tableMap: {
      type: Map
    }
  }
}
</script>

<style scoped>
.tableList div {
  display: flex;
  justify-content: space-between;
}

.tableList div:hover {
  background-color: lightgray;
}

.tableList div div:last-child {
  color: darkgrey;
}

</style>