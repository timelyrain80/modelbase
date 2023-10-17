<template>
<div>
  <a-form>
    <a-form-item label="编码">
      <a-input/>
    </a-form-item>
    <a-form-item label="显示名称">
      <a-input/>
    </a-form-item>
  </a-form>


</div>
</template>

<script>
export default {
  name: "FieldEdit",
  props:{
    table:{
      type: Object
    }
  }
}
</script>

<style scoped>

</style>