
<template>
  <div :class="[this.selected ? 'selected' : '', 'table-item']" @click="doClick(this.table)">
    <div style="display: inline-flex;">
      <a-checkbox v-model:checked="table._checked" class="selector" v-if="this.checkable"/>
      <div>
        <div class="labelDiv">{{ this.table.label }}</div>
        <div class="codeDiv">{{ this.table.code }}</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  components: {  },
  props: {
    selected: {
      type: Boolean
    },
    checkable: {
      type: Boolean
    },
    table: {
      type: Object
    }
  },
  methods: {
    doEdit(obj) {
      this.$emit('edit', obj)
    },
    doClick(obj){
      this.$emit('select', obj)
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
  padding: 0 4px 0 4px;
  margin: 2px 0 2px 0;
  display: flex;
  justify-content: space-between;
  vertical-align: middle;
  cursor: pointer;
}
.selector{
  padding-right: 5px;
}

.labelDiv {
  font-size: 10px;
}

.codeDiv {
  font-size: 8px;
  color: rgb(128, 128, 128);
}
.operator{
  padding-left: 5px;
}

</style>