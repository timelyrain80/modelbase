
<template>
  <div :class="[this.selected ? 'selected' : '', 'table-item']" @click="doClick(this.data)">
    <div style="display: inline-flex;">
      <a-checkbox v-model:checked="data._checked" class="selector" v-if="this.checkable"/>
      <div>
        <div class="label">{{ data.label }}</div>
        <div class="code">{{ data.code }}</div>
      </div>
    </div>
    <a class="operator" @click.stop="doEdit(this.data)">
      <EditOutlined/>
    </a>
  </div>
</template>

<script>
import EditOutlined from '@ant-design/icons-vue'

export default {
  components: { EditOutlined },
  props: {
    selected: {
      type: Boolean
    },
    checkable: {
      type: Boolean
    },
    data: {
      type: Object
    }
  },
  methods: {
    doEdit(obj) {
      this.$emit('edit', obj)
    },
    doClick(obj){
      this.$emit('click', obj)
    }
  }
}

</script>

<style scoped>
.selected {
  background-color: lightblue;
}

.table-item {
  border-radius: 3px;
  padding: 2px 4px 2px 4px;
  display: flex;
  justify-content: space-between;
  vertical-align: middle;
}
.selector{
  padding-right: 5px;
}
.label{
  font-size: 10px;
}
.code{
  font-size: 8px;
  color: gray;
}
.operator{
  padding-left: 5px;
}

</style>